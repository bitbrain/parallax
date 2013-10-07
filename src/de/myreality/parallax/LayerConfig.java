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

import de.myreality.parallax.util.Filterable;
import de.myreality.parallax.util.SimpleFilterable;

/**
 * Configuration which holds all configuration for a specific layer
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class LayerConfig extends SimpleFilterable implements Filterable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float velocityX;
	
	private float velocityY;
	
	private LayerTexture texture;

	private float index;
	
	private int tileWidth, tileHeight;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public LayerConfig(float index, LayerTexture texture, float velocityX, float velocityY) {
		setVelocity(velocityX, velocityY);
		setTexture(texture);
	}
	
	public LayerConfig(float index, LayerTexture texture) {
		this(index, texture, 0f, 0f);
	}
	
	public LayerConfig(LayerTexture texture) {
		this(0, texture);
	}
	
	public LayerConfig(LayerConfig config) {
		this.velocityX = config.velocityX;
		this.velocityY = config.velocityY;
		this.index = config.index;
		this.tileWidth = config.tileWidth;
		this.tileHeight = config.tileHeight;
		this.texture = config.texture;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public LayerConfig setVelocity(float velocityX, float velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		return this;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}
	
	public LayerConfig setTexture(LayerTexture texture) {
		
		if (texture != null) {
			this.texture = texture;
			tileWidth = 0;
			tileHeight = 0;
		}
		
		return this;
	}
	
	public LayerTexture getTexture() {
		return texture;
	}
	
	public LayerConfig setZIndex(float index) {
		this.index = index;
		return this;
	}
	
	public float getZIndex() {
		return index;
	}
	
	public LayerConfig setTileWidth(int width) {
		this.tileWidth = width;
		return this;
	}
	
	public LayerConfig setTileHeight(int height) {
		this.tileHeight = height;
		return this;
	}
	
	public int getTileWidth() {
		if (tileWidth < 1) {
			return texture.getWidth();
		} else {
			return tileWidth;
		}
	}
	
	public int getTileHeight() {
		if (tileHeight < 1) {
			return texture.getHeight();
		} else {
			return tileHeight;
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
