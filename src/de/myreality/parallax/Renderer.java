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

import de.myreality.parallax.util.Clearable;
import de.myreality.parallax.util.Drawable;

/**
 * Renderer which provides rendering functionality in order to
 * draw objects of type {@see Drawable}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
interface Renderer extends Clearable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Updates and renders all containing objects of type {@see Drawable}
	 * 
	 * @param delta
	 */
	void updateAndRender(float delta);
	
	/**
	 * Returns the current {@see Viewport} of this renderer
	 *
	 * @return current {@see Viewport}
	 */
	Viewport getViewport();
	
	/**
	 * Sets a new viewport to this renderer
	 * 
	 * @param viewport new viewport to set
	 */
	void setViewport(Viewport viewport);
	
	/**
	 * Adds a new drawable to this renderer instance. As higher the value
	 * as earlier will the object be processed by this renderer.
	 * 
	 * @param drawable
	 */
	void add(float priority, Drawable drawable);

}
