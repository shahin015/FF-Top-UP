package com.movies.daimontopup;

import java.util.Date;

public class DataClass {

    String fullname,phone,playerid,bank,account_no,transctionid;
    String  order_no;

    public DataClass(String fullname, String phone, String playerid, String bank, String account_no, String transctionid, String order_no) {
        this.fullname = fullname;
        this.phone = phone;
        this.playerid = playerid;
        this.bank = bank;
        this.account_no = account_no;
        this.transctionid = transctionid;
        this.order_no = order_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public DataClass() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getTransctionid() {
        return transctionid;
    }

    public void setTransctionid(String transctionid) {
        this.transctionid = transctionid;
    }
}
