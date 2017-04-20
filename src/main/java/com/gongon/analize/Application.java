package com.gongon.analize;

import com.gongon.analize.controller.Prompter;
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

        Prompter prompter = new Prompter();
        prompter.runApplication();

    }


}