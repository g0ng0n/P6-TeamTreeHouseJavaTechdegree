package com.gongon.analize.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Created by g0ng0n.
 */
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Size(min = 1, max=3)
    private String code;

    @Column
    @Size(min = 1, max=32)
    private String name;

    @Column
    @Digits(integer=11, fraction=8)
    private Double internetUsers;

    @Column
    @Digits(integer=11, fraction=8)
    private Double adultLiteracyRate;

    // default constructor
    public Country(){

    }

    public Country(CountryBuilder builder){

        this.code = builder.code;
        this.name = builder.name;
        this.internetUsers = builder.internetUsers;
        this.adultLiteracyRate = builder.adultLiteracyRate;
    }

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

    public Double getInternetUsers() {
        return internetUsers;
    }

    public Double getAdultLiteracyRate() {
            return adultLiteracyRate;
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



    public static class CountryBuilder {

        private String code;

        private String name;

        private Double internetUsers;

        private Double adultLiteracyRate;

        public CountryBuilder(String code){
            this.code = code;
        }


        public CountryBuilder withName(String name){
            this.name = name;
            return this;
        }

        public CountryBuilder withInternetUsers(Double internetUsers){
            this.internetUsers = internetUsers;
            return this;
        }

        public CountryBuilder withAdultLiteracyRate( Double adultLiteracyRate){
            this.adultLiteracyRate = adultLiteracyRate;
            return this;
        }

        public Country build(){
            return new Country(this);
        }

    }
}
