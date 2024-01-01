package net.kozibrodka.tmt.TURBO_MODEL_164;

public class Shape3D {

    public PositionTransformVertex[] vertices;
    public TexturedPolygon[] faces;

    public Shape3D(PositionTransformVertex[] verts, TexturedPolygon[] poly) {
        this.vertices = verts;
        this.faces = poly;
    }
}
