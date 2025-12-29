#version 120

uniform sampler2D u_texture;
uniform vec2 u_resolution;

// maybe not uniform
uniform int samples = 35;
uniform int lod = 2;
// blur color
uniform vec4 u_color = vec4(0.0, 0.0, 0.0, 0.0);

float gaussian(vec2 i, float sigma) {
    vec2 d = i / sigma;
    return exp(-0.5 * dot(d, d)) / (6.28318 * sigma * sigma);
}

vec4 blur(sampler2D text, vec2 uv, vec2 pixel_size) {
    vec4 original_color = vec4(0.0);

    float sigma = float(samples) * 0.25;

    float s_lod = pow(2.0, float(lod));

    int s = int(float(samples) / s_lod);
    int count = s * s;

    for (int i = 0; i < count; i++) {
        float row = floor(float(i) / float(s));
        float col = mod(float(i), float(s));

        vec2 offset = vec2(col, row) * s_lod - float(samples) / 2.0;

        vec2 sampleUV = uv + pixel_size * offset;

        original_color += gaussian(offset, sigma) * texture2D(text, sampleUV, float(lod));
    }

    if (original_color.a > 0.0) {
        return original_color / original_color.a;
    } else {
        return vec4(0.0);
    }
}

void main() {
    vec2 texCoord = gl_FragCoord.xy / u_resolution;
    vec2 pixel_size = 1.0 / u_resolution;

    vec4 blurred_color = blur(u_texture, texCoord, pixel_size);

    vec3 mixed_rgb = mix(blurred_color.rgb, u_color.rgb, u_color.a);

    gl_FragColor = vec4(mixed_rgb, blurred_color.a);
}