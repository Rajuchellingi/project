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
@WebServlet("/view")
public class view extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  Connection c=null;
      PreparedStatement ps=null;
      ResultSet rs=null;
      String qry="select * from person";
      try {
    	  Class.forName(user.driver);
			 c=DriverManager.getConnection(user.url,user.user,user.password);
			 ps= c.prepareStatement(qry);
			rs= ps.executeQuery();
			 RequestDispatcher dp=null;
			 PrintWriter wt=null;
			 if(rs.next()) {
				 dp=req.getRequestDispatcher("view.jsp");
				 HttpSession ses=req.getSession();
				 dp.forward(req, resp);
				
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
