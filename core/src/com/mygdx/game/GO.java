package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GO extends Sprite {
    public GO(int srcX, int srcY) {
        super(new Texture("mine.png"), srcX, srcY, 8, 8);
    }
}