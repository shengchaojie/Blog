package com.scj.interceptor;

/**
 * Created by shengchaojie on 2016/8/3.
 */
public class FrequencyMetaData {
    private String url;
    private long unitTime;
    private int unitCount;
    private FrequencyControlLevel level;
    private long interval;

    public FrequencyMetaData(String url, long unitTime, int unitCount, FrequencyControlLevel level,long interval) {
        this.url = url;
        this.unitTime = unitTime;
        this.unitCount = unitCount;
        this.level = level;
        this.interval =interval;
    }

    public FrequencyMetaData() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(long unitTime) {
        this.unitTime = unitTime;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public FrequencyControlLevel getLevel() {
        return level;
    }

    public void setLevel(FrequencyControlLevel level) {
        this.level = level;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}


