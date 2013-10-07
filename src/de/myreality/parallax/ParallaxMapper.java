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

import java.util.ArrayList;
import java.util.List;

import de.myreality.parallax.util.SimpleFilterable;

/**
 * Mapper which contains all layers and does the main logic
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ParallaxMapper extends SimpleFilterable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Buffer buffer;
	
	private LayerFactory factory;
	
	private Renderer renderer;
	
	private List<Layer> layers;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ParallaxMapper(Viewport viewport) {
		layers = new ArrayList<Layer>();
		this.buffer = SharedBuffer.getInstance();
		this.factory = new SimpleLayerFactory();
		this.renderer = new SimpleRenderer(viewport, this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void add(LayerConfig config) {
		Layer layer = factory.create(config, buffer);
		layers.add(layer);
		renderer.add(layer);
	}
	
	public void clear() {
		
		for (Layer layer : layers) {
			layer.unload();
		}
		
		layers.clear();		
		renderer.clear();
	}
	
	public void updateAndRender(float delta) {
		buffer.update();
		renderer.updateAndRender(delta);
	}
	
	public Viewport getViewport() {
		return renderer.getViewport();
	}
	
	public void setViewport(Viewport viewport) {
		renderer.setViewport(viewport);
	}
	
	public static void setBuffering(int buffering) {
		SharedBuffer.getInstance().setBuffer(buffering);
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
