package com.soggyrhino.triggered.client.api.objects.client.color.block;

import com.soggyrhino.triggered.client.api.objects.block.Block;
import com.soggyrhino.triggered.client.api.objects.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class BlockColors {
    public net.minecraft.client.color.block.BlockColors mcObject;

    public BlockColors(net.minecraft.client.color.block.BlockColors mcObject) {
        this.mcObject = mcObject;
    }

    //todo change to wrappers
    @HostAccess.Export
    public int getParticleColor(BlockState state, World world, BlockPos pos) {
        return mcObject.getParticleColor(state.mcObject, world, pos);
    }
    @HostAccess.Export
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        return mcObject.getColor(state.mcObject, world, pos, tintIndex);
    }
    @HostAccess.Export
    public Set<Property<?>> getProperties(Block block) {
        return mcObject.getProperties(block.mcObject);
    }
}
