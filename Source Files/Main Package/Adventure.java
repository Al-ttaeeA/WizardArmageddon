/**************************************
 * Project:         WizardArmageddon
 * Program:         Adventure.java
 **************************************/
package wizardarmageddon;

public class Adventure {
    //These variables are all common for both methods in this class
    private static double diffModifier;
    private static double goldModifier = 1;
    private static double luckModifier;
    
    //Variables that start with "enem" are enemy variables
    private static Enemy enemActive;
    private static int enemRemainingHealth;
    private static boolean enemIsDead;
    
    //Varibales that start with "play" are player variables
    private static int playRemainingHealth;
    private static int playPotions;
    
    public static void Adventure(){
        //Setting up all the variables
        int diffChoice;
        int stayChoice = 1;
        int battleChoice;
        boolean continueStatus;
        
        diffModifier = 1;
        int gainedGold;
        
        playRemainingHealth = Main.playMaximumHealth;
        playPotions = 3;
        int potionHealth;
        int spellDamage = 0;
        int spellCooldown1 = 0;
        int spellCooldown2 = 0;
        int spellChoice;
        Spell activeSpell = new Spell();
        
        int enemDamage;
        int goldBag;
        int keepChoice;
        
        boolean wizardFight = false;
        
        //Print the banner and ask where the user wants to go on an adventure
        System.out.println("********************************************************************************");
        System.out.println("*                               A D V E N T U R E                              *");
        System.out.println("*****                                                                      *****");
        
        System.out.println("\nWhere would you like to go on an adventure?");
        System.out.println("\n1. The Misty Forest of Dul Balgur (Rec. Level 1-5)");
        System.out.println("\n2. The Rocky Plateau of Del Agarth (Rec. Level 6-10)");
        System.out.println("\n3. The Volcanic Wasteland of Tirad Mirin (Rec. Level 11-15)");
        System.out.println("\n4. The Snowy Hills of Gratha Mirin (Rec. Level 16-20)");
        System.out.println("\n5. The Cursed Badlands of the Ancient Kingdom (Rec. Level 21-25)");
        System.out.println("\n6. The Hell Dimension (Extremely risky, unknown recommended magic level)");
        System.out.println("\n7. The Lost Dungeons of The Eternal Kingdom of Voladia (Rec. Any Level)");
        System.out.println("\n8. Cancel adventure\n");
        diffChoice = Commands.inputInt(1,8);
        Commands.clear();
        
        //If the user chooses to cancel adventure
        if(diffChoice == 8){
            //This sets the village choice back to 0, at this point it would have been 5 so it would have updated the shop
            //However, if the user cancels adventure, this line prevents the shop from updating
            Main.villageChoice = 0;
            
            return;
        }
        
        //Print a different message for each choice
        switch(diffChoice){
            case 1:{
                System.out.println("You start a new adventure in The Misty Forest of Dul Balgur");
                break;
            }
            case 2:{
                System.out.println("You start a new adventure in The Rocky Plateau of Del Agarth");
                break;
            }
            case 3:{
                System.out.println("You start a new adventure in The Volcanic Wasteland of Tirad Mirin");
                break;
            }
            case 4:{
                System.out.println("You start a new adventure in The Snowy Hills of Gratha Mirin");
                break;
            }
            case 5:{
                System.out.println("You start a new adventure in The Cursed Badlands of the Ancient Kingdom");
                break;
            }
            case 6:{
                System.out.println("You start a new adventure in The Hell Dimension, Trust no one but your spells!");
                break;
            }
            case 7:{
                System.out.println("You start a new adventure in The Lost Dungeons of The Eternal Kingdom of Voladia");
                break;
            }
        }
        Commands.pressEnter();
        
        //Do while loop that ends if the user wishes to end the adventure
        do{
            //Switch to setup a difficulty and gold modifier according to the location the user is going for an adventure
            switch(diffChoice){
                case 1:{
                    diffModifier = Commands.getRandomDouble(1, 1.5);
                    goldModifier = Commands.getRandomDouble(1.5, 2.5);
                    luckModifier = 0.2;
                    break;
                }
                case 2:{
                    diffModifier = Commands.getRandomDouble(1.5, 2.5);
                    goldModifier = Commands.getRandomDouble(2.5, 4);
                    luckModifier = 0.22;
                    break;
                }
                case 3:{
                    diffModifier = Commands.getRandomDouble(2.5, 4);
                    goldModifier = Commands.getRandomDouble(4, 6);
                    luckModifier = 0.25;
                    break;
                }
                case 4:{
                    diffModifier = Commands.getRandomDouble(4, 6);
                    goldModifier = Commands.getRandomDouble(6, 9);
                    luckModifier = 0.28;
                    break;
                }
                case 5:{
                    diffModifier = Commands.getRandomDouble(6, 9);
                    goldModifier = Commands.getRandomDouble(9, 13);
                    luckModifier = 0.32;
                    break;
                }
                case 6:{
                    diffModifier = Commands.getRandomDouble(1, 20);
                    goldModifier = diffModifier * Commands.getRandomDouble(1, 3);
                    
                    if(diffModifier < 5){
                        luckModifier = 0.37;
                    }
                    else if(diffModifier < 10){
                        luckModifier = 0.43;
                    }
                    else if(diffModifier < 15){
                        luckModifier = 0.50;
                    }
                    else{
                        luckModifier = 0.60;
                    }
                    
                    break;
                }
                case 7:{
                    diffModifier = Math.pow(1.12, Main.playLevel);
                    goldModifier = Math.pow(1.14, Main.playLevel);
                    break;
                }
            }
            
            enemIsDead = false;
            
            //If statement to see if a wizard fight is going to happen
            if(Commands.getRandomDouble(0, 1) < 0.2){
                wizardFight = true;
            } else {
                //Set up a random enemy and their health
                enemActive = Data.enemies[Commands.getRandom(Data.enemies.length - 1) - 1];
                
                enemRemainingHealth = (int) (enemActive.getHealth() * diffModifier);
                
                System.out.println("On your adventure you encounter a " + enemActive.getName() + "!");
                System.out.println("\nTHE BATTLE BEGINS!!!");
                Commands.pressEnter();
                
                wizardFight = false;
            }
            
            //While loop that ends when the enemy is defeated
            while(!enemIsDead){
                
                //If its a wizard fight, skip the entire fight procedure
                if(wizardFight){
                    wizard();
                    continue;
                }
                
                do{ //Do while for the player's turn, ends when the user has made a valid action
                    continueStatus = false;
                    spellChoice = 0; //This is for the cooldown mechanism
                    
                    battleBanner();
                    
                    //Ask the user what they want to do
                    System.out.println("\nIt's your turn, what would you like to do?");
                    System.out.println("\n1. Attack");
                    System.out.println("\n2. Cast a Spell");
                    System.out.println("\n3. Drink a Potion\n");
                    battleChoice = Commands.inputInt(1, 3);
                    Commands.clear();
                    
                    //Switch for each choice
                    switch(battleChoice){
                        case 1:{
                            //Case 1 normal attack with a sword
                            
                            //Subtract enemy health according to the sword's damage
                            enemRemainingHealth -= Main.sword.getDamage();
                            
                            System.out.println("You choose to attack the " + enemActive.getName() + " with your " + Main.sword.getName() + "!");
                            System.out.println("\nYour attack deals " + Main.sword.getDamage() + " damage!");
                            
                            //If the enemy's health become 0 or less, indicate that the enemy has died
                            if(enemRemainingHealth <= 0){
                                System.out.println("\nThis attack kills the " + enemActive.getName() + "!!!");
                                Commands.pressEnter();
                                enemIsDead = true;
                            } else { //Else print the enemy's remaining health
                                System.out.println("\nThe " + enemActive.getName() + " now has " + enemRemainingHealth + " HP remaining!");
                                Commands.pressEnter();
                            } 
                            
                            //This means the user has made a valid action
                            continueStatus = true;
                            break; 
                        }
                        case 2:{
                            //Case 2 for casting spells
                            
                            //Print all the player's spells and ask which spell the user wishes to use
                            System.out.println("Here are your memorized spells, which spell would you like to cast? (Enter 6 to cancel spell)");
                            System.out.println("\nSlot 1: " + Main.spell1.getName() + " " + Main.spell1.getElement());
                            System.out.println("\nSlot 2: " + Main.spell2.getName() + " " + Main.spell2.getElement());
                            System.out.println("\nSlot 3: " + Main.spell3.getName() + " " + Main.spell3.getElement());
                            System.out.println("\nSlot 4: " + Main.spell4.getName() + " " + Main.spell4.getElement());
                            System.out.println("\nSlot 5: " + Main.spell5.getName() + " " + Main.spell5.getElement() + "\n");
                            spellChoice = Commands.inputInt(1, 6);
                            Commands.clear();
                            
                            //If the user wishes to cancel the spell casting, break the switch
                            if(spellChoice == 6){
                                spellChoice = -1;
                                break;
                            } else if (spellChoice == spellCooldown1 || spellChoice == spellCooldown2){
                                //If the spell is on cooldown, indicate this
                                System.out.println("This spell is on cooldown!");
                                spellChoice = -1; //This is for the cooldown mechanism
                                Commands.pressEnter();
                                break;
                            }
                            
                            continueStatus = true;
                            
                            //Switch that sets the active spell to the spell the user chose
                            switch(spellChoice){
                                case 1:{
                                    activeSpell = Main.spell1;
                                    break;
                                }
                                case 2:{
                                    activeSpell = Main.spell2;
                                    break;
                                }
                                case 3:{
                                    activeSpell = Main.spell3;
                                    break;
                                }
                                case 4:{
                                    activeSpell = Main.spell4;
                                    break;
                                }
                                case 5:{
                                    activeSpell = Main.spell5;
                                    break;
                                }
                            }
                            
                            System.out.println("You cast " + activeSpell.getName() + "!!!");
                            Commands.pressEnter();
                            
                            //Check to see if the enemy has dodged the spell
                            if(Commands.getRandomDouble(0, 1) < activeSpell.getDodge()){
                                System.out.println("Unfortunately, the " + enemActive.getName() + " dodges your " + activeSpell.getName() + ", therefore dealing 0 damage");
                                Commands.pressEnter(); //This counts as a valid action even if the enemy dodges the spell
                                break;
                            } else if (activeSpell.getName().equals("Nothing (literally does nothing)") && Commands.getRandomDouble(0, 1) < 0.1){
                                //Check to see if the spell is Nothing and there is a 10% chance it instantly kills the enemy
                                //This is an easter egg in the game
                                System.out.println("Your cursed spell of nothingness somehow deals 1 Billion Damage and kills the " + enemActive.getName() + "!!!");
                                Commands.pressEnter();
                                enemIsDead = true;
                                break;
                            }
                            
                            //Set a spell damage to print it
                            spellDamage = activeSpell.getDamage(Main.staff.getDamageMultiplier());

                            System.out.println("Your " + activeSpell.getName() + " deals " + spellDamage + " damage with your staff modifier!");
                            
                            //Check if the enemy is weak or resistant to the spell and print an applicable message
                            if(activeSpell.getElement().equals(enemActive.getWeakness())){
                                spellDamage = spellDamage * 2;
                                System.out.println("\nSince the " + enemActive.getName() + " is weak to " + activeSpell.getElement() + ", the damage doubles for a total of " 
                                        + spellDamage + " damage!");
                            } else if (activeSpell.getElement().equals(enemActive.getResistance())){
                                spellDamage = spellDamage / 2;
                                System.out.println("\nSince the " + enemActive.getName() + " is resistant to " + activeSpell.getElement() + ", the damage halves for a total of " 
                                        + spellDamage + " damage!");
                            }
                            
                            //Subtract the enemy health according to the spell damage
                            enemRemainingHealth -= spellDamage;
                            
                            //If the enemy's health become 0 or less, indicate that the enemy has died
                            if(enemRemainingHealth <= 0){
                                System.out.println("\nThis spell kills the " + enemActive.getName() + "!!!");
                                Commands.pressEnter();
                                enemIsDead = true;
                            } else { //Else print the enemy's remaining health
                                System.out.println("\nThe " + enemActive.getName() + " now has " + enemRemainingHealth + " HP remaining!");
                                Commands.pressEnter();
                            } 
                            
                            break;
                        }
                        case 3:{
                            //Case 3 is for drinking a potion
                            
                            //Check if the player has any potions left
                            if(playPotions == 0){
                                System.out.println("Unfortunately you have no potions left");
                                spellChoice = -1;
                                Commands.pressEnter();
                                break;
                            }
                            
                            continueStatus = true;
                            playPotions--;
                            
                            //Check if the player's health is at maximum and print a message stating that the user drank a potion for nothing
                            if(playRemainingHealth == Main.playMaximumHealth){
                                System.out.println("You drink a potion for nothing, you just wasted a precious potion!");
                            } else if(playRemainingHealth > (Main.playMaximumHealth / 2)){
                                //If the player's health is more than their maximum health's half, then they heal until max health
                                potionHealth = Main.playMaximumHealth - playRemainingHealth;
                                playRemainingHealth = Main.playMaximumHealth;
                                
                                System.out.println("You drink a potion for " + potionHealth + " HP, therefore reaching your maximum health of " + playRemainingHealth + " HP!");
                            } else {
                                //Heal for half their maximum health
                                potionHealth = Main.playMaximumHealth / 2;
                                playRemainingHealth += potionHealth;
                                
                                System.out.println("You drink a potion for " + potionHealth + " HP, now you have " + playRemainingHealth + " HP remaining!");
                            }
                            
                            System.out.println("\nYou now have " + playPotions + " potions left");
                            Commands.pressEnter();
                            
                            break;
                        }
                        
                    }
                    
                    //If the spell choice is -1 then skip the cooldown because that means the user did not do a valid action
                    if(spellChoice != -1){
                        spellCooldown2 = spellCooldown1;
                        spellCooldown1 = spellChoice;
                    }
                    
                }while(!continueStatus);
                
                //If the enemy is dead
                if(enemIsDead){
                    //Increase the user's level and max health, and increase their gold
                    Main.playLevel++;
                    
                    if(Main.playMaximumHealth < 10000){
                        Main.playMaximumHealth += 200;
                    }
                    
                    Townhall.addEnemy(enemActive.getName());
                    gainedGold = (int) (50 * goldModifier);
                    Main.gold += gainedGold;
                    Main.totalGainedGold += gainedGold;
                    
                    //Print that the enemy has been defeated and the user's gains from that fight, then ask if the user wishes to contine their adventure
                    System.out.println("The " + enemActive.getName() + " has been defeated successfully!");
                    System.out.println("\nAfter your amazing victory, you level up to level " + Main.playLevel + "!");
                    System.out.println("\nYour maximum health is now " + Main.playMaximumHealth + " HP!");
                    System.out.println("\nYou have also gained " + gainedGold + " G from that battle, you now have " + Main.gold + " G!");
                    Commands.pressEnter();
                    
                    //If statement with nested if sequence for item drops
                    if(Commands.getRandomDouble(0, 1) < luckModifier){
                        if(enemActive.getItemType() == null){ //If the item drop is nothing
                            goldBag = (int) ((50 + Commands.getRandom(100)) * goldModifier);
                            
                            Main.gold += goldBag;
                            
                            System.out.println("The " + enemActive.getName() + " have also dropped a bag of Gold!");
                            System.out.println("\nThe bag of Gold has " + goldBag + " G, you now have " + Main.gold + " G!");
                            Commands.pressEnter();
                        }
                        else if(enemActive.getItemType().equals("Sword")){ //If the item drop is a sword
                            System.out.println("The " + enemActive.getName() + " have also dropped a(n) " + enemActive.getSword().getName() + "!");
                            System.out.println("\n" + enemActive.getSword().toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nYour current Sword");
                            System.out.println("\n" + Main.sword.toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nWould you like to keep? (If you keep you will lose your current sword)");
                            System.out.println("\n1. Keep");
                            System.out.println("\n2. Sell for half the cost\n");
                            keepChoice = Commands.inputInt(1, 2);
                            Commands.clear();
                            
                            //If the user wishes to sell
                            if(keepChoice == 2){
                                Main.gold += enemActive.getSword().getCost() / 2;
                                
                                System.out.println("You choose to sell the " + enemActive.getSword().getName() + " for " + (enemActive.getSword().getCost() / 2) + " G!");
                                System.out.println("\nYou now have " + Main.gold + " G!");
                                Commands.pressEnter();
                            } else {
                                //Set the current sword to the dropped sword
                                Main.sword = enemActive.getSword();
                                
                                System.out.println("You choose to keep the " + enemActive.getSword().getName());
                                Commands.pressEnter();
                            }
                        }
                        else if(enemActive.getItemType().equals("Staff")){ //If the item drop is a staff
                            System.out.println("The " + enemActive.getName() + " have also dropped a(n) " + enemActive.getStaff().getName() + "!");
                            System.out.println("\n" + enemActive.getStaff().toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nYour current Staff");
                            System.out.println("\n" + Main.staff.toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nWould you like to keep? (If you keep you will lose your current staff)");
                            System.out.println("\n1. Keep");
                            System.out.println("\n2. Sell for half the cost\n");
                            keepChoice = Commands.inputInt(1, 2);
                            Commands.clear();
                            
                            //If the user wishes to sell
                            if(keepChoice == 2){
                                Main.gold += enemActive.getStaff().getCost() / 2;
                                
                                System.out.println("You choose to sell the " + enemActive.getStaff().getName() + " for " + (enemActive.getStaff().getCost() / 2) + " G!");
                                System.out.println("\nYou now have " + Main.gold + " G!");
                                Commands.pressEnter();
                            } else {
                                //Set the current staff to the dropped staff
                                Main.staff = enemActive.getStaff();
                                
                                System.out.println("You choose to keep the " + enemActive.getStaff().getName());
                                Commands.pressEnter();
                            }
                        }
                        else if(enemActive.getItemType().equals("Armor")){ //If the item drop is a armor
                            System.out.println("The " + enemActive.getName() + " have also dropped a(n) " + enemActive.getArmor().getName() + "!");
                            System.out.println("\n" + enemActive.getArmor().toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nYour current Armor");
                            System.out.println("\n" + Main.armor.toString());
                            System.out.println("\n********************************************************************************");
                            System.out.println("\nWould you like to keep? (If you keep you will lose your current armor)");
                            System.out.println("\n1. Keep");
                            System.out.println("\n2. Sell for half the cost\n");
                            keepChoice = Commands.inputInt(1, 2);
                            Commands.clear();
                            
                            //If the user wishes to sell
                            if(keepChoice == 2){
                                Main.gold += enemActive.getArmor().getCost() / 2;
                                
                                System.out.println("You choose to sell the " + enemActive.getArmor().getName() + " for " + (enemActive.getArmor().getCost() / 2) + " G!");
                                System.out.println("\nYou now have " + Main.gold + " G!");
                                Commands.pressEnter();
                            } else {
                                //Set the current armor to the dropped armor
                                Main.armor = enemActive.getArmor();
                                
                                System.out.println("You choose to keep the " + enemActive.getArmor().getName());
                                Commands.pressEnter();
                            }
                        }
                    }
                    
                    continue; //This is to skip the enemy's turn
                }
                
                //Print that it is the enemy's turn now
                battleBanner();
                System.out.println("\nThe " + enemActive.getName() + "'s turn now!");
                Commands.pressEnter();
                
                //Set up enemy's damage
                enemDamage = enemActive.getDamage(diffModifier);
                
                System.out.println("\nThe " + enemActive.getName() + " attacks you for " + enemDamage + " damage!");
                
                //20% chance the enemy's hit lands critical for 1.8x the damage
                if(Commands.getRandomDouble(0, 1) < 0.2){
                    enemDamage = (int) (enemDamage * 1.8);
                    System.out.println("\nThe " + enemActive.getName() + "'s attack lands critically for a total of " + enemDamage + " damage!");
                }
                
                //Decrease armor from the enemy's damage
                enemDamage -= Main.armor.getArmor();
                
                //If the enemy damage becomes negative because of the armor, make it 0
                if(enemDamage <= 0){
                    enemDamage = 0;
                }
                
                System.out.println("\nWith your " + Main.armor.getName() + ", the damage is decreased to " + enemDamage + " damage!");
                
                //Decrease the player's health
                playRemainingHealth -= enemDamage;
                
                //If the player's health become 0 or less, indicate that the player has died
                if(playRemainingHealth <= 0){
                    System.out.println("\nThis attack kills you!");
                    Commands.pressEnter();
                    playerDeath();
                }
                else{ //If not, print the player's remaining health
                    System.out.println("\nThis attack leaves you with " + playRemainingHealth + " HP remaining!");
                    Commands.pressEnter();
                }
            }
            
            System.out.println("Would you like to continue your adventure or go back to The Village?");
            System.out.println("\n1. Continue");
            System.out.println("\n2. Go back to the village\n");
            stayChoice = Commands.inputInt(1, 2);
            Commands.clear();
            
            //If the user wishes to stay then print a message according to the location they picked
            if(stayChoice == 1){
                switch(diffChoice){
                    case 1:{
                        System.out.println("You continue your adventure in The Misty Forest of Dul Balgur");
                        break;
                    }
                    case 2:{
                        System.out.println("You continue your adventure in The Rocky Plateau of Del Agarth");
                        break;
                    }
                    case 3:{
                        System.out.println("You continue your adventure in The Volcanic Wasteland of Tirad Mirin");
                        break;
                    }
                    case 4:{
                        System.out.println("You continue your adventure in The Snowy Hills of Gratha Mirin");
                        break;
                    }
                    case 5:{
                        System.out.println("You continue your adventure in The Cursed Badlands of the Ancient Kingdom");
                        break;
                    }
                    case 6:{
                        System.out.println("You continue your adventure in The Hell Dimension, May your spells never fail you!");
                        break;
                    }
                    case 7:{
                        System.out.println("You continue your adventure in The Lost Dungeons of The Eternal Kingdom of Voladia");
                        break;
                    }
                }
                Commands.pressEnter();
            }
            
        }while(stayChoice == 1);
        
    }
    
    
    
    /***************************
     * wizard
     * This method runs when the player encounters a wizard fight
     ***************************/
    private static void wizard(){
        //Declare all wizard variables and items
        String wizName = Data.wizNames[Commands.getRandom(Data.wizNames.length - 1)] + " The " + Data.wizAdjectives[Commands.getRandom(Data.wizAdjectives.length - 1)];
        
        Sword wizSword = Data.swords[Commands.getRandom(Data.swords.length - 1)];
        Staff wizStaff = Data.staffs[Commands.getRandom(Data.staffs.length - 1)];
        Armor wizArmor = Data.armors[Commands.getRandom(Data.armors.length - 1)];
        
        Spell wizSpell1 = Data.spells[Commands.getRandom(Data.spells.length - 1)];
        Spell wizSpell2 = Data.spells[Commands.getRandom(Data.spells.length - 1)];
        Spell wizSpell3 = Data.spells[Commands.getRandom(Data.spells.length - 1)];
        
        if(diffModifier < 1.5){
            wizSword = Data.swords[Commands.getRandom(5)];
            wizStaff = Data.staffs[Commands.getRandom(5)];
            wizArmor = Data.armors[Commands.getRandom(5)];
        } else if (diffModifier < 2.5){
            wizSword = Data.swords[Commands.getRandom(10)];
            wizStaff = Data.staffs[Commands.getRandom(10)];
            wizArmor = Data.armors[Commands.getRandom(10)];
        } else if (diffModifier < 5){
            wizSword = Data.swords[(int) Commands.getRandomDouble(5, 15)];
            wizStaff = Data.staffs[(int) Commands.getRandomDouble(5, 15)];
            wizArmor = Data.armors[(int) Commands.getRandomDouble(5, 15)];
        } else if (diffModifier < 8){
            wizSword = Data.swords[(int) Commands.getRandomDouble(10, 20)];
            wizStaff = Data.staffs[(int) Commands.getRandomDouble(10, 20)];
            wizArmor = Data.armors[(int) Commands.getRandomDouble(10, 20)];
        } else {
            wizSword = Data.swords[(int) Commands.getRandomDouble(15, Data.swords.length - 1)];
            wizStaff = Data.staffs[(int) Commands.getRandomDouble(15, Data.swords.length - 1)];
            wizArmor = Data.armors[(int) Commands.getRandomDouble(15, Data.swords.length - 1)];
        }
        
        //Enemy wizard related variables
        int enemMaxHealth = (int) (1000 * diffModifier);
        
        if(enemMaxHealth > 10000){
            enemMaxHealth = 10000;
        }
        
        enemRemainingHealth = enemMaxHealth;
        int wizPotions = 3;
        int wizChoice;
        boolean attackIsSpell = false;
        
        //Other variables
        boolean continueStatus;
        int spellChoice;
        int battleChoice;
        int keepChoice;
        
        int spellCooldown1 = 0;
        int spellCooldown2 = 0;
        int spellDamage;
        int potionHealth;
        int swordDamage;
        
        int gainedGold;
        
        Spell activeSpell = new Spell();
        
        
        
        System.out.println("On you adventure you encounter a fellow Wizard, " + wizName + "!");
        System.out.println("\n" + wizName + " challenges you to a Magical Duel!");
        System.out.println("\nLET THE DUEL BEGIN!!!");
        Commands.pressEnter();
        
        while(!enemIsDead){
            do{ //Do while for the player's turn, ends when the user has made a valid action
                continueStatus = false;
                spellChoice = 0; //This is for the cooldown mechanism
                
                System.out.println("********************************************************************************\n*");
                System.out.println("* " + wizName + "\n*");
                System.out.println("* Remaining Health:     " + enemRemainingHealth + " HP\n*");
                System.out.println("* Sword Damage:         " + wizSword.getDamage() + "\n*");
                System.out.println("* Staff Spell Modifier: " + wizStaff.getDamageMultiplier() + "x\n*");
                System.out.println("* Armor:                " + wizArmor.getArmor()  + "\n*");
                System.out.println("* Potions Left:         " + wizPotions);
                System.out.println("\n********************************************************************************\n*");
                System.out.println("* " + Main.playName + "\n*");
                System.out.println("* Remaining Health:     " + playRemainingHealth + " HP\n*");
                System.out.println("* Sword Damage:         " + Main.sword.getDamage() + "\n*");
                System.out.println("* Staff Spell Modifier: " + Main.staff.getDamageMultiplier() + "x\n*");
                System.out.println("* Armor:                " + Main.armor.getArmor()  + "\n*");
                System.out.println("* Potions Left:         " + playPotions);
                System.out.println("*\n********************************************************************************");

                //Ask the user what they want to do
                System.out.println("\nIt's your turn, what would you like to do?");
                System.out.println("\n1. Attack");
                System.out.println("\n2. Cast a Spell");
                System.out.println("\n3. Drink a Potion\n");
                battleChoice = Commands.inputInt(1, 3);
                Commands.clear();

                //Switch for each choice
                switch(battleChoice){
                    case 1:{
                        //Case 1 normal attack with a sword
                        
                        swordDamage = Main.sword.getDamage() - wizArmor.getArmor();
                        
                        if(swordDamage <= 0){
                            swordDamage = 0;
                        }
                        
                        //Subtract enemy health according to the sword's damage
                        enemRemainingHealth -= swordDamage;
                        
                        System.out.println("You choose to attack the " + wizName + " with your " + Main.sword.getName() + "!");
                        System.out.println("\nWith their armor, your attack deals " + swordDamage + " damage!");
                        
                        //If the enemy's health become 0 or less, indicate that the enemy has died
                        if(enemRemainingHealth <= 0){
                            System.out.println("\nThis attack kills " + wizName + "!!!");
                            Commands.pressEnter();
                            enemIsDead = true;
                        } else { //Else print the enemy's remaining health
                            System.out.println("\n" + wizName + " now has " + enemRemainingHealth + " HP remaining!");
                            Commands.pressEnter();
                        } 
                        
                        //This means the user has made a valid action
                        continueStatus = true;
                        break; 
                    }
                    case 2:{
                        //Case 2 for casting spells
                        
                        //Print all the player's spells and ask which spell the user wishes to use
                        System.out.println("Here are your memorized spells, which spell would you like to cast? (Enter 6 to cancel spell)");
                        System.out.println("\nSlot 1: " + Main.spell1.getName() + " " + Main.spell1.getElement());
                        System.out.println("\nSlot 2: " + Main.spell2.getName() + " " + Main.spell2.getElement());
                        System.out.println("\nSlot 3: " + Main.spell3.getName() + " " + Main.spell3.getElement());
                        System.out.println("\nSlot 4: " + Main.spell4.getName() + " " + Main.spell4.getElement());
                        System.out.println("\nSlot 5: " + Main.spell5.getName() + " " + Main.spell5.getElement() + "\n");
                        spellChoice = Commands.inputInt(1, 6);
                        Commands.clear();
                        
                        //If the user wishes to cancel the spell casting, break the switch
                        if(spellChoice == 6){
                            spellChoice = -1;
                            break;
                        } else if (spellChoice == spellCooldown1 || spellChoice == spellCooldown2){
                            //If the spell is on cooldown, indicate this
                            System.out.println("This spell is on cooldown!");
                            spellChoice = -1; //This is for the cooldown mechanism
                            Commands.pressEnter();
                            break;
                        }
                        
                        continueStatus = true;
                        
                        //Switch that sets the active spell to the spell the user chose
                        switch(spellChoice){
                            case 1:{
                                activeSpell = Main.spell1;
                                break;
                            }
                            case 2:{
                                activeSpell = Main.spell2;
                                break;
                            }
                            case 3:{
                                activeSpell = Main.spell3;
                                break;
                            }
                            case 4:{
                                activeSpell = Main.spell4;
                                break;
                            }
                            case 5:{
                                activeSpell = Main.spell5;
                                break;
                            }
                        }
                        
                        System.out.println("You cast " + activeSpell.getName() + "!!!");
                        Commands.pressEnter();
                        
                        //Check to see if the enemy has dodged the spell
                        if(Commands.getRandomDouble(0, 1) < activeSpell.getDodge()){
                            System.out.println("Unfortunately, " + wizName + " dodges your " + activeSpell.getName() + ", therefore dealing 0 damage");
                            Commands.pressEnter(); //This counts as a valid action even if the enemy dodges the spell
                            break;
                        } else if (activeSpell.getName().equals("Nothing (literally does nothing)") && Commands.getRandomDouble(0, 1) < 0.1){
                            //Check to see if the spell is Nothing and there is a 10% chance it instantly kills the enemy
                            //This is an easter egg in the game
                            System.out.println("Your cursed spell of nothingness somehow deals 1 Billion Damage and kills " + wizName + "!!!");
                            Commands.pressEnter();
                            enemIsDead = true;
                            break;
                        }
                        
                        //Set a spell damage to print it
                        spellDamage = activeSpell.getDamage(Main.staff.getDamageMultiplier());
                        
                        System.out.println("Your " + activeSpell.getName() + " deals " + spellDamage + " damage with your staff modifier!");
                        
                        //Subtract the enemy health according to the spell damage
                        enemRemainingHealth -= spellDamage;
                        
                        //If the enemy's health become 0 or less, indicate that the enemy has died
                        if(enemRemainingHealth <= 0){
                            System.out.println("\nThis spell kills " + wizName + "!!!");
                            Commands.pressEnter();
                            enemIsDead = true;
                        } else { //Else print the enemy's remaining health
                            System.out.println("\n" + wizName + " now has " + enemRemainingHealth + " HP remaining!");
                            Commands.pressEnter();
                        } 
                        
                        break;
                    }
                    case 3:{
                        //Case 3 is for drinking a potion
                        
                        //Check if the player has any potions left
                        if(playPotions == 0){
                            System.out.println("Unfortunately you have no potions left");
                            spellChoice = -1;
                            Commands.pressEnter();
                            break;
                        }
                        
                        continueStatus = true;
                        playPotions--;
                        
                        //Check if the player's health is at maximum and print a message stating that the user drank a potion for nothing
                        if(playRemainingHealth == Main.playMaximumHealth){
                            System.out.println("You drink a potion for nothing, you just wasted a precious potion!");
                        } else if(playRemainingHealth > (Main.playMaximumHealth / 2)){
                            //If the player's health is more than their maximum health's half, then they heal until max health
                            potionHealth = Main.playMaximumHealth - playRemainingHealth;
                            playRemainingHealth = Main.playMaximumHealth;
                            
                            System.out.println("You drink a potion for " + potionHealth + " HP, therefore reaching your maximum health of " + playRemainingHealth + " HP!");
                        } else {
                            //Heal for half their maximum health
                            potionHealth = Main.playMaximumHealth / 2;
                            playRemainingHealth += potionHealth;
                            
                            System.out.println("You drink a potion for " + potionHealth + " HP, now you have " + playRemainingHealth + " HP remaining!");
                        }
                        
                        System.out.println("\nYou now have " + playPotions + " potions left");
                        Commands.pressEnter();
                        
                        break;
                    }
                    
                }
                
                //If the spell choice is -1 then skip the cooldown because that means the user did not do a valid action
                if(spellChoice != -1){
                    spellCooldown2 = spellCooldown1;
                    spellCooldown1 = spellChoice;
                }

            }while(!continueStatus); //End of players turn
            
            
            
            //If the enemy is dead
            if(enemIsDead){
                //Increase the user's level and max health, and increase their gold
                Main.playLevel++;
                Main.playMaximumHealth += 200;
                Townhall.addEnemy(wizName);
                gainedGold = (int) (50 * goldModifier);
                Main.gold += gainedGold;
                Main.totalGainedGold += gainedGold;

                //Print that the enemy wizard has been defeated and the user's gains from that fight, then ask if the user wishes to contine their adventure
                System.out.println(wizName + " has been defeated successfully!");
                System.out.println("\nAfter your amazing victory, you level up to level " + Main.playLevel + "!");
                System.out.println("\nYour maximum health is now " + Main.playMaximumHealth + " HP!");
                System.out.println("\nYou have also gained " + gainedGold + " G from that battle, you now have " + Main.gold + " G!");
                Commands.pressEnter();
                
                System.out.println(wizName + " have dropped some items!");
                System.out.println("\nSword: " + wizSword.getName());
                System.out.println("\nStaff: " + wizStaff.getName());
                System.out.println("\nArmor: " + wizArmor.getName());
                Commands.pressEnter();
                
                //Sword drop
                
                System.out.println(wizName + "'s Sword: ");
                System.out.println("\n" + wizSword.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nYour current Sword");
                System.out.println("\n" + Main.sword.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nWould you like to keep? (If you keep you will lose your current sword)");
                System.out.println("\n1. Keep");
                System.out.println("\n2. Sell for half the cost\n");
                keepChoice = Commands.inputInt(1, 2);
                Commands.clear();

                //If the user wishes to sell
                if(keepChoice == 2){
                    Main.gold += wizSword.getCost() / 2;

                    System.out.println("You choose to sell the " + wizSword.getName() + " for " + (wizSword.getCost() / 2) + " G!");
                    System.out.println("\nYou now have " + Main.gold + " G!");
                    Commands.pressEnter();
                } else {
                    //Set the current sword to the dropped sword
                    Main.sword = wizSword;

                    System.out.println("You choose to keep the " + wizSword.getName());
                    Commands.pressEnter();
                }
                
                //Staff drop
                
                System.out.println(wizName + "'s Staff: ");
                System.out.println("\n" + wizStaff.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nYour current Staff");
                System.out.println("\n" + Main.staff.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nWould you like to keep? (If you keep you will lose your current staff)");
                System.out.println("\n1. Keep");
                System.out.println("\n2. Sell for half the cost\n");
                keepChoice = Commands.inputInt(1, 2);
                Commands.clear();
                
                //If the user wishes to sell
                if(keepChoice == 2){
                    Main.gold += wizStaff.getCost() / 2;
                    
                    System.out.println("You choose to sell the " + wizStaff.getName() + " for " + (wizStaff.getCost() / 2) + " G!");
                    System.out.println("\nYou now have " + Main.gold + " G!");
                    Commands.pressEnter();
                } else {
                    //Set the current staff to the dropped staff
                    Main.staff = wizStaff;
                    
                    System.out.println("You choose to keep the " + wizStaff.getName());
                    Commands.pressEnter();
                }
                
                //Armor drop
                
                System.out.println(wizName + "'s Armor: ");
                System.out.println("\n" + wizArmor.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nYour current Armor");
                System.out.println("\n" + Main.armor.toString());
                System.out.println("\n********************************************************************************");
                System.out.println("\nWould you like to keep? (If you keep you will lose your current armor)");
                System.out.println("\n1. Keep");
                System.out.println("\n2. Sell for half the cost\n");
                keepChoice = Commands.inputInt(1, 2);
                Commands.clear();
                
                //If the user wishes to sell
                if(keepChoice == 2){
                    Main.gold += wizArmor.getCost() / 2;
                    
                    System.out.println("You choose to sell the " + wizArmor.getName() + " for " + (wizArmor.getCost() / 2) + " G!");
                    System.out.println("\nYou now have " + Main.gold + " G!");
                    Commands.pressEnter();
                } else {
                    //Set the current staff to the dropped staff
                    Main.armor = wizArmor;
                    
                    System.out.println("You choose to keep the " + wizArmor.getName());
                    Commands.pressEnter();
                }
                
                continue; //This is to skip the enemy's turn
            }
            
            //Enemy wizard's turn
            
            System.out.println("********************************************************************************\n*");
            System.out.println("* " + wizName + "\n*");
            System.out.println("* Remaining Health:     " + enemRemainingHealth + " HP\n*");
            System.out.println("* Sword Damage:         " + wizSword.getDamage() + "\n*");
            System.out.println("* Staff Spell Modifier: " + wizStaff.getDamageMultiplier() + "x\n*");
            System.out.println("* Armor:                " + wizArmor.getArmor()  + "\n*");
            System.out.println("* Potions Left:         " + wizPotions);
            System.out.println("\n********************************************************************************\n*");
            System.out.println("* " + Main.playName + "\n*");
            System.out.println("* Remaining Health:     " + playRemainingHealth + " HP\n*");
            System.out.println("* Sword Damage:         " + Main.sword.getDamage() + "\n*");
            System.out.println("* Staff Spell Modifier: " + Main.staff.getDamageMultiplier() + "x\n*");
            System.out.println("* Armor:                " + Main.armor.getArmor()  + "\n*");
            System.out.println("* Potions Left:         " + playPotions);
            System.out.println("*\n********************************************************************************");
            
            System.out.println("\n" + wizName + "'s turn!");
            Commands.pressEnter();
            
            //Initiate necessary variables for the enemy wizard's turn
            
            //If the wizard's health falls below 50% they will drink a potion automatically
            if(enemRemainingHealth < (enemMaxHealth / 2) && wizPotions > 0){
                potionHealth = enemMaxHealth / 2;
                enemRemainingHealth += potionHealth;
                wizPotions -= 1;
                
                System.out.println(wizName + " chooses to drink a potion for " + potionHealth + " HP, now " + wizName + " has " + enemRemainingHealth + " HP remaining!\n");
                System.out.println(wizName + " has " + wizPotions + " potions left!");
                Commands.pressEnter();
            }
            //Else runs the other possible actions of the wizards
            else{
                //If the wizard's sword damage is more than the player's armor, the wizard may attack with a sword
                if(wizSword.getDamage() > Main.armor.getArmor()){
                    wizChoice = Commands.getRandom(4);
                }
                //If its not, the wizard will only cast spells
                else{
                    wizChoice = Commands.getRandom(3) + 1;
                }
                
                
                switch(wizChoice){
                    case 1:{ //1 is sword attack
                        attackIsSpell = false;
                        System.out.println(wizName + " chooses to attack!");
                        
                        swordDamage = wizSword.getDamage() - Main.armor.getArmor();
                        
                        if(swordDamage <= 0){
                            swordDamage = 0;
                        }
                        
                        //Subtract player's health according to the damage
                        playRemainingHealth -= swordDamage;
                        
                        System.out.println("\nWith your armor, " + wizName + "'s attack deals " + swordDamage + " damage!");
                        
                        //If the player's health become 0 or less, indicate that the player has died
                        if(playRemainingHealth <= 0){
                            System.out.println("\nThis attack kills you!");
                            Commands.pressEnter();
                            playerDeath();
                        }
                        else{ //If not, print the player's remaining health
                            System.out.println("\nThis attack leaves you with " + playRemainingHealth + " HP remaining!");
                            Commands.pressEnter();
                        }
                        
                        break;
                    }
                    case 2:{ //2 is the first spell
                        attackIsSpell = true;
                        activeSpell = wizSpell1;
                        
                        break;
                    }
                    case 3:{ //3 is the second spell
                        attackIsSpell = true;
                        activeSpell = wizSpell2;
                        
                       
                        break;
                    }
                    case 4:{ //4 is the third spell
                        attackIsSpell = true;
                        activeSpell = wizSpell3;
                        
                        break;
                    }
                }
                
                //If the attack was a spell
                if(attackIsSpell){
                    System.out.println(wizName + " chooses to cast " + activeSpell.getName() + "!!!");
                    Commands.pressEnter();
                    
                    //Check to see if the enemy has dodged the spell
                    if(Commands.getRandomDouble(0, 1) < activeSpell.getDodge()){
                        System.out.println("Fortunately, you dodge " + wizName + "'s " + activeSpell.getName() + " therefore getting no damage!");
                        Commands.pressEnter();
                    } 
                    //If you didnt dodge
                    else {
                        //Set a spell damage to print it
                        spellDamage = activeSpell.getDamage(wizStaff.getDamageMultiplier());
                        
                        System.out.println(wizName + "'s " + activeSpell.getName() + " deals " + spellDamage + " damage with their staff modifier!");
                        
                        //Subtract the player health according to the spell damage
                        playRemainingHealth -= spellDamage;
                        
                        //If the player's health become 0 or less, indicate that the player has died
                        if(playRemainingHealth <= 0){
                            System.out.println("\nThis spell kills you!");
                            Commands.pressEnter();
                            playerDeath();
                        }
                        else{ //If not, print the player's remaininghealth
                            System.out.println("\nThis leaves you with " + playRemainingHealth + " HP remaining!");
                            Commands.pressEnter();
                        }
                        
                    }
                    
                }
                
            } //End of enemy wizard's turn
            
        } //End of battle
    }
    
    
    
    /********************************
     * playerDeath
     * This method runs when the player dies, ending the program
     ********************************/
    private static void playerDeath(){
        //Print this run to the file
        Townhall.printPreviousRun(Main.playName, Main.playLevel, Main.totalGainedGold);
        
        //Ending messages
        System.out.println("Unfortunately, you have been defeated!");
        System.out.println("\nAfter many adventures, countless memorized spells, and many raided areas of Riucaria, "
                + "\nyour journey ends after defeating " + Townhall.enemiesDefeated.size() + " enemies...");
        Commands.pressEnter();
        
        System.out.println("One extra thing you may want to know before leaving the game:");
        System.out.println("\nThere is an easter egg in the game that you might have stumbled upon accidentally, "
                + "\nit is highly advised that you try to seek it (hint: it is connected to the "
                + "\nNothing spell that you start with)");
        System.out.println("\n\nThank you for playing Wizard's Armageddon!!!");
        Commands.pressEnter();
        
        System.exit(0);
    }
    
    
    
    /*********************************
     * battleBanner
     * This method prints the battle banner with all the stats of the two sides
     *********************************/
    private static void battleBanner(){
        System.out.println("********************************************************************************\n*");
        System.out.println("* " + enemActive.getName() + "\n*");
        System.out.println("* Remaining Health:     " + enemRemainingHealth + " HP\n*");
        System.out.println("* Maximum Damage:       " + enemActive.getMaxDamage(diffModifier) + "\n*");
        System.out.println("* Minimum Damage:       " + enemActive.getMinDamage(diffModifier) + "\n*");
        System.out.println("* Resistance:           " + enemActive.getResistance() + "\n*");
        System.out.println("* Weakness:             " + enemActive.getWeakness() + "\n*");
        System.out.println("********************************************************************************\n*");
        System.out.println("* " + Main.playName + "\n*");
        System.out.println("* Remaining Health:     " + playRemainingHealth + " HP\n*");
        System.out.println("* Sword Damage:         " + Main.sword.getDamage() + "\n*");
        System.out.println("* Staff Spell Modifier: " + Main.staff.getDamageMultiplier() + "x\n*");
        System.out.println("* Armor:                " + Main.armor.getArmor()  + "\n*");
        System.out.println("* Potions Left:         " + playPotions);
        System.out.println("*\n********************************************************************************");
    }
}
