package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.Gemulation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block {

    public BaseBlock(String unlocalizedName, Material material, float hardness, float resistance)
    {
        super(material);

        this.setCreativeTab(Gemulation.tabGemulation);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);

        handleRegName();
    }

    protected void handleRegName()
    {

    }

    public BaseBlock(String unlocalizedName, float hardness, float resistance)
    {
        this(unlocalizedName,Material.ROCK,hardness,resistance);
    }

    public BaseBlock(String unlocalizedName)
    {
        this(unlocalizedName,2.0f,10.0f);
    }
}
