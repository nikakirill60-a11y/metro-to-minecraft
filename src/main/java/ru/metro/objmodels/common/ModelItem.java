package ru.metro.objmodels.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.metro.objmodels.ObjModels;

public class ModelItem extends Item {
    public static final ModelItem INSTANCE=new ModelItem();
    private ModelItem(){ setRegistryName(ObjModels.MODID,"model_tool"); setUnlocalizedName(ObjModels.MODID+".model_tool"); setCreativeTab(CreativeTabs.DECORATIONS); setMaxStackSize(1); }
    public static void register(){ GameRegistry.register(INSTANCE); }
    @Override public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        if(world.isRemote) ru.metro.objmodels.client.GuiModels.open();
        return new ActionResult<>(EnumActionResult.SUCCESS,player.getHeldItem(hand));
    }
}
