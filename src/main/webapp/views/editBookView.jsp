<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h3>Chỉnh sửa thông tin sách</h3>
		<p style="color: red">${errors }</p>
		<c:if test="${not empty book}">
			<form action="editBook" method="POST">
				<input type="hidden" name="bookId" value="${book.bookId }" />
				<table>
					<tr>
						<td>Tiêu đề</td>
						<td><input type="text" name="title" value="${book.title }"></td>
					</tr>
					<tr>
						<td>Tác giả</td>
						<td><input type="text" name="author" value="${book.author }"></td>
					</tr>
					<tr>
						<td>Giá tiền</td>

						<td> <input type="text"
							name="price" value="${book.price }">&nbsp;&nbsp;(vđn)
						</td>
					</tr>
					<tr>
						<td>Số lượng có trong kho</td>
						<td><input type="text" name="quantityInStock"
							value="${book.quantityInStock }"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Cập nhật">
							&nbsp;&nbsp; <a href="adminHome">Bỏ qua</a></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
</body>
</html>