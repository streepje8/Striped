package com.streep.engine.GUI;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import com.streep.engine.core.Setup;
import com.streep.engine.util.Vector2;

public class GlWindow extends BasisWindow {

	private long window = 0; //openGL window
	public int width = 800;
	public int height = 400;
	
	public GlWindow(int width, int height, String title) {
		this.width = width;
		this.height = height;
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
		});

		//Center the window
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
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		// Set the clear color
		glClearColor(Setup.clearColor.getRed(), Setup.clearColor.getGreen(), Setup.clearColor.getBlue(), 0.0f);
	}
	
	public static Vector2 getSize(long window) {
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*
			glfwGetWindowSize(window, pWidth, pHeight);
			GL11.glViewport(0, 0, pWidth.get(0), pHeight.get(0));
			return new Vector2(pWidth.get(0), pHeight.get(0));
		} catch(Exception e) {
			return new Vector2(0,0);
		}
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(getWindow());
		glfwPollEvents();
	}

	public long getWindow() {
		return window;
	}

	@Override
	public boolean getKey(int key) {
		return GLFW.glfwGetKey(this.window, key) == GLFW.GLFW_PRESS;
	}

	@Override
	public boolean getKeyReleased(int key) {
		return GLFW.glfwGetKey(this.window, key) == GLFW.GLFW_RELEASE;
	}

}
