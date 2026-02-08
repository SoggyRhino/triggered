package com.soggyrhino.triggered.client.api.objects.block;

import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.CollisionView;
import org.graalvm.polyglot.HostAccess;

public class ShapeContext {


    public net.minecraft.block.ShapeContext mcObject;

    public ShapeContext(net.minecraft.block.ShapeContext mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public boolean isDescending() {
        return mcObject.isDescending();
    }

    @HostAccess.Export
    public boolean isAbove(VoxelShape var1, BlockPos var2, boolean var3) {
        return mcObject.isAbove(var1, var2, var3);
    }

    @HostAccess.Export
    public boolean isHolding(Item var1) {
        return mcObject.isHolding(var1);
    }

    @HostAccess.Export
    public boolean canWalkOnFluid(FluidState var1, FluidState var2) {
        return mcObject.canWalkOnFluid(var1, var2);
    }

    @HostAccess.Export
    public VoxelShape getCollisionShape(BlockState var1, CollisionView var2, BlockPos var3) {
        return mcObject.getCollisionShape(var1.mcObject, var2, var3);
    }

    @HostAccess.Export
    public boolean isPlacement() {
        return mcObject.isPlacement();
    }
}
