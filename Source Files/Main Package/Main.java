/**************************************
 * Project:         WizardArmageddon
 * Program:         Main.java
 **************************************/
package wizardarmageddon;

public class Main {
    //Player stats variables
    public static String playName;
    public static int gold = 0;
    public static int totalGainedGold = 0;
    public static int playMaximumHealth = 2000;
    public static int playLevel = 1;
    
    //Inventoy variables
    public static Spell spell1 = Data.spells[0];
    public static Spell spell2 = Data.spells[1];
    public static Spell spell3 = Data.spells[2];
    public static Spell spell4 = new Spell();
    public static Spell spell5 = new Spell();
    public static Sword sword = new Sword();
    public static Staff staff = new Staff();
    public static Armor armor = new Armor();
    
    //Shop variables
    private static Sword shopSword;
    private static Staff shopStaff;
    private static Armor shopArmor;
    private static Spell shopSpell;
    private static Spell makeSpell;
    private static Spell shopRandomSpell;
    private static Sword shopRandomSword;
    private static Staff shopRandomStaff;
    private static Armor shopRandomArmor;
    
    //This variable may be modified from other classes so it is a public static variable
    public static int villageChoice;
        
    public static void main(String[] args) {
        //Introduction to the game
        System.out.println("********************************************************************************");
        System.out.println("*                                                                              *");
        System.out.println("*                      W I Z A R D'S  A R M A G E D D O N                      *");
        System.out.println("*                                                                              *");
        System.out.println("********************************************************************************");
        System.out.println("\nWelcome to Wizard's Armageddon!");
        System.out.println("\nA Roguelike RPG fighting game with a unique battle system!\n");
        Commands.pressEnter();
        
        
        Savegames.Savegames(); //Calls in savegames so that the program either starts a new game or loads an old save game
        
        
        System.out.println("Rules to follow:");
        System.out.println("\n1. Please follow the input instructions, if asked for an integer input, ONLY enter integer inputs."
                + "\nAny other input may lead to a game breaking error, if there is no instrcutions then the input is"
                + "\na String input.");
        System.out.println("\n2. You MUST finish the game, play the game until you lose and do not stop until your player dies."
                + "\nIF you stop the program midway through the game, your progress will not be saved to previous runs.");
        System.out.println("\n3. You MUST enjoy this game, this game is the best game ever made, if you do not agree to this "
                + "\nstatement then you shall not play.");
        Commands.pressEnter();
        
        
        System.out.println("********************************************************************************");
        System.out.println("*                                   PROLOGUE                                   *");
        System.out.println("*****                                                                      *****");
        System.out.println("\n" + playName + ", welcome to Riucaria! A land far far away, hidden by a fog that none but the"
                + "\nstrong can pass! But you have passed the fog and succeeded in reaching this mysterious and magical land!\n"
                + "\nYou seek to learn all sorts of magic, defeat all the enemies, and become the strongest wizard to ever exist!\n"
                + "\nAs soon as you arrive on Riucaria, a village greets you and welcomes you, and you decide this shall"
                + "\nbe your hub, the place where you come back to when you are sick of fighting.");
        Commands.pressEnter();
        
        
        //While loop for the village, never ends but the program ends when player dies
        while(true){
            villageChoice = 0;
            //Setting up the shop everytime the user returns from an adventure
            shopSword = Data.swords[Commands.getRandom(Data.swords.length - 1) - 1];
            shopStaff = Data.staffs[Commands.getRandom(Data.staffs.length - 1) - 1];
            shopArmor = Data.armors[Commands.getRandom(Data.armors.length - 1) - 1];
            shopSpell = Data.spells[Commands.getRandom(Data.spells.length - 1) - 1];
            
            
            while(villageChoice != 5){
                //Village menu, this is the main menu of the entire game
                System.out.println("********************************************************************************");
                System.out.println("*                                 The  Village                                 *");
                System.out.println("*****                                                                      *****");
                
                System.out.println("\nLevel: " + playLevel + "\t\t\t\t\t\t\t\tGold: " + gold + " G");
                System.out.println("\n********************************************************************************");
                System.out.println("\nWhere do you want to go?");
                System.out.println("\n1. The Shop");
                System.out.println("\n2. The Library");
                System.out.println("\n3. The Inn");
                System.out.println("\n4. The Town Hall");
                System.out.println("\n5. Start a new adventure");
                System.out.println("\n6. Save and exit\n");
                villageChoice = Commands.inputInt(1, 6);
                
                Commands.clear();
                
                //Switch for the village choice, each case is a different mechanic
                switch(villageChoice){
                    case 1:{
                        //Case 1 for shop
                        shop();
                        break;
                    }
                    case 2:{
                        //Case 2 for library, this has tutorial, spell info and enemy info
                        library();
                        break;
                    }
                    case 3:{
                        //Case 3 for the inn, this has the user info, inventory and memorized spells
                        inn();
                        break;
                    }
                    case 4:{
                        //Case 4 calls the townhall method from the townhall class, this has the saved previous runs, defeated enemies, and a search for enemies mechanic
                        Townhall.Townhall();
                        break;
                    }
                    case 5:{
                        //Case 5 calls the adventure method from the adventure class, this has adventure and battle, the game is basically this
                        Adventure.Adventure();
                        break;
                    }
                    case 6:{
                        //Case 6 for saving and exitting the game
                        exit();
                        break;
                    }
                
                } //End of switch
            
            } //End of village while loop
            
        } //End of never ending while loop
    
    } //End of main method
    
    
    /************************
     * shop
     * This method acts as a shop for the user, it has multiple items the player can buy
     ************************/
    public static void shop(){
        //Declaring necessary varibales
        int shopChoice = 0;
        int buyChoice;
        int spellChoice;
        int keepChoice;
        int itemCost;
        double treasureChestRandom;
        
        //While loop that continues until the user chooses to exit the shop
        while(shopChoice != 9){
            //Shop banner with all the available items
            System.out.println("********************************************************************************");
            System.out.println("*                                   The Shop                                   *");
            System.out.println("*****                                                                      *****");
            System.out.println("\nWelcome to the shop! Items of the day are 25% off" + "\t\t\tGold: " + gold + " G");
            System.out.println("\n********************************************************************************");
            System.out.println("\nWhat would you like to view?");
            itemCost = (int) (shopSword.getCost() * 0.75);
            System.out.println("\n1. Sword of the Day: " + shopSword.getName() + " (" + itemCost + " G)");
            itemCost = (int) (shopStaff.getCost() * 0.75);
            System.out.println("\n2. Staff of the Day: " + shopStaff.getName() + " (" + itemCost + " G)");
            itemCost = (int) (shopArmor.getCost() * 0.75);
            System.out.println("\n3. Armor of the Day: " + shopArmor.getName() + " (" + itemCost + " G)");
            System.out.println("\n4. Spell of the Day: " + shopSpell.getName() + " (150 G)");
            System.out.println("\n5. Unknown book of knowledge (75 G)");
            System.out.println("\n6. Mystery Treasure chest (500 G)");
            System.out.println("\n7. Browse all available items");
            
            if(playLevel < 10){
                System.out.println("\n8. %*@$ &( !%#&$ [UNLOCKED AT LEVEL 10]");
            }
            else{
                System.out.println("\n8. Tome of Magic (500 G)");
            }
            
            System.out.println("\n9. Exit\n");
            shopChoice = Commands.inputInt(1, 9);
            Commands.clear();
            
            //Switch for each item the player can buy
            switch(shopChoice){
                case 1:{
                    //Case 1 is for sword
                    
                    itemCost = (int) (shopSword.getCost() * 0.75);
                    
                    //Print the available sword and current sword and ask user if they wish to buy
                    System.out.println("Sword of the Day");
                    System.out.println("\n" + shopSword.toString());
                    System.out.println("\nCost on sale: " + itemCost + " G");
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Sword");
                    System.out.println("\n" + sword.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current sword, and if you have"
                            + "\nthe same sword you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the sword to the new sword
                    gold -= itemCost;
                    sword = shopSword;
                    
                    System.out.println("You buy the " + sword.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                        
                    break;
                }
                case 2:{
                    //Case 2 for staff
                    
                    itemCost = (int) (shopStaff.getCost() * 0.75);
                    
                    //Print available staff and current staff and ask if the user wishes to buy
                    System.out.println("Staff of the Day");
                    System.out.println("\n" + shopStaff.toString());
                    System.out.println("\nCost on sale: " + itemCost + " G");
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Staff");
                    System.out.println("\n" + staff.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current staff, and if you have"
                            + "\nthe same staff you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the staff to the new staff
                    gold -= itemCost;
                    staff = shopStaff;
                    
                    System.out.println("You buy the " + staff.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 3:{
                    //Case 3 for armor
                    
                    itemCost = (int) (shopArmor.getCost() * 0.75);
                    
                    //Print available armor and current armor and ask if the user wishes to buy
                    System.out.println("Armor of the Day");
                    System.out.println("\n" + shopArmor.toString());
                    System.out.println("\nCost on sale: " + itemCost + " G");
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Armor");
                    System.out.println("\n" + armor.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current armor, and if you have"
                            + "\nthe same armor you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the armor to the new armor
                    gold -= itemCost;
                    armor = shopArmor;

                    System.out.println("You buy the " + armor.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 4:{
                    //Case 4 for spell
                    
                    //Print available spell and ask if the user wishes to buy
                    System.out.println("Spell of the Day");
                    System.out.println("\n" + shopSpell.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy for 150 G? (If you buy you will lose one of your current spells)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(150)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold and ask which spell the user wishes to replace with the new spell
                    gold -= 150;
                    System.out.println("You buy " + shopSpell.getName() + " " + shopSpell.getElement() + "!");
                    System.out.println("\nWhich spell slot would you like to use for this spell?");
                    System.out.println("\nSlot 1: " + spell1.getName() + " " + spell1.getElement());
                    System.out.println("\nSlot 2: " + spell2.getName() + " " + spell2.getElement());
                    System.out.println("\nSlot 3: " + spell3.getName() + " " + spell3.getElement());
                    System.out.println("\nSlot 4: " + spell4.getName() + " " + spell4.getElement());
                    System.out.println("\nSlot 5: " + spell5.getName() + " " + spell5.getElement() + "\n");
                    spellChoice = Commands.inputInt(1, 5);
                    Commands.clear();
                    
                    //Switch that replaces a spell slot with the new spell according to the user's choice
                    switch(spellChoice){
                        case 1:{
                            System.out.println("You have replaced " + spell1.getName() + " with " + shopSpell.getName());
                            spell1 = shopSpell;
                            break;
                        }
                        case 2:{
                            System.out.println("You have replaced " + spell2.getName() + " with " + shopSpell.getName());
                            spell2 = shopSpell;
                            break;
                        }
                        case 3:{
                            System.out.println("You have replaced " + spell3.getName() + " with " + shopSpell.getName());
                            spell3 = shopSpell;
                            break;
                        }
                        case 4:{
                            System.out.println("You have replaced " + spell4.getName() + " with " + shopSpell.getName());
                            spell4 = shopSpell;
                            break;
                        }
                        case 5:{
                            System.out.println("You have replaced " + spell5.getName() + " with " + shopSpell.getName());
                            spell5 = shopSpell;
                            break;
                        }
                    }
                    System.out.println("\nYou now have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 5:{
                    //Case 5 for random spell
                    
                    //Print message saying what the random book of knowledge is and ask if the user wishes to buy
                    System.out.println("Unknown Book of Knowledge");
                    System.out.println("\nThis book gives you the knowledge of a random spell");
                    System.out.println("\nWould you like to buy for 75 G? (You can discard the spell if you didn't like it, but you will lose Gold)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    }
                    else if(buyChoice == 1 && !buy(75)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold, assign a random spell, and print the random spell information then ask if the user wishes to keep this spell
                    gold -= 75;
                    
                    shopRandomSpell = Data.spells[Commands.getRandom(Data.spells.length -1)];
                    
                    System.out.println("The Random Book of Knowledge has " + shopRandomSpell.getName());
                    System.out.println("\n" + shopRandomSpell.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to keep? (If you keep you will lose one of your current spells)");
                    System.out.println("\n1. Keep");
                    System.out.println("\n2. Discard\n");
                    keepChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //If the user wishes to discard the spell, break the case
                    if(keepChoice == 2){
                        System.out.println("You choose to discard " + shopRandomSpell.getName() + "!");
                        System.out.println("\nYou now have " + gold + " G left");
                        Commands.pressEnter();
                        break;
                    } 
                    
                    //Ask which spell the user wishes to replace with the new spell
                    System.out.println("\nWhich spell slot would you like to use for " + shopRandomSpell.getName() + "?");
                    System.out.println("\nSlot 1: " + spell1.getName() + " " + spell1.getElement());
                    System.out.println("\nSlot 2: " + spell2.getName() + " " + spell2.getElement());
                    System.out.println("\nSlot 3: " + spell3.getName() + " " + spell3.getElement());
                    System.out.println("\nSlot 4: " + spell4.getName() + " " + spell4.getElement());
                    System.out.println("\nSlot 5: " + spell5.getName() + " " + spell5.getElement() + "\n");
                    spellChoice = Commands.inputInt(1, 5);
                    Commands.clear();
                    
                    //Switch that replaces a spell slot with the new spell according to the user's choice
                    switch(spellChoice){
                        case 1:{
                            System.out.println("You have replaced " + spell1.getName() + " with " + shopRandomSpell.getName());
                            spell1 = shopRandomSpell;
                            break;
                        }
                        case 2:{
                            System.out.println("You have replaced " + spell2.getName() + " with " + shopRandomSpell.getName());
                            spell2 = shopRandomSpell;
                            break;
                        }
                        case 3:{
                            System.out.println("You have replaced " + spell3.getName() + " with " + shopRandomSpell.getName());
                            spell3 = shopRandomSpell;
                            break;
                        }
                        case 4:{
                            System.out.println("You have replaced " + spell4.getName() + " with " + shopRandomSpell.getName());
                            spell4 = shopRandomSpell;
                            break;
                        }
                        case 5:{
                            System.out.println("You have replaced " + spell5.getName() + " with " + shopRandomSpell.getName());
                            spell5 = shopRandomSpell;
                            break;
                        }
                    }
                    
                    System.out.println("\nYou now have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 6:{
                    //Case 6 for random item 
                    
                    //Print what the mystery treasure chest is and ask if the user wishes to buy
                    System.out.println("Mystery Treasure Chest");
                    System.out.println("\nThis chest gives you a random sword, staff, or armor");
                    System.out.println("\nWould you like to buy for 500 G? (You can discard the item if you didn't like it, but you will lose Gold)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    }
                    else if(buyChoice == 1 && !buy(500)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold and assign a double value between 0 and 1 to a variable
                    gold -= 500;
                    
                    treasureChestRandom = Commands.getRandomDouble(0, 1);
                    
                    //If else statements for 3 equally random cases
                    if(treasureChestRandom < 0.333333){
                        //First case is for a sword, assign a random sword to a variable
                        shopRandomSword = Data.swords[Commands.getRandom(Data.swords.length - 1) - 1];
                        
                        //Print the random sword, the current sword, and ask if the user wishes to keep the sword
                        System.out.println("The Mystery Treasure Chest has a(n) " + shopRandomSword.getName());
                        System.out.println("\n" + shopRandomSword.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nYour current Sword");
                        System.out.println("\n" + sword.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nWould you like to keep? (If you keep you will lose your current sword)");
                        System.out.println("\n1. Keep");
                        System.out.println("\n2. Discard\n");
                        keepChoice = Commands.inputInt(1, 2);
                        Commands.clear();
                        
                        //If the user wishes to discard, break the case
                        if(keepChoice == 2){
                            System.out.println("You choose to discard the " + shopRandomSword.getName() + "!");
                            System.out.println("\nYou now have " + gold + " G left");
                            Commands.pressEnter();
                            break;
                        } 
                        
                        //Set the current sword to the random sword
                        sword = shopRandomSword;
                        
                        System.out.println("You choose to keep the " + shopRandomSword.getName());
                        System.out.println("\nYou now have " + gold + " G left");
                        Commands.pressEnter();
                        
                        break;
                    } 
                    else if (treasureChestRandom > 0.333333 && treasureChestRandom < 0.666667){
                        //Second case is for a staff, assign a random staff to a variable
                        shopRandomStaff = Data.staffs[Commands.getRandom(Data.staffs.length - 1) - 1];
                        
                        //Print the random staff, the current staff, and ask if the user wishes to keep the staff
                        System.out.println("The Mystery Treasure Chest has a " + shopRandomStaff.getName());
                        System.out.println("\n" + shopRandomStaff.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nYour current Staff");
                        System.out.println("\n" + staff.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nWould you like to keep? (If you keep you will lose your current Staff)");
                        System.out.println("\n1. Keep");
                        System.out.println("\n2. Discard\n");
                        keepChoice = Commands.inputInt(1, 2);
                        Commands.clear();
                        
                        //If the user wishes to discard, break the case
                        if(keepChoice == 2){
                            System.out.println("You choose to discard the " + shopRandomStaff.getName() + "!");
                            System.out.println("\nYou now have " + gold + " G left");
                            Commands.pressEnter();
                            break;
                        } 
                        
                        //Set the current staff to the random staff
                        staff = shopRandomStaff;
                        
                        System.out.println("You choose to keep the " + shopRandomStaff.getName());
                        System.out.println("\nYou now have " + gold + " G left");
                        Commands.pressEnter();
                        
                        break;
                    }
                    else {
                        //Third case is for armor, assign a random armor to a variable
                        shopRandomArmor = Data.armors[Commands.getRandom(Data.armors.length - 1) - 1];
                        
                        //Print the random armor, the current armor, and ask if the user wishes to keep the armor
                        System.out.println("The Mystery Treasure Chest has a " + shopRandomArmor.getName());
                        System.out.println("\n" + shopRandomArmor.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nYour current Armor");
                        System.out.println("\n" + armor.toString());
                        System.out.println("\n********************************************************************************");
                        System.out.println("\nWould you like to keep? (If you keep you will lose your current Armor)");
                        System.out.println("\n1. Keep");
                        System.out.println("\n2. Discard\n");
                        keepChoice = Commands.inputInt(1, 2);
                        Commands.clear();
                        
                        //If the user wishes to discard, break the case
                        if(keepChoice == 2){
                            System.out.println("You choose to discard the " + shopRandomArmor.getName() + "!");
                            System.out.println("\nYou now have " + gold + " G left");
                            Commands.pressEnter();
                            break;
                        } 
                        
                        //Set the current armor to the random armor
                        armor = shopRandomArmor;
                        
                        System.out.println("You choose to keep the " + shopRandomArmor.getName());
                        System.out.println("\nYou now have " + gold + " G left");
                        Commands.pressEnter();
                        
                        break;
                    }
                }
                case 7:{
                    //Case 7 is for browsing all items, calls a method
                    browse();
                    break;
                }
                case 8:{
                    //Case 8 for allowing the player to make a custom spell
                    
                    //Checks if the user is below the level threshold
                    if(playLevel < 10){
                        System.out.println("You do not have enough magic experience!");
                        System.out.println("\nCome back when you are level 10");
                        Commands.pressEnter();
                        break;
                    }
                    
                    System.out.println("Tome of Magic");
                    System.out.println("\nThis ancient Tome gives the ability to write and create a new spell");
                    System.out.println("\nWould you like to buy for 500 G (You can discard the spell if you do not want it, but you will lose the Gold)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    }
                    else if(buyChoice == 1 && !buy(500)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold and assign a double value between 0 and 1 to a variable
                    gold -= 500;
                    
                    makeSpell = makeSpellC();
                    
                    System.out.println("With your magnicificent wizardry, you have created " + makeSpell.getName());
                    System.out.println("\n" + makeSpell.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to keep? (If you keep you will lose one of your current spells)");
                    System.out.println("\n1. Keep");
                    System.out.println("\n2. Discard\n");
                    keepChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //If the user wishes to discard the spell, break the case
                    if(keepChoice == 2){
                        System.out.println("You choose to discard " + makeSpell.getName() + "!");
                        System.out.println("\nYou now have " + gold + " G left");
                        Commands.pressEnter();
                        break;
                    } 
                    
                    //Ask which spell the user wishes to replace with the new spell
                    System.out.println("\nWhich spell slot would you like to use for " + makeSpell.getName() + "?");
                    System.out.println("\nSlot 1: " + spell1.getName() + " " + spell1.getElement());
                    System.out.println("\nSlot 2: " + spell2.getName() + " " + spell2.getElement());
                    System.out.println("\nSlot 3: " + spell3.getName() + " " + spell3.getElement());
                    System.out.println("\nSlot 4: " + spell4.getName() + " " + spell4.getElement());
                    System.out.println("\nSlot 5: " + spell5.getName() + " " + spell5.getElement() + "\n");
                    spellChoice = Commands.inputInt(1, 5);
                    Commands.clear();
                    
                    //Switch that replaces a spell slot with the new spell according to the user's choice
                    switch(spellChoice){
                        case 1:{
                            System.out.println("You have replaced " + spell1.getName() + " with " + makeSpell.getName());
                            spell1 = makeSpell;
                            break;
                        }
                        case 2:{
                            System.out.println("You have replaced " + spell2.getName() + " with " + makeSpell.getName());
                            spell2 = makeSpell;
                            break;
                        }
                        case 3:{
                            System.out.println("You have replaced " + spell3.getName() + " with " + makeSpell.getName());
                            spell3 = makeSpell;
                            break;
                        }
                        case 4:{
                            System.out.println("You have replaced " + spell4.getName() + " with " + makeSpell.getName());
                            spell4 = makeSpell;
                            break;
                        }
                        case 5:{
                            System.out.println("You have replaced " + spell5.getName() + " with " + makeSpell.getName());
                            spell5 = makeSpell;
                            break;
                        }
                    }
                    
                    System.out.println("\nYou now have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 9:{
                    //Case 8 is for exitting the shop, this just breaks the switch and ends the while loop
                    break;
                }
            }
        }
    }
    
    
    /**************************
     * browse
     * This method lets the user browse all items available for sale
     **************************/
    public static void browse(){
        int menuChoice = 0;
        int itemChoice;
        int buyChoice;
        int itemCost;
        Sword viewSword;
        Sword activeSword;
        Staff viewStaff;
        Staff activeStaff;
        Armor viewArmor;
        Armor activeArmor;
        
        while(menuChoice != 4){
            System.out.println("Item Browsing");
            System.out.println("\n********************************************************************************");
            System.out.println("\nWhat would you like to browse?");
            System.out.println("\n1. Swords");
            System.out.println("\n2. Staffs");
            System.out.println("\n3. Armors");
            System.out.println("\n4. Exit\n");
            menuChoice = Commands.inputInt(1, 4);
            Commands.clear();
            
            switch(menuChoice){
                case 1:{
                    //Case 1 for swords
                    
                    //Printing all available swords and asking which sword the user wishes to view
                    System.out.println("Which sword would you like to view? (Enter 0 to go back)");
                    
                    for(int i = 0; i < Data.swords.length; i++){
                        viewSword = Data.swords[i];
                        System.out.println("\n" + (i+1) + ". " + viewSword.getName() + " (" + viewSword.getCost() + " G)");
                    }
                    
                    System.out.println("");
                    itemChoice = Commands.inputInt(0, Data.swords.length) - 1;
                    Commands.clear();
                    
                    if(itemChoice == -1){
                        break;
                    }
                    
                    activeSword = Data.swords[itemChoice];
                    itemCost = activeSword.getCost();
                    
                    System.out.println(activeSword.getName());
                    System.out.println("\n" + activeSword.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Sword");
                    System.out.println("\n" + sword.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current sword, and if you have"
                            + "\nthe same sword you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the sword to the new sword
                    gold -= itemCost;
                    sword = activeSword;
                    
                    System.out.println("You buy the " + sword.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 2:{
                    //Case 2 is for staffs
                    
                    //Printing all available staffs and asking which staff the user wishes to view
                    System.out.println("Which staff would you like to view? (Enter 0 to go back)");
                    
                    for(int i = 0; i < Data.staffs.length; i++){
                        viewStaff = Data.staffs[i];
                        System.out.println("\n" + (i+1) + ". " + viewStaff.getName() + " (" + viewStaff.getCost() + " G)");
                    }
                    
                    System.out.println("");
                    itemChoice = Commands.inputInt(0, Data.staffs.length) - 1;
                    Commands.clear();
                    
                    if(itemChoice == -1){
                        break;
                    }
                    
                    activeStaff = Data.staffs[itemChoice];
                    itemCost = activeStaff.getCost();
                    
                    System.out.println(activeStaff.getName());
                    System.out.println("\n" + activeStaff.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Staff");
                    System.out.println("\n" + staff.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current sword, and if you have"
                            + "\nthe same sword you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the staff to the new staff
                    gold -= itemCost;
                    staff = activeStaff;
                    
                    System.out.println("You buy the " + staff.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 3:{
                    //Case 3 is for armors
                    
                    //Printing all available armors and asking which armor the user wishes to view
                    System.out.println("Which armor would you like to view? (Enter 0 to go back)");
                    
                    for(int i = 0; i < Data.armors.length; i++){
                        viewArmor = Data.armors[i];
                        System.out.println("\n" + (i+1) + ". " + viewArmor.getName() + " (" + viewArmor.getCost() + " G)");
                    }
                    
                    System.out.println("");
                    itemChoice = Commands.inputInt(0, Data.armors.length) - 1;
                    Commands.clear();
                    
                    if(itemChoice == -1){
                        break;
                    }
                    
                    activeArmor = Data.armors[itemChoice];
                    itemCost = activeArmor.getCost();
                    
                    System.out.println(activeArmor.getName());
                    System.out.println("\n" + activeArmor.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nYour current Armor");
                    System.out.println("\n" + armor.toString());
                    System.out.println("\n********************************************************************************");
                    System.out.println("\nWould you like to buy? (If you buy you will lose your current sword, and if you have"
                            + "\nthe same sword you will gain nothing)");
                    System.out.println("\n1. Buy");
                    System.out.println("\n2. Back\n");
                    buyChoice = Commands.inputInt(1, 2);
                    Commands.clear();
                    
                    //Break the case if the user wishes to not buy or the user does not have enough gold
                    if(buyChoice == 2){
                        break;
                    } 
                    else if (buyChoice == 1 && !buy(itemCost)){
                        System.out.println("You do not have enough gold!");
                        Commands.pressEnter();
                        break;
                    }
                    
                    //Subtract gold from the user and set the armor to the new armor
                    gold -= itemCost;
                    armor = activeArmor;
                    
                    System.out.println("You buy the " + armor.getName() + " for " + itemCost + " G");
                    System.out.println("\nNow you have " + gold + " G left");
                    Commands.pressEnter();
                    
                    break;
                }
                case 4:{
                    //Case 4 just breaks and ends the while loop of the method
                    break;
                }
            }
        }
    }
    
    /*******************************
     * makeSpellC
     * This method allows the user to create a new spell
     * @return - The custom spell
     *******************************/
    public static Spell makeSpellC(){
        Spell makeSpell;
        String name;
        String element = null;
        int damageConstant;
        int damageCoefficient;
        int totalDamage;
        double dodge;
        int choice;
        
        System.out.println("Please choose an element for your spell:");
        System.out.println("\n1. [Fire]\n\n2. [Earth]\n\n3. [Water]\n\n4. [Dark]\n\n5. [Light]\n");
        choice = Commands.inputInt(1, 5);
        
        Commands.clear();
        
        switch(choice){
            case 1:{
                element = "[Fire]";
                break;
            }
            case 2:{
                element = "[Earth]";
                break;
            }
            case 3:{
                element = "[Water]";
                break;
            }
            case 4:{
                element = "[Dark]";
                break;
            }
            case 5:{
                element = "[Light]";
                break;
            }
        }
        
        System.out.print("Please enter a name for your spell: ");
        name = Commands.inputString();
        
        Commands.clear();
        
        dodge = ((int) (Commands.getRandomDouble(0, 0.40) * 100)) / 100.0;
        
        if(dodge < 0.1){
            totalDamage = 5 * ((int) (Commands.getRandomDouble(20, 70)));
            damageCoefficient = (int) (Commands.getRandomDouble(5, 18));
        }
        else if(dodge < 0.2){
            totalDamage = 5 * ((int) (Commands.getRandomDouble(70, 85)));
            damageCoefficient = (int) (Commands.getRandomDouble(5, 30));
        }
        else{
            totalDamage = 5 * ((int) (Commands.getRandomDouble(85, 100)));
            damageCoefficient = (int) (Commands.getRandomDouble(5, 40));
        }
        
        damageConstant = totalDamage - (10 * damageCoefficient);
        
        makeSpell = new Spell(name, damageConstant, damageCoefficient, dodge, element);
        
        return makeSpell;
    }
    
    
    /**********************************
     * buy
     * This method checks if the user is able to buy an item 
     * @param cost - The cost of the item
     * @return - True if the user can buy, false if the user cannot buy
     **********************************/
    public static boolean buy(int cost){
        if(cost <= gold){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    /***************************
     * library
     * This method acts as a library in the village
     ***************************/
    public static void library(){
        //Declare necessary variables
        int libraryChoice = 0;
        int infoChoice;
        
        //While loop that ends when the user wishes to exit the library
        while(libraryChoice != 4){
            //Print the banner and menu and ask for the user's choice
            System.out.println("********************************************************************************");
            System.out.println("*                                 The  Library                                 *");
            System.out.println("*****                                                                      *****");
            System.out.println("\nWhat do you wish to study?");
            System.out.println("\n1. Ancient Book of Magic (Tutorial)");
            System.out.println("\n2. Books of Knowledge (Spells)");
            System.out.println("\n3. Bestiary (Enemies)");
            System.out.println("\n4. Exit library\n");
            libraryChoice = Commands.inputInt(1, 4);
            Commands.clear();
            
            //Switch for the user's choice
            switch(libraryChoice){
                case 1:{
                    //Case 1 prints a tutorial for the user
                    System.out.println("TUTORIAL");
                    System.out.println("\nWhen you embark on an adventure you will face monsters and enemies of a difficulty corresponding"
                            + "\nto the adventure area chosen. Every time you defeat an enemy, you can either return to the village or "
                            + "\ncontinue the adventure, keep in mind your health does not replenish during an adventure unless "
                            + "\nyou drink a potion. In a battle, you have three options you can choose from during your turn:");
                    System.out.println("\n1. Attack: you attack with your sword, this attack does a set amount of damage and is not dodgable, "
                            + "\nhowever, the damage is relatively low.");
                    System.out.println("\n2. Cast a Spell: you cast one of the five spells you have memorized to deal damage, the damage of a "
                            + "\nspell is highly random, but there is a minimum and maximum damage, and every spell can be dodged."
                            + "\nHowever, every spell has an element that the enemy may have a weakness against.");
                    System.out.println("\n3. Drink a Potion: you drink a health potion to replenish some of your health, the amount replenished "
                            + "\nis always half of your maximum health.");
                    System.out.println("\nEvery enemy has a weakness and a resistance to one of the elements. A weakness makes the spell "
                            + "\ndamage double, while a resistance makes it half. But an attack by your sword has no such modifiers.");
                    System.out.println("\nWhen your turn ends, the enemy attacks you with a random damage that has a minimum and a maximum.");
                    System.out.println("\nEverytime you defeat an enemy you level up, gain 200 extra max health, and gain a random amount of Gold"
                            + "\nbased on the difficulty of the enemy. Gold can be used in the village shop to buy new items or spells.");
                    Commands.pressEnter();
                    
                    break;
                }
                case 2:{
                    //Case 2 prints all the spells and asks which spell the user wishes to view, then shows the details of that spell
                    System.out.println("Which spell would you like to view:\n");
                    for(int i = 0; i < Data.spells.length; i++){
                        System.out.println((i+1) + ". " + Data.spells[i].getName() + "\n");
                    }
                    infoChoice = Commands.inputInt(1, Data.spells.length) - 1;
                    Commands.clear();
                    
                    System.out.println("Spell info:");
                    System.out.println("\n" + Data.spells[infoChoice].toString());
                    Commands.pressEnter();
                    
                    break;
                }
                case 3:{
                    //Case 3 prints all the enemies and asks which enemy the user wishes to view, then shows the details of that enemy
                    System.out.println("Which enemy would you like to view:\n");
                    for(int i = 0; i < Data.enemies.length; i++){
                        System.out.println((i+1) + ". " + Data.enemies[i].getName() + "\n");
                    }
                    infoChoice = Commands.inputInt(1, Data.enemies.length) - 1;
                    Commands.clear();
                    
                    System.out.println("Enemy info:");
                    System.out.println("\n" + Data.enemies[infoChoice].toString());
                    Commands.pressEnter();
                    
                    break;
                }
                case 4:{
                    //Case 4 just exits
                    break;
                }
            }
        }
    }
    
    
    /***********************
     * inn
     * This method acts as a hub that the user can view their information and inventory from
     ***********************/
    public static void inn(){
        //Declare necessary variables
        int choice = 0;
        
        while(choice != 4){
            //Print the banner and menu and ask for the user's choice
            System.out.println("********************************************************************************");
            System.out.println("*                                   The  Inn                                   *");
            System.out.println("*****                                                                      *****");

            System.out.println("\nWhat would you like to do?");
            System.out.println("\n1. View Player info");
            System.out.println("\n2. View Inventory");
            System.out.println("\n3. View Memorized Spells");
            System.out.println("\n4. Exit the Inn\n");
            choice = Commands.inputInt(1, 4);
            Commands.clear();
            
            //Switch for the user's choice
            switch(choice){
                case 1:{
                    //Case 1 prints the player's information
                    System.out.println("Player Name: " + playName);
                    System.out.println("\nLevel: " + playLevel);
                    System.out.println("\nMaximum Health: " + playMaximumHealth);
                    System.out.println("\nBalance: " + gold + " G");
                    System.out.println("\nTotal Gold Gained: " + totalGainedGold + " G");
                    Commands.pressEnter();
                    break;
                }
                case 2:{
                    //Case 2 prints the user's inventory
                    System.out.println("Inventory: ");
                    System.out.println("\nSword: " + sword.getName() + " (Damage: " + sword.getDamage() + ")");
                    System.out.println("\nStaff: " + staff.getName() + " (Damage Multiplier: " + staff.getDamageMultiplier() + ")");
                    System.out.println("\nArmor: " + armor.getName() + " (Armor: " + armor.getArmor() + ")");
                    Commands.pressEnter();
                    break;
                }
                case 3:{
                    //Case 3 prints the user's spells
                    System.out.println("Memorized Spells: ");
                    System.out.println("\nSlot 1: " + spell1.getName() + " " + spell1.getElement());
                    System.out.println("\nSlot 2: " + spell2.getName() + " " + spell2.getElement());
                    System.out.println("\nSlot 3: " + spell3.getName() + " " + spell3.getElement());
                    System.out.println("\nSlot 4: " + spell4.getName() + " " + spell4.getElement());
                    System.out.println("\nSlot 5: " + spell5.getName() + " " + spell5.getElement());
                    Commands.pressEnter();
                    break;
                }
                case 4:{
                    break;
                }
            }
        }
    }
    
    
    /************************
     * exit
     * This method acts as a way for the user to exit the program
     ************************/
    public static void exit(){
        //Declare snecessary variables
        int saveChoice;
        
        //Ask the user to make sure if the player wants to save and exit or not
        System.out.println("Are you sure you want to save and exit?");
        System.out.println("\nEverything will be saved except for the list of monsters defeated and trophies");
        System.out.println("\n1. Save and exit");
        System.out.println("\n2. Return to main menu\n");
        saveChoice = Commands.inputInt(1, 2);
        Commands.clear();
        
        switch(saveChoice){
            case 1:{
                if(Savegames.save() == -1){
                    return;
                }
                
                System.out.println("Your data has been saved, you can load it the next time you play");
                System.out.println("\nThank you for playing Wizard Armageddon!");
                Commands.pressEnter();
                
                System.exit(0);
            }
            case 2:{
                return;
            }
        }
    }
}
 