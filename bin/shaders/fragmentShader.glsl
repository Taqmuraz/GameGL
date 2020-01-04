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

void calculateLight (void);
void calculateCutOff (void);
void calculateReflection(void);
void calculateFog(void);

vec3 diffuse;
vec3 unitNormal;
vec3 unitLightVector;
vec4 textureColor;

void main (void)
{
	textureColor = texture(textureSampler, pass_textureCoords);

	calculateCutOff(void);
	calculateLight (void);
	calculateReflection(void);
	calculateFog(void);
}
void calculateLight (void)
{
	unitNormal = normalize(surfaceNormal);
	unitLightVector = normalize (toLightVector);
	
	float nDotl = dot (unitNormal, unitLightVector);
	float brightness = max(nDotl, ambienceIntencivity);
	
	diffuse = brightness * lightColor;
}
void calculateCutOff (void)
{
	if (textureColor.a < alphaCutOff)
	{
		discard;
	}
}
void calculateReflection(void)
{
	vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightLirection = reflect(lightDirection, unitNormal);
	
	float specularFactor = dot(reflectedLightLirection, unitVectorToCamera);
	specularFactor = max(specularFactor, 0.0);
	float damperFactor = pow(specularFactor, shineDamper);
	
	vec3 finalSpecular = damperFactor * reflectivity * lightColor;
	
	out_Color = vec4(diffuse, 1.0) * textureColor + vec4(finalSpecular, 1.0);
}
void calculateFog(void)
{
	out_Color = mix(vec4(skyColor, 1.0), out_Color, fogVisibility);
}






