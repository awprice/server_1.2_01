package net.minecraft.server;

import java.util.List;

public class EntityMinecart extends Entity implements IInventory {

    private ItemStack[] al;
    public int a;
    public int b;
    public int c;
    private boolean am;
    public int d;
    public int e;
    public double f;
    public double ak;
    private static final int[][][] an = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int ao;
    private double ap;
    private double aq;
    private double ar;
    private double as;
    private double at;

    public EntityMinecart(World world) {
        super(world);
        this.al = new ItemStack[36];
        this.a = 0;
        this.b = 0;
        this.c = 1;
        this.am = false;
        this.i = true;
        this.a(0.98F, 0.7F);
        this.height = this.width / 2.0F;
        this.M = false;
    }

    protected void a() {}

    public AxisAlignedBB d(Entity entity) {
        return entity.boundingBox;
    }

    public AxisAlignedBB u() {
        return null;
    }

    public boolean z() {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2, int i) {
        this(world);
        this.a(d0, d1 + (double) this.height, d2);
        this.motX = 0.0D;
        this.motY = 0.0D;
        this.motZ = 0.0D;
        this.lastX = d0;
        this.lastY = d1;
        this.lastZ = d2;
        this.d = i;
    }

    public double k() {
        return (double) this.width * 0.0D - 0.30000001192092896D;
    }

