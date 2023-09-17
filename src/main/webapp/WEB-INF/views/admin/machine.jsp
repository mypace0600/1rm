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
               <%-- <button type="button">관리자 로그인</button>
                <button type="button">관리자 가입</button>
                <button type="button" style="display: none">로그아웃</button>
                <button type="button" style="display: none">프로필</button>--%>
            </div>
        </div>
    </nav>
</header>
<main class="main">
    <div class="main-content">
        <div class="search-box">
            <div class="search-box-title">머신검색</div>
            <div class="search-box-detail">
                <select>
                    <option value="ALL">전체</option>
                    <option value="NAME">이름</option>
                    <option value="ID">id</option>
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
                    머신목록
                </div>
                <div class="table-meta-paging">
                    <span>총 건</span>
                    <select>
                        <option value="10">10건</option>
                        <option value="30">30건</option>
                        <option value="50">50건</option>
                    </select>
                </div>
            </div>
            <table class="table table-striped">
                <colgroup>
                    <col span="10%">
                    <col span="10%">
                    <col span="10%">
                    <col span="10%">
                    <col span="20%">
                    <col span="20%">
                    <col span="20%">
                </colgroup>
                <thead>
                <tr>
                    <th scope="col">no</th>
                    <th scope="col">이름</th>
                    <th scope="col">타입</th>
                    <th scope="col">자극포인트</th>
                    <th scope="col">이미지</th>
                    <th scope="col">썸네일</th>
                    <th scope="col">동영상</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${ fn:length(machineList) == 0}">
                        <tr>
                            <td colspan="7" >데이터가 없습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="item" items="${machineList}" varStatus="status">
                            <tr>
                                <td>${status.index +1}</td>
                                <td><a href="/admin/machine/detail/${item.id}">${item.machineName}</a></td>
                                <td>${item.machineType}</td>
                                <td>${item.stimulatePoint}</td>
                                <td>${item.imgUrl}</td>
                                <td>${item.thumbImgUrl}</td>
                                <td>${item.videoUrl}</td>
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
            <div>
                <button class="primary-btn">머신등록</button>
            </div>
        </div>

    </div>
</main>
<footer>

</footer>
</body>
</html>

