package fi.dy.masa.malilib.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.MinecraftServer;
import fi.dy.masa.malilib.event.ServerHandler;
import fi.dy.masa.malilib.util.time.TickUtils;

/**
 * For invoking IntegratedServer() calls
 */
@Mixin(value = MinecraftServer.class)
public abstract class MixinMinecraftServer
{
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "runServer")
    private void malilib_onServerStarting(CallbackInfo ci)
    {
        ((ServerHandler) ServerHandler.getInstance()).onServerStarting((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createMetadata()Lnet/minecraft/server/ServerMetadata;", ordinal = 0), method = "runServer")
    private void malilib_onServerStarted(CallbackInfo ci)
    {
        ((ServerHandler) ServerHandler.getInstance()).onServerStarted((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void malilib_onServerStopping(CallbackInfo info)
    {
        ((ServerHandler) ServerHandler.getInstance()).onServerStopping((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "shutdown")
    private void malilib_onServerStopped(CallbackInfo info)
    {
        ((ServerHandler) ServerHandler.getInstance()).onServerStopped((MinecraftServer) (Object) this);
    }

    @Inject(method = "pushTickLog", at = @At("HEAD"))
    private void malilib_onServerTick(long tickStartTime, CallbackInfo ci)
    {
        TickUtils.getInstance().updateNanoTickFromIntegratedServer((MinecraftServer) (Object) this);
    }
}
