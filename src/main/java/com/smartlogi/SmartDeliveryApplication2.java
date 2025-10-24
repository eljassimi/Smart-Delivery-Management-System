package com.smartlogi;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class SmartDeliveryApplication2 {

    public static void main(String[] args) throws LifecycleException {

        System.out.println("\n=== Starting Smart Delivery System ===\n");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8086);
        tomcat.getConnector();

        Context context = tomcat.addContext("", new File(".").getAbsolutePath());


        XmlWebApplicationContext webContext = new XmlWebApplicationContext();
        webContext.setConfigLocation("classpath:applicationContext.xml");
        webContext.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);

        Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();

        System.out.println("=====================================");
        System.out.println("Server started successfully!");
        System.out.println("=====================================");
        System.out.println("\nAPI: http://localhost:8080/api/livreurs");
        System.out.println("Press CTRL+C to stop...\n");

        tomcat.getServer().await();
    }
}
