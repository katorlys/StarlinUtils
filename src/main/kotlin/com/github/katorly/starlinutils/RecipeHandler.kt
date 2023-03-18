package com.github.katorly.starlinutils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapelessRecipe

class RecipeHandler {
    companion object {
        fun registerConcreteRecipe() { // 插件加载时注册混凝土合成表
            concreteRecipe(Material.BLACK_CONCRETE, Material.BLACK_DYE)
            concreteRecipe(Material.BLUE_CONCRETE, Material.BLUE_DYE)
            concreteRecipe(Material.BROWN_CONCRETE, Material.BROWN_DYE)
            concreteRecipe(Material.CYAN_CONCRETE, Material.CYAN_DYE)
            concreteRecipe(Material.GRAY_CONCRETE, Material.GRAY_DYE)
            concreteRecipe(Material.GREEN_CONCRETE, Material.GREEN_DYE)
            concreteRecipe(Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_BLUE_DYE)
            concreteRecipe(Material.LIGHT_GRAY_CONCRETE, Material.LIGHT_GRAY_DYE)
            concreteRecipe(Material.LIME_CONCRETE, Material.LIME_DYE)
            concreteRecipe(Material.MAGENTA_CONCRETE, Material.MAGENTA_DYE)
            concreteRecipe(Material.ORANGE_CONCRETE, Material.ORANGE_DYE)
            concreteRecipe(Material.PINK_CONCRETE, Material.PINK_DYE)
            concreteRecipe(Material.PURPLE_CONCRETE, Material.PURPLE_DYE)
            concreteRecipe(Material.RED_CONCRETE, Material.RED_DYE)
            concreteRecipe(Material.WHITE_CONCRETE, Material.WHITE_DYE)
            concreteRecipe(Material.YELLOW_CONCRETE, Material.YELLOW_DYE)
        }

        fun concreteRecipe(concrete: Material, dye: Material?) { // 1 * 彩色染料 + 8 * 石头 = 8 * 彩色混凝土.
            var concreteRecipe = ShapelessRecipe(
                NamespacedKey(StarlinUtils.INSTANCE, concrete.toString() + "_starlin_recipe"),
                ItemStack(concrete, 8)
            )
            concreteRecipe = concreteRecipe.addIngredient(1, dye!!).addIngredient(8, Material.STONE)
            Bukkit.addRecipe(concreteRecipe)
            StarlinUtils.recipeKeys.add(concreteRecipe.key)
        }
    }
}