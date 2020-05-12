<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
	 	<title>게시판</title>
	</head>
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  <%@include file="nav.jsp" %>
			</nav>
			<hr />
			
			<section id="container">
				<form role="form" method="post" action="/board/update">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="bno">글 번호</label><input type="text" id="bno" name="bno" value="${updateView.bno}"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" value="${updateView.title}"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content"><c:out value="${updateView.content}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="writer">작성자</label><input type="text" id="writer" name="writer" value="${updateView.writer}" />
								</td>
							</tr>
							<tr>
								<td>						
									<button onclick="location.href = '/board/readView?bno=${updateView.bno}'">취소</button>
								</td>
								<td>						
									<button type="submit">수정하기</button>
								</td>
							</tr>		
						</tbody>			
					</table>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>