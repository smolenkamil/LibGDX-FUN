/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kamil
 */
public class GameObject extends Rectangle{
    
    private Texture texture;
    
    public GameObject(Texture texture){
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
    
    
}
