package net.kozibrodka.tmt.TURBO_MODEL_2125_DEV;

public class Shape3D {

    public PositionTransformVertex[] vertices;
    public TexturedPolygon[] faces;

    public Shape3D(PositionTransformVertex[] verts, TexturedPolygon[] poly) {
        this.vertices = verts;
        this.faces = poly;
    }
}
