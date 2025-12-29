#version 120

uniform vec2 u_size;
uniform vec4 u_color;
uniform float u_radius;

float sdf(vec2 point, vec2 bounds, float radius) {
    vec2 q = abs(point) - bounds + radius;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - radius;
}

void main() {
    vec2 pos = gl_TexCoord[0].st * u_size;

    vec2 center = u_size * 0.5;

    float dist = sdf(pos - center, center, u_radius);

    float alpha = 1.0 - smoothstep(0.0, 1.0, dist);

    if (alpha <= 0.0) discard;

    gl_FragColor = vec4(u_color.rgb, u_color.a * alpha);
}