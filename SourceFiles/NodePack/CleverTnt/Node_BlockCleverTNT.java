package net.minecraft.src.NodePack.CleverTnt;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_Node_PackLoader;
import net.minecraft.src.NodePack.Shared.Blocks.Node_BlockContainerBase;

/**
 * Copied concepts from: BlockTNT
 * 
 * @author Alex
 *
 */
public class Node_BlockCleverTNT extends Node_BlockContainerBase {
	
	public Icon blockIconTop;
	public Icon blockIconBottom;
	
	public Node_BlockCleverTNT(int b_id) {
		super(b_id,Material.tnt); //8 = tnt texture index
		
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new Node_TileEntityCleverTNT();
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int meta)
    {
        if(i == 0)
        {
            return blockIconBottom;
        }
        if(i == 1)
        {
            return blockIconTop;
        }
        return blockIcon;
    }
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		if(entityplayer.getCurrentEquippedItem() == null)
        {
			//if this block has been right clicked with another of this block then change the value of its pattern
			Node_TileEntityCleverTNT te = (Node_TileEntityCleverTNT)world.getBlockTileEntity(i, j, k);
	        te.changePattern();
        } else {
        	//if this block has been right clicked with anything else then change the value of it's distance
        	int metaData = world.getBlockMetadata(i, j, k);
        	metaData++;
        	world.setBlockMetadataWithNotify(i, j, k, metaData, 0);
        	metaData = world.getBlockMetadata(i, j, k);
        	int stepSize = mod_Node_PackLoader.getIntOption("CleverTNTLength");
        	if(world.isRemote) {
        		mod_Node_PackLoader.printToChat("Path Length: "+(stepSize * (metaData+1)));
        	}
        }
		return false;
        
    }
	
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
        {
        	//if this block has been right clicked with flint and steel then activate it
			Node_TileEntityCleverTNT te = (Node_TileEntityCleverTNT)world.getBlockTileEntity(i, j, k);
			int direction = getTunnelDirectionForBlock(world, i, j, k, entityplayer);
			te.activate(direction,world.getBlockMetadata(i, j, k));
        }
        super.onBlockClicked(world, i, j, k, entityplayer);
    }
    
    private static int getTunnelDirectionForBlock(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(MathHelper.abs((float)entityplayer.posX - (float)i) < 2.0F && MathHelper.abs((float)entityplayer.posZ - (float)k) < 2.0F)
        {
            double d = (entityplayer.posY + 1.8200000000000001D) - (double)entityplayer.yOffset;
            if(d - (double)j > 2D)
            {
                return 1;
            }
            if((double)j - d > 0.0D)
            {
                return 0;
            }
        }
        int l = MathHelper.floor_double((double)((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            return 2;
        }
        if(l == 1)
        {
            return 5;
        }
        if(l == 2)
        {
            return 3;
        }
        return l != 3 ? 0 : 4;
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	super.registerIcons(par1IconRegister);
        this.blockIconTop = par1IconRegister.registerIcon("tnt_top");
        this.blockIconBottom = par1IconRegister.registerIcon("tnt_bottom");
    }
}
