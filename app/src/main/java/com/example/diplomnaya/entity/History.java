package com.example.diplomnaya.entity;

import java.io.Serializable;

public class History implements Serializable {

    private String ruShirt;
    private String intShirt;
    private String euShirt;
    private String usShirt;
    private String ruJeans;
    private String intJeans;
    private String euJeans;
    private String usJeans;
    private String height;
    private String sex;

    public History(String ruShirt, String intShirt, String euShirt, String usShirt, String ruJeans, String intJeans, String euJeans, String usJeans, String height, String sex) {
        this.ruShirt = ruShirt;
        this.intShirt = intShirt;
        this.euShirt = euShirt;
        this.usShirt = usShirt;
        this.ruJeans = ruJeans;
        this.intJeans = intJeans;
        this.euJeans = euJeans;
        this.usJeans = usJeans;
        this.height = height;
        this.sex = sex;
    }

    public String ruShirt() {
        return ruShirt;
    }

    public void setRuShirt(String ruShirt) {
        this.ruShirt = ruShirt;
    }

    public String intShirt() {
        return intShirt;
    }

    public void setIntShirt(String intShirt) {
        this.intShirt = intShirt;
    }

    public String euShirt() {
        return euShirt;
    }

    public void setEuShirt(String euShirt) {
        this.euShirt = euShirt;
    }

    public String usShirt() {
        return usShirt;
    }

    public void setUsShirt(String usShirt) {
        this.usShirt = usShirt;
    }

    public String ruJeans() {
        return ruJeans;
    }

    public void setRuJeans(String ruJeans) {
        this.ruJeans = ruJeans;
    }

    public String intJeans() {
        return intJeans;
    }

    public void setIntJeans(String intJeans) {
        this.intJeans = intJeans;
    }

    public String euJeans() {
        return euJeans;
    }

    public void setEuJeans(String euJeans) {
        this.euJeans = euJeans;
    }

    public String usJeans() {
        return usJeans;
    }

    public void setUsJeans(String usJeans) {
        this.usJeans = usJeans;
    }

    public String height() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String sex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}