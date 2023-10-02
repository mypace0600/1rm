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
    <header>
        <nav>
            <div class="topNav">
                <div class="topNavMenus">
                    <a href="/admin">1RM</a>
                    <a href="/admin/member">회원관리</a>
                    <a href="/admin/record">기록관리</a>
                    <a href="/admin/machine">기기관리</a>
                </div>
            </div>
        </nav>
    </header>
        <main>
            <form method="post" action="/admin/loginForm">
                <label for="loginId">아이디</label>
                <input type="text" id="loginId" name="loginId"/>
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password"/>
                <button type="button">로그인</button>
            </form>
        </main>
    <footer>

    </footer>
</body>
</html>

