package ru.metro.objmodels;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.metro.objmodels.common.CommonProxy;
import ru.metro.objmodels.network.PlaceModelMessage;

@Mod(modid=ObjModels.MODID, name="OBJ Models", version="1.0.0", acceptableRemoteVersions="*")
public class ObjModels {
    public static final String MODID="objmodels";
    @Mod.Instance(MODID) public static ObjModels INSTANCE;
    @SidedProxy(clientSide="ru.metro.objmodels.client.ClientProxy", serverSide="ru.metro.objmodels.common.CommonProxy") public static CommonProxy proxy;
    public static SimpleNetworkWrapper NETWORK;
    @Mod.EventHandler public void preInit(FMLPreInitializationEvent e) {
        NETWORK= NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        NETWORK.registerMessage(PlaceModelMessage.Handler.class, PlaceModelMessage.class, 0, Side.SERVER);
        proxy.preInit(e);
    }
    @Mod.EventHandler public void init(FMLInitializationEvent e) { proxy.init(e); }
}
