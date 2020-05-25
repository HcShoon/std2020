<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script type="text/javascript">

function publicCall(call) {
	var url;
	var order;
	var payment;
	
	url = "/public/" + call + "/";
	order =  $("#order").val().trim();
	payment = $("#payment").val().trim();
	console.log(call);
	$.ajax({
		url : call,
		type:"get",
		dataType: "json",
		data : {
			"call" : url,
			"order" : order,
			"payment" : payment
		},
		success: function(data) {
			if(call == "ticker")
				trickerDraw(data,call,order,payment);
			else
				orderbookDrow(data,call,order,payment);
		}
	});
	
}

function privateCall(call) {
	var url;
	var order;
	var payment;
	
	url = "/info/" + call;
	order =  $("#order").val().trim();
	payment = $("#payment").val().trim();
	
	console.log(url);
	$.ajax({
		url : call,
		type:"GET",
		dataType: "json",
		data : {
			"call" : url,
			"order" : order,
			"payment" : payment
		},
		success: function(data) {
			console.log(data);
			if(call == 'account')
				accountDraw(data,call,order,payment);
			else
				trickerUserDraw(data,call,order,payment);
		}
	});
	
}
function orderbookDrow(data,call,order,payment){
	//console.log(data);
	var result = data.data;
	var length = Object.keys(result).length;
	var resultDiv = "<h3> "+ call + " - " + order + " - " + payment +" </h3></br>";
	/* for(key in result){
		console.log(key + " : " + result[key]);
		resultDiv += key + " : " + result[key] + "</br>";
	} */
	console.log(result);
	resultDiv += "	주문 통화 (코인) : " + result["order_currency"] + "</br>";
	resultDiv += "결제 통화 (마켓) : " + result["payment_currency"] + "</br>";
	resultDiv += "</br><h4> 매수요청 </h4></br>"
    for(i in result.asks){
    	resultDiv += (i + 1) + "일 매도 요청 내역  - 수량 : " + result.asks[i].price + " 거래가 : " + result.asks[i].quantity + "</br>";
		
	}
    resultDiv += "</br><h4> 매수요청 </h4></br>"
	for(i in result.bids){
		
		resultDiv += (i + 1) + "일  매수 요청 내역 - 수량 : " + result.bids[i].price + " 거래가 : " + result.bids[i].quantity + "</br>";
	}
	
	$("#result").html(resultDiv)
}

function trickerDraw(data,call,order,payment){
	//console.log(data);

	var result = data.data;
	var length = Object.keys(data.data).length;
	var resultDiv = "<h3> "+ call + " - " + order + " - " + payment +" </h3></br>";

	console.log(resultDiv);
	
	resultDiv += "	시가 00시 기준 : " + result["opening_price"] + "</br>";
	resultDiv += "	종가 00시 기준 : " + result["closing_price"] + "</br>";
	resultDiv += "	저가 00시 기준 : " + result["min_price"] + "</br>";
	resultDiv += "	고가 00시 기준 : " + result["max_price"] + "</br>";
	resultDiv += "	거래량 00시 기준 : " + result["units_traded"] + "</br>";
	resultDiv += "	거래금액 00시 기준 : " + result["acc_trade_value"] + "</br>";
	resultDiv += "	전일종가 : " + result["prev_closing_price"] + "</br>";
	resultDiv += "	최근 24시간 거래량 : " + result["units_traded_24H"] + "</br>";
	resultDiv += "	최근 24시간 거래금액 : " + result["acc_trade_value_24H"] + "</br>";
	resultDiv += "	최근 24시간 변동가 : " + result["fluctate_24H"] + "</br>";
	resultDiv += "	최근 24시간 변동률 : " + result["fluctate_rate_24H"] + "</br>";

	$("#result").html(resultDiv);

}

function accountDraw(data,call,order,payment){
	//console.log(data);
	var result = data.data;
	var length = Object.keys(data.data).length;
	var resultDiv = "<h3> "+ call + " - " + order + " - " + payment +" </h3></br>";

	console.log(resultDiv);
	
	resultDiv += "	주문 통화 (코인) : " + result["order_currency"] + "</br>";
	resultDiv += "	결제 통화 (마켓) : " + result["payment_currency"] + "</br>";
	resultDiv += "	거래 수수료율 : " + result["trade_fee"] + "</br>";
	resultDiv += "	주문 가능 수량 : " + result["balance"] + "</br>";
		
	
	$("#result").html(resultDiv)
}

function trickerUserDraw(data,call,order,payment){
	//console.log(data);
	var result = data.data;
	var length = Object.keys(data.data).length;
	var resultDiv = "<h3> "+ call + " - " + order + " - " + payment +" </h3></br>";

	console.log(resultDiv);
	
	resultDiv += "	주문 통화 (코인) : " + result["order_currency"] + "</br>";
	resultDiv += "	결제 통화 (마켓) : " + result["payment_currency"] + "</br>";
	resultDiv += "	회원 시작 거래가 (최근 24시간) : " + result["opening_price"] + "</br>";
	resultDiv += "	회원 마지막 거래가 (최근 24시간) : " + result["closing_price"] + "</br>";
	resultDiv += "	회원 최저 거래가 (최근 24시간) : " + result["min_price"] + "</br>";
	resultDiv += "	회원 최고 거래가 (최근 24시간) : " + result["max_price"] + "</br>";
	resultDiv += "	평균가 (최근 24시간) : " + result["average_price"] + "</br>";
	resultDiv += "	거래량 (최근 24시간) : " + result["units_traded"] + "</br>";
	resultDiv += "	거래량 (최근 1일) : " + result["volume_1day"] + "</br>";
	resultDiv += "	거래량 (최근 7일) : " + result["volume_7day"] + "</br>";
	resultDiv += "	최근 24시간 변동가 : " + result["fluctate_24H"] + "</br>";
	resultDiv += "	최근 24시간 변동률 : " + result["fluctate_rate_24H"] + "</br>";

	
	$("#result").html(resultDiv)
}

</script>
<body>

<input id = 'order' type = 'text' value="BTC">
<input id = 'payment' type = 'text'value="KRW">
<input type = 'button' onclick="publicCall('ticker')" value="ticker"/>
<input type = 'button' onclick="publicCall('orderbook')" value="orderbook"/>
<input type = 'button' onclick="privateCall('account')" value="account"/>
<input type = 'button' onclick="privateCall('tickerUser')" value="tickerUser"/><br/><br/>
<div id = "result">

</div>
</body>
</html>
