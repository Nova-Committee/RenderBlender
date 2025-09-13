package committee.nova.mods.renderblender.client;

import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.client.model.*;
import committee.nova.mods.renderblender.client.shader.RBShaders;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        event.register("halo_cosmic", HaloCosmicModelLoader.INSTANCE);
        event.register("halo_eternal", HaloEternalModelLoader.INSTANCE);
    }
}
