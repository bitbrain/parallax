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

import java.util.LinkedList;

import de.myreality.parallax.util.Bufferable;

/**
 * Simple implementation of {@see TextureBufferer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
class SimpleTextureBufferer implements TextureBufferer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private LinkedList<Bufferable> targets;
	
	private int buffer;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleTextureBufferer() {
		targets = new LinkedList<Bufferable>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.util.Clearable#clear()
	 */
	@Override
	public void clear() {
		targets.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.TextureBufferer#setBuffer(int)
	 */
	@Override
	public void setBuffer(int buffer) {
		if (buffer > 0) {
			this.buffer = buffer;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.TextureBufferer#getBuffer()
	 */
	@Override
	public int getBuffer() {
		return buffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.TextureBufferer#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.parallax.TextureBufferer#add(de.myreality.parallax.util.
	 * Bufferable)
	 */
	@Override
	public void add(Bufferable bufferable) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
