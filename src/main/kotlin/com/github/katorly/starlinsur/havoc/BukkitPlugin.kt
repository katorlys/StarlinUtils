/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import java.io.File

/**
 * HavocBukkit
 * havocbukkit.core.BukkitPlugin
 *
 * Set & get some basic plugin information.
 *
 * @author Katorly
 * @since 0.0.0
 */

/** Plugin Name */
var name: String = plugin.description.name

/* Plugin Prefix, used for loggers */
var prefix: String = "[${plugin.description.name}] "

/* Plugin Version */
var version: String = plugin.description.version

/* Plugin Author */
var author: MutableList<String> = plugin.description.authors

/* Plugin Description */
var description: String = plugin.description.description ?: "null"
var desc: String = description

/* Plugin Bukkit API version */
var apiVersion: String = plugin.description.apiVersion ?: "null"

/* Plugin depend */
var depend: MutableList<String> = plugin.description.depend

/* Plugin soft depend */
var softDepend: MutableList<String> = plugin.description.softDepend

/* Plugin data folder */
var folder: File = plugin.dataFolder