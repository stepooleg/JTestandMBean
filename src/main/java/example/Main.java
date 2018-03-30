package main.java.example;

import main.java.server.ServerController;
import main.java.server.ServerControllerMBean;
import main.java.server.ServerUserRegistr;
import main.java.server.ServerUserRegistrable;
import main.java.servlets.ServletUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            logger.error("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);

        logger.info("Starting at http://127.0.0.1:" + portString);

        ServerUserRegistrable serverUserRegistrable = new ServerUserRegistr(1);
        ServerControllerMBean serverControllerMBean = new ServerController(serverUserRegistrable);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServerManager:type=ServerUserRegistr");
        mBeanServer.registerMBean(serverControllerMBean, name);

        Server server = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new ServletUser(serverUserRegistrable)), ServletUser.PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("static");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});
        server.setHandler(handlerList);

        server.start();
        logger.info("Server started");
        server.join();

    }
}
