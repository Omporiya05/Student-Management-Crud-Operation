package com.om.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.om.entities.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	final String DB_URL    = "jdbc:postgresql://ep-icy-sky-a1672e3v-pooler.ap-southeast-1.aws.neon.tech/neondb";
	final String DB_USER   = "neondb_owner";
	final String DB_PWD    = "npg_q6JKZXoYHIn3";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Student> list = new ArrayList<>();

		
		
		String query = "";
		
		String rno  = request.getParameter("rno");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			query = "SELECT * FROM STUDENT order by rno";
		}
		else if(sbtn.equals("Refresh"))
		{
			query = "SELECT * FROM STUDENT order by rno";
		}
		else if(sbtn.equals("Search"))
		{
			query = "SELECT * FROM STUDENT where rno = " + rno + " order by rno";
			
		}
		
	
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Student s = new Student();

				s.setRno(rs.getInt("rno"));
				s.setName(rs.getString("name"));
				s.setPer(rs.getDouble("per"));

				list.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				list = null;
			}

		}

		request.setAttribute("listofStudent", list);
		RequestDispatcher rd = request.getRequestDispatcher("Delete.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Deleting Student Logic
		
		response.setContentType("text/html");
		String trno = request.getParameter("rno");
		Connection con = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();

		
		String query = "DELETE FROM STUDENT WHERE RNO = " + trno;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			
			out.println("Success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("Failure");
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
