	package com.my.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }
    
    public void init() throws ServletException{
    	super.init();
    	String fileName = "handler.properties";
		String fileRealPath =
				getServletContext().getRealPath(fileName);
		Properties env = new Properties();
		try {
			env.load(new FileInputStream(fileRealPath));
			Set<Object> keys = env.keySet();
			ServletContext application = getServletContext();
			
			for(Object key: keys){
				String propertyName = (String) key;
				String className = (String)env.getProperty(propertyName);
				
				if(className == null){
					System.out.println("class("+className+")가 없습니다.");				
				}else{
					Class clazz;
					try {
						clazz = Class.forName(className);	//클래스 로드
						Object obj = clazz.newInstance();
						Handler handler = (Handler)obj;
						application.setAttribute(propertyName, handler);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletPath());
		String path = request.getServletPath();

		ServletContext application = getServletContext();
		Handler handler = (Handler)application.getAttribute(path);
		
		if(handler != null){
			String forwardURL = handler.execute(request, response);
			if(forwardURL !=null){
			     RequestDispatcher rd = request.getRequestDispatcher(forwardURL);
			     rd.forward(request, response);
				
			}
		}				
	}
}
