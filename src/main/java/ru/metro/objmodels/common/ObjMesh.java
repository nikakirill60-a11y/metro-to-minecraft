package ru.metro.objmodels.common;

import java.io.*;import java.nio.charset.StandardCharsets;import java.util.*;
import net.minecraft.util.math.AxisAlignedBB;

/** Small Wavefront OBJ reader: v/vt/vn and polygon faces; negative indices are supported. */
public class ObjMesh {
 public final List<float[]> vertices=new ArrayList<>(), uvs=new ArrayList<>(); public final List<Face> faces=new ArrayList<>();
 public static class Face { public final int[] v,t; Face(int[]v,int[]t){this.v=v;this.t=t;} }
 public static ObjMesh load(File f) throws IOException {
  ObjMesh m=new ObjMesh(); BufferedReader r=new BufferedReader(new InputStreamReader(new FileInputStream(f),StandardCharsets.UTF_8)); String s;
  while((s=r.readLine())!=null){s=s.trim(); if(s.startsWith("v ")){String[]a=s.split("\\s+");m.vertices.add(new float[]{Float.parseFloat(a[1]),Float.parseFloat(a[2]),Float.parseFloat(a[3])});}
   else if(s.startsWith("vt ")){String[]a=s.split("\\s+");m.uvs.add(new float[]{Float.parseFloat(a[1]),1-Float.parseFloat(a[2])});}
   else if(s.startsWith("f ")){String[]a=s.substring(2).trim().split("\\s+");int n=a.length;int[]v=new int[n],t=new int[n];for(int i=0;i<n;i++){String[]b=a[i].split("/");v[i]=index(b[0],m.vertices.size());t[i]=b.length>1&&!b[1].isEmpty()?index(b[1],m.uvs.size()):-1;}m.faces.add(new Face(v,t));}
  }r.close(); if(m.vertices.isEmpty()||m.faces.isEmpty())throw new IOException("OBJ has no renderable faces"); return m;
 }
 private static int index(String s,int size){int i=Integer.parseInt(s);return i<0?size+i:i-1;}
 public AxisAlignedBB bounds(){double x0=1e9,y0=1e9,z0=1e9,x1=-1e9,y1=-1e9,z1=-1e9;for(float[]v:vertices){x0=Math.min(x0,v[0]);y0=Math.min(y0,v[1]);z0=Math.min(z0,v[2]);x1=Math.max(x1,v[0]);y1=Math.max(y1,v[1]);z1=Math.max(z1,v[2]);}return new AxisAlignedBB(x0,y0,z0,x1,y1,z1);}
}
