package de.myreality.parallax.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.parallax.LayerConfig;
import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.ParallaxMapper;
import de.myreality.parallax.Viewport;
import de.myreality.parallax.libgdx.GdxTexture;
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
	private Texture gradient;

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
        
        Texture space = new Texture("res/space-far.png");
        gradient = new Texture("res/black-gradient.png");
        Texture fog = new Texture("res/fog.png");
        
        final int starLayers = 8;
		
		for (int i = 0; i < starLayers; ++i) {
			LayerTexture texture = new PreprocessedTexture(128, 128, batch, new BlockCreator());
			LayerConfig config = new LayerConfig(texture);
			config.setZIndex((float) (Math.pow(i, 1.2) + 5f));
			mapper.add(config);
		}
		

		int fogLayers = 5;
		float veloX = 1f;
		float veloY = 2f;
		
		LayerTexture fogTexture = new GdxTexture(fog, batch);
		
		for (int i = 0; i < fogLayers; ++i) {
			LayerConfig config = new LayerConfig(fogTexture);
			config.setZIndex((float) (Math.sin(i) + 5));
			config.setVelocity(veloX, veloY);
			mapper.add(config);
		}
		
		// Add the background
		LayerTexture backgroundTexture = new GdxTexture(space, batch);
		LayerConfig config = new LayerConfig(backgroundTexture);
		config.setZIndex(20f);
		config.setFilter(0.5f, 0.1f, 0.6f, 0.1f);
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
		
		camera.position.x = Gdx.input.getX();
        camera.position.y = Gdx.input.getY();
	
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapper.updateAndRender(Gdx.graphics.getDeltaTime() + 1f);
        batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		mapper.clear();
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
		cfg.vSyncEnabled = true;
		cfg.samples = 8;
		
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
				
				map.fillRectangle(x, y, 2, 2);
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
