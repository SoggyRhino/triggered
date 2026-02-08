package com.soggyrhino.triggered.client.api.objects.block;

import com.soggyrhino.triggered.client.api.objects.entity.Entity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SideShapeType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.graalvm.polyglot.HostAccess;

import java.util.List;
import java.util.stream.Collectors;

public class BlockState {

    public net.minecraft.block.BlockState mcObject;

    public BlockState(net.minecraft.block.BlockState mcObject) {
        this.mcObject = mcObject;
    }


    @HostAccess.Export
    public Block getBlock() {
        return new Block(mcObject.getBlock());
    }

    @HostAccess.Export
    public RegistryEntry<net.minecraft.block.Block> getRegistryEntry() {
        return mcObject.getRegistryEntry();
    }

    @Deprecated
    @HostAccess.Export
    public boolean blocksMovement() {
        return mcObject.blocksMovement();
    }

    @Deprecated
    @HostAccess.Export
    public boolean isSolid() {
        return mcObject.isSolid();
    }

    @HostAccess.Export
    public boolean allowsSpawning(BlockView world, BlockPos pos, EntityType<?> type) {
        return mcObject.allowsSpawning(world, pos, type);
    }

    @HostAccess.Export
    public boolean isTransparent() {
        return mcObject.isTransparent();
    }

    @HostAccess.Export
    public int getOpacity() {
        return mcObject.getOpacity();
    }

    @HostAccess.Export
    public boolean exceedsCube() {
        return mcObject.exceedsCube();
    }

    @HostAccess.Export
    public boolean hasSidedTransparency() {
        return mcObject.hasSidedTransparency();
    }

    @HostAccess.Export
    public int getLuminance() {
        return mcObject.getLuminance();
    }

    @HostAccess.Export
    public boolean isAir() {
        return mcObject.isAir();
    }

    @HostAccess.Export
    public boolean isBurnable() {
        return mcObject.isBurnable();
    }

    @Deprecated
    @HostAccess.Export
    public boolean isLiquid() {
        return mcObject.isLiquid();
    }

    @HostAccess.Export
    public MapColor getMapColor(BlockView world, BlockPos pos) {
        return new MapColor(mcObject.getMapColor(world, pos));
    }

    @HostAccess.Export
    public String getRenderType() {
        return mcObject.getRenderType().name();
    }

    @HostAccess.Export
    public boolean hasEmissiveLighting(BlockView world, BlockPos pos) {
        return mcObject.hasEmissiveLighting(world, pos);
    }

    @HostAccess.Export
    public float getAmbientOcclusionLightLevel(BlockView world, BlockPos pos) {
        return mcObject.getAmbientOcclusionLightLevel(world, pos);
    }

    @HostAccess.Export
    public boolean isSolidBlock(BlockView world, BlockPos pos) {
        return mcObject.isSolidBlock(world, pos);
    }

    @HostAccess.Export
    public boolean emitsRedstonePower() {
        return mcObject.emitsRedstonePower();
    }

    @HostAccess.Export
    public int getWeakRedstonePower(BlockView world, BlockPos pos, Direction direction) {
        return mcObject.getWeakRedstonePower(world, pos, direction);
    }

    @HostAccess.Export
    public boolean hasComparatorOutput() {
        return mcObject.hasComparatorOutput();
    }

    @HostAccess.Export
    public int getComparatorOutput(World world, BlockPos pos) {
        return mcObject.getComparatorOutput(world, pos);
    }

    @HostAccess.Export
    public float getHardness(BlockView world, BlockPos pos) {
        return mcObject.getHardness(world, pos);
    }

    @HostAccess.Export
    public float calcBlockBreakingDelta(PlayerEntity player, BlockView world, BlockPos pos) {
        return mcObject.calcBlockBreakingDelta(player, world, pos);
    }

    @HostAccess.Export
    public int getStrongRedstonePower(BlockView world, BlockPos pos, Direction direction) {
        return mcObject.getStrongRedstonePower(world, pos, direction);
    }

    @HostAccess.Export
    public String getPistonBehavior() {
        return mcObject.getPistonBehavior().name();
    }

    @HostAccess.Export
    public boolean isOpaqueFullCube() {
        return mcObject.isOpaqueFullCube();
    }

    @HostAccess.Export
    public boolean isOpaque() {
        return mcObject.isOpaque();
    }

    @HostAccess.Export
    public boolean isSideInvisible(net.minecraft.block.BlockState state, Direction direction) {
        return mcObject.isSideInvisible(state, direction);
    }

    @HostAccess.Export
    public VoxelShape getOutlineShape(BlockView world, BlockPos pos) {
        return mcObject.getOutlineShape(world, pos);
    }

    @HostAccess.Export
    public VoxelShape getOutlineShape(BlockView world, BlockPos pos, ShapeContext context) {
        return mcObject.getOutlineShape(world, pos, context);
    }

