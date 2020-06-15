package com.covid19.project;


class Data {

    String state, positive, negative, recovered, death, total, lastUpdated;

    public Data(String state, String positive, String negative, String recovered, String death, String total, String lastUpdated) {
        this.state = state;
        this.positive = positive;
        this.negative = negative;
        this.recovered = recovered;
        this.death = death;
        this.total = total;
        this.lastUpdated = lastUpdated;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
