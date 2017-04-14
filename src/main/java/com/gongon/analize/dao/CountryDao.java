package com.gongon.analize.dao;

import com.gongon.analize.model.Country;

import java.util.List;

/**
 * Created by gonzalo.gisbert on 12/04/17.
 */
public interface CountryDao {

    List<Country> fetchAllCountries();

    void update(Country country);

    void save(Country country);

    void delete(Country country);

    Country fetchCountry(String name);


}
