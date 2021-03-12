package com.streep.engine.buildincomponents.renderers.gui;

import com.streep.engine.core.Component;
import com.streep.engine.util.Vector2;

public abstract class GuiElement extends Component {

	private static final long serialVersionUID = 1L;
	
	public Vector2 position = new Vector2(0,0);
	public Vector2 rotation = new Vector2(0,0);
	public Vector2 scale = new Vector2(1,1);
	

}
