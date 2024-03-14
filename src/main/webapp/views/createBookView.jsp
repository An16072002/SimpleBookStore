<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sách</title>
</head>
<body>
	<div align="center">
		<h3>Thêm sách mới</h3>
		<p style="color: red">${errors}</p>
		<form action="createBook" method="post">
			<table border="0">
				<tr>
					<td>Tiêu đề</td>
					<td><input type="text" name="title" value=${book.title}></td>
				</tr>
				<tr>
					<td>Tác giả</td>
					<td><input type="text" name="author" value=${book.author}></td>
				</tr>
				<tr>
					<td>Giá tiền</td>
					<td><input type="text" name="price" value=${book.price}>&nbsp;&nbsp; (vnđ)</td>
				</tr>
				<tr>
					<td>Số lượng</td>
					<td><input type="text" name="quantityInStock" value=${book.quantityInStock}></td>
				</tr>
				<tr>
					<td><input type="submit" value="Thêm mới">&nbsp;&nbsp;
						<a href="adminHome">Bỏ qua</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>