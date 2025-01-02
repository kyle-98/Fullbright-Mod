package com.poggers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;


public class NotifyPlayer {
    public static void displayMessage(String message, boolean aboveHotbar){
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        client.player.sendMessage(Text.literal(message), aboveHotbar);
    }
}
