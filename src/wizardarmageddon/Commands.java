/**************************************
 * Project:         WizardArmageddon
 * Program:         Commands.java
 **************************************/
package wizardarmageddon;

import java.util.*;

public class Commands {
    
    /******************************
     * pressEnter
     * This method makes a pause in the program until the user enters anything
     ******************************/
    public static void pressEnter(){
        Scanner scanS = new Scanner(System.in);
        
        for(int i = 0; i < 80; i++){System.out.print("*");}
        System.out.println("\nPress [Enter] to continue");
        scanS.nextLine();
        for(int i = 0; i < 80; i++){System.out.print("*");}
        for(int i = 0; i < 10; i++){System.out.println("\n");}
    }
    
    
    /*************************
     * clear
     * This method prints a line of asterisks followed by a 10 line space to seperate different section of the console, sort of 
     * "clearing" the screen
     *************************/
    public static void clear(){
        for(int i = 0; i < 80; i++){System.out.print("*");}
        for(int i = 0; i < 10; i++){System.out.println("\n");}
    }
    
    
    /*************************************
     * getRandom
     * This method gets a random integer value between 1 and the limit parameter, including 1 and the limit
     * @param limit - the limit of the random numbers, the maximum number 
     * @return - returns the random integer
     *************************************/
    public static int getRandom(int limit){
        int num = (int) ((Math.random() * limit) + 1);
        return num;
    }
    
    
    /***********************************************************
     * getRandomDouble
     * This method gets a random double value between min and max parameters
     * @param min - The minimum value
     * @param max - The maximum value
     * @return - returns the random double
     ***********************************************************/
    public static double getRandomDouble(double min, double max){
        double num = min + (Math.random() * (max - min));
        return num;
    }
    
    
    /*******************************************
     * inputInt
     * This method traps the user to input one of the right choices
     * @param min - the minimum choice the user must make
     * @param max - the maximum choice the user must make
     * @return - returns the user's input
     *******************************************/
    public static int inputInt(int min, int max){
        Scanner scanN = new Scanner(System.in);
        int input = 0;
        boolean flag = true;
        
        do{
            try{
                System.out.println("********************************************************************************\n");
                System.out.print("Enter input (int): ");
                input = scanN.nextInt();
                
                while(input < min || input > max){
                    System.out.println("********************************************************************************\n");
                    System.out.print("Invalid input, please try again: ");
                    input = scanN.nextInt();
                }
                
                flag = false;
            }catch(InputMismatchException e){
                System.out.println("********************************************************************************\n");
                System.out.println("Please enter an integer value!");
                scanN.nextLine();
            }
        }while(flag);
        
        return input;
    }
    
    
    /*********************************
     * inputString
     * This method gets a string input from the console and returns it
     * @return - returns the string input
     *********************************/
    public static String inputString(){
        Scanner scanS = new Scanner(System.in);
        
        String string = scanS.nextLine();
        
        return string;
    }
}