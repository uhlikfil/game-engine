/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import java.awt.image.BufferedImage;

/**
 * loads all the cropped images at the start of the game as a public static object
 */
public class Assets {
    
    private static final int CLASSIC_CREATURE_WIDTH = 200, CLASSIC_CREATURE_HEIGHT = 200;
    private static final int OTHER_WIDTH = 64, OTHER_HEIGHT = 64;
    public static final int ITEM_WIDTH = 20, ITEM_HEIGHT = 20;
    
    public static BufferedImage menuBackground, controls, textBg, youDied,
                                player_charge_right, player_charge_left, player_att_right, player_att_left, player_dead, player_resting,
                                enemy_dead, enemy_charge_left, enemy_charge_right, enemy_att_left, enemy_att_right, 
                                healthbar, stone_bottom, stone_top, pillar, wood, wood_bg, roof,
                                soul;
    public static BufferedImage[] player_idle, player_walking_right, player_walking_left,   
                                    enemy_idle, enemy_walking_right, enemy_walking_left,
                                    bonfire;
    /**
     * crops all the textures from sheets
     */
    public static void init() {
        String bgPath = "textures/backgrounds/";
        String sheetsPath = "textures/sheets/";
        menuBackground = ImageLoader.loadImage(bgPath + "menuBg.jpg");
        controls = ImageLoader.loadImage(bgPath + "controls.jpg");
        SpriteSheet creature_sheet = new SpriteSheet(ImageLoader.loadImage(sheetsPath + "creature_sheet.png"));
        SpriteSheet other_sheet = new SpriteSheet(ImageLoader.loadImage(sheetsPath + "other_sheet.png"));
        SpriteSheet items_sheet = new SpriteSheet(ImageLoader.loadImage(sheetsPath + "items_sheet.png"));
        
        player_idle = new BufferedImage[4];
        player_idle[0] = creature_sheet.crop(0, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_idle[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_idle[2] = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_idle[3] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        
        player_walking_right = new BufferedImage[4];
        player_walking_right[0] = creature_sheet.crop(0, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_walking_right[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_walking_right[2] = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_walking_right[3] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);

        
        player_walking_left = new BufferedImage[4];
        player_walking_left[0] = creature_sheet.crop(0, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_walking_left[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_walking_left[2] = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT); // NOT FINISHED
        player_walking_left[3] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        
        player_charge_right = creature_sheet.crop(3*CLASSIC_CREATURE_WIDTH, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_att_right = creature_sheet.crop(4*CLASSIC_CREATURE_WIDTH, 0, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_charge_left = creature_sheet.crop(3*CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_att_left = creature_sheet.crop(4*CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_dead = creature_sheet.crop(3*CLASSIC_CREATURE_WIDTH, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        player_resting = creature_sheet.crop(4*CLASSIC_CREATURE_WIDTH, 2*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        
        enemy_dead = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, 3*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_charge_right = creature_sheet.crop(3*CLASSIC_CREATURE_WIDTH, 3*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_att_right = creature_sheet.crop(4*CLASSIC_CREATURE_WIDTH, 3*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_charge_left = creature_sheet.crop(3*CLASSIC_CREATURE_WIDTH, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_att_left = creature_sheet.crop(4*CLASSIC_CREATURE_WIDTH, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        
        enemy_idle = new BufferedImage[2];
        enemy_idle[0] = creature_sheet.crop(0, 3*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_idle[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 3*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
     
        enemy_walking_right = new BufferedImage[4];
        enemy_walking_right[0] = creature_sheet.crop(0, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_right[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_right[2] = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_right[3] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 4*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);

        enemy_walking_left = new BufferedImage[4];
        enemy_walking_left[0] = creature_sheet.crop(0, 5*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_left[1] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 5*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_left[2] = creature_sheet.crop(2*CLASSIC_CREATURE_WIDTH, 5*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        enemy_walking_left[3] = creature_sheet.crop(CLASSIC_CREATURE_WIDTH, 5*CLASSIC_CREATURE_HEIGHT, CLASSIC_CREATURE_WIDTH, CLASSIC_CREATURE_HEIGHT);
        
        youDied = other_sheet.crop(0, 5*OTHER_HEIGHT, 8*OTHER_WIDTH, 2*OTHER_HEIGHT);
        textBg = other_sheet.crop(0, 4*OTHER_HEIGHT, 4*OTHER_WIDTH, OTHER_HEIGHT);
        stone_top = other_sheet.crop(0, 0, OTHER_WIDTH, OTHER_HEIGHT);
        stone_bottom = other_sheet.crop(0, OTHER_HEIGHT, OTHER_WIDTH, OTHER_HEIGHT);
        pillar = other_sheet.crop(2*OTHER_WIDTH, OTHER_WIDTH, OTHER_WIDTH, OTHER_HEIGHT);
        roof = other_sheet.crop(2*OTHER_WIDTH, 0, OTHER_WIDTH, OTHER_HEIGHT);
        wood = other_sheet.crop(OTHER_WIDTH, 0, OTHER_WIDTH, OTHER_HEIGHT);
        wood_bg = other_sheet.crop(OTHER_WIDTH, OTHER_WIDTH, OTHER_WIDTH, OTHER_HEIGHT);
        healthbar = other_sheet.crop(3*OTHER_WIDTH, 0, 2*OTHER_WIDTH, OTHER_HEIGHT);
        
        soul = items_sheet.crop(0, 0, ITEM_WIDTH, ITEM_HEIGHT);
        
        bonfire = new BufferedImage[2];
        bonfire[0] = other_sheet.crop(0, 2*OTHER_HEIGHT, OTHER_WIDTH, 2*OTHER_HEIGHT);
        bonfire[1] = other_sheet.crop(OTHER_WIDTH, 2*OTHER_HEIGHT, OTHER_WIDTH, 2*OTHER_HEIGHT);
    }
    
}
