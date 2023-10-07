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
<style>
    #pageContainer{
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #pageBox{
        display: flex;
        width: 60%;
        justify-content: space-between;
        align-items: center;
    }
</style>
<body>
<jsp:include page="../layout/header.jsp"/>
<main class="main">
    <div class="main-content">
        <div class="search-box">
            <div class="search-box-title">공제검색</div>
            <div class="search-box-detail">
                <select>
                    <option value="ALL">전체</option>
                    <option value="MACHINE">제목</option>
                    <option value="MEMBER">내용</option>
                </select>
                <input type="text">
                <div>검색</div>
            </div>
        </div>
    </div>
    <div class="main-content">
        <div class="user-list-table">
            <div class="table-meta">
                <div class="table-meta-title">
                    기록목록
                </div>
                <div class="table-meta-paging">
                    <span>총 ${paging.totalCount}건</span>
                </div>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">no</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">기록일사</th>
                        <th scope="col">조회수</th>
                        <th scope="col">좋아요수</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${ fn:length(noticeList) == 0}">
                        <tr>
                            <td colspan="6" >데이터가 없습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="item" items="${noticeList}" varStatus="status">
                            <tr>
                                <td>${item.id}</td>
                                <td><a href="/admin/notice/${item.id}">${item.title}</a></td>
                                <td>${item.member.username}</td>
                                <td>${item.createdDate}</td>
                                <td>${item.viewCount}</td>
                                <td>${item.likeCount}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


                </tbody>
            </table>
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
            <div>
                <a href="/admin/notice/register" ><button class="primary-btn">공지등록</button></a>
            </div>
        </div>

    </div>
</main>
<footer>

</footer>
</body>
</html>
<script>

    let firstPage = document.getElementById("firstPage").value;
    let lastPage = document.getElementById("lastPage").value;
    let pageSize = document.getElementById("pageSize").value;
    let pageBox = document.getElementById("pageBox");
    let nowPage = document.getElementById("nowPage").value;
    let totalPage = document.getElementById("totalPage").value;
    let rowSize = document.getElementById("rowSize").value;
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
            aTag.setAttribute("href", "/admin/notice?nowPage=" + i + "&rowSize=" + rowSize);
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
            nextATag.setAttribute("href","/admin/notice?nowPage="+nextFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.appendChild(nextATag);
        }

        if(parseInt(firstPage)>1){
            let previousATag = document.createElement("a");
            let previousLiTag = document.createElement("li");
            let previousText = document.createTextNode("previous");
            previousLiTag.appendChild(previousText);
            previousATag.appendChild(previousLiTag);
            let previousFirstPage = parseInt(firstPage)-10>0?parseInt(firstPage)-10:1;
            previousATag.setAttribute("href","/admin/notice?nowPage="+previousFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.prepend(previousATag);
        }
    })




</script>
