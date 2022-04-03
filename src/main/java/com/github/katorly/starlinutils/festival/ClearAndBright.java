package com.github.katorly.starlinutils.festival;

import java.text.SimpleDateFormat;
import java.util.Objects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.github.katorly.starlinutils.StarlinUtils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class ClearAndBright {
    public static boolean isToday(String date) {
        long t = System.currentTimeMillis();
        SimpleDateFormat d = new SimpleDateFormat("MM-dd");
        String n = d.format(t);
        if (Objects.equals(n, date))
            return true;
        else
            return false;
    }
    
    public static void grayChatting() {
        StarlinUtils.cab.addPacketListener(new PacketAdapter(PacketAdapter.params().plugin(StarlinUtils.INSTANCE).serverSide().listenerPriority(ListenerPriority.NORMAL).gamePhase(GamePhase.PLAYING).optionAsync().options(ListenerOptions.SKIP_PLUGIN_VERIFIER).types(PacketType.Play.Server.CHAT)) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (isToday("04-05")) {
                    PacketContainer packet = event.getPacket();
                    PacketType packetType = event.getPacketType();
                    if (packetType.equals(PacketType.Play.Server.CHAT)) {
                        if (packet.getChatTypes() == null) return;
                        if (packet.getChatTypes().getValues().get(0) != EnumWrappers.ChatType.SYSTEM) return;
                        WrappedChatComponent warppedComponent = packet.getChatComponents().getValues().get(0);
                        String json = warppedComponent.getJson();
                        BaseComponent[] components = ComponentSerializer.parse(json);
                        components = TextComponent.fromLegacyText(TextComponent.toLegacyText(components));
                        for(BaseComponent component:components) component.setColor(ChatColor.GRAY);
                        String newJson = ComponentSerializer.toString(components);
                        warppedComponent.setJson(newJson);
                        packet.getChatComponents().write(0, warppedComponent);
                    }
                }
            }
        });
    }
}
