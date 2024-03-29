package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class Entity {

    private static int entityCount = 0;
    public int id;
    public double h;
    public boolean i;
    public Entity passenger;
    public Entity vehicle;
    public World world;
    public double lastX;
    public double lastY;
    public double lastZ;
    public double locX;
    public double locY;
    public double locZ;
    public double motX;
    public double motY;
    public double motZ;
    public float yaw;
    public float pitch;
    public float lastYaw;
    public float lastPitch;
    public final AxisAlignedBB boundingBox;
    public boolean onGround;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean dead;
    public float height;
    public float length;
    public float width;
    public float K;
    public float L;
    protected boolean M;
    protected float fallDistance;
    private int b;
    public double O;
    public double P;
    public double Q;
    public float R;
    public float S;
    public boolean T;
    public float U;
    public boolean V;
    protected Random random;
    public int ticksLived;
    public int maxFireTicks;
    public int fireTicks;
    protected int maxAirTicks;
    protected boolean ab;
    public int noDamageTicks;
    public int airTicks;
    private boolean justCreated;
    protected boolean ae;
    protected DataWatcher datawatcher;
    private double d;
    private double e;
    public boolean ag;
    public int chunkX;
    public int ai;
    public int chunkZ;

    public Entity(World world) {
        this.id = entityCount++;
        this.h = 1.0D;
        this.i = false;
        this.boundingBox = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.onGround = false;
        this.D = false;
        this.E = false;
        this.F = true;
        this.dead = false;
        this.height = 0.0F;
        this.length = 0.6F;
        this.width = 1.8F;
        this.K = 0.0F;
        this.L = 0.0F;
        this.M = true;
        this.fallDistance = 0.0F;
        this.b = 1;
        this.R = 0.0F;
        this.S = 0.0F;
        this.T = false;
        this.U = 0.0F;
        this.V = false;
        this.random = new Random();
        this.ticksLived = 0;
        this.maxFireTicks = 1;
        this.fireTicks = 0;
        this.maxAirTicks = 300;
        this.ab = false;
        this.noDamageTicks = 0;
        this.airTicks = 300;
        this.justCreated = true;
        this.ae = false;
        this.datawatcher = new DataWatcher();
        this.ag = false;
        this.world = world;
        this.a(0.0D, 0.0D, 0.0D);
        this.datawatcher.a(0, Byte.valueOf((byte) 0));
        this.a();
    }

    protected abstract void a();

    public DataWatcher p() {
        return this.datawatcher;
    }

    public boolean equals(Object object) {
        return object instanceof Entity ? ((Entity) object).id == this.id : false;
    }

    public int hashCode() {
        return this.id;
    }

    public void q() {
        this.dead = true;
    }

    protected void a(float f, float f1) {
        this.length = f;
        this.width = f1;
    }

    protected void b(float f, float f1) {
        this.yaw = f;
        this.pitch = f1;
    }

    public void a(double d0, double d1, double d2) {
        this.locX = d0;
        this.locY = d1;
        this.locZ = d2;
        float f = this.length / 2.0F;
        float f1 = this.width;

        this.boundingBox.c(d0 - (double) f, d1 - (double) this.height + (double) this.R, d2 - (double) f, d0 + (double) f, d1 - (double) this.height + (double) this.R + (double) f1, d2 + (double) f);
    }

    public void b_() {
        this.r();
    }

    public void r() {
        if (this.vehicle != null && this.vehicle.dead) {
            this.vehicle = null;
        }

        ++this.ticksLived;
        this.K = this.L;
        this.lastX = this.locX;
        this.lastY = this.locY;
        this.lastZ = this.locZ;
        this.lastPitch = this.pitch;
        this.lastYaw = this.yaw;
        if (this.v()) {
            if (!this.ab && !this.justCreated) {
                float f = MathHelper.a(this.motX * this.motX * 0.20000000298023224D + this.motY * this.motY + this.motZ * this.motZ * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.world.a(this, "random.splash", f, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                float f1 = (float) MathHelper.b(this.boundingBox.b);

                int i;
                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.length * 20.0F; ++i) {
                    f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length;
                    f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length;
                    this.world.a("bubble", this.locX + (double) f2, (double) (f1 + 1.0F), this.locZ + (double) f3, this.motX, this.motY - (double) (this.random.nextFloat() * 0.2F), this.motZ);
                }

                for (i = 0; (float) i < 1.0F + this.length * 20.0F; ++i) {
                    f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length;
                    f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.length;
                    this.world.a("splash", this.locX + (double) f2, (double) (f1 + 1.0F), this.locZ + (double) f3, this.motX, this.motY, this.motZ);
                }
            }

            this.fallDistance = 0.0F;
            this.ab = true;
            this.fireTicks = 0;
        } else {
            this.ab = false;
        }

        if (this.world.isStatic) {
            this.fireTicks = 0;
        } else if (this.fireTicks > 0) {
            if (this.ae) {
                this.fireTicks -= 4;
                if (this.fireTicks < 0) {
                    this.fireTicks = 0;
                }
            } else {
                if (this.fireTicks % 20 == 0) {
                    this.a((Entity) null, 1);
                }

                --this.fireTicks;
            }
        }

        if (this.x()) {
            this.s();
        }

        if (this.locY < -64.0D) {
            this.t();
        }

        if (!this.world.isStatic) {
            this.a(0, this.fireTicks > 0);
            this.a(2, this.vehicle != null);
        }

        this.justCreated = false;
    }

    protected void s() {
        if (!this.ae) {
            this.a((Entity) null, 4);
            this.fireTicks = 600;
        }
    }

    protected void t() {
        this.q();
    }

    public boolean b(double d0, double d1, double d2) {
        AxisAlignedBB axisalignedbb = this.boundingBox.c(d0, d1, d2);
        List list = this.world.a(this, axisalignedbb);

        return list.size() > 0 ? false : !this.world.b(axisalignedbb);
    }

    public void c(double d0, double d1, double d2) {
        if (this.T) {
            this.boundingBox.d(d0, d1, d2);
            this.locX = (this.boundingBox.a + this.boundingBox.d) / 2.0D;
            this.locY = this.boundingBox.b + (double) this.height - (double) this.R;
            this.locZ = (this.boundingBox.c + this.boundingBox.f) / 2.0D;
        } else {
            double d3 = this.locX;
            double d4 = this.locZ;
            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            AxisAlignedBB axisalignedbb = this.boundingBox.b();
            boolean flag = this.onGround && this.J();

            if (flag) {
                double d8;

                for (d8 = 0.05D; d0 != 0.0D && this.world.a(this, this.boundingBox.c(d0, -1.0D, 0.0D)).size() == 0; d5 = d0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }
                }

                for (; d2 != 0.0D && this.world.a(this, this.boundingBox.c(0.0D, -1.0D, d2)).size() == 0; d7 = d2) {
                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }
                }
            }

            List list = this.world.a(this, this.boundingBox.a(d0, d1, d2));

            for (int i = 0; i < list.size(); ++i) {
                d1 = ((AxisAlignedBB) list.get(i)).b(this.boundingBox, d1);
            }

            this.boundingBox.d(0.0D, d1, 0.0D);
            if (!this.F && d6 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.onGround || d6 != d1 && d6 < 0.0D;

            int j;

            for (j = 0; j < list.size(); ++j) {
                d0 = ((AxisAlignedBB) list.get(j)).a(this.boundingBox, d0);
            }

            this.boundingBox.d(d0, 0.0D, 0.0D);
            if (!this.F && d5 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (j = 0; j < list.size(); ++j) {
                d2 = ((AxisAlignedBB) list.get(j)).c(this.boundingBox, d2);
            }

            this.boundingBox.d(0.0D, 0.0D, d2);
            if (!this.F && d7 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d9;
            double d10;
            int k;

            if (this.S > 0.0F && flag1 && this.R < 0.05F && (d5 != d0 || d7 != d2)) {
                d9 = d0;
                d10 = d1;
                double d11 = d2;

                d0 = d5;
                d1 = (double) this.S;
                d2 = d7;
                AxisAlignedBB axisalignedbb1 = this.boundingBox.b();

                this.boundingBox.b(axisalignedbb);
                list = this.world.a(this, this.boundingBox.a(d5, d1, d7));

                for (k = 0; k < list.size(); ++k) {
                    d1 = ((AxisAlignedBB) list.get(k)).b(this.boundingBox, d1);
                }

                this.boundingBox.d(0.0D, d1, 0.0D);
                if (!this.F && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d0 = ((AxisAlignedBB) list.get(k)).a(this.boundingBox, d0);
                }

                this.boundingBox.d(d0, 0.0D, 0.0D);
                if (!this.F && d5 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d2 = ((AxisAlignedBB) list.get(k)).c(this.boundingBox, d2);
                }

                this.boundingBox.d(0.0D, 0.0D, d2);
                if (!this.F && d7 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    this.boundingBox.b(axisalignedbb1);
                } else {
                    this.R = (float) ((double) this.R + 0.5D);
                }
            }

            this.locX = (this.boundingBox.a + this.boundingBox.d) / 2.0D;
            this.locY = this.boundingBox.b + (double) this.height - (double) this.R;
            this.locZ = (this.boundingBox.c + this.boundingBox.f) / 2.0D;
            this.B = d5 != d0 || d7 != d2;
            this.C = d6 != d1;
            this.onGround = d6 != d1 && d6 < 0.0D;
            this.D = this.B || this.C;
            this.a(d1, this.onGround);
            if (d5 != d0) {
                this.motX = 0.0D;
            }

            if (d6 != d1) {
                this.motY = 0.0D;
            }

            if (d7 != d2) {
                this.motZ = 0.0D;
            }

            d9 = this.locX - d3;
            d10 = this.locZ - d4;
            int l;
            int i1;
            int j1;

            if (this.M && !flag) {
                this.L = (float) ((double) this.L + (double) MathHelper.a(d9 * d9 + d10 * d10) * 0.6D);
                l = MathHelper.b(this.locX);
                i1 = MathHelper.b(this.locY - 0.20000000298023224D - (double) this.height);
                j1 = MathHelper.b(this.locZ);
                k = this.world.getTypeId(l, i1, j1);
                if (this.L > (float) this.b && k > 0) {
                    ++this.b;
                    StepSound stepsound = Block.byId[k].stepSound;

                    if (this.world.getTypeId(l, i1 + 1, j1) == Block.SNOW.id) {
                        stepsound = Block.SNOW.stepSound;
                        this.world.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    } else if (!Block.byId[k].material.isLiquid()) {
                        this.world.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    }

                    Block.byId[k].b(this.world, l, i1, j1, this);
                }
            }

            l = MathHelper.b(this.boundingBox.a);
            i1 = MathHelper.b(this.boundingBox.b);
            j1 = MathHelper.b(this.boundingBox.c);
            k = MathHelper.b(this.boundingBox.d);
            int k1 = MathHelper.b(this.boundingBox.e);
            int l1 = MathHelper.b(this.boundingBox.f);

            if (this.world.a(l, i1, j1, k, k1, l1)) {
                for (int i2 = l; i2 <= k; ++i2) {
                    for (int j2 = i1; j2 <= k1; ++j2) {
                        for (int k2 = j1; k2 <= l1; ++k2) {
                            int l2 = this.world.getTypeId(i2, j2, k2);

                            if (l2 > 0) {
                                Block.byId[l2].a(this.world, i2, j2, k2, this);
                            }
                        }
                    }
                }
            }

            this.R *= 0.4F;
            boolean flag2 = this.v();

            if (this.world.c(this.boundingBox)) {
                this.b(1);
                if (!flag2) {
                    ++this.fireTicks;
                    if (this.fireTicks == 0) {
                        this.fireTicks = 300;
                    }
                }
            } else if (this.fireTicks <= 0) {
                this.fireTicks = -this.maxFireTicks;
            }

            if (flag2 && this.fireTicks > 0) {
                this.world.a(this, "random.fizz", 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                this.fireTicks = -this.maxFireTicks;
            }
        }
    }

    protected void a(double d0, boolean flag) {
        if (flag) {
            if (this.fallDistance > 0.0F) {
                this.a(this.fallDistance);
                this.fallDistance = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.fallDistance = (float) ((double) this.fallDistance - d0);
        }
    }

    public AxisAlignedBB u() {
        return null;
    }

    protected void b(int i) {
        if (!this.ae) {
            this.a((Entity) null, i);
        }
    }

    protected void a(float f) {}

    public boolean v() {
        return this.world.a(this.boundingBox.b(0.0D, -0.4000000059604645D, 0.0D), Material.WATER, this);
    }

    public boolean a(Material material) {
        double d0 = this.locY + (double) this.w();
        int i = MathHelper.b(this.locX);
        int j = MathHelper.d((float) MathHelper.b(d0));
        int k = MathHelper.b(this.locZ);
        int l = this.world.getTypeId(i, j, k);

        if (l != 0 && Block.byId[l].material == material) {
            float f = BlockFluids.c(this.world.getData(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float w() {
        return 0.0F;
    }

    public boolean x() {
        return this.world.a(this.boundingBox.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.LAVA);
    }

    public void a(float f, float f1, float f2) {
        float f3 = MathHelper.c(f * f + f1 * f1);

        if (f3 >= 0.01F) {
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = MathHelper.a(this.yaw * 3.1415927F / 180.0F);
            float f5 = MathHelper.b(this.yaw * 3.1415927F / 180.0F);

            this.motX += (double) (f * f5 - f1 * f4);
            this.motZ += (double) (f1 * f5 + f * f4);
        }
    }

    public float b(float f) {
        int i = MathHelper.b(this.locX);
        double d0 = (this.boundingBox.e - this.boundingBox.b) * 0.66D;
        int j = MathHelper.b(this.locY - (double) this.height + d0);
        int k = MathHelper.b(this.locZ);

        return this.world.a(MathHelper.b(this.boundingBox.a), MathHelper.b(this.boundingBox.b), MathHelper.b(this.boundingBox.c), MathHelper.b(this.boundingBox.d), MathHelper.b(this.boundingBox.e), MathHelper.b(this.boundingBox.f)) ? this.world.l(i, j, k) : 0.0F;
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.lastX = this.locX = d0;
        this.lastY = this.locY = d1;
        this.lastZ = this.locZ = d2;
        this.lastYaw = this.yaw = f;
        this.lastPitch = this.pitch = f1;
        this.R = 0.0F;
        double d3 = (double) (this.lastYaw - f);

        if (d3 < -180.0D) {
            this.lastYaw += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.lastYaw -= 360.0F;
        }

        this.a(this.locX, this.locY, this.locZ);
        this.b(f, f1);
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        this.O = this.lastX = this.locX = d0;
        this.P = this.lastY = this.locY = d1 + (double) this.height;
        this.Q = this.lastZ = this.locZ = d2;
        this.yaw = f;
        this.pitch = f1;
        this.a(this.locX, this.locY, this.locZ);
    }

    public float a(Entity entity) {
        float f = (float) (this.locX - entity.locX);
        float f1 = (float) (this.locY - entity.locY);
        float f2 = (float) (this.locZ - entity.locZ);

        return MathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double d(double d0, double d1, double d2) {
        double d3 = this.locX - d0;
        double d4 = this.locY - d1;
        double d5 = this.locZ - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.locX - d0;
        double d4 = this.locY - d1;
        double d5 = this.locZ - d2;

        return (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double b(Entity entity) {
        double d0 = this.locX - entity.locX;
        double d1 = this.locY - entity.locY;
        double d2 = this.locZ - entity.locZ;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void b(EntityHuman entityhuman) {}

    public void c(Entity entity) {
        if (entity.passenger != this && entity.vehicle != this) {
            double d0 = entity.locX - this.locX;
            double d1 = entity.locZ - this.locZ;
            double d2 = MathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.U);
                d1 *= (double) (1.0F - this.U);
                this.f(-d0, 0.0D, -d1);
                entity.f(d0, 0.0D, d1);
            }
        }
    }

    public void f(double d0, double d1, double d2) {
        this.motX += d0;
        this.motY += d1;
        this.motZ += d2;
    }

    protected void y() {
        this.E = true;
    }

    public boolean a(Entity entity, int i) {
        this.y();
        return false;
    }

    public boolean c_() {
        return false;
    }

    public boolean z() {
        return false;
    }

    public void b(Entity entity, int i) {}

    public boolean c(NBTTagCompound nbttagcompound) {
        String s = this.A();

        if (!this.dead && s != null) {
            nbttagcompound.a("id", s);
            this.d(nbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Pos", (NBTBase) this.a(new double[] { this.locX, this.locY, this.locZ}));
        nbttagcompound.a("Motion", (NBTBase) this.a(new double[] { this.motX, this.motY, this.motZ}));
        nbttagcompound.a("Rotation", (NBTBase) this.a(new float[] { this.yaw, this.pitch}));
        nbttagcompound.a("FallDistance", this.fallDistance);
        nbttagcompound.a("Fire", (short) this.fireTicks);
        nbttagcompound.a("Air", (short) this.airTicks);
        nbttagcompound.a("OnGround", this.onGround);
        this.a(nbttagcompound);
    }

    public void e(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.k("Pos");
        NBTTagList nbttaglist1 = nbttagcompound.k("Motion");
        NBTTagList nbttaglist2 = nbttagcompound.k("Rotation");

        this.a(0.0D, 0.0D, 0.0D);
        this.motX = ((NBTTagDouble) nbttaglist1.a(0)).a;
        this.motY = ((NBTTagDouble) nbttaglist1.a(1)).a;
        this.motZ = ((NBTTagDouble) nbttaglist1.a(2)).a;
        this.lastX = this.O = this.locX = ((NBTTagDouble) nbttaglist.a(0)).a;
        this.lastY = this.P = this.locY = ((NBTTagDouble) nbttaglist.a(1)).a;
        this.lastZ = this.Q = this.locZ = ((NBTTagDouble) nbttaglist.a(2)).a;
        this.lastYaw = this.yaw = ((NBTTagFloat) nbttaglist2.a(0)).a;
        this.lastPitch = this.pitch = ((NBTTagFloat) nbttaglist2.a(1)).a;
        this.fallDistance = nbttagcompound.f("FallDistance");
        this.fireTicks = nbttagcompound.c("Fire");
        this.airTicks = nbttagcompound.c("Air");
        this.onGround = nbttagcompound.l("OnGround");
        this.a(this.locX, this.locY, this.locZ);
        this.b(nbttagcompound);
    }

    protected final String A() {
        return EntityTypes.b(this);
    }

    protected abstract void b(NBTTagCompound nbttagcompound);

    protected abstract void a(NBTTagCompound nbttagcompound);

    protected NBTTagList a(double... adouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble1 = adouble;
        int i = adouble.length;

        for (int j = 0; j < i; ++j) {
            double d0 = adouble1[j];

            nbttaglist.a((NBTBase) (new NBTTagDouble(d0)));
        }

        return nbttaglist;
    }

    protected NBTTagList a(float... afloat) {
        NBTTagList nbttaglist = new NBTTagList();
        float[] afloat1 = afloat;
        int i = afloat.length;

        for (int j = 0; j < i; ++j) {
            float f = afloat1[j];

            nbttaglist.a((NBTBase) (new NBTTagFloat(f)));
        }

        return nbttaglist;
    }

    public EntityItem a(int i, int j) {
        return this.a(i, j, 0.0F);
    }

    public EntityItem a(int i, int j, float f) {
        return this.a(new ItemStack(i, j, 0), f);
    }

    public EntityItem a(ItemStack itemstack, float f) {
        EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY + (double) f, this.locZ, itemstack);

        entityitem.c = 10;
        this.world.a((Entity) entityitem);
        return entityitem;
    }

    public boolean B() {
        return !this.dead;
    }

    public boolean C() {
        int i = MathHelper.b(this.locX);
        int j = MathHelper.b(this.locY + (double) this.w());
        int k = MathHelper.b(this.locZ);

        return this.world.d(i, j, k);
    }

    public boolean a(EntityHuman entityhuman) {
        return false;
    }

    public AxisAlignedBB d(Entity entity) {
        return null;
    }

    public void D() {
        if (this.vehicle.dead) {
            this.vehicle = null;
        } else {
            this.motX = 0.0D;
            this.motY = 0.0D;
            this.motZ = 0.0D;
            this.b_();
            this.vehicle.E();
            this.e += (double) (this.vehicle.yaw - this.vehicle.lastYaw);

            for (this.d += (double) (this.vehicle.pitch - this.vehicle.lastPitch); this.e >= 180.0D; this.e -= 360.0D) {
                ;
            }

            while (this.e < -180.0D) {
                this.e += 360.0D;
            }

            while (this.d >= 180.0D) {
                this.d -= 360.0D;
            }

            while (this.d < -180.0D) {
                this.d += 360.0D;
            }

            double d0 = this.e * 0.5D;
            double d1 = this.d * 0.5D;
            float f = 10.0F;

            if (d0 > (double) f) {
                d0 = (double) f;
            }

            if (d0 < (double) (-f)) {
                d0 = (double) (-f);
            }

            if (d1 > (double) f) {
                d1 = (double) f;
            }

            if (d1 < (double) (-f)) {
                d1 = (double) (-f);
            }

            this.e -= d0;
            this.d -= d1;
            this.yaw = (float) ((double) this.yaw + d0);
            this.pitch = (float) ((double) this.pitch + d1);
        }
    }

    public void E() {
        this.passenger.a(this.locX, this.locY + this.k() + this.passenger.F(), this.locZ);
    }

    public double F() {
        return (double) this.height;
    }

    public double k() {
        return (double) this.width * 0.75D;
    }

    public void e(Entity entity) {
        this.d = 0.0D;
        this.e = 0.0D;
        if (entity == null) {
            if (this.vehicle != null) {
                this.c(this.vehicle.locX, this.vehicle.boundingBox.b + (double) this.vehicle.width, this.vehicle.locZ, this.yaw, this.pitch);
                this.vehicle.passenger = null;
            }

            this.vehicle = null;
        } else if (this.vehicle == entity) {
            this.vehicle.passenger = null;
            this.vehicle = null;
            this.c(entity.locX, entity.boundingBox.b + (double) entity.width, entity.locZ, this.yaw, this.pitch);
        } else {
            if (this.vehicle != null) {
                this.vehicle.passenger = null;
            }

            if (entity.passenger != null) {
                entity.passenger.vehicle = null;
            }

            this.vehicle = entity;
            entity.passenger = this;
        }
    }

    public Vec3D G() {
        return null;
    }

    public void H() {}

    public ItemStack[] I() {
        return null;
    }

    public boolean J() {
        return this.c(1);
    }

    public void b(boolean flag) {
        this.a(1, flag);
    }

    protected boolean c(int i) {
        return (this.datawatcher.a(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag) {
        byte b0 = this.datawatcher.a(0);

        if (flag) {
            this.datawatcher.b(0, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.datawatcher.b(0, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }
}
