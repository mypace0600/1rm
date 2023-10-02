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
                <%--<div class="topNavButtons">
                    <button type="button">관리자 로그인</button>
                    <button type="button">관리자 가입</button>
                    <button type="button" style="display: none">로그아웃</button>
                    <button type="button" style="display: none">프로필</button>
                </div>--%>
            </div>
        </nav>
    </header>
        <main class="main">
            <div class="main-content">
                <div class="search-box">
                    <div class="search-box-title">회원검색</div>
                    <div class="search-box-detail">
                        <select>
                            <option value="ALL">전체</option>
                            <option value="NAME">이름</option>
                            <option value="ID">id</option>
                        </select>
                        <input type="text">
                        <button>검색</button>
                    </div>
                </div>
            </div>
            <div class="main-content">
                <div class="user-list-table">
                    <div class="table-meta">
                        <div class="table-meta-title">
                            회원목록
                        </div>
                        <div class="table-meta-paging">
                            <span>총 ${paging.totalCount}건</span>
                            <select>
                                <option value="10">10건</option>
                                <option value="30">30건</option>
                                <option value="50">50건</option>
                            </select>
                        </div>
                    </div>
                    <table class="table table-striped">
                        <colgroup>
                            <col span="5%">
                            <col span="15%">
                            <col span="10%">
                            <col span="5%">
                            <col span="20%">
                            <col span="15%">
                            <col span="*">
                            <col span="*">
                            <col span="5%">
                            <col span="5%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">no</th>
                            <th scope="col">id</th>
                            <th scope="col">이름</th>
                            <th scope="col">성별</th>
                            <th scope="col">이메일</th>
                            <th scope="col">연락처</th>
                            <th scope="col">등록일</th>
                            <th scope="col">최근로그인</th>
                            <th scope="col">권한</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${ fn:length(memberList) == 0}">
                                <tr>
                                    <td colspan="9" >데이터가 없습니다.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="item" items="${memberList}" varStatus="status">
                                    <tr class="memberRow" id="memberId_${item.id}">
                                        <td>${status.index +1}</td>
                                        <td>${item.loginId}</td>
                                        <td>${item.userName}</td>
                                        <td>${item.gender}</td>
                                        <td>${item.email}</td>
                                        <td>${item.phone}</td>
                                        <td>${item.createDate}</td>
                                        <td>${item.lastLoginDate}</td>
                                        <td>${item.role}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>


                        </tbody>
                    </table>
                    <!-- paging -->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination pagination-seperated "></ul>
                    </nav>
                </div>

            </div>
        </main>
    <footer>

    </footer>
</body>
</html>
<script>
    const memberRowList = document.getElementsByClassName("memberRow");
    for(let i = 0 ; i < memberRowList.length ; i++){
        memberRowList[i].addEventListener("click",function(event){
            const memberIds = event.currentTarget.id.split("_");
            const memberId = memberIds[1];
            console.log(memberId);
            location.href = "/admin/member/"+memberId;
        });
    };

    let firstPage = document.getElementById("firstPage").value;
    let lastPage = document.getElementById("lastPage").value;
    let pageSize = document.getElementById("pageSize").value;
    let pageBox = document.getElementById("pageBox");
    let nowPage = document.getElementById("nowPage").value;
    let totalPage = document.getElementById("totalPage").value;
    let rowSize = document.getElementById("rowSize").value;
    console.log("@@@@@@@ firstPage : "+firstPage);
    console.log("@@@@@@@ lastPage : "+lastPage);
    if(firstPage != lastPage) {
        for (let i = firstPage; i <= lastPage; i++) {
            let aTag = document.createElement("a");
            let numberBox = document.createElement("li");
            let number = document.createTextNode(i);
            numberBox.appendChild(number);
            aTag.appendChild(numberBox);
            if (i == nowPage) {
                console.log("nowPage : " + nowPage);
                aTag.setAttribute("style", "text-decoration:underline;")
            }
            aTag.setAttribute("href", "/admin/record?nowPage=" + i + "&rowSize=" + rowSize);
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
            console.log("nextFirstPage : "+nextFirstPage);
            nextATag.setAttribute("href","/admin/record?nowPage="+nextFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.appendChild(nextATag);
        }

        if(parseInt(firstPage)>1){
            let previousATag = document.createElement("a");
            let previousLiTag = document.createElement("li");
            let previousText = document.createTextNode("previous");
            previousLiTag.appendChild(previousText);
            previousATag.appendChild(previousLiTag);
            let previousFirstPage = parseInt(firstPage)-10>0?parseInt(firstPage)-10:1;
            console.log("previousFirstPage : "+previousFirstPage);
            previousATag.setAttribute("href","/admin/record?nowPage="+previousFirstPage.toString()+"&rowSize="+rowSize);
            pageBox.prepend(previousATag);
        }
    })

</script>