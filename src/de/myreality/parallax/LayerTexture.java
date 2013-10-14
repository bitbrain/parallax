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

import de.myreality.parallax.util.Bufferable;
import de.myreality.parallax.util.Drawable;

/**
 * Texture which is used by a layer to draw itself
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface LayerTexture extends Drawable, Bufferable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the width of this texture
	 * 
	 * @return current width
	 */
	int getWidth();
	
	/**
	 * Returns the height of this texture
	 * 
	 * @return current height
	 */
	int getHeight();

}
