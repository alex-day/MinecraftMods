package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 
 * Copied concepts from: BlockFurnace, 
 * 
 * @author Alex
 */
public class BlockWiFi extends BlockContainer {
	
	protected BlockWiFi(int blockID) {
		super(blockID, Material.iron);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setTickRandomly(true);
	}

	public static int[] effects = new int[] {
		Potion.moveSpeed.id,
		Potion.digSpeed.id,
		Potion.resistance.id,
		Potion.jump.id,
		Potion.damageBoost.id,
		Potion.regeneration.id,
		Potion.nightVision.id,
		Potion.fireResistance.id
	};
	
	public static String[] wifiNames =  new String[]{
		"Move Speed",
		"Dig Speed",
		"Resistance",
		"Jump",
		"Damage Boost",
		"Regeneration",
		"Night Vision",
		"Fire Resistance",
		"Ultimate Move Speed",
		"Ultimate Dig Speed",
		"Ultimate Resistance",
		"Ultimate Jump",
		"Ultimate Damage Boost",
		"Ultimate Regeneration",
		"Ultimate Night Vision",
		"Ultimate Fire Resistance"
	};
	
	public Icon blockIconUltimate;
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        if (par2 <= 7)
        {
            return this.blockIcon;
        }
        else
        {
            return this.blockIconUltimate;
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * Takes a dye damage value and returns the block damage value to match
     */
    public static int getBlockFromDye(int par0)
    {
        return ~par0 & 15;
    }

    /**
     * Takes a block damage value and returns the dye damage value to match
     */
    public static int getDyeFromBlock(int par0)
    {
        return ~par0 & 15;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int metaIterator = 0; metaIterator < 16; ++metaIterator)
        {
            par3List.add(new ItemStack(par1, 1, metaIterator));
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTotem();
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		super.registerIcons(par1IconRegister);
        this.blockIconUltimate = par1IconRegister.registerIcon("blockWiFiUlt");
    }
}
