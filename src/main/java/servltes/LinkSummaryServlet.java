package servltes;

import accounts.AccountService;
import dbService.DBService;
import dbService.DBServiceImpl;
import dbService.DataSets.LinksDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class LinkSummaryServlet extends HttpServlet {

    private final AccountService accountService;


    public LinkSummaryServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String owner = request.getParameter("login");
        DBService dbService = new DBServiceImpl();
        List<LinksDataSet> a = null;
        PrintWriter out = response.getWriter();

        try {
            a = dbService.getLinks(owner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for(LinksDataSet l : a)
            sb.append(l.toString());
        out.println(sb);


    }



}
