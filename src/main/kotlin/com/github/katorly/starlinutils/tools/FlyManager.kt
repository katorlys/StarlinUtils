/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.StarlinUtils
import net.luckperms.api.model.group.Group
import net.luckperms.api.model.group.GroupManager
import net.luckperms.api.node.Node

object FlyManager {
    /**
     * 若 fly 权限组不存在, 则自动创建
     *
     *
     */
    fun setup() {
        if (StarlinUtils.islp()) {
            val gm: GroupManager = StarlinUtils.lp.groupManager
            gm.modifyGroup("fly") { group: Group ->
                group.data().add(Node.builder("essentials.fly").value(true).build())
                group.data().add(Node.builder("essentials.fly.safelogin").value(true).build())
            }
        }
    }
}