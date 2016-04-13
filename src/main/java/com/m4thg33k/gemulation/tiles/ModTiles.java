package com.m4thg33k.gemulation.tiles;

import com.m4thg33k.gemulation.lib.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {

    public static void init()
    {
        String prefix = "tile.gemulation:";

        GameRegistry.registerTileEntity(TileGemChanger.class,prefix + Names.GEM_CHANGER);
    }
}
