<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript">
	function submit(val){
		$("#f").attr("apiValue",val);
		$("#apiValue").attr("value",val);
		$("#f").submit();
	}
	</script>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<form id = "f" method="post" action="apiCall" style="height: 60px; padding: 20px;">
	<div class="form-group col-xs-3">
	  <label for="apiKey">api key:</label>
	  <input type="text" class="form-control" id="apiKey" name = "apiKey" value="">
	</div>
	<div class="form-group col-xs-3">
	  <label for="secretKey">secret key:</label>
	  <input type="text" class="form-control" id="secretKey" name = "secretKey" value="">
	</div>
	<input type = "text" id = "apiValue" value="" name = "apiValue" hidden="hidden">
	
</form>

<div style="margin-top: 40px; padding: 0 40px;">
<h2>
	PUBLIC API 
</h2>
<input type = "button" class="btn" id = "ticker" onclick="submit('ticker');" value="Ticker">
<input type = "button" class="btn" id = "orderbook" onclick="submit('orderBook');" value="Orderbook">
<h2>
	PRIVATE API - INFO
</h2>
<input type = "button" class="btn" id = "account" onclick="submit('account');" value="Account">
<input type = "button" class="btn" id = "walletAdd" onclick="submit('walletAdd');" value="Wallet Address">
</div>
</body>
</html>
