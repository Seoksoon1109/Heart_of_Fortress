package data;

import ingame.RegenBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import system.Main;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class MapData {
    public static FileConfiguration config;
    public static Main main = system.Main.getInstance();
    public static World world;
    public static int towerTime;
    public static int appleTime;
    public static int buffTime;
    public static Location defaultSpawn;
    public static Location redSpawn;
    public static Location blueSpawn;
    public static Location redStart;
    public static Location blueStart;
    public static Location redNpc;
    public static Location blueNpc;
    public static Location red_left_tower;
    public static Location red_left_tower_beacon;
    public static Location red_right_tower;
    public static Location red_right_tower_beacon;
    public static Location red_heart;
    public static Location blue_left_tower;
    public static Location blue_left_tower_beacon;
    public static Location blue_right_tower;
    public static Location blue_right_tower_beacon;
    public static Location blue_heart;
    public static float redNpc_HeadRotation;
    public static float blueNpc_HeadRotation;
    public static ArrayList<Location> chest = new ArrayList<>();
    public static ArrayList<Location> mineLoc = new ArrayList<>();
    public static ArrayList<Location> redTargetLoc = new ArrayList<>();
    public static ArrayList<Location> blueTargetLoc = new ArrayList<>();
    public static boolean onTower;
    public static int redLeft;
    public static int redRight;
    public static int blueLeft;
    public static int blueRight;


    public static void loadConfig(){
        File file = new File("plugins/" +Main.getInstance().getDescription().getName() + "/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        try {
            if (!file.exists()) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(main.getResource("config.yml"))));
                for (Map.Entry<String, Object> entry : config.getValues(true).entrySet()) {
                    main.getConfig().addDefault(entry.getKey(), entry.getValue());
                }
                main.getConfig().options().copyDefaults(true);
                main.saveConfig();
            }
            config.load(file);
        } catch (Exception e) {}
    }

    public static void loadSettings(){
        FileConfiguration config = main.getConfig();
        world = Bukkit.getWorld(config.getString("defaultWorld"));
        towerTime = config.getInt("towerTime");
        appleTime = config.getInt("appleTime");
        buffTime = config.getInt("buff_regen_time");
        defaultSpawn = loadLocation(config, world, "defaultSpawn");

        redSpawn = loadLocation(config, world, "redSpawn");
        blueSpawn = loadLocation(config, world, "blueSpawn");

        redStart = loadLocation(config, world, "redStart");
        blueStart = loadLocation(config, world, "blueStart");

        red_heart = loadLocation(config, world, "red_heart");
        blue_heart = loadLocation(config, world, "blue_heart");

        red_left_tower = loadLocation(config, world, "red_left_tower");
        blue_left_tower = loadLocation(config, world, "blue_left_tower");

        red_left_tower_beacon = loadLocation(config, world, "red_left_tower_beacon");
        blue_left_tower_beacon = loadLocation(config, world, "blue_left_tower_beacon");

        red_right_tower = loadLocation(config, world, "red_right_tower");
        blue_right_tower = loadLocation(config, world, "blue_right_tower");

        red_right_tower_beacon = loadLocation(config, world, "red_right_tower_beacon");
        blue_right_tower_beacon = loadLocation(config, world, "blue_right_tower_beacon");

        redNpc = loadLocation(config, world, "redNpc");
        blueNpc = loadLocation(config, world, "blueNpc");

        redNpc_HeadRotation = loadRotation("redNpc");
        blueNpc_HeadRotation = loadRotation("blueNpc");

        for(int i = 1; i <= 12; ++i) {
            try {
                int x1 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("x1");
                int y1 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("y1");
                int z1 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("z1");
                int x2 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("x2");
                int y2 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("y2");
                int z2 = (Integer)config.getConfigurationSection("RegenBlock").getConfigurationSection("Location_" + i + "").get("z2");
                RegenBlock.addLocation(x1, y1, z1, x2, y2, z2);
            } catch (Exception ignored) {
            }
        }

    }

    public static Location loadLocation(FileConfiguration config, World world, String path){
        return new Location(world,
                config.getConfigurationSection(path).getDouble("x"),
                config.getConfigurationSection(path).getDouble("y"),
                config.getConfigurationSection(path).getDouble("z"),
                (float)config.getConfigurationSection(path).getDouble("yaw"),
                (float)config.getConfigurationSection(path).getDouble("pitch"));
    }

    public static float loadRotation(String path){
        return (float) config.getConfigurationSection(path).getInt("r");
    }
}