    public boolean a(Entity entity, int i) {
        if (!this.world.isStatic && !this.dead) {
            this.c = -this.c;
            this.b = 10;
            this.y();
            this.a += i * 10;
            if (this.a > 40) {
                this.a(Item.MINECART.id, 1, 0.0F);
                if (this.d == 1) {
                    this.a(Block.CHEST.id, 1, 0.0F);
                } else if (this.d == 2) {
                    this.a(Block.FURNACE.id, 1, 0.0F);
                }

                this.q();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean c_() {
        return !this.dead;
    }

    public void q() {
        for (int i = 0; i < this.h_(); ++i) {
            ItemStack itemstack = this.a(i);

            if (itemstack != null) {
                float f = this.random.nextFloat() * 0.8F + 0.1F;
                float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                while (itemstack.count > 0) {
                    int j = this.random.nextInt(21) + 10;

                    if (j > itemstack.count) {
                        j = itemstack.count;
                    }

                    itemstack.count -= j;
                    EntityItem entityitem = new EntityItem(this.world, this.locX + (double) f, this.locY + (double) f1, this.locZ + (double) f2, new ItemStack(itemstack.id, j, itemstack.h()));
                    float f3 = 0.05F;

                    entityitem.motX = (double) ((float) this.random.nextGaussian() * f3);
                    entityitem.motY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                    entityitem.motZ = (double) ((float) this.random.nextGaussian() * f3);
                    this.world.a((Entity) entityitem);
                }
            }
        }

        super.q();
    }

    public void b_() {
        if (this.b > 0) {
            --this.b;
        }

        if (this.a > 0) {
            --this.a;
        }

        double d0;

        if (this.world.isStatic && this.ao > 0) {
            if (this.ao > 0) {
                double d1 = this.locX + (this.ap - this.locX) / (double) this.ao;
                double d2 = this.locY + (this.aq - this.locY) / (double) this.ao;
                double d3 = this.locZ + (this.ar - this.locZ) / (double) this.ao;

                for (d0 = this.as - (double) this.yaw; d0 < -180.0D; d0 += 360.0D) {
                    ;
                }

                while (d0 >= 180.0D) {
                    d0 -= 360.0D;
                }

                this.yaw = (float) ((double) this.yaw + d0 / (double) this.ao);
                this.pitch = (float) ((double) this.pitch + (this.at - (double) this.pitch) / (double) this.ao);
                --this.ao;
                this.a(d1, d2, d3);
                this.b(this.yaw, this.pitch);
            } else {
                this.a(this.locX, this.locY, this.locZ);
                this.b(this.yaw, this.pitch);
            }
        } else {
            this.lastX = this.locX;
            this.lastY = this.locY;
            this.lastZ = this.locZ;
            this.motY -= 0.03999999910593033D;
            int i = MathHelper.b(this.locX);
            int j = MathHelper.b(this.locY);
            int k = MathHelper.b(this.locZ);

            if (this.world.getTypeId(i, j - 1, k) == Block.RAILS.id) {
                --j;
            }

            double d4 = 0.4D;
            boolean flag = false;

            d0 = 0.0078125D;
            if (this.world.getTypeId(i, j, k) == Block.RAILS.id) {
                Vec3D vec3d = this.g(this.locX, this.locY, this.locZ);
                int l = this.world.getData(i, j, k);

                this.locY = (double) j;
                if (l >= 2 && l <= 5) {
                    this.locY = (double) (j + 1);
                }

                if (l == 2) {
                    this.motX -= d0;
                }

                if (l == 3) {
                    this.motX += d0;
                }

                if (l == 4) {
                    this.motZ += d0;
                }

                if (l == 5) {
                    this.motZ -= d0;
                }

                int[][] aint = an[l];
                double d5 = (double) (aint[1][0] - aint[0][0]);
                double d6 = (double) (aint[1][2] - aint[0][2]);
                double d7 = Math.sqrt(d5 * d5 + d6 * d6);
                double d8 = this.motX * d5 + this.motZ * d6;

                if (d8 < 0.0D) {
                    d5 = -d5;
                    d6 = -d6;
                }

                double d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);

                this.motX = d9 * d5 / d7;
                this.motZ = d9 * d6 / d7;
                double d10 = 0.0D;
                double d11 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
                double d12 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
                double d13 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
                double d14 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

                d5 = d13 - d11;
                d6 = d14 - d12;
                double d15;
                double d16;
                double d17;

                if (d5 == 0.0D) {
                    this.locX = (double) i + 0.5D;
                    d10 = this.locZ - (double) k;
                } else if (d6 == 0.0D) {
                    this.locZ = (double) k + 0.5D;
                    d10 = this.locX - (double) i;
                } else {
                    d15 = this.locX - d11;
                    d17 = this.locZ - d12;
                    d16 = (d15 * d5 + d17 * d6) * 2.0D;
                    d10 = d16;
                }

                this.locX = d11 + d5 * d10;
                this.locZ = d12 + d6 * d10;
                this.a(this.locX, this.locY + (double) this.height, this.locZ);
                d15 = this.motX;
                d17 = this.motZ;
                if (this.passenger != null) {
                    d15 *= 0.75D;
                    d17 *= 0.75D;
                }

                if (d15 < -d4) {
                    d15 = -d4;
                }

                if (d15 > d4) {
                    d15 = d4;
                }

                if (d17 < -d4) {
                    d17 = -d4;
                }

                if (d17 > d4) {
                    d17 = d4;
                }

                this.c(d15, 0.0D, d17);
                if (aint[0][1] != 0 && MathHelper.b(this.locX) - i == aint[0][0] && MathHelper.b(this.locZ) - k == aint[0][2]) {
                    this.a(this.locX, this.locY + (double) aint[0][1], this.locZ);
                } else if (aint[1][1] != 0 && MathHelper.b(this.locX) - i == aint[1][0] && MathHelper.b(this.locZ) - k == aint[1][2]) {
                    this.a(this.locX, this.locY + (double) aint[1][1], this.locZ);
                }

                if (this.passenger != null) {
                    this.motX *= 0.996999979019165D;
                    this.motY *= 0.0D;
                    this.motZ *= 0.996999979019165D;
                } else {
                    if (this.d == 2) {
                        d16 = (double) MathHelper.a(this.f * this.f + this.ak * this.ak);
                        if (d16 > 0.01D) {
                            flag = true;
                            this.f /= d16;
                            this.ak /= d16;
                            double d18 = 0.04D;

                            this.motX *= 0.800000011920929D;
                            this.motY *= 0.0D;
                            this.motZ *= 0.800000011920929D;
                            this.motX += this.f * d18;
                            this.motZ += this.ak * d18;
                        } else {
                            this.motX *= 0.8999999761581421D;
                            this.motY *= 0.0D;
                            this.motZ *= 0.8999999761581421D;
                        }
                    }

                    this.motX *= 0.9599999785423279D;
                    this.motY *= 0.0D;
                    this.motZ *= 0.9599999785423279D;
                }

                Vec3D vec3d1 = this.g(this.locX, this.locY, this.locZ);

                if (vec3d1 != null && vec3d != null) {
                    double d19 = (vec3d.b - vec3d1.b) * 0.05D;

                    d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                    if (d9 > 0.0D) {
                        this.motX = this.motX / d9 * (d9 + d19);
                        this.motZ = this.motZ / d9 * (d9 + d19);
                    }

                    this.a(this.locX, vec3d1.b, this.locZ);
                }

                int i1 = MathHelper.b(this.locX);
                int j1 = MathHelper.b(this.locZ);

                if (i1 != i || j1 != k) {
                    d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                    this.motX = d9 * (double) (i1 - i);
                    this.motZ = d9 * (double) (j1 - k);
                }

                if (this.d == 2) {
                    double d20 = (double) MathHelper.a(this.f * this.f + this.ak * this.ak);

                    if (d20 > 0.01D && this.motX * this.motX + this.motZ * this.motZ > 0.0010D) {
                        this.f /= d20;
                        this.ak /= d20;
                        if (this.f * this.motX + this.ak * this.motZ < 0.0D) {
                            this.f = 0.0D;
                            this.ak = 0.0D;
                        } else {
                            this.f = this.motX;
                            this.ak = this.motZ;
                        }
                    }
                }
            } else {
                if (this.motX < -d4) {
                    this.motX = -d4;
                }

                if (this.motX > d4) {
                    this.motX = d4;
                }

                if (this.motZ < -d4) {
                    this.motZ = -d4;
                }

                if (this.motZ > d4) {
                    this.motZ = d4;
                }

                if (this.onGround) {
                    this.motX *= 0.5D;
                    this.motY *= 0.5D;
                    this.motZ *= 0.5D;
                }

                this.c(this.motX, this.motY, this.motZ);
                if (!this.onGround) {
                    this.motX *= 0.949999988079071D;
                    this.motY *= 0.949999988079071D;
                    this.motZ *= 0.949999988079071D;
                }
            }

            this.pitch = 0.0F;
            double d21 = this.lastX - this.locX;
            double d22 = this.lastZ - this.locZ;

            if (d21 * d21 + d22 * d22 > 0.0010D) {
                this.yaw = (float) (Math.atan2(d22, d21) * 180.0D / 3.141592653589793D);
                if (this.am) {
                    this.yaw += 180.0F;
                }
            }

            double d23;

            for (d23 = (double) (this.yaw - this.lastYaw); d23 >= 180.0D; d23 -= 360.0D) {
                ;
            }

            while (d23 < -180.0D) {
                d23 += 360.0D;
            }

            if (d23 < -170.0D || d23 >= 170.0D) {
                this.yaw += 180.0F;
                this.am = !this.am;
            }

            this.b(this.yaw, this.pitch);
            List list = this.world.b((Entity) this, this.boundingBox.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int k1 = 0; k1 < list.size(); ++k1) {
                    Entity entity = (Entity) list.get(k1);

                    if (entity != this.passenger && entity.z() && entity instanceof EntityMinecart) {
                        entity.c((Entity) this);
                    }
                }
            }

            if (this.passenger != null && this.passenger.dead) {
                this.passenger = null;
            }

            if (flag && this.random.nextInt(4) == 0) {
                --this.e;
                if (this.e < 0) {
                    this.f = this.ak = 0.0D;
                }

                this.world.a("largesmoke", this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public Vec3D g(double d0, double d1, double d2) {
        int i = MathHelper.b(d0);
        int j = MathHelper.b(d1);
        int k = MathHelper.b(d2);

        if (this.world.getTypeId(i, j - 1, k) == Block.RAILS.id) {
            --j;
        }

        if (this.world.getTypeId(i, j, k) == Block.RAILS.id) {
            int l = this.world.getData(i, j, k);

            d1 = (double) j;
            if (l >= 2 && l <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = an[l];
            double d3 = 0.0D;
            double d4 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) j + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) j + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i + 0.5D;
                d3 = d2 - (double) k;
            } else if (d12 == 0.0D) {
                d2 = (double) k + 0.5D;
                d3 = d0 - (double) i;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;
                double d15 = (d13 * d10 + d14 * d12) * 2.0D;

                d3 = d15;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return Vec3D.b(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Type", this.d);
        if (this.d == 2) {
            nbttagcompound.a("PushX", this.f);
            nbttagcompound.a("PushZ", this.ak);
            nbttagcompound.a("Fuel", (short) this.e);
        } else if (this.d == 1) {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i = 0; i < this.al.length; ++i) {
                if (this.al[i] != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                    nbttagcompound1.a("Slot", (byte) i);
                    this.al[i].a(nbttagcompound1);
                    nbttaglist.a((NBTBase) nbttagcompound1);
                }
            }

            nbttagcompound.a("Items", (NBTBase) nbttaglist);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("Type");
        if (this.d == 2) {
            this.f = nbttagcompound.g("PushX");
            this.ak = nbttagcompound.g("PushZ");
            this.e = nbttagcompound.c("Fuel");
        } else if (this.d == 1) {
            NBTTagList nbttaglist = nbttagcompound.k("Items");

            this.al = new ItemStack[this.h_()];

            for (int i = 0; i < nbttaglist.b(); ++i) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
                int j = nbttagcompound1.b("Slot") & 255;

                if (j >= 0 && j < this.al.length) {
                    this.al[j] = new ItemStack(nbttagcompound1);
                }
            }
        }
    }

    public void c(Entity entity) {
        if (!this.world.isStatic) {
            if (entity != this.passenger) {
                if (entity instanceof EntityLiving && !(entity instanceof EntityHuman) && this.d == 0 && this.motX * this.motX + this.motZ * this.motZ > 0.01D && this.passenger == null && entity.vehicle == null) {
                    entity.e((Entity) this);
                }

                double d0 = entity.locX - this.locX;
                double d1 = entity.locZ - this.locZ;
                double d2 = d0 * d0 + d1 * d1;

                if (d2 >= 9.999999747378752E-5D) {
                    d2 = (double) MathHelper.a(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.10000000149011612D;
                    d1 *= 0.10000000149011612D;
                    d0 *= (double) (1.0F - this.U);
                    d1 *= (double) (1.0F - this.U);
                    d0 *= 0.5D;
                    d1 *= 0.5D;
                    if (entity instanceof EntityMinecart) {
                        double d4 = entity.motX + this.motX;
                        double d5 = entity.motZ + this.motZ;

                        if (((EntityMinecart) entity).d == 2 && this.d != 2) {
                            this.motX *= 0.20000000298023224D;
                            this.motZ *= 0.20000000298023224D;
                            this.f(entity.motX - d0, 0.0D, entity.motZ - d1);
                            entity.motX *= 0.699999988079071D;
                            entity.motZ *= 0.699999988079071D;
                        } else if (((EntityMinecart) entity).d != 2 && this.d == 2) {
                            entity.motX *= 0.20000000298023224D;
                            entity.motZ *= 0.20000000298023224D;
                            entity.f(this.motX + d0, 0.0D, this.motZ + d1);
                            this.motX *= 0.699999988079071D;
                            this.motZ *= 0.699999988079071D;
                        } else {
                            d4 /= 2.0D;
                            d5 /= 2.0D;
                            this.motX *= 0.20000000298023224D;
                            this.motZ *= 0.20000000298023224D;
                            this.f(d4 - d0, 0.0D, d5 - d1);
                            entity.motX *= 0.20000000298023224D;
                            entity.motZ *= 0.20000000298023224D;
                            entity.f(d4 + d0, 0.0D, d5 + d1);
                        }
                    } else {
                        this.f(-d0, 0.0D, -d1);
                        entity.f(d0 / 4.0D, 0.0D, d1 / 4.0D);
                    }
                }
            }
        }
    }

    public int h_() {
        return 27;
    }

    public ItemStack a(int i) {
        return this.al[i];
    }

    public ItemStack b(int i, int j) {
        if (this.al[i] != null) {
            ItemStack itemstack;

            if (this.al[i].count <= j) {
                itemstack = this.al[i];
                this.al[i] = null;
                return itemstack;
            } else {
                itemstack = this.al[i].a(j);
                if (this.al[i].count == 0) {
                    this.al[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public void a(int i, ItemStack itemstack) {
        this.al[i] = itemstack;
        if (itemstack != null && itemstack.count > this.c()) {
            itemstack.count = this.c();
        }
    }

    public String b() {
        return "Minecart";
    }

    public int c() {
        return 64;
    }

    public void d() {}

    public boolean a(EntityHuman entityhuman) {
        if (this.d == 0) {
            if (this.passenger != null && this.passenger instanceof EntityHuman && this.passenger != entityhuman) {
                return true;
            }

            if (!this.world.isStatic) {
                entityhuman.e(this);
            }
        } else if (this.d == 1) {
            if (!this.world.isStatic) {
                entityhuman.a((IInventory) this);
            }
        } else if (this.d == 2) {
            ItemStack itemstack = entityhuman.inventory.e();

            if (itemstack != null && itemstack.id == Item.COAL.id) {
                if (--itemstack.count == 0) {
                    entityhuman.inventory.a(entityhuman.inventory.c, (ItemStack) null);
                }

                this.e += 1200;
            }

            this.f = this.locX - entityhuman.locX;
            this.ak = this.locZ - entityhuman.locZ;
        }

        return true;
    }

    public boolean a_(EntityHuman entityhuman) {
        return this.dead ? false : entityhuman.b((Entity) this) <= 64.0D;
    }
}
