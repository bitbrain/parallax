package de.myreality.parallax.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
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
	
	static final int WIDTH  = 800;
    static final int HEIGHT = 600;

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
		
		batch = new SpriteBatch();		
		
		camera = new ParallaxCamera(WIDTH, HEIGHT);   
        
        mapper = new ParallaxMapper(camera);
		LayerTexture texture = new PreprocessedTexture(128, 128, batch, new BlockCreator());
		LayerConfig config = new LayerConfig(texture);
		config.setZIndex(1f);
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
		
		int deltaX = (int)(Gdx.graphics.getWidth() / 2f);
		int deltaY = (int)(Gdx.graphics.getHeight() / 2f);
		
		camera.position.x = Gdx.input.getX();
        camera.position.y = Gdx.input.getY();
	
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.zoom *= 1.001;
        camera.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
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
		cfg.width = WIDTH;
		cfg.height = HEIGHT;

		new LwjglApplication(new ParallaxGdxTest(), cfg);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	class BlockCreator implements GdxTextureProcessor {

		@Override
		public void process(Pixmap map) {
			
			map.setColor(1f, 1f, 1f, 0.5f);
			
			int starCount = 20;
			
			for (int i = 0; i < starCount; ++i) {
				int x = (int) (Math.random() * 128);
				int y = (int) (Math.random() * 128);
				
				map.drawCircle(x, y, 1);
			}
		}

	}

	class ParallaxCamera extends OrthographicCamera implements Viewport {

		
		
		public ParallaxCamera(float viewportWidth, float viewportHeight) {
			super(viewportWidth, viewportHeight);
		}

		@Override
		public float getLeft() {
			return this.position.x;
		}

		@Override
		public float getRight() {
			return getLeft() + this.viewportWidth * zoom;
		}

		@Override
		public float getTop() {
			return this.position.y;
		}

		@Override
		public float getBottom() {
			return getTop() + this.viewportHeight * zoom;
		}

	}

}
