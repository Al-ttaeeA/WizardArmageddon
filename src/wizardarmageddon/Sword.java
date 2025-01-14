/**************************************
 * Project:         WizardArmageddon
 * Program:         Sword.java
 **************************************/
package wizardarmageddon;

public class Sword {
    //Fields
    private final String name;
    private final int damage;
    private final int cost;
    
    //Constructors
    
    /***************************************
     * Argument based contructor
     * @param name - The name of the sword
     * @param damage - The damage of the sword
     * @param cost - The cost of the sword
     ***************************************/
    Sword(String name, int damage, int cost){
        this.name = name;
        this.damage = damage;
        this.cost = cost;
    }
    
    //No Arg Constructor, this makes a default sword that the user starts with
    Sword(){
        name = "Bronze Sword";
        damage = 180;
        cost = 0;
    }
    
    //Get commands
    
    /**********************
     * getName
     * @return - The sword's name
     **********************/
    public String getName(){
        return this.name;
    }
    
    /*********************
     * getDamage
     * @return - The sword's damage
     *********************/
    public int getDamage(){
        return this.damage;
    }
    
    /*******************
     * getCost
     * @return - The sword's cost
     *******************/
    public int getCost(){
        return this.cost;
    }
    
    
    /***********************
     * toString
     * This converts the sword's data to a string
     * @return - A string that has the sword's data
     ***********************/
    public String toString(){
        String string = "Name: " + name + "\n\nDamage: " + damage + "\n\nCost: " + cost + " G";
        return string;
    }
}
