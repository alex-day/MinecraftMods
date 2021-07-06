package net.minecraft.src;

import java.util.Random;

public class BlockMilkyQuartz extends Block {
	
	protected BlockMilkyQuartz(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0f);
		this.setResistance(12.0f);
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int parFortuneModifier)
    {
        if (parFortuneModifier > 3)
        {
            parFortuneModifier = 3;
        }

        return par2Random.nextInt(10 - parFortuneModifier * 3) == 0 ? mod_Grant.itemQuartzMilk.itemID : this.blockID;
    }
}
