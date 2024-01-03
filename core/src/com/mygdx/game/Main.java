package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Player player;
	float speed = 5f;
	boolean isHidden = true;
	Board board;
	Sound sound;

	@Override
	public void create() {
		batch = new SpriteBatch();
		player = new Player(0, 0);
		player.setPosition(100, 100);
		board = new Board(Gdx.graphics.getWidth() / 56, Gdx.graphics.getHeight() / 56, 30);
		sound = Gdx.audio.newSound(Gdx.files.internal("lose.mp3"));
		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				int x = screenX / 56;
				int y = screenY / 56;
//				Vector3 input = new Vector3(screenX, screenY, 0);
				System.out.printf("%d %d %d %d%n",x, y, pointer, button);
				if (button == 0) {
					int gameState = board.makeMove(x,board.getY() - y - 1);
					switch (gameState) {
						case 0 -> {
							Gdx.audio.newSound(Gdx.files.internal("win.mp3")).play();
							long time = System.currentTimeMillis() + 1700;
							while (time > System.currentTimeMillis()) {}
							Gdx.app.exit();
						}
						case -1 -> {
							sound.play();
							long time = System.currentTimeMillis() + 1500;
							while (time > System.currentTimeMillis()) {}
							Gdx.app.exit();
						}
						default -> {
							System.out.println(gameState + " mines left!");
						}
					}
				} else if (button == 1) {
					board.mark(x, board.getY() - y - 1);
				}
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(float amountX, float amountY) {
				return false;
			}
		});
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		board.draw(false, batch);
		board.drawTest(isHidden);
		isHidden = false;
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		sprite.getTexture().dispose();
	}
}