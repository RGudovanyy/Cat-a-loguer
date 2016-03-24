package servltes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("In dispatcherServlet");

        RequestDispatcher rd = request.getRequestDispatcher("/public_html/hello.html");
        rd.include(request, response);
    }

}