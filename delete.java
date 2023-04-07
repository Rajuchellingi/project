package user_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/del")

public class delete extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	Connection c=null;
	PreparedStatement ps=null;
	String qry="delete from person where id=?";
	try {
		Class.forName(user.driver);
		 c=DriverManager.getConnection(user.url,user.user,user.password);
		 ps=c.prepareStatement(qry);
		 ps.setInt(1, id);
		int rs= ps.executeUpdate();
		
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
