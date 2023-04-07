package user_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/reg")

public class register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
	    String name=req.getParameter("nm");
		int age=Integer.parseInt(req.getParameter("age"));
		Long phone=Long.parseLong(req.getParameter("ph"));
		String password=req.getParameter("ps");
		Connection c=null;
		PreparedStatement ps=null;
		String qry="insert into person values(?,?,?,?,?)";
		try {
			 Class.forName(user.driver);
			 c=DriverManager.getConnection(user.url,user.user,user.password);
			 ps=c.prepareStatement(qry);
			 ps.setInt(1, id);
			 ps.setString(2, name);
			 ps.setInt(3, age);
			 ps.setLong(4, phone);
			 ps.setString(5, password);
			 ps.executeUpdate();
			 PrintWriter wt=resp.getWriter();
			 wt.write("<html><body><h1> User Saved Successfull..!! </h1></body></html>");
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
			}
			
		}
	}

}
