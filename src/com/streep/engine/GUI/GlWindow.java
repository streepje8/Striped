package com.streep.engine.GUI;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.streep.engine.core.Game;
import com.streep.engine.core.QuickSetup;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GlWindow extends BasisWindow{

	private long window = 0; //openGL window
	
	public GlWindow(int width, int height, String title) {
		System.out.println("Striped using LWJGL version " + Version.getVersion() + "!");
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit())
			throw new IllegalStateException("Striped is unable to initialize GLFW!");	
		
		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		// Create the window
		this.window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (getWindow() == NULL)
			throw new RuntimeException("Striped failed to create the GLFW window");

		glfwSetKeyCallback(getWindow(), (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true);
				Game.Input.glPressed(key);
		});

		//Code opengl recommended
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*
			glfwGetWindowSize(getWindow(), pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(
				getWindow(),
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}
		
		glfwShowWindow(getWindow());
		glfwMakeContextCurrent(getWindow());
		glfwSwapInterval(1);
		GL.createCapabilities();

		// Set the clear color
		glClearColor(QuickSetup.clearColor.getRed(), QuickSetup.clearColor.getGreen(), QuickSetup.clearColor.getBlue(), 0.0f);
	}
	
	public void update() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(getWindow());
		glfwPollEvents();
	}

	public long getWindow() {
		return window;
	}

}
