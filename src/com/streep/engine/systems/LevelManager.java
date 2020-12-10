package com.streep.engine.systems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LevelManager {

	public static Level currentLevel = new Level("DefaultLevel");
	
	public static void gotoLevel(Level level) {
		currentLevel = level;
	}
	
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	
	public static Level LoadFromFile(File f) {
		try {
        	FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fileIn);
			Level l = (Level) in.readObject();
			in.close();
			fileIn.close();
			return l;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean saveToFile(Level level, File f) { 
		try {
        	FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(level);
			out.close();
			fileOut.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
