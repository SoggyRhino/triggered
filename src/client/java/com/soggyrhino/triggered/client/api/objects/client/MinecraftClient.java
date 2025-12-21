package com.soggyrhino.triggered.client.api.objects.client;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.BanDetails;
import com.mojang.datafixers.DataFixer;
import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.entity.Entity;
import net.minecraft.client.QuickPlayLogger;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.ShaderLoader;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.navigation.GuiNavigationType;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.SocialInteractionsManager;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.option.InactivityFpsLimiter;
import net.minecraft.client.realms.RealmsPeriodicCheckers;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.client.resource.VideoWarningManager;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.resource.server.ServerResourcePackLoader;
import net.minecraft.client.resource.waypoint.WaypointStyleAssetManager;
import net.minecraft.client.session.Session;
import net.minecraft.client.session.report.AbuseReportContext;
import net.minecraft.client.session.telemetry.TelemetryManager;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.texture.*;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.client.util.CommandHistoryManager;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.Window;
import net.minecraft.network.encryption.SignatureVerifier;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.WorldGenerationProgressTracker;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.text.Text;
import net.minecraft.util.ModStatus;
import net.minecraft.util.path.SymlinkFinder;
import net.minecraft.world.level.storage.LevelStorage;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.Proxy;
import java.nio.file.Path;
import java.util.UUID;

@MCObject
public class MinecraftClient {
    public net.minecraft.client.MinecraftClient mcObject;

