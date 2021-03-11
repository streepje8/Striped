#version 150

in vec2 pass_uv;
in vec3 pass_normal;
in vec3 pass_position;

out vec4 out_color;

uniform sampler2D textureSampler;

uniform int pointLightCount;
uniform int directionalLightCount;

uniform vec3 pointLightPositions[128];
uniform vec3 pointLightColors[128];
uniform float pointLightIntensities[128];
uniform vec3 directionalLightDirections[128];
uniform vec3 directionalLightColors[128];
uniform float directionalLightIntensities[128];

void main(void) {
	vec3 lightAmount = vec3(0,0,0);
	for(int i = 0; i < directionalLightCount; i++)
	{
	    vec3 direction = -directionalLightDirections[i];
		vec3 color = directionalLightColors[i];
		float intensity = directionalLightIntensities[i];
		float dotLN = dot(normalize(pass_normal),direction);
		if(dotLN > 0) {
			dotLN = 0;
		}
		lightAmount += color * dotLN * intensity;
	}
	for(int i = 0; i < pointLightCount; i++)
	{
	    vec3 position = pointLightPositions[i];
		vec3 color = pointLightColors[i];
		float intensity = pointLightIntensities[i];
		float dotLN = dot(normalize(pass_normal),normalize(position - pass_position));
		if(dotLN > 0) {
			dotLN = 0;
		}
		lightAmount += color * dotLN * intensity;
	}
	vec4 lightVector = vec4(0.1) + vec4(lightAmount,1.0); 
	out_color = vec4(pass_position,1.0);
	//out_color = texture(textureSampler, pass_uv) * lightVector;
}