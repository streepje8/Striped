package com.streep.engine.buildincomponents;

import com.streep.engine.systems.Component;
import com.streep.engine.systems.Time;

public class RpgController extends Component {

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
