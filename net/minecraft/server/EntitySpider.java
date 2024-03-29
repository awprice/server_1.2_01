package net.minecraft.server;

public class EntitySpider extends EntityMonster {

    public EntitySpider(World world) {
        super(world);
        this.texture = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bC = 0.8F;
    }

    public double k() {
        return (double) this.width * 0.75D - 0.5D;
    }

    protected Entity l() {
        float f = this.b(1.0F);

        if (f < 0.5F) {
            double d0 = 16.0D;

            return this.world.a(this, d0);
        } else {
            return null;
        }
    }

    protected String e() {
        return "mob.spider";
    }

    protected String f() {
        return "mob.spider";
    }

    protected String g() {
        return "mob.spiderdeath";
    }

    protected void a(Entity entity, float f) {
        float f1 = this.b(1.0F);

        if (f1 > 0.5F && this.random.nextInt(100) == 0) {
            this.d = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.random.nextInt(10) == 0) {
                if (this.onGround) {
                    double d0 = entity.locX - this.locX;
                    double d1 = entity.locZ - this.locZ;
                    float f2 = MathHelper.a(d0 * d0 + d1 * d1);

                    this.motX = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.motX * 0.20000000298023224D;
                    this.motZ = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.motZ * 0.20000000298023224D;
                    this.motY = 0.4000000059604645D;
                }
            } else {
                super.a(entity, f);
            }
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected int h() {
        return Item.STRING.id;
    }

    public boolean m() {
        return this.B;
    }
}
