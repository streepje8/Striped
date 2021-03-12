#version 150

in vec3 position;
in vec2 uv;
in vec3 normal;

out vec3 pass_normal;
out vec2 pass_uv;
out vec3 pass_position;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position,1.0);
	pass_uv = uv;
	pass_normal = (transformationMatrix * vec4(normal,0.0)).xyz;
	pass_position = (transformationMatrix * vec4(position,1.0)).xyz;
}