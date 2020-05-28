<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript">	
	function fnWalletAddCall(){
		var apiKey = $("#apiKey").val();
		var secretKey = $("#secretKey").val();
		var endPoint = "/api/v1/wallet";
		var symbol = $("#symbol").val();
		var url = "walletAdd";
		
		$.ajax({
			url : url,
			data : {
				"apiKey" : apiKey,
				"secretKey" : secretKey,
				"symbol" : symbol,
				"endpoint" : endPoint
			},
			dataType : "json",
			type:"get",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				var result = "<table class = 'table'>";
				result     += "<tr><td style = 'font-weight: bold'>Description</td><td style = 'font-weight: bold'>value</td></tr>";
				
				for(i in data){
					result += "<tr><td>" + i + "</td><td>" + data[i] + "</td></tr>";	
				}
				result += "</table>";
				$("#result").html(result);
				console.log(data);
			},error: function(data){
				console.log("error");
				console.log(data);
			}
		});
	}
	</script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body style="margin: 20px">
<h2>
	<%=request.getAttribute("apiVal")%>
</h2>
<input type = "text" id = "apiKey" value='<%=request.getAttribute("apiKey")%>' hidden="hidden">
<input type = "text" id = "secretKey" value='<%=request.getAttribute("secretKey")%>' hidden="hidden">
<div class="form-group">
	  <label for="apiKey">코인 코드:</label>
	  <input type="text" class="form-control " id="symbol" name = "symbol" value="btc">
</div>
<input type = "button" class="btn btn-l" onclick="fnWalletAddCall();" value="Wallet Address"><br/>
<div id = "result" style="margin-top: 10px"></div>
</body>
</html>
