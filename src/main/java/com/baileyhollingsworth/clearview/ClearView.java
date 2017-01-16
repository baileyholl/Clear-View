package com.baileyhollingsworth.clearview;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Bailey on 1/14/2017.
 */
@Mod(modid = "clear_view", useMetadata = true, name = "Clear View", clientSideOnly = true)
public class ClearView
{

    @Mod.EventHandler
    public void handleEvent(FMLInitializationEvent event)
    {
        Proxy.setup();
    }
}