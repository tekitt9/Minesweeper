package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Board {
    private static final Texture mine = new Texture("mine_56.png");
    private static final Texture zero = new Texture("0_56.png");
    private GameObject[][] board;
    private final int x;
    private final int y;
    private boolean isBoardFilled = false;
    private boolean moved = false;

    public GameObject[][] getBoard() {
        return board;
    }

    public Board(int x, int y, int mines) {
        board = new GameObject[y][x];
        this.y = y;
        this.x = x;
        int rX;
        int rY;
        Random random = new Random();
        for (int i = 0; i < mines; i++) {
            rX = random.nextInt(x);
            rY = random.nextInt(y);
//            if (rX != rY) {
                if (board[rY][rX] == null) {
                    board[rY][rX] = new GameObject(mine);
                    board[rY][rX].setPosition(rX * 56, rY * 56);
                } else i--;
//            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new GameObject(calculateNumber(j, i));
                    board[i][j].setPosition(j * 56, i * 56);
                }
            }
        }
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                if (i % (y - 1) == 0 || j % (x - 1) == 0) {
//                    board[i][j].setHidden(false);
//                }
//            }
//        }
    }

    public Board(int x, int y, int mines, int firstX, int firstY) {
        board = new GameObject[y][x];
        this.y = y;
        this.x = x;
        int rX;
        int rY;
        Random random = new Random();
        for (int i = 0; i < mines; i++) {
            rX = random.nextInt(x);
            rY = random.nextInt(y);
//            if (rX != rY) {
                if (board[rY][rX] == null && rX != firstX && rY != firstY) {
                    board[rY][rX] = new GameObject(mine);
                    board[rY][rX].setPosition(rX * 56, rY * 56);
                } else i--;
//            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new GameObject(calculateNumber(j, i));
                    board[i][j].setPosition(j * 56, i * 56);
                }
            }
        }
    }
    public void draw(boolean uncover, Batch batch){
        if (!uncover) {
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    board[i][j].draw(batch);
                }
            }
        } else {
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    board[i][j].setHidden(false);
                    board[i][j].draw(batch);
                }
            }
        }
    }

    public void drawTest(boolean b){
        if (b) for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                System.out.print(go.getTexture().toString().substring(0, 2) + " ");
            }
            System.out.println();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int calculateNumber(int x, int y) {
        int i = 0;
        if (x > 0) {
            if (y > 0) {
                if (board[y - 1][x - 1] != null) {
                    if (board[y - 1][x - 1].getTexture().toString().equals(mine.toString())) i++;
                }
            }
            if (board[y][x - 1] != null) {
                if (board[y][x - 1].getTexture().toString().equals(mine.toString())) i++;
            }
            if (y < board.length - 1) {
                if (board[y + 1][x - 1] != null) {
                    if (board[y + 1][x - 1].getTexture().toString().equals(mine.toString())) i++;
                }
            }
        }
        if (x < board[0].length - 1) {
            if (y > 0) {
                if (board[y - 1][x + 1] != null) {
                    if (board[y - 1][x + 1].getTexture().toString().equals(mine.toString())) i++;
                }
            }
            if (board[y][x + 1] != null) {
                if (board[y][x + 1].getTexture().toString().equals(mine.toString())) i++;
            }
            if (y < board.length - 1) {
                if (board[y + 1][x + 1] != null) {
                    if (board[y + 1][x + 1].getTexture().toString().equals(mine.toString())) i++;
                }
            }
        }
        if (y > 0) {
            if (board[y - 1][x] != null) {
                if (board[y - 1][x].getTexture().toString().equals(mine.toString())) i++;
            }
        }
        if (y < board.length - 1) {
            if (board[y + 1][x] != null) {
                if (board[y + 1][x].getTexture().toString().equals(mine.toString())) i++;
            }
        }
        return i;
    }

    public int makeMove(int x, int y) {
        int i = 0;
        if (board[y][x].getTexture().toString().equals(mine.toString()) && !board[y][x].isMarked()) {
            if (moved) {
                return -1;
            } else {
                Random random = new Random();
                int rX;
                int rY;
                moved = true;
                do {
                    rX = random.nextInt(this.x - 1);
                    rY = random.nextInt(this.y - 1);
                } while (board[rY][rX].getTexture().toString().equals(mine.toString()) && rY == y && rX == x);
                board[rY][rX] = board[y][x];
//                if (rX > 0) {
//                    if (rY > 0) {
//                        if (board[y - 1][rX - 1].getTexture() != mine &&
//                                board[y - 1][rX - 1].isHidden()) {
//                            if (board[y - 1][rX - 1].getTexture() == zero) {
//                                System.out.println("WAAA");
//                                board[y - 1][x - 1].uncoverAdjacent(board, x - 1, y - 1);
//                            } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x - 1].setHidden(false);
//                        }
//                    }
//                    if (board[y][x - 1].getTexture() != mine &&
//                            board[y][x - 1].isHidden()) {
//                        if (board[y][x - 1].getTexture() == zero) {
//                            System.out.println("WAAA");
//                            board[y][x - 1].uncoverAdjacent(board, x - 1, y);
//                        } else System.out.println(board[y + 1][x].getTexture()); board[y][x - 1].setHidden(false);
//                    }
//                    if (y < maxY) {
//                        if (board[y + 1][x - 1].getTexture() != mine &&
//                                board[y + 1][x - 1].isHidden()) {
//                            if (board[y + 1][x - 1].getTexture() == zero) {
//                                System.out.println("WAAA");
//                                board[y + 1][x - 1].uncoverAdjacent(board, x - 1, y + 1);
//                            } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x - 1].setHidden(false);
//                        }
//                    }
//                }
//                if (x < maxX) {
//                    if (y > 0) {
//                        if (board[y - 1][x + 1].getTexture() != mine &&
//                                board[y - 1][x + 1].isHidden()) {
//                            if (board[y - 1][x + 1].getTexture() == zero) {
//                                System.out.println("WAAA");
//                                board[y - 1][x + 1].uncoverAdjacent(board, x + 1, y - 1);
//                            } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x + 1].setHidden(false);
//                        }
//                    }
//                    if (board[y][x + 1].getTexture() != mine &&
//                            board[y][x + 1].isHidden()) {
//                        if (board[y][x + 1].getTexture() == zero) {
//                            System.out.println("WAAA");
//                            board[y][x + 1].uncoverAdjacent(board, x + 1, y);
//                        } else System.out.println(board[y + 1][x].getTexture()); board[y][x + 1].setHidden(false);
//                    }
//                    if (y < maxY) {
//                        if (board[y + 1][x + 1].getTexture() != mine &&
//                                board[y + 1][x + 1].isHidden()) {
//                            if (board[y + 1][x + 1].getTexture() == zero) {
//                                System.out.println("WAAA");
//                                board[y + 1][x + 1].uncoverAdjacent(board, x + 1, y + 1);
//                            } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x + 1].setHidden(false);
//                        }
//                    }
//                }
//                if (y > 0) {
//                    if (board[y - 1][x].getTexture() != mine &&
//                            board[y - 1][x].isHidden()) {
//                        if (board[y - 1][x].getTexture() == zero) {
//                            System.out.println("WAAA");
//                            board[y - 1][x].uncoverAdjacent(board, x, y - 1);
//                        } else System.out.println(board[y + 1][x].getTexture()); board[y - 1][x].setHidden(false);
//                    }
//                }
//                if (y < maxY) {
//                    if (board[y + 1][x].getTexture() != mine &&
//                            board[y + 1][x].isHidden()) {
//                        if (board[y + 1][x].getTexture() == zero) {
//                            System.out.println("WAAA");
//                            board[y + 1][x].uncoverAdjacent(board, x, y + 1);
//                        } else System.out.println(board[y + 1][x].getTexture()); board[y + 1][x].setHidden(false);
//                    }
//                }
            }
        } else if (board[y][x].getTexture().toString().equals(zero.toString()) && !board[y][x].isMarked()) {
            board[y][x].uncoverAdjacent(board, x, y);
//            board[y][x].setHidden(false);
            System.out.println("1");
            moved = true;
        } else if (!board[y][x].isMarked()){
            board[y][x].setHidden(false);
            moved = true;
        }
        for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                if (!go.getTexture().toString().equals(mine.toString()) && go.isHidden()) {
                    i++;
                }
            }
        }
        return i;
    }

    public int makeMoveTest(int x, int y) {
        board[y][x].setHidden(false);
        int i = 0;
        for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                if (!go.getTexture().toString().equals(mine.toString()) && go.isHidden()) {
                    i++;
                }
            }
        }
        return i;
    }

    public int mineCount() {
        int i = 0;
        for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                if (go.getTexture().toString().equals(mine.toString())) i++;
            }
        }
        return i;
    }

    public boolean isBoardFilled() {
        return isBoardFilled;
    }

    public void setBoardFilled(boolean boardFilled) {
        isBoardFilled = boardFilled;
    }

    public void mark(int x, int y) {
        if (board[y][x].isHidden()) board[y][x].mark();
    }
}