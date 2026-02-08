package com.soggyrhino.triggered.client.api.objects.entity;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.block.BlockState;
import com.soggyrhino.triggered.client.api.objects.block.piston.PistonBehavior;
import com.soggyrhino.triggered.client.api.objects.util.math.BlockPos;
import com.soggyrhino.triggered.client.api.objects.util.math.Direction;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.dimension.NetherPortal;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MCObject
public class Entity {
    public final net.minecraft.entity.Entity mcEntity;

    public Entity(net.minecraft.entity.Entity mcEntity) {
        this.mcEntity = mcEntity;
    }


    /**
     * {@return whether the entity collides with the block {@code state} at {@code pos}}
     */
    @HostAccess.Export
    public boolean collidesWithStateAtPos(BlockPos pos, BlockState state) {
        return mcEntity.collidesWithStateAtPos(pos.mcObject, state.mcObject);
    }

    /**
     * {@return the team color value, or {@code 0xFFFFFF} if the entity is not in
     * a team or the color is not set}
     */
    @HostAccess.Export
    public int getTeamColorValue() {
        return mcEntity.getTeamColorValue();
    }

    /**
     * {@return whether the entity is a spectator}
     *
     * <p>This returns {@code false} unless the entity is a player in spectator game mode.
     */
    @HostAccess.Export
    public boolean isSpectator() {
        return mcEntity.isSpectator();
    }

    @HostAccess.Export
    public TrackedPosition getTrackedPosition() {
        return mcEntity.getTrackedPosition();
    }

    @HostAccess.Export
    public EntityType<?> getType() {
        return mcEntity.getType();
    }

    @HostAccess.Export
    public boolean shouldAlwaysSyncAbsolute() {
        return mcEntity.shouldAlwaysSyncAbsolute();
    }

    @HostAccess.Export
    public int getId() {
        return mcEntity.getId();
    }

    @HostAccess.Export
    public Set<String> getCommandTags() {
        return mcEntity.getCommandTags();
    }

    @HostAccess.Export
    public DataTracker getDataTracker() {
        return mcEntity.getDataTracker();
    }

    @HostAccess.Export
    public EntityPose getPose() {
        return this.mcEntity.getPose();
    }

    @HostAccess.Export
    public boolean isInPose(EntityPose pose) {
        return this.mcEntity.isInPose(pose);
    }

    /**
     * {@return whether the distance between this entity and {@code entity} is below
     * {@code radius}}
     */
    @HostAccess.Export
    public boolean isInRange(net.minecraft.entity.Entity entity, double radius) {
        return this.mcEntity.isInRange(entity, radius);
    }

    /**
     * {@return whether both the horizontal and vertical distances between this entity and
     * {@code entity} are below the passed values}
     */
    @HostAccess.Export
    public boolean isInRange(Entity entity, double horizontalRadius, double verticalRadius) {
        return mcEntity.isInRange(entity.mcEntity, horizontalRadius, verticalRadius);
    }

    @HostAccess.Export
    public int getPortalCooldown() {
        return mcEntity.getPortalCooldown();
    }

    /**
     * {@return whether the entity's portal cooldown is in effect}
     */
    @HostAccess.Export
    public boolean hasPortalCooldown() {
        return mcEntity.hasPortalCooldown();
    }


    @HostAccess.Export
    public int getFireTicks() {
        return mcEntity.getFireTicks();
    }

