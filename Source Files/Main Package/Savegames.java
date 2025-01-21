/**************************************
 * Project:         WizardArmageddon
 * Program:         Savegames.java
 **************************************/
package wizardarmageddon;

import java.io.*;
import java.util.*;

public class Savegames {
    //Declare file writing and reading tools, these tools are common for all methods
    private static FileWriter savegame;
    private static File savegameFile;
    private static PrintWriter pw;
    private static Scanner scanFile;
    
    /*****************************
     * Savegames
     * This method calls in the menu of save games once the program starts
     *****************************/
    public static void Savegames(){
        int menuChoice;
        
        //Try catch in case the file doesnt exist or theres an error in reading it
        try{
            savegameFile = new File("savegame.txt");
            scanFile = new Scanner(savegameFile);
            String[] tokens; 
            
            //Checks if the file has no save game, starts a new game
            if(scanFile.nextLine().equals("-1")){
                System.out.println("No save data found, starting a new game");
                Commands.pressEnter();
                newgame();
                return;
            }
            
            //Scans first line to display the name and level of the save game found
            tokens = scanFile.nextLine().split(",");
            
            Main.playName = tokens[0];
            Main.playLevel = Integer.parseInt(tokens[1]);
            
            System.out.println("Would you like to load a save game or start a new game?");
            System.out.println("\n1. Load save game (" + Main.playName + ", Level " + Main.playLevel +")");
            System.out.println("\n2. Start a new game (WARNING: your old save game will be deleted)");
            menuChoice = Commands.inputInt(1, 2);
            Commands.clear();
            
            //Switch for the choices given
            switch(menuChoice){
                case 1:{ //Either the save game loads
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.gold = Integer.parseInt(tokens[0]);
                    Main.totalGainedGold = Integer.parseInt(tokens[1]);
                    Main.playMaximumHealth = Integer.parseInt(tokens[2]);
                    
                    //Line for the 1st spell
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.spell1 = new Spell(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]);
                    
                    //Line for the 2nd spell
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.spell2 = new Spell(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]);
                    
                    //Line for the 3rd spell
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.spell3 = new Spell(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]);
                    
                    //Line for the 4th spell
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.spell4 = new Spell(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]);
                    
                    //Line for the 5th spell
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.spell5 = new Spell(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4]);
                    
                    //Line for the sword
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.sword = new Sword(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    
                    //Line for the staff
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.staff = new Staff(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]));
                    
                    //Line for the armor
                    tokens = scanFile.nextLine().split(",");
                    
                    Main.armor = new Armor(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    
                    break;
                }
                case 2:{ //Or a new game starts
                    newgame();
                    
                    break;
                }
            }
        }
        catch(IOException e){
            
            try{
                savegame = new FileWriter("savegame.txt");
            }
            catch(IOException e2){
                System.out.println("Error 1");
                System.exit(0);
            }
            
            newgame();
        }
    }
    
    /***************************
     * newgame
     * Starts a new game, deletes the already existing game
     ***************************/
    public static void newgame(){
        try{
            savegame = new FileWriter("savegame.txt");
            pw = new PrintWriter(savegame);
            
            pw.print("-1");
            pw.close();
            
            System.out.print("Please enter your username: ");
            Main.playName = Commands.inputString();
            Commands.clear();
        }
        catch(IOException e){
            System.out.println("File doesnt exist");
            System.exit(0);
        }
    }
    
    /***********************
     * save
     * Saves the player's data into the file "savegame.txt"
     * @return 1 if saved successfully, -1 if it didnt save
     ***********************/
    public static int save(){
        try{
            savegame = new FileWriter("savegame.txt");
            pw = new PrintWriter(savegame);
            
            pw.println(1);
            pw.println(Main.playName + "," + Main.playLevel);
            pw.println(Main.gold + "," + Main.totalGainedGold + "," + Main.playMaximumHealth);
            pw.println(Main.spell1.getName() + "," + Main.spell1.getDamageConstant() + ","+ Main.spell1.getDamageMultiplier() + "," + Main.spell1.getDodge() + "," + Main.spell1.getElement());
            pw.println(Main.spell2.getName() + "," + Main.spell2.getDamageConstant() + ","+ Main.spell2.getDamageMultiplier() + "," + Main.spell2.getDodge() + "," + Main.spell2.getElement());
            pw.println(Main.spell3.getName() + "," + Main.spell3.getDamageConstant() + ","+ Main.spell3.getDamageMultiplier() + "," + Main.spell3.getDodge() + "," + Main.spell3.getElement());
            pw.println(Main.spell4.getName() + "," + Main.spell4.getDamageConstant() + ","+ Main.spell4.getDamageMultiplier() + "," + Main.spell4.getDodge() + "," + Main.spell4.getElement());
            pw.println(Main.spell5.getName() + "," + Main.spell5.getDamageConstant() + ","+ Main.spell5.getDamageMultiplier() + "," + Main.spell5.getDodge() + "," + Main.spell5.getElement());
            pw.println(Main.sword.getName() + "," + Main.sword.getDamage() + "," + Main.sword.getCost());
            pw.println(Main.staff.getName() + "," + Main.staff.getDamageMultiplier() + "," + Main.staff.getCost());
            pw.println(Main.armor.getName() + "," + Main.armor.getArmor() + "," + Main.armor.getCost());
            
            pw.close();
            
            return 1;
        }
        catch(IOException e){
            System.out.println("Error saving game, returning to program");
            return -1;
        }
    }
}
