package Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.Apple;
import Entity.User;
import Graphics.Screen;
import Graphics.Sprite;
import Input.Keyboard;

public class world {
	
	public int width, height, num=0, length=10;
	private Screen screen;
	private Keyboard key;
	protected Random rnd = new Random();
	public List<User> Players = new ArrayList<User>();
	public List<Apple> Apples = new ArrayList<Apple>();
	public int[] grid;

	
	public world(int width, int height, Screen screen, Keyboard key){
		this.width=width;
		this.height=height;
		grid=new int[width*height];
		this.screen = screen;
		this.key=key;
		Players.add(new User(Screen.width/2, Screen.height/2, screen, key, this));
		for(int i = 0; i < grid.length;i++){
			grid[i]=0;
		}
		Apples.add(new Apple(50,50,screen));
	}
	
	public void update(){
		for(int i = 0; i < Players.size();i++){
			Players.get(i).update();
		}
		for(int i = 0; i < Apples.size();i++){
			if(Apples.get(i).removed){
				Apples.remove(i);
				Apples.add(new Apple(rnd.nextInt(screen.width-4)+2,rnd.nextInt(screen.height-4)+2,screen));
			}
		}
		grid[Players.get(0).x+Players.get(0).y*width]=length;
		for(int i = 0; i < grid.length;i++){
			grid[i]=grid[i]<=0?0:grid[i]-1;
		}
	}
	
	public void render(){
		for(int i = 0; i < Players.size();i++){
			Players.get(i).render();
		}
		for(int i = 0; i < Apples.size();i++){
			Apples.get(i).render();
		}
		for(int yp = 0; yp < height;yp++){
			for(int xp = 0; xp < width;xp++){
				if(grid[xp+yp*width]!=0){
					screen.renderSprite(xp, yp, new Sprite(1,0xffffff));
				}
			}
		}
	}
}
