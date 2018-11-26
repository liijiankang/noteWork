package com.ljk.modle;

/**
 * @program: noteWork
 * @description: 连接池信息
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-22 10:07
 **/
public class DataBaseInfo {

    private String url;
    private String driver;
    private String user;
    private String password;
    private String initialSize;
    private String maxTotal;
    private String maxIdle;
    private String minIdle;
    private String maxWaitMillis;
    private String removeAbandonedOnMaintenance;
    private String removeAbandonedOnBorrow;
    private String removeAbandonedTimeout;

    @Override
    public String toString() {
        return "DataBaseInfo{" +
                "url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", initialSize='" + initialSize + '\'' +
                ", maxTotal='" + maxTotal + '\'' +
                ", maxIdle='" + maxIdle + '\'' +
                ", minIdle='" + minIdle + '\'' +
                ", maxWaitMillis='" + maxWaitMillis + '\'' +
                ", removeAbandonedOnMaintenance='" + removeAbandonedOnMaintenance + '\'' +
                ", removeAbandonedOnBorrow='" + removeAbandonedOnBorrow + '\'' +
                ", removeAbandonedTimeout='" + removeAbandonedTimeout + '\'' +
                '}';
    }

    public DataBaseInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(String maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getRemoveAbandonedOnMaintenance() {
        return removeAbandonedOnMaintenance;
    }

    public void setRemoveAbandonedOnMaintenance(String removeAbandonedOnMaintenance) {
        this.removeAbandonedOnMaintenance = removeAbandonedOnMaintenance;
    }

    public String getRemoveAbandonedOnBorrow() {
        return removeAbandonedOnBorrow;
    }

    public void setRemoveAbandonedOnBorrow(String removeAbandonedOnBorrow) {
        this.removeAbandonedOnBorrow = removeAbandonedOnBorrow;
    }

    public String getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(String removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public DataBaseInfo(String url, String driver, String user, String password, String initialSize, String maxTotal, String maxIdle, String minIdle, String maxWaitMillis, String removeAbandonedOnMaintenance, String removeAbandonedOnBorrow, String removeAbandonedTimeout) {

        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.initialSize = initialSize;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.removeAbandonedOnMaintenance = removeAbandonedOnMaintenance;
        this.removeAbandonedOnBorrow = removeAbandonedOnBorrow;
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }
}
