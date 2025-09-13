package committee.nova.mods.renderblender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import committee.nova.mods.renderblender.init.data.ModDataGen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RenderBlenderLib.MOD_ID)
public class RenderBlenderLib
{

    public static final String MOD_ID = "renderblender";

    public static final Logger LOGGER = LogUtils.getLogger();
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create();
    public RenderBlenderLib()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ModDataGen::gatherData);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
}
