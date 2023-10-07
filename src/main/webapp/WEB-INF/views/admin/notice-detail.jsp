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
        <input type="hidden" id="noticeId" name="noticeId" value="${notice.id}"/>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" value="${notice.title}"/>
        <label for="textContent">내용</label>
        <textarea id="textContent" name="textContent">${notice.textContent}
        </textarea>
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <label for="isImportant">중요</label>
            <input type="checkbox" id="isImportant" name="isImportant" <c:if test="${notice.isImportant}">checked</c:if>/>
            <label for="isPopup">팝업</label>
            <input type="checkbox" id="isPopup" name="isPopup" <c:if test="${notice.isPopup}">checked</c:if>/>
            <input type="hidden" id="memberId" name="memberId" value="${principal.id}"/>
            <label for="username">아이디</label>
            <input type="text" id="username" name="username" value="${principal.username}" disabled>
            <label for="viewCount">조회수</label>
            <input type="text" id="viewCount" name="viewCount" value="${notice.viewCount}" disabled>
            <label for="likeCount">좋아요수</label>
            <input type="text" id="likeCount" name="likeCount" value="${notice.likeCount}" disabled>
        </div>
        <div>
            <div>댓글 목록</div>
            <c:choose>
                <c:when test="${fn:length(replyList)==0}">
                    <div>댓글이 없습니다.</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${replyList}" varStatus="status">
                        <div>
                            <span>${item.textContent}</span>
                            <span>${item.member.username}</span>
                            <span>${item.likeCount}</span>
                            <span>${item.createdDate}</span>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- paging -->
        <nav id="pageContainer">
            <ul id="pageBox"></ul>
            <input type="hidden" id="totalCount" value="${paging.totalCount}"/>
            <input type="hidden" id="pageSize" value="${paging.pageSize}"/>
            <input type="hidden" id="rowSize" value="${paging.rowSize}"/>
            <input type="hidden" id="totalPage" value="${paging.totalPage}"/>
            <input type="hidden" id="pageGroup" value="${paging.pageGroup}"/>
            <input type="hidden" id="firstPage" value="${paging.firstPage}"/>
            <input type="hidden" id="lastPage" value="${paging.lastPage}"/>
            <input type="hidden" id="nowPage" value="${paging.nowPage}"/>
        </nav>
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
        const memberId = document.getElementById("memberId").value;
        const importantBox = document.getElementById("isImportant");
        let isImportant = false;
        if(importantBox.checked){
            isImportant = true;
        };
        const popupBox = document.getElementById("isPopup");
        let isPopup = false;
        if(popupBox.checked){
            isPopup = true;
        };
        let data = {
            title:title,
            textContent:textContent,
            memberId:memberId,
            isImportant:isImportant,
            isPopup:isPopup
        };
        console.log(data)
        if(null != targetId && '' != targetId){
            noticeUpdate(data,targetId);
        } else {
            noticeInsert(data);
        }
    });

    let firstPage = document.getElementById("firstPage").value;
    let lastPage = document.getElementById("lastPage").value;
    let pageSize = document.getElementById("pageSize").value;
    let pageBox = document.getElementById("pageBox");
    let nowPage = document.getElementById("nowPage").value;
    let totalPage = document.getElementById("totalPage").value;
    let rowSize = document.getElementById("rowSize").value;
    let noticeId= document.getElementById("noticeId").value;
    if(firstPage != lastPage) {
        for (let i = firstPage; i <= lastPage; i++) {
            let aTag = document.createElement("a");
            let numberBox = document.createElement("li");
            let number = document.createTextNode(i);
            numberBox.appendChild(number);
            aTag.appendChild(numberBox);
            if (i == nowPage) {
                aTag.setAttribute("style", "text-decoration:underline;")
            }
            aTag.setAttribute("href", "/admin/notice/"+noticeId+"?nowPage=" + i + "&rowSize=" + rowSize);
            pageBox.appendChild(aTag);
        }
    }
    document.addEventListener("DOMContentLoaded",function(){
        if(parseInt(totalPage)>parseInt(lastPage)){
            let nextATag = document.createElement("a");
            let nextLiTag = document.createElement("li");
            let nextText = document.createTextNode("next");
            nextLiTag.appendChild(nextText);
            nextATag.appendChild(nextLiTag);

            let nextFirstPage = parseInt(firstPage)+10>parseInt(totalPage)?parseInt(totalPage):parseInt(firstPage)+10;
            nextATag.setAttribute("href","/admin/notice/"+noticeId+"?nowPage="+nextFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.appendChild(nextATag);
        }

        if(parseInt(firstPage)>1){
            let previousATag = document.createElement("a");
            let previousLiTag = document.createElement("li");
            let previousText = document.createTextNode("previous");
            previousLiTag.appendChild(previousText);
            previousATag.appendChild(previousLiTag);
            let previousFirstPage = parseInt(firstPage)-10>0?parseInt(firstPage)-10:1;
            previousATag.setAttribute("href","/admin/notice/"+noticeId+"?nowPage="+previousFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.prepend(previousATag);
        }
    })
</script>