    public MinecraftClient(net.minecraft.client.MinecraftClient mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public boolean isFinishedLoading() {
        return mcObject.isFinishedLoading();
    }

    @HostAccess.Export
    public static ModStatus getModStatus() {
        return net.minecraft.client.MinecraftClient.getModStatus();
    }


    @HostAccess.Export
    public Framebuffer getFramebuffer() {
        return mcObject.getFramebuffer();
    }

    @HostAccess.Export
    public String getGameVersion() {
        return mcObject.getGameVersion();
    }

    @HostAccess.Export
    public String getVersionType() {
        return mcObject.getVersionType();
    }

    @HostAccess.Export
    public boolean forcesUnicodeFont() {
        return mcObject.forcesUnicodeFont();
    }


    @HostAccess.Export
    public LevelStorage getLevelStorage() {
        return mcObject.getLevelStorage();
    }


    @HostAccess.Export
    public int getCurrentFps() {
        return mcObject.getCurrentFps();
    }

    @HostAccess.Export
    public long getRenderTime() {
        return mcObject.getRenderTime();
    }


    @HostAccess.Export
    public boolean isRunning() {
        return mcObject.isRunning();
    }


    @HostAccess.Export
    public MusicTracker getMusicTracker() {
        return mcObject.getMusicTracker();
    }


    @HostAccess.Export
    public TelemetryManager getTelemetryManager() {
        return mcObject.getTelemetryManager();
    }

    @HostAccess.Export
    public double getGpuUtilizationPercentage() {
        return mcObject.getGpuUtilizationPercentage();
    }

    @HostAccess.Export
    public boolean isOptionalTelemetryEnabled() {
        return mcObject.isOptionalTelemetryEnabled();
    }

    @HostAccess.Export
    public boolean isOptionalTelemetryEnabledByApi() {
        return mcObject.isOptionalTelemetryEnabledByApi();
    }

    @HostAccess.Export
    public boolean isTelemetryEnabledByApi() {
        return mcObject.isTelemetryEnabledByApi();
    }

    @HostAccess.Export
    public boolean isMultiplayerEnabled() {
        return mcObject.isMultiplayerEnabled();
    }

    @HostAccess.Export
    public boolean isRealmsEnabled() {
        return mcObject.isRealmsEnabled();
    }

    @Nullable
    @HostAccess.Export
    public BanDetails getMultiplayerBanDetails() {
        return mcObject.getMultiplayerBanDetails();
    }

    @HostAccess.Export
    public boolean isUsernameBanned() {
        return mcObject.isUsernameBanned();
    }

    @HostAccess.Export
    public boolean shouldBlockMessages(UUID sender) {
        return mcObject.shouldBlockMessages(sender);
    }

    @HostAccess.Export
    public net.minecraft.client.MinecraftClient.ChatRestriction getChatRestriction() {
        return mcObject.getChatRestriction();
    }

    @HostAccess.Export
    public boolean isDemo() {
        return mcObject.isDemo();
    }

    @Nullable
    @HostAccess.Export
    public ClientPlayNetworkHandler getNetworkHandler() {
        return mcObject.getNetworkHandler();
    }


    @Nullable
    @HostAccess.Export
    public ServerInfo getCurrentServerEntry() {
        return mcObject.getCurrentServerEntry();
    }

    @HostAccess.Export
    public boolean isInSingleplayer() {
        return mcObject.isInSingleplayer();
    }

    @HostAccess.Export
    public boolean isIntegratedServerRunning() {
        return mcObject.isIntegratedServerRunning();
    }

    @Nullable
    @HostAccess.Export
    public IntegratedServer getServer() {
        return mcObject.getServer();
    }

    @HostAccess.Export
    public boolean isConnectedToLocalServer() {
        return mcObject.isConnectedToLocalServer();
    }

    @HostAccess.Export
    public boolean uuidEquals(UUID uuid) {
        return mcObject.uuidEquals(uuid);
    }

    @HostAccess.Export
    public Session getSession() {
        return mcObject.getSession();
    }

    @HostAccess.Export
    public GameProfile getGameProfile() {
        return mcObject.getGameProfile();
    }

    @HostAccess.Export
    public Proxy getNetworkProxy() {
        return mcObject.getNetworkProxy();
    }

    @HostAccess.Export
    public TextureManager getTextureManager() {
        return mcObject.getTextureManager();
    }

    @HostAccess.Export
    public ShaderLoader getShaderLoader() {
        return mcObject.getShaderLoader();
    }

    @HostAccess.Export
    public ResourceManager getResourceManager() {
        return mcObject.getResourceManager();
    }

    @HostAccess.Export
    public ResourcePackManager getResourcePackManager() {
        return mcObject.getResourcePackManager();
    }

    @HostAccess.Export
    public DefaultResourcePack getDefaultResourcePack() {
        return mcObject.getDefaultResourcePack();
    }

    @HostAccess.Export
    public ServerResourcePackLoader getServerResourcePackProvider() {
        return mcObject.getServerResourcePackProvider();
    }

    @HostAccess.Export
    public Path getResourcePackDir() {
        return mcObject.getResourcePackDir();
    }

    @HostAccess.Export
    public LanguageManager getLanguageManager() {
        return mcObject.getLanguageManager();
    }

    @HostAccess.Export
    public boolean isPaused() {
        return mcObject.isPaused();
    }

    @HostAccess.Export
    public VideoWarningManager getVideoWarningManager() {
        return mcObject.getVideoWarningManager();
    }

    @HostAccess.Export
    public SoundManager getSoundManager() {
        return mcObject.getSoundManager();
    }

    @HostAccess.Export
    public MusicInstance getMusicInstance() {
        return mcObject.getMusicInstance();
    }

    @HostAccess.Export
    public PlayerSkinProvider getSkinProvider() {
        return mcObject.getSkinProvider();
    }

    @Nullable
    @HostAccess.Export
    public Entity getCameraEntity() {
        return new Entity(mcObject.getCameraEntity());
    }

    @HostAccess.Export
    public boolean hasOutline(Entity entity) {
        return mcObject.hasOutline(entity.mcEntity);
    }

    @HostAccess.Export
    public BlockRenderManager getBlockRenderManager() {
        return mcObject.getBlockRenderManager();
    }

    @HostAccess.Export
    public EntityRenderDispatcher getEntityRenderDispatcher() {
        return mcObject.getEntityRenderDispatcher();
    }

    @HostAccess.Export
    public BlockEntityRenderDispatcher getBlockEntityRenderDispatcher() {
        return mcObject.getBlockEntityRenderDispatcher();
    }

    @HostAccess.Export
    public ItemRenderer getItemRenderer() {
        return mcObject.getItemRenderer();
    }

    @HostAccess.Export
    public MapRenderer getMapRenderer() {
        return mcObject.getMapRenderer();
    }

    @HostAccess.Export
    public DataFixer getDataFixer() {
        return mcObject.getDataFixer();
    }

    @HostAccess.Export
    public RenderTickCounter getRenderTickCounter() {
        return mcObject.getRenderTickCounter();
    }

    @HostAccess.Export
    public BlockColors getBlockColors() {
        return mcObject.getBlockColors();
    }

    @HostAccess.Export
    public boolean hasReducedDebugInfo() {
        return mcObject.hasReducedDebugInfo();
    }

    @HostAccess.Export
    public ToastManager getToastManager() {
        return mcObject.getToastManager();
    }

    @HostAccess.Export
    public TutorialManager getTutorialManager() {
        return mcObject.getTutorialManager();
    }

    @HostAccess.Export
    public boolean isWindowFocused() {
        return mcObject.isWindowFocused();
    }

    @HostAccess.Export
    public HotbarStorage getCreativeHotbarStorage() {
        return mcObject.getCreativeHotbarStorage();
    }

    @HostAccess.Export
    public BakedModelManager getBakedModelManager() {
        return mcObject.getBakedModelManager();
    }

    @HostAccess.Export
    public PaintingManager getPaintingManager() {
        return mcObject.getPaintingManager();
    }

    @HostAccess.Export
    public MapTextureManager getMapTextureManager() {
        return mcObject.getMapTextureManager();
    }

    @HostAccess.Export
    public MapDecorationsAtlasManager getMapDecorationsAtlasManager() {
        return mcObject.getMapDecorationsAtlasManager();
    }

    @HostAccess.Export
    public GuiAtlasManager getGuiAtlasManager() {
        return mcObject.getGuiAtlasManager();
    }

    @HostAccess.Export
    public WaypointStyleAssetManager getWaypointStyleAssetManager() {
        return mcObject.getWaypointStyleAssetManager();
    }


    //todo look at moving to lib
    @HostAccess.Export
    public Text takePanorama(File directory) {
        return mcObject.takePanorama(directory);
    }

    @Nullable
    @HostAccess.Export
    public WorldGenerationProgressTracker getWorldGenerationProgressTracker() {
        return mcObject.getWorldGenerationProgressTracker();
    }

    @HostAccess.Export
    public SplashTextResourceSupplier getSplashTextLoader() {
        return mcObject.getSplashTextLoader();
    }

    @Nullable
    @HostAccess.Export
    public Overlay getOverlay() {
        return mcObject.getOverlay();
    }

    @HostAccess.Export
    public SocialInteractionsManager getSocialInteractionsManager() {
        return mcObject.getSocialInteractionsManager();
    }

    @HostAccess.Export
    public Window getWindow() {
        return mcObject.getWindow();
    }

    @HostAccess.Export
    public InactivityFpsLimiter getInactivityFpsLimiter() {
        return mcObject.getInactivityFpsLimiter();
    }

    @HostAccess.Export
    public DebugHud getDebugHud() {
        return mcObject.getDebugHud();
    }

    @HostAccess.Export
    public BufferBuilderStorage getBufferBuilders() {
        return mcObject.getBufferBuilders();
    }


    @HostAccess.Export
    public LoadedEntityModels getLoadedEntityModels() {
        return mcObject.getLoadedEntityModels();
    }

    @HostAccess.Export
    public boolean shouldFilterText() {
        return mcObject.shouldFilterText();
    }

    @Nullable
    @HostAccess.Export
    public SignatureVerifier getServicesSignatureVerifier() {
        return mcObject.getServicesSignatureVerifier();
    }

    @HostAccess.Export
    public boolean providesProfileKeys() {
        return mcObject.providesProfileKeys();
    }

    @HostAccess.Export
    public GuiNavigationType getNavigationType() {
        return mcObject.getNavigationType();
    }


    @HostAccess.Export
    public NarratorManager getNarratorManager() {
        return mcObject.getNarratorManager();
    }

    @HostAccess.Export
    public MessageHandler getMessageHandler() {
        return mcObject.getMessageHandler();
    }

    @HostAccess.Export
    public AbuseReportContext getAbuseReportContext() {
        return mcObject.getAbuseReportContext();
    }

    @HostAccess.Export
    public RealmsPeriodicCheckers getRealmsPeriodicCheckers() {
        return mcObject.getRealmsPeriodicCheckers();
    }

    @HostAccess.Export
    public QuickPlayLogger getQuickPlayLogger() {
        return mcObject.getQuickPlayLogger();
    }

    @HostAccess.Export
    public CommandHistoryManager getCommandHistoryManager() {
        return mcObject.getCommandHistoryManager();
    }

    @HostAccess.Export
    public SymlinkFinder getSymlinkFinder() {
        return mcObject.getSymlinkFinder();
    }

    @HostAccess.Export
    public ItemModelManager getItemModelManager() {
        return mcObject.getItemModelManager();
    }
}