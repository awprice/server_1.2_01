package net.minecraft.server;

import java.util.Random;

public class BlockLog extends Block {

    protected BlockLog(int i) {
        super(i, Material.WOOD);
        this.textureId = 20;
    }

    public int a(Random random) {
        return 1;
    }

    public int a(int i, Random random) {
        return Block.LOG.id;
    }

    public void b(World world, int i, int j, int k) {
        byte b0 = 4;
        int l = b0 + 1;

        if (world.a(i - l, j - l, k - l, i + l, j + l, k + l)) {
            for (int i1 = -b0; i1 <= b0; ++i1) {
                for (int j1 = -b0; j1 <= b0; ++j1) {
                    for (int k1 = -b0; k1 <= b0; ++k1) {
                        int l1 = world.getTypeId(i + i1, j + j1, k + k1);

                        if (l1 == Block.LEAVES.id) {
                            int i2 = world.getData(i + i1, j + j1, k + k1);

                            if ((i2 & 4) == 0) {
                                world.d(i + i1, j + j1, k + k1, i2 | 4);
                            }
                        }
                    }
                }
            }
        }
    }

    protected int b(int i) {
        return i;
    }
}
