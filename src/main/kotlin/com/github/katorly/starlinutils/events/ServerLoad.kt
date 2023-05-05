/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.tools.GameruleHandler.setGamerule
import org.bukkit.event.server.ServerLoadEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info

/**
 * 服务器加载完成后自动执行游戏规则自动设置.
 *
 */
object ServerLoad {
    @SubscribeEvent
    fun onServerLoaded(e: ServerLoadEvent) {
        info("[StarlinUtils] 执行游戏规则自动设置完毕, 共更改 ${setGamerule()} 处.")
    }
}