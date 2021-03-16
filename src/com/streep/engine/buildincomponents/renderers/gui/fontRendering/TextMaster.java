package com.streep.engine.buildincomponents.renderers.gui.fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL30;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.renderers.gui.fontMeshCreator.FontType;
import com.streep.engine.buildincomponents.renderers.gui.fontMeshCreator.GUIText;
import com.streep.engine.buildincomponents.renderers.gui.fontMeshCreator.TextMeshData;
import com.streep.engine.core.rendering.GLRenderer.VMemory;

public class TextMaster {
	
	private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer renderer;
	public static Window window;
	
	public static void init(Window game_window){
		renderer = new FontRenderer();
		renderer.onStart();
		window = game_window;
	}
	
	public static void render(){
		renderer.render(texts);
	}
	
	public static void loadText(GUIText text){
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = VMemory.loadTextMeshData(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null){
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void removeText(GUIText text){
		List<GUIText> textBatch = texts.get(text.getFont());
		GL30.glDeleteVertexArrays(text.getMesh());
		textBatch.remove(text);
		if(textBatch.isEmpty()){
			texts.remove(texts.get(text.getFont()));
		}
	}
	
	public static void cleanUp(){
		renderer.cleanUp();
	}

}
