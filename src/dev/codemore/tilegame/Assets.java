package dev.codemore.tilegame;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 71, height = 71;

    public static BufferedImage  dirt, rock, enemy, deep1, deep2, sky, cloud1, cloud2, brick, tree;
    public static BufferedImage[] player_left,player_right,player_jumpL,player_jumpR,player_stillL,player_stillR;
    public static BufferedImage[] btn_start;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sprite sheet1.png"));

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(244, 244,width * 2, height);
        btn_start[1] = sheet.crop(244,324, width * 2, height);

        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_jumpL = new BufferedImage[1];
        player_jumpR = new BufferedImage[1];
        player_stillL = new BufferedImage[1];
        player_stillR = new BufferedImage[1];

        player_left[0] = sheet.crop(84, 84, width, height);
        player_left[1] = sheet.crop(84, 244, width, height);
        player_right[0] = sheet.crop(5, 84, width, height);
        player_right[1] = sheet.crop(5, 244, width, height);
        player_jumpL[0] = sheet.crop(84, 324, width, height);
        player_jumpR[0] = sheet.crop(5, 324, width ,height);
        player_stillL[0] = sheet.crop(84, 164, width, height);
        player_stillR[0] = sheet.crop(5, 5, width, height);


        dirt = sheet.crop(84, 5, width, height);
        rock = sheet.crop(164, 5, width, height);
        enemy = sheet.crop(324, 5, width, height);
        deep1 = sheet.crop(244, 5, width, height);
        deep2 = sheet.crop(244, 84, width, height);
        sky = sheet.crop(164, 84, width, height);
        cloud1 = sheet.crop(164, 164, width, height);
        cloud2 = sheet.crop(164, 244, width, height);
        brick = sheet.crop(244, 164, width, height);
        tree = sheet.crop(164, 324, width, height);


    }
}
