package committee.nova.mods.renderblender.client.shader;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.api.client.shader.CCShaderInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = RenderBlenderLib.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RBShaders {
    public static boolean inventoryRender = false;
    public static int renderTime;
    public static float renderFrame;

    public static CCShaderInstance COSMIC_SHADER;
    public static CCShaderInstance COSMIC_ARMOR_SHADER;
    public static CCShaderInstance ETERNAL_SHADER;
    public static CCShaderInstance HELL_SHADER;
    public static CCShaderInstance UNSTABLE_SHADER;

    public static Uniform cosmicTime;
    public static Uniform cosmicYaw;
    public static Uniform cosmicPitch;
    public static Uniform cosmicExternalScale;
    public static Uniform cosmicOpacity;
    public static Uniform cosmicUVs;

    public static Uniform eternalTime;
    public static Uniform eternalYaw;
    public static Uniform eternalPitch;
    public static Uniform eternalExternalScale;
    public static Uniform eternalOpacity;
    public static Uniform eternalUVs;

    public static Uniform hellTime;
    public static Uniform hellYaw;
    public static Uniform hellPitch;
    public static Uniform hellExternalScale;
    public static Uniform hellOpacity;
    public static Uniform hellUVs;

    public static Uniform unstableTime;
    public static Uniform unstableYaw;
    public static Uniform unstablePitch;
    public static Uniform unstableExternalScale;
    public static Uniform unstableOpacity;
    public static Uniform unstableUVs;

    public static void onRegisterShaders(RegisterShadersEvent event) {
        COSMIC_SHADER = CCShaderInstance.create(event.getResourceProvider(), new ResourceLocation(RenderBlenderLib.MOD_ID, "cosmic"), DefaultVertexFormat.BLOCK);
        COSMIC_ARMOR_SHADER = CCShaderInstance.create(event.getResourceProvider(), new ResourceLocation(RenderBlenderLib.MOD_ID, "cosmic"), DefaultVertexFormat.NEW_ENTITY);
        ETERNAL_SHADER = CCShaderInstance.create(event.getResourceProvider(), new ResourceLocation(RenderBlenderLib.MOD_ID, "eternal"), DefaultVertexFormat.BLOCK);
        HELL_SHADER = CCShaderInstance.create(event.getResourceProvider(), new ResourceLocation(RenderBlenderLib.MOD_ID, "hell"), DefaultVertexFormat.BLOCK);
        UNSTABLE_SHADER = CCShaderInstance.create(event.getResourceProvider(), new ResourceLocation(RenderBlenderLib.MOD_ID, "unstable"), DefaultVertexFormat.BLOCK);

        event.registerShader(COSMIC_SHADER, RBShaders::cosmicShader);
        event.registerShader(COSMIC_ARMOR_SHADER, RBShaders::cosmicShader);
        event.registerShader(ETERNAL_SHADER, RBShaders::eternalShader);
        event.registerShader(HELL_SHADER, RBShaders::hellShader);
        event.registerShader(UNSTABLE_SHADER, RBShaders::unstableShader);
    }

    public static void cosmicShader(ShaderInstance e){
        COSMIC_SHADER = (CCShaderInstance) e;
        cosmicTime = Objects.requireNonNull(COSMIC_SHADER.getUniform("time"));
        cosmicYaw = Objects.requireNonNull(COSMIC_SHADER.getUniform("yaw"));
        cosmicPitch = Objects.requireNonNull(COSMIC_SHADER.getUniform("pitch"));
        cosmicExternalScale = Objects.requireNonNull(COSMIC_SHADER.getUniform("externalScale"));
        cosmicOpacity = Objects.requireNonNull(COSMIC_SHADER.getUniform("opacity"));
        cosmicUVs = Objects.requireNonNull(COSMIC_SHADER.getUniform("cosmicuvs"));
        cosmicTime.set((float) renderTime + renderFrame);
        COSMIC_SHADER.onApply(() -> {
            cosmicTime.set((float) renderTime + renderFrame);
        });
    }

    public static void eternalShader(ShaderInstance e){
        ETERNAL_SHADER = (CCShaderInstance) e;
        eternalTime = Objects.requireNonNull(ETERNAL_SHADER.getUniform("time"));
        eternalYaw = Objects.requireNonNull(ETERNAL_SHADER.getUniform("yaw"));
        eternalPitch = Objects.requireNonNull(ETERNAL_SHADER.getUniform("pitch"));
        eternalExternalScale = Objects.requireNonNull(ETERNAL_SHADER.getUniform("externalScale"));
        eternalOpacity = Objects.requireNonNull(ETERNAL_SHADER.getUniform("opacity"));
        eternalUVs = Objects.requireNonNull(ETERNAL_SHADER.getUniform("cosmicuvs"));
        eternalTime.set((float) renderTime + renderFrame);
        ETERNAL_SHADER.onApply(() -> {
            eternalTime.set((float) renderTime + renderFrame);
        });
    }

    public static void hellShader(ShaderInstance e){
        HELL_SHADER = (CCShaderInstance) e;
        hellTime = Objects.requireNonNull(HELL_SHADER.getUniform("time"));
        hellYaw = Objects.requireNonNull(HELL_SHADER.getUniform("yaw"));
        hellPitch = Objects.requireNonNull(HELL_SHADER.getUniform("pitch"));
        hellExternalScale = Objects.requireNonNull(HELL_SHADER.getUniform("externalScale"));
        hellOpacity = Objects.requireNonNull(HELL_SHADER.getUniform("opacity"));
        hellUVs = Objects.requireNonNull(HELL_SHADER.getUniform("cosmicuvs"));
        hellTime.set((float) renderTime + renderFrame);
        HELL_SHADER.onApply(() -> {
            hellTime.set((float) renderTime + renderFrame);
        });
    }

    public static void unstableShader(ShaderInstance e){
        UNSTABLE_SHADER = (CCShaderInstance) e;
        unstableTime = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("time"));
        unstableYaw = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("yaw"));
        unstablePitch = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("pitch"));
        unstableExternalScale = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("externalScale"));
        unstableOpacity = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("opacity"));
        unstableUVs = Objects.requireNonNull(UNSTABLE_SHADER.getUniform("cosmicuvs"));
        unstableTime.set((float) renderTime + renderFrame);
        UNSTABLE_SHADER.onApply(() -> {
            unstableTime.set((float) renderTime + renderFrame);
        });
    }
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (!Minecraft.getInstance().isPaused() && event.phase == TickEvent.Phase.END) {
            ++renderTime;
        }
    }

    @SubscribeEvent
    public static void renderTick(TickEvent.RenderTickEvent event) {
        if (!Minecraft.getInstance().isPaused() && event.phase == TickEvent.Phase.START) {
            renderFrame = event.renderTickTime;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void drawScreenPre(final ScreenEvent.Render.Pre e) {
        RBShaders.inventoryRender = true;
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void drawScreenPost(final ScreenEvent.Render.Post e) {
        RBShaders.inventoryRender = false;
    }
}
