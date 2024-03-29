package net.minecraft.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldServer extends World {

    public ChunkProviderServer A;
    public boolean B = false;
    public boolean C;
    private MinecraftServer D;
    private EntityList E = new EntityList();

    public WorldServer(MinecraftServer minecraftserver, File file1, String s, int i) {
        super(file1, s, (new Random()).nextLong(), WorldProvider.a(i));
        this.D = minecraftserver;
    }

    public void f() {
        super.f();
    }

    public void a(Entity entity, boolean flag) {
        if (!this.D.m && (entity instanceof EntityAnimal || entity instanceof EntityWaterAnimal)) {
            entity.q();
        }

        if (entity.passenger == null || !(entity.passenger instanceof EntityHuman)) {
            super.a(entity, flag);
        }
    }

    public void b(Entity entity, boolean flag) {
        super.a(entity, flag);
    }

    protected IChunkProvider a(File file1) {
        this.A = new ChunkProviderServer(this, this.q.a(file1), this.q.c());
        return this.A;
    }

    public List d(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.c.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.c.get(k1);

            if (tileentity.b >= i && tileentity.c >= j && tileentity.d >= k && tileentity.b < l && tileentity.c < i1 && tileentity.d < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int k) {
        int l = (int) MathHelper.e((float) (i - this.spawnX));
        int i1 = (int) MathHelper.e((float) (k - this.spawnZ));

        if (l > i1) {
            i1 = l;
        }

        return i1 > 16 || this.D.f.g(entityhuman.name);
    }

    protected void b(Entity entity) {
        super.b(entity);
        this.E.a(entity.id, entity);
    }

    protected void c(Entity entity) {
        super.c(entity);
        this.E.d(entity.id);
    }

    public Entity a(int i) {
        return (Entity) this.E.a(i);
    }

    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);

        this.D.k.b(entity, packet38entitystatus);
    }

    public Explosion a(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        Explosion explosion = super.a(entity, d0, d1, d2, f, flag);

        this.D.f.a(d0, d1, d2, 64.0D, new Packet60Explosion(d0, d1, d2, f, explosion.g));
        return explosion;
    }

    public void c(int i, int j, int k, int l, int i1) {
        super.c(i, j, k, l, i1);
        this.D.f.a((double) i, (double) j, (double) k, 64.0D, new Packet54PlayNoteBlock(i, j, k, l, i1));
    }
}
