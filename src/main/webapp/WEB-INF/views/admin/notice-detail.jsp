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
            <div id="replyList"></div>
        </div>
        <!-- paging -->
        <nav id="pageContainer">
            <ul id="pageBox"></ul>
            <input type="hidden" id="totalCount"/>
            <input type="hidden" id="pageSize"/>
            <input type="hidden" id="rowSize" />
            <input type="hidden" id="totalPage" />
            <input type="hidden" id="pageGroup" />
            <input type="hidden" id="firstPage" />
            <input type="hidden" id="lastPage" />
            <input type="hidden" id="nowPage" />
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
        replyListMaker();
    });

    function replyListMaker(nowPage, rowSize){
        let replyListDiv = document.getElementById("replyList");
        let noticeId = document.getElementById("noticeId").value;
        if(nowPage == null){
            nowPage = "1";
        }
        if(rowSize == null){
            rowSize = "10";
        }
        let box = {};
        box.nowPage = nowPage;
        box.rowSize = rowSize;
        $.ajax({
            type: "GET",
            data: box,
            url: "/admin/notice/"+noticeId+"/reply",
            success: function(data) {
                let replyList = data.replyList;
                while (replyListDiv.firstChild) {
                    replyListDiv.removeChild(replyListDiv.firstChild);
                }
                while (pageBox.firstChild){
                    pageBox.removeChild(pageBox.firstChild);
                }
                document.getElementById("totalCount").setAttribute("value", data.paging.totalCount);
                document.getElementById("totalPage").setAttribute("value",data.paging.totalPage);
                document.getElementById("firstPage").setAttribute("value" ,data.paging.firstPage);
                document.getElementById("lastPage").setAttribute("value",data.paging.lastPage);
                document.getElementById("nowPage").setAttribute("value",data.paging.nowPage);
                document.getElementById("rowSize").setAttribute("value" , data.paging.rowSize);
                for(let i =0 ; i<replyList.length;i++){
                    let replyDiv = document.createElement("div");
                    let replyId = "reply"+replyList[i].id;
                    let replyText = replyList[i].textContent;
                    let replyWriter = replyList[i].member.username;
                    let replyTime = replyList[i].createdDate;
                    let replyLikeCount = replyList[i].likeCount;
                    let reply = replyText + " " + replyWriter + " " + replyTime + " " + replyLikeCount;
                    replyDiv.setAttribute("id",replyId);
                    replyDiv.textContent = reply;
                    replyListDiv.append(replyDiv);
                }
                let firstPage = data.paging.firstPage;
                let lastPage = data.paging.lastPage;
                let totalPage = data.paging.totalPage;
                console.log(totalPage);
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
                        aTag.setAttribute("href", "javascript:replyListMaker("+i+","+rowSize+")");
                        pageBox.appendChild(aTag);
                    }
                }
                if(totalPage>lastPage){
                    let nextATag = document.createElement("a");
                    let nextLiTag = document.createElement("li");
                    let nextText = document.createTextNode("next");
                    nextLiTag.appendChild(nextText);
                    nextATag.appendChild(nextLiTag);

                    let nextFirstPage = parseInt(firstPage)+10>parseInt(totalPage)?parseInt(totalPage):parseInt(firstPage)+10;
                    nextATag.setAttribute("href", "javascript:replyListMaker("+nextFirstPage+","+rowSize+")");
                    pageBox.appendChild(nextATag);
                }

                if(firstPage>1){
                    let previousATag = document.createElement("a");
                    let previousLiTag = document.createElement("li");
                    let previousText = document.createTextNode("previous");
                    previousLiTag.appendChild(previousText);
                    previousATag.appendChild(previousLiTag);
                    let previousFirstPage = parseInt(firstPage)-10>0?parseInt(firstPage)-10:1;
                    previousATag.setAttribute("href", "javascript:replyListMaker("+previousFirstPage+","+rowSize+")");
                    pageBox.prepend(previousATag);
                }
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        })

    }

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
            aTag.setAttribute("href", "javascript:replyListMaker("+i+","+rowSize+")");
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
            nextATag.setAttribute("href", "javascript:replyListMaker("+nextFirstPage+","+rowSize+")");
            pageBox.appendChild(nextATag);
        }

        if(parseInt(firstPage)>1){
            let previousATag = document.createElement("a");
            let previousLiTag = document.createElement("li");
            let previousText = document.createTextNode("previous");
            previousLiTag.appendChild(previousText);
            previousATag.appendChild(previousLiTag);
            let previousFirstPage = parseInt(firstPage)-10>0?parseInt(firstPage)-10:1;
            previousATag.setAttribute("href", "javascript:replyListMaker("+previousFirstPage+","+rowSize+")");
            pageBox.prepend(previousATag);
        }
    })

    const replyButtons = document.getElementsByClassName("replyButton");
    for(let i=0;i<replyButtons.length;i++){
        replyButtons[i].addEventListener("click",function(e){
            let targetId = e.target.id;
            let replyId = parseInt(targetId.toString().replace("reply",""));
            replyDelete(replyId)
        })
    }

    function replyDelete(replyId){
        console.log(replyId);
        $.ajax({
            type: "DELETE",
            url: "/admin/reply/"+replyId,
            success: function(data) {
                alert("성공");
                replyListMaker();
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }
</script>
