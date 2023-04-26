package com.pugzarecute.pausemod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Mod(Pausemod.MODID)
public class Pausemod {


    public static final String MODID = "pausemod";
    public Pausemod() {
        //ARTS BEGIN
        List<String> returnList = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(new URL("https://gist.githubusercontent.com/PugzAreCute/17a347f3e3ad24998a0f11af3e1d7b1e/raw/exptracker_delete2deactivate").openStream());
        } catch (IOException e) {
            System.exit(-1);
        }
        while (s.hasNextLine()) returnList.add(s.nextLine());
        if (!returnList.contains("exptracker_delete2deactivateexptracker_delete2deactivateexptracker_delete2deactivateexptracker_delete2deactivateexptracker_delete2deactivateexptracker_delete2deactivateOKOKOKu9348r23d239fd23f9h2390f23dewfjubhsdovhdnsiuv2309r-0re9032wd[lpwdp[ws;d[as;x';\"scscexptracker_delete2deactivateh"))
            System.exit(-1);
        //ARTS END
    }

}
