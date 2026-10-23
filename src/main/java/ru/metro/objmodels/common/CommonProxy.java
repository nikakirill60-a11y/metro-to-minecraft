package ru.metro.objmodels.common;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import ru.metro.objmodels.ObjModels;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        ObjModelsRegistry.init(e.getModConfigurationDirectory());
        EntityRegistry.registerModEntity(new net.minecraft.util.ResourceLocation(ObjModels.MODID,"placed_model"), PlacedModelEntity.class, "placed_model", 1, ObjModels.INSTANCE, 64, 1, true);
        ModelItem.register();
    }
    public void init(net.minecraftforge.fml.common.event.FMLInitializationEvent e) { }
}
