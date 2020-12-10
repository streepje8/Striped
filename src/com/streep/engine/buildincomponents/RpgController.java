package com.streep.engine.buildincomponents;

import com.streep.engine.core.Component;
import com.streep.engine.util.Time;

public class RpgController extends Component {

	private static final long serialVersionUID = 1L;
	
	public float speed = 2f;
	
	@Override
	public void start() {}

	@Override
	public void update() {
		if(Input.getKeyDown('w')) {
			gameObject.y -= speed * Time.DeltaTime;
		}
		if(Input.getKeyDown('a')) {
			gameObject.x -= speed * Time.DeltaTime;
		}
		if(Input.getKeyDown('s')) {
			gameObject.y += speed * Time.DeltaTime;
		}
		if(Input.getKeyDown('d')) {
			gameObject.x += speed * Time.DeltaTime;
		}
	}
}
