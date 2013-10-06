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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import de.myreality.parallax.util.Bufferable;

/**
 * Singleton implementation of {@see TextureBufferer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
class SharedBufferer implements Bufferer {

	// ===========================================================
	// Constants
	// ===========================================================
	
	private static SharedBufferer instance;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<Bufferable> targets;
	
	private Map<Bufferable, Integer> loaded;
	
	private int buffer;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	static {
		instance = new SharedBufferer();
	}
	
	private SharedBufferer() {
		targets = new CopyOnWriteArrayList<Bufferable>();
		loaded = new HashMap<Bufferable, Integer>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public boolean isLoaded(Bufferable bufferable) {
		return loaded.containsKey(bufferable);
	}
	
	public static SharedBufferer getInstance() {
		return instance;
	}

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
		loaded.clear();
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
		int jobIndex = 0;
		for (Bufferable target : targets) {
			target.load();
			targets.remove(target);
			loaded.put(target, loaded.get(target) + 1);
			if (++jobIndex >= buffer) {
				break;
			}
		}
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
		if (!targets.contains(bufferable)) {
			targets.add(bufferable);
			
			if (!loaded.containsKey(bufferable)) {
				loaded.put(bufferable, 0);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void unload(Bufferable bufferable) {
		if (loaded.containsKey(bufferable)) {
			loaded.put(bufferable, loaded.get(bufferable) - 1);
			
			if (loaded.get(bufferable) < 1) {
				loaded.remove(bufferable);
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
