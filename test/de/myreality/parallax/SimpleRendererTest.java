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

package de.myreality.parallax;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.myreality.parallax.util.Color;
import de.myreality.parallax.util.Drawable;

/**
 * Test case for {@see SimpleRenderer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleRendererTest {

	// ===========================================================
	// Setup
	// ===========================================================
	
	SimpleRenderer renderer;
	
	ViewportMock viewport;
	
	final float WIDTH = 1000;
	
	final float HEIGHT = 2000;
	
	final float X = 5000;
	
	final float Y = 5000;

	@Before
	public void beforeTest() {
		viewport = new ViewportMock(X, Y);
		renderer = new SimpleRenderer(viewport);
	}

	// ===========================================================
	// Tests
	// ===========================================================

	@Test
	public void testUpdateAndRender() {
		renderer.add(new DrawableMock());
		renderer.add(new DrawableMock());
		renderer.add(new DrawableMock());
		
		renderer.updateAndRender(0f);
	}	
	
	class ViewportMock implements Viewport {
		
		private float x;
		
		private float y;
		
		public ViewportMock(float x, float y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public float getLeft() {
			return x;
		}

		@Override
		public float getRight() {
			return x + WIDTH;
		}

		@Override
		public float getTop() {
			return y;
		}

		@Override
		public float getBottom() {
			return y + HEIGHT;
		}
		
	}
	
	
	class DrawableMock implements Drawable {

		@Override
		public void draw(float x, float y, float width, float height,
				Color filter) {
			assertTrue("X has to be " + X, x == X);
			assertTrue("Y has to be " + Y, y == Y);
			assertTrue("Width has to be " + WIDTH, width == WIDTH);
			assertTrue("Height has to be " + HEIGHT, height == HEIGHT);
		}
		
	}

}
