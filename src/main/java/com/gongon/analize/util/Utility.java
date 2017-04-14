package com.gongon.analize.util;

import org.h2.util.StringUtils;

import java.util.Scanner;

/**
 * Created by gonzalo.gisbert on 14/04/17.
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

    public Float checkFloat(String input){
        Boolean valid;
        Float value = null;
        do{
            if (input.isEmpty()){
                value = null;
                valid = true;
            }else{
                if (StringUtils.isNumber(input)){
                    value = Float.parseFloat(input);
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

}
