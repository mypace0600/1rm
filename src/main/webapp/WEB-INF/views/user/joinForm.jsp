<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>1RM Admin</title>
    <link rel="stylesheet" href="/css/nav.css">
    <!-- 부트스트랩 CDN 링크 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
        <main>
            <h1>회원가입 페이지</h1>
            <hr>
            <form action="/join" method="POST">
                <input type="text" id="username" name="username" placeholder="username"/>
                <input type="password" id="password" name="password" placeholder="password"/>
                <input type="email" id="email" name="email" placeholder="email"/>
                <input type="text" id="nickname" name="nickname" placeholder="nick"/>
                <input type="text" id="phone" name="phone" placeholder="phone"/>
                <input type="text" id="gender" name="gender" placeholder="gender"/>
                <button>회원가입</button>
            </form>
        </main>
    <footer>

    </footer>
</body>
</html>

