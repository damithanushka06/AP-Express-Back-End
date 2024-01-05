package com.ap_express_server.models.chart;

public class ChartData {

    private int month;

    private int noOfItems;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month =month;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public ChartData(int month, int noOfItems) {
        this.month = month;
        this.noOfItems = noOfItems;
    }
}
