package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class Block {

    public static final StepSound d = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound e = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound f = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound g = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound h = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound i = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound j = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound k = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound l = new StepSoundSand("sand", 1.0F, 1.0F);
    public static final Block[] byId = new Block[256];
    public static final boolean[] n = new boolean[256];
    public static final boolean[] o = new boolean[256];
    public static final boolean[] p = new boolean[256];
    public static final int[] q = new int[256];
    public static final boolean[] r = new boolean[256];
    public static final int[] s = new int[256];
    public static final Block STONE = (new BlockStone(1, 1)).c(1.5F).b(10.0F).a(h).a("stone");
    public static final BlockGrass GRASS = (BlockGrass) (new BlockGrass(2)).c(0.6F).a(g).a("grass");
    public static final Block DIRT = (new BlockDirt(3, 2)).c(0.5F).a(f).a("dirt");
    public static final Block COBBLESTONE = (new Block(4, 16, Material.STONE)).c(2.0F).b(10.0F).a(h).a("stonebrick");
    public static final Block WOOD = (new Block(5, 4, Material.WOOD)).c(2.0F).b(5.0F).a(e).a("wood");
    public static final Block SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(g).a("sapling");
    public static final Block BEDROCK = (new Block(7, 17, Material.STONE)).c(-1.0F).b(6000000.0F).a(h).a("bedrock");
    public static final Block WATER = (new BlockFlowing(8, Material.WATER)).c(100.0F).e(3).a("water");
    public static final Block STATIONARY_WATER = (new BlockStationary(9, Material.WATER)).c(100.0F).e(3).a("water");
    public static final Block LAVA = (new BlockFlowing(10, Material.LAVA)).c(0.0F).a(1.0F).e(255).a("lava");
    public static final Block STATIONARY_LAVA = (new BlockStationary(11, Material.LAVA)).c(100.0F).a(1.0F).e(255).a("lava");
    public static final Block SAND = (new BlockSand(12, 18)).c(0.5F).a(l).a("sand");
    public static final Block GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
    public static final Block GOLD_ORE = (new BlockOre(14, 32)).c(3.0F).b(5.0F).a(h).a("oreGold");
    public static final Block IRON_ORE = (new BlockOre(15, 33)).c(3.0F).b(5.0F).a(h).a("oreIron");
    public static final Block COAL_ORE = (new BlockOre(16, 34)).c(3.0F).b(5.0F).a(h).a("oreCoal");
    public static final Block LOG = (new BlockLog(17)).c(2.0F).a(e).a("log");
    public static final BlockLeaves LEAVES = (BlockLeaves) (new BlockLeaves(18, 52)).c(0.2F).e(1).a(g).a("leaves");
    public static final Block SPONGE = (new BlockSponge(19)).c(0.6F).a(g).a("sponge");
    public static final Block GLASS = (new BlockGlass(20, 49, Material.SHATTERABLE, false)).c(0.3F).a(j).a("glass");
    public static final Block LAPIS_ORE = (new BlockOre(21, 160)).c(3.0F).b(5.0F).a(h).a("oreLapis");
    public static final Block LAPIS_BLOCK = (new Block(22, 144, Material.STONE)).c(3.0F).b(5.0F).a(h).a("blockLapis");
    public static final Block DISPENSER = (new BlockDispenser(23)).c(3.5F).a(h).a("dispenser");
    public static final Block SANDSTONE = (new BlockSandStone(24)).a(h).c(0.8F).a("sandStone");
    public static final Block NOTE_BLOCK = (new BlockNote(25)).c(0.8F).a("musicBlock");
    public static final Block S = null;
    public static final Block T = null;
    public static final Block U = null;
    public static final Block V = null;
    public static final Block W = null;
    public static final Block X = null;
    public static final Block Y = null;
    public static final Block Z = null;
    public static final Block aa = null;
    public static final Block WOOL = (new BlockCloth()).c(0.8F).a(k).a("cloth");
    public static final Block ac = null;
    public static final BlockFlower YELLOW_FLOWER = (BlockFlower) (new BlockFlower(37, 13)).c(0.0F).a(g).a("flower");
    public static final BlockFlower RED_ROSE = (BlockFlower) (new BlockFlower(38, 12)).c(0.0F).a(g).a("rose");
    public static final BlockFlower BROWN_MUSHROOM = (BlockFlower) (new BlockMushroom(39, 29)).c(0.0F).a(g).a(0.125F).a("mushroom");
    public static final BlockFlower RED_MUSHROOM = (BlockFlower) (new BlockMushroom(40, 28)).c(0.0F).a(g).a("mushroom");
    public static final Block GOLD_BLOCK = (new BlockOreBlock(41, 23)).c(3.0F).b(10.0F).a(i).a("blockGold");
    public static final Block IRON_BLOCK = (new BlockOreBlock(42, 22)).c(5.0F).b(10.0F).a(i).a("blockIron");
    public static final Block DOUBLE_STEP = (new BlockStep(43, true)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final Block STEP = (new BlockStep(44, false)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final Block BRICK = (new Block(45, 7, Material.STONE)).c(2.0F).b(10.0F).a(h).a("brick");
    public static final Block TNT = (new BlockTNT(46, 8)).c(0.0F).a(g).a("tnt");
    public static final Block BOOKSHELF = (new BlockBookshelf(47, 35)).c(1.5F).a(e).a("bookshelf");
    public static final Block MOSSY_COBBLESTONE = (new Block(48, 36, Material.STONE)).c(2.0F).b(10.0F).a(h).a("stoneMoss");
    public static final Block OBSIDIAN = (new BlockObsidian(49, 37)).c(10.0F).b(2000.0F).a(h).a("obsidian");
    public static final Block TORCH = (new BlockTorch(50, 80)).c(0.0F).a(0.9375F).a(e).a("torch");
    public static final BlockFire FIRE = (BlockFire) (new BlockFire(51, 31)).c(0.0F).a(1.0F).a(e).a("fire");
    public static final Block MOB_SPAWNER = (new BlockMobSpawner(52, 65)).c(5.0F).a(i).a("mobSpawner");
    public static final Block WOOD_STAIRS = (new BlockStairs(53, WOOD)).a("stairsWood");
    public static final Block CHEST = (new BlockChest(54)).c(2.5F).a(e).a("chest");
    public static final Block REDSTONE_WIRE = (new BlockRedstoneWire(55, 84)).c(0.0F).a(d).a("redstoneDust");
    public static final Block DIAMOND_ORE = (new BlockOre(56, 50)).c(3.0F).b(5.0F).a(h).a("oreDiamond");
    public static final Block DIAMOND_BLOCK = (new BlockOreBlock(57, 24)).c(5.0F).b(10.0F).a(i).a("blockDiamond");
    public static final Block WORKBENCH = (new BlockWorkbench(58)).c(2.5F).a(e).a("workbench");
    public static final Block CROPS = (new BlockCrops(59, 88)).c(0.0F).a(g).a("crops");
    public static final Block SOIL = (new BlockSoil(60)).c(0.6F).a(f).a("farmland");
    public static final Block FURNACE = (new BlockFurnace(61, false)).c(3.5F).a(h).a("furnace");
    public static final Block BURNING_FURNACE = (new BlockFurnace(62, true)).c(3.5F).a(h).a(0.875F).a("furnace");
    public static final Block SIGN_POST = (new BlockSign(63, TileEntitySign.class, true)).c(1.0F).a(e).a("sign");
    public static final Block WOODEN_DOOR = (new BlockDoor(64, Material.WOOD)).c(3.0F).a(e).a("doorWood");
    public static final Block LADDER = (new BlockLadder(65, 83)).c(0.4F).a(e).a("ladder");
    public static final Block RAILS = (new BlockMinecartTrack(66, 128)).c(0.7F).a(i).a("rail");
    public static final Block COBBLESTONE_STAIRS = (new BlockStairs(67, COBBLESTONE)).a("stairsStone");
    public static final Block WALL_SIGN = (new BlockSign(68, TileEntitySign.class, false)).c(1.0F).a(e).a("sign");
    public static final Block LEVER = (new BlockLever(69, 96)).c(0.5F).a(e).a("lever");
    public static final Block STONE_PLATE = (new BlockPressurePlate(70, STONE.textureId, EnumMobType.MOBS)).c(0.5F).a(h).a("pressurePlate");
    public static final Block IRON_DOOR_BLOCK = (new BlockDoor(71, Material.ORE)).c(5.0F).a(i).a("doorIron");
    public static final Block WOOD_PLATE = (new BlockPressurePlate(72, WOOD.textureId, EnumMobType.EVERYTHING)).c(0.5F).a(e).a("pressurePlate");
    public static final Block REDSTONE_ORE = (new BlockRedstoneOre(73, 51, false)).c(3.0F).b(5.0F).a(h).a("oreRedstone");
    public static final Block GLOWING_REDSTONE_ORE = (new BlockRedstoneOre(74, 51, true)).a(0.625F).c(3.0F).b(5.0F).a(h).a("oreRedstone");
    public static final Block REDSTONE_TORCH_OFF = (new BlockRedstoneTorch(75, 115, false)).c(0.0F).a(e).a("notGate");
    public static final Block REDSTONE_TORCH_ON = (new BlockRedstoneTorch(76, 99, true)).c(0.0F).a(0.5F).a(e).a("notGate");
    public static final Block STONE_BUTTON = (new BlockButton(77, STONE.textureId)).c(0.5F).a(h).a("button");
    public static final Block SNOW = (new BlockSnow(78, 66)).c(0.1F).a(k).a("snow");
    public static final Block ICE = (new BlockIce(79, 67)).c(0.5F).e(3).a(j).a("ice");
    public static final Block SNOW_BLOCK = (new BlockSnowBlock(80, 66)).c(0.2F).a(k).a("snow");
    public static final Block CACTUS = (new BlockCactus(81, 70)).c(0.4F).a(k).a("cactus");
    public static final Block CLAY = (new BlockClay(82, 72)).c(0.6F).a(f).a("clay");
    public static final Block SUGAR_CANE_BLOCK = (new BlockReed(83, 73)).c(0.0F).a(g).a("reeds");
    public static final Block JUKEBOX = (new BlockJukeBox(84, 74)).c(2.0F).b(10.0F).a(h).a("jukebox");
    public static final Block FENCE = (new BlockFence(85, 4)).c(2.0F).b(5.0F).a(e).a("fence");
    public static final Block PUMPKIN = (new BlockPumpkin(86, 102, false)).c(1.0F).a(e).a("pumpkin");
    public static final Block NETHERRACK = (new BlockBloodStone(87, 103)).c(0.4F).a(h).a("hellrock");
    public static final Block SOUL_SAND = (new BlockSlowSand(88, 104)).c(0.5F).a(l).a("hellsand");
    public static final Block GLOWSTONE = (new BlockLightStone(89, 105, Material.SHATTERABLE)).c(0.3F).a(j).a(1.0F).a("lightgem");
    public static final BlockPortal PORTAL = (BlockPortal) (new BlockPortal(90, 14)).c(-1.0F).a(j).a(0.75F).a("portal");
    public static final Block JACK_O_LANTERN = (new BlockPumpkin(91, 102, true)).c(1.0F).a(e).a(1.0F).a("litpumpkin");
    public static final Block CAKE_BLOCK = (new BlockCake(92, 121)).c(0.5F).a(k).a("cake");
    public int textureId;
    public final int id;
    protected float strength;
    protected float durability;
    public double minX;
    public double minY;
    public double minZ;
    public double maxX;
    public double maxY;
    public double maxZ;
    public StepSound stepSound;
    public float bs;
    public final Material material;
    public float frictionFactor;
    private String name;

    protected Block(int i, Material material) {
        this.stepSound = d;
        this.bs = 1.0F;
        this.frictionFactor = 0.6F;
        if (byId[i] != null) {
            throw new IllegalArgumentException("Slot " + i + " is already occupied by " + byId[i] + " when adding " + this);
        } else {
            this.material = material;
            byId[i] = this;
            this.id = i;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            o[i] = this.a();
            q[i] = this.a() ? 255 : 0;
            r[i] = this.f();
            p[i] = false;
        }
    }

    protected Block(int i, int j, Material material) {
        this(i, material);
        this.textureId = j;
    }

    protected Block a(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }

    protected Block e(int i) {
        q[this.id] = i;
        return this;
    }

    protected Block a(float f) {
        s[this.id] = (int) (15.0F * f);
        return this;
    }

    protected Block b(float f) {
        this.durability = f * 3.0F;
        return this;
    }

    private boolean f() {
        return false;
    }

    protected Block c(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }

    protected void a(boolean flag) {
        n[this.id] = flag;
    }

    public void a(float f, float f1, float f2, float f3, float f4, float f5) {
        this.minX = (double) f;
        this.minY = (double) f1;
        this.minZ = (double) f2;
        this.maxX = (double) f3;
        this.maxY = (double) f4;
        this.maxZ = (double) f5;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return l == 0 && this.minY > 0.0D ? true : (l == 1 && this.maxY < 1.0D ? true : (l == 2 && this.minZ > 0.0D ? true : (l == 3 && this.maxZ < 1.0D ? true : (l == 4 && this.minX > 0.0D ? true : (l == 5 && this.maxX < 1.0D ? true : !iblockaccess.d(i, j, k))))));
    }

    public int a(int i) {
        return this.textureId;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        AxisAlignedBB axisalignedbb1 = this.d(world, i, j, k);

        if (axisalignedbb1 != null && axisalignedbb.a(axisalignedbb1)) {
            arraylist.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) j + this.maxY, (double) k + this.maxZ);
    }

    public boolean a() {
        return true;
    }

    public boolean a(int i, boolean flag) {
        return this.d();
    }

    public boolean d() {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {}

    public void a(World world, int i, int j, int k, int l) {}

    public void b(World world, int i, int j, int k, int l) {}

    public int b() {
        return 10;
    }

    public void e(World world, int i, int j, int k) {}

    public void b(World world, int i, int j, int k) {}

    public int a(Random random) {
        return 1;
    }

    public int a(int i, Random random) {
        return this.id;
    }

    public float a(EntityHuman entityhuman) {
        return this.strength < 0.0F ? 0.0F : (!entityhuman.b(this) ? 1.0F / this.strength / 100.0F : entityhuman.a(this) / this.strength / 30.0F);
    }

    public void a_(World world, int i, int j, int k, int l) {
        this.a(world, i, j, k, l, 1.0F);
    }

    public void a(World world, int i, int j, int k, int l, float f) {
        if (!world.isStatic) {
            int i1 = this.a(world.l);

            for (int j1 = 0; j1 < i1; ++j1) {
                if (world.l.nextFloat() <= f) {
                    int k1 = this.a(l, world.l);

                    if (k1 > 0) {
                        float f1 = 0.7F;
                        double d0 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        double d1 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        double d2 = (double) (world.l.nextFloat() * f1) + (double) (1.0F - f1) * 0.5D;
                        EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, new ItemStack(k1, 1, this.b(l)));

                        entityitem.c = 10;
                        world.a((Entity) entityitem);
                    }
                }
            }
        }
    }

    protected int b(int i) {
        return 0;
    }

    public float a(Entity entity) {
        return this.durability / 5.0F;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a((IBlockAccess) world, i, j, k);
        vec3d = vec3d.c((double) (-i), (double) (-j), (double) (-k));
        vec3d1 = vec3d1.c((double) (-i), (double) (-j), (double) (-k));
        Vec3D vec3d2 = vec3d.a(vec3d1, this.minX);
        Vec3D vec3d3 = vec3d.a(vec3d1, this.maxX);
        Vec3D vec3d4 = vec3d.b(vec3d1, this.minY);
        Vec3D vec3d5 = vec3d.b(vec3d1, this.maxY);
        Vec3D vec3d6 = vec3d.c(vec3d1, this.minZ);
        Vec3D vec3d7 = vec3d.c(vec3d1, this.maxZ);

        if (!this.a(vec3d2)) {
            vec3d2 = null;
        }

        if (!this.a(vec3d3)) {
            vec3d3 = null;
        }

        if (!this.b(vec3d4)) {
            vec3d4 = null;
        }

        if (!this.b(vec3d5)) {
            vec3d5 = null;
        }

        if (!this.c(vec3d6)) {
            vec3d6 = null;
        }

        if (!this.c(vec3d7)) {
            vec3d7 = null;
        }

        Vec3D vec3d8 = null;

        if (vec3d2 != null && (vec3d8 == null || vec3d.a(vec3d2) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d2;
        }

        if (vec3d3 != null && (vec3d8 == null || vec3d.a(vec3d3) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d3;
        }

        if (vec3d4 != null && (vec3d8 == null || vec3d.a(vec3d4) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d4;
        }

        if (vec3d5 != null && (vec3d8 == null || vec3d.a(vec3d5) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d5;
        }

        if (vec3d6 != null && (vec3d8 == null || vec3d.a(vec3d6) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d6;
        }

        if (vec3d7 != null && (vec3d8 == null || vec3d.a(vec3d7) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d7;
        }

        if (vec3d8 == null) {
            return null;
        } else {
            byte b0 = -1;

            if (vec3d8 == vec3d2) {
                b0 = 4;
            }

            if (vec3d8 == vec3d3) {
                b0 = 5;
            }

            if (vec3d8 == vec3d4) {
                b0 = 0;
            }

            if (vec3d8 == vec3d5) {
                b0 = 1;
            }

            if (vec3d8 == vec3d6) {
                b0 = 2;
            }

            if (vec3d8 == vec3d7) {
                b0 = 3;
            }

            return new MovingObjectPosition(i, j, k, b0, vec3d8.c((double) i, (double) j, (double) k));
        }
    }

    private boolean a(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.b >= this.minY && vec3d.b <= this.maxY && vec3d.c >= this.minZ && vec3d.c <= this.maxZ;
    }

    private boolean b(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.minX && vec3d.a <= this.maxX && vec3d.c >= this.minZ && vec3d.c <= this.maxZ;
    }

    private boolean c(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.minX && vec3d.a <= this.maxX && vec3d.b >= this.minY && vec3d.b <= this.maxY;
    }

    public void a_(World world, int i, int j, int k) {}

    public boolean a(World world, int i, int j, int k) {
        int l = world.getTypeId(i, j, k);

        return l == 0 || byId[l].material.isLiquid();
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public void b(World world, int i, int j, int k, Entity entity) {}

    public void c(World world, int i, int j, int k, int l) {}

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {}

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {}

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {}

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void a(World world, int i, int j, int k, Entity entity) {}

    public boolean d(World world, int i, int j, int k, int l) {
        return false;
    }

    public void g(World world, int i, int j, int k, int l) {
        this.a_(world, i, j, k, l);
    }

    public boolean f(World world, int i, int j, int k) {
        return true;
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {}

    public Block a(String s) {
        this.name = "tile." + s;
        return this;
    }

    public String e() {
        return this.name;
    }

    public void a(World world, int i, int j, int k, int l, int i1) {}

    static {
        Item.byId[WOOL.id] = (new ItemCloth(WOOL.id - 256)).a("cloth");
        Item.byId[LOG.id] = (new ItemLog(LOG.id - 256)).a("log");

        for (int i = 0; i < 256; ++i) {
            if (byId[i] != null && Item.byId[i] == null) {
                Item.byId[i] = new ItemBlock(i - 256);
            }
        }
    }
}
