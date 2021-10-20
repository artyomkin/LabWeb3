package model;

import com.google.gson.Gson;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {

    ServletContext servletContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        resp.sendRedirect("controller");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long startTime = System.currentTimeMillis();

        Double[] xValues = (Double[]) req.getAttribute("x");
        Double[] yValues = (Double[]) req.getAttribute("y");
        Double[] RValues = (Double[]) req.getAttribute("R");

        servletContext = getServletContext();
        ArrayList<Dot> dots = (ArrayList<Dot>) servletContext.getAttribute("dots");

        if(dots==null){
            dots = new ArrayList<>();
        }

        for (int i = 0; i<xValues.length; i++){

            for (int j = 0; j<RValues.length; j++){

                double x = Double.valueOf(xValues[i]);
                double y = Double.valueOf(yValues[0]);
                double R = Double.valueOf(RValues[j]);

                        //first quarter      //hit
                boolean hit =
                        (x >= 0 && y >= 0 && y < -x + R/2) ||
                        //second quarter    //hit
                        (x <= 0 && y > 0 && x > -R/2 && y < R) ||
                        //third quarter    //hit
                        (x < 0 && y <= 0 && x * x + y * y < R * R);

                Dot dot = new Dot(x,y,R,hit);
                dots.add(dot);

            }

        }

        long workingTime = System.currentTimeMillis() - startTime;

        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String currentTime = hours + ":" + minutes + ":" + seconds;

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Response response = new Response(dots, workingTime, currentTime);
        Gson gson = new Gson();
        String JSONResponse = gson.toJson(response);
        resp.getWriter().print(JSONResponse);
    }

}
