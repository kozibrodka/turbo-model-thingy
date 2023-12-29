package net.kozibrodka.tmt.TURBO_MODEL_173;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class ModelPool
{

    public ModelPool()
    {
    }

    public static ModelPoolEntry addFile(String s, Class class1, Map map)
    {
        if(modelMap.containsKey(s))
        {
            return (ModelPoolEntry)modelMap.get(s);
        }
        ModelPoolEntry modelpoolentry = null;
        try
        {
            modelpoolentry = (ModelPoolEntry)class1.newInstance();
        }
        catch(Exception exception)
        {
            return null;
        }
        File file = null;
        for(int i = 0; i < resourceDir.length && (file == null || !file.exists()); i++)
        {
            String s1 = Minecraft.getWorkingDirectory(resourceDir[i]).getAbsolutePath();
            if(!s1.endsWith("/") || !s1.endsWith("\\"))
            {
                s1 = (new StringBuilder()).append(s1).append("/").toString();
            }
            file = modelpoolentry.checkValidPath((new StringBuilder()).append(s1).append(s).toString());
        }

        if(file == null || !file.exists())
        {
            return null;
        } else
        {
            modelpoolentry.groups = map;
            modelpoolentry.setGroup("0");
            modelpoolentry.getModel(file);
            modelMap.put(s, modelpoolentry);
            return modelpoolentry;
        }
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    private static Map modelMap = new HashMap();
    private static String resourceDir[] = {
            "minecraft/resources/models/", "minecraft/resources/mod/models/"
    };
    public static final Class OBJ;

    static
    {
        OBJ = ModelPoolObjEntry.class; //net.minecraft.src.ModelPoolObjEntry.class;
    }
}
