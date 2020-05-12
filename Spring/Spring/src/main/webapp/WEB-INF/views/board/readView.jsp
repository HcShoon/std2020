<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
	 	<title>게시판</title>
	 	<script>
	 	function fnDeleteBbs(){
	 		location.href = "/board/delete?bno=" + ${read.bno};
	 	}
	 	</script>
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
				<form role="form" method="get" action="/board/updateView">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="bno">글 번호</label><input readonly type="text" id="bno" name="bno" value="${read.bno}"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="title">제목</label><input readonly type="text" id="title"  value="${read.title}"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label><textarea readonly  id="content" ><c:out value="${read.content}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="writer">작성자</label><input readonly type="text" id="writer" value="${read.writer}" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="regdate">작성날짜</label>
									<fmt:formatDate value="${read.regdate}" pattern="yyyy-MM-dd"/>					
								</td>
								<td>						
									<button type="submit">수정하기</button>
								</td>
								<td>						
									<input type="button" onclick="javascript:fnDeleteBbs();" value = '삭제하기'/>
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