package com.example.summer.models.pojo;

/**
 * @author 24047
 * @date 2022/6/27
 * @description 原本是用来同时展示data的中文名和数据值的，现在被弃用了
 */
public class WorkloadData {
    private String ChineseName;

    private Object value;

    public WorkloadData(String chineseName, Object value) {
        ChineseName = chineseName;
        this.value = value;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
