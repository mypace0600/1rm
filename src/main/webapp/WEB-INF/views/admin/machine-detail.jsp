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
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
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
                <button type="button">관리자 로그인</button>
                <button type="button">관리자 가입</button>
                <button type="button" style="display: none">로그아웃</button>
                <button type="button" style="display: none">프로필</button>
            </div>
        </div>
    </nav>
</header>
<main class="main">
    <div class="main-content">
        <div class="search-box">
            <div class="search-box-title">
                <label for="machineName">이름</label>
                <input name="machineName" id="machineName" type="text" value="${machine.machineName}"/>
            </div>
        </div>
    </div>
    <div class="main-content">
        <div>
            <label for="imgUrl">이미지</label>
            <input name="imgUrl" id="imgUrl" type="text" value="${machine.imgUrl}"/>
        </div>
        <div>
            <div>
                <label for="machineType">머신타입</label>
                <input name="machineType" id="machineType" type="text" value="${machine.machineType}"/>
            </div>
            <div>
                <label for="stimulatePoint">자극지점</label>
                <input name="stimulatePoint" id="stimulatePoint" type="text" value="${machine.stimulatePoint}"/>
            </div>
            <div>
                <label for="videoUrl">비디오</label>
                <input name="videoUrl" id="videoUrl" type="text" value="${machine.videoUrl}"/>
            </div>
            <input id="machineId" type="hidden" value="${machine.id}"/>
        </div>
    </div>
    <div>
        <button class="primary-btn"><a href="/admin/machine">목록</a></button>
        <button class="primary-btn" id="patchBtn">수정</button>
        <button class="primary-btn" id="deleteBtn">삭제</button>
    </div>
</main>
<footer>

</footer>
</body>
</html>
<script>
    const deleteBtn = document.getElementById("deleteBtn");
    deleteBtn.addEventListener("click",()=>{
        const targetId = document.getElementById("machineId").value;
        const url = "/admin/machine/"+targetId;
        console.log("url : "+url);

        $.ajax({
            type: "delete",
            url: url,
            dataType:'json',
        }).done(function (){
            alert("삭제 완료");
            location.href = "/admin/machine";
        }).fail(function (){
            alert("삭제 실패");
            location.href = "/admin/machine";
        })
    });
</script>
