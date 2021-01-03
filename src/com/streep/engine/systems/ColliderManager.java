package com.streep.engine.systems;

import java.util.ArrayList;

import com.streep.engine.buildincomponents.colliders.CircleCollider;
import com.streep.engine.buildincomponents.colliders.Collider;
import com.streep.engine.buildincomponents.colliders.SquareCollider;
import com.streep.engine.core.Component;

public class ColliderManager {

	public ArrayList<Collider> colliders = new ArrayList<Collider>();
	
	public ArrayList<Collider> getColliders() {
		return colliders;
	}
	
	public void registerCollider(Component c) {
		boolean success = false;
		if(c instanceof SquareCollider) {
			colliders.add((SquareCollider) c);
			success = true;
		}
		if(c instanceof CircleCollider) {
			colliders.add((CircleCollider) c);
			success = true;
		}
		if(!success) {
			if(c instanceof Collider) {
				System.out.println("[Warning] registered an unknown collider! Collider class: " + c.getClass().getName());
				colliders.add((Collider) c);
			} else {
				System.err.println("[Error] tried to register a non-collider! Class: " + c.getClass().getName());
			}
		}
	}
	
	public void unRegisterCollider(Component c) {
		boolean success = false;
		if(c instanceof SquareCollider) {
			SquareCollider sq = (SquareCollider) c;
			if(colliders.contains(sq)) {
				colliders.remove(sq);
			} else {
				System.err.println("[Error] tried to unregister a square collider that is not regsitered!");
			}
			success = true;
		}
		if(c instanceof CircleCollider) {
			CircleCollider cq = (CircleCollider) c;
			if(colliders.contains(cq)) {
				colliders.remove(cq);
			} else {
				System.err.println("[Error] tried to unregister a circle collider that is not regsitered!");
			}
			success = true;
		}
		if(!success) {
			if(colliders.contains(c)) {
				System.out.println("[Warning] Unregistered an unknown collider! Collider type " + c.getClass().getName());
				colliders.remove(c);
			} else {
				System.err.println("[Error] Tried to unregister an non-collider/not registerd collider! Class: " + c.getClass().getName());
			}
		}
	}
	
}
