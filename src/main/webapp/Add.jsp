<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<body>
	<div class="container" style="margin-top: 100px; width: 500px;">
		<h1 class="text-center mb-5">Registration Form</h1>

		<form method="POST" action="./AddStudent">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Roll
					Number</label> <input type="text" name="rno" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
			</div>

			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Name</label> <input
					type="text" name="name" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
			</div>

			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Percentage</label>
				<input type="text" name="per" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp">
			</div>

			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>

		<%
		if (request.getAttribute("msg") != null) {
		%>
		<p><%=request.getAttribute("msg")%></p>
		<%
		}
		%>
	</div>

</body>
</html>