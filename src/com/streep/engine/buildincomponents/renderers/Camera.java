package com.streep.engine.buildincomponents.renderers;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.streep.engine.core.Component;
import com.streep.engine.util.Vector2;

public class Camera extends Component {

	private static final long serialVersionUID = 1L;


    public float FOV = 70;
    public float Z_NEAR = 0.01f;
    public float Z_FAR = 1000.f;
    public boolean GUI_ENABLED = true;

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Matrix4f getProjectionMatrix(Vector2 textureSize) {
		float aspectRatio = textureSize.x / textureSize.y;
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = this.Z_FAR - this.Z_NEAR;

		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((this.Z_FAR + this.Z_NEAR) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * this.Z_NEAR * this.Z_FAR) / frustum_length);
		projectionMatrix.m33 = 0;
		return projectionMatrix;
	}
	
	public Matrix4f getViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(this.gameObject.rotation.x), new Vector3f(1, 0, 0), viewMatrix,
				viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(this.gameObject.rotation.y), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(this.gameObject.rotation.z), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
		Vector3f cameraPos = this.gameObject.position.toVector3f();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}

}
