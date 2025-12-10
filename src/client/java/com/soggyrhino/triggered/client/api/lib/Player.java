package com.soggyrhino.triggered.client.api.lib;

import com.soggyrhino.triggered.client.api.annotations.Library;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

@Library
public class Player {


    public void getPositon(){
        PlayerEntity player = MinecraftClient.getInstance().player;



    }
}
