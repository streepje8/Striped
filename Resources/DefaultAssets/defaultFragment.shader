#version 150

in vec2 pass_uv;

out vec4 out_color;

uniform sampler2D textureSampler;

void main(void) {
	out_color = texture(textureSampler, pass_uv);
}