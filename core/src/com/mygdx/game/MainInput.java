package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainInput extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture("square.png")); // Replace "image.png" with your image file
		sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);

		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Vector3 input = new Vector3(screenX, screenY, 0);
				if (sprite.getBoundingRectangle().contains(input.x, input.y)) {
					System.out.println("Sprite was clicked!");
				}
				return false;
			}

			// Implement other InputProcessor methods as needed
			@Override public boolean keyDown(int keycode) { return false; }
			@Override public boolean keyUp(int keycode) { return false; }
			@Override public boolean keyTyped(char character) { return false; }
			@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

			@Override
			public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
			@Override public boolean mouseMoved(int screenX, int screenY) { return false; }

			@Override
			public boolean scrolled(float amountX, float amountY) {
				return false;
			}

			public boolean scrolled(int amount) { return false; }
		});
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		sprite.getTexture().dispose();
	}
}
