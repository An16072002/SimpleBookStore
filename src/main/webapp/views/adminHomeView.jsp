<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ phía admin</title>
</head>
<body>
	Xin chào
	<b>${loginedUser.fullname}</b>
	<br>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h3>Danh sách các cuốn sách</h3>
	<p style="color: red">${errors }</p>
	<form action="deleteBook" id="deleteBookFromAdminForm" method="post">
		<input type="hidden" name="bookId" id="deleteBookFromAdmin" />
	</form>
	<div align="center" id="searchResultArea">
		<table border="1">
			<tr>
				<th>Tiêu đề</th>
				<th>Tác giả</th>
				<th>Giá tiền</th>
				<th colspan="2" width="120px">Thao tác</th>
			</tr>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td><fmt:formatNumber maxFractionDigits="0" type="number"
							value="${book.price }" /><sup>đ</sup></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailBook?bookId=${book.bookId}')">Xem
							chi tiết</button></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('editBook?bookId=${book.bookId}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteBook('${book.title}', ${book.bookId})">Xóa</button></td>
				</tr>
			</c:forEach>
		</table>
		<a href="createBook">Thêm sách mới</a><br>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
	<script type="text/javascript">
		function onClickDeleteBook(bookTitle, bookId) {
			let c = confirm('Bạn chắc chắn muốn xóa cuốn sách '+bookTitle+'?');
			if(c){
				document.getElementById("deleteBookFromAdmin").value = bookId;
				document.getElementById("deleteBookFromAdminForm").submit();
			}
		}
		function activeAsLink(link){
			window.location = link;
		}
		var request;
		function sendInfo() {
			var keyword = document.getElementById("keyword").value;
			var url = "search?keyword=" + keyword;
			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				request.onreadystatechange = getInfo;
				request.open("GET", url, true);
				request.send();
			} catch (e) {
				alert("Unable to connect to server");
			}
		}
		function getInfo() {
			if (request.readyState == 4) {
				var val = request.responseText;
				document.getElementById('searchResultArea').innerHTML = val;
			}
		}
	</script>
</body>
</html>