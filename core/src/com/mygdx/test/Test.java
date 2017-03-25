package com.mygdx.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import javax.swing.JOptionPane;

public class Test extends Game {

    private SpriteBatch batch;
    private Texture texture2, texture1, verticalBorder, horizontalBorder, tlo;
    private BitmapFont font;
    private GameObject dynamite, fire, bottomVerBor, topVerBor, rightHorBor, leftHorBor;
    private OrthographicCamera camera;
    private float timeHelper;
    private long sec ;
    private int wygrane = 0;
    private Music music;
    private Sound sound;

    @Override
    public void create() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
       
        sound = Gdx.audio.newSound(Gdx.files.internal("sound.ogg"));
        
        camera = new OrthographicCamera(1000, 800);
        texture1 = new Texture("fire.png");
        texture2 = new Texture("dynamit.png");
        verticalBorder = new Texture("verticalBorder.png");
        horizontalBorder = new Texture("horizontalBorder.png");
        tlo = new Texture("tlo.png");
        
        batch = new SpriteBatch();
        font = new BitmapFont();
        
        dynamite = new GameObject(texture2);
        dynamite.x = 50;
        dynamite.y = 50;
        dynamite.width = dynamite.getTexture().getWidth();
        dynamite.height = dynamite.getTexture().getHeight();
        
        fire = new GameObject(texture1);
        fire.x = 500;
        fire.y = 500;
        fire.width = fire.getTexture().getWidth();
        fire.height = fire.getTexture().getHeight();
        
        bottomVerBor = new GameObject(verticalBorder);
        bottomVerBor.x = 0;
        bottomVerBor.y = 0;
        bottomVerBor.width = bottomVerBor.getTexture().getWidth();
        bottomVerBor.height = bottomVerBor.getTexture().getHeight();
        
        topVerBor = new GameObject(verticalBorder);
        topVerBor.x = 0;
        topVerBor.y = 800;
        topVerBor.width = topVerBor.getTexture().getWidth();
        topVerBor.height = topVerBor.getTexture().getHeight();
        
        rightHorBor = new GameObject(horizontalBorder);
        rightHorBor.x = 1000;
        rightHorBor.y = 0;
        rightHorBor.width = rightHorBor.getTexture().getWidth();
        rightHorBor.height = rightHorBor.getTexture().getHeight();
        
        leftHorBor = new GameObject(horizontalBorder);
        leftHorBor.x = 0;
        leftHorBor.y = 0;
        leftHorBor.width = leftHorBor.getTexture().getWidth();
        leftHorBor.height = leftHorBor.getTexture().getHeight();
        
        sec = System.currentTimeMillis();
    }

    @Override
    public void render() {
        update();

        //Gdx.gl.glClearColor(135/255f, 15/255f, 15/255f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(tlo, 0, 0);
        batch.draw(bottomVerBor.getTexture(), bottomVerBor.x , bottomVerBor.y);
        batch.draw(topVerBor.getTexture(), topVerBor.x , topVerBor.y);
        batch.draw(rightHorBor.getTexture(), rightHorBor.x, rightHorBor.y);
        batch.draw(leftHorBor.getTexture(), leftHorBor.x, leftHorBor.y);
        batch.draw(dynamite.getTexture(), dynamite.x, dynamite.y);
        batch.draw(fire.getTexture(), fire.x, fire.y);
        font.draw(batch, "Wygrane  " + wygrane , 350, 810);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture1.dispose();
        texture2.dispose();
        verticalBorder.dispose();
        horizontalBorder.dispose();
        font.dispose();
        music.dispose();
        sound.dispose();
    }

    private void update() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        camera.position.set(dynamite.x + dynamite.width/2, dynamite.y + dynamite.height/2, 0);
        
        if(dynamite.overlaps(bottomVerBor)){
            dynamite.y = 10;
        }
        if(dynamite.overlaps(topVerBor)){
            dynamite.y = 700;
        }
        if(dynamite.overlaps(rightHorBor)){
            dynamite.x = 900;
        }
        if(dynamite.overlaps(leftHorBor)){
            dynamite.x = 10;
        }
        
        if(dynamite.overlaps(fire)){
            sound.play();
            
            dynamite.x = MathUtils.random(910);;
            dynamite.y = MathUtils.random(710);;
            
            fire.x = MathUtils.random(910);;
            fire.y = MathUtils.random(710);;
            if(wygrane>=29){
                sec = System.currentTimeMillis() - sec;
                JOptionPane.showMessageDialog(null, sec + " milisekund");
                Gdx.app.exit();
                
            }
            wygrane++;
            
        }
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
            camera.zoom += 0.02f;
        }
        if (Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
            camera.zoom -= 0.02f;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            dynamite.y += 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            dynamite.x -= 500 * Gdx.graphics.getDeltaTime();;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            dynamite.y -= 500 * Gdx.graphics.getDeltaTime();;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            dynamite.x += 500 * Gdx.graphics.getDeltaTime();;
        }
        
        timeHelper += Gdx.graphics.getDeltaTime();
        if(timeHelper > 0.02f){
            camera.rotate(0.20f);
            timeHelper = 0;
            
        }
    }


}
