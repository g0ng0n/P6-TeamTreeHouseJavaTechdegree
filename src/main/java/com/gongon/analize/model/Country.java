package com.gongon.analize.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by g0ng0n.
 */
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    private String name;

    private int internetUsers;

    private int adultLiteracyRate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(int internetUsers) {
        this.internetUsers = internetUsers;
    }

    public int getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(int adultLiteracyRate) {
        this.adultLiteracyRate = adultLiteracyRate;
    }


    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", internetUsers=" + internetUsers +
                ", adultLiteracyRate=" + adultLiteracyRate +
                '}';
    }
}
