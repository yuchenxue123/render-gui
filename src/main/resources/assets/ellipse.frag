#version 120

uniform vec2 u_size;
uniform vec4 u_color;
uniform float u_smoothness = 1.0;

void main() {
    vec2 uv = gl_TexCoord[0].st;

    vec2 diff = uv - 0.5;

    float dist = length(diff);

    vec2 ps = 1.0 / u_size;

    float feather  = max(ps.x, ps.y) * u_smoothness;

    float alpha = 1.0 - smoothstep(0.5 - feather, 0.5, dist);

    if (alpha <= 0.0) discard;

    gl_FragColor = vec4(u_color.rgb, u_color.a * alpha);
}