package me.cooltimmetje.RoodCore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

/**
 * This class has been created on 8-3-2015 at 17:21 by cooltimmetje.
 */
public class CustomRecipes {

    public static ShapedRecipe boneMealGrind =
            new ShapedRecipe( new ItemStack(Material.INK_SACK, 24, (byte) 15))
                    .shape(new String[] { " % ","%*%"," % "})
                    .setIngredient('%', Material.BONE)
                    .setIngredient('*', Material.FLINT);

}
