/**************************************
 * Project:         WizardArmageddon
 * Program:         Enemy.java
 **************************************/
package wizardarmageddon;

public class Enemy {
    //Fields
    private final String name;
    private final String resistance;
    private final String weakness;
    private final int health;
    private final int damageConstant;
    private final int damageMultiplier;
    private Sword sword = null;
    private Staff staff = null;
    private Armor armor = null;
    private final String itemType; 
    
    
    //Constructors
    
    /***************************************************************************************************
     * Argument based constructor - This is for enemies that have no item drops
     * @param name - The name of the enemy
     * @param health - The health of the enemy
     * @param damage - The damage constant of the enemy
     * @param damageMultiplier - The damage multiplier
     * @param resistance - The resistance of the enemy
     * @param weakness - The weakness of the enemy
     ***************************************************************************************************/
    Enemy(String name, int health, int damage, int damageMultiplier, String resistance, String weakness){
        this.name = name;
        this.health = health;
        this.damageConstant = damage;
        this.damageMultiplier = damageMultiplier;
        this.resistance = resistance;
        this.weakness = weakness;
        itemType = null;
    }
    
    /***************************************************************************************************
     * Argument based constructor - This is for enemies that have a sword item drop
     * @param name - The name of the enemy
     * @param health - The health of the enemy
     * @param damage - The damage constant of the enemy
     * @param damageMultiplier - The damage multiplier
     * @param resistance - The resistance of the enemy
     * @param weakness - The weakness of the enemy
     * @param sword - The sword item of the enemy
     ***************************************************************************************************/
    Enemy(String name, int health, int damage, int damageMultiplier, String resistance, String weakness, Sword sword){
        this.name = name;
        this.health = health;
        this.damageConstant = damage;
        this.damageMultiplier = damageMultiplier;
        this.resistance = resistance;
        this.weakness = weakness;
        this.sword = sword;
        itemType = "Sword";
    }
    
    /***************************************************************************************************
     * Argument based constructor - This is for enemies that have a staff item drop
     * @param name - The name of the enemy
     * @param health - The health of the enemy
     * @param damage - The damage constant of the enemy
     * @param damageMultiplier - The damage multiplier
     * @param resistance - The resistance of the enemy
     * @param weakness - The weakness of the enemy
     * @param staff - The staff item of the enemy
     ***************************************************************************************************/
    Enemy(String name, int health, int damage, int damageMultiplier, String resistance, String weakness, Staff staff){
        this.name = name;
        this.health = health;
        this.damageConstant = damage;
        this.damageMultiplier = damageMultiplier;
        this.resistance = resistance;
        this.weakness = weakness;
        this.staff = staff;
        itemType = "Staff";
    }
    
    /***************************************************************************************************
     * Argument based constructor - This is for enemies that have a armor item drop
     * @param name - The name of the enemy
     * @param health - The health of the enemy
     * @param damage - The damage constant of the enemy
     * @param damageMultiplier - The damage multiplier
     * @param resistance - The resistance of the enemy
     * @param weakness - The weakness of the enemy
     * @param armor - The armor item of the enemy
     ***************************************************************************************************/
    Enemy(String name, int health, int damage, int damageMultiplier, String resistance, String weakness, Armor armor){
        this.name = name;
        this.health = health;
        this.damageConstant = damage;
        this.damageMultiplier = damageMultiplier;
        this.resistance = resistance;
        this.weakness = weakness;
        this.armor = armor;
        itemType = "Armor";
    }
    
    //No Arg Constructor
    Enemy(){
        name = null;
        health = 0;
        damageConstant = 0;
        damageMultiplier = 0;
        resistance = null;
        weakness = null;
        itemType = null;
    }
    
    
    //Get commands
    
    /**********************
     * getName
     * @return - The name of the enemy
     **********************/
    public String getName(){
        return this.name;
    }
    
    /*********************
     * getHealth
     * @return - The health of the enemy
     *********************/
    public int getHealth(){
        return this.health;
    }
    
    /*****************************
     * getDamageConstant
     * @return - The damage constant of the enemy
     *****************************/
    public int getDamageConstant(){
        return this.damageConstant;
    }
    
    /*******************************
     * getDamageMultiplier
     * @return - The damage multiplier
     *******************************/
    public int getDamageMultiplier(){
        return this.damageMultiplier;
    }
    
    /****************************
     * getResistance
     * @return - The resistance of the enemy
     ****************************/
    public String getResistance(){
        return this.resistance;
    }
    
    /**************************
     * getWeakness
     * @return - The weakness of the enemy
     **************************/
    public String getWeakness(){
        return this.weakness;
    }
    
    /**************************
     * getItemType
     * @return - The item type of the enemy
     **************************/
    public String getItemType(){
        return this.itemType;
    }
    
    /**********************
     * getSword
     * @return - The sword item drop
     **********************/
    public Sword getSword(){
        return this.sword;
    }
    
    /**********************
     * getStaff
     * @return - The staff item drop
     **********************/
    public Staff getStaff(){
        return this.staff;
    }
    
    /**********************
     * getArmor
     * @return - The armor item drop
     **********************/
    public Armor getArmor(){
        return this.armor;
    }
    
    
    //Other methods
    
    /*******************************************
     * getMinDamage
     * This method gets the minimum damage an enemy could deal according to the difficulty
     * @param diffModifier - The difficulty modifier
     * @return - The minimum damage
     *******************************************/
    public int getMinDamage(double diffModifier){
        return (int) ((damageConstant + damageMultiplier) * diffModifier);
    }
    
    /*******************************************
     * getMaxDamage
     * This method gets the maximum damage an enemy could deal according to the difficulty
     * @param diffModifier - The difficulty modifier
     * @return - The maximum damage
     *******************************************/
    public int getMaxDamage(double diffModifier){
        return (int) ((damageConstant + (damageMultiplier * 10)) * diffModifier);
    }
    
    /****************************************
     * getDamage
     * This method gets a random damage based on the difficulty
     * @param diffModifier - The difficulty modifier
     * @return - The damage the enemy deals
     ****************************************/
    public int getDamage(double diffModifier){
        return (int) ((this.damageConstant + (this.damageMultiplier * Commands.getRandom(10))) * diffModifier);
    }
    
    /***********************
     * toString
     * This method converts the enemy's stats into a string
     * @return - A string that has the enemy's stats
     ***********************/
    public String toString(){
        int minDamage = damageConstant + damageMultiplier;
        int maxDamage = damageConstant + (damageMultiplier * 10);
        
        String string = "Name: " + name + "\n\nHealth: " + health + "\n\nBase Minimum Damage: " + minDamage + "\n\nBase Maximum Damage: " + maxDamage + "\n\nResistance: " + resistance + "\n\nWeakness: " + weakness;
        
        if(itemType == null){
            string += "\n\nItem Drop: No item"; 
        } else if (itemType.equals("Sword")){
            string += "\n\nItem Drop: " + sword.getName();
        } else if (itemType.equals("Staff")){
            string += "\n\nItem Drop: " + staff.getName();
        } else if (itemType.equals("Armor")){
            string += "\n\nItem Drop: " + armor.getName();
        }
        
        return string;
    }
}
