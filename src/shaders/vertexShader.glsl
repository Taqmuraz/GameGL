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
void calculateLight (void);
void calculateTextureCoords (void);
void calculateFog (void);
vec2 getCoords (vec2 origin);

void main ()
{
	
	calculatePosition (void);
	calculateLight (void);
	calculateTextureCoords (void);
	calculateFog(void);
}
void calculateFog (void)
{
	float visibility = 1.0;
	if (enableFog)
	{
		float distance = length(relativeToCamPosition.xyz);
		visibility = exp(-pow((distance * fogDencity), fogGradient));
		visibility = clamp(visibility, 0.0, 1.0);
	}
	fogVisibility = visibility;
}
vec2 getCoords (vec2 origin)
{
	return vec2(origin.x * tiling.x, origin.y * tiling.y) + textureOffset;
}
void calculateTextureCoords (void)
{
	pass_textureCoords = getCoords(textureCoords);
}
void calculateLight (void)
{
	vec3 actualNormal = normal;
	if (globalNormal)
	{
		actualNormal = vec3(0.0,1.0,0.0);
	}
	
	surfaceNormal = (transformationMatrix * vec4(actualNormal, 0.0)).xyz;
	
	toLightVector = lightPosition - worldPosition.xyz;
	
	toCameraVector = (inverse(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;
}
vec3 calculateWaterPosition (vec3 position)
{
	vec3 waterRoot = position;//position - (transformationMatrix * position4);
	vec3 effect = vec3(sin(waterRoot.y + time), sin(sin(waterRoot.x * 100.0) + sin(waterRoot.z * 100.0) + time), cos(waterRoot.y - time));
	return worldPosition.xyz + effect * waterEffect;
}
void calculatePosition (void)
{
	vec4 position4 = vec4 (position, 1.0);
	mat4 vt = viewMatrix * transformationMatrix;

	vec4 rawWorldPosition = vt * position4;
	
	relativeToCamPosition = rawWorldPosition;

	vec3 rootPosition = (vt * vec4(0.0,0.0,0.0,1.0)).xyz;
	
	vec3 windAxis = (rawWorldPosition.xyz - rootPosition);
	
	float windForce = max(length(windAxis) - 1.0, 0.0);
	
	worldPosition = rawWorldPosition + vec4(windEffect * windForce, 1.0);
	
	//vec4 worldPosition = transformationMatrix * vec4 (textureCoords.x, 1.0 - textureCoords.y, 0.0, 1.0);
	
	gl_Position = projectionMatrix * worldPosition;
}




