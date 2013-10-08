package de.myreality.parallax.gdx;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.parallax.LayerConfig;
import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.ParallaxMapper;
import de.myreality.parallax.Viewport;
import de.myreality.parallax.libgdx.GdxTextureProcessor;
import de.myreality.parallax.libgdx.PreprocessedTexture;

public class ParallaxGdxTest extends Game {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ParallaxMapper mapper;

	private ParallaxCamera camera;
	
	private SpriteBatch batch;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void create() {
		camera = new ParallaxCamera();
		mapper = new ParallaxMapper(camera);
		batch = new SpriteBatch();
		LayerTexture texture = new PreprocessedTexture(100, 100, batch, new BlockCreator());
		LayerConfig config = new LayerConfig(texture);
		mapper.add(config);
		config.setZIndex(3f);
		mapper.add(config);
		config.setZIndex(10f);
		mapper.add(config);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;

		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		super.render();

		Gdx.gl.glClearColor(0f, 0, 0, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		camera.position.x = Gdx.input.getX();
		camera.position.y = -Gdx.input.getY();
		
		camera.update();
		batch.begin();		
		mapper.updateAndRender(Gdx.graphics.getDeltaTime());
		batch.end();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Parallax - LibGDX Test";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;

		new LwjglApplication(new ParallaxGdxTest(), cfg);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	class BlockCreator implements GdxTextureProcessor {

		@Override
		public void process(Pixmap map) {
			map.setColor(1f, 0, 0, 1f);
			map.drawLine(0, 0, 20, 20);
		}

	}

	class ParallaxCamera extends OrthographicCamera implements Viewport {

		@Override
		public float getLeft() {
			return this.position.x;
		}

		@Override
		public float getRight() {
			return getLeft() + this.viewportWidth;
		}

		@Override
		public float getTop() {
			return this.position.y;
		}

		@Override
		public float getBottom() {
			return getTop() + this.viewportHeight;
		}

	}

}
