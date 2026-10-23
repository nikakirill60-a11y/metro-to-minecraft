package ru.metro.objmodels.common;
import java.io.*;import java.util.*;import net.minecraft.util.math.AxisAlignedBB;
public final class ObjModelsRegistry {
 public static File root; private static final Map<String,ObjMesh>CACHE=new HashMap<>();
 public static void init(File config){root=new File(config,"obj");if(!root.exists())root.mkdirs();}
 public static List<String> names(){List<String> out=new ArrayList<>();if(root==null)return out;File[]fs=root.listFiles();if(fs!=null)for(File f:fs)if(f.isDirectory()&&new File(f,"model.obj").isFile())out.add(f.getName());Collections.sort(out);return out;}
 public static ObjMesh mesh(String id){if(root==null||id==null||id.contains("..")||id.contains("/")||id.contains("\\"))return null;if(CACHE.containsKey(id))return CACHE.get(id);try{ObjMesh m=ObjMesh.load(new File(new File(root,id),"model.obj"));CACHE.put(id,m);return m;}catch(Exception e){return null;}}
 public static AxisAlignedBB bounds(String id){ObjMesh m=mesh(id);return m==null?null:m.bounds();}
 public static File texture(String id){return new File(new File(root,id),"texture.png");}
}
