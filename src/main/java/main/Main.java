package main;

import accounts.AccountService;
import accounts.AccountServiceImpl;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servltes.*;

public class Main {

    public static void main(String[] args) {

        AccountService accountService = new AccountServiceImpl();

        accountService.addNewUser(new UserProfile("admin"));

        //part of jetty server
        //TODO replace this part when migrate to Tomcat
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new LinkSaveServlet(accountService)), "/save");
        context.addServlet(new ServletHolder(new LinkSummaryServlet(accountService)), "/summary");
        context.addServlet(new ServletHolder(new DispatcherServlet()), "/DispatcherServlet");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler,context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
