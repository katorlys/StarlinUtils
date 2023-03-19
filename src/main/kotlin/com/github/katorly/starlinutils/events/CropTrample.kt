package com.github.katorly.starlinutils.events

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

/**
 * 防止耕地被踩踏.
 *
 */
class CropTrample : Listener {
    @EventHandler
    fun onCropTrample(e: PlayerInteractEvent) {
        if (e.action == Action.PHYSICAL && e.clickedBlock!!.type == Material.FARMLAND) {
            e.isCancelled = true
        }
    }
}