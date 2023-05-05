/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.PlayTime.initPlayTime
import com.github.katorly.starlinutils.utils.Messager.sm
import com.github.katorly.starlinutils.utils.Messager.st
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit

object PlayerJoin {
    @SubscribeEvent
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val p: Player = e.player

        /**
         * 检测服务器是否即将关闭, 若是, 则提醒玩家.
         *
         */
        if (StarlinUtils.serverClosing) {
            st(p, "&b&l服务器即将重启", "&7请保管好个人物品!")
        }

        // PlayTime 相关功能
        initPlayTime(p)

        /**
         * 防卡下界门: 若玩家登录地点为下界门,
         * 则传送其至出生点, 防止玩家无法使用登录功能.
         *
         * 正版验证为否时打开.
         *
         */
        if (!Bukkit.getOnlineMode()) {
            val pl: Location = p.location
            val b: Block = pl.block
            val bf: Array<BlockFace> = BlockFace.values()
            for (blockface in bf) {
                if (b.getRelative(blockface).type == Material.NETHER_PORTAL) {
                    submit(delay = 4L) {
                        p.teleport(pl.world!!.spawnLocation)
                        sm(
                            p, "${prefix}检测到您在下界门处上线, 为防止您无法正常登录, 已将您传送到出生点!"
                        )
                        val l: Location = b.getRelative(blockface).location
                        val x0 = String.format("%.2f", l.x)
                        val y0 = String.format("%.2f", l.y)
                        val z0 = String.format("%.2f", l.z)
                        sm(p, "${prefix}下界门位置: $x0, $y0, $z0")
                        cancel()
                    }
                    break
                }
            }
        }
    }
}