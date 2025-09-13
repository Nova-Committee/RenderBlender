package committee.nova.mods.renderblender.api.iface;

import net.minecraft.nbt.CompoundTag;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2025/2/23 01:46
 * @Description:
 */
public interface IDataReceiver {
    void receive(CompoundTag tag);
}
