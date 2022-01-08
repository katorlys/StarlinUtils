package com.github.katorly.starlin_l2.backup;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageSender {
    /**
     * Replace "&" with "§" to fix color messages.
     * 
     * @param string
     * @return
     */
    public static String Color(String string) {
        return Objects.requireNonNull(string).replace("&", "§");
    }

    /**
     * Send message to a player.
     * 
     * @param player
     * @param message
     */
    public static void sendMessage(Player player, String message) {
        player.sendMessage(MessageSender.Color(message));
    }

    /**
     * Send message to all online players.
     * 
     * @param message
     */
    public static void broadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(MessageSender.Color(message));
        }
    }

    /**
     * Send message to console and all online players.
     * 
     * @param message
     */
    public static void broadcastMessageAll(String message) {
        Bukkit.broadcastMessage(MessageSender.Color(message));
    }

    /**
     * Send title to a player.
     * 
     * @param player
     * @param title
     * @param subtitle
     */
    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(MessageSender.Color(title), MessageSender.Color(subtitle), 10, 40, 20); // Show title 2s
    }

    /**
     * Send title to all online players.
     * 
     * @param title
     * @param subtitle
     */
    public static void broadcastTitle(String title, String subtitle) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(MessageSender.Color(title), MessageSender.Color(subtitle), 10, 40, 20); // Show title 2s
        }
    }
}