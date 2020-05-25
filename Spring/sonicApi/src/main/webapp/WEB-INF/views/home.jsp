<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript">
	function fnAccountCall(){
		var apiKey = $("#apiKey").val();
		var secretKey = $("#secretKey").val();
		var endPoint = "/api/v1/account";
		var url = "account";
		
		$.ajax({
			url : url,
			data : {
				"apiKey" : apiKey,
				"secretKey" : secretKey,
				"endpoint" : endPoint
			},
			dataType : "json",
			type:"get",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				
				console.log(data);
			},error: function(data){
				console.log("error");
				console.log(data);
			}
		});
	}
	function fnBalanceCall(){
		var apiKey = $("#apiKey").val();
		var secretKey = $("#secretKey").val();
		var endPoint = "/api/v1/my-balance";
		var url = "balance";
		
		$.ajax({
			url : url,
			data : {
				"apiKey" : apiKey,
				"secretKey" : secretKey,
				"endpoint" : endPoint
			},
			dataType : "json",
			type:"get",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				
				console.log(data);
			},error: function(data){
				console.log("error");
				console.log(data);
			}
		});
	}
	</script>
</head>
<body>
<h1>
	한글 
</h1>

<input type = "text" id = "apiKey" value="[apiKey]">
<input type = "text" id = "secretKey" value="[secretKey]">

<input type = "button" onclick="fnAccountCall();" value="Account">
<input type = "button" onclick="fnBalanceCall();" value="Balance">
</body>
</html>
