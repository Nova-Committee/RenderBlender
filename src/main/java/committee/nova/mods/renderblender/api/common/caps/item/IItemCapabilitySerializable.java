package committee.nova.mods.renderblender.api.common.caps.item;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2025/3/6 13:55
 * @Description:
 */
public interface IItemCapabilitySerializable extends INBTSerializable<Tag> {
    String getStorageKey();
}
