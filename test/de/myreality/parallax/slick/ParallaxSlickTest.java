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
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.pushTransform();
		g.translate(-target.getLeft(), -target.getTop());
		mapper.updateAndRender(1f);
		g.popTransform();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		Image fog = new Image("res/fog.png");
		Image space = new Image("res/space-far.png");
		Image gradient = new Image("res/black-gradient.png");
		target = new ViewportImpl(gc.getWidth(), gc.getHeight());
		LayerTexture texSpace = new SlickTexture(space);
		LayerTexture fogTexture = new SlickTexture(fog);
		LayerConfig config = new LayerConfig(texSpace);
	
		mapper = new ParallaxMapper(target);
		config.setFilter(0.5f, 0.1f, 0.6f, 0.1f);
		config.setTexture(texSpace);
		config.setZIndex(15f);
		mapper.add(config);
		
		int starLayers = 8;
		
		for (int i = 0; i < starLayers; ++i) {
			LayerTexture texture = new PreprocessedTexture(400, 400, new StarGenerator(gradient));
			config = new LayerConfig(texture);
			config.setZIndex((float) (Math.pow(i, 1.2) + 5f));
			mapper.add(config);
		}
		
		int fogLayers = 5;
		float veloX = 1f;
		float veloY = 2f;
		
		for (int i = 0; i < fogLayers; ++i) {
			config = new LayerConfig(fogTexture);
			config.setZIndex((float) (Math.sin(i) + 5));
			config.setVelocity(veloX, veloY);
			mapper.add(config);
		}
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
	
	class StarGenerator implements SlickTextureProcessor {
		
		private Image gradient;
		
		public StarGenerator(Image gradient) {
			this.gradient = gradient;
		}

		@Override
		public void process(Graphics g) {
			int starAmount = (int) (3 * 100);

			for (int i = 0; i < starAmount; ++i) {
				drawStar((float) (400 * Math.random()),
						(float) (400 * Math.random()), g);
			}
		}
		
		
		private void drawStar(float x, float y, Graphics g) {
			Color color = new Color(255, 255, 255, 255);
			float size = 1f;
			if (Math.random() < 0.03f) {
				size += 1.3f;
			} else if (Math.random() < 0.05f) {
				size += 1.4f;
			} else if (Math.random() < 0.08f) {
				size += 1.1f;
			}
			color.r = (float) (Math.random() * 0.5f + 0.6f);
			color.g = (float) (Math.random() * 0.5f + 0.6f);
			color.b = (float) (Math.random() * 0.5f + 0.6f);
			g.setDrawMode(Graphics.MODE_ALPHA_MAP);
			Image blendImage = gradient;
			blendImage.draw(x - size / 2f, y - size / 2f, size * 2f, size * 2f);
			g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
			g.setColor(color);
			g.fillRect(x - size / 2f, y - size / 2f, size * 2f, size * 2f);

			color.a = 1.0f;
		}

	}

}
