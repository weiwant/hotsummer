package com.example.summer.models.pojo;


import java.util.List;
import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/27
 */
public class TeachingWorkloadFormat {

    public TeachingWorkloadFormat(){
//空的构造方法
    }


    public TeachingWorkloadFormat(List<WorkloadData> dataList, String[] workloadTableHeader, String[] workloadTableHeaderEnglish, List<Map<String, Object>> workloadTableDataString) {
        this.dataList = dataList;
        this.workloadTableHeader = workloadTableHeader;
        this.workloadTableHeaderEnglish = workloadTableHeaderEnglish;
        this.workloadTableDataString = workloadTableDataString;
    }

    private List<WorkloadData> dataList;

    public List<WorkloadData> getDataList() {
        return dataList;
    }

    public void setDataList(List<WorkloadData> dataList) {
        this.dataList = dataList;
    }

    private String[] workloadTableHeader;

    private String[] workloadTableHeaderEnglish;

    public TeachingWorkloadFormat(String[] workloadTableHeader, String[] workloadTableHeaderEnglish, List<Map<String, Object>> workloadTableDataString) {
        this.workloadTableHeader = workloadTableHeader;
        this.workloadTableHeaderEnglish = workloadTableHeaderEnglish;
        this.workloadTableDataString = workloadTableDataString;
    }

    public String[] getWorkloadTableHeaderEnglish() {
        return workloadTableHeaderEnglish;
    }

    public void setWorkloadTableHeaderEnglish(String[] workloadTableHeaderEnglish) {
        this.workloadTableHeaderEnglish = workloadTableHeaderEnglish;
    }

    private List<Map<String,Object>> workloadTableDataString;//这是workloadTableData全部转为了String


    public List<Map<String,Object>> getWorkloadTableDataString() {
        return workloadTableDataString;
    }

    public TeachingWorkloadFormat(String[] workloadTableHeader, List<Map<String, Object>> workloadTableDataString) {
        this.workloadTableHeader = workloadTableHeader;
        this.workloadTableDataString = workloadTableDataString;
    }

    public void setWorkloadTableDataString(List<Map<String,Object>>  workloadTableDataString) {
        this.workloadTableDataString = workloadTableDataString;
    }

    public String[] getWorkloadTableHeader() {
        return workloadTableHeader;
    }

    public void setWorkloadTableHeader(String[] workloadTableHeader) {
        this.workloadTableHeader = workloadTableHeader;
    }


}
