package com.gongon.analize.controller;

import com.gongon.analize.dao.CountryDao;
import com.gongon.analize.dao.implementation.CountryDaoImpl;
import com.gongon.analize.model.Country;
import com.gongon.analize.util.Utility;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.List;
import java.util.Scanner;

/**
 * Created by g0ng0n.
 */
public class Prompter {

    private Scanner scanner = new Scanner(System.in);
    private boolean exit;
    private CountryDao dao = new CountryDaoImpl();
    private Utility util = new Utility();

    public void runApplication() {
        do {
            promptMainMenu();
        } while (!exit);
    }


    private void promptMainMenu() {
        Utility.generateMessage("%n0----||===============================>%n" +
                "To chose enter a NUMBER of an option.%n" +
                "1. View list of Countries %n" +
                "2. Indicator statistics %n" +
                "3. Edit Country.%n" +
                "4. Add Country %n" +
                "5. Remove Country %n" +
                "6.Exit%n0----||===============================>%n");
        switch (scanner.nextInt()) {
            case 1:
                showAllContacts();
                break;
            case 2:
                getStatistics();
                break;
            case 3:
                editCountry();
                break;
            case 4:
                addCountry();
                break;
            case 5:
                removeCountry();
                break;
            case 6:
                exit = true;
                break;
            default:
                Utility.generateMessage("There is NO such an option");
                break;
        }
    }

    private void getStatistics() {

        List<Country> countries = util.getCountriesWithIndicators(dao.fetchAllCountries());

        Utility.generateMessage("Correlation Coefficient = %.6f%n", new PearsonsCorrelation().correlation(
                ArrayUtils.toPrimitive( dao.getAdultLiteracyRateFromCountries().toArray(new Double[dao.getAdultLiteracyRateFromCountries().size()])),
                ArrayUtils.toPrimitive( dao.getInternetUsersFromCountries().toArray(new Double[dao.getInternetUsersFromCountries().size()]))));

        Country countryWithMinInternetUsers = util.getCountryWithMinInternetUsers(countries);
        Utility.generateMessage("The country with the Minimum Internet Users is = %s %n", countryWithMinInternetUsers.getName());
        Country countryWithMaxInternetUsers = util.getCountryWithMaxInternetUsers(countries);
        Utility.generateMessage("The country with the Max Internet Users is = %s %n", countryWithMinInternetUsers.getName());

        Country countryWithMinLiteracyRate = util.getCountryWithMinLiteracyRate(countries);
        Utility.generateMessage("The country with the Minimum Literacy Rate22 is = %s %n", countryWithMinInternetUsers.getName());
        Country countryWithMaxLiteracyRate = util.getCountryWithMaxLiteracyRate(countries);
        Utility.generateMessage("The country with the Max Literacy Rate  is = %s %n", countryWithMinInternetUsers.getName());

    }


    private void showAllContacts(){

        List<Country> countries = dao.fetchAllCountries();

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


    private void addCountry() {
        scanner.nextLine();

        Utility.generateMessage("Enter Country code:%n");
        String code = util.validateCode().toUpperCase();

        Utility.generateMessage("Enter Country name:%n");
        String name = util.validateStringInput(scanner.nextLine());

        Utility.generateMessage("Enter Country internet users indicator:%n");
        Double internetUsers = util.checkDouble(scanner.nextLine());

        Utility.generateMessage("Enter Country adult literacy rate:%n");
        Double adultLiteracyRate =  util.checkDouble(scanner.nextLine());

        Country country = new Country.CountryBuilder(code)
                .withName(name)
                .withInternetUsers(internetUsers)
                .withAdultLiteracyRate(adultLiteracyRate)
                .build();

        dao.save(country);
        Utility.generateMessage("The country %s was added successfully!%n", country.getName());
    }

    private void removeCountry() {
        String countryName;
        boolean deleted = false;
        scanner.nextLine();
        do {
            Utility.generateMessage("Enter name of the Country to remove:%n");
            countryName = util.validateCode().toUpperCase();

            Country country = dao.fetchCountry(countryName);

            if (country !=null){
                dao.delete(country);
                deleted = true;
            }else{
                Utility.generateMessage("Country Not Found. Try again:%n");
                scanner.nextLine();
            }

        } while (!deleted);
    }

    private void editCountry() {

        String countryName;
        boolean deleted = false;
        scanner.nextLine();
        do {
            Utility.generateMessage("Enter name of the Country to remove:%n");
            countryName = util.validateCode().toUpperCase();

            Country country = dao.fetchCountry(countryName);

            scanner.nextLine();

            Utility.generateMessage("Enter the data of the Country to update:%n");

            Utility.generateMessage("Enter Country code:%n");
            String code = util.validateCode().toUpperCase();

            Utility.generateMessage("Enter Country name:%n");
            String name = util.validateStringInput(scanner.nextLine());

            Utility.generateMessage("Enter Country internet users indicator:%n");
            Double internetUsers = util.checkDouble(scanner.nextLine());

            Utility.generateMessage("Enter Country adult literacy rate:%n");
            Double adultLiteracyRate =  util.checkDouble(scanner.nextLine());

            Country countryToUpdate = new Country.CountryBuilder(code)
                    .withName(name)
                    .withInternetUsers(internetUsers)
                    .withAdultLiteracyRate(adultLiteracyRate)
                    .build();

            if (country !=null){
                dao.update(countryToUpdate);
                deleted = true;
            }else{
                Utility.generateMessage("Country Not Found. Try again:%n");
                scanner.nextLine();
            }

        } while (!deleted);

    }

}
