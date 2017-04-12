package com.gongon.analize.dao.implementation;

import com.gongon.analize.dao.CountryDao;
import com.gongon.analize.model.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by gonzalo.gisbert on 12/04/17.
 */
public class CountryDaoImpl implements CountryDao{

    // holds a reusable reference to aSession Factory (since we need only one)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        //create a StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Country> fetchAllContacts() {
        // Open session
        Session session = sessionFactory.openSession();

        // Create a criteria Object
        Criteria criteria = session.createCriteria(Country.class);

        // Get a List of countries objeccts according to the crietria object
        List<Country> countries = criteria.list();

        // Close session
        session.close();
        return countries;
    }
}
