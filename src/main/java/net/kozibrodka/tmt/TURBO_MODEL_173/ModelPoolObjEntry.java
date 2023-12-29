package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.render.QuadPoint;
import net.minecraft.util.maths.MathHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelPoolObjEntry extends ModelPoolEntry
{

    public ModelPoolObjEntry()
    {
        fileExtensions = (new String[] {
                "obj"
        });
    }

    public void getModel(File file)
    {
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist2 = new ArrayList();
            ArrayList arraylist3 = new ArrayList();
            do
            {
                String s;
                if((s = bufferedreader.readLine()) == null)
                {
                    break;
                }
                if(s.indexOf("#") > -1)
                {
                    s = s.substring(0, s.indexOf("#"));
                }
                s = s.trim();
                if(!s.equals(""))
                {
                    if(s.startsWith("v "))
                    {
                        s = s.substring(s.indexOf(" ") + 1).trim();
                        float af[] = new float[3];
                        for(int k = 0; k < 3; k++)
                        {
                            int j1 = s.indexOf(" ");
                            if(j1 > -1)
                            {
                                af[k] = Float.parseFloat(s.substring(0, j1));
                            } else
                            {
                                af[k] = Float.parseFloat(s.substring(0));
                            }
                            s = s.substring(s.indexOf(" ") + 1).trim();
                        }

                        float f = af[2];
                        af[2] = -af[1];
                        af[1] = f;
                        arraylist.add(new PositionTransformVertex(af[0], af[1], af[2], 0.0F, 0.0F));
                    } else
                    if(s.startsWith("vt "))
                    {
                        s = s.substring(s.indexOf(" ") + 1).trim();
                        float af1[] = new float[2];
                        for(int l = 0; l < 2; l++)
                        {
                            int k1 = s.indexOf(" ");
                            if(k1 > -1)
                            {
                                af1[l] = Float.parseFloat(s.substring(0, k1));
                            } else
                            {
                                af1[l] = Float.parseFloat(s.substring(0));
                            }
                            s = s.substring(s.indexOf(" ") + 1).trim();
                        }

                        arraylist1.add(new float[] {
                                af1[0], 1.0F - af1[1]
                        });
                    } else
                    if(s.startsWith("vn "))
                    {
                        s = s.substring(s.indexOf(" ") + 1).trim();
                        float af2[] = new float[3];
                        for(int i1 = 0; i1 < 3; i1++)
                        {
                            int l1 = s.indexOf(" ");
                            if(l1 > -1)
                            {
                                af2[i1] = Float.parseFloat(s.substring(0, l1));
                            } else
                            {
                                af2[i1] = Float.parseFloat(s.substring(0));
                            }
                            s = s.substring(s.indexOf(" ") + 1).trim();
                        }

                        float f1 = af2[2];
                        af2[2] = af2[1];
                        af2[1] = f1;
                        arraylist2.add(new float[] {
                                af2[0], af2[1], af2[2]
                        });
                    } else
                    if(s.startsWith("f "))
                    {
                        s = s.substring(s.indexOf(" ") + 1).trim();
                        ArrayList arraylist4 = new ArrayList();
                        int i2 = 0;
                        float af3[] = {
                                0.0F, 0.0F, 0.0F
                        };
                        do
                        {
                            int l2 = s.indexOf(" ");
                            String s1 = s;
                            if(l2 > -1)
                            {
                                s1 = s.substring(0, l2);
                            }
                            int j2;
                            float af4[];
                            float af5[];
                            if(s1.indexOf("/") > -1)
                            {
                                String as[] = s1.split("/");
                                j2 = Integer.parseInt(as[0]) - 1;
                                if(as[1].equals(""))
                                {
                                    as[1] = as[0];
                                }
                                int i3 = Integer.parseInt(as[1]) - 1;
                                if(arraylist1.size() > i3)
                                {
                                    af4 = (float[])arraylist1.get(i3);
                                } else
                                {
                                    af4 = (new float[] {
                                            0.0F, 0.0F
                                    });
                                }
                                int j3 = 0;
                                if(as.length == 3)
                                {
                                    if(as[2].equals(""))
                                    {
                                        as[2] = as[0];
                                    }
                                    j3 = Integer.parseInt(as[2]) - 1;
                                } else
                                {
                                    j3 = Integer.parseInt(as[0]) - 1;
                                }
                                if(arraylist2.size() > j3)
                                {
                                    af5 = (float[])arraylist2.get(j3);
                                } else
                                {
                                    af5 = (new float[] {
                                            0.0F, 0.0F, 0.0F
                                    });
                                }
                            } else
                            {
                                j2 = Integer.parseInt(s1) - 1;
                                if(arraylist1.size() > j2)
                                {
                                    af4 = (float[])arraylist1.get(j2);
                                } else
                                {
                                    af4 = (new float[] {
                                            0.0F, 0.0F
                                    });
                                }
                                if(arraylist2.size() > j2)
                                {
                                    af5 = (float[])arraylist2.get(j2);
                                } else
                                {
                                    af5 = (new float[] {
                                            0.0F, 0.0F, 0.0F
                                    });
                                }
                            }
                            af3[0] += af5[0];
                            af3[1] += af5[1];
                            af3[2] += af5[2];
                            if(j2 < arraylist.size())
                            {
                                arraylist4.add(((PositionTransformVertex)arraylist.get(j2)).method_983(af4[0], af4[1]));
                            }
                            if(l2 > -1)
                            {
                                s = s.substring(s.indexOf(" ") + 1).trim();
                            } else
                            {
                                i2++;
                            }
                        } while(i2 < 1);
                        float f2 = MathHelper.sqrt(af3[0] * af3[0] + af3[1] * af3[1] + af3[2] * af3[2]);
                        af3[0] /= f2;
                        af3[1] /= f2;
                        af3[2] /= f2;
                        QuadPoint apositiontexturevertex[] = new QuadPoint[arraylist4.size()];
                        for(int k2 = 0; k2 < arraylist4.size(); k2++)
                        {
                            apositiontexturevertex[k2] = (QuadPoint)arraylist4.get(k2);
                        }

                        TexturedPolygon texturedpolygon = new TexturedPolygon(apositiontexturevertex);
                        texturedpolygon.setNormals(af3[0], af3[1], af3[2]);
                        arraylist3.add(texturedpolygon);
                    }
                }
            } while(true);
            vertices = new PositionTransformVertex[arraylist.size()];
            for(int i = 0; i < arraylist.size(); i++)
            {
                vertices[i] = (PositionTransformVertex)arraylist.get(i);
            }

            faces = new TexturedPolygon[arraylist3.size()];
            for(int j = 0; j < arraylist3.size(); j++)
            {
                faces[j] = (TexturedPolygon)arraylist3.get(j);
            }

            bufferedreader.close();
        }
        catch(Throwable throwable) { }
    }
}

