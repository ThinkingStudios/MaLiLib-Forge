package fi.dy.masa.malilib.compat.lwgl;

import com.mojang.blaze3d.systems.GpuDevice;

import fi.dy.masa.malilib.MaLiLib;

public class GpuCompat
{
    private static String GPUVendor = "";
    private static String GPURenderer = "";
    private static String GPUVersion = "";

    public static void init(GpuDevice device)
    {
        GPUVendor = device.getVendor();
        GPURenderer = device.getRenderer();
        GPUVersion = device.getVersion();

        MaLiLib.debugLog("Detected GPU: [{} / {}]", GPURenderer, GPUVersion);
    }

    public static String getVendor()
    {
        return GPUVendor;
    }

    public static String getRenderer()
    {
        return GPURenderer;
    }

    public static String getVersion()
    {
        return GPUVersion;
    }

    /**
     * Designed for troubleshooting various Rendering issues by detecting the Card Vendors (nVidia)
     * @return (True|False)
     */
    public static boolean isNvidiaGpu()
    {
        return GPURenderer.toLowerCase().contains("nvidia");
    }

    /**
     * Designed for troubleshooting various Rendering issues by detecting the Card Vendors (Amd / Ati)
     * @return (True|False)
     */
    public static boolean isAmdGpu()
    {
        return GPURenderer.toLowerCase().contains("amd") || GpuCompat.getVendor().toLowerCase().contains("ati");
    }

    /**
     * Designed for troubleshooting various Rendering issues by detecting the Card Vendors (Intel)
     * @return (True|False)
     */
    public static boolean isIntelGpu()
    {
        return GPURenderer.toLowerCase().contains("intel");
    }
}
