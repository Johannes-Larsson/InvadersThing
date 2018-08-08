package se.johannes_larsson.reactionclass;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {
	public ArrayList<GameObject> objects, toAdd, toRemove;
	
	public void initialize() {
		objects = new ArrayList<GameObject>();
		toAdd = new ArrayList<GameObject>();
		toRemove = new ArrayList<GameObject>();
	}
	
	public void update() {
		for (int i = 0; i < toAdd.size(); i++) {
			if (objects.size() == 0) objects.add(toAdd.get(i));
			else {
				for (int j = 0; j < objects.size(); j++) {
					if (objects.get(j).depth <= toAdd.get(i).depth) { 
						objects.add(j, toAdd.get(i));
						break;
					}
				}
			}
		}
		toAdd.clear();
		
		//for (GameObject g : objects) if (g.dead) toRemove.add(g);
		
		for (int i = 0; i < toRemove.size(); i++) {
			objects.remove(toRemove.get(i));
		}
		toRemove.clear();
		
		for (GameObject o : objects) {
			o.update();			
			for (GameObject g : objects) {
				if (g == o) continue;
				if (g.intersects(o)) o.onCollide(g);
			}
		}
	}
	
	public void onResume() { }
	
	public void draw(SpriteBatch batch) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(batch);
		}
	}
}
