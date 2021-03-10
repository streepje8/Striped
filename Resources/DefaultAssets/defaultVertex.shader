#version 150

in vec3 position;
in vec2 uv;

out vec2 pass_uv;

uniform mat4 transformationMatrix;

void main(void) {
	gl_Position = transformationMatrix * vec4(position,1.0);
	pass_uv = uv;
}