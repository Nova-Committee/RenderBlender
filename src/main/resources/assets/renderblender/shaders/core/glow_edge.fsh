#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform int glowColor;
uniform float glowWidth;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;
in vec4 normal;

out vec4 fragColor;

vec3 rgbToVec3(int rgb) {
    float r = float((rgb >> 16) & 0xFF) / 255.0;
    float g = float((rgb >> 8) & 0xFF) / 255.0;
    float b = float(rgb & 0xFF) / 255.0;
    return vec3(r, g, b);
}

void main() {
    // 采样原始纹理
    vec4 texColor = texture(Sampler0, texCoord0);

    // 如果原始像素透明度很低，不显示发光效果
    if (texColor.a < 0.1) {
        fragColor = vec4(0.0, 0.0, 0.0, 0.0);
        return;
    }

    // 使用指定的发光颜色
    vec3 glow = rgbToVec3(glowColor);

    // 创建发光效果，使用全亮度颜色
    fragColor = vec4(glow, texColor.a);

    // 应用雾效
    fragColor = linear_fog(fragColor * ColorModulator, vertexDistance, FogStart, FogEnd, FogColor);
}