    @HostAccess.Export
    public VoxelShape getCollisionShape(BlockView world, BlockPos pos) {
        return mcObject.getCollisionShape(world, pos);
    }

    @HostAccess.Export
    public VoxelShape getCollisionShape(BlockView world, BlockPos pos, ShapeContext context) {
        return mcObject.getCollisionShape(world, pos, context);
    }

    @HostAccess.Export
    public VoxelShape getInsideCollisionShape(BlockView blockView, BlockPos pos, Entity entity) {
        return mcObject.getInsideCollisionShape(blockView, pos, entity.mcEntity);
    }

    @HostAccess.Export
    public VoxelShape getSidesShape(BlockView world, BlockPos pos) {
        return mcObject.getSidesShape(world, pos);
    }

    @HostAccess.Export
    public VoxelShape getCameraCollisionShape(BlockView world, BlockPos pos, ShapeContext context) {
        return mcObject.getCameraCollisionShape(world, pos, context);
    }

    @HostAccess.Export
    public VoxelShape getRaycastShape(BlockView world, BlockPos pos) {
        return mcObject.getRaycastShape(world, pos);
    }

    @HostAccess.Export
    public final boolean hasSolidTopSurface(BlockView world, BlockPos pos, Entity entity) {
        return mcObject.hasSolidTopSurface(world, pos, entity.mcEntity);
    }

    @HostAccess.Export
    public final boolean isSolidSurface(BlockView world, BlockPos pos, Entity entity, Direction direction) {
        return mcObject.isSolidSurface(world, pos, entity.mcEntity, direction);
    }

    @HostAccess.Export
    public Vec3d getModelOffset(BlockPos pos) {
        return mcObject.getModelOffset(pos);
    }

    @HostAccess.Export
    public boolean hasModelOffset() {
        return mcObject.hasModelOffset();
    }


    @HostAccess.Export
    public List<ItemStack> getDroppedStacks(LootWorldContext.Builder builder) {
        return mcObject.getDroppedStacks(builder);
    }


    @HostAccess.Export
    public boolean shouldSuffocate(BlockView world, BlockPos pos) {
        return mcObject.shouldSuffocate(world, pos);
    }

    @HostAccess.Export
    public boolean shouldBlockVision(BlockView world, BlockPos pos) {
        return mcObject.shouldBlockVision(world, pos);
    }

    @HostAccess.Export
    public boolean canBucketPlace(Fluid fluid) {
        return mcObject.canBucketPlace(fluid);
    }

    @HostAccess.Export
    public boolean canPlaceAt(WorldView world, BlockPos pos) {
        return mcObject.canPlaceAt(world, pos);
    }


    @HostAccess.Export
    public boolean isOf(RegistryEntry<net.minecraft.block.Block> blockEntry) {
        return mcObject.isOf(blockEntry);
    }

    @HostAccess.Export
    public List<TagKey<net.minecraft.block.Block>> listTags() {
        return mcObject.streamTags().collect(Collectors.toList());
    }

    @HostAccess.Export
    public boolean hasBlockEntity() {
        return mcObject.hasBlockEntity();
    }

    @HostAccess.Export
    public boolean isOf(Block block) {
        return mcObject.isOf(block.mcObject);
    }

    @HostAccess.Export
    public boolean matchesKey(RegistryKey<net.minecraft.block.Block> key) {
        return mcObject.matchesKey(key);
    }

    @HostAccess.Export
    public FluidState getFluidState() {
        return mcObject.getFluidState();
    }

    @HostAccess.Export
    public boolean hasRandomTicks() {
        return mcObject.hasRandomTicks();
    }

    @HostAccess.Export
    public long getRenderingSeed(BlockPos pos) {
        return mcObject.getRenderingSeed(pos);
    }

    @HostAccess.Export
    public boolean isSideSolidFullSquare(BlockView world, BlockPos pos, Direction direction) {
        return mcObject.isSideSolidFullSquare(world, pos, direction);
    }

    @HostAccess.Export
    public boolean isSideSolid(BlockView world, BlockPos pos, Direction direction, SideShapeType shapeType) {
        return mcObject.isSideSolid(world, pos, direction, shapeType);
    }

    @HostAccess.Export
    public boolean isFullCube(BlockView world, BlockPos pos) {
        return mcObject.isFullCube(world, pos);
    }

    @HostAccess.Export
    public ItemStack getPickStack(WorldView world, BlockPos pos, boolean includeData) {
        return mcObject.getPickStack(world, pos, includeData);
    }

    @HostAccess.Export
    public boolean isToolRequired() {
        return mcObject.isToolRequired();
    }

    @HostAccess.Export
    public boolean hasBlockBreakParticles() {
        return mcObject.hasBlockBreakParticles();
    }

    @HostAccess.Export
    public String getInstrument() {
        return mcObject.getInstrument().name();
    }

}
