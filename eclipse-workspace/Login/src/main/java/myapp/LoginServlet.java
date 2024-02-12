package myapp;

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
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
    	
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
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
		String query = "select name from user where username=? and password=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,username);
        pst.setString(2,password);
		ResultSet rs=pst.executeQuery();
        if(rs.next()) {
        	String html="<html><title>MyApp</title><body>"
        		   +" <style>"
        		   +" body {"
        		   +"background-color: #3498db;"
//                   +"display: flex;"
//                   +"justify-content: center;"
//                	+"align-items: center;"
                	+"}"
            		+"</style>"

        			+" <center><h2> Hello "+rs.getString(1)+"</h2>"
                	+"<h3> Choose Your Fav Color</h3>"
                	+"<tabel border='1'><th>"
                	+ "<td>1</td>"
                	+"<td>red</td>"
                	+ "</th>"
                	+"<tr>"
                	+ "<td>2</td>"
                	+"<td>green</td>"
                	+ "</tr>"
//                	+"<tr>"
//                	+ "<td>3</td>"
//                	+"<td>red</td>"
//                	+ "</tr>"
//                	+"<tr><td>4</td>"
//                	+"<td>red</td></tr>"
//                	+"<tr><td>5</td>"
//                	+"<td>red</td></tr>"
                	
                	+"</tabel></center>"
        			+"</body></html>";
        	
        	pw.println(html);
        }
        else {
        	pw.println("<html><title>MyApp</title><body>");
        	pw.println("<h1>NO USER FOUND</h1>");
        	pw.println("</body></html>");
        }
        
		}catch(Exception e) {
			pw.print(e);
		}
		
		
	}

}
