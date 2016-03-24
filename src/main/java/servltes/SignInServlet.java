package servltes;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet{
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        try {
            request.getRequestDispatcher("/linksMain.html").include(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login == null || password == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try{
            UserProfile profile = accountService.getUserProfile(login);

            if(!profile.getPass().equals(password)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writer.println("Login or password is incorrect");
                writer.flush();
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.include(request,response);

            }

            if(profile.getLogin().equals(login) && profile.getPass().equals(password)){
                response.setStatus(HttpServletResponse.SC_OK);
                writer.println("You successfully logged in");
                writer.println("Welcome," + login);
                Cookie ck = new Cookie("login",login);
                response.addCookie(ck);
                RequestDispatcher rd = request.getRequestDispatcher("main.html");
                rd.include(request,response);

            }
        }
        catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writer.println("Unauthorized");
            response.sendRedirect("/index.html");
        } catch (ServletException e) {
            e.printStackTrace();
        }

        writer.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("SignIn");
    }


}
