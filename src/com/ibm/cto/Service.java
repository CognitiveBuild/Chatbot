package com.ibm.cto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Service
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Service" })
public class Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Service() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hi");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String payload = request.getParameter("payload");
		
		JSONObject result = new JSONObject();
		result.put("code", 200);
		result.put("message", "This is service response from IBM Blockchain ("+category+").");
		
		response.getWriter().append(result.toString());

		System.out.println("### Payload received from Blockchain ###");
		System.out.println(payload);
		System.out.println("### /Payload received from Blockchain ###");
	}

}
