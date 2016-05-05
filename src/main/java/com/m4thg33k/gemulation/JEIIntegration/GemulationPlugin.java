package com.m4thg33k.gemulation.JEIIntegration;

import com.m4thg33k.gemulation.items.ItemGemBag;
import com.m4thg33k.gemulation.items.ModItems;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

import javax.annotation.Nonnull;

@JEIPlugin
public class GemulationPlugin extends BlankModPlugin{

    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        jeiHelpers.getNbtIgnoreList().ignoreNbtTagNames(ModItems.itemGemBag,ItemGemBag.TAG_ITEMS,ItemGemBag.TAG_SLOT);
    }
}
