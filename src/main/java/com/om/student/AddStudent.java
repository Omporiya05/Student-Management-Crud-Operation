package com.om.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet( "/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public AddStudent() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("Add.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		String message = null;
		
		/*final String DB_URL    = "jdbc:mysql://localhost:3306/omdb";
		final String DB_USER   = "root";
		final String DB_PWD    = "root@123";*/
		
		
		final String DB_URL    = "jdbc:postgresql://ep-icy-sky-a1672e3v-pooler.ap-southeast-1.aws.neon.tech/neondb";
		final String DB_USER   = "neondb_owner";
		final String DB_PWD    = "npg_q6JKZXoYHIn3";
		
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		String name = request.getParameter("name");
		Double per  = Double.parseDouble(request.getParameter("per"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
			
			con.setAutoCommit(false);
			
			ps  = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?)");
			ps.setInt(1, rno);
			ps.setString(2, name);
			ps.setDouble(3, per);
			
			ps.executeUpdate();
			
			con.commit();
			message = "<div class=\'alert alert-success mt-3\' role=\'alert\'>Record is Saved SuccessFully !!! </div>";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
			message = "<div class=\'alert alert-danger mt-3\' role=\'alert\'>Unable To Saved Record For Roll Number : " + rno + "</div>";
		}
		finally
		{
			try {
				con.close();
			}
			catch(SQLException e)
			{
				//e.printStackTrace();
			}
			
		}
		
		request.setAttribute("msg", message);
		RequestDispatcher rd = request.getRequestDispatcher("Add.jsp");
		rd.forward(request, response);
	}

}
