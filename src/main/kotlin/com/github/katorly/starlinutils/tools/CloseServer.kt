package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.ConfigHandler.config
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.StarlinUtils.plugin
import com.github.katorly.starlinutils.utils.Messager.bt
import taboolib.common.platform.function.onlinePlayers
import taboolib.common.platform.function.submit

/**
 * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
 * 若无, 则直接关闭服务器.
 *
 */
object CloseServer {
    fun close() {
        if (onlinePlayers().isNotEmpty()) {
            StarlinUtils.serverClosing = true
            bt(
                "&b&l服务器 ${config.getInt("close-countdown")} 秒后重启",
                "&7请保管好个人物品!"
            )
            submit(delay = (config.getInt("close-countdown").times(20)).toLong()) {
                plugin.server.shutdown()
            }
        } else {
            plugin.server.shutdown()
        }
    }
}