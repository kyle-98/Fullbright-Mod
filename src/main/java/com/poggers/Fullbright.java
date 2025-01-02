package com.poggers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Fullbright implements ClientModInitializer {
	public static boolean isFullbrightEnabled = false;
	public static KeyBinding toggleKey;

	@Override
	public void onInitializeClient() {
		toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.fullbright.toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_J,
				"category.fullbright"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleKey.wasPressed()) {
				SimpleOption<Double> gamma = MinecraftClient.getInstance().options.getGamma();
				if(isFullbrightEnabled) {
					gamma.setValue(1.0);
					NotifyPlayer.displayMessage("Fullbright OFF", true);
				}
				else {
					gamma.setValue(15.0);
					NotifyPlayer.displayMessage("Fullbright ON", true);
				}
				isFullbrightEnabled = !isFullbrightEnabled;
			}
		});
	}
}
