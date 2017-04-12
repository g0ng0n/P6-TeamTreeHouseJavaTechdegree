package com.gongon.analize;

import com.gongon.analize.dao.CountryDao;
import com.gongon.analize.dao.implementation.CountryDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import com.gongon.analize.model.Country;

import java.util.List;

/**
 * Created by g0ng0n.
 */
public class Application {


    public static void main (String[] args){

        showAllContacts();


    }



    private static void showAllContacts(){

        CountryDao dao = new CountryDaoImpl();
        List<Country> countries = dao.fetchAllContacts();

        String leftAlignFormat = "| %-4s | %-30s | %-14s | %-8s |%n";

        System.out.format("+------+--------------------------------+----------------+----------+%n");
        System.out.format("| Code | Country                        | Internet Users | Literacy |%n");
        System.out.format("+------+--------------------------------+----------------+----------+%n");
        for (Country c : countries) {

            System.out.format(
                    leftAlignFormat,
                    c.getCode(),
                    c.getName(),
                    c.getInternetUsers() == null ? "--" : String.format("%.2f", c.getInternetUsers()),
                    c.getAdultLiteracyRate() == null ? "--" : String.format("%.2f", c.getAdultLiteracyRate()));
        }
        System.out.format("+------+--------------------------------+----------------+----------+%n");

    }


}