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

import de.myreality.parallax.util.Color;

/**
 * Simple implementation of {@SimpleLayer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
class SimpleLayer implements Layer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private LayerConfig config;

	private Buffer buffer;

	private float localX;

	private float localY;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleLayer(LayerConfig config, Buffer buffer) {
		this.config = config;
		this.buffer = buffer;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void update(float delta) {
		localX -= config.getVelocityX() * delta;
		localY -= config.getVelocityY() * delta;
	}

	@Override
	public void draw(float x, float y, float width, float height, Color filter) {
		
		int boundableX = (int) Math.ceil(x);
		int boundableY = (int) Math.ceil(y);
		
		LayerTexture texture = config.getTexture();
		
		if (texture != null && buffer.isLoaded(texture)) {

			for (int localX = getStartX(x) + boundableX; localX < width
					+ config.getTileWidth() + boundableX; localX += config.getTileWidth()) {
				for (int localY = getStartY(y) + boundableY; localY < height
						+ config.getTileHeight() + boundableY; localY += config.getTileHeight()) {
					
					texture.draw(localX + getXClip(x), localY + getYClip(y), config.getTileWidth(),
							config.getTileHeight(), config.getFilter());
				}
			}
		
		}
	}

	@Override
	public void setConfig(LayerConfig layerConfig) {
		this.config = layerConfig;
	}

	@Override
	public LayerConfig getConfig() {
		return config;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private float getTargetX(float focusX) {

		return (float) (Math.floor(-focusX + localX) / config
					.getZIndex());
	}

	private float getTargetY(float focusY) {
			return (float) (Math.floor(-focusY + localY) / config
					.getZIndex());
	}

	private int getStartX(float focusX) {
		int startX = 0;
		if (getTargetX(focusX) > 0) {
			startX = -config.getTileWidth();
		}
		return startX;
	}

	private int getStartY(float focusY) {
		int startY = 0;
		if (getTargetY(focusY) > 0) {
			startY = -config.getTileHeight();
		}
		return startY;
	}

	private int getXClip(float focusX) {
		return (int) (getTargetX(focusX) % config.getTileWidth());
	}

	private int getYClip(float focusY) {
		return (int) (getTargetY(focusY) % config.getTileHeight());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
