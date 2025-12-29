#version 120

#define PI 3.14159265359

uniform float u_time;
uniform vec2 u_size;
uniform vec4 top_color;
uniform vec4 bottom_color;

uniform float wave_amp;         // 0.0 ~ 0.5
uniform float wave_size;        // 1.0 ~ 10.0
uniform float wave_time_mul;    // 0.1 ~ 2.0
uniform int total_phases;       // 2 ~ 600

float rand(float n) {
    return fract(sin(n) * 43758.5453123);
}

float noise(float p) {
    float fl = floor(p);
    float fc = fract(p);
    return mix(rand(fl), rand(fl + 1.0), fc);
}

float fmod(float x, float y) {
    return x - floor(x / y) * y;
}

void main() {
    vec2 uv = gl_TexCoord[0].st;

    float t = float(total_phases);
    float effective_wave_amp = min(wave_amp, 0.5 / t);

    float d = fmod(uv.y, 1.0 / t);
    float i = floor(uv.y * t);
    float vi = floor(uv.y * t + t * effective_wave_amp);

    float noise_val = noise(vi);
    float phase_speed = max(1.0 / t, noise_val) * wave_time_mul * vi / t;
    float s = effective_wave_amp * sin((uv.x + u_time * phase_speed) * 2.0 * PI * wave_size);

    if (d < s) {
        i -= 1.0;
    }
    if (d > s + 1.0 / t) {
        i += 1.0;
    }

    i = clamp(i, 0.0, t - 1.0);

    gl_FragColor = mix(top_color, bottom_color, i / (t - 1.0));
}