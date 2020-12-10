package com.streep.engine.systems;

public class SMath {

	public static int sign(float f) {
		if(f < 0) {
			return -1;
		} else {
			if(f > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
}
