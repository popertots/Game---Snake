package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import Input.Keyboard;
import Level.world;

public class User {
	private Screen Screen;
	private world level;
	private Keyboard key;
	public int x=0,y=0,xa=0,ya=0;
	private Sprite sprite;
	private int speed;
	
	public User(int x, int y, Screen screen, Keyboard key, world level){
		this.x=x;
		this.y=y;
		this.Screen=screen;
		this.key=key;
		this.level=level;
		ya=-1;
	}
	
	public void update(){
		if(key.up&&ya!=1){
			ya=-1;
			xa=0;
		}
		if(key.down&&ya!=-1){
			ya=1;
			xa=0;
		}
		if(key.left&&xa!=1){
			xa=-1;
			ya=0;
		}
		if(key.right&&xa!=-1){
			xa=1;
			ya=0;
		}
		if((x%Screen.width==0)||(x%Screen.width==Screen.width-1)||(y%Screen.height==0)||(y%Screen.height==Screen.height-1)||Screen.pixels[(x+xa)+(y+ya)*Screen.width]==0xffffff){
			System.exit(1);
		}
		speed++;
		if(speed>=2){
			move(xa, ya);
			speed=0;
		}
		for(int i = 0; i < level.Apples.size();i++){
			if(x==level.Apples.get(i).x&&y==level.Apples.get(i).y){
				level.Apples.get(i).remove();
				level.length+=10;
			}
		}
	}
	
	public void move(int xa, int ya){
		if (xa!=0&&ya!=0){
			move(xa,0);
			move(0,ya);
			return;
		}
		x += xa;
		y += ya;
	}
	
	public void render(){
		Screen.renderEntity(x, y, new Sprite(1,0xffffff), 0);
	}
}
