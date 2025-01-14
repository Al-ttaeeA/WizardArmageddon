/**************************************
 * Project:         WizardArmageddon
 * Program:         Data.java
 **************************************/
package wizardarmageddon;

/***************************************************************************************************
 * This file contains all the data for enemies, spells, sword, staffs, and armors
 * The user is allowed and encouraged to add more enemies, spells, or items
 * There is instructions for each array that must be followed to ensure that there will be 
 * no errors
 * 
 * Some Notes:
 * 
 * Current elements are: [Fire] [Water] [Earth] [Dark] [Light]
 * The user is allowed to add an entirely new element in the format [element]
 * This element will be used for the resistance and weakness of the enemies as well as 
 * the spell element
 * 
 * The user should not add "The" or "A" or any such keyword before the name of the items and 
 * spells.
 * 
 * The user is absolutely allowed to add a ridiculously strong item with 10000 damage and 10 G cost
 * for example
 * 
 * Have fun with balancing out the items as you wish, you may nerf all the items and spells to make 
 * the game harder or do quite the opposite
 ***************************************************************************************************/

public class Data {
    /***************************************************************************************************
     * SPELLS
     * 
     * The format for adding a new spell is as follows:
     * new Spell("Name of spell", Damage Constant, Damage Multiplier, Dodge Chance, Element)
     * 
     * Dodge Chance must be a decimal between 0 and 1 as a percentage, so 0.35 dodge means 35% dodge
     * 
     * Minimum Damage of the spell will be [Damage Constant + Damage Multiplier]
     * 
     * Maximum Damage of the spell will be [Damage Constant + (Damage Multiplier * 10)]
     * 
     * 
     * DO NOT Forget to add a comma after each line except for the last line of the array
     ***************************************************************************************************/
    public final static Spell[] spells = {
        new Spell("Fireball"            , 160, 30, 0.27, "[Fire]"),
        new Spell("Earthquake"          , 160, 10, 0.06, "[Earth]"),
        new Spell("Ice Shards"          , 190, 30, 0.35, "[Water]"),
        new Spell("Fire Breath"         , 180, 25, 0.20, "[Fire]"),
        new Spell("Lesser Sun beam"     , 380, 10, 0.30, "[Fire]"),
        new Spell("Meteor Rain"         , 90 , 40, 0.35, "[Fire]"),
        new Spell("Inferno Blaze"       , 130, 30, 0.20, "[Fire]"),
        new Spell("Rock Punch"          , 100, 10, 0.02, "[Earth]"),
        new Spell("Meteor Strike"       , 100, 38, 0.30, "[Earth]"),
        new Spell("Sandstorm"           , 60 , 40, 0.27, "[Earth]"),
        new Spell("Rockslide"           , 360, 10, 0.29, "[Earth]"),
        new Spell("Water Gun"           , 130, 30, 0.20, "[Water]"),
        new Spell("Ice Sword"           , 230, 10, 0.10, "[Water]"),
        new Spell("Aqua Jet"            , 120, 28, 0.15, "[Water]"),
        new Spell("Tsunami"             , 160, 10, 0.03, "[Water]"),
        new Spell("Black Hole"          , 200, 30, 0.40, "[Dark]"),
        new Spell("Dark Shroud"         , 100, 12, 0.02, "[Dark]"),
        new Spell("Shadow Fist"         , 200, 15, 0.12, "[Dark]"),
        new Spell("Shadow Bolt"         , 120, 30, 0.18, "[Dark]"),
        new Spell("Cursed Fire"         , 320, 10, 0.20, "[Dark]"),
        new Spell("Blinding Bright"     , 120, 10, 0.03, "[Light]"),
        new Spell("Sun Beam"            , 400, 12, 0.40, "[Light]"),
        new Spell("Laser Bomb"          , 150, 16, 0.08, "[Light]"),
        new Spell("Glowing Orb"         , 200, 16, 0.11, "[Light]"),
        new Spell("Solar Flare"         , 170, 12, 0.05, "[Light]")
    };
    
    
    /***************************************************************************************************
     * SWORDS
     * 
     * The format for adding a new sword is as follows:
     * new Sword("Name of sword", Damage, Cost)
     * 
     * 
     * DO NOT Forget to add a comma after each line except for the last line of the array
     ***************************************************************************************************/
    public final static Sword[] swords = {
        new Sword("Iron Sword"              , 160, 40),
        new Sword("Rusty Greatsword"        , 180, 90),
        new Sword("Silver Dagger"           , 200, 120),
        new Sword("Bronze Sickle"           , 245, 180),
        new Sword("Ironclad"                , 260, 200),
        new Sword("Dragon Slayer"           , 275, 230),
        new Sword("Embersteel"              , 300, 270),
        new Sword("Shadowfang"              , 305, 280),
        new Sword("Venomspike"              , 310, 290),
        new Sword("Moonlit Edge"            , 315, 295),
        new Sword("Death Scepter"           , 320, 300),
        new Sword("Galeforce"               , 345, 345),
        new Sword("Phantom Slayer"          , 360, 370),
        new Sword("Kraken Scale Sword"      , 365, 380),
        new Sword("Gerethia Royal Sword"    , 395, 420),
        new Sword("Diamond Dual Daggers"    , 420, 520),
        new Sword("Excalibur"               , 450, 600),
        new Sword("Engraved Damascus Katana", 520, 720),
        new Sword("Forged Dualblade"        , 600, 840),
        new Sword("Hellfire Saber"          , 680, 960),
        new Sword("Reaper's Scythe"         , 730, 1020),
        new Sword("Soulstealer Blade"       , 800, 1200)
    };
    
    /***************************************************************************************************
     * This array is ONLY for Swords that may drop as an item from enemies
     * 
     * It follows the same format as the swords array
     ***************************************************************************************************/
    public final static Sword[] itemSwords = {
        new Sword("Icebreaker Edge"         , 435, 550),
        new Sword("Bloodflame Blade"        , 520, 700)
    };
    
    
    /***************************************************************************************************
     * STAFFS
     * 
     * The format for adding a new staff is as follows:
     * new Staff("Name of staff", Damage Multiplier, Cost)
     * 
     * 
     * DO NOT Forget to add a comma after each line except for the last line of the array
     ***************************************************************************************************/
    public final static Staff[] staffs = {
        new Staff("Forgotten Staff"         , 1.20, 60),
        new Staff("Sapphire Star Staff"     , 1.25, 100),
        new Staff("Mystic Wood Staff"       , 1.30, 120),
        new Staff("Harmony Weaver Staff"    , 1.40, 140),
        new Staff("Crystal Staff"           , 1.50, 160),
        new Staff("Flamecaller Staff"       , 1.55, 170),
        new Staff("Icebound Staff"          , 1.75, 200),
        new Staff("Meteorite Staff"         , 1.85, 230),
        new Staff("Thunderstrike Staff"     , 2.05, 260),
        new Staff("Lavastone Staff"         , 2.15, 280),
        new Staff("Stormweaver Staff"       , 2.20, 300),
        new Staff("Gerethia Wizard's Staff" , 2.35, 340),
        new Staff("Lunar Eclipse Staff"     , 2.40, 360),
        new Staff("Sunfire Staff"           , 2.55, 400),
        new Staff("Centaur Horn Staff"      , 2.60, 420),
        new Staff("Moonshadow Staff"        , 2.80, 460),
        new Staff("Voladia Royal Staff"     , 2.85, 520),
        new Staff("Dragon Heart Staff"      , 3.25, 600),
        new Staff("Ethereal Whisper Staff"  , 3.40, 660),
        new Staff("Golden Sun Staff"        , 3.45, 680),
        new Staff("Phoenix Wing Staff"      , 3.60, 740),
        new Staff("Ancient Rune Staff"      , 3.80, 820),
        new Staff("Forged Volcanic Staff"   , 4.00, 920),
        new Staff("Twilight's End Staff"    , 4.50, 1100),
        new Staff("Abyssal Depths Staff"    , 4.80, 1150),
        new Staff("Ethereal Nexus Staff"    , 5.00, 1200)
    };
    