    /**
     * {@return whether the bounding box with the given offsets do not collide with
     * blocks or fluids}
     */
    @HostAccess.Export
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return mcEntity.doesNotCollide(offsetX, offsetY, offsetZ);
    }

    @HostAccess.Export
    public boolean isSupportedBy(BlockPos pos) {
        return mcEntity.isSupportedBy(pos.mcObject);
    }

    /**
     * {@return whether the entity is on the ground}
     */
    @HostAccess.Export
    public boolean isOnGround() {
        return mcEntity.isOnGround();
    }

    /**
     * {@return the landing position}
     *
     * @implNote Landing position is the entity's position, with {@code 0.2} subtracted
     * from the Y coordinate. This means that, for example, if a player is on a carpet on
     * a soul soil, the soul soil's position would be returned.
     * @see #getSteppingPos()
     */
    @Deprecated
    @HostAccess.Export
    public BlockPos getLandingPos() {
        return new BlockPos(mcEntity.getLandingPos());
    }

    /**
     * {@return whether the entity is silent}
     *
     * <p>Silent entities should not make sounds. PlaySound checks this method by
     * default, but if a sound is played manually, this has to be checked too.
     *
     * <p>This is saved under the {@code Silent} NBT key.
     */
    @HostAccess.Export
    public BlockPos getVelocityAffectingPos() {
        return new BlockPos(mcEntity.getVelocityAffectingPos());
    }

    /**
     * {@return the stepping position}
     *
     * @implNote Stepping position is the entity's position, with {@code 1e-05} subtracted
     * from the Y coordinate. This means that, for example, if a player is on a carpet on
     * a soul soil, the carpet's position would be returned.
     * @see #getLandingPos()
     * @see #getSteppingBlockState()
     */
    @HostAccess.Export
    public BlockPos getSteppingPos() {
        return new BlockPos(mcEntity.getSteppingPos());
    }

    //todo missing static methods

    @HostAccess.Export
    public boolean collidesWithFluid(FluidState state, BlockPos fluidPos, Vec3d oldPos, Vec3d newPos) {
        return mcEntity.collidesWithFluid(state, fluidPos.mcObject, oldPos, newPos);
    }

    @HostAccess.Export
    public boolean collides(Vec3d oldPos, Vec3d newPos, List<Box> boxes) {
        return mcEntity.collides(oldPos, newPos, boxes);
    }

    @HostAccess.Export
    public BlockPos getWorldSpawnPos(ServerWorld world, BlockPos basePos) {
        return new BlockPos(mcEntity.getWorldSpawnPos(world, basePos.mcObject));
    }

    /**
     * {@return whether the entity has no gravity}
     *
     * <p>Entities using {@link FlightMoveControl} has
     * no gravity. This is saved under the {@code NoGravity} NBT key.
     */
    @HostAccess.Export
    public boolean hasNoGravity() {
        return mcEntity.hasNoGravity();
    }

    /**
     * {@return whether the entity should not emit vibrations}
     *
     * <p>By default, wool or carpet {@linkplain ItemEntity item entities}, and
     * {@link WardenEntity} do not emit vibrations.
     */
    @HostAccess.Export
    public final double getFinalGravity() {
        return mcEntity.getFinalGravity();
    }

    @HostAccess.Export
    public boolean occludeVibrationSignals() {
        return mcEntity.occludeVibrationSignals();
    }

    /**
     * {@return whether the entity is immune to {@linkplain
     * DamageTypeTags#IS_FIRE fire damage}}
     *
     * @see EntityType.Builder#makeFireImmune
     */
    @HostAccess.Export
    public boolean isFireImmune() {
        return mcEntity.isFireImmune();
    }

    /**
     * Returns whether this entity's hitbox is touching water fluid.
     */
    @HostAccess.Export
    public boolean isTouchingWater() {
        return mcEntity.isTouchingWater();
    }


    /**
     * {@return whether this entity is touching water or is being rained on (but does not check
     * for a bubble column)}
     *
     * @see net.minecraft.entity.Entity#isTouchingWater()
     * @see net.minecraft.entity.Entity#isInFluid()
     */
    @HostAccess.Export
    public boolean isTouchingWaterOrRain() {
        return mcEntity.isTouchingWaterOrRain();
    }

    @HostAccess.Export
    public boolean isInFluid() {
        return mcEntity.isInFluid();
    }

    /**
     * {@return whether this entity's hitbox is fully submerged in water}
     */
    @HostAccess.Export
    public boolean isSubmergedInWater() {
        return mcEntity.isSubmergedInWater();
    }

    @HostAccess.Export
    public boolean isAtCloudHeight() {
        return mcEntity.isAtCloudHeight();
    }


    /**
     * {@return the block state at the stepping position}
     *
     * @implNote Stepping position is the entity's position, with {@code 1e-05} subtracted
     * from the Y coordinate. This means that, for example, if a player is on a carpet on
     * a soul soil, the carpet's position would be returned.
     * @see #getSteppingPos()
     */
    @HostAccess.Export
    public BlockState getSteppingBlockState() {
        return new BlockState(mcEntity.getSteppingBlockState());
    }

    @HostAccess.Export
    public boolean shouldSpawnSprintingParticles() {
        return mcEntity.shouldSpawnSprintingParticles();
    }

    /**
     * {@return whether the entity is submerged in a fluid in {@code fluidTag}}
     */
    @HostAccess.Export
    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return mcEntity.isSubmergedIn(fluidTag);
    }

    /**
     * {@return whether the entity is in lava}
     */
    @HostAccess.Export
    public boolean isInLava() {
        return mcEntity.isInLava();
    }

    @HostAccess.Export
    public final Vec3d getLastRenderPos() {
        return mcEntity.getLastRenderPos();
    }


    /**
     * {@return the distance between this entity and {@code entity}}
     */
    @HostAccess.Export
    public float distanceTo(Entity entity) {
        return mcEntity.distanceTo(entity.mcEntity);
    }

    @HostAccess.Export
    public double squaredDistanceTo(double x, double y, double z) {
        return mcEntity.squaredDistanceTo(x, y, z);
    }


    /**
     * {@return the squared distance between this entity and {@code entity}}
     */
    @HostAccess.Export
    public double squaredDistanceTo(Entity entity) {
        return mcEntity.squaredDistanceTo(entity.getPos());
    }

    /**
     * {@return the squared distance between this entity and the given position}
     */
    @HostAccess.Export
    public double squaredDistanceTo(Vec3d vector) {
        return mcEntity.squaredDistanceTo(vector);
    }

    @HostAccess.Export
    public final Vec3d getRotationVec(float tickProgress) {
        return mcEntity.getRotationVec(tickProgress);
    }

    @HostAccess.Export
    public String getFacingDirection() {
        return Direction.toString(mcEntity.getFacing());
    }

    @HostAccess.Export
    public float getPitch(float tickProgress) {
        return mcEntity.getPitch(tickProgress);
    }

    @HostAccess.Export
    public float getYaw(float tickProgress) {
        return mcEntity.getYaw(tickProgress);
    }

    @HostAccess.Export
    public float getLerpedPitch(float tickProgress) {
        return mcEntity.getLerpedPitch(tickProgress);
    }

    @HostAccess.Export
    public float getLerpedYaw(float tickProgress) {
        return mcEntity.getLerpedYaw(tickProgress);
    }

    @HostAccess.Export
    public final Vec3d getRotationVector(float pitch, float yaw) {
        return mcEntity.getRotationVector(pitch, yaw);
    }

    @HostAccess.Export
    public final Vec3d getOppositeRotationVector(float tickProgress) {
        return mcEntity.getOppositeRotationVector(tickProgress);
    }


    /**
     * {@return the position of the eye}
     */
    @HostAccess.Export
    public final Vec3d getEyePos() {
        return mcEntity.getEyePos();
    }

    @HostAccess.Export
    public final Vec3d getCameraPosVec(float tickProgress) {
        return mcEntity.getCameraPosVec(tickProgress);
    }

    @HostAccess.Export
    public Vec3d getClientCameraPosVec(float tickProgress) {
        return mcEntity.getCameraPosVec(tickProgress);
    }

    @HostAccess.Export
    public final Vec3d getLerpedPos(float deltaTicks) {
        return mcEntity.getLerpedPos(deltaTicks);
    }

    @HostAccess.Export
    public HitResult raycast(double maxDistance, float tickProgress, boolean includeFluids) {
        return mcEntity.raycast(maxDistance, tickProgress, includeFluids);
    }

    @HostAccess.Export
    public boolean canBeHitByProjectile() {
        return mcEntity.canBeHitByProjectile();
    }


    /**
     * {@return whether the entity can be hit with a projectile or be targeted by
     * the player crosshair}
     */
    @HostAccess.Export
    public boolean canHit() {
        return mcEntity.canHit();
    }


    /**
     * {@return whether the entity can be pushed by other entities}
     */
    @HostAccess.Export
    public boolean isPushable() {
        return mcEntity.isPushable();
    }

    @HostAccess.Export
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return mcEntity.shouldRender(cameraX, cameraY, cameraZ);
    }

    @HostAccess.Export
    public boolean shouldRender(double distance) {
        return mcEntity.shouldRender(distance);
    }


    /**
     * {@return whether the entity is alive}
     *
     * <p>For non-{@link LivingEntity}, this is the same as negating isRemoved.
     * {@link LivingEntity} checks the entity's health in addition to the removal.
     */
    @HostAccess.Export
    public boolean isAlive() {
        return mcEntity.isAlive();
    }


    /**
     * {@return whether the entity is in a wall and should suffocate}
     *
     * <p>This returns {@code false} if noClip is {@code true}; otherwise,
     * this returns {@code true} if the eye position is occupied by a
     * AbstractBlock.Settings#suffocates block that can suffocate.
     */
    @HostAccess.Export
    public boolean isInsideWall() {
        return mcEntity.isInsideWall();
    }


    /**
     * {@return whether this entity cannot occupy the same space with {@code other}}
     *
     * <p>This returns {@code false} if {@code other} is isConnectedThroughVehicle
     * connected through vehicles}.
     *
     * @see #isCollidable
     */
    @HostAccess.Export
    public boolean collidesWith(Entity other) {
        return mcEntity.collidesWith(other.mcEntity);
    }


    /**
     * {@return whether other entities cannot occupy the same space with this entity}
     *
     * <p>If {@code true}, other entities can stand on this entity without falling.
     * {@link BoatEntity} and {@link
     * ShulkerEntity} has this behavior.
     *
     * @see #collidesWith
     */
    @HostAccess.Export
    public boolean isCollidable(Entity entity) {
        return mcEntity.isCollidable(entity.mcEntity);
    }

    @HostAccess.Export
    public Vec3d getVehicleAttachmentPos(Entity vehicle) {
        return mcEntity.getVehicleAttachmentPos(vehicle.mcEntity);
    }

    @HostAccess.Export
    public Vec3d getPassengerRidingPos(Entity passenger) {
        return mcEntity.getPassengerRidingPos(passenger.mcEntity);
    }

    @HostAccess.Export
    public boolean isLiving() {
        return mcEntity.isLiving();
    }


    /**
     * {@return the margin around the entity's bounding box where the entity
     * targeting is still successful}
     *
     * @apiNote {@link ExplosiveProjectileEntity}
     * overrides this method to return {@code 1.0f}, which expands the ghast fireball's
     * effective hitbox.
     */
    @HostAccess.Export
    public float getTargetingMargin() {
        return mcEntity.getTargetingMargin();
    }

    @HostAccess.Export
    public Vec3d getRotationVector() {
        return mcEntity.getRotationVector();
    }

    @HostAccess.Export
    public Vec3d getHandPosOffset(Item item) {
        return mcEntity.getHandPosOffset(item);
    }

    @HostAccess.Export
    public Vec2f getRotationClient() {
        return mcEntity.getRotationClient();
    }

    @HostAccess.Export
    public Vec3d getRotationVecClient() {
        return mcEntity.getRotationVecClient();
    }


    /**
     * {@return the entity's default portal cooldown}
     *
     * <p>This is 300 ticks by default, or 10 ticks for players.
     *
     */
    @HostAccess.Export
    public int getDefaultPortalCooldown() {
        return mcEntity.getDefaultPortalCooldown();
    }


    /**
     * {@return whether the entity is on fire and is not fire immune}
     *
     * @see #isFireImmune
     */
    @HostAccess.Export
    public boolean isOnFire() {
        return mcEntity.isOnFire();
    }


    /**
     * {@return whether this entity is riding an entity}
     *
     * <p>This is the opposite of {@link #hasPassengers}.
     *
     * @see #hasPassengers
     */
    @HostAccess.Export
    public boolean hasVehicle() {
        return mcEntity.hasVehicle();
    }


    /**
     * {@return whether another entity is riding this entity}
     *
     * <p>This is the opposite of {@link #hasVehicle}.
     *
     * @see #hasVehicle
     */
    @HostAccess.Export
    public boolean hasPassengers() {
        return mcEntity.hasPassengers();
    }


    /**
     * {@return whether this vehicle should dismount the passenger if submerged underwater}
     */
    @HostAccess.Export
    public boolean shouldDismountUnderwater() {
        return mcEntity.shouldDismountUnderwater();
    }

    @HostAccess.Export
    public boolean shouldControlVehicles() {
        return mcEntity.shouldControlVehicles();
    }


    /**
     * {@return whether the entity is sneaking}
     *
     * <p>This only returns {@code true} if the entity is a player and that player
     * is pressing the Sneak key. See also {@link #isInSneakingPose}.
     *
     * @see #isInSneakingPose
     */
    @HostAccess.Export
    public boolean isSneaking() {
        return mcEntity.isSneaking();
    }

    @HostAccess.Export
    public boolean isSneaky() {
        return mcEntity.isSneaky();
    }

    /**
     * {@return whether the entity should bypass effects caused by stepping}
     *
     * <p>This returns {@link #isSneaking} by default.
     *
     * @apiNote Stepping effects include magma blocks dealing fire damage, turtle eggs
     * breaking, or sculk sensors triggering.
     * @see #bypassesLandingEffects
     */
    @HostAccess.Export
    public boolean bypassesSteppingEffects() {
        return mcEntity.isSneaking();
    }

    /**
     * {@return whether the entity should bypass effects caused by landing on a block}
     *
     * <p>This returns {@link #isSneaking} by default.
     *
     * @apiNote Landing effects include slime blocks nullifying the fall damage and
     * slime blocks and beds bouncing the entity.
     * @see #bypassesSteppingEffects
     */
    @HostAccess.Export
    public boolean bypassesLandingEffects() {
        return mcEntity.isSneaking();
    }

    /**
     * {@return whether the entity is actively descending}
     *
     * <p>This affects scaffolding and powder snow (if the entity can walk on it), and
     * returns {@link #isSneaking} by default. This returns {@code false} for entities
     * descending a ladder, since the entity is not actively doing so, instead letting
     * the gravity to do so.
     */
    @HostAccess.Export
    public boolean isDescending() {
        return mcEntity.isDescending();
    }

    /**
     * {@return whether the entity is in a crouching pose}
     *
     * <p>Compared to {@link #isSneaking()}, it only makes the entity appear
     * crouching and does not bring other effects of sneaking, such as no less
     * obvious name label rendering, no dismounting while riding, etc.
     *
     * <p>This is used by vanilla for non-player entities to crouch, such as
     * for foxes and cats. This is also used when the entity is a player and
     * the player would otherwise collide with blocks (for example, when the
     * player is in a 1.5 blocks tall tunnel).
     */
    @HostAccess.Export
    public boolean isInSneakingPose() {
        return mcEntity.isInSneakingPose();
    }


    /**
     * {@return whether the entity is sprinting}
     *
     * <p>Swimming is also considered as sprinting.
     * <p>
     * #setSprinting
     */
    @HostAccess.Export
    public boolean isSprinting() {
        return mcEntity.isSprinting();
    }


    /**
     * {@return whether the entity is swimming}
     *
     * <p>An entity is swimming if it is touching water, not riding any entities, and is
     * sprinting. Note that to start swimming, the entity must first be submerged in
     * water.
     *
     */
    @HostAccess.Export
    public boolean isSwimming() {
        return mcEntity.isSwimming();
    }

    // @HostAccess.Export
    // public static Vec3d adjustMovementForCollisions(@Nullable Entity entity, Vec3d movement, Box entityBoundingBox, World world, List<VoxelShape> collisions) {
    //   List<VoxelShape> list = Entity.findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));
    //
    //
    // }

    /**
     * {@return whether the entity is in swimming pose}
     *
     * <p>This includes crawling entities and entities using elytra that aren't fall-flying.
     * Players start crawling if they would otherwise collide with blocks (for example,
     * when the player is in a 1 block tall tunnel).
     *
     * @see #isCrawling
     */
    @HostAccess.Export
    public boolean isInSwimmingPose() {
        return this.isInPose(EntityPose.SWIMMING);
    }

    /**
     * {@return whether the entity is crawling}
     *
     * <p>An entity is crawling if it is in swimming pose, but is not touching water.
     * Players start crawling if they would otherwise collide with blocks (for example,
     * when the player is in a 1 block tall tunnel).
     *
     * @see #isInSwimmingPose
     */
    @HostAccess.Export
    public boolean isCrawling() {
        return this.isInSwimmingPose() && !this.isTouchingWater();
    }


    /**
     * {@return whether the entity is glowing, without checking the entity flags}
     *
     * @apiNote This is only used to copy entity data to NBT when bucketing.
     * @see #isGlowing
     */
    @HostAccess.Export
    public final boolean isGlowingLocal() {
        return mcEntity.isGlowing();
    }


    /**
     * {@return whether the entity is glowing, checking the entity flags on the client}
     *
     * <p>Glowing entities have an outline when rendered.
     *
     * @see #isGlowingLocal
     */
    @HostAccess.Export
    public boolean isGlowing() {
        return mcEntity.isGlowing();
    }

    /**
     * {@return whether the entity is invisible to everyone}
     *
     * <p>Invisibility status effect and {@link
     * ArmorStandEntity}'s {@code Invisible} NBT key can
     * cause an entity to be invisible.
     *
     * @see #isInvisibleTo
     */
    @HostAccess.Export
    public boolean isInvisible() {
        return mcEntity.isInvisible();
    }

    /**
     * {@return whether the entity is invisible to {@code player}}
     *
     * <p>Spectators can see all entities, and entities on the same team as player's can
     * see all entities if {@link AbstractTeam#shouldShowFriendlyInvisibles} returns
     * {@code true}. Otherwise, this returns {@link #isInvisible}.
     *
     * @see AbstractTeam#shouldShowFriendlyInvisibles
     * @see #isInvisible
     */
    @HostAccess.Export
    public boolean isInvisibleTo(PlayerEntity player) {
        return mcEntity.isInvisibleTo(player);
    }

    @HostAccess.Export
    public boolean isOnRail() {
        return false;
    }

    /**
     * {@return the scoreboard team the entity belongs to, or {@code null} if there is none}
     */
    @Nullable
    @HostAccess.Export
    public Team getScoreboardTeam() {
        return mcEntity.getScoreboardTeam();
    }

    /**
     * {@return whether this entity and {@code other} are in the same team}
     *
     * <p>This returns {@code false} if this entity is not in any team.
     */
    @HostAccess.Export
    public final boolean isTeammate(@Nullable Entity other) {
        return other != null && mcEntity.isTeammate(other.mcEntity);
    }


    /**
     * {@return whether this entity is in {@code team}}
     *
     * <p>This returns {@code false} if this entity is not in any team.
     */
    @HostAccess.Export
    public boolean isTeamPlayer(@Nullable AbstractTeam team) {
        return mcEntity.isTeamPlayer(team);
    }


    /**
     * {@return the maximum amount of air the entity can hold, in ticks}
     *
     * <p>Most entities have the max air of 300 ticks, or 15 seconds.
     * {@link DolphinEntity} has 4800 ticks or 4
     * minutes; {@link AxolotlEntity} has 6000 ticks
     * or 5 minutes. Note that this does not include enchantments.
     *
     * @see #getAir
     */
    @HostAccess.Export
    public int getMaxAir() {
        return mcEntity.getMaxAir();
    }

    /**
     * {@return the air left for the entity, in ticks}
     *
     * <p>Air is decremented every tick if the entity's eye is submerged in water.
     * If this is {@code -20}, the air will be reset to {@code 0} and the entity takes
     * a drowning damage.
     *
     * @apiNote {@link WaterCreatureEntity} reuses the air to
     * indicate the entity's air breathed when the entity is in water. If the entity is
     * not touching a water, the air decrements, and the entity drowns in the same way
     * as other entities.
     * @see #getMaxAir
     */
    @HostAccess.Export
    public int getAir() {
        return mcEntity.getAir();
    }


    /**
     * {@return how long the entity is freezing, in ticks}
     *
     * <p>If this is equal to or above {@link #getMinFreezeDamageTicks}, the entity
     * receives freezing damage.
     *
     * @see #getFreezingScale
     * @see #isFrozen
     * @see #getMinFreezeDamageTicks
     */
    @HostAccess.Export
    public int getFrozenTicks() {
        return mcEntity.getFrozenTicks();
    }


    /**
     * {@return the current freezing scale}
     *
     * <p>Freezing scale is calculated as {@code
     * Math.min(1, getFrozenTicks() / getMinFreezeDamageTicks())}.
     *
     * @see #getFrozenTicks
     * @see #isFrozen
     * @see #getMinFreezeDamageTicks
     */
    @HostAccess.Export
    public float getFreezingScale() {
        return mcEntity.getFreezingScale();
    }

    /**
     * {@return whether the entity is frozen}
     *
     * <p>Frozen entities take freezing damage. Entity becomes frozen {@link
     * #getMinFreezeDamageTicks} ticks after starting to freeze.
     *
     * @see #getFrozenTicks
     * @see #getFreezingScale
     * @see #getMinFreezeDamageTicks
     */
    @HostAccess.Export
    public boolean isFrozen() {
        return mcEntity.isFrozen();
    }

    /**
     * {@return how long it takes for the entity to be completely frozen and receive
     * freezing damage, in ticks}
     *
     * @see #getFrozenTicks
     * @see #getFreezingScale
     * @see #isFrozen
     */
    @HostAccess.Export
    public int getMinFreezeDamageTicks() {
        return mcEntity.getMinFreezeDamageTicks();
    }

    @HostAccess.Export
    public Text getName() {
        return mcEntity.getName();
    }


    /**
     * {@return whether this entity is part of {@code entity}}
     *
     * <p>This is just an equality check for all entities except the ender dragon part.
     * An ender dragon is composed of several entity parts; each part returns {@code true}
     * for {@code part.isPartOf(dragon)}.
     */
    @HostAccess.Export
    public boolean isPartOf(Entity entity) {
        return mcEntity.isPartOf(entity.mcEntity);
    }

    /**
     * {@return the head yaw of the entity}
     *
     */
    @HostAccess.Export
    public float getHeadYaw() {
        return mcEntity.getHeadYaw();
    }


    /**
     * {@return whether the entity can be attacked by players}
     *
     * <p>Note that this is not called for most entities defined in vanilla as unattackable
     * (such as {@link ItemEntity} and {@link ExperienceOrbEntity}) as trying to attack them
     * kicks the player.
     *
     * @see ServerPlayNetworkHandler#onPlayerInteractEntity
     */
    @HostAccess.Export
    public boolean isAttackable() {
        return mcEntity.isAttackable();
    }

    @HostAccess.Export
    public String toString() {
        return mcEntity.toString();
    }


    /**
     * {@return whether the entity is invulnerable}
     *
     * <p>This is saved on the {@code Invulnerable} NBT key.
     *
     * @implNote Invulnerable entities are immune from all damages except {@link
     * DamageTypes#OUT_OF_WORLD}
     * and damages by creative mode players by default.
     */
    @HostAccess.Export
    public boolean isInvulnerable() {
        return mcEntity.isInvulnerable();
    }


    /**
     * {@return the entity's position in the portal after teleportation}
     *
     * @see NetherPortal#entityPosInPortal
     */
    @HostAccess.Export
    public Vec3d positionInPortal(String portalAxis, BlockLocating.Rectangle portalRect) {
        return mcEntity.positionInPortal(Direction.Axis.fromString(portalAxis), portalRect);
    }

    /**
     * {@return whether the entity can use nether portals and end portals}
     *
     * <p>{@link EnderDragonEntity},
     * {@link WitherEntity}, and {@link
     * FishingBobberEntity} cannot use portals.
     */
    @HostAccess.Export
    public boolean canUsePortals(boolean allowVehicles) {
        return mcEntity.canUsePortals(allowVehicles);
    }

    @HostAccess.Export
    public boolean canTeleportBetween(World from, World to) {
        return mcEntity.canTeleportBetween(from, to);
    }

    /**
     * {@return the blast resistance of {@code blockState} for an explosion caused
     * by this entity}
     *
     * @apiNote {@link WitherSkullEntity} overrides
     * this to implement the "charged/blue skull" behavior.
     * @see ExplosionBehavior#getBlastResistance
     */
    @HostAccess.Export
    public float getEffectiveExplosionResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, float max) {
        return mcEntity.getEffectiveExplosionResistance(explosion, world, pos.mcObject, blockState.mcObject, fluidState, max);
    }

    /**
     * {@return whether {@code explosion} from this entity can destroy {@code state}}
     *
     * @apiNote This is used by {@link
     * TntMinecartEntity} to prevent the rail from being
     * destroyed by explosion.
     * @see ExplosionBehavior#canDestroyBlock
     */
    @HostAccess.Export
    public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
        return mcEntity.canExplosionDestroyBlock(explosion, world, pos.mcObject, state.mcObject, explosionPower);
    }

    /**
     * {@return the maximum height of a fall the entity takes during pathfinding}
     */
    @HostAccess.Export
    public int getSafeFallDistance() {
        return mcEntity.getSafeFallDistance();
    }

    /**
     * {@return whether the entity cannot trigger pressure plates or tripwires}
     *
     * <p>{@link BatEntity} is the only entity in vanilla
     * that can avoid traps.
     */
    @HostAccess.Export
    public boolean canAvoidTraps() {
        return mcEntity.canAvoidTraps();
    }


    /**
     * {@return whether an entity should render as being on fire}
     *
     * <p>This returns whether the entity {@linkplain #isOnFire is on fire} and
     * is not a spectator.
     *
     * @see #isOnFire
     */
    @HostAccess.Export
    public boolean doesRenderOnFire() {
        return mcEntity.doesRenderOnFire();
    }

    @HostAccess.Export
    public UUID getUuid() {
        return mcEntity.getUuid();
    }

    /**
     * {@return the entity's UUID as string}
     *
     * <p>This is a shortcut of {@code getUuid().toString()}.
     *
     * @see #getUuid
     */
    @HostAccess.Export
    public String getUuidAsString() {
        return mcEntity.getUuidAsString();
    }

    @HostAccess.Export
    public String getNameForScoreboard() {
        return mcEntity.getNameForScoreboard();
    }

    /**
     * {@return whether the entity is pushed by fluids}
     *
     * @apiNote Aquatic mobs should override this to return {@code false}.
     * Players are not pushed by fluids if they can fly (e.g. because of game mode).
     */
    @HostAccess.Export
    public boolean isPushedByFluids() {
        return mcEntity.isPushedByFluids();
    }

    /**
     * {@return the entity render distance multiplier}
     *
     * <p>This is only usable on the client.
     */
    @HostAccess.Export
    public static double getRenderDistanceMultiplier() {
        return net.minecraft.entity.Entity.getRenderDistanceMultiplier();
    }

    @HostAccess.Export
    public Text getDisplayName() {
        return mcEntity.getDisplayName();
    }


    @Nullable
    @HostAccess.Export
    public Text getCustomName() {
        return mcEntity.getCustomName();
    }

    @HostAccess.Export
    public boolean hasCustomName() {
        return mcEntity.hasCustomName();
    }


    /**
     * {@return whether the custom name should be shown}
     *
     * <p>This is stored on {@code CustomNameVisible} NBT key.
     *
     */
    @HostAccess.Export
    public boolean isCustomNameVisible() {
        return mcEntity.isCustomNameVisible();
    }


    /**
     * {@return whether to render the name of the entity}
     *
     * <p>This returns {@code true} for players and {@link #isCustomNameVisible} for
     * other entities.
     *
     * @see #isCustomNameVisible
     */
    @HostAccess.Export
    public boolean shouldRenderName() {
        return mcEntity.shouldRenderName();
    }


    @HostAccess.Export
    public boolean recalculateDimensions(EntityDimensions previous) {
        return mcEntity.recalculateDimensions(previous);
    }

    @HostAccess.Export
    public String getHorizontalFacing() {
        return Direction.fromHorizontalDegrees(this.getYaw());
    }

    @HostAccess.Export
    public String getMovementDirection() {
        return this.getHorizontalFacing();
    }

    /**
     * {@return whether {@code spectator} can spectate this entity}
     *
     * <p>Spectator players (other than themselves) cannot be spectated.
     */
    @HostAccess.Export
    public boolean canBeSpectated(ServerPlayerEntity spectator) {
        return mcEntity.canBeSpectated(spectator);
    }

    @HostAccess.Export
    public final Box getBoundingBox() {
        return mcEntity.getBoundingBox();
    }


    /**
     * {@return the eye height for {@code pose}}
     */
    @HostAccess.Export
    public final float getEyeHeight(EntityPose pose) {
        return mcEntity.getEyeHeight(pose);
    }

    /**
     * {@return the standing eye height}
     *
     * <p>This is used for calculating the leash offset.
     *
     */
    @HostAccess.Export
    public final float getStandingEyeHeight() {
        return mcEntity.getStandingEyeHeight();
    }


    /**
     * {@return the server the entity is in, or {@code null} if called on the client side}
     */
    @Nullable
    @HostAccess.Export
    public MinecraftServer getServer() {
        return mcEntity.getServer();
    }


    /**
     * {@return whether the entity is immune from explosion knockback and damage}
     *
     * <p>Invisible {@link ArmorStandEntity} and
     * emerging or digging {@link WardenEntity} are
     * immune from explosions.
     */
    @HostAccess.Export
    public boolean isImmuneToExplosion(Explosion explosion) {
        return mcEntity.isImmuneToExplosion(explosion);
    }

    @HostAccess.Export
    public ProjectileDeflection getProjectileDeflection(ProjectileEntity projectile) {
        return mcEntity.getProjectileDeflection(projectile);
    }

    /**
     * {@return the passenger in control of this entity, or {@code null} if there is none}
     *
     * <p>Rideable entities should override this to return the entity. This is
     * usually {@code #getFirstPassenger}.
     *
     * @see #hasControllingPassenger
     * @see #getPassengerList
     * @see #getFirstPassenger
     */
    @Nullable
    @HostAccess.Export
    public LivingEntity getControllingPassenger() {
        return mcEntity.getControllingPassenger();
    }

    /**
     * {@return whether there is a passenger in control of this entity}
     *
     * @see #getControllingPassenger
     * @see #getPassengerList
     * @see #getFirstPassenger
     */
    @HostAccess.Export
    public final boolean hasControllingPassenger() {
        return mcEntity.hasControllingPassenger();
    }

    /**
     * {@return the list of passengers of this entity}
     *
     * @see #getControllingPassenger
     * @see #getFirstPassenger
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     */
    @HostAccess.Export
    public final List<Entity> getPassengerList() {
        return mcEntity.getPassengerList().stream().map(Entity::new).collect(Collectors.toList());
    }

    /**
     * {@return the first passenger of the {@linkplain #getPassengerList passenger list},
     * or {@code null} if there is no passengers}
     *
     * <p>Such passenger is usually also the {@linkplain #getControllingPassenger the
     * controlling passenger}.
     *
     * @see #getControllingPassenger
     * @see #hasControllingPassenger
     * @see #getPassengerList
     */
    @Nullable
    @HostAccess.Export
    public Entity getFirstPassenger() {
        return new Entity(mcEntity.getFirstPassenger());
    }

    /**
     * {@return whether {@code passenger} is a passenger of this entity}
     *
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     * @see #hasPassenger(Predicate)
     */
    @HostAccess.Export
    public boolean hasPassenger(Entity passenger) {
        return mcEntity.hasPassenger(passenger.mcEntity);
    }

    /**
     * {@return whether there is a passenger of this entity matching {@code predicate}}
     *
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     * @see #hasPassenger(Entity)
     */
    @HostAccess.Export
    public boolean hasPassenger(Predicate<net.minecraft.entity.Entity> predicate) {
        return mcEntity.hasPassenger(predicate);
    }

    /**
     * {@return a recursive stream of all passengers}
     *
     * <p>This is recursive; for example, if a boat has 2 pigs, ridden by player A and
     * player B, then {@code boat.streamIntoPassengers()} would return a stream of
     * the first pig, player A, the second pig, and player B. This does not stream
     * the vehicle itself.
     *
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     */
    @HostAccess.Export
    public Stream<Entity> streamSelfAndPassengers() {
        return mcEntity.streamSelfAndPassengers().map(Entity::new);
    }

    @HostAccess.Export
    public Stream<Entity> streamPassengersAndSelf() {
        return mcEntity.streamPassengersAndSelf().map(Entity::new);
    }

    /**
     * {@return an iterable of all passengers}
     *
     * <p>This is recursive; for example, if a boat has 2 pigs, ridden by player A and
     * player B, then {@code boat.streamIntoPassengers()} would return a stream of
     * the first pig, player A, the second pig, and player B. This does not stream
     * the vehicle itself.
     *
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     */
    @HostAccess.Export
    public Iterable<Entity> getPassengersDeep() {
        return () -> mcEntity.getPassengerList().stream().map(Entity::new).iterator();
    }

    @HostAccess.Export
    public int getPlayerPassengers() {
        return mcEntity.getPlayerPassengers();
    }

    /**
     * {@return whether a player is riding this entity or any of its passengers}
     *
     * @implNote The default implementation is very inefficient.
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     * @see #hasPassengerDeep
     */
    @HostAccess.Export
    public boolean hasPlayerRider() {
        return mcEntity.hasPlayerRider();
    }

    /**
     * {@return the lowest entity this entity is riding}
     *
     * @see #getVehicle
     */
    @HostAccess.Export
    public Entity getRootVehicle() {
        return new Entity(mcEntity.getRootVehicle());
    }

    /**
     * {@return whether this entity and another entity share the same root vehicle}
     *
     * @param entity the other entity
     * @see #getRootVehicle
     * @see #getVehicle
     */
    @HostAccess.Export
    public boolean isConnectedThroughVehicle(Entity entity) {
        return mcEntity.isConnectedThroughVehicle(entity.mcEntity);
    }

    /**
     * {@return whether {@code passenger} is riding this entity or any of its passengers}
     *
     * @see #getPassengerList
     * @see #streamSelfAndPassengers
     * @see #streamPassengersAndSelf
     * @see #getPassengersDeep
     * @see #hasPlayerRider
     */
    @HostAccess.Export
    public boolean hasPassengerDeep(Entity passenger) {
        return mcEntity.hasPassengerDeep(passenger.mcEntity);
    }

    @HostAccess.Export
    public final boolean isLogicalSideForUpdatingMovement() {
        return mcEntity.isLogicalSideForUpdatingMovement();
    }


    @HostAccess.Export
    public boolean isControlledByPlayer() {
        return mcEntity.isControlledByPlayer();
    }

    @HostAccess.Export
    public boolean canMoveVoluntarily() {
        return mcEntity.canMoveVoluntarily();
    }

    @HostAccess.Export
    public boolean canActVoluntarily() {
        return mcEntity.canActVoluntarily();
    }


    /**
     * {@return the entity this entity rides, or {@code null} if there is none}
     *
     * @see #getRootVehicle
     * @see #getControllingVehicle
     */
    @Nullable
    @HostAccess.Export
    public Entity getVehicle() {
        net.minecraft.entity.Entity vehicle = mcEntity.getVehicle();
        return vehicle == null ? null : new Entity(vehicle);
    }

    /**
     * {@return the entity this entity rides and controls, or {@code null} if there is none}
     *
     * @see #getRootVehicle
     * @see #getVehicle
     */
    @Nullable
    @HostAccess.Export
    public Entity getControllingVehicle() {
        net.minecraft.entity.Entity controllingVehicle = mcEntity.getControllingVehicle();
        return controllingVehicle == null ? null : new Entity(controllingVehicle);
    }

    @HostAccess.Export
    public String getPistonBehavior() {
        return PistonBehavior.toString(mcEntity.getPistonBehavior());
    }

    /**
     * {@return a command source which represents this entity}
     */
    @HostAccess.Export
    public ServerCommandSource getCommandSource(ServerWorld world) {
        return mcEntity.getCommandSource(world);
    }


    @HostAccess.Export
    public float lerpYaw(float tickProgress) {
        return mcEntity.lerpYaw(tickProgress);
    }

    @HostAccess.Export
    public boolean updateMovementInFluid(TagKey<Fluid> tag, double speed) {
        return mcEntity.updateMovementInFluid(tag, speed);
    }

    /**
     * {@return whether any part of this entity's bounding box is in an unloaded
     * region of the world the entity is in}
     *
     * @implNote This implementation expands this entity's bounding box by 1 in
     * each axis and checks whether the expanded box's smallest enclosing
     * axis-aligned integer box is fully loaded in the world.
     */
    @HostAccess.Export
    public boolean isRegionUnloaded() {
        return mcEntity.isRegionUnloaded();
    }

    /**
     * {@return the height of the fluid in {@code fluid} tag}
     */
    @HostAccess.Export
    public double getFluidHeight(TagKey<Fluid> fluid) {
        return mcEntity.getFluidHeight(fluid);
    }

    /**
     * {@return the minimum submerged height of this entity in fluid so that it
     * would be affected by fluid physics}
     *
     * @apiNote This is also used by living entities for checking whether to
     * start swimming.
     * @implNote This implementation returns {@code 0.4} if its
     * {@linkplain #getStandingEyeHeight standing eye height} is larger than
     * {@code 0.4}; otherwise it returns {@code 0.0} for shorter entities.
     * The swim height of 0 allows short entities like baby animals
     * to start swimming to avoid suffocation.
     */
    @HostAccess.Export
    public double getSwimHeight() {
        return mcEntity.getSwimHeight();
    }

    /**
     * {@return the width of the entity's current dimension}
     */
    @HostAccess.Export
    public final float getWidth() {
        return mcEntity.getWidth();
    }

    /**
     * {@return the height of the entity's current dimension}
     */
    @HostAccess.Export
    public final float getHeight() {
        return mcEntity.getHeight();
    }

    /**
     * {@return the dimensions of the entity with the given {@code pose}}
     *
     * @see #getWidth
     * @see #getHeight
     */
    @HostAccess.Export
    public EntityDimensions getDimensions(EntityPose pose) {
        return mcEntity.getDimensions(pose);
    }

    @HostAccess.Export
    public final EntityAttachments getAttachments() {
        return mcEntity.getAttachments();
    }

    /**
     * {@return the exact position of the entity}
     *
     * @see #getSyncedPos
     * @see #getBlockPos
     * @see #getChunkPos
     */
    @HostAccess.Export
    public Vec3d getPos() {
        return mcEntity.getPos();
    }

    /**
     * {@return the position of the entity synced to clients}
     *
     * <p>This is the same as {@link #getPos} except for paintings which return the
     * attachment position.
     *
     * @see #getPos
     * @see #getBlockPos
     * @see #getChunkPos
     */
    @HostAccess.Export
    public Vec3d getSyncedPos() {
        return this.getPos();
    }

    @HostAccess.Export
    public BlockPos getBlockPos() {
        return new BlockPos(mcEntity.getBlockPos());
    }

    /**
     * {@return the block state at the entity's position}
     *
     * <p>The result is cached.
     *
     * @see #getBlockPos
     * @see #getSteppingBlockState
     */
    @HostAccess.Export
    public BlockState getBlockStateAtPos() {
        return new BlockState(mcEntity.getBlockStateAtPos());
    }

    /**
     * {@return the chunk position of the entity}
     */
    @HostAccess.Export
    public ChunkPos getChunkPos() {
        return mcEntity.getChunkPos();
    }

    @HostAccess.Export
    public Vec3d getVelocity() {
        return mcEntity.getVelocity();
    }


    @HostAccess.Export
    public final int getBlockX() {
        return mcEntity.getBlockX();
    }

    @HostAccess.Export
    public final double getX() {
        return mcEntity.getX();
    }

    @HostAccess.Export
    public double getBodyX(double widthScale) {
        return mcEntity.getBodyX(widthScale);
    }

    @HostAccess.Export
    public double getParticleX(double widthScale) {
        return mcEntity.getParticleX(widthScale);
    }

    @HostAccess.Export
    public final int getBlockY() {
        return mcEntity.getBlockY();
    }

    @HostAccess.Export
    public final double getY() {
        return mcEntity.getY();
    }

    @HostAccess.Export
    public double getBodyY(double heightScale) {
        return mcEntity.getBodyY(heightScale);
    }

    @HostAccess.Export
    public double getRandomBodyY() {
        return mcEntity.getRandomBodyY();
    }

    @HostAccess.Export
    public double getEyeY() {
        return mcEntity.getEyeY();
    }

    @HostAccess.Export
    public final int getBlockZ() {
        return mcEntity.getBlockZ();
    }

    @HostAccess.Export
    public final double getZ() {
        return mcEntity.getZ();
    }

    @HostAccess.Export
    public double getBodyZ(double widthScale) {
        return mcEntity.getBodyZ(widthScale);
    }

    @HostAccess.Export
    public double getParticleZ(double widthScale) {
        return mcEntity.getParticleZ(widthScale);
    }


    @HostAccess.Export
    public Vec3d[] getHeldQuadLeashOffsets() {
        return mcEntity.getHeldQuadLeashOffsets();
    }

    @HostAccess.Export
    public boolean hasQuadLeashAttachmentPoints() {
        return mcEntity.hasQuadLeashAttachmentPoints();
    }

    /**
     * {@return the position of the leash this entity holds}
     *
     * <p>This is different from getLeashOffset; this method is called on the entity
     * that holds the leash.
     *
     * @see #getStandingEyeHeight
     */
    @HostAccess.Export
    public Vec3d getLeashPos(float tickProgress) {
        return mcEntity.getLeashPos(tickProgress);
    }


    /**
     * {@return the stack for creative "pick block" functionality, or {@code null}
     * if there is none}
     *
     * <p>If the entity has an item representation (such as boats or minecarts),
     * this should be overridden to return a new stack. Note that {@link
     * MobEntity} handles the spawn eggs.
     * {@link ItemFrameEntity} instead returns
     * the copy of the stack held in the frame.
     */
    @Nullable
    @HostAccess.Export
    public ItemStack getPickBlockStack() {
        return mcEntity.getPickBlockStack();
    }


    /**
     * {@return whether the entity can freeze}
     *
     * @implNote Entities cannot be frozen if they are in the {@link
     * EntityTypeTags#FREEZE_IMMUNE_ENTITY_TYPES} tag. In addition to this, {@link
     * LivingEntity} cannot be frozen if they are spectator or if they wear an
     * item inside {@link ItemTags#FREEZE_IMMUNE_WEARABLES} tag.
     */
    @HostAccess.Export
    public boolean canFreeze() {
        return mcEntity.canFreeze();
    }

    /**
     * {@return whether the entity should escape from powder snow}
     *
     * <p>This returns {@code true} if the entity is/was in powder snow and
     * if it can freeze.
     *
     * @see #canFreeze
     * @see #isFrozen
     */
    @HostAccess.Export
    public boolean shouldEscapePowderSnow() {
        return this.getFrozenTicks() > 0;
    }

    @HostAccess.Export
    public float getYaw() {
        return mcEntity.getYaw();
    }

    /**
     * {@return the body yaw of the entity}
     *
     */
    @HostAccess.Export
    public float getBodyYaw() {
        return mcEntity.getBodyYaw();
    }


    @HostAccess.Export
    public float getPitch() {
        return mcEntity.getPitch();
    }


    @HostAccess.Export
    public boolean canSprintAsVehicle() {
        return mcEntity.canSprintAsVehicle();
    }

    @HostAccess.Export
    public float getStepHeight() {
        return mcEntity.getStepHeight();
    }


    @HostAccess.Export
    public final boolean isRemoved() {
        return mcEntity.isRemoved();
    }

    /**
     * {@return the reason for the entity's removal, or {@code null} if it is not removed}
     */
    @Nullable
    @HostAccess.Export
    public net.minecraft.entity.Entity.RemovalReason getRemovalReason() {
        return mcEntity.getRemovalReason();
    }


    @HostAccess.Export
    public boolean shouldSave() {
        return mcEntity.shouldSave();
    }

    @HostAccess.Export
    public boolean isPlayer() {
        return mcEntity.isPlayer();
    }

    /**
     * {@return whether the entity can modify the world at {@code pos}}
     *
     * <p>This returns {@code true} for most entities. Players check canPlayerModifyAt
     * to prevent them from modifying entities in the spawn protection or outside the world border. {@link
     * ProjectileEntity} delegates it to the owner
     * if the owner is a player; if the owner is a non-player entity, this returns
     * the value of {@link GameRules#DO_MOB_GRIEFING}, and ownerless
     * projectiles are always allowed to modify the world.
     *
     */
    @HostAccess.Export
    public boolean canModifyAt(ServerWorld world, BlockPos pos) {
        return mcEntity.canModifyAt(world, pos.mcObject);
    }

    @HostAccess.Export
    public boolean isFlyingVehicle() {
        return mcEntity.isFlyingVehicle();
    }

    @HostAccess.Export
    public World getWorld() {
        return mcEntity.getWorld();
    }


    @HostAccess.Export
    public DamageSources getDamageSources() {
        return mcEntity.getDamageSources();
    }

    @HostAccess.Export
    public DynamicRegistryManager getRegistryManager() {
        return mcEntity.getRegistryManager();
    }

    @HostAccess.Export
    public Vec3d getMovement() {
        return mcEntity.getMovement();
    }

    @Nullable
    @HostAccess.Export
    public ItemStack getWeaponStack() {
        return mcEntity.getWeaponStack();
    }

    @HostAccess.Export
    public Optional<RegistryKey<LootTable>> getLootTableKey() {
        return mcEntity.getLootTableKey();
    }

}