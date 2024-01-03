package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMessage extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont font;
    int variable;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        variable = 10; // Replace this with your actual variable
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (variable == 10) { // Replace 10 with the value you're comparing to
            font.draw(batch, "Variable is equal to 10!", 100, 100);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
