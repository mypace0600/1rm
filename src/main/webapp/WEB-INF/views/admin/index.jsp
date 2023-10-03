<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>1RM Admin</title>
    <link rel="stylesheet" href="/css/nav.css">
    <link rel="stylesheet" href="/css/content.css">
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
            <div class="topNavButtons">
                <c:if test="${auth != null}">
                    <a href="/logout" ><button type="button">로그아웃</button></a>
                </c:if>
            </div>
        </div>
    </nav>
</header>
<main class="main">
    <div class="main-content">
        <h1>관리자 메인</h1>
    </div>
</main>
<footer>

</footer>
</body>
</html>