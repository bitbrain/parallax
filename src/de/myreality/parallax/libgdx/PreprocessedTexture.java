/* Library which provides parallax scrolling support for Java 2D libraries such as LibGDX and Slick2D.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package de.myreality.parallax.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.util.Color;

/**
 * Pre-processed LibGDX implementation of {@see LayerTexture} to 
 * provide pre-processed textures for layers at runtime.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
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
