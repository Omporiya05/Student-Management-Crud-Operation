<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	function display()
	{
		var h1 = document.getElementById("msg");
		h1.innerText = "I Love India";
	}
	
	function show()
	{
		var sn = document.getElementById("sn");
		var data = sn.value;
		sn.value = data.toUpperCase();
	}
</script>

<h1 id = "msg">Welcome To Om</h1>
<input type="text" name="sn" id="sn" onkeyup="show()"> <br><br> 
<input type="button" value="Click Here" onClick="display()">
</body>
</html>