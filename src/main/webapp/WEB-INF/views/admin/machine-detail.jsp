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
        const targetId = document.getElementById("machineId").value;
        const url = "/admin/machine/"+targetId;
        console.log("url : "+url);

        $.ajax({
            type: "delete",
            url: url,
            success: function(data) {
                alert("성공");
                location.href="/admin/machine";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        })
    });

    function machineUpdate(data,targetId){
        const url = '/admin/machine/'+targetId;
        console.log("id : "+targetId);
        $.ajax({
            type: "put",
            url: url,
            data:data,
            success: function(data) {
                alert("성공");
                location.href="/admin/machine";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }

    function machineInsert(data){
        const url = '/admin/machine';
        $.ajax({
            type: "POST",
            url: url,
            data:data,
            success: function(data) {
                alert("성공");
                location.href="/admin/machine";
            },
            error: function(xhr, error, msg) {
                console.log(error);
                console.log(msg);
            }
        });
    }

    const saveBtn = document.getElementById("saveBtn");
    saveBtn.addEventListener("click",function (){
        const targetId = document.getElementById("machineId").value;
        const machineName = document.getElementById("machineName").value;
        const imgUrl = document.getElementById("imgUrl").value;
        const machineType = document.getElementById("machineType").value;
        const stimulatePoint = document.getElementById("stimulatePoint").value;
        const videoUrl = document.getElementById("videoUrl").value;

        const data = {
            machineName:machineName,
            imgUrl:imgUrl,
            stimulatePoint:stimulatePoint,
            machineType:machineType,
            videoUrl:videoUrl
        };
        if(null != targetId && '' != targetId){
            machineUpdate(data,targetId);
        } else {
            machineInsert(data);
        }
    });
</script>
