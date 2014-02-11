![parallax](parallax.png)

Library which provides parallax scrolling support for Java 2D libraries such as LibGDX and Slick2D.

## Features

This library is able to..

* generate custom (pre-processed) textures on runtime
* display parallax effects
* customize your backgrounds with parallax patterns

#Download

You can download the library [here](https://www.dropbox.com/s/wt8m9w53obixzbc/parallax.jar).

## Getting started

To create a new parallax effect, you need a so called ```Viewport``` and ```ParallaxMapper```. The ```Viewport``` is an required interface implementation of your field of view (FOV). You can implement for instance the interface in your camera class:

```java
public class MyCamera extends Camera implements Viewport {

   @Override
   public float getLeft() {
      return getX();
   }

   @Override
   public float getTop() {
      return getY();
   }

   @Override
   public float getRight() {
      return getLeft() + getWidth();
   }

   @Override
   public float getBottom() {
      return getTop() + getHeight();
   }
}
```

Afterwards you are able to create your parallax mapper:

```java
MyCamera camera = new MyCamera();
ParallaxMapper mapper = new ParallaxMapper(camera);
```

### Slick2D remarks

The parallax system works only with matrix translation. Therefore you need to translate the matrix by the current camera position:

```java
// Code in the render method
g.pushMatrix();
g.translate(camera.getX(), camera.getY();
mapper.render();
g.popMatrix();
```

### LibGDX remarks

In order to use the parallax effect properly, you have to define a ```SpriteBatch``` instance. You need to wrap *begin* and *end* around the render method:

```java
batch.begin();
mapper.render();
batch.end();
```

## Adding layers

To add a layer, you need a layer configuration. This configuration requires a ```LayerTexture``` in order to draw the parallax effect properly. For [LibGDX](http://libgdx.badlogicgames.com/) and [Slick2D](http://slick.ninjacave.com/) exist already implementation of those textures.

```java
Image image = new Image("test.png"); // Slick2D
LayerTexture texture = new SlickTexture(image); // Slick2D
LayerConfig config = new LayerConfig(texture);
mapper.add(10.0f, config);
```

That's all! You can define a so called *z index* in order to specify the distance of the layer. 

### Preprocessed Textures

It is also possible to create textures on your own on runtime! For instance you want to generate some kind of random star field without using a pre-defined image texture. You need to implement ```TextureProcessor``` due to generate a texture on your own:

```
TexturePreprocessor processor = new SlickTextureProcessor({

   @Override
   public void process(Graphics g) {
      g.setColor(Color.red);
      g.fillRect(0, 0, 10, 10);
   }
});

LayerTexture customTexture = new PreprocessedTexture(64, 64, processor);
config.setTexture(customTexture);
mapper.add(20.0f, config); 
``` 

As you can see it is pretty easy to extend your parallax effect by custom textures.
