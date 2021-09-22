package com.egov.entity;

/**
 * @author JJ
 * @date 2021/5/11  - {TIME}
 */
public class EnterPrise {
    private String orgcode;
    private String regno;
    private String cnname;
    private String enname;
    private String contactman;
    private String contacttel;
    private String regcap;
    private String outregcap;
    private String regcry;
    private String usercode;
    private String regdate;

    public EnterPrise(String orgcode,String cnname, String regcap, String regcry,String outregcap) {
        this.orgcode = orgcode;
        this.cnname = cnname;
        this.regcap = regcap;
        this.regcry = regcry;
        this.outregcap = outregcap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    public EnterPrise() {
    }

    public EnterPrise(String orgcode, String regno, String cnname, String enname, String contactman, String contacttel, String regcap, String outregcap, String regcry, String usercode, String regdate) {
        this.orgcode = orgcode;
        this.regno = regno;
        this.cnname = cnname;
        this.enname = enname;
        this.contactman = contactman;
        this.contacttel = contacttel;
        this.regcap = regcap;
        this.outregcap = outregcap;
        this.regcry = regcry;
        this.usercode = usercode;
        this.regdate = regdate;
    }

    public EnterPrise(String orgcode, String cnname, String regdate, String username) {
        this.orgcode = orgcode;
        this.cnname = cnname;
        this.regdate = regdate;
        this.username = username;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getRegcap() {
        return regcap;
    }

    public void setRegcap(String regcap) {
        this.regcap = regcap;
    }

    public String getOutregcap() {
        return outregcap;
    }

    public void setOutregcap(String outregcap) {
        this.outregcap = outregcap;
    }

    public String getRegcry() {
        return regcry;
    }

    public void setRegcry(String regcry) {
        this.regcry = regcry;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
