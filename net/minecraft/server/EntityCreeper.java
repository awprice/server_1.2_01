package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    int a;
    int b;

    public EntityCreeper(World world) {
        super(world);
        this.texture = "/mob/creeper.png";
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void b_() {
        this.b = this.a;
        if (this.world.isStatic) {
            int i = this.K();

            if (i > 0 && this.a == 0) {
                this.world.a(this, "random.fuse", 1.0F, 0.5F);
            }

            this.a += i;
            if (this.a < 0) {
                this.a = 0;
            }

            if (this.a >= 30) {
                this.a = 30;
            }
        }

        super.b_();
    }

    protected String f() {
        return "mob.creeper";
    }

    protected String g() {
        return "mob.creeperdeath";
    }

    public void f(Entity entity) {
        super.f(entity);
        if (entity instanceof EntitySkeleton) {
            this.a(Item.GOLD_RECORD.id + this.random.nextInt(2), 1);
        }
    }

    protected void a(Entity entity, float f) {
        int i = this.K();

        if ((i > 0 || f >= 3.0F) && (i <= 0 || f >= 7.0F)) {
            this.a(-1);
            --this.a;
            if (this.a < 0) {
                this.a = 0;
            }
        } else {
            if (this.a == 0) {
                this.world.a(this, "random.fuse", 1.0F, 0.5F);
            }

            this.a(1);
            ++this.a;
            if (this.a >= 30) {
                this.world.a(this, this.locX, this.locY, this.locZ, 3.0F);
                this.q();
            }

            this.e = true;
        }
    }

    protected int h() {
        return Item.SULPHUR.id;
    }

    private int K() {
        return this.datawatcher.a(16);
    }

    private void a(int i) {
        this.datawatcher.b(16, Byte.valueOf((byte) i));
    }
}
