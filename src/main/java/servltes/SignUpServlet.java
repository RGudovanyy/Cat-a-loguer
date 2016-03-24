package servltes;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet{
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if((login == null) || (password1 == null)){
            response.sendRedirect("");
        }
        if (!password1.equals(password2)){
            response.sendRedirect("/reg.html");
            Gson gson = new Gson();
            String json = gson.toJson("Incorrect password");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
        }
        else {
            UserProfile profile = new UserProfile(login,password1);
            accountService.addNewUser(profile);
            RequestDispatcher rd = request.getRequestDispatcher("signin");
            rd.forward(request,response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
