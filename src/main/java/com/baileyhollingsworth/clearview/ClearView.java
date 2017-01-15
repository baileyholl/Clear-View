package com.baileyhollingsworth.clearview;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Bailey on 1/14/2017.
 */
@Mod(modid = "clear_view", useMetadata = true, name = "Clear View")
public class ClearView
{

    @Mod.EventHandler
    public void handleEvent(FMLInitializationEvent event)
    {
        if (event.getSide().isClient())
            Proxy.setup();
        else
            System.err.println("This mod is client-only, please remove it from your server");
    }
}