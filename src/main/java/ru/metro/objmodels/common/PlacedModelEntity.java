package ru.metro.objmodels.common;

import io.netty.buffer.ByteBuf;import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.entity.Entity;import net.minecraft.nbt.NBTTagCompound;import net.minecraft.world.World;import net.minecraft.util.math.AxisAlignedBB;

public class PlacedModelEntity extends Entity implements IEntityAdditionalSpawnData {
 private String model=""; private float rotation;
 public PlacedModelEntity(World w){super(w);setSize(1,1);}
 public PlacedModelEntity(World w,double x,double y,double z,String id,float rot){this(w);model=id;rotation=rot;setPosition(x,y,z);refreshBounds();}
 public String getModel(){return model;} public float getRotation(){return rotation;}
 private void refreshBounds(){AxisAlignedBB b=ObjModelsRegistry.bounds(model);if(b!=null){double sx=Math.max(.1,b.maxX-b.minX), sy=Math.max(.1,b.maxY-b.minY), sz=Math.max(.1,b.maxZ-b.minZ);double scale=1.0/Math.max(1.0,Math.max(sx,Math.max(sy,sz)));sx*=scale;sy*=scale;sz*=scale;double radians=Math.toRadians(rotation), halfX=(Math.abs(Math.cos(radians))*sx+Math.abs(Math.sin(radians))*sz)/2,halfZ=(Math.abs(Math.sin(radians))*sx+Math.abs(Math.cos(radians))*sz)/2;setSize((float)Math.min(16,2*halfX),(float)Math.min(16,sy));setEntityBoundingBox(new AxisAlignedBB(posX-halfX,posY,posZ-halfZ,posX+halfX,posY+sy,posZ+halfZ));}}
 @Override protected void entityInit(){}
 @Override protected void readEntityFromNBT(NBTTagCompound n){model=n.getString("Model");rotation=n.getFloat("Rotation");}
 @Override protected void writeEntityToNBT(NBTTagCompound n){n.setString("Model",model);n.setFloat("Rotation",rotation);}
 @Override public void writeSpawnData(ByteBuf b){byte[] a=model.getBytes(java.nio.charset.StandardCharsets.UTF_8);b.writeShort(a.length);b.writeBytes(a);b.writeFloat(rotation);}
 @Override public void readSpawnData(ByteBuf b){byte[]a=new byte[b.readUnsignedShort()];b.readBytes(a);model=new String(a,java.nio.charset.StandardCharsets.UTF_8);rotation=b.readFloat();refreshBounds();}
 @Override public void onUpdate(){super.onUpdate();if(!world.isRemote)refreshBounds();}
 @Override public boolean canBeCollidedWith(){return true;}
}
