package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ServerConfigurationManager {

    public static Logger a = Logger.getLogger("Minecraft");
    public List b = new ArrayList();
    private MinecraftServer c;
    private PlayerManager d;
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private File i;
    private File j;
    private File k;
    private PlayerNBTManager l;

    public ServerConfigurationManager(MinecraftServer minecraftserver) {
        this.c = minecraftserver;
        this.i = minecraftserver.a("banned-players.txt");
        this.j = minecraftserver.a("banned-ips.txt");
        this.k = minecraftserver.a("ops.txt");
        this.d = new PlayerManager(minecraftserver);
        this.e = minecraftserver.d.a("max-players", 20);
        this.e();
        this.g();
        this.i();
        this.f();
        this.h();
        this.j();
    }

    public void a(WorldServer worldserver) {
        this.l = new PlayerNBTManager(new File(worldserver.t, "players"));
    }

    public int a() {
        return this.d.b();
    }

    public void a(EntityPlayer entityplayer) {
        this.b.add(entityplayer);
        this.l.b(entityplayer);
        this.c.e.A.d((int) entityplayer.locX >> 4, (int) entityplayer.locZ >> 4);

        while (this.c.e.a(entityplayer, entityplayer.boundingBox).size() != 0) {
            entityplayer.a(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
        }

        this.c.e.a(entityplayer);
        this.d.a(entityplayer);
    }

    public void b(EntityPlayer entityplayer) {
        this.d.c(entityplayer);
    }

    public void c(EntityPlayer entityplayer) {
        this.l.a(entityplayer);
        this.c.e.d(entityplayer);
        this.b.remove(entityplayer);
        this.d.b(entityplayer);
    }

    public EntityPlayer a(NetLoginHandler netloginhandler, String s, String s1) {
        if (this.f.contains(s.trim().toLowerCase())) {
            netloginhandler.a("You are banned from this server!");
            return null;
        } else {
            String s2 = netloginhandler.b.b().toString();

            s2 = s2.substring(s2.indexOf("/") + 1);
            s2 = s2.substring(0, s2.indexOf(":"));
            if (this.g.contains(s2)) {
                netloginhandler.a("Your IP address is banned from this server!");
                return null;
            } else if (this.b.size() >= this.e) {
                netloginhandler.a("The server is full!");
                return null;
            } else {
                for (int i = 0; i < this.b.size(); ++i) {
                    EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

                    if (entityplayer.name.equalsIgnoreCase(s)) {
                        entityplayer.a.a("You logged in from another location");
                    }
                }

                return new EntityPlayer(this.c, this.c.e, s, new ItemInWorldManager(this.c.e));
            }
        }
    }

    public EntityPlayer d(EntityPlayer entityplayer) {
        this.c.k.a(entityplayer);
        this.c.k.b(entityplayer);
        this.d.b(entityplayer);
        this.b.remove(entityplayer);
        this.c.e.e(entityplayer);
        EntityPlayer entityplayer1 = new EntityPlayer(this.c, this.c.e, entityplayer.name, new ItemInWorldManager(this.c.e));

        entityplayer1.id = entityplayer.id;
        entityplayer1.a = entityplayer.a;
        this.c.e.A.d((int) entityplayer1.locX >> 4, (int) entityplayer1.locZ >> 4);

        while (this.c.e.a(entityplayer1, entityplayer1.boundingBox).size() != 0) {
            entityplayer1.a(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
        }

        entityplayer1.a.b((Packet) (new Packet9Respawn()));
        entityplayer1.a.a(entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch);
        this.d.a(entityplayer1);
        this.c.e.a(entityplayer1);
        this.b.add(entityplayer1);
        entityplayer1.l();
        return entityplayer1;
    }

    public void b() {
        this.d.a();
    }

    public void a(int i, int j, int k) {
        this.d.a(i, j, k);
    }

    public void a(Packet packet) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            entityplayer.a.b(packet);
        }
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.b.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((EntityPlayer) this.b.get(i)).name;
        }

        return s;
    }

    public void a(String s) {
        this.f.add(s.toLowerCase());
        this.f();
    }

    public void b(String s) {
        this.f.remove(s.toLowerCase());
        this.f();
    }

    private void e() {
        try {
            this.f.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.i));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.f.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ban list: " + exception);
        }
    }

    private void f() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.i, false));
            Iterator iterator = this.f.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ban list: " + exception);
        }
    }

    public void c(String s) {
        this.g.add(s.toLowerCase());
        this.h();
    }

    public void d(String s) {
        this.g.remove(s.toLowerCase());
        this.h();
    }

    private void g() {
        try {
            this.g.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.j));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.g.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }
    }

    private void h() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.j, false));
            Iterator iterator = this.g.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ip ban list: " + exception);
        }
    }

    public void e(String s) {
        this.h.add(s.toLowerCase());
        this.j();
    }

    public void f(String s) {
        this.h.remove(s.toLowerCase());
        this.j();
    }

    private void i() {
        try {
            this.h.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.k));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.h.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load ip ban list: " + exception);
        }
    }

    private void j() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.k, false));
            Iterator iterator = this.h.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save ip ban list: " + exception);
        }
    }

    public boolean g(String s) {
        return this.h.contains(s.trim().toLowerCase());
    }

    public EntityPlayer h(String s) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            if (entityplayer.name.equalsIgnoreCase(s)) {
                return entityplayer;
            }
        }

        return null;
    }

    public void a(String s, String s1) {
        EntityPlayer entityplayer = this.h(s);

        if (entityplayer != null) {
            entityplayer.a.b((Packet) (new Packet3Chat(s1)));
        }
    }

    public void a(double d0, double d1, double d2, double d3, Packet packet) {
        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);
            double d4 = d0 - entityplayer.locX;
            double d5 = d1 - entityplayer.locY;
            double d6 = d2 - entityplayer.locZ;

            if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                entityplayer.a.b(packet);
            }
        }
    }

    public void i(String s) {
        Packet3Chat packet3chat = new Packet3Chat(s);

        for (int i = 0; i < this.b.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) this.b.get(i);

            if (this.g(entityplayer.name)) {
                entityplayer.a.b((Packet) packet3chat);
            }
        }
    }

    public boolean a(String s, Packet packet) {
        EntityPlayer entityplayer = this.h(s);

        if (entityplayer != null) {
            entityplayer.a.b(packet);
            return true;
        } else {
            return false;
        }
    }

    public void d() {
        for (int i = 0; i < this.b.size(); ++i) {
            this.l.a((EntityPlayer) this.b.get(i));
        }
    }

    public void a(int i, int j, int k, TileEntity tileentity) {}
}
