package committee.nova.mods.renderblender.api.common.crafting;

/**
 * @Project: renderblender
 * @Author: cnlimiter
 * @CreateTime: 2024/10/12 23:00
 * @Description:
 */
public interface ITierRecipe {

    public int getTier();

    public boolean hasRequiredTier();
}
