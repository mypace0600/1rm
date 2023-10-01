package com.example.rm.entity;

import lombok.Data;

@Data
public class Paging {
    private int totalCount; // 전체 데이터 개수
    private int rowSize = 10; // 한 페이지 안에 보이는 행의 수
    private int pageSize = 10; // 한 화면안에 보이는 페이지의 수
    private int nowPage = 1; // 현재 페이지

    private int totalPage; // 전체 페이지 수 Math.ceil(totalCount/rowSize)
    private int pageGroup; // 현재 페이지가 속한 페이지 그룹 Math.ceil(nowPage/totalPage)
    private int firstPage; // 현재 페이지가 속한 페이지 그룹의 첫번째 페이지 (lastPage - pageSize - 1 <= 0?1:lastPage - pageSize - 1)
    private int lastPage; // 현재 페이지가 속한 페이지 그룹의 마지막 페이지 (pageGroup * pageSize > totalPage ? totalPage : pageGroup * pageSize)
}

