package world;

import engine.Updatable;
import render.Drawable;

/**
 * Abstract class GamePiece - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class GamePiece implements Updatable, Drawable
{
    float x;
    float y;
    float z;
    
    //Mesh mesh;
    String texture;
    
    public GamePiece() {
        
    }
    
    public void setPos(float x, float z) {
        this.x = x;
        this.z = z;
    }
    
    public void update(float dt) {
        
    }
    
    /*
     * public Mesh getMesh() {
     *     return mesh;
     * }
     */
    
    public String getTexture() {
        return texture;
    }
    
    public void render() {
        
    }
}
