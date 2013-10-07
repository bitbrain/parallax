package de.myreality.parallax.slick;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.myreality.parallax.LayerConfig;
import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.ParallaxMapper;
import de.myreality.parallax.Viewport;

public class ParallaxSlickTest extends BasicGame {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ViewportImpl target;
	
	private ParallaxMapper mapper;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ParallaxSlickTest(String title) {
		super(title);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		mapper.updateAndRender(1f);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Image fog = new Image("res/fog.png");
		target = new ViewportImpl(gc.getWidth(), gc.getHeight());
		LayerTexture texture = new SlickTexture(fog);
		LayerConfig config = new LayerConfig(texture);
		
		mapper = new ParallaxMapper(target);
		
		mapper.add(config);
		config.setZIndex(5f);
		mapper.add(config);
		config.setZIndex(10f);
		mapper.add(config);
		
		LayerTexture preprocessed = new PreprocessedTexture(100, 100, new SlickTextureProcessor() {

			@Override
			public void process(Graphics g) {
				//g.setColor(Color.red);
				//g.fillRect(0, 0, 20, 20);
			}
			
		});
		
		LayerConfig config2 = new LayerConfig(preprocessed);
		mapper.add(config2);
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		target.setX(gc.getInput().getMouseX());
		target.setY(gc.getInput().getMouseY());
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new ParallaxSlickTest("Parallax - Slick Test"));
		app.setDisplayMode(800, 600, false);
		app.start();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class ViewportImpl implements Viewport {
		
		private float x, y;
		int width, height;
		
		public ViewportImpl(int width, int height) {
			this.x = this.y = 0;
			this.width = width;
			this.height = height;
		}

		@Override
		public float getLeft() {
			return x;
		}

		@Override
		public float getRight() {
			return x + width;
		}

		@Override
		public float getTop() {
			return y;
		}

		@Override
		public float getBottom() {
			return y + height;
		}
		
		public void setX(float x) {
			this.x = x;
		}
		
		public void setY(float y) {
			this.y = y;
		}
		
	}

}
