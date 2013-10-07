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

import org.newdawn.slick.Image;

import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.util.Color;

/**
 * Texture implementation for Slick2D
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SlickTexture implements LayerTexture {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Image image;
	
	private ColorAdapter colorAdapter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SlickTexture(Image image) {
		this.image = image;
		this.colorAdapter = new ColorAdapter(Color.white);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void draw(float x, float y, float width, float height, Color filter) {
		image.draw(x, y, width, height, colorAdapter.set(filter));
	}

	@Override
	public void load() {
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class ColorAdapter extends org.newdawn.slick.Color {

		private static final long serialVersionUID = 1L;

		public ColorAdapter(Color color) {
			super(color.r, color.g, color.b, color.a);
		}
		
		public org.newdawn.slick.Color set(Color color) {
			r = color.r;
			g = color.g;
			b = color.b;
			a = color.a;
			return this;
		}
		
	}

}
