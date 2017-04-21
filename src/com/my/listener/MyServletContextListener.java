package com.my.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	System.out.println("MyServletContextListener 감시자 객체생성");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	//Tomcat이 켜지자 마자 할 일이 있다면
    	System.out.println("contextInitialized() 호출");
   }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("contextDestroyed() 호출");
    }
	
}
