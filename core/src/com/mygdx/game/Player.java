package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
    public Player() {
        super(new Texture("square.png"));
    }

    public Player(int srcX, int srcY) {
        super(new Texture("square.png"), srcX, srcY, 7, 7);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void move(int i) {
        if (Gdx.input.isButtonPressed(1)) {
            translateX(i);
        } else if (Gdx.input.isButtonPressed(0)) {
            translateX(-i);
        }
    }
}



//package com.mygdx.game;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//
//public class Player extends GameObject {
//    private final Texture img;
//
//    public Player() {
//        img = new Texture("square.png");
//    }
//
//    public Texture getImg() {
//        return img;
//    }
//}