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

package de.myreality.parallax.util;

/**
 * Provides color filtering
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface Filterable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Sets a new filter
	 * 
	 * @param r new red value
	 * @param g new green value
	 * @param b new blue value
	 * @param a new alpha value
	 */
	void setFilter(float r, float g, float b, float a);
	
	/**
	 * Sets a new filter
	 * 
	 * @param filter new filter
	 */
	void setFilter(Color filter);
	
	/**
	 * Returns the current filter of this object
	 * 
	 * @return current filter (default is #fff)
	 */
	Color getFilter();

}
