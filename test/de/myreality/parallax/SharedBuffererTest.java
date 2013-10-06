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

import de.myreality.parallax.util.Bufferable;

/**
 * Test case for {@see SharedBufferer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SharedBuffererTest {

	// ===========================================================
	// Setup
	// ===========================================================

	SharedBufferer bufferer;

	BufferableMock mock1, mock2;

	@Before
	public void beforeTest() {
		bufferer = SharedBufferer.getInstance();
		bufferer.setBuffer(0);
		bufferer.clear();
		mock1 = new BufferableMock();
		mock2 = new BufferableMock();
	}

	// ===========================================================
	// Tests
	// ===========================================================

	@Test
	public void testIsLoaded() {

		bufferer.add(mock1);
		bufferer.add(mock2);

		assertTrue("First mock has to be in the buffer",
				bufferer.isLoaded(mock1));
		assertTrue("Second mock has to be in the buffer",
				bufferer.isLoaded(mock2));
		bufferer.clear();

		bufferer.setBuffer(1);

		bufferer.add(mock1);
		bufferer.add(mock2);

		assertFalse("First mock does not have to be in the buffer",
				bufferer.isLoaded(mock1));
		assertFalse("Second mock does not have to be in the buffer",
				bufferer.isLoaded(mock2));

		bufferer.update();

		assertTrue("First mock has to be in the buffer",
				bufferer.isLoaded(mock1));
		assertFalse("Second mock does not have to be in the buffer",
				bufferer.isLoaded(mock2));

		bufferer.update();

		assertTrue("First mock has to be in the buffer",
				bufferer.isLoaded(mock1));
		assertTrue("Second mock has to be in the buffer",
				bufferer.isLoaded(mock2));
	}

	@Test
	public void testClear() {
		bufferer.add(mock1);
		bufferer.add(mock2);
		assertTrue("First mock has to be in the buffer",
				bufferer.isLoaded(mock1));
		assertTrue("Second mock has to be in the buffer",
				bufferer.isLoaded(mock2));
		bufferer.clear();
		assertFalse("First mock does not have to be in the buffer",
				bufferer.isLoaded(mock1));
		assertFalse("Second mock does not have to be in the buffer",
				bufferer.isLoaded(mock2));
	}

	@Test
	public void testUnload() {

		final int MOCK1_COUNT = 10;
		final int MOCK2_COUNT = 5;

		for (int i = 0; i < MOCK1_COUNT; ++i) {
			bufferer.add(mock1);
		}

		for (int i = 0; i < MOCK2_COUNT; ++i) {
			bufferer.add(mock2);
		}

		assertTrue("First mock has to be in the buffer",
				bufferer.isLoaded(mock1));
		assertTrue("Second mock has to be in the buffer",
				bufferer.isLoaded(mock2));

		for (int i = 0; i < MOCK1_COUNT - 1; ++i) {
			bufferer.unload(mock1);
			assertTrue("First mock has to be in the buffer",
					bufferer.isLoaded(mock1));
		}

		for (int i = 0; i < MOCK2_COUNT - 1; ++i) {
			bufferer.unload(mock2);
			assertTrue("Second mock has to be in the buffer",
					bufferer.isLoaded(mock2));
		}

		bufferer.unload(mock1);
		bufferer.unload(mock2);

		assertFalse("First mock does not have to be in the buffer",
				bufferer.isLoaded(mock1));
		assertFalse("Second mock does not have to be in the buffer",
				bufferer.isLoaded(mock2));
	}

	class BufferableMock implements Bufferable {

		@Override
		public void load() {
			// DO NOTHING
		}

	}

}
