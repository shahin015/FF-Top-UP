package com.movies.toupadmin;

public class PackCalss {
    String taka,maindaimond,bonas,totaldaimond;

    public PackCalss(String taka, String maindaimond, String bonas, String totaldaimond) {

        this.taka = taka;
        this.maindaimond = maindaimond;
        this.bonas = bonas;
        this.totaldaimond = totaldaimond;
    }

    public PackCalss() {
    }

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }

    public String getMaindaimond() {
        return maindaimond;
    }

    public void setMaindaimond(String maindaimond) {
        this.maindaimond = maindaimond;
    }

    public String getBonas() {
        return bonas;
    }

    public void setBonas(String bonas) {
        this.bonas = bonas;
    }

    public String getTotaldaimond() {
        return totaldaimond;
    }

    public void setTotaldaimond(String totaldaimond) {
        this.totaldaimond = totaldaimond;
    }
}
