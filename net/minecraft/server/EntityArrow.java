package net.minecraft.server;

import java.util.List;

public class EntityArrow extends Entity {

    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = 0;
    private boolean ak = false;
    public int a = 0;
    public EntityLiving b;
    private int al;
    private int am = 0;

    public EntityArrow(World world) {
        super(world);
        this.a(0.5F, 0.5F);
    }

    public EntityArrow(World world, double d0, double d1, double d2) {
        super(world);
        this.a(0.5F, 0.5F);
        this.a(d0, d1, d2);
        this.height = 0.0F;
    }

    public EntityArrow(World world, EntityLiving entityliving) {
        super(world);
        this.b = entityliving;
        this.a(0.5F, 0.5F);
        this.c(entityliving.locX, entityliving.locY + (double) entityliving.w(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
        this.locX -= (double) (MathHelper.b(this.yaw / 180.0F * 3.1415927F) * 0.16F);
        this.locY -= 0.10000000149011612D;
        this.locZ -= (double) (MathHelper.a(this.yaw / 180.0F * 3.1415927F) * 0.16F);
        this.a(this.locX, this.locY, this.locZ);
        this.height = 0.0F;
        this.motX = (double) (-MathHelper.a(this.yaw / 180.0F * 3.1415927F) * MathHelper.b(this.pitch / 180.0F * 3.1415927F));
        this.motZ = (double) (MathHelper.b(this.yaw / 180.0F * 3.1415927F) * MathHelper.b(this.pitch / 180.0F * 3.1415927F));
        this.motY = (double) (-MathHelper.a(this.pitch / 180.0F * 3.1415927F));
        this.a(this.motX, this.motY, this.motZ, 1.5F, 1.0F);
    }

    protected void a() {}

    public void a(double d0, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.random.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.random.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.random.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.motX = d0;
        this.motY = d1;
        this.motZ = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);

        this.lastYaw = this.yaw = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.lastPitch = this.pitch = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.al = 0;
    }

    public void b_() {
        super.b_();
        if (this.lastPitch == 0.0F && this.lastYaw == 0.0F) {
            float f = MathHelper.a(this.motX * this.motX + this.motZ * this.motZ);

            this.lastYaw = this.yaw = (float) (Math.atan2(this.motX, this.motZ) * 180.0D / 3.1415927410125732D);
            this.lastPitch = this.pitch = (float) (Math.atan2(this.motY, (double) f) * 180.0D / 3.1415927410125732D);
        }

        if (this.a > 0) {
            --this.a;
        }

        if (this.ak) {
            int i = this.world.getTypeId(this.c, this.d, this.e);

            if (i == this.f) {
                ++this.al;
                if (this.al == 1200) {
                    this.q();
                }

                return;
            }

            this.ak = false;
            this.motX *= (double) (this.random.nextFloat() * 0.2F);
            this.motY *= (double) (this.random.nextFloat() * 0.2F);
            this.motZ *= (double) (this.random.nextFloat() * 0.2F);
            this.al = 0;
            this.am = 0;
        } else {
            ++this.am;
        }

        Vec3D vec3d = Vec3D.b(this.locX, this.locY, this.locZ);
        Vec3D vec3d1 = Vec3D.b(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
        MovingObjectPosition movingobjectposition = this.world.a(vec3d, vec3d1);

        vec3d = Vec3D.b(this.locX, this.locY, this.locZ);
        vec3d1 = Vec3D.b(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
        if (movingobjectposition != null) {
            vec3d1 = Vec3D.b(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
        }

        Entity entity = null;
        List list = this.world.b((Entity) this, this.boundingBox.a(this.motX, this.motY, this.motZ).b(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;

        float f1;

        for (int j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity) list.get(j);

            if (entity1.c_() && (entity1 != this.b || this.am >= 5)) {
                f1 = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.b((double) f1, (double) f1, (double) f1);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);

                if (movingobjectposition1 != null) {
                    double d1 = vec3d.a(movingobjectposition1.f);

                    if (d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null) {
            movingobjectposition = new MovingObjectPosition(entity);
        }

        float f2;

        if (movingobjectposition != null) {
            if (movingobjectposition.g != null) {
                if (movingobjectposition.g.a(this.b, 4)) {
                    this.world.a(this, "random.drr", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                    this.q();
                } else {
                    this.motX *= -0.10000000149011612D;
                    this.motY *= -0.10000000149011612D;
                    this.motZ *= -0.10000000149011612D;
                    this.yaw += 180.0F;
                    this.lastYaw += 180.0F;
                    this.am = 0;
                }
            } else {
                this.c = movingobjectposition.b;
                this.d = movingobjectposition.c;
                this.e = movingobjectposition.d;
                this.f = this.world.getTypeId(this.c, this.d, this.e);
                this.motX = (double) ((float) (movingobjectposition.f.a - this.locX));
                this.motY = (double) ((float) (movingobjectposition.f.b - this.locY));
                this.motZ = (double) ((float) (movingobjectposition.f.c - this.locZ));
                f2 = MathHelper.a(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
                this.locX -= this.motX / (double) f2 * 0.05000000074505806D;
                this.locY -= this.motY / (double) f2 * 0.05000000074505806D;
                this.locZ -= this.motZ / (double) f2 * 0.05000000074505806D;
                this.world.a(this, "random.drr", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.ak = true;
                this.a = 7;
            }
        }

        this.locX += this.motX;
        this.locY += this.motY;
        this.locZ += this.motZ;
        f2 = MathHelper.a(this.motX * this.motX + this.motZ * this.motZ);
        this.yaw = (float) (Math.atan2(this.motX, this.motZ) * 180.0D / 3.1415927410125732D);

        for (this.pitch = (float) (Math.atan2(this.motY, (double) f2) * 180.0D / 3.1415927410125732D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F) {
            ;
        }

        while (this.pitch - this.lastPitch >= 180.0F) {
            this.lastPitch += 360.0F;
        }

        while (this.yaw - this.lastYaw < -180.0F) {
            this.lastYaw -= 360.0F;
        }

        while (this.yaw - this.lastYaw >= 180.0F) {
            this.lastYaw += 360.0F;
        }

        this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
        this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
        float f3 = 0.99F;

        f1 = 0.03F;
        if (this.v()) {
            for (int k = 0; k < 4; ++k) {
                float f4 = 0.25F;

                this.world.a("bubble", this.locX - this.motX * (double) f4, this.locY - this.motY * (double) f4, this.locZ - this.motZ * (double) f4, this.motX, this.motY, this.motZ);
            }

            f3 = 0.8F;
        }

        this.motX *= (double) f3;
        this.motY *= (double) f3;
        this.motZ *= (double) f3;
        this.motY -= (double) f1;
        this.a(this.locX, this.locY, this.locZ);
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.c);
        nbttagcompound.a("yTile", (short) this.d);
        nbttagcompound.a("zTile", (short) this.e);
        nbttagcompound.a("inTile", (byte) this.f);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.ak ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.c = nbttagcompound.c("xTile");
        this.d = nbttagcompound.c("yTile");
        this.e = nbttagcompound.c("zTile");
        this.f = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.ak = nbttagcompound.b("inGround") == 1;
    }

    public void b(EntityHuman entityhuman) {
        if (!this.world.isStatic) {
            if (this.ak && this.b == entityhuman && this.a <= 0 && entityhuman.inventory.a(new ItemStack(Item.ARROW, 1))) {
                this.world.a(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.c(this, 1);
                this.q();
            }
        }
    }
}
