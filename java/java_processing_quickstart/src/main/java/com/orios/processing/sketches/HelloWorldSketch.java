package com.orios.processing.sketches;

import processing.core.PApplet;

/**
 *
 * @author obedrios
 */
public class HelloWorldSketch extends PApplet {
    
    public HelloWorldSketch(){        
    }
    
    
    @Override
    public void settings(){
        size(400, 300);
    }
    
    @Override
    public void setup(){
        
    }
    
    @Override
    public void draw(){
        
    }
    
    public static void main(String[] args) {        
        // PApplet.main("com.orios.processing.sketches.HelloWorldSketch");
        // PApplet.main(HelloWorldSketch.class);
        PApplet.runSketch(new String[]{""}, new HelloWorldSketch());
        
    }
    
}
