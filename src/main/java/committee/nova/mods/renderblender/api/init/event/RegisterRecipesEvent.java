package committee.nova.mods.renderblender.api.init.event;

import committee.nova.mods.renderblender.api.utils.RecipeUtils;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class RegisterRecipesEvent extends Event {
    private final RecipeManager manager;

    public RegisterRecipesEvent(RecipeManager manager) {
        this.manager = manager;
    }

    public RecipeManager getRecipeManager() {
        return this.manager;
    }

    public void addRecipe(Recipe<?> recipe) {
        RecipeUtils.addRecipe(recipe);
    }
}
    
