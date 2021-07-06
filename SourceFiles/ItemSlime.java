package net.minecraft.src;

import net.minecraft.src.NodePack.Shared.Blocks.BlockSlime;

public class ItemSlime extends ItemBlock {
	public ItemSlime(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return mod_Alex.blockSlimeCube.getBlockTextureFromSideAndMetadata(2, BlockSlime.getBlockFromItem(par1));
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + ItemDye.dyeColorNames[BlockSlime.getBlockFromItem(par1ItemStack.getItemDamage())];
    }
}
