package ingame;

import data.MapData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

public class RegenBlock {

    public RegenBlock(){}

    public static void addLocation(int x1, int y1, int z1, int x2, int y2, int z2){
        int tmp;
        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        if (y1 > y2) {
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        if (z1 > z2) {
            tmp = z1;
            z1 = z2;
            z2 = tmp;
        }

        for(int x = x1; x <= x2; ++x) {
            for(int y = y1; y <= y2; ++y) {
                for(int z = z1; z <= z2; ++z) {
                    MapData.mineLoc.add(new Location(Bukkit.getServer().getWorld("world"), x, y, z));
                }
            }
        }
    }

    public static void regenEffect(Location loc){
        MapData.world.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 26);
    }

    public static void regenBLock(Location loc){
        Random random = new Random();
        int res = random.nextInt(10000);
        if (res <= 6500) {
            loc.getBlock().setType(Material.STONE);
            regenEffect(loc);
        } else if (res <= 8000) {
            loc.getBlock().setType(Material.COAL_ORE);
            regenEffect(loc);
        } else if (res <= 9000) {
            loc.getBlock().setType(Material.IRON_ORE);
            regenEffect(loc);
        } else if (res <= 9650) {
            loc.getBlock().setType(Material.GOLD_ORE);
            regenEffect(loc);
        } else if (res <= 9900) {
            loc.getBlock().setType(Material.DIAMOND_ORE);
            regenEffect(loc);
        } else{
            loc.getBlock().setType(Material.EMERALD_ORE);
            regenEffect(loc);
        }
    }

}
