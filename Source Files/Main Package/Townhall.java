/**************************************
 * Project:         WizardArmageddon
 * Program:         Townhall.java
 **************************************/
package wizardarmageddon;

import java.util.*;
import java.io.*;

public class Townhall {
    //Declare file writing and reading tools, these tools are common for all methods
    private static FileWriter previousRuns;
    private static File previousRunsFile;
    private static PrintWriter pw;
    private static Scanner scanFile;
    
    //Arraylist that has the enemies defeated
    public static ArrayList<String> enemiesDefeated = new ArrayList<String>();
    
    /****************************
     * Townhall
     * This acts as a hub where the user can view previous runs and enemies defeated from
     ****************************/
    public static void Townhall(){
        //Declare necessary variables
        int menuChoice = 0;
        
        //While loop that ends when the user wishes to exit the townhall
        while(menuChoice != 4){
            //Print the banner and a menu, then ask for the user's choice
            System.out.println("********************************************************************************");
            System.out.println("*                                 The Townhall                                 *");
            System.out.println("*****                                                                      *****");
            System.out.println("\nWelcome to the townhall, here you can view the enemies you have defeated, previous "
                    + "\nruns, and search for an enemy to check how many times you have defeated it");
            System.out.println("\nWhat would you like to do?");
            System.out.println("\n1. Hall of Fame (Previous runs)");
            System.out.println("\n2. Trophies of Battle (Enemies defeated)");
            System.out.println("\n3. Trophy Count (Search for an enemy)");
            System.out.println("\n4. Exit\n");
            menuChoice = Commands.inputInt(1, 4);
            Commands.clear();
            
            //Switch for the user's choice, each case just calls a corresponding method
            switch(menuChoice){
                case 1:{
                    previousRuns();
                    break;
                }
                case 2:{
                    enemiesDefeated();
                    break;
                }
                case 3:{
                    enemySearch();
                    break;
                }
                case 4:{
                    break;
                }
            }
        }
    }
    
    
    /********************************************************************
     * printPreviousRun
     * This method prints the user stats of this run to a file
     * @param name - The player's username 
     * @param level - The player's level
     * @param gold - The player's total accumulated gold
     ********************************************************************/
    public static void printPreviousRun(String name, int level, int gold){
        try{
            previousRuns = new FileWriter("PreviousRuns.txt", true);
            pw = new PrintWriter(previousRuns);
            
            pw.println(name + "," + level + "," + gold);
            
            pw.close();
        }
        catch(IOException e){
            System.out.println("Error with printing highscore: " + e.getMessage());
            Commands.pressEnter();
        }
    }
    
    
    /********************************************
     * addEnemy
     * This method adds an enemy to the enemies defeated arraylist
     * @param enemyName - The enemy's name
     ********************************************/
    public static void addEnemy(String enemyName){
        enemiesDefeated.add(enemyName);
    }
    
    
    /*********************************
     * previousRuns
     * This method prints the previous runs of the user from a file
     *********************************/
    private static void previousRuns(){
        String[] tokens;
        String name;
        int level;
        int gold;
        int run = 0;
        
        try{
            previousRunsFile = new File("PreviousRuns.txt");
            scanFile = new Scanner(previousRunsFile);
            
            System.out.println("Previous runs:");
            
            while(scanFile.hasNext()){
                run++;
                
                tokens = scanFile.nextLine().split(",");
                name = tokens[0];
                level = Integer.parseInt(tokens[1]);
                gold = Integer.parseInt(tokens[2]);
                
                System.out.println("\n\n\nRun " + run + "\nName: " + name + "\nLevel: " + level + "\nTotal Gold Gained: " + gold);
            }
            
            scanFile.close();
            
            Commands.pressEnter();
        }
        catch(IOException e){
            System.out.println("Error encountered: " + e.getMessage() + " OR previous runs not found");
            Commands.pressEnter();
        }
    }
    
    
    /************************************
     * enemiesDefeated
     * This method prints all the enemies the user has defeated in this run
     ************************************/
    private static void enemiesDefeated(){
        System.out.println("You have defeated these enemies in order: ");
        
        for(int i = 0; i < enemiesDefeated.size(); i++){
            System.out.println("\n" + enemiesDefeated.get(i) + "   Defeated!");
        }
        
        Commands.pressEnter();
    }
    
    
    /**************************************
     * search
     * This method searches throught the enemies defeated arraylist to find how many times an enemy has been defeated
     * @param enemy - The enemy name that is being looked for
     * @return - The number of times the enemy has been defeated or -1 if the enemy is not found
     **************************************/
    private static int search(String enemy){
        int count = 0;
        
        for(int i = 0; i < enemiesDefeated.size(); i++){
            if(enemy.equalsIgnoreCase(enemiesDefeated.get(i))){
                count++;
            }
        }
        
        if(count == 0){
            System.out.println("The " + enemy + " has not been found among the enemies defeated");
            Commands.pressEnter();
            return -1;
        }
        
        return count;
    }
    
    
    /********************************
     * enemySearch
     * This method allows the user to search for an enemy to check how many times it has been defeated
     ********************************/
    private static void enemySearch(){
        String enemy;
        int count;
        
        System.out.println("Which enemy do you want to search for?");
        enemy = Commands.inputString();
        Commands.clear();
        
        count = search(enemy);
        
        if(count == -1){
            return;
        }
        
        System.out.println("You have defeated the " + enemy + " " + count + " time(s) before");
        Commands.pressEnter();
    }
}
