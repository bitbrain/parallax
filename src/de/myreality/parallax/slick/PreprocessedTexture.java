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

package de.myreality.parallax.slick;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.util.Color;

/**
 * Texture implementation for Slick2D
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class PreprocessedTexture implements LayerTexture {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final int DEFAULT_WIDTH = 128;

	public static final int DEFAULT_HEIGHT = 128;

	// ===========================================================
	// Fields
	// ===========================================================

	private SlickTexture texture;

	private SlickTextureProcessor preprocessor;

	private Image buffer;

	private int width, height;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PreprocessedTexture(int width, int height,SlickTextureProcessor processor) {

		this.preprocessor = processor;
		this.width = width;
		this.height = height;
	}

	public PreprocessedTexture(SlickTextureProcessor processor) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, processor);
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
		try {
			buffer = new Image(width, height);
			Graphics g = buffer.getGraphics();
			g.clear();
			preprocessor.process(g);
			g.flush();
			texture = new SlickTexture(buffer);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {
		this.texture = null;
		if (buffer != null) {
			try {
				buffer.destroy();
				buffer = null;
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
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
