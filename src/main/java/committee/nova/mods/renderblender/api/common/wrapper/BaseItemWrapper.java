package committee.nova.mods.renderblender.api.common.wrapper;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2025/2/1 02:48
 * @Description:
 */
public interface BaseItemWrapper extends IItemHandlerModifiable, INBTSerializable<CompoundTag> {
}
