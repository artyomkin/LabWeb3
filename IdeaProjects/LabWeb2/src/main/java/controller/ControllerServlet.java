package controller;

import com.google.gson.Gson;
import model.Dot;
import model.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{

        req.getRequestDispatcher("view/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();
        try {
            Request request = gson.fromJson(body, Request.class);
            System.out.println(body);
            for (int i = 0; i<request.getX().length; i++){
                System.out.println(request.getX()[i]);
            }
            for (int i = 0; i<request.getY().length; i++){
                System.out.println(request.getY()[i]);
            }
            for (int i = 0; i<request.getR().length; i++){
                System.out.println(request.getR()[i]);
            }

            req.setAttribute("x",request.getX());
            req.setAttribute("y",request.getY());
            req.setAttribute("R",request.getR());
            req.getRequestDispatcher("/areaCheck").forward(req, resp);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("controller servlet redirects user to index.jsp");
            req.getRequestDispatcher("view/index.jsp").forward(req,resp);
        }




    }

}
