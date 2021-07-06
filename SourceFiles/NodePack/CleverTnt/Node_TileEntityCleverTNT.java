package net.minecraft.src.NodePack.CleverTnt;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.src.Block;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.mod_Node_CleverTNT;
import net.minecraft.src.mod_Node_PackLoader;
import net.minecraft.src.NodePack.Shared.Node_Pattern;
import net.minecraft.src.NodePack.Shared.Node_PatternCharTracker;
import net.minecraft.src.NodePack.Shared.Node_PatternTracker;

public class Node_TileEntityCleverTNT extends TileEntity {
	private static int maxSpacesToMove = 32767;
	private int patternToUse = 0;
	private boolean isActive;
	private int direction;
	
	private int spacesMoved;
	private int metadataAtTimeOfActivate;
	
	Node_PatternTracker patternTracker;
	Map<Character,Node_PatternCharTracker> charTrackers;
	
	public Node_TileEntityCleverTNT() {
		patternToUse = 0;
		spacesMoved = 0;
	}
	
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        patternToUse = nbttagcompound.getShort("pattern");
        isActive = nbttagcompound.getBoolean("isActive");
        direction = nbttagcompound.getShort("direction");
        spacesMoved = nbttagcompound.getShort("spacesMoved");
        metadataAtTimeOfActivate = nbttagcompound.getShort("metadata");
        
        Node_Pattern pattern = getPatternToUse();
        patternTracker = pattern.initialisePatternTracker(spacesMoved);
        charTrackers = pattern.initialiseCharPatternTrackers(spacesMoved);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("pattern", (short)patternToUse);
        nbttagcompound.setBoolean("isActive", isActive);
        nbttagcompound.setShort("direction", (short)direction);
        nbttagcompound.setShort("spacesMoved", (short)spacesMoved);
        nbttagcompound.setShort("metadata", (short)metadataAtTimeOfActivate);
    }
	
	public void changePattern() {
		patternToUse++;
		if(patternToUse >= mod_Node_PackLoader.patterns.size()) {
			patternToUse = 0;
		}
		if(this.worldObj.isRemote) {
    		mod_Node_PackLoader.printToChat("Pattern changed to: "+getPatternName());
		}
	}
	
	public Node_Pattern getPatternToUse() {
		String[] keys = (String[]) (mod_Node_PackLoader.patterns.keySet()).toArray(new String[0]);
		if(keys.length > 0) {
			return mod_Node_PackLoader.getPattern(keys[patternToUse]);
		} else {
			return null;
		}
	}
	
	public String getPatternName() {
		String[] keys = (String[]) (mod_Node_PackLoader.patterns.keySet()).toArray(new String[0]);
		return keys[patternToUse];
	}
	
	
	public void removeBlocks() {
		Node_Pattern pattern = getPatternToUse();
		
		if(direction == 0 || direction == 1) {
			for(int xOff = pattern.getMinUOffset(); xOff < pattern.getMaxUOffset(); xOff++) {
				for(int zOff = pattern.getMinVOffset(); zOff < pattern.getMaxVOffset(); zOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, xOff,zOff)) {
						if(xOff != 0 || zOff != 0) {
							int bId = worldObj.getBlockId(xCoord-xOff, yCoord, zCoord+zOff);
							if(pattern.isBlockBreakable(xOff, zOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord-xOff, yCoord, zCoord+zOff) != -1) {
									if(bId != 0) {
										Block.blocksList[bId].dropBlockAsItemWithChance(worldObj, xCoord-xOff, yCoord, zCoord+zOff, 0, (float)pattern.dropAtOffsetPosition(charTrackers, xOff, zOff),0);
									}
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, xOff, zOff);
									if(!mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, xOff, zOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord-xOff, yCoord, zCoord+zOff);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, xOff, zOff);
											worldObj.setBlock(xCoord-xOff, yCoord, zCoord+zOff, rID, meta, 2);
										}
									}
								}
							}
						}
					}
				}
			}
		} else if(direction == 2 || direction == 3) {
			for(int yOff = pattern.getMinUOffset(); yOff < pattern.getMaxUOffset(); yOff++) {
				for(int xOff = pattern.getMinVOffset(); xOff < pattern.getMaxVOffset(); xOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, yOff, xOff)) {
						if(xOff != 0 || yOff != 0) {
							int bId = worldObj.getBlockId(xCoord-xOff, yCoord-yOff, zCoord);
							if(pattern.isBlockBreakable(yOff, xOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord-xOff, yCoord-yOff, zCoord) != -1) {
									if(bId != 0) {
										Block.blocksList[bId].dropBlockAsItemWithChance(worldObj, xCoord-xOff, yCoord-yOff, zCoord, 0, (float)pattern.dropAtOffsetPosition(charTrackers, yOff, xOff),0);
									}
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, yOff, xOff);
									if(!mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, yOff, xOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord-xOff, yCoord-yOff, zCoord);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, yOff, xOff);
											worldObj.setBlock(xCoord-xOff, yCoord-yOff, zCoord, rID, meta, 2);
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			for(int yOff = pattern.getMinUOffset(); yOff < pattern.getMaxUOffset(); yOff++) {
				for(int zOff = pattern.getMinVOffset(); zOff < pattern.getMaxVOffset(); zOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, yOff,zOff)) {
						if(zOff != 0 || yOff != 0) {
							int bId = worldObj.getBlockId(xCoord, yCoord-yOff, zCoord+zOff);
							if(pattern.isBlockBreakable(yOff, zOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord, yCoord-yOff, zCoord+zOff) != -1) {
									if(bId != 0) {
										Block.blocksList[bId].dropBlockAsItemWithChance(worldObj, xCoord, yCoord-yOff, zCoord+zOff, 0, (float)pattern.dropAtOffsetPosition(charTrackers, yOff, zOff), 0);
									}
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, yOff, zOff);
									if(!mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, yOff, zOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord, yCoord-yOff, zCoord+zOff);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, yOff, zOff);
											worldObj.setBlock(xCoord, yCoord-yOff, zCoord+zOff, rID, meta, 0);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * a second pass for placing blocks.
	 * Will place all blocks that are known to have placement dependencies
	 * example blocks:
	 *    torch, redstone torch, mushroom, track, powered rail,
	 *    switch, lever, redstone, repeater
	 */
	public void placeDetailBlocks() {
		Node_Pattern pattern = getPatternToUse();
		
		if(direction == 0 || direction == 1) {
			for(int xOff = pattern.getMinUOffset(); xOff < pattern.getMaxUOffset(); xOff++) {
				for(int zOff = pattern.getMinVOffset(); zOff < pattern.getMaxVOffset(); zOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, xOff,zOff)) {
						if(xOff != 0 || zOff != 0) {
							int bId = worldObj.getBlockId(xCoord-xOff, yCoord, zCoord+zOff);
							if(pattern.isBlockBreakable(xOff, zOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord-xOff, yCoord, zCoord+zOff) != -1) {
									
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, xOff, zOff);
									if(mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, xOff, zOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord-xOff, yCoord, zCoord+zOff);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, xOff, zOff);
											worldObj.setBlock(xCoord-xOff, yCoord, zCoord+zOff, rID, meta, 2);
										}
									}
								}
							}
						}
					}
				}
			}
		} else if(direction == 2 || direction == 3) {
			for(int yOff = pattern.getMinUOffset(); yOff < pattern.getMaxUOffset(); yOff++) {
				for(int xOff = pattern.getMinVOffset(); xOff < pattern.getMaxVOffset(); xOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, yOff, xOff)) {
						if(xOff != 0 || yOff != 0) {
							int bId = worldObj.getBlockId(xCoord-xOff, yCoord-yOff, zCoord);
							if(pattern.isBlockBreakable(yOff, xOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord-xOff, yCoord-yOff, zCoord) != -1) {
									
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, yOff, xOff);
									if(mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, yOff, xOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord-xOff, yCoord-yOff, zCoord);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, yOff, xOff);
											worldObj.setBlock(xCoord-xOff, yCoord-yOff, zCoord, rID, meta, 2);
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			for(int yOff = pattern.getMinUOffset(); yOff < pattern.getMaxUOffset(); yOff++) {
				for(int zOff = pattern.getMinVOffset(); zOff < pattern.getMaxVOffset(); zOff++) {
					if(pattern.removeAtOffsetPosition(charTrackers, yOff,zOff)) {
						if(zOff != 0 || yOff != 0) {
							int bId = worldObj.getBlockId(xCoord, yCoord-yOff, zCoord+zOff);
							if(pattern.isBlockBreakable(yOff, zOff, bId)) {
								if(bId == 0 || Block.blocksList[bId].getBlockHardness(worldObj, xCoord, yCoord-yOff, zCoord+zOff) != -1) {
									
									//get the replacement block
									int rID = pattern.replaceIDAtOffsetPosition(charTrackers, yOff, zOff);
									if(mod_Node_PackLoader.getSequence("DetailBlockIDs").contains(rID)) {
										boolean replace = pattern.replaceAtOffsetPosition(charTrackers, yOff, zOff);
										
										if(rID == 0 || !replace) {
											worldObj.setBlockToAir(xCoord, yCoord-yOff, zCoord+zOff);
										} else {
											//get metadata for block
											int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, yOff, zOff);
											worldObj.setBlock(xCoord, yCoord-yOff, zCoord+zOff, rID, meta, 2);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void updateEntity()
    {
		if (!this.worldObj.isRemote) {
		if(isActive) {
			//based on the direction given, clear all of the blocks that should be cleared using the chosen pattern.
			removeBlocks();
			placeDetailBlocks();
			
			//if this block has reached the number of blocks it should have moved
			int stepSize = mod_Node_PackLoader.getIntOption("CleverTNTLength");
			if(spacesMoved >= maxSpacesToMove || spacesMoved >= (stepSize * (metadataAtTimeOfActivate+1)) || yCoord <= 0 || yCoord >= 128) {
				//remove this block
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			} else {//otherwise
				//move this block one in the direction that it should be moving.
				int newX = xCoord;
				int newY = yCoord;
				int newZ = zCoord;
				if(direction == 0) {
					newY ++;
				} else if(direction == 1) {
					newY --;
				} else if(direction == 2) {
					newZ ++;
				} else if(direction == 3) {
					newZ --;
				} else if(direction == 4) {
					newX ++;
				} else {
					newX --;
				}
				
				Node_Pattern pattern = getPatternToUse();
				int uOff = pattern.getUOffset(patternTracker);
				int vOff = pattern.getVOffset(patternTracker);
				if(direction == 0 || direction == 1) {
					newX += uOff;
					newZ += vOff;
				} else if(direction == 2 || direction == 3) {
					newY += uOff;
					newX += vOff;
				} else {
					newY += uOff;
					newZ += vOff;
				}
				
				
				int rID = pattern.replaceIDAtOffsetPosition(charTrackers, 0, 0);
				boolean replace = pattern.replaceAtOffsetPosition(charTrackers, 0, 0);
				
				if(rID == 0 || !replace) {
					worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				} else {
					//get metadata for block
					int meta = pattern.replaceMetaAtOffsetPosition(charTrackers, 0, 0);
					worldObj.setBlock(xCoord, yCoord, zCoord, rID, meta, 2);
				}
				
				
				worldObj.setBlock(newX, newY, newZ, mod_Node_CleverTNT.cleverTNT.blockID, metadataAtTimeOfActivate, 2);
				Node_TileEntityCleverTNT te = new Node_TileEntityCleverTNT();
				te.patternToUse = patternToUse;
				te.spacesMoved = spacesMoved+1;
				te.activate(direction, metadataAtTimeOfActivate);
				
				//take a step in all the trackers
				patternTracker.takeStep();
				Set<Character> keys = charTrackers.keySet();
				for(Iterator<Character> it = keys.iterator(); it.hasNext();) {
					char key = it.next();
					charTrackers.get(key).takeStep();
				}
				
				worldObj.setBlockTileEntity(newX, newY, newZ, te);
			}
		}
		}
    }
	
	public void activate(int direction, int metadataAtTimeOfActivate) {
		
		Node_Pattern pattern = getPatternToUse();
        
		if(pattern != null) {
			isActive = true;
			this.metadataAtTimeOfActivate = metadataAtTimeOfActivate;
			this.direction = direction;
			
			patternTracker = pattern.initialisePatternTracker(spacesMoved);
	        charTrackers = pattern.initialiseCharPatternTrackers(spacesMoved);
		} else {
			if(this.worldObj.isRemote) {
        		mod_Node_PackLoader.printToChat("Cannot Activate! You have no patterns defined.");
        	}
		}
	}
}
