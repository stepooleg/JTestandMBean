package main.java.servlets;

import main.java.server.ServerUserRegistrable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ServletUser extends HttpServlet {
    static final Logger logger = LogManager.getLogger(ServletUser.class.getName());
    public static final String PAGE_URL = "/home";
    private final ServerUserRegistrable serverUserRegistrable;

    public ServletUser(ServerUserRegistrable serverUserRegistrable) {
        this.serverUserRegistrable = serverUserRegistrable;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String remove = req.getParameter("remove");

        if (remove != null) {
            serverUserRegistrable.removeUser();
            resp.getWriter().println("Goodbay!!");
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = serverUserRegistrable.getUsersLimit();
        int count = serverUserRegistrable.getUsersCount();

        logger.info("Limit: {}. Count: {}", limit, count);

        if (limit > count) {
            logger.info("User pass");
            serverUserRegistrable.addUser();
            resp.getWriter().println("Hello, User!!");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            logger.info("User were rejected...");
            resp.getWriter().println("Server is closed for maintenance!");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
