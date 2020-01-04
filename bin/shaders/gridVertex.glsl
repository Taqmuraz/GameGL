#version 400 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;
out float fogVisibility;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;
uniform vec2 tiling;
uniform vec2 textureOffset;
uniform bool globalNormal;
uniform vec3 windEffect;
uniform float fogDencity;
uniform float fogGradient;
uniform bool enableFog;
uniform float time;
uniform float waterEffect;

vec4 worldPosition;
vec4 relativeToCamPosition;

void calculatePosition (void);

void main ()
{
	calculatePosition();
	vec4 pos = vec4(position, 1) * transformationMatrix;
	pass_textureCoords = vec2(pos.x, pos.z);
}

void calculatePosition (void)
{
	vec4 position4 = vec4 (position, 1.0);
	mat4 vt = viewMatrix * transformationMatrix;

	worldPosition = vt * position4;

	//vec4 worldPosition = transformationMatrix * vec4 (textureCoords.x, 1.0 - textureCoords.y, 0.0, 1.0);

	gl_Position = projectionMatrix * worldPosition;
}



