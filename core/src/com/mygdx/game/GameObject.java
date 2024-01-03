package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.TreeMap;

public class GameObject extends Sprite {
    private static final Texture def = new Texture("tile_64_2.png");
    private static final Texture mine = new Texture("mine_56.png");
    private static final Texture mark = new Texture("marked.png");
    private static final Texture zero = new Texture("0_56.png");

    public int getNumber() {
        return number;
    }

    public boolean isMarked() {
        return marked;
    }

    private boolean marked = false;
    private boolean isHidden = true;
    private int number;
    public GameObject(int number) {
        super(new Texture(String.format("%s_56.png", number)), 0, 0, 56, 56);
    }

    public GameObject(Texture texture) {
        super(texture, 0, 0, 56, 56);
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public void draw(Batch batch) {
        if (marked) {
            batch.draw(mark, getX(), getY());
        } else if (!isHidden) {
            super.draw(batch);
        } else {
            batch.draw(def, getX(), getY());
        }
    }

    public void uncoverAdjacent(GameObject[][] board, int x, int y) {
        System.out.println("Yay");
        setHidden(false);
        int i = 0;
        int maxX = board[0].length - 1;
        int maxY = board.length - 1;
        if (x > 0) {
            if (y > 0) {
                if (board[y - 1][x - 1].getTexture() != mine &&
                        board[y - 1][x - 1].isHidden()) {
                    if (board[y - 1][x - 1].getTexture().toString().equals(zero.toString())) {
                        System.out.println("WAAA");
                        board[y - 1][x - 1].uncoverAdjacent(board, x - 1, y - 1);
                    } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x - 1].setHidden(false);
                }
            }
            if (board[y][x - 1].getTexture() != mine &&
                    board[y][x - 1].isHidden()) {
                if (board[y][x - 1].getTexture().toString().equals(zero.toString())) {
                    System.out.println("WAAA");
                    board[y][x - 1].uncoverAdjacent(board, x - 1, y);
                } else System.out.println(board[y + 1][x].getTexture()); board[y][x - 1].setHidden(false);
            }
            if (y < maxY) {
                if (board[y + 1][x - 1].getTexture() != mine &&
                        board[y + 1][x - 1].isHidden()) {
                    if (board[y + 1][x - 1].getTexture().toString().equals(zero.toString())) {
                        System.out.println("WAAA");
                        board[y + 1][x - 1].uncoverAdjacent(board, x - 1, y + 1);
                    } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x - 1].setHidden(false);
                }
            }
        }
        if (x < maxX) {
            if (y > 0) {
                if (board[y - 1][x + 1].getTexture() != mine &&
                        board[y - 1][x + 1].isHidden()) {
                    if (board[y - 1][x + 1].getTexture().toString().equals(zero.toString())) {
                        System.out.println("WAAA");
                        board[y - 1][x + 1].uncoverAdjacent(board, x + 1, y - 1);
                    } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x + 1].setHidden(false);
                }
            }
            if (board[y][x + 1].getTexture() != mine &&
                    board[y][x + 1].isHidden()) {
                if (board[y][x + 1].getTexture().toString().equals(zero.toString())) {
                    System.out.println("WAAA");
                    board[y][x + 1].uncoverAdjacent(board, x + 1, y);
                } else System.out.println(board[y + 1][x].getTexture()); board[y][x + 1].setHidden(false);
            }
            if (y < maxY) {
                if (board[y + 1][x + 1].getTexture() != mine &&
                        board[y + 1][x + 1].isHidden()) {
                    if (board[y + 1][x + 1].getTexture().toString().equals(zero.toString())) {
                        System.out.println("WAAA");
                        board[y + 1][x + 1].uncoverAdjacent(board, x + 1, y + 1);
                    } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x + 1].setHidden(false);
                }
            }
        }
        if (y > 0) {
            if (board[y - 1][x].getTexture() != mine &&
                    board[y - 1][x].isHidden()) {
                if (board[y - 1][x].getTexture().toString().equals(zero.toString())) {
                    System.out.println("WAAA");
                    board[y - 1][x].uncoverAdjacent(board, x, y - 1);
                } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x].setHidden(false);
            }
        }
        if (y < maxY) {
            if (board[y + 1][x].getTexture() != mine &&
                    board[y + 1][x].isHidden()) {
                if (board[y + 1][x].getTexture().toString().equals(zero.toString())) {
                    System.out.println("WAAA");
                    board[y + 1][x].uncoverAdjacent(board, x, y + 1);
                } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x].setHidden(false);
            }
        }
    }

    public void mark() {
        marked = !marked;
    }
}