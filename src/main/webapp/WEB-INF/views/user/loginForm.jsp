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
            <h1>로그인페이지</h1>
            <hr>
            <form method="POST" action="/login">
                <input type="text" id="username" name="username" placeholder="username"/>
                <input type="password" id="password" name="password" placeholder="password"/>
                <button>로그인</button>
            </form>
            <a href="/joinForm">회원가입을 아직 하지 않으셨나요?</a>
        </main>
    <footer>

    </footer>
</body>
</html>

