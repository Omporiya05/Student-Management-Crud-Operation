<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.om.entities.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Display Student</title>

<script>
	function modify(trno)
	{
		alert("Deleting Roll Number : " + trno);
		// take that rno
		// Prepare Request : http://localhost:8080/Project-Crud-Operation-Using-JDBC/DeleteStudent
		// method : POST
		// true   : 101
		// send
		// fetch(URL,{body})
		// data = " Success "
		
		
		fetch('./DeleteStudent',
			{
			  method :'POST',
			  body   : new URLSearchParams({'rno': trno})
			}
		)
		.then(response => response.text())
		.then(data =>{
						if(data.trim() == "Success")
						{
							//alert("Record is Deleted for Roll Number : " + trno);
							var tr = document.getElementById(trno);
							tr.remove();
						}
						
						if(data.trim() == "Failure")
						{
							alert("Record is Not Deleted for Roll Number : " + trno);
						}
						
					 })
		.catch(error => console.error("MyError While Deleting :",error))
		
	}
</script>
</head>
<body>

<%
	String rno = request.getParameter("rno");
	String sbtn = request.getParameter("sbtn");
	
	if(rno == null){
		rno = "";
	}
	
	if(sbtn!=null && sbtn.equals("Refresh"))
	{
		rno = "";
	}
%>

	<div class="container" style="margin-top: 100px;">
		<h3 class="text-center mb-3 text-primary">Student InFormation</h3>
		
		
		<!-- Search Start -->
		
		<div class="container-fluid d-flex justify-content-end">
			<form class="d-flex" role="search" method="GET"
				action="./DeleteStudent">
				<input class="form-control me-2" type="search" name="rno"
					placeholder="Enter Roll Number" value="<%=rno %>"aria-label="Search">
				<button class="btn btn-outline-success" type="submit" value="Search"
					name="sbtn">Search</button>
				<button class="btn btn-outline-success ms-2" type="submit"
					value="Refresh" name="sbtn">Refresh</button>
			</form>
		</div>

		<!-- Search End -->
		
		
		<!-- Table Start -->
		<table class="table table-hover table-bordered text-center mt-3">
			<thead>
				<tr class="table-primary">
					<th scope="col">RNO</th>
					<th scope="col">Name</th>
					<th scope="col">Per</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>

				<%
				List<Student> L = (List<Student>) request.getAttribute("listofStudent");

				if (L == null || L.isEmpty()) {
%>
				<tr>
					<td colspan="4" class="text-danger"> Not Record Found !!!</td>
				</tr>
<%
				} else {
						for (Student s : L) 
						{
							String cls = "";
							if(s.getPer() < 50)
							{
								cls = "table-danger";
							}
%>
				<tr id="<%= s.getRno() %>" class="<%=cls%>">
					<td><%=s.getRno()%></td>
					<td><%=s.getName()%></td>
					<td><%=s.getPer()%></td>
					<td><input type="button" class=" btn btn-danger" value="Delete" onclick="modify(<%= s.getRno()%>)"> </td>
				</tr>
				<%
	}// for
}//else
				%>
			</tbody>
		</table>
	</div>
	<!-- Table End -->
	
	
</body>
</html>