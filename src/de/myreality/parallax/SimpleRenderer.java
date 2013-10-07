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

import de.myreality.parallax.util.Drawable;
import de.myreality.parallax.util.Filterable;
import de.myreality.parallax.util.SimpleFilterable;

/**
 * Simple implementation of {@see Renderer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleRenderer implements Renderer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Viewport viewport;

	private List<Drawable> drawables;

	private Filterable filterable;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleRenderer(Viewport viewport, Filterable parent) {
		drawables = new ArrayList<Drawable>();
		this.filterable = parent;
		setViewport(viewport);
	}
	
	public SimpleRenderer(Viewport viewport) {
		this(viewport, new SimpleFilterable());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void add(Drawable drawable) {
		if (!drawables.contains(drawable)) {
			drawables.add(drawable);
		}
	}

	@Override
	public void clear() {
		drawables.clear();
	}

	@Override
	public void updateAndRender(float delta) {
		if (viewport != null) {
			for (Drawable drawable : drawables) {
				draw(drawable);
			}
		}
	}

	@Override
	public Viewport getViewport() {
		return viewport;
	}

	@Override
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void draw(Drawable drawable) {
		drawable.draw(viewport.getLeft(), viewport.getTop(),
				viewport.getRight() - viewport.getLeft(),
				viewport.getBottom() - viewport.getTop(),
				filterable.getFilter());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
