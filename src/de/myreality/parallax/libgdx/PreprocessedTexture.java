package de.myreality.parallax.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.util.Color;

public class PreprocessedTexture implements LayerTexture {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private GdxTexture texture;
	
	private GdxTextureProcessor processor;
	
	private int width, height;
	
	private SpriteBatch batch;
	
	private Texture rawTexture;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PreprocessedTexture(int width, int height, SpriteBatch batch, GdxTextureProcessor processor) {
		this.processor = processor;
		this.width = width;
		this.height = height;
		this.batch = batch;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void draw(float x, float y, float width, float height, Color filter) {
		if (texture != null) {
			texture.draw(x, y, width, height, filter);
		}
	}

	@Override
	public void load() {
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		processor.process(map);
		rawTexture = new Texture(map);
		map.dispose();
		Gdx.gl.glDisable(GL10.GL_BLEND);
		texture = new GdxTexture(rawTexture, batch);
	}

	@Override
	public void dispose() {
		texture.dispose();
		rawTexture.dispose();
		texture = null;
		rawTexture = null;
	}

	@Override
	public int getWidth() {
		if (texture != null) {
			return texture.getWidth();
		} else {
			return 0;
		}
	}

	@Override
	public int getHeight() {
		if (texture != null) {
			return texture.getHeight();
		} else {
			return 0;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