    /***************************************************************************************************
     * This array is ONLY for Staffs that may drop as an item from enemies
     * 
     * It follows the same format as the staffs array
     ***************************************************************************************************/
    public final static Staff[] itemStaffs = {
        new Staff("Vine Woven Staff"        , 3.00, 560),
        new Staff("Centaur Horn Staff"      , 2.25, 320),
        new Staff("Giant's Nail Staff"      , 2.70, 440),
        new Staff("Kirin Horn Staff"        , 3.60, 750)
    };
    
    
    /***************************************************************************************************
     * ARMORS
     * 
     * The format for adding a new armor is as follows:
     * new Armor("Name of armor", Armor, Cost)
     * 
     * 
     * DO NOT Forget to add a comma after each line except for the last line of the array
     ***************************************************************************************************/
    public final static Armor[] armors = {
        new Armor("Wizard's Cloak"          , 100, 100),
        new Armor("Elven Silk Robe"         , 120, 130),
        new Armor("Chainmail Armor"         , 140, 150),
        new Armor("Shadowweave Cloak"       , 150, 180),
        new Armor("Fallen Warrior Armor"    , 180, 200),
        new Armor("Silversteel Chainmail"   , 200, 210),
        new Armor("Ghostweave Armor"        , 220, 220),
        new Armor("Knight Armor"            , 240, 250),
        new Armor("Bronze Scale Armor"      , 265, 280),
        new Armor("Emberheart Robe"         , 280, 300),
        new Armor("Gerethia Warrior's Armor", 295, 320),
        new Armor("Phoenix Feather Cape"    , 310, 380),
        new Armor("Dragonbone Plate"        , 320, 400),
        new Armor("Dragon Skin Armor"       , 335, 480),
        new Armor("Ancient Phoenix Cloak"   , 350, 510),
        new Armor("Meteor Shards"           , 390, 540),
        new Armor("Forged Steel Plate"      , 430, 650),
        new Armor("Venomspine Carapace"     , 450, 680),
        new Armor("Devourer's Husk"         , 490, 720),
        new Armor("Frostwolf Pelt"          , 530, 780),
        new Armor("Ancient Voladia Robe"    , 560, 830),
        new Armor("Dark Shadow Armor"       , 630, 940),
        new Armor("Stormforged Chainmail"   , 680, 1020),
        new Armor("Starweave Mantle"        , 720, 1100),
        new Armor("Celestial Radiance Armor", 800, 1200),
    };
    
