package fr.septmg.Toggle.Sprint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import javax.swing.*;


public class Main implements ModInitializer {

    public static final String ModID = "tsbs";
    public static boolean Toggle = false;
    public static KeyBinding key;

    @Override
    public void onInitialize() {
        key = KeyBindingHelper.registerKeyBinding(new KeyBinding("toggle sprint", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "Toggle Sprint Bs"));
        ClientTickEvents.START_CLIENT_TICK.register(client -> {

            if (MinecraftClient.getInstance().player != null) {
                if (client.player.input.pressingForward & Toggle){
                    client.player.setSprinting(true);
                }
             }
            while (key.wasPressed() == true) {

                client.player.sendSystemMessage(Text.of(""), client.player.getUuid());
                if (Toggle) {
                    Toggle = false;
                    client.player.setSprinting(false);
                    client.player.sendSystemMessage(Text.of("§6[Toggle Sprint Bs] §c" + Toggle), client.player.getUuid());
                } else {
                    Toggle = true;
                    client.player.setSprinting(true);
                    client.player.sendSystemMessage(Text.of("§6[Toggle Sprint Bs] §a" + Toggle), client.player.getUuid());
                }
            }
        });
    }
}
