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
<jsp:include page="../layout/header.jsp"/>
<main class="main">
    <div class="main-content">
        <div>
            <label for="loginId">아이디</label>
            <input name="loginId" id="loginId" type="text" value="${member.loginId}"disabled/>
        </div>
        <div>
            <div>
                <label for="userName">이름</label>
                <input name="userName" id="userName" type="text" value="${member.userName}"disabled/>
            </div>
            <div>
                <label for="email">이메일</label>
                <input name="email" id="email" type="text" value="${member.email}"disabled/>
            </div>
            <div>
                <label for="gender">성별</label>
                <input name="gender" id="gender" type="text" value="${member.gender}"disabled/>
            </div>
            <div>
                <label for="phone">연락처</label>
                <input name="phone" id="phone" type="text" value="${member.phone}" disabled/>
            </div>
            <div>
                <label for="role">권한</label>
                <select name="role" id="role">
                    <option disabled>선택하시오</option>
                    <option <c:if test="${roleType == 'ADMIN'}">selected</c:if> >ADMIN</option>
                    <option <c:if test="${roleType == 'USER'}">selected</c:if> >USER</option>
                </select>
            </div>
            <div>
                <label for="lastLoginDate">마지막 접속일</label>
                <input name="lastLoginDate" id="lastLoginDate" type="text" value="${member.lastLoginDate}"disabled/>
            </div>
            <div>
                <label for="createDate">계정 생성일</label>
                <input name="createDate" id="createDate" type="text" value="${member.createDate}"disabled/>
            </div>

            <input id="memberId" type="hidden" value="${member.id}"disabled/>
        </div>
    </div>
    <div>
        <button class="primary-btn"><a href="/admin/member">목록</a></button>
        <button class="primary-btn" id="saveBtn">저장</button>
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
        const targetId = document.getElementById("memberId").value;
        const url = "/admin/member/"+targetId;
        console.log("url : "+url);

        $.ajax({
            type: "delete",
            url: url,
            success: function(data) {
                alert("성공");
                location.href="/admin/member";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        })
    });

    function memberUpdate(data,targetId){
        const url = '/admin/member/'+targetId;
        console.log("id : "+targetId);
        console.log("url : "+url);
        $.ajax({
            type: "put",
            url: url,
            data:data,
            success: function(data) {
                alert("성공");
                location.href="/admin/member";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }




    const saveBtn = document.getElementById("saveBtn");
    saveBtn.addEventListener("click",function (){
        const targetId = document.getElementById("memberId").value;
        const roleSelectBox = document.getElementById("role");
        const role = roleSelectBox.options[roleSelectBox.selectedIndex].value;

        const data = {
            role:role
        };
        if(null != targetId && '' != targetId){
            memberUpdate(data,targetId);
        }
    });
</script>
