package com.soggyrhino.triggered.client.api.objects.block;

import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.StateManager;
import net.minecraft.text.MutableText;
import org.graalvm.polyglot.HostAccess;

public class Block {

    public net.minecraft.block.Block mcObject;

    public Block(net.minecraft.block.Block mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public String getTranslationKey() {
        return mcObject.getTranslationKey();
    }

    @HostAccess.Export
    public MutableText getName() {
        return mcObject.getName();
    }

    @HostAccess.Export
    public float getBlastResistance() {
        return mcObject.getBlastResistance();
    }

    @HostAccess.Export
    public float getSlipperiness() {
        return mcObject.getSlipperiness();
    }

    @HostAccess.Export
    public float getVelocityMultiplier() {
        return mcObject.getVelocityMultiplier();
    }

    @HostAccess.Export
    public float getJumpVelocityMultiplier() {
        return mcObject.getJumpVelocityMultiplier();
    }

    @HostAccess.Export
    public BlockState getDefaultState() {
        return new BlockState(mcObject.getDefaultState());
    }

    @HostAccess.Export
    public StateManager<net.minecraft.block.Block, net.minecraft.block.BlockState> getStateManager() {
        return mcObject.getStateManager();
    }

    @HostAccess.Export
    public Item asItem() {
        return mcObject.asItem();
    }

    @HostAccess.Export
    public boolean hasDynamicBounds() {
        return mcObject.hasDynamicBounds();
    }

    @HostAccess.Export
    public RegistryEntry.Reference<net.minecraft.block.Block> getRegistryEntry() {
        return mcObject.getRegistryEntry();
    }

    @HostAccess.Export
    public String toString() {
        return mcObject.toString();
    }
}