package com.soggyrhino.triggered.client.api.objects.entity;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

@MCObject
public class Entity {
    private final net.minecraft.entity.Entity mcEntity;

    private Entity(net.minecraft.entity.Entity mcEntity) {
        this.mcEntity = mcEntity;
    }


    /**
     * {@return whether the entity collides with the block {@code state} at {@code pos}}
     */
    @HostAccess.Export
    public boolean collidesWithStateAtPos(BlockPos pos, BlockState state) {
        return mcEntity.collidesWithStateAtPos(pos, state);
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
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return mcEntity.doesNotCollide(offsetX, offsetY, offsetZ);
    }

    public boolean isSupportedBy(BlockPos pos) {
        return mcEntity.isSupportedBy(pos);
    }

    /**
     * {@return whether the entity is on the ground}
     */
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
    public BlockPos getLandingPos() {
        return mcEntity.getLandingPos();
    }

    /**
     * {@return whether the entity is silent}
     *
     * <p>Silent entities should not make sounds. PlaySound checks this method by
     * default, but if a sound is played manually, this has to be checked too.
     *
     * <p>This is saved under the {@code Silent} NBT key.
     */
    public BlockPos getVelocityAffectingPos() {
        return mcEntity.getVelocityAffectingPos();
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
    public BlockPos getSteppingPos() {
        return mcEntity.getSteppingPos();
    }

    //todo missing static methods

    public boolean collidesWithFluid(FluidState state, BlockPos fluidPos, Vec3d oldPos, Vec3d newPos) {
        return mcEntity.collidesWithFluid(state, fluidPos, oldPos, newPos);
    }

    public boolean collides(Vec3d oldPos, Vec3d newPos, List<Box> boxes) {
        return mcEntity.collides(oldPos, newPos, boxes);
    }

    public BlockPos getWorldSpawnPos(ServerWorld world, BlockPos basePos) {
        return mcEntity.getWorldSpawnPos(world, basePos);
    }

    /**
     * {@return whether the entity has no gravity}
     *
     * <p>Entities using {@link net.minecraft.entity.ai.control.FlightMoveControl} has
     * no gravity. This is saved under the {@code NoGravity} NBT key.
     */
    public boolean hasNoGravity() {
        return mcEntity.hasNoGravity();
    }

    /**
     * {@return whether the entity should not emit vibrations}
     *
     * <p>By default, wool or carpet {@linkplain ItemEntity item entities}, and
     * {@link net.minecraft.entity.mob.WardenEntity} do not emit vibrations.
     */
    public final double getFinalGravity() {
        return mcEntity.getFinalGravity();
    }

    public boolean occludeVibrationSignals() {
        return mcEntity.occludeVibrationSignals();
    }

    /**
     * {@return whether the entity is immune to {@linkplain
     * net.minecraft.registry.tag.DamageTypeTags#IS_FIRE fire damage}}
     *
     * @see EntityType.Builder#makeFireImmune
     */
    public boolean isFireImmune() {
        return mcEntity.isFireImmune();
    }

    /**
     * Returns whether this entity's hitbox is touching water fluid.
     */
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
    public boolean isTouchingWaterOrRain() {
        return mcEntity.isTouchingWaterOrRain();
    }

    public boolean isInFluid() {
        return mcEntity.isInFluid();
    }

    /**
     * {@return whether this entity's hitbox is fully submerged in water}
     */
    public boolean isSubmergedInWater() {
        return mcEntity.isSubmergedInWater();
    }

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
    public BlockState getSteppingBlockState() {
        return mcEntity.getSteppingBlockState();
    }

    public boolean shouldSpawnSprintingParticles() {
        return mcEntity.shouldSpawnSprintingParticles();
    }

    /**
     * {@return whether the entity is submerged in a fluid in {@code fluidTag}}
     */
    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return mcEntity.isSubmergedIn(fluidTag);
    }

    /**
     * {@return whether the entity is in lava}
     */
    public boolean isInLava() {
        return mcEntity.isInLava();
    }

    public final Vec3d getLastRenderPos() {
        return mcEntity.getLastRenderPos();
    }


    /**
     * {@return the distance between this entity and {@code entity}}
     */
    public float distanceTo(Entity entity) {
        return mcEntity.distanceTo(entity.mcEntity);
    }

    public double squaredDistanceTo(double x, double y, double z) {
        return mcEntity.squaredDistanceTo(x, y, z);
    }


    /**
     * {@return the squared distance between this entity and {@code entity}}
     */
    public double squaredDistanceTo(Entity entity) {
        return mcEntity.squaredDistanceTo(entity.getPos());
    }

    /**
     * {@return the squared distance between this entity and the given position}
     */
    public double squaredDistanceTo(Vec3d vector) {
        return mcEntity.squaredDistanceTo(vector);
    }

    public final Vec3d getRotationVec(float tickProgress) {
        return mcEntity.getRotationVec(tickProgress);
    }

    public Direction getFacing() {
        return mcEntity.getFacing();
    }

    public float getPitch(float tickProgress) {
        return mcEntity.getPitch(tickProgress);
    }

    public float getYaw(float tickProgress) {
        return mcEntity.getYaw(tickProgress);
    }

    public float getLerpedPitch(float tickProgress) {
        return mcEntity.getLerpedPitch(tickProgress);
    }

    public float getLerpedYaw(float tickProgress) {
        return mcEntity.getLerpedYaw(tickProgress);
    }

    public final Vec3d getRotationVector(float pitch, float yaw) {
        return mcEntity.getRotationVector(pitch, yaw);
    }

    public final Vec3d getOppositeRotationVector(float tickProgress) {
        return mcEntity.getOppositeRotationVector(tickProgress);
    }


    /**
     * {@return the position of the eye}
     *
     * @see #getEyeY
     */
    public final Vec3d getEyePos() {
        return mcEntity.getEyePos();
    }

    public final Vec3d getCameraPosVec(float tickProgress) {
        return mcEntity.getCameraPosVec(tickProgress);
    }

    public Vec3d getClientCameraPosVec(float tickProgress) {
        return mcEntity.getCameraPosVec(tickProgress);
    }

    public final Vec3d getLerpedPos(float deltaTicks) {
        return mcEntity.getLerpedPos(deltaTicks);
    }

    public HitResult raycast(double maxDistance, float tickProgress, boolean includeFluids) {
        return mcEntity.raycast(maxDistance, tickProgress, includeFluids);
    }

    public boolean canBeHitByProjectile() {
        return mcEntity.canBeHitByProjectile();
    }


    /**
     * {@return whether the entity can be hit with a projectile or be targeted by
     * the player crosshair}
     */
    public boolean canHit() {
        return mcEntity.canHit();
    }


    /**
     * {@return whether the entity can be pushed by other entities}
     */
    public boolean isPushable() {
        return mcEntity.isPushable();
    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return mcEntity.shouldRender(cameraX, cameraY, cameraZ);
    }

    public boolean shouldRender(double distance) {
        return mcEntity.shouldRender(distance);
    }


    /**
     * {@return whether the entity is alive}
     *
     * <p>For non-{@link LivingEntity}, this is the same as negating {@link #isRemoved}.
     * {@link LivingEntity} checks the entity's health in addition to the removal.
     */
    public boolean isAlive() {
        return mcEntity.isAlive();
    }


    /**
     * {@return whether the entity is in a wall and should suffocate}
     *
     * <p>This returns {@code false} if noClip is {@code true}; otherwise,
     * this returns {@code true} if the eye position is occupied by a {@linkplain
     * net.minecraft.block.AbstractBlock.Settings#suffocates block that can suffocate}.
     */
    public boolean isInsideWall() {
        return mcEntity.isInsideWall();
    }


    /**
     * {@return whether this entity cannot occupy the same space with {@code other}}
     *
     * <p>This returns {@code false} if {@code other} is {@linkplain #isConnectedThroughVehicle
     * connected through vehicles}.
     *
     * @see #isCollidable
     */
    public boolean collidesWith(Entity other) {
        return mcEntity.collidesWith(other.mcEntity);
    }


    /**
     * {@return whether other entities cannot occupy the same space with this entity}
     *
     * <p>If {@code true}, other entities can stand on this entity without falling.
     * {@link net.minecraft.entity.vehicle.BoatEntity} and {@link
     * net.minecraft.entity.mob.ShulkerEntity} has this behavior.
     *
     * @see #collidesWith
     */
    public boolean isCollidable(Entity entity) {
        return mcEntity.isCollidable(entity.mcEntity);
    }

    public Vec3d getVehicleAttachmentPos(Entity vehicle) {
        return mcEntity.getVehicleAttachmentPos(vehicle.mcEntity);
    }

    public Vec3d getPassengerRidingPos(Entity passenger) {
        return mcEntity.getPassengerRidingPos(passenger.mcEntity);
    }

    public boolean isLiving() {
        return mcEntity.isLiving();
    }


    /**
     * {@return the margin around the entity's bounding box where the entity
     * targeting is still successful}
     *
     * @apiNote {@link net.minecraft.entity.projectile.ExplosiveProjectileEntity}
     * overrides this method to return {@code 1.0f}, which expands the ghast fireball's
     * effective hitbox.
     */
    public float getTargetingMargin() {
        return mcEntity.getTargetingMargin();
    }

    public Vec3d getRotationVector() {
        return mcEntity.getRotationVector();
    }

    public Vec3d getHandPosOffset(Item item) {
        return mcEntity.getHandPosOffset(item);
    }

    public Vec2f getRotationClient() {
        return mcEntity.getRotationClient();
    }

    public Vec3d getRotationVecClient() {
        return mcEntity.getRotationVecClient();
    }


    /**
     * {@return the entity's default portal cooldown}
     *
     * <p>This is 300 ticks by default, or 10 ticks for players.
     *
     */
    public int getDefaultPortalCooldown() {
        return mcEntity.getDefaultPortalCooldown();
    }


    /**
     * {@return whether the entity is on fire and is not fire immune}
     *
     * @see #isFireImmune
     */
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
    public boolean hasPassengers() {
        return mcEntity.hasPassengers();
    }


    /**
     * {@return whether this vehicle should dismount the passenger if submerged underwater}
     */
    public boolean shouldDismountUnderwater() {
        return mcEntity.shouldDismountUnderwater();
    }

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
    public boolean isSneaking() {
        return mcEntity.isSneaking();
    }

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
    public boolean isSwimming() {
        return mcEntity.isSwimming();
    }


// public static Vec3d adjustMovementForCollisions(@Nullable Entity entity, Vec3d movement, Box entityBoundingBox, World world, List<VoxelShape> collisions) {
//     List<VoxelShape> list = Entity.findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));


    /**
     * {@return whether the entity is in swimming pose}
     *
     * <p>This includes crawling entities and entities using elytra that aren't fall-flying.
     * Players start crawling if they would otherwise collide with blocks (for example,
     * when the player is in a 1 block tall tunnel).
     *
     * @see #isCrawling
     */
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
    public boolean isCrawling() {
        return this.isInSwimmingPose() && !this.isTouchingWater();
    }


    /**
     * {@return whether the entity is glowing, without checking the entity flags}
     *
     * @apiNote This is only used to copy entity data to NBT when bucketing.
     * @see #isGlowing
     */
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
    public boolean isGlowing() {
        return mcEntity.isGlowing();
    }

    /**
     * {@return whether the entity is invisible to everyone}
     *
     * <p>Invisibility status effect and {@link
     * net.minecraft.entity.decoration.ArmorStandEntity}'s {@code Invisible} NBT key can
     * cause an entity to be invisible.
     *
     * @see #isInvisibleTo
     */
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
    public boolean isInvisibleTo(PlayerEntity player) {
        return mcEntity.isInvisibleTo(player);
    }

    public boolean isOnRail() {
        return false;
    }

    /**
     * {@return the scoreboard team the entity belongs to, or {@code null} if there is none}
     */
    @Nullable
    public Team getScoreboardTeam() {
        return mcEntity.getScoreboardTeam();
    }

    /**
     * {@return whether this entity and {@code other} are in the same team}
     *
     * <p>This returns {@code false} if this entity is not in any team.
     */
    public final boolean isTeammate(@Nullable Entity other) {
        return other != null && mcEntity.isTeammate(other.mcEntity);
    }


    /**
     * {@return whether this entity is in {@code team}}
     *
     * <p>This returns {@code false} if this entity is not in any team.
     */
    public boolean isTeamPlayer(@Nullable AbstractTeam team) {
        return mcEntity.isTeamPlayer(team);
    }


    /**
     * {@return the maximum amount of air the entity can hold, in ticks}
     *
     * <p>Most entities have the max air of 300 ticks, or 15 seconds.
     * {@link net.minecraft.entity.passive.DolphinEntity} has 4800 ticks or 4
     * minutes; {@link net.minecraft.entity.passive.AxolotlEntity} has 6000 ticks
     * or 5 minutes. Note that this does not include enchantments.
     *
     * @see #getAir
     */
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
     * @apiNote {@link net.minecraft.entity.mob.WaterCreatureEntity} reuses the air to
     * indicate the entity's air breathed when the entity is in water. If the entity is
     * not touching a water, the air decrements, and the entity drowns in the same way
     * as other entities.
     * @see #getMaxAir
     */
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
    public int getMinFreezeDamageTicks() {
        return mcEntity.getMinFreezeDamageTicks();
    }

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
    public boolean isPartOf(Entity entity) {
        return mcEntity.isPartOf(entity.mcEntity);
    }

    /**
     * {@return the head yaw of the entity}
     *
     */
    public float getHeadYaw() {
        return mcEntity.getHeadYaw();
    }


    /**
     * {@return whether the entity can be attacked by players}
     *
     * <p>Note that this is not called for most entities defined in vanilla as unattackable
     * (such as {@link net.minecraft.entity.ItemEntity} and {@link net.minecraft.entity.ExperienceOrbEntity}) as trying to attack them
     * kicks the player.
     *
     * @see net.minecraft.server.network.ServerPlayNetworkHandler#onPlayerInteractEntity
     */
    public boolean isAttackable() {
        return mcEntity.isAttackable();
    }

    public String toString() {
        return mcEntity.toString();
    }


    /**
     * {@return whether the entity is invulnerable}
     *
     * <p>This is saved on the {@code Invulnerable} NBT key.
     *
     * @implNote Invulnerable entities are immune from all damages except {@link
     * net.minecraft.entity.damage.DamageTypes#OUT_OF_WORLD}
     * and damages by creative mode players by default.
     */
    public boolean isInvulnerable() {
        return mcEntity.isInvulnerable();
    }


    /**
     * {@return the entity's position in the portal after teleportation}
     *
     * @see net.minecraft.world.dimension.NetherPortal#entityPosInPortal
     */
    public Vec3d positionInPortal(Direction.Axis portalAxis, BlockLocating.Rectangle portalRect) {
        return mcEntity.positionInPortal(portalAxis, portalRect);
    }

    /**
     * {@return whether the entity can use nether portals and end portals}
     *
     * <p>{@link net.minecraft.entity.boss.dragon.EnderDragonEntity},
     * {@link net.minecraft.entity.boss.WitherEntity}, and {@link
     * net.minecraft.entity.projectile.FishingBobberEntity} cannot use portals.
     */
    public boolean canUsePortals(boolean allowVehicles) {
        return mcEntity.canUsePortals(allowVehicles);
    }

    public boolean canTeleportBetween(World from, World to) {
        return mcEntity.canTeleportBetween(from, to);
    }

    /**
     * {@return the blast resistance of {@code blockState} for an explosion caused
     * by this entity}
     *
     * @apiNote {@link net.minecraft.entity.projectile.WitherSkullEntity} overrides
     * this to implement the "charged/blue skull" behavior.
     * @see net.minecraft.world.explosion.ExplosionBehavior#getBlastResistance
     */
    public float getEffectiveExplosionResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, float max) {
        return mcEntity.getEffectiveExplosionResistance(explosion, world, pos, blockState, fluidState, max);
    }

    /**
     * {@return whether {@code explosion} from this entity can destroy {@code state}}
     *
     * @apiNote This is used by {@link
     * net.minecraft.entity.vehicle.TntMinecartEntity} to prevent the rail from being
     * destroyed by explosion.
     * @see net.minecraft.world.explosion.ExplosionBehavior#canDestroyBlock
     */
    public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
        return mcEntity.canExplosionDestroyBlock(explosion, world, pos, state, explosionPower);
    }

    /**
     * {@return the maximum height of a fall the entity takes during pathfinding}
     */
    public int getSafeFallDistance() {
        return mcEntity.getSafeFallDistance();
    }

    /**
     * {@return whether the entity cannot trigger pressure plates or tripwires}
     *
     * <p>{@link net.minecraft.entity.passive.BatEntity} is the only entity in vanilla
     * that can avoid traps.
     */
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
    public boolean doesRenderOnFire() {
        return mcEntity.doesRenderOnFire();
    }

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
    public String getUuidAsString() {
        return mcEntity.getUuidAsString();
    }

    public String getNameForScoreboard() {
        return mcEntity.getNameForScoreboard();
    }

    /**
     * {@return whether the entity is pushed by fluids}
     *
     * @apiNote Aquatic mobs should override this to return {@code false}.
     * Players are not pushed by fluids if they can fly (e.g. because of game mode).
     */
    public boolean isPushedByFluids() {
        return mcEntity.isPushedByFluids();
    }

    /**
     * {@return the entity render distance multiplier}
     *
     * <p>This is only usable on the client.
     */
    public static double getRenderDistanceMultiplier() {
        return net.minecraft.entity.Entity.getRenderDistanceMultiplier();
    }

    public Text getDisplayName() {
        return mcEntity.getDisplayName();
    }


    @Nullable
    public Text getCustomName() {
        return mcEntity.getCustomName();
    }

    public boolean hasCustomName() {
        return mcEntity.hasCustomName();
    }


    /**
     * {@return whether the custom name should be shown}
     *
     * <p>This is stored on {@code CustomNameVisible} NBT key.
     *
     */
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
    public boolean shouldRenderName() {
        return mcEntity.shouldRenderName();
    }


    public boolean recalculateDimensions(EntityDimensions previous) {
        return mcEntity.recalculateDimensions(previous);
    }

    public Direction getHorizontalFacing() {
        return Direction.fromHorizontalDegrees(this.getYaw());
    }

    public Direction getMovementDirection() {
        return this.getHorizontalFacing();
    }


    /**
     * {@return whether {@code spectator} can spectate this entity}
     *
     * <p>Spectator players (other than themselves) cannot be spectated.
     */
    public boolean canBeSpectated(ServerPlayerEntity spectator) {
        return mcEntity.canBeSpectated(spectator);
    }

    public final Box getBoundingBox() {
        return mcEntity.getBoundingBox();
    }


    /**
     * {@return the eye height for {@code pose}}
     */
    public final float getEyeHeight(EntityPose pose) {
        return mcEntity.getEyeHeight(pose);
    }

    /**
     * {@return the standing eye height}
     *
     * <p>This is used for calculating the leash offset.
     *
     */
    public final float getStandingEyeHeight() {
        return mcEntity.getStandingEyeHeight();
    }


    /**
     * {@return the server the entity is in, or {@code null} if called on the client side}
     */
    @Nullable
    public MinecraftServer getServer() {
        return mcEntity.getServer();
    }


    /**
     * {@return whether the entity is immune from explosion knockback and damage}
     *
     * <p>Invisible {@link net.minecraft.entity.decoration.ArmorStandEntity} and
     * emerging or digging {@link net.minecraft.entity.mob.WardenEntity} are
     * immune from explosions.
     */
    public boolean isImmuneToExplosion(Explosion explosion) {
        return mcEntity.isImmuneToExplosion(explosion);
    }

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
    public final List<Entity> getPassengerList() {
        return mcEntity.getPassengerList().stream().map(Entity::new).toList();
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
    public Stream<Entity> streamSelfAndPassengers() {
        return mcEntity.streamSelfAndPassengers().map(Entity::new);
    }

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
    public Iterable<Entity> getPassengersDeep() {
        return () -> mcEntity.getPassengerList().stream().map(Entity::new).iterator();
    }

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
    public boolean hasPlayerRider() {
        return mcEntity.hasPlayerRider();
    }

    /**
     * {@return the lowest entity this entity is riding}
     *
     * @see #getVehicle
     */
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
    public boolean hasPassengerDeep(Entity passenger) {
        return mcEntity.hasPassengerDeep(passenger.mcEntity);
    }

    public final boolean isLogicalSideForUpdatingMovement() {
        return mcEntity.isLogicalSideForUpdatingMovement();
    }


    public boolean isControlledByPlayer() {
        return mcEntity.isControlledByPlayer();
    }

    public boolean canMoveVoluntarily() {
        return mcEntity.canMoveVoluntarily();
    }

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
    public Entity getControllingVehicle() {
        net.minecraft.entity.Entity controllingVehicle = mcEntity.getControllingVehicle();
        return controllingVehicle == null ? null : new Entity(controllingVehicle);
    }

    /**
     * {@return the behavior of the piston for this entity}
     *
     * <p>This is {@link PistonBehavior#NORMAL} by default. {@link net.minecraft.entity.AreaEffectCloudEntity},
     * {@link net.minecraft.entity.MarkerEntity}, and marker {@link net.minecraft.entity.decoration.ArmorStandEntity}
     * return {@link PistonBehavior#IGNORE}, causing the piston to not affect the entity's
     * position. Other piston behaviors are inapplicable to entities, and treated like
     * {@link PistonBehavior#NORMAL}.
     */
    public PistonBehavior getPistonBehavior() {
        return mcEntity.getPistonBehavior();
    }

    /**
     * {@return a command source which represents this entity}
     */
    public ServerCommandSource getCommandSource(ServerWorld world) {
        return mcEntity.getCommandSource(world);
    }


    public float lerpYaw(float tickProgress) {
        return mcEntity.lerpYaw(tickProgress);
    }

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
    public boolean isRegionUnloaded() {
        return mcEntity.isRegionUnloaded();
    }

    /**
     * {@return the height of the fluid in {@code fluid} tag}
     */
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
    public double getSwimHeight() {
        return mcEntity.getSwimHeight();
    }

    /**
     * {@return the width of the entity's current dimension}
     */
    public final float getWidth() {
        return mcEntity.getWidth();
    }

    /**
     * {@return the height of the entity's current dimension}
     */
    public final float getHeight() {
        return mcEntity.getHeight();
    }

    /**
     * {@return the dimensions of the entity with the given {@code pose}}
     *
     * @see #getWidth
     * @see #getHeight
     */
    public EntityDimensions getDimensions(EntityPose pose) {
        return mcEntity.getDimensions(pose);
    }

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
    public Vec3d getSyncedPos() {
        return this.getPos();
    }

    public BlockPos getBlockPos() {
        return mcEntity.getBlockPos();
    }

    /**
     * {@return the block state at the entity's position}
     *
     * <p>The result is cached.
     *
     * @see #getBlockPos
     * @see #getSteppingBlockState
     */
    public BlockState getBlockStateAtPos() {
        return mcEntity.getBlockStateAtPos();
    }

    /**
     * {@return the chunk position of the entity}
     */
    public ChunkPos getChunkPos() {
        return mcEntity.getChunkPos();
    }

    public Vec3d getVelocity() {
        return mcEntity.getVelocity();
    }


    public final int getBlockX() {
        return mcEntity.getBlockX();
    }

    public final double getX() {
        return mcEntity.getX();
    }

    public double getBodyX(double widthScale) {
        return mcEntity.getBodyX(widthScale);
    }

    public double getParticleX(double widthScale) {
        return mcEntity.getParticleX(widthScale);
    }

    public final int getBlockY() {
        return mcEntity.getBlockY();
    }

    public final double getY() {
        return mcEntity.getY();
    }

    public double getBodyY(double heightScale) {
        return mcEntity.getBodyY(heightScale);
    }

    public double getRandomBodyY() {
        return mcEntity.getRandomBodyY();
    }

    public double getEyeY() {
        return mcEntity.getEyeY();
    }

    public final int getBlockZ() {
        return mcEntity.getBlockZ();
    }

    public final double getZ() {
        return mcEntity.getZ();
    }

    public double getBodyZ(double widthScale) {
        return mcEntity.getBodyZ(widthScale);
    }

    public double getParticleZ(double widthScale) {
        return mcEntity.getParticleZ(widthScale);
    }


    public Vec3d[] getHeldQuadLeashOffsets() {
        return mcEntity.getHeldQuadLeashOffsets();
    }

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
    public Vec3d getLeashPos(float tickProgress) {
        return mcEntity.getLeashPos(tickProgress);
    }


    /**
     * {@return the stack for creative "pick block" functionality, or {@code null}
     * if there is none}
     *
     * <p>If the entity has an item representation (such as boats or minecarts),
     * this should be overridden to return a new stack. Note that {@link
     * net.minecraft.entity.mob.MobEntity} handles the spawn eggs.
     * {@link net.minecraft.entity.decoration.ItemFrameEntity} instead returns
     * the copy of the stack held in the frame.
     */
    @Nullable
    public ItemStack getPickBlockStack() {
        return mcEntity.getPickBlockStack();
    }


    /**
     * {@return whether the entity can freeze}
     *
     * @implNote Entities cannot be frozen if they are in the {@link
     * net.minecraft.registry.tag.EntityTypeTags#FREEZE_IMMUNE_ENTITY_TYPES} tag. In addition to this, {@link
     * LivingEntity} cannot be frozen if they are spectator or if they wear an
     * item inside {@link net.minecraft.registry.tag.ItemTags#FREEZE_IMMUNE_WEARABLES} tag.
     */
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
    public boolean shouldEscapePowderSnow() {
        return this.getFrozenTicks() > 0;
    }

    public float getYaw() {
        return mcEntity.getYaw();
    }

    /**
     * {@return the body yaw of the entity}
     *
     */
    public float getBodyYaw() {
        return mcEntity.getBodyYaw();
    }


    public float getPitch() {
        return mcEntity.getPitch();
    }


    public boolean canSprintAsVehicle() {
        return mcEntity.canSprintAsVehicle();
    }

    public float getStepHeight() {
        return mcEntity.getStepHeight();
    }


    public final boolean isRemoved() {
        return mcEntity.isRemoved();
    }

    /**
     * {@return the reason for the entity's removal, or {@code null} if it is not removed}
     */
    @Nullable
    public net.minecraft.entity.Entity.RemovalReason getRemovalReason() {
        return mcEntity.getRemovalReason();
    }


    public boolean shouldSave() {
        return mcEntity.shouldSave();
    }

    public boolean isPlayer() {
        return mcEntity.isPlayer();
    }

    /**
     * {@return whether the entity can modify the world at {@code pos}}
     *
     * <p>This returns {@code true} for most entities. Players check canPlayerModifyAt
     * to prevent them from modifying entities in the spawn protection or outside the world border. {@link
     * net.minecraft.entity.projectile.ProjectileEntity} delegates it to the owner
     * if the owner is a player; if the owner is a non-player entity, this returns
     * the value of {@link net.minecraft.world.GameRules#DO_MOB_GRIEFING}, and ownerless
     * projectiles are always allowed to modify the world.
     *
     */
    public boolean canModifyAt(ServerWorld world, BlockPos pos) {
        return mcEntity.canModifyAt(world, pos);
    }

    public boolean isFlyingVehicle() {
        return mcEntity.isFlyingVehicle();
    }

    public World getWorld() {
        return mcEntity.getWorld();
    }


    public DamageSources getDamageSources() {
        return mcEntity.getDamageSources();
    }

    public DynamicRegistryManager getRegistryManager() {
        return mcEntity.getRegistryManager();
    }

    public Vec3d getMovement() {
        return mcEntity.getMovement();
    }

    @Nullable
    public ItemStack getWeaponStack() {
        return mcEntity.getWeaponStack();
    }

    public Optional<RegistryKey<LootTable>> getLootTableKey() {
        return mcEntity.getLootTableKey();
    }

    @Nullable
    public <T> T get(ComponentType<? extends T> type) {
        return mcEntity.get(type);
    }
}