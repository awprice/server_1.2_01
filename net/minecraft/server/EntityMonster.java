package net.minecraft.server;

public class EntityMonster extends EntityCreature implements IMonster {

    protected int c = 2;

    public EntityMonster(World world) {
        super(world);
        this.health = 20;
    }

    public void o() {
        float f = this.b(1.0F);

        if (f > 0.5F) {
            this.bw += 2;
        }

        super.o();
    }

    public void b_() {
        super.b_();
        if (this.world.k == 0) {
            this.q();
        }
    }

    protected Entity l() {
        EntityHuman entityhuman = this.world.a(this, 16.0D);

        return entityhuman != null && this.i(entityhuman) ? entityhuman : null;
    }

    public boolean a(Entity entity, int i) {
        if (super.a(entity, i)) {
            if (this.passenger != entity && this.vehicle != entity) {
                if (entity != this) {
                    this.d = entity;
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    protected void a(Entity entity, float f) {
        if ((double) f < 2.5D && entity.boundingBox.e > this.boundingBox.b && entity.boundingBox.b < this.boundingBox.e) {
            this.attackTicks = 20;
            entity.a(this, this.c);
        }
    }

    protected float a(int i, int j, int k) {
        return 0.5F - this.world.l(i, j, k);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public boolean b() {
        int i = MathHelper.b(this.locX);
        int j = MathHelper.b(this.boundingBox.b);
        int k = MathHelper.b(this.locZ);

        if (this.world.a(EnumSkyBlock.SKY, i, j, k) > this.random.nextInt(32)) {
            return false;
        } else {
            int l = this.world.j(i, j, k);

            return l <= this.random.nextInt(8) && super.b();
        }
    }
}
