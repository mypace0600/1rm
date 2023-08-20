<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <a href="/admin/users">회원관리</a>
                    <a href="/admin/records">기록관리</a>
                    <a href="/admin/machines">기기관리</a>
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
                            <col span="20%">
                            <col span="20%">
                            <col span="20%">
                            <col span="20%">
                            <col span="10%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">no</th>
                            <th scope="col">id</th>
                            <th scope="col">이름</th>
                            <th scope="col">출석률</th>
                            <th scope="col">최근 출석일</th>
                            <th scope="col">뱃지</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="6" >데이터가 없습니다.</td>
                        </tr>
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

