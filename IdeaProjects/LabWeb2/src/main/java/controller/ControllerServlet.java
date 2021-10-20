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
        String[] xVals = req.getParameterValues("x");
        String[] yVals = req.getParameterValues("y");
        String[] RVals = req.getParameterValues("R");

        if(xVals == null || yVals == null || RVals == null){
            req.getRequestDispatcher("view/index.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/areaCheck").forward(req, resp);
        }
    }

}
