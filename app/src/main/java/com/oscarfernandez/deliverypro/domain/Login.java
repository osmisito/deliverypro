package com.oscarfernandez.deliverypro.domain;

public class Login {

    private String rut;
    private String pass;

    public Login(String rut, String pass){
        this.rut = rut;
        this.pass = pass;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
