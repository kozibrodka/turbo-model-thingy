package net.kozibrodka.tmt.TURBO_MODEL_125;

public class Coord2D {

    public Coord2D(double x, double y) {
        this.xCoord = x;
        this.yCoord = y;
        this.uCoord = (int)Math.floor(x);
        this.vCoord = (int)Math.floor(y);
    }

    public Coord2D(double x, double y, int u, int v) {
        this(x, y);
        this.uCoord = u;
        this.vCoord = v;
    }

    public double xCoord;
    public double yCoord;
    public int uCoord;
    public int vCoord;
}
