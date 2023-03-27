package com.github.katorly.starlinutils.events

import org.bukkit.Material
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * 防止耕地被踩踏.
 *
 */
object CropTrample {
    @SubscribeEvent
    fun onCropTrample(e: PlayerInteractEvent) {
        if (e.action == Action.PHYSICAL && e.clickedBlock!!.type == Material.FARMLAND) {
            e.isCancelled = true
        }
    }
}