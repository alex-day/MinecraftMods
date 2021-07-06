package net.minecraft.src;

import java.util.Map;
import java.util.Random;

import javax.swing.plaf.SliderUI;

import net.minecraft.src.NodePack.Shared.Blocks.BlockSlime;
import net.minecraft.src.NodePack.Shared.Blocks.Node_BlockTransparentBase;

public class mod_Alex extends BaseMod {

	public static final Block blockNetherQuartz = new BlockNetherQuartz(170,0).setUnlocalizedName("blockNetherQuartz");
	
	
	public static final Block blockWiFi = new BlockWiFi(173).setUnlocalizedName("blockWiFi");
	
	public static final Block blockSlimeCube = new BlockSlime(174, false).setUnlocalizedName("slimeCube");
	
	public static final Block blockPoswderLight = new BlockPowderLight(175, Material.circuits).setUnlocalizedName("powderLight");
	
	public static final Item itemQuartzRuby = new ItemQuartzGem(5100).setUnlocalizedName("itemQuartzRuby");
	public static final Item itemQuartzRubyPick = new ItemAlexPick(5101, EnumAlexToolMaterial.QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubyPick");
	public static final Item itemQuartzRubyShovel = new ItemAlexSpade(5104, EnumAlexToolMaterial.QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubyShovel");
	public static final Item itemQuartzRubyAxe = new ItemAlexAxe(5106, EnumAlexToolMaterial.QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubyAxe");
	public static final Item itemQuartzRubyHoe = new ItemAlexHoe(5108, EnumAlexToolMaterial.QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubyHoe");
	public static final Item itemQuartzRubySword = new ItemAlexSword(5110, EnumAlexToolMaterial.QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubySword");
	
	public static final Item itemQuartzAmethyst = new ItemQuartzGem(5102).setUnlocalizedName("itemQuartzAmethyst");
	public static final Item itemQuartzAmethystPick = new ItemAlexPick(5103, EnumAlexToolMaterial.QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystPick");
	public static final Item itemQuartzAmethystShovel = new ItemAlexSpade(5105, EnumAlexToolMaterial.QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystShovel");
	public static final Item itemQuartzAmethystAxe = new ItemAlexAxe(5107, EnumAlexToolMaterial.QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystAxe");
	public static final Item itemQuartzAmethystHoe = new ItemAlexHoe(5109, EnumAlexToolMaterial.QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystHoe");
	public static final Item itemQuartzAmethystSword = new ItemAlexSword(5111, EnumAlexToolMaterial.QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystSword");
	
	public static final Item itemEpicPick = new ItemAlexPick(5121, EnumAlexToolMaterial.EPIC).setUnlocalizedName("itemEpicPick");
	public static final Item itemEpicShovel = new ItemAlexSpade(5122, EnumAlexToolMaterial.EPIC).setUnlocalizedName("itemEpicShovel");
	public static final Item itemEpicAxe = new ItemAlexAxe(5123, EnumAlexToolMaterial.EPIC).setUnlocalizedName("itemEpicAxe");
	public static final Item itemEpicHoe = new ItemAlexHoe(5124, EnumAlexToolMaterial.EPIC).setUnlocalizedName("itemEpicHoe");
	public static final Item itemEpicSword = new ItemAlexSword(5125, EnumAlexToolMaterial.EPIC).setUnlocalizedName("itemEpicSword");
	
	public static final Item itemRefinedNetherQuartz = new Item(5112).setUnlocalizedName("itemRefinedNetherQuartz").setCreativeTab(CreativeTabs.tabMaterials);
	public static final int armorIndexNetherQuartz = ModLoader.addArmor("Nether_Quartz");
	public static final Item itemNetherQuartzHelmet = new ItemAlexArmor(5113, EnumAlexArmorMaterial.NETHER_QUARTZ, armorIndexNetherQuartz, 0).setUnlocalizedName("itemNetherQuartzHelmet");
	public static final Item itemNetherQuartzChest = new ItemAlexArmor(5114, EnumAlexArmorMaterial.NETHER_QUARTZ, armorIndexNetherQuartz, 1).setUnlocalizedName("itemNetherQuartzChest");
	public static final Item itemNetherQuartzLeggings = new ItemAlexArmor(5115, EnumAlexArmorMaterial.NETHER_QUARTZ, armorIndexNetherQuartz, 2).setUnlocalizedName("itemNetherQuartzLeggings");
	public static final Item itemNetherQuartzBoots = new ItemAlexArmor(5116, EnumAlexArmorMaterial.NETHER_QUARTZ, armorIndexNetherQuartz, 3).setUnlocalizedName("itemNetherQuartzBoots");
	
	public static final Item itemIronThrowingKnife = new ItemThrowingKnife(5117, ItemThrowingKnife.TYPE_IRON).setUnlocalizedName("itemIronThrowingKnife");
	public static final Item itemDiamondThrowingKnife = new ItemThrowingKnife(5118, ItemThrowingKnife.TYPE_DIAMOND).setUnlocalizedName("itemDiamondThrowingKnife");
	public static final Item itemQuartzRubyThrowingKnife = new ItemThrowingKnife(5119, ItemThrowingKnife.TYPE_QUARTZ_RUBY).setUnlocalizedName("itemQuartzRubyThrowingKnife");
	public static final Item itemQuartzAmethystThrowingKnife = new ItemThrowingKnife(5120, ItemThrowingKnife.TYPE_QUARTZ_AMETHYST).setUnlocalizedName("itemQuartzAmethystThrowingKnife");
	public static int ironThrowingKnifeEID = 232;
	public static int diamondThrowingKnifeEID = 233;
	public static int quartzRubyThrowingKnifeEID = 234;
	public static int quartzAmethystThrowingKnifeEID = 235;
	
	public static final Item itemLightPowder = new ItemLightPowder(5126).setUnlocalizedName("itemLightPowder");
	public static int lightPowderEID = 236;
	
	@Override
	public String getVersion() {
		return "Version 1.4.5";
	}

	@Override
	public void load() {
		ModLoader.registerBlock(blockNetherQuartz);
		ModLoader.addName(blockNetherQuartz, "Nether Quartz Ore");
		
		ModLoader.registerBlock(blockWiFi, ItemWiFi.class);
		for(int metaIndex = 0; metaIndex < 16; metaIndex ++) {
			ModLoader.addName(new ItemStack(blockWiFi,1,metaIndex), "WiFi "+BlockWiFi.wifiNames[metaIndex]);
		}
		ModLoader.registerTileEntity(TileEntityTotem.class, "Totem");
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,0), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.sugar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,1), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemWhiteChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,2), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Block.obsidian
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,3), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemMilkChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,4), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.blazePowder
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,4), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemDarkChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,5), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.speckledMelon
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,6), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.goldenCarrot
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,7), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.magmaCream
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,8), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.sugar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,9), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemWhiteChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,10), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Block.obsidian
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,11), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemMilkChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,12), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.blazePowder
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,12), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_NodeFood.itemDarkChocolateBar
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,13), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.speckledMelon
		});
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,14), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.goldenCarrot
		});
		
		ModLoader.addRecipe(new ItemStack(blockWiFi,1,15), new Object[] {
			"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), Item.magmaCream
		});
		if(ModLoader.isModLoaded("mod_Grant")) {
			ModLoader.addRecipe(new ItemStack(blockWiFi,1,1), new Object[] {
				"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_Grant.itemPizzaGroundBeef
			});
			ModLoader.addRecipe(new ItemStack(blockWiFi,1,6), new Object[] {
				"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotIron,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_Grant.itemPizzaMargherita
			});
			ModLoader.addRecipe(new ItemStack(blockWiFi,1,9), new Object[] {
				"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_Grant.itemPizzaGroundBeef
			});
			ModLoader.addRecipe(new ItemStack(blockWiFi,1,14), new Object[] {
				"XXX","YZY","XXX",Character.valueOf('X'), Item.ingotGold,Character.valueOf('Y'), Item.redstone,Character.valueOf('Z'), mod_Grant.itemPizzaMargherita
			});
		}
		
		ModLoader.registerBlock(blockSlimeCube, ItemSlime.class);
		for(int metaIndex = 0; metaIndex < 16; metaIndex ++) {
			ModLoader.addName(new ItemStack(blockSlimeCube,1,metaIndex), ItemDye.dyeColorNames[metaIndex]+" Slime Cube");
			
			for(int metaIndex2 = 0; metaIndex2 < 16; metaIndex2 ++) {
				ModLoader.addShapelessRecipe(new ItemStack(blockSlimeCube,1,metaIndex), new Object[] {
					new ItemStack(blockSlimeCube,1,metaIndex2), new ItemStack(Item.dyePowder,1,metaIndex)
				});
			}
		}
		ModLoader.addRecipe(new ItemStack(blockSlimeCube,1,10), new Object[] {
			"XX","XX",Character.valueOf('X'), Item.slimeBall
		});
		ModLoader.addRecipe(new ItemStack(Item.slimeBall,4), new Object[] {
			"X",Character.valueOf('X'), blockSlimeCube
		});
		
		
		ModLoader.registerBlock(blockPoswderLight);
		ModLoader.addName(blockPoswderLight, "Powder Light");
		
		
		ModLoader.addName(itemQuartzRuby, "Quartz Ruby");
		
		ModLoader.addName(itemRefinedNetherQuartz, "Refined Nether Quartz");
		ModLoader.addSmelting(blockNetherQuartz.blockID, new ItemStack(itemRefinedNetherQuartz), 1.0f);
		
		
		ModLoader.addName(itemQuartzRubyPick, "Quartz Ruby Pickaxe");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubyPick), new Object[] {
			"XXX"," Y "," Y ", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzRubyShovel, "Quartz Ruby Shovel");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubyShovel), new Object[] {
			"X","Y","Y", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzRubyAxe, "Quartz Ruby Axe");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubyAxe), new Object[] {
			"XX","XY"," Y", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzRubyHoe, "Quartz Ruby Hoe");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubyHoe), new Object[] {
			"XX"," Y"," Y", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzRubySword, "Quartz Ruby Sword");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubySword), new Object[] {
			"X","X","Y", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemNetherQuartzHelmet, "Nether Quartz Helmet");
		ModLoader.addRecipe(new ItemStack(itemNetherQuartzHelmet), new Object[] {
			"XXX","X X", Character.valueOf('X'), itemRefinedNetherQuartz
		});
		ModLoader.addName(itemNetherQuartzChest, "Nether Quatz Chestplate");
		ModLoader.addRecipe(new ItemStack(itemNetherQuartzChest), new Object[] {
			"X X","XXX","XXX", Character.valueOf('X'), itemRefinedNetherQuartz
		});
		ModLoader.addName(itemNetherQuartzLeggings, "Nether Quartz Leggings");
		ModLoader.addRecipe(new ItemStack(itemNetherQuartzLeggings), new Object[] {
			"XXX","X X","X X", Character.valueOf('X'), itemRefinedNetherQuartz
		});
		ModLoader.addName(itemNetherQuartzBoots, "Nether Quartz Boots");
		ModLoader.addRecipe(new ItemStack(itemNetherQuartzBoots), new Object[] {
			"X X","X X", Character.valueOf('X'), itemRefinedNetherQuartz
		});
		
		
		
		ModLoader.addName(itemQuartzAmethyst, "Quartz Amethyst");
		
		ModLoader.addName(itemQuartzAmethystPick, "Quartz Amethyst Pickaxe");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystPick), new Object[] {
			"XXX"," Y "," Y ", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzAmethystShovel, "Quartz Amethyst Shovel");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystShovel), new Object[] {
			"X","Y","Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzAmethystAxe, "Quartz Amethyst Axe");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystAxe), new Object[] {
			"XX","XY"," Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzAmethystHoe, "Quartz Amethyst Hoe");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystHoe), new Object[] {
			"XX"," Y"," Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.addName(itemQuartzAmethystSword, "Quartz Amethyst Sword");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystSword), new Object[] {
			"X","X","Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		
		ModLoader.addName(itemEpicPick, "Z32-Performance-X");
		ModLoader.addRecipe(new ItemStack(itemEpicPick), new Object[] {
			"DQD"," G "," I ",
			Character.valueOf('D'), Item.diamond,
			Character.valueOf('Q'), itemQuartzAmethyst,
			Character.valueOf('G'), Item.ingotGold,
			Character.valueOf('I'), Item.ingotIron
		});
		
		ModLoader.addName(itemEpicShovel, "Z32-Pro-Di-G");
		ModLoader.addRecipe(new ItemStack(itemEpicShovel), new Object[] {
			"D","G","I",
			Character.valueOf('G'), Item.ingotGold,
			Character.valueOf('I'), Item.ingotIron,
			Character.valueOf('D'), Item.diamond
		});
		
		ModLoader.addName(itemEpicAxe, "Z32-Flamento-AX");
		ModLoader.addRecipe(new ItemStack(itemEpicAxe), new Object[] {
			"DQ","DG"," I",
			Character.valueOf('Q'), itemQuartzAmethyst,
			Character.valueOf('G'), Item.ingotGold,
			Character.valueOf('D'), Item.diamond,
			Character.valueOf('I'), Item.ingotIron
		});
		
		ModLoader.addName(itemEpicHoe, "Z32-Scythe-Ki");
		ModLoader.addRecipe(new ItemStack(itemEpicHoe), new Object[] {
			"DQ"," G"," I",
			Character.valueOf('Q'), itemQuartzAmethyst,
			Character.valueOf('G'), Item.ingotGold,
			Character.valueOf('D'), Item.diamond,
			Character.valueOf('I'), Item.ingotIron
		});
		
		ModLoader.addName(itemEpicSword, "Z32-Blitzer");
		ModLoader.addRecipe(new ItemStack(itemEpicSword), new Object[] {
			"Z","X","Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.ingotGold, Character.valueOf('Z'), Item.diamond
		});
		
		
		ModLoader.addName(itemIronThrowingKnife, "Iron Throwing Knife");
		ModLoader.addRecipe(new ItemStack(itemIronThrowingKnife, 8), new Object[] {
			"X","Y", Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), Item.stick
		});
		ModLoader.addName(itemDiamondThrowingKnife, "Diamond Throwing Knife");
		ModLoader.addRecipe(new ItemStack(itemDiamondThrowingKnife, 8), new Object[] {
			"X","Y", Character.valueOf('X'), Item.diamond, Character.valueOf('Y'), Item.stick
		});
		ModLoader.addName(itemQuartzRubyThrowingKnife, "Quartz Ruby Throwing Knife");
		ModLoader.addRecipe(new ItemStack(itemQuartzRubyThrowingKnife, 8), new Object[] {
			"X","Y", Character.valueOf('X'), itemQuartzRuby, Character.valueOf('Y'), Item.stick
		});
		ModLoader.addName(itemQuartzAmethystThrowingKnife, "Quartz Ametyst Throwing Knife");
		ModLoader.addRecipe(new ItemStack(itemQuartzAmethystThrowingKnife, 8), new Object[] {
			"X","Y", Character.valueOf('X'), itemQuartzAmethyst, Character.valueOf('Y'), Item.stick
		});
		
		ModLoader.registerEntityID(EntityThrowingKnifeIron.class, "ironThrowingKnife", ironThrowingKnifeEID);
		ModLoader.registerEntityID(EntityThrowingKnifeDiamond.class, "diamondThrowingKnife", diamondThrowingKnifeEID);
		ModLoader.registerEntityID(EntityThrowingKnifeQuartzRuby.class, "quartzRubyThrowingKnife", quartzRubyThrowingKnifeEID);
		ModLoader.registerEntityID(EntityThrowingKnifeQuartzAmethyst.class, "quartzAmethystThrowingKnife", quartzAmethystThrowingKnifeEID);
		ModLoader.addEntityTracker(this, EntityThrowingKnifeIron.class, ironThrowingKnifeEID , 64, 20, true);
		ModLoader.addEntityTracker(this, EntityThrowingKnifeDiamond.class, diamondThrowingKnifeEID , 64, 20, true);
		ModLoader.addEntityTracker(this, EntityThrowingKnifeQuartzRuby.class, quartzRubyThrowingKnifeEID , 64, 20, true);
		ModLoader.addEntityTracker(this, EntityThrowingKnifeQuartzAmethyst.class, quartzAmethystThrowingKnifeEID , 64, 20, true);
		ModLoader.addDispenserBehavior(itemIronThrowingKnife, new BehaviorThrowingKnifeDispense(ItemThrowingKnife.TYPE_IRON));
		ModLoader.addDispenserBehavior(itemDiamondThrowingKnife, new BehaviorThrowingKnifeDispense(ItemThrowingKnife.TYPE_DIAMOND));
		ModLoader.addDispenserBehavior(itemQuartzRubyThrowingKnife, new BehaviorThrowingKnifeDispense(ItemThrowingKnife.TYPE_QUARTZ_RUBY));
		ModLoader.addDispenserBehavior(itemQuartzAmethystThrowingKnife, new BehaviorThrowingKnifeDispense(ItemThrowingKnife.TYPE_QUARTZ_AMETHYST));
		
		ModLoader.addName(itemLightPowder, "Light Powder in a Bottle");
		ModLoader.addRecipe(new ItemStack(itemLightPowder, 4), new Object[] {
			"X","Y", Character.valueOf('X'), mod_Grant.itemRefinedQuartz, Character.valueOf('Y'), Item.glassBottle
		});
		
		ModLoader.registerEntityID(EntityLightPowder.class, "lightPowder", lightPowderEID);
		ModLoader.addEntityTracker(this, EntityLightPowder.class, lightPowderEID , 64, 20, true);
		ModLoader.addDispenserBehavior(itemLightPowder, new BehaviorLightPowderDispense());
		
	}

	@Override
	public void generateNether(World parWorld, Random parRandom, int chunkX, int chunkz) {
		useWorldGenerator(chunkX, chunkz, parWorld, parRandom, 20, new WorldGenMinableNether(blockNetherQuartz.blockID, 10), 0, 64);
	}
	
	protected void useWorldGenerator(int x, int z, World world, Random random, int i, WorldGenMinableNether worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = x + random.nextInt(16);
            int j1 = random.nextInt(k - j) + j;
            int k1 = z + random.nextInt(16);
            worldgenerator.generate(world, random, i1, j1, k1);
        }
    }
	@Override
	public Entity spawnEntity(int var1, World var2, double var3, double var5, double var7) {
		if (var1 == ironThrowingKnifeEID) {
			EntityThrowingKnife throwingKnife = new EntityThrowingKnifeIron(var2, var3, var5, var7);
			throwingKnife.setKnifeType(ItemThrowingKnife.TYPE_IRON);
			return throwingKnife;
		} else
		if (var1 == diamondThrowingKnifeEID) {
			EntityThrowingKnife throwingKnife = new EntityThrowingKnifeDiamond(var2, var3, var5, var7);
			throwingKnife.setKnifeType(ItemThrowingKnife.TYPE_DIAMOND);
			return throwingKnife;
		} else
		if (var1 == quartzRubyThrowingKnifeEID) {
			EntityThrowingKnife throwingKnife = new EntityThrowingKnifeQuartzRuby(var2, var3, var5, var7);
			throwingKnife.setKnifeType(ItemThrowingKnife.TYPE_QUARTZ_RUBY);
			return throwingKnife;
		} else
		if (var1 == quartzAmethystThrowingKnifeEID) {
			EntityThrowingKnife throwingKnife = new EntityThrowingKnifeQuartzAmethyst(var2, var3, var5, var7);
			throwingKnife.setKnifeType(ItemThrowingKnife.TYPE_QUARTZ_AMETHYST);
			return throwingKnife;
		} else
		if (var1 == lightPowderEID) {
			EntityLightPowder lightPowder = new EntityLightPowder(var2, var3, var5, var7);
			return lightPowder;
		}
		
		return null;
	}
	@Override
	public Packet23VehicleSpawn getSpawnPacket(Entity var1, int var2) {
		if(var1 instanceof EntityThrowingKnife) {
			return new Packet23VehicleSpawn(var1, var2);
		}
		if(var1 instanceof EntityLightPowder) {
			return new Packet23VehicleSpawn(var1, var2);
		}
		return null;
	}
	
	@Override
	public void addRenderer(Map map)
	{
		map.put(EntityThrowingKnife.class, new RenderThrowingKnife());
		map.put(EntityLightPowder.class, new RenderSnowball(itemLightPowder));
	}
}
