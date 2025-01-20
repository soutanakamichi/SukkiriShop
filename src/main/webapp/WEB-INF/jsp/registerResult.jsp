<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スッキリ商店</title>
</head>
<body>
	<table>
		<tr>
			<td>ユーザーID</td>
			<td><c:out value="${account.userId}" /></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><c:out value="${account.pass}" /></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><c:out value="${account.mail}" /></td>
		</tr>
		<tr>
			<td>お名前</td>
			<td><c:out value="${account.name}" /></td>
		</tr>
		<tr>
			<td>年齢</td>
			<td><c:out value="${account.age}" /></td>
		</tr>
	</table>
	<a href="WelcomeServlet">トップメニューへ戻る</a>
</body>
</html>