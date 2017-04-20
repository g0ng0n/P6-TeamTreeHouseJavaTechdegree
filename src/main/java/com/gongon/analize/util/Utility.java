package com.gongon.analize.util;

import com.gongon.analize.model.Country;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.h2.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by g0ng0n.
 */
public class Utility {

    private Scanner scanner = new Scanner(System.in);

    public static void generateMessage(String message, Object... objects) {
        System.out.printf(message, objects);
    }


    public String validateStringInput(String input){

        Boolean valid;
        do{

            valid = checkIfEmpty(input);
            if (!valid){
                input = scanner.nextLine();
            }
        }while (!valid);

        return input;
    }

    public String validateCode(){
        String input;

        Boolean valid = false;

        do{
            input = scanner.nextLine();
            valid = checkIfEmpty(input);
            if(input.length()==3 && valid){
                valid = true;
            }else{
                generateMessage("Code should be 3 characters long%nPlease try again:%n");
            }

        }while (!valid);
        return input;
    }

    private static Boolean checkIfEmpty(String input){

        if (input.isEmpty() || input.matches("\\s+")) {
            generateMessage("Can't be empty. Try again:%n");
            return false;
        }

        return true;
    }

    public Double checkDouble(String input){
        Boolean valid;
        Double value = null;
        do{
            if (input.isEmpty()){
                value = null;
                valid = true;
            }else{
                if (StringUtils.isNumber(input)){
                    value = Double.parseDouble(input);
                    valid = true;
                    if (value < 0 || value > 100) {
                        generateMessage("Value has to be in range from 0 to 100%nPlease try again:%n");
                        input = scanner.nextLine();
                        valid = false;
                    }
                }else {
                    valid = false;
                    input = scanner.nextLine();

                }
            }

        }while (!valid);

        return value;
    }

    public static List<Country> getCountriesWithIndicators(List<Country> countries) {

            return countries.stream()
                    .filter(country -> country.getInternetUsers() != null && country.getAdultLiteracyRate() != null)
                    .collect(Collectors.toList());
    }

    public Country getCountryWithMinInternetUsers(List<Country> countries) {

        return countries.stream()
                .min(Comparator.comparingDouble(country -> country.getInternetUsers()))
                .get();
    }

    public Country getCountryWithMaxInternetUsers(List<Country> countries) {
        return countries.stream()
                .max(Comparator.comparingDouble(country -> country.getInternetUsers()))
                .get();
    }

    public Country getCountryWithMinLiteracyRate(List<Country> countries) {
        return countries.stream()
                .min(Comparator.comparingDouble(country -> country.getAdultLiteracyRate()))
                .get();
    }

    public Country getCountryWithMaxLiteracyRate(List<Country> countries) {
        return countries.stream()
                .max(Comparator.comparingDouble(country -> country.getAdultLiteracyRate()))
                .get();
    }
}
