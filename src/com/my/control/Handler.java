package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;	
}
