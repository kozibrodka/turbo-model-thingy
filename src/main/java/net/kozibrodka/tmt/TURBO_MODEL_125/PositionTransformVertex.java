package net.kozibrodka.tmt.TURBO_MODEL_125;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.util.maths.Vec3f;

import java.util.ArrayList;

public class PositionTransformVertex extends QuadPoint {

    public PositionTransformVertex(float x, float y, float z, float u, float v) {
        this(Vec3f.method_1293((double) x, (double) y, (double) z), u, v);
    }

    public PositionTransformVertex(QuadPoint vertex, float u, float v) {
        super(vertex, u, v);
        this.transformGroups = new ArrayList();
        if (vertex instanceof PositionTransformVertex) {
            this.neutralVector = ((PositionTransformVertex) vertex).neutralVector;
        } else {
            this.neutralVector = Vec3f.method_1293(vertex.pointVector.x, vertex.pointVector.y, vertex.pointVector.z);
        }

    }

    public PositionTransformVertex(QuadPoint vertex) {
        this(vertex, vertex.field_1147, vertex.field_1148);
    }

    public PositionTransformVertex(Vec3f vector, float u, float v) {
        super(vector, u, v);
        this.transformGroups = new ArrayList();
        this.neutralVector = Vec3f.method_1293(vector.x, vector.y, vector.z);
    }

    public void setTransformation() {
        if (this.transformGroups.size() == 0) {
            this.pointVector.x = this.neutralVector.x;
            this.pointVector.y = this.neutralVector.y;
            this.pointVector.z = this.neutralVector.z;
        } else {
            double weight = 0.0D;

            int i;
            for (i = 0; i < this.transformGroups.size(); ++i) {
                weight += ((TransformGroup) this.transformGroups.get(i)).getWeight();
            }

            this.pointVector.x = 0.0D;
            this.pointVector.y = 0.0D;
            this.pointVector.z = 0.0D;

            for (i = 0; i < this.transformGroups.size(); ++i) {
                TransformGroup group = (TransformGroup) this.transformGroups.get(i);
                double cWeight = group.getWeight() / weight;
                Vec3f vector = group.doTransformation(this);
                this.pointVector.x += cWeight * vector.x;
                this.pointVector.y += cWeight * vector.y;
                this.pointVector.z += cWeight * vector.z;
            }

        }
    }

    public void addGroup(TransformGroup group) {
        this.transformGroups.add(group);
    }

    public void removeGroup(TransformGroup group) {
        this.transformGroups.remove(group);
    }
    public Vec3f neutralVector;
    public ArrayList transformGroups;
}
