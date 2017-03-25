/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Kamil
 */
public class Menu extends Game {

    private SpriteBatch batch;
    private Texture tex;
    private Button playerButton;

    protected Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        tex = new Texture("badlogic.jpg");
        initPlayerButton();
    }

    

    private void initPlayerButton() {
        playerButton = new Button(new ButtonStyle());
        playerButton.setWidth(300);
        playerButton.setHeight(70);
        playerButton.setX(400);
        playerButton.setY(400);
        playerButton.setDebug(true);

        //stage.addActor(playerButton);

        playerButton.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                    int pointer, int button) {


                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(tex, 0, 0);
        batch.end();
    
    }

}

 
