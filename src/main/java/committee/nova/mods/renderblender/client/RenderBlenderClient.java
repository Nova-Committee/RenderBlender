package committee.nova.mods.renderblender.client;

import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.client.model.*;
import committee.nova.mods.renderblender.client.model.cosmic.CosmicModelLoader;
import committee.nova.mods.renderblender.client.model.cosmic.HaloCosmicModelLoader;
import committee.nova.mods.renderblender.client.model.eternal.EternalModelLoader;
import committee.nova.mods.renderblender.client.model.eternal.HaloEternalModelLoader;
import committee.nova.mods.renderblender.client.model.hell.HaloHellModelLoader;
import committee.nova.mods.renderblender.client.model.hell.HellModelLoader;
import committee.nova.mods.renderblender.client.model.unstable.HaloUnstableModelLoader;
import committee.nova.mods.renderblender.client.model.unstable.UnstableModelLoader;
import committee.nova.mods.renderblender.client.shader.RBShaders;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RenderBlenderLib.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderBlenderClient {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRegisterShaders(RegisterShadersEvent event) {
        RBShaders.onRegisterShaders(event);//注册着色器
    }

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register("cosmic", CosmicModelLoader.INSTANCE);
        event.register("halo", HaloModelLoader.INSTANCE);
        event.register("eternal", EternalModelLoader.INSTANCE);
        event.register("hell", HellModelLoader.INSTANCE);
        event.register("unstable", UnstableModelLoader.INSTANCE);
        event.register("halo_unstable", HaloUnstableModelLoader.INSTANCE);
        event.register("halo_hell", HaloHellModelLoader.INSTANCE);
        event.register("halo_cosmic", HaloCosmicModelLoader.INSTANCE);
        event.register("halo_eternal", HaloEternalModelLoader.INSTANCE);
        event.register("glow_edge", GlowEdgeModelLoader.INSTANCE);
        event.register("scale", ScaleModelLoader.INSTANCE);
    }
}
