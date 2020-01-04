#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float fogVisibility;

out vec4 out_Color;


uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;
uniform float ambienceIntencivity;
uniform float alphaCutOff;
uniform vec3 skyColor;

vec3 diffuse;
vec3 unitNormal;
vec3 unitLightVector;
vec4 textureColor;

void main (void)
{
	vec2 x = round(pass_textureCoords);
	x -= pass_textureCoords;
	out_Color = vec4(1,1,1,1) * (1 - pow(length(x), 2));
}





