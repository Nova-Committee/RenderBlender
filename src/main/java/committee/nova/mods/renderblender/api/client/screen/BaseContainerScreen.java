package committee.nova.mods.renderblender.api.client.screen;

import committee.nova.mods.renderblender.api.iface.IDataReceiver;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/4/2 11:37
 * Version: 1.0
 */
public abstract class BaseContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> implements ContainerListener, IDataReceiver {
    protected ResourceLocation bgTexture;
    protected int bgImgWidth;
    protected int bgImgHeight;

    public BaseContainerScreen(T container, Inventory inventory, Component title) {
        this(container, inventory, title, null);
    }

    public BaseContainerScreen(T container, Inventory inventory, Component title, ResourceLocation bgTexture) {
        this(container, inventory, title, bgTexture, 176, 166, 256, 256);
    }

    public BaseContainerScreen(T container, Inventory inventory, Component title, ResourceLocation bgTexture, int bgWidth, int bgHeight) {
        this(container, inventory, title, bgTexture, bgWidth, bgHeight, 256, 256);
    }

    public BaseContainerScreen(T container, Inventory inventory, Component title, ResourceLocation bgTexture, int bgWidth, int bgHeight, int bgImgWidth, int bgImgHeight) {
        super(container, inventory, title);
        this.imageWidth = bgWidth;
        this.imageHeight = bgHeight;
        this.bgTexture = bgTexture;
        this.bgImgWidth = bgImgWidth;
        this.bgImgHeight = bgImgHeight;
    }

    protected static String number(Object number) {
        return NumberFormat.getInstance().format(number);
    }

    protected static String fraction(Object number) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(number);
    }

    protected void subInit() {
    }

    @Override
    protected void init() {
        super.init();
        this.subInit();
        this.menu.addSlotListener(this);
    }

    @Override
    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderFg(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    protected void renderFg(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        if (this.bgTexture != null) pGuiGraphics.blit(this.bgTexture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.bgImgWidth, this.bgImgHeight);
        this.renderBgOthers(pGuiGraphics, this.leftPos, this.topPos);
    }

    protected abstract void renderBgOthers(GuiGraphics pGuiGraphics, int pX, int pY);


    public void dataChanged(@NotNull AbstractContainerMenu pContainerMenu, int pDataSlotIndex, int pValue) {
    }

    public void slotChanged(@NotNull AbstractContainerMenu pContainerToSend, int pSlotInd, @NotNull ItemStack pStack) {
    }

    @Override
    public void receive(CompoundTag tag) {

    }
}
