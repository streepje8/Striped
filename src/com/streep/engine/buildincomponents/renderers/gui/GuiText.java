package com.streep.engine.buildincomponents.renderers.gui;

import java.io.File;

import com.streep.engine.buildincomponents.renderers.gui.fontMeshCreator.FontType;
import com.streep.engine.buildincomponents.renderers.gui.fontMeshCreator.GUIText;
import com.streep.engine.buildincomponents.renderers.gui.fontRendering.TextMaster;
import com.streep.engine.elements.Texture;
import com.streep.engine.util.Vector3;

public class GuiText extends GuiElement {

	private static final long serialVersionUID = 1L;
	public String text;
	private static Texture defaultAtlas = new Texture("./Resources/DefaultAssets/defaultFont.png");
	private static File defaultFontFile = new File("./Resources/DefaultAssets/defaultFont.fnt");
	public FontType font = new FontType(defaultAtlas, defaultFontFile);
	public float fontSize = 1f;
	public Vector3 color = Vector3.zero();
	public float maxLineLength = 2000f;
	public boolean centered = false;
	private GUIText guitext = null;
	
	public FontType getFont() {
		return font;
	}

	public void setFont(FontType font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public static Texture getDefaultAtlas() {
		return defaultAtlas;
	}

	public static File getDefaultFontFile() {
		return defaultFontFile;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void update() {
		
	}
	
	public void updateText() {
		if(this.guitext != null) {
			TextMaster.removeText(this.guitext);
		}
		this.guitext = new GUIText(this);
		this.guitext.setColour(this.color.x, this.color.y, this.color.z);
	}

}
