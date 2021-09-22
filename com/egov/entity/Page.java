package com.egov.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2021/4/28  - {TIME}
 */
public class Page <T>{
    private Integer pageNum;
    private  Integer pageSize;
    private  Integer totalSize;
    private  List <T> dateList;


    public Page(String pageNum) {
        if (pageNum != null){
            this.pageNum = Integer.parseInt(pageNum);
        }
        else {
            this.pageNum = 1;
        }
        this.pageSize = 3;
        this.dateList = new ArrayList<T>();
    }

    public List<T> getDateList() {
        return dateList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setDateList(List<T> dateList) {
        this.dateList = dateList;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
    public Integer getPageCount() {
        return totalSize % pageSize == 0 ? totalSize/pageSize : totalSize/pageSize + 1;
    }

}
