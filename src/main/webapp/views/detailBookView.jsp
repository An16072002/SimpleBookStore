<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/bookstore_script.js"></script>
<script >
function plusValue(elementId, maxQuantity) {
	let quantity = parseInt(document.getElementById(elementId).value);
	if (quantity + 1 <= maxQuantity) {
		document.getElementById(elementId).value = quantity + 1;
	} else {
		alert('Giá trị không được vượt quá: ' + maxQuantity);
	}
}

function minusValue(elementId) {
	let quantity = parseInt(document.getElementById(elementId).value);
	if (quantity - 1 >= 1) {
		document.getElementById(elementId).value = quantity - 1;
	}
}
</script>
</head>
<body>
	<div align="center">
		<h3>Xem chi tiết thông tin sách</h3>
		<p style="color: red">${errors }</p>
		<c:if test="${not empty book}">
			<div align="center">
				<form id="detailBookForm" action="cartBook/addToCart" method="POST">
					<input type="hidden" name="bookId" value="${book.bookId }" />
					<table>
						<tr>
							<td>Tiêu đề</td>
							<td>${book.title }</td>
						</tr>
						<tr>
							<td>Tác giả</td>
							<td>${book.author }</td>
						</tr>
						<tr>
							<td>Giá tiền</td>

							<td><fmt:formatNumber type="number" value="${book.price }" /><sup>đ</sup>
							</td>
						</tr>
						<tr>
							<td>Số lượng có trong kho</td>
							<td>${book.quantityInStock }</td>
						</tr>
						<tr align="center">
							<td><a href="adminHome">Bỏ qua</a></td>
						</tr>
					</table>
					<div style="margin-top: 20px;">
						<img src="img/icon-remove.png"
							onclick="minusValue('quantity${book.bookId}')" width="20px"
							height="20px" /> <input type="text" value="1" size="2"
							style="line-height: 20px"
							onchange="validateValue(this,${book.quantityInStock})"
							id="quantity${book.bookId}" name="quatityPurchased" /> <img
							src="img/icon-add.png"
							onclick="plusValue('quantity${book.bookId}',${book.quantityInStock})"
							width="20px" height="20px" /> &nbsp;&nbsp;&nbsp;
						<c:if test="${not empty loginedUser }">
							<button
								onclick="checkQuantityAndSubmit('quantity${book.bookId}',${book.quantityInStock})">Thêm
								vào giỏ hàng</button>
						</c:if>
						<c:if test="${empty loginedUser }">
							<button type="button" onclick="alert('Bạn cần đăng nhập')">Thêm
								vào giỏ hàng</button>
						</c:if>
						&nbsp;&nbsp;&nbsp; <a href="clientHome">Tiếp tục mua sách</a>
					</div>
				</form>
			</div>
		</c:if>
	</div>

</body>
</html>