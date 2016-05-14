package Entity;

import Graphics.Screen;
import Graphics.Sprite;

public class Apple {
	
	public int x, y;
	private Screen screen;
	public boolean removed=false;

	public Apple(int x, int y, Screen screen){
		this.x=x;
		this.y=y;
		this.screen=screen;
	}
	
	public void remove(){
		removed = true;
	}

	public void render() {
		screen.renderSprite(x, y, new Sprite(1,0x00ff00));
	}
}