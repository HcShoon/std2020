<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript">
	function fnOrderBookCall(){
		var apiKey = $("#apiKey").val();
		var symbol = $("#symbol").val();
		var endPoint = "/api/v1/depth";
		var url = "orderBook";
		
		$.ajax({
			url : url,
			data : {
				"apiKey" : apiKey,
				"symbol" : symbol,
				"endpoint" : endPoint
			},
			dataType : "json",
			type:"get",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data){
				var result = "<table class = 'table'>";
				var asks = data['asks'].reverse(); //매수 요청 내역
				var bids = data['bids']; //매도 요청 내역
				console.log(asks);
				result     += "<h3>매도 요청 내역</h3>";
				result     += "<tr><td style = 'font-weight: bold'>가격</td><td style = 'font-weight: bold'>수량</td></tr>";
				
				for(i in bids){
					result += "</td><td>" + bids[i][0] + "</td><td>" + bids[i][1] + "</td></tr>" 	
					
				}
				result += "</table> <br/> <table class = 'table'>";
				
				result     += "<h3>매수 요청 내역<h3>";
				result     += "<tr><td style = 'font-weight: bold'>가격</td><td style = 'font-weight: bold'>수량</td></tr>";
				
				for(i in asks){
					
					result += "</td><td>" + asks[i][0] + "</td><td>" + asks[i][1] + "</td></tr>" ;	
					
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
	  <input type="text" class="form-control " id="symbol" name = "symbol" value="btckrw">
</div>

<input type = "button"  class="btn" onclick="fnOrderBookCall();" value="orderBook 확인하기"><br/>
<div id = "result" style="margin-top: 10px"></div>
</body>
</html>
