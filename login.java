package user_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class login extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	      Long phone=Long.parseLong(req.getParameter("ph"));
	      String password=req.getParameter("ps");
	      Connection c=null;
	      PreparedStatement ps=null;
	      ResultSet rs=null;
	      String qry= "select * from person where phone=? and password=?" ;
	      try {
	    	  Class.forName(user.driver);
				 c=DriverManager.getConnection(user.url,user.user,user.password);
				 ps= c.prepareStatement(qry);
				 ps.setLong(1, phone);
				 ps.setString(2, password);
				rs= ps.executeQuery();
				 RequestDispatcher dp=null;
				 if(rs.next()) 
				 {
					 dp=req.getRequestDispatcher("home.jsp");
					 HttpSession ses=req.getSession();
					 ses.setAttribute("id", rs.getInt("id"));
					ses.setAttribute("name",rs.getString("name"));
					  ses.setAttribute("age", rs.getInt("age"));
					 ses.setAttribute("phone", rs.getLong("phone"));
					 ses.setAttribute("password", rs.getString("password"));
					 dp.forward(req, resp);
					 
				 }
				 else
				 {
					 PrintWriter wt=resp.getWriter();
					 wt.write("<html><body><h1 v>Invalid user and password</h1></body></html>");
					 dp=req.getRequestDispatcher("login.jsp");
					 dp.include(req, resp);
				 } 
	      }
	      catch(SQLException |ClassNotFoundException  e)
			{
				e.printStackTrace();
				
			}
			finally {
				if(c!=null) {
					try {
						
						c.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
				if(ps!=null) {
					try {
					
						ps.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
						
					}
				}if(rs!=null) {
					try {
					
						rs.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
						
					}
			
				}
				
			}
	}
}
