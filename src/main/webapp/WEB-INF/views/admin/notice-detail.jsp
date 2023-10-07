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

    <!-- 섬머노트 -->
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<main class="main">
    <div class="main-content">
        <input type="hidden" id="noticeId" name="noticeId" value="${view.notice.id}"/>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" value="${view.notice.title}"/>
        <label for="textContent">내용</label>
        <textarea id="textContent" name="textContent">${view.notice.textContent}
        </textarea>
        <input type="hidden" id="memberId" name="memberId" value="${view.member.id}"/>
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" value="${view.member.username}" disabled>
        <label for="viewCount">조회수</label>
        <input type="text" id="viewCount" name="viewCount" value="${view.notice.viewCount}" disabled>
        <label for="likeCount">좋아요수</label>
        <input type="text" id="likeCount" name="likeCount" value="${view.notice.likeCount}" disabled>
    </div>
    <div>
        <button class="primary-btn"><a href="/admin/notice">목록</a></button>
        <button class="primary-btn" id="saveBtn">저장</button>
        <button class="primary-btn" id="deleteBtn">삭제</button>
    </div>
</main>
<footer>

</footer>
</body>
</html>
<script>
    $(document).ready(function() {
        $('#textContent').summernote({
        });
    });

    const deleteBtn = document.getElementById("deleteBtn");
    deleteBtn.addEventListener("click",()=>{
        const targetId = document.getElementById("noticeId").value;
        const url = "/admin/notice/"+targetId;
        console.log("url : "+url);

        $.ajax({
            type: "delete",
            url: url,
            success: function(data) {
                alert("성공");
                location.href="/admin/notice";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        })
    });

    function noticeUpdate(data,targetId){
        const url = '/admin/notice/'+targetId;
        console.log("id : "+targetId);
        $.ajax({
            type: "put",
            url: url,
            data:data,
            success: function(data) {
                alert("성공");
                location.href="/admin/notice";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }

    function noticeInsert(data){
        const url = '/admin/notice';
        $.ajax({
            type: "POST",
            url: url,
            data:data,
            success: function(data) {
                alert("성공");
                location.href="/admin/notice";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }

    const saveBtn = document.getElementById("saveBtn");
    saveBtn.addEventListener("click",function (){
        const targetId = document.getElementById("noticeId").value;
        const title = document.getElementById("title").value;
        const textContent = document.getElementById("textContent").value;

        const data = {
            title:machineName,
            imgUrl:imgUrl,
            stimulatePoint:stimulatePoint,
            machineType:machineType,
            videoUrl:videoUrl
        };
        if(null != targetId && '' != targetId){
            noticeUpdate(data,targetId);
        } else {
            noticeInsert(data);
        }
    });
</script>
