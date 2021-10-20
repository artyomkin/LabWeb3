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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {

    ServletContext servletContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        resp.sendRedirect("controller");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        List<Double> xValues = Arrays.stream(req.getParameterValues("x"))
                .map(xString -> Double.parseDouble(xString))
                .collect(Collectors.toList());
        List<Double> yValues = Arrays.stream(req.getParameterValues("y"))
                .map(xString -> Double.parseDouble(xString))
                .collect(Collectors.toList());
        List<Double> RValues = Arrays.stream(req.getParameterValues("R"))
                .map(xString -> Double.parseDouble(xString))
                .collect(Collectors.toList());
        Boolean async = req.getParameter("async").equals("true");
        boolean xError = xValues.stream().anyMatch(x -> x < -2 || x > 5);
        boolean yError = yValues.stream().anyMatch(y -> y < -3 || y > 5);
        boolean RError = RValues.stream().anyMatch(R -> R < 1 || R > 5);

        if(xError || yError || RError){
            req.getRequestDispatcher("view/error.jsp").forward(req,resp);
            return;
        }
        servletContext = getServletContext();
        ArrayList<Dot> dots = (ArrayList<Dot>) servletContext.getAttribute("dots");

        if(dots==null){
            dots = new ArrayList<>();
        }

        for (int i = 0; i<xValues.size(); i++){

            for (int j = 0; j<RValues.size(); j++){

                double x = Double.valueOf(xValues.get(i));
                double y = Double.valueOf(yValues.get(0));
                double R = Double.valueOf(RValues.get(j));

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
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("dots", dots);
        if(async){
            Response response = new Response(dots, workingTime, currentTime);
            Gson gson = new Gson();
            String JSONResponse = gson.toJson(response);
            resp.getWriter().print(JSONResponse);
        } else {
            req.getRequestDispatcher("view/tableResults.jsp").forward(req,resp);
        }
    }

}
