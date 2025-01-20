<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スッキリ商店</title>
</head>
<body>
    <c:if test="${not empty errormsg}">
        <div style="color: red;">
            <p>${errormsg}</p>
        </div>
    </c:if>
	<form action="RegisterServlet" method="post">
		ユーザーID:<input type="text" name="userId"><br>
		パスワード:<input type="password" name="pass"><br>
		メールアドレス:<input type="email" name="mail"><br>
		お名前:<input type="text" name="name"><br>
		年齢:<input type="text" name="age"><br>
		<input type="submit" value="登録">
	</form>
	<a href="WelcomeServlet">トップメニューへ戻る</a>
</body>
</html>