package net.minecraft.server;

import java.util.List;

public class EntityPigZombie extends EntityZombie {

    private int a = 0;
    private int b = 0;
    private static final ItemStack f = new ItemStack(Item.GOLD_SWORD, 1);

    public EntityPigZombie(World world) {
        super(world);
        this.texture = "/mob/pigzombie.png";
        this.bC = 0.5F;
        this.c = 5;
        this.ae = true;
    }

    public void b_() {
        this.bC = this.d != null ? 0.95F : 0.5F;
        if (this.b > 0 && --this.b == 0) {
            this.world.a(this, "mob.zombiepig.zpigangry", this.i() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.b_();
    }

    public boolean b() {
        return this.world.k > 0 && this.world.a(this.boundingBox) && this.world.a((Entity) this, this.boundingBox).size() == 0 && !this.world.b(this.boundingBox);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Anger", (short) this.a);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.c("Anger");
    }

    protected Entity l() {
        return this.a == 0 ? null : super.l();
    }

    public void o() {
        super.o();
    }

    public boolean a(Entity entity, int i) {
        if (entity instanceof EntityHuman) {
            List list = this.world.b((Entity) this, this.boundingBox.b(32.0D, 32.0D, 32.0D));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1 instanceof EntityPigZombie) {
                    EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;

                    entitypigzombie.g(entity);
                }
            }

            this.g(entity);
        }

        return super.a(entity, i);
    }

    private void g(Entity entity) {
        this.d = entity;
        this.a = 400 + this.random.nextInt(400);
        this.b = this.random.nextInt(40);
    }

    protected String e() {
        return "mob.zombiepig.zpig";
    }

    protected String f() {
        return "mob.zombiepig.zpighurt";
    }

    protected String g() {
        return "mob.zombiepig.zpigdeath";
    }

    protected int h() {
        return Item.GRILLED_PORK.id;
    }
}
