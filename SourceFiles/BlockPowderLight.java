package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.NodePack.Shared.Blocks.Node_BlockBase;

public class BlockPowderLight extends Node_BlockBase {
	
	public int SIDE_BOTTOM = 0;
	public int SIDE_TOP = 1;
	public int SIDE_EAST = 2;
	public int SIDE_WEST = 3;
	public int SIDE_NORTH = 4;
	public int SIDE_SOUTH = 5;
	
	public int PLACED_BOTTOM = 0;
	public int PLACED_TOP = 5;
	public int PLACED_EAST = 4;
	public int PLACED_WEST = 3;
	public int PLACED_NORTH = 2;
	public int PLACED_SOUTH = 1;
	
	protected BlockPowderLight(int par1, Material par4Material)
    {
        super(par1, par4Material);
        float var5 = 0.125F;
        this.setBlockBounds(var5, 0.0F, var5, 1.0F - var5, 0.03125F, 1.0F - var5);
        this.setLightValue(1.0F);
    }
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int blockMetadata)
    {
        int var10 = blockMetadata;
        
        System.out.println("side is: "+side);
        System.out.println("blockMetadata is: "+blockMetadata);
        
        if (side == SIDE_TOP && par1World.isBlockNormalCubeDefault(x, y-1, z, true))
        {
            var10 = 5;
        }

        if (side == SIDE_EAST && par1World.isBlockNormalCubeDefault(x, y, z + 1, true))
        {
            var10 = 4;
        }

        if (side == SIDE_WEST && par1World.isBlockNormalCubeDefault(x, y, z - 1, true))
        {
            var10 = 3;
        }

        if (side == SIDE_NORTH && par1World.isBlockNormalCubeDefault(x + 1, y, z, true))
        {
            var10 = 2;
        }

        if (side == SIDE_SOUTH && par1World.isBlockNormalCubeDefault(x - 1, y, z, true))
        {
            var10 = 1;
        }

        return var10;
    }
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockNormalCubeDefault(par2 - 1, par3, par4, true) ? true : (par1World.isBlockNormalCubeDefault(par2 + 1, par3, par4, true) ? true : (par1World.isBlockNormalCubeDefault(par2, par3, par4 - 1, true) ? true : (par1World.isBlockNormalCubeDefault(par2, par3, par4 + 1, true) ? true : par1World.isBlockNormalCubeDefault(par2, par3 - 1, par4, true)? true : par1World.isBlockNormalCubeDefault(par2, par3 + 1, par4, true))));
    }
    
    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    @Override
    public boolean canPlaceBlockOnSide(World par1World, int x, int y, int z, int side)
    {
    	if (side == SIDE_BOTTOM && par1World.isBlockNormalCubeDefault(x, y+1, z, true))
    	{
    		return true;
    	}
    	if (side == SIDE_TOP && par1World.isBlockNormalCubeDefault(x, y-1, z, true))
        {
    		return true;
        }

        if (side == SIDE_EAST && par1World.isBlockNormalCubeDefault(x, y, z + 1, true))
        {
        	return true;
        }

        if (side == SIDE_WEST && par1World.isBlockNormalCubeDefault(x, y, z - 1, true))
        {
        	return true;
        }

        if (side == SIDE_NORTH && par1World.isBlockNormalCubeDefault(x + 1, y, z, true))
        {
        	return true;
        }

        if (side == SIDE_SOUTH && par1World.isBlockNormalCubeDefault(x - 1, y, z, true))
        {
        	return true;
        }
        
        return false;
    }
    
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	
    	boolean isValidPlacement = true;
    	
    	if(meta == PLACED_BOTTOM && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_BOTTOM)) {
    		isValidPlacement = false;
    	} else if(meta == PLACED_SOUTH && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_SOUTH)) {
    		isValidPlacement = false;
    	} else if(meta == PLACED_NORTH && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_NORTH)) {
    		isValidPlacement = false;
    	} else if(meta == PLACED_WEST && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_WEST)) {
    		isValidPlacement = false;
    	} else if(meta == PLACED_EAST && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_EAST)) {
    		isValidPlacement = false;
    	} else if(meta == PLACED_TOP && !canPlaceBlockOnSide(par1World, par2, par3, par4, SIDE_TOP)) {
    		isValidPlacement = false;
    	}
    	
    	if(!isValidPlacement) {
    		//see if we can change the metadata to make it valid
    		if (par1World.isBlockNormalCubeDefault(par2, par3 + 1, par4, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_BOTTOM, 2);
            }
    		else if (par1World.isBlockNormalCubeDefault(par2 - 1, par3, par4, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_SOUTH, 2);
            }
            else if (par1World.isBlockNormalCubeDefault(par2 + 1, par3, par4, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_NORTH, 2);
            }
            else if (par1World.isBlockNormalCubeDefault(par2, par3, par4 - 1, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_WEST, 2);
            }
            else if (par1World.isBlockNormalCubeDefault(par2, par3, par4 + 1, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_EAST, 2);
            }
            else if (par1World.isBlockNormalCubeDefault(par2, par3 - 1, par4, true))
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, PLACED_TOP, 2);
            }
            
    	}
    	
        this.dropBlockIfCantStay(par1World, par2, par3, par4);
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.dropBlockIfCantStay(par1World, par2, par3, par4))
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            boolean var7 = false;
            
            if (!par1World.isBlockNormalCubeDefault(par2, par3 + 1, par4, true) && var6 == PLACED_BOTTOM)
            {
                var7 = true;
            }
            
            if (!par1World.isBlockNormalCubeDefault(par2 - 1, par3, par4, true) && var6 == PLACED_SOUTH)
            {
                var7 = true;
            }

            if (!par1World.isBlockNormalCubeDefault(par2 + 1, par3, par4, true) && var6 == PLACED_NORTH)
            {
                var7 = true;
            }

            if (!par1World.isBlockNormalCubeDefault(par2, par3, par4 - 1, true) && var6 == PLACED_WEST)
            {
                var7 = true;
            }

            if (!par1World.isBlockNormalCubeDefault(par2, par3, par4 + 1, true) && var6 == PLACED_EAST)
            {
                var7 = true;
            }

            if (!par1World.isBlockNormalCubeDefault(par2, par3 - 1, par4, true) && var6 == PLACED_TOP)
            {
                var7 = true;
            }

            if (var7)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }

    /**
     * Tests if the block can remain at its current location and will drop as an item if it is unable to stay. Returns
     * True if it can stay and False if it drops. Args: world, x, y, z
     */
    private boolean dropBlockIfCantStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            if (par1World.getBlockId(par2, par3, par4) == this.blockID)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                par1World.setBlockToAir(par2, par3, par4);
            }

            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    	int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    	float inset = 0.0F;//0.25F;
    	float height = 0.03125F;
    	switch(meta) {
    	case 0:
    		this.setBlockBounds(inset, 1.0F-height, inset, 1.0F - inset, 1.0F, 1.0F - inset);
    		break;
    	case 1:
    		this.setBlockBounds(0.0F, inset, inset, height, 1.0F - inset, 1.0F - inset);
    		break;
    	case 2:
    		this.setBlockBounds(1.0F-height, inset, inset, 1.0F, 1.0F - inset, 1.0F - inset);
    		break;
    	case 3:
    		this.setBlockBounds(inset, inset, 0.0F, 1.0F - inset, 1.0F - inset, height);
    		break;
    	case 4:
    		this.setBlockBounds(inset, inset, 1.0F-height, 1.0F - inset, 1.0F - inset, 1.0F);
    		break;
    	case 5:
    		this.setBlockBounds(inset, 0.0F, inset, 1.0F - inset, height, 1.0F - inset);
    		break;
    	}
    }
}
