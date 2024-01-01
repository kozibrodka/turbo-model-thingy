package net.kozibrodka.tmt.TURBO_MODEL_164;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelPoolObjEntry extends ModelPoolEntry {
    public ModelPoolObjEntry() {
        this.fileExtensions = new String[]{"obj"};
    }

    public void getModel(File file) {
        try {
            BufferedReader e = new BufferedReader(new FileReader(file));
            ArrayList verts = new ArrayList();
            ArrayList uvs = new ArrayList();
            ArrayList normals = new ArrayList();
            ArrayList face = new ArrayList();

            String s;
            while((s = e.readLine()) != null) {
                if(s.indexOf("#") > -1) {
                    s = s.substring(0, s.indexOf("#"));
                }

                s = s.trim();
                if(!s.equals("")) {
                    if(s.startsWith("g ")) {
                        this.setTextureGroup(s.substring(s.indexOf(" ") + 1).trim());
                    } else {
                        int finalPhase;
                        float[] var21;
                        int var23;
                        float var24;
                        if(s.startsWith("v ")) {
                            s = s.substring(s.indexOf(" ") + 1).trim();
                            var21 = new float[3];

                            for(var23 = 0; var23 < 3; ++var23) {
                                finalPhase = s.indexOf(" ");
                                if(finalPhase > -1) {
                                    var21[var23] = Float.parseFloat(s.substring(0, finalPhase));
                                } else {
                                    var21[var23] = Float.parseFloat(s.substring(0));
                                }

                                s = s.substring(s.indexOf(" ") + 1).trim();
                            }

                            var24 = var21[2];
                            var21[2] = -var21[1];
                            var21[1] = var24;
                            verts.add(new PositionTransformVertex(var21[0], var21[1], var21[2], 0.0F, 0.0F));
                        } else if(s.startsWith("vt ")) {
                            s = s.substring(s.indexOf(" ") + 1).trim();
                            var21 = new float[2];

                            for(var23 = 0; var23 < 2; ++var23) {
                                finalPhase = s.indexOf(" ");
                                if(finalPhase > -1) {
                                    var21[var23] = Float.parseFloat(s.substring(0, finalPhase));
                                } else {
                                    var21[var23] = Float.parseFloat(s.substring(0));
                                }

                                s = s.substring(s.indexOf(" ") + 1).trim();
                            }

                            uvs.add(new float[]{var21[0], 1.0F - var21[1]});
                        } else if(s.startsWith("vn ")) {
                            s = s.substring(s.indexOf(" ") + 1).trim();
                            var21 = new float[3];

                            for(var23 = 0; var23 < 3; ++var23) {
                                finalPhase = s.indexOf(" ");
                                if(finalPhase > -1) {
                                    var21[var23] = Float.parseFloat(s.substring(0, finalPhase));
                                } else {
                                    var21[var23] = Float.parseFloat(s.substring(0));
                                }

                                s = s.substring(s.indexOf(" ") + 1).trim();
                            }

                            var24 = var21[2];
                            var21[2] = var21[1];
                            var21[1] = var24;
                            normals.add(new float[]{var21[0], var21[1], var21[2]});
                        } else if(s.startsWith("f ")) {
                            s = s.substring(s.indexOf(" ") + 1).trim();
                            ArrayList i = new ArrayList();
                            finalPhase = 0;
                            float[] normal = new float[]{0.0F, 0.0F, 0.0F};
                            ArrayList iNormal = new ArrayList();

                            do {
                                int ind = s.indexOf(" ");
                                String s1 = s;
                                if(ind > -1) {
                                    s1 = s.substring(0, ind);
                                }

                                int d;
                                float[] vToArr;
                                float[] poly;
                                if(s1.indexOf("/") > -1) {
                                    String[] f = s1.split("/");
                                    d = Integer.parseInt(f[0]) - 1;
                                    if(f[1].equals("")) {
                                        f[1] = f[0];
                                    }

                                    int vtInt = Integer.parseInt(f[1]) - 1;
                                    if(uvs.size() > vtInt) {
                                        vToArr = (float[])uvs.get(vtInt);
                                    } else {
                                        vToArr = new float[]{0.0F, 0.0F};
                                    }

                                    boolean vnInt = false;
                                    int var29;
                                    if(f.length == 3) {
                                        if(f[2].equals("")) {
                                            f[2] = f[0];
                                        }

                                        var29 = Integer.parseInt(f[2]) - 1;
                                    } else {
                                        var29 = Integer.parseInt(f[0]) - 1;
                                    }

                                    if(normals.size() > var29) {
                                        poly = (float[])normals.get(var29);
                                    } else {
                                        poly = new float[]{0.0F, 0.0F, 0.0F};
                                    }
                                } else {
                                    d = Integer.parseInt(s1) - 1;
                                    if(uvs.size() > d) {
                                        vToArr = (float[])uvs.get(d);
                                    } else {
                                        vToArr = new float[]{0.0F, 0.0F};
                                    }

                                    if(normals.size() > d) {
                                        poly = (float[])normals.get(d);
                                    } else {
                                        poly = new float[]{0.0F, 0.0F, 0.0F};
                                    }
                                }

                                iNormal.add(Vec3f.method_1293((double)poly[0], (double)poly[1], (double)poly[2]));
                                normal[0] += poly[0];
                                normal[1] += poly[1];
                                normal[2] += poly[2];
                                if(d < verts.size()) {
                                    i.add(((PositionTransformVertex)verts.get(d)).method_983(vToArr[0], vToArr[1]));
                                    if(verts.get(d) instanceof PositionTransformVertex) {
                                        ((PositionTransformVertex)verts.get(d)).addGroup(this.group);
                                    }
                                }

                                if(ind > -1) {
                                    s = s.substring(s.indexOf(" ") + 1).trim();
                                } else {
                                    ++finalPhase;
                                }
                            } while(finalPhase < 1);

                            float var25 = MathHelper.sqrt((double)(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]));
                            normal[0] /= var25;
                            normal[1] /= var25;
                            normal[2] /= var25;
                            QuadPoint[] var26 = new QuadPoint[i.size()];

                            for(int var27 = 0; var27 < i.size(); ++var27) {
                                var26[var27] = (QuadPoint)i.get(var27);
                            }

                            TexturedPolygon var28 = new TexturedPolygon(var26);
                            var28.setNormals(normal[0], normal[1], normal[2]);
                            var28.setNormals(iNormal);
                            face.add(var28);
                            this.texture.addPoly(var28);
                        }
                    }
                }
            }

            this.vertices = new PositionTransformVertex[verts.size()];

            int var22;
            for(var22 = 0; var22 < verts.size(); ++var22) {
                this.vertices[var22] = (PositionTransformVertex)verts.get(var22);
            }

            this.faces = new TexturedPolygon[face.size()];

            for(var22 = 0; var22 < face.size(); ++var22) {
                this.faces[var22] = (TexturedPolygon)face.get(var22);
            }

            e.close();
        } catch (Throwable var20) {
        }

    }
}
