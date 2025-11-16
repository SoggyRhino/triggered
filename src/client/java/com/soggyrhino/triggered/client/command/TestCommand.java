package com.soggyrhino.triggered.client.command;

import com.mojang.brigadier.CommandDispatcher;
import com.soggyrhino.triggered.client.engine.ModuleManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TestCommand {
    // This method will be called to register the command
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        // Create the command builder
        dispatcher.register(ClientCommandManager.literal("triggers")
                .then(ClientCommandManager.literal("reload")
                        .executes(commandContext -> {
                            MinecraftClient.getInstance().player.sendMessage(Text.of("Reloading Modules"), false);
                            ModuleManager.instance.reload();
                            return 1;
                        })
                )
        );
    }
}
