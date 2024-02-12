package myapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegestrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegestrationServlet() {
        super();
    }
    Connection con=null;
    public void init(ServletConfig config)throws ServletException 
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con =DriverManager.getConnection("jdbc:mysql://localhost:3306/myapp","root","Mohit@123");
    		
    	}catch(Exception e) {
    		System.out.print(e);    
    		
    	}
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String username=request.getParameter("username");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
		String query = "insert into user values (?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        
        pst.setString(3,email);
        pst.setString(4,password);
        pst.setString(1,name);
        pst.setString(2,username);
		
        int n =pst.executeUpdate();
        if(n>0) {
        	String html="<html><body>"+
        				"<script>"
        				+ " alert('Regestration Successfully');"
        				+ "</script>";
        	pw.print(html);
        	RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        	rd.include(request, response);
        	
        }
        
		}catch(Exception e) {
			pw.print(e);
		}
		
	}

}