    /***************************************************************************************************
     * This array is ONLY for Armors that may drop as an item from enemies
     * 
     * It follows the same format as the armors array
     ***************************************************************************************************/
    public final static Armor[] itemArmors = {
        new Armor("Serpent Scale Armor"     , 400, 550),
        new Armor("Hydra Wings Husk"        , 580, 850)
    };
    
    
    /***************************************************************************************************
     * ENEMIES
     * 
     * The format for adding a new enemy is as follows:
     * new Enemy("Name of enemy", Health, Damage Constant, Damage Multiplier, Resistance, Weakness, Item Drop)
     * 
     * Minimum Damage of the enemy will be [Damage Constant + Damage Multiplier]
     * 
     * Maximum Damage of the enemy will be [Damage Constant + (Damage Multiplier * 10)]
     * 
     * Refer to another array of objects to set the item type, or leave empty if the enemy has no item to drop
     * and will drop a bag of Gold
     * 
     * 
     * DO NOT Forget to add a comma after each line except for the last line of the array
     ***************************************************************************************************/
    public final static Enemy[] enemies = {
        new Enemy("Fire Dragon"         , 800, 140, 10, "[Fire]", "[Water]"  , armors[13]),
        new Enemy("Lava Wyrm"           , 1000, 90, 18, "[Fire]", "[Water]"  , staffs[9]),
        new Enemy("Rock Golem"          , 900, 100, 15,"[Earth]", "[Light]"  ),
        new Enemy("Ice Yeti"            , 1000, 70, 20,"[Water]", "[Fire]"   , itemSwords[0]),
        new Enemy("Dark Necromancer"    , 600, 170, 20, "[Dark]", "[Light]"  , armors[21]),
        new Enemy("Mystic Ghoul"        , 1200, 50, 25, "[Dark]", "[Light]"  , swords[1]),
        new Enemy("Tree Ent"            , 920, 105, 13, "[Earth]", "[Fire]"  , itemStaffs[0]),
        new Enemy("Mythical Serpent"    , 1100, 95, 15, "[Light]", "[Dark]"  , itemArmors[0]),
        new Enemy("Wild Centaur"        , 800, 120, 12, "[Light]", "[Earth]" , itemStaffs[1]),
        new Enemy("Dark Vampire"        , 700, 90, 23, "[Dark]", "[Light]"   , swords[7]),
        new Enemy("Epic Bigfoot"        , 1100, 90, 17, "[Earth]", "[Water]" ),
        new Enemy("Blazing Phoenix"     , 600, 120, 30, "[Fire]", "[Water]"  , staffs[20]),
        new Enemy("Land Kraken"         , 1200, 80, 13, "[Water]", "[Fire]"  , swords[13]),
        new Enemy("Zombified Warrior"   , 900, 160, 14, "[Dark]", "[Light]"  ),
        new Enemy("Ancient Giant"       , 1500, 80, 10, "[Earth]", "[Light]" , itemStaffs[2]),
        new Enemy("Gerethia Warrior"    , 1100, 120, 18, "[Dark]", "[Light]" , armors[10]),
        new Enemy("Gerethia Knight"     , 900, 200, 15, "[Dark]", "[Light]"  , swords[14]),
        new Enemy("Gerethia Wizard"     , 700, 100, 35, "[Dark]", "[Light]"  , staffs[11]),
        new Enemy("Blazing Chimera"     , 1100, 50, 36, "[Fire]", "[Water]"  ),
        new Enemy("Wild Kirin"          , 1000, 140, 20, "[Light]", "[Dark]" , itemStaffs[3]),
        new Enemy("Land Siren"          , 900, 140, 25, "[Water]", "[Light]" ),
        new Enemy("Midnight Reaper"     , 700, 300, 17, "[Dark]", "[Light]"  , swords[20]),
        new Enemy("Solar Elf"           , 500, 50, 45, "[Light]", "[Dark]"   , staffs[13]),
        new Enemy("Fire Sprite"         , 300, 500, 15, "[Fire]", "[Water]"  , itemSwords[1]),
        new Enemy("Mythical Hydra"      , 1400, 120, 10, "[Water]", "[Earth]", itemArmors[1]),
        new Enemy("Sand Worm"           , 1200, 90, 13, "[Earth]", "[Water]" ),
        new Enemy("Solar Guardian"      , 2000, 40, 10, "[Light]", "[Dark]"  , staffs[19]),
        new Enemy("Eclipse Demon"       , 1800, 60, 10, "[Dark]", "[Light]"  , staffs[12])
    };
    
    
    
    /***************************************************************************************************
     * This array is for possible wizard enemy first names
     ***************************************************************************************************/
    public final static String[] wizNames = {
    "Ganfald", "Suraman", "Alsub", "Menril", "Draco", "Leon", "Rhaqium", "Astrum", "Jegrus", "Ovenitor", 
    "Aharad", "Gyoubu", "Alaric", "Eldrin", "Thalindra", "Zephyrion", "Galadriel", "Maelwyn", "Fenwick", 
    "Sorin", "Elowen", "Thrain", "Lysandra", "Draven", "Sylvanus", "Orin", "Caelus", "Nymeria", "Eryndor", 
    "Aric", "Valeria", "Thalos", "Marisella", "Vesper", "Daelin", "Isolde", "Dorian", "Lyria", "Aeliana", 
    "Thaddeus", "Merida", "Elric", "Morgana", "Thalion", "Faelan", "Seraphina", "Balin", "Cassandra", 
    "Gideon", "Rowan", "Lysander", "Mirabella"
};
    
    
    
    /***************************************************************************************************
     * This array is for possible wizard enemy adjectives
     ***************************************************************************************************/
    public final static String[] wizAdjectives = {
    "Gray", "Great", "Noble", "Courageous", "Stupid", "Crazy", "Kind", "Gentle", "Fat", "Strong", "Tall",
    "Wise", "Powerful", "Mysterious", "Enigmatic", "Ancient", "Arcane", "Cunning", "Brave", "Valiant", 
    "Swift", "Mighty", "Witty", "Sage", "Bold", "Clever", "Daring", "Fearless", "Mystic", "Fierce", 
    "Lazy", "Gallant", "Astute", "Keen", "Vigilant", "Resolute", "Shrewd", "Tenacious", "Clumsy", 
    "Eloquent", "Arrogant", "Insightful", "Resourceful", "Greedy", "Audacious", "Prudent", "Sagacious", 
    "Formidable"
};
}
