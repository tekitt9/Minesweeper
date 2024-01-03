package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TestBoard {
    private GameObject[][] board = new GameObject[16][10];
    public TestBoard() {
    }

    public GameObject[][] getBoard() {
        return board;
    }
}