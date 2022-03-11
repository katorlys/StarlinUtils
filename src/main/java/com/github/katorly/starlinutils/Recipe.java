package com.github.katorly.starlinutils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class Recipe {
    public static void registerConcreteRecipe() { //Add colored concrete recipe onEnable
        Recipe.concreteRecipe(Material.BLACK_CONCRETE, Material.BLACK_DYE);
        Recipe.concreteRecipe(Material.BLUE_CONCRETE, Material.BLUE_DYE);
        Recipe.concreteRecipe(Material.BROWN_CONCRETE, Material.BROWN_DYE);
        Recipe.concreteRecipe(Material.CYAN_CONCRETE, Material.CYAN_DYE);
        Recipe.concreteRecipe(Material.GRAY_CONCRETE, Material.GRAY_DYE);
        Recipe.concreteRecipe(Material.GREEN_CONCRETE, Material.GREEN_DYE);
        Recipe.concreteRecipe(Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_BLUE_DYE);
        Recipe.concreteRecipe(Material.LIGHT_GRAY_CONCRETE, Material.LIGHT_GRAY_DYE);
        Recipe.concreteRecipe(Material.LIME_CONCRETE, Material.LIME_DYE);
        Recipe.concreteRecipe(Material.MAGENTA_CONCRETE, Material.MAGENTA_DYE);
        Recipe.concreteRecipe(Material.ORANGE_CONCRETE, Material.ORANGE_DYE);
        Recipe.concreteRecipe(Material.PINK_CONCRETE, Material.PINK_DYE);
        Recipe.concreteRecipe(Material.PURPLE_CONCRETE, Material.PURPLE_DYE);
        Recipe.concreteRecipe(Material.RED_CONCRETE, Material.RED_DYE);
        Recipe.concreteRecipe(Material.WHITE_CONCRETE, Material.WHITE_DYE);
        Recipe.concreteRecipe(Material.YELLOW_CONCRETE, Material.YELLOW_DYE);
    }

    public static void concreteRecipe(Material concrete, Material dye) { //8*colored concrete = 1*dye + 8*stones.
        ShapelessRecipe concreteRecipe = new ShapelessRecipe(new NamespacedKey(StarlinUtils.INSTANCE, concrete.toString() + "_recipe"), new ItemStack(concrete, 8));
        concreteRecipe = concreteRecipe.addIngredient(1, dye).addIngredient(8, Material.STONE);
        Bukkit.addRecipe(concreteRecipe);
    }
}