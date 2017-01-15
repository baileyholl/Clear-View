package com.baileyhollingsworth.clearview;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.ref.WeakReference;
import java.util.Collection;

import static net.minecraft.entity.EntityLivingBase.POTION_EFFECTS;

/**
 * Created by Bailey on 1/14/2017.
 */
public class Proxy {

    //Taken from CleanView
    WeakReference<EntityLivingBase> ref;
    private static final String TAG = "0256d9da-9c1b-46ea-a83c-01ae6981a2c7";
    private static final Proxy INSTANCE = new Proxy();

    public static void setup() {
        if (INSTANCE == null)
            throw new RuntimeException();
    }

    private Proxy()
    {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void removePotionParticles(TickEvent.ClientTickEvent event){
        if (event.phase == TickEvent.Phase.START)
        {
            EntityLivingBase ent = (EntityLivingBase) FMLClientHandler.instance().getClient().getRenderViewEntity();

            EntityLivingBase prevEnt;
            prevEnt = ref != null ? ref.get() : null;

            if (prevEnt != ent)
            {
                if (prevEnt != null && prevEnt.getEntityData().getBoolean(TAG))
                {
                    @SuppressWarnings("rawtypes")
                    Collection effects = prevEnt.getActivePotionEffects();
                    if (!effects.isEmpty()) {
                        prevEnt.getDataManager().set(POTION_EFFECTS, PotionUtils.getPotionColorFromEffectList(effects));
                    }
                    prevEnt.getEntityData().removeTag(TAG);
                }
                ref = (ent != null) ? new WeakReference<EntityLivingBase>(ent) : null;
            }

            if (ent != null)
            {
                ent.getDataManager().set(POTION_EFFECTS, 0);
                if (!ent.getEntityData().getBoolean(TAG))
                    ent.getEntityData().setBoolean(TAG, true);
            }
        }
    }
}
