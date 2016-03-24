package servltes;

import accounts.AccountService;
import dbService.DBService;
import dbService.DBServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinkSaveServlet extends HttpServlet {

    private final AccountService accountService;

    public LinkSaveServlet(AccountService accountService){
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        DBService dbService = new DBServiceImpl();
        String URL = request.getParameter("url");
        String owner = request.getParameter("login");
        try {
            dbService.addLink(URL,owner);
            response.getWriter().println("Link successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){


    }
}
