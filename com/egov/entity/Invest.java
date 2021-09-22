package com.egov.entity;

/**
 * @author JJ
 * @date 2021/4/29  - {TIME}
 */
public class Invest {
    private  String invnum;
    private  String invname;
    private  String cty;
    private  String orgcode;
    private  String contactman;
    private  String contacttel;
    private  String email;
    private  String regdete;
    private  String usercode;
    private  String username;

    public String getRamark() {
        return ramark;
    }

    public void setRamark(String ramark) {
        this.ramark = ramark;
    }

    private  String ramark;


    public String getRegdete() {
        return regdete;
    }

    public void setRegdete(String regdete) {
        this.regdete = regdete;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInvnum() {
        return invnum;
    }

    public void setInvnum(String invnum) {
        this.invnum = invnum;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getCty() {
        return cty;
    }

    public void setCty(String cty) {
        this.cty = cty;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Invest() {
    }

    public Invest(String invnum, String invname, String cty, String regdete, String username) {
        this.invnum = invnum;
        this.invname = invname;
        this.cty = cty;
        this.regdete = regdete;
        this.username = username;
    }

    public Invest( String invname, String cty, String orgcode, String contactman, String contacttel, String email, String regdete, String usercode, String ramark) {
        this.invname = invname;
        this.cty = cty;
        this.orgcode = orgcode;
        this.contactman = contactman;
        this.contacttel = contacttel;
        this.email = email;
        this.regdete = regdete;
        this.usercode = usercode;
        this.ramark = ramark;
    }
}
