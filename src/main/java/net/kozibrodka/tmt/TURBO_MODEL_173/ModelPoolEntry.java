package net.kozibrodka.tmt.TURBO_MODEL_173;
import java.io.File;
import java.util.Map;

public abstract class ModelPoolEntry
{

    public ModelPoolEntry()
    {
    }

    public File checkValidPath(String s)
    {
        File file = null;
        for(int i = 0; i < fileExtensions.length && (file == null || !file.exists()); i++)
        {
            String s1 = s;
            if(!s.endsWith((new StringBuilder()).append(".").append(fileExtensions[i]).toString()))
            {
                s1 = (new StringBuilder()).append(s1).append(".").append(fileExtensions[i]).toString();
            }
            file = new File(s1);
        }

        if(file == null || !file.exists())
        {
            return null;
        } else
        {
            return file;
        }
    }

    public abstract void getModel(File file);

    protected void setGroup(String s)
    {
        setGroup(s, new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D);
    }

    protected void setGroup(String s, Bone bone, double d)
    {
        if(!groups.containsKey(s))
        {
            groups.put(s, new TransformGroupBone(bone, d));
        }
        group = (TransformGroupBone)groups.get(s);
    }

    public PositionTransformVertex vertices[];
    public TexturedPolygon faces[];
    public Map groups;
    protected TransformGroupBone group;
    protected String fileExtensions[];
}
