package net.inkyquill.equestria.flight.math;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Region {
    public Location Pos1;
    public Location Pos2;

    public Region(Location loc1, Location loc2) {
        Pos1 = loc1;
        Pos2 = loc2;
    }

    public Region() {
        Pos1 = null;
        Pos2 = null;
    }

    public static Region fromString(String s) {
        if (!s.contains(":"))
            return null;

        String[] st = s.split(":");
        if (s.length() < 3)
            return null;

        World w = Bukkit.getWorld(st[0]);
        if (w == null)
            return null;

        if (!st[1].contains(",") || !st[2].contains(","))
            return null;

        String[] l1 = st[1].split(",");
        String[] l2 = st[2].split(",");

        if (l1.length < 3 || l2.length < 3)
            return null;

        Location loc1 = new Location(w, Integer.parseInt(l1[0]), Integer.parseInt(l1[1]), Integer.parseInt(l1[2]));
        Location loc2 = new Location(w, Integer.parseInt(l2[0]), Integer.parseInt(l2[1]), Integer.parseInt(l2[2]));

        return new Region(loc1, loc2);
    }

    public boolean inRegion(Player p) {
        if (!isCorrect()) return false;
        if (p.getLocation().getWorld() != Pos1.getWorld()) return false;

        double maxX = Math.max(Pos1.getX(), Pos2.getX());
        double minX = Math.min(Pos1.getX(), Pos2.getX());
        double maxY = Math.max(Pos1.getY(), Pos2.getY());
        double minY = Math.min(Pos1.getY(), Pos2.getY());
        double maxZ = Math.max(Pos1.getZ(), Pos2.getZ());
        double minZ = Math.min(Pos1.getZ(), Pos2.getZ());

        Location pl = p.getLocation();
        return pl.getX() >= minX && pl.getX() <= maxX && pl.getY() >= minY && pl.getY() <= maxY && pl.getZ() >= minZ && pl.getZ() <= maxZ;
    }

    @Override
    public String toString() {
        return Pos1.getWorld().getName()
                + ":"
                + Pos1.getBlockX() + "," + Pos1.getBlockY() + "," + Pos1.getBlockZ()
                + ":"
                + Pos2.getBlockX() + "," + Pos2.getBlockY() + "," + Pos2.getBlockZ();
    }

    public String toReadable() {
        return "(" + Pos1.getWorld().getName() + ")" + Pos1.getBlockX() + "/" + Pos1.getBlockY() + "/" + Pos1.getBlockZ()
                + " - "
                + Pos2.getBlockX() + "/" + Pos2.getBlockY() + "/" + Pos2.getBlockZ();
    }

    public boolean isCorrect() {
        return Pos1 != null && Pos2 != null && Pos1.getWorld() == Pos2.getWorld();
    }

}
