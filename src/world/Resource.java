package world;

import engine.Updatable;
import render.Drawable;

public abstract class Resource implements Updatable, Drawable {
    //Mesh mesh;
    String texture;
    
    public Resource() {
        
    }
    
    public void update(float dt) {
        
    }
    
    public void render() {
        
    }
}