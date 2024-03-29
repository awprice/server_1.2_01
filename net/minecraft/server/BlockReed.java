package net.minecraft.server;

import java.util.Random;

public class BlockReed extends Block {

    protected BlockReed(int i, int j) {
        super(i, Material.PLANT);
        this.textureId = j;
        float f = 0.375F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        this.a(true);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (world.isEmpty(i, j + 1, k)) {
            int l;

            for (l = 1; world.getTypeId(i, j - l, k) == this.id; ++l) {
                ;
            }

            if (l < 3) {
                int i1 = world.getData(i, j, k);

                if (i1 == 15) {
                    world.e(i, j + 1, k, this.id);
                    world.c(i, j, k, 0);
                } else {
                    world.c(i, j, k, i1 + 1);
                }
            }
        }
    }

    public boolean a(World world, int i, int j, int k) {
        int l = world.getTypeId(i, j - 1, k);

        return l == this.id ? true : (l != Block.GRASS.id && l != Block.DIRT.id ? false : (world.getMaterial(i - 1, j - 1, k) == Material.WATER ? true : (world.getMaterial(i + 1, j - 1, k) == Material.WATER ? true : (world.getMaterial(i, j - 1, k - 1) == Material.WATER ? true : world.getMaterial(i, j - 1, k + 1) == Material.WATER))));
    }

    public void b(World world, int i, int j, int k, int l) {
        this.g(world, i, j, k);
    }

    protected final void g(World world, int i, int j, int k) {
        if (!this.f(world, i, j, k)) {
            this.a_(world, i, j, k, world.getData(i, j, k));
            world.e(i, j, k, 0);
        }
    }

    public boolean f(World world, int i, int j, int k) {
        return this.a(world, i, j, k);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public int a(int i, Random random) {
        return Item.SUGAR_CANE.id;
    }

    public boolean a() {
        return false;
    }
}
