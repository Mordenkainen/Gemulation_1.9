package com.m4thg33k.gemulation.items;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemGemBag itemGemBag = new ItemGemBag();

    public static void createItems()
    {
        GameRegistry.register(itemGemBag);
    }
}
