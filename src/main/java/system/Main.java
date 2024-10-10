package system;

import data.MapData;
import ingame.ScoreSystem;
import listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;


    public static Main getInstance(){
        return instance;
    }

    public void onEnable(){
        instance = this;
        this.getCommand("h").setExecutor(new Commands());
        MapData.loadConfig();
        MapData.loadSettings();
        ScoreSystem.boardSetting();
        TeamSystem.teamSetting();
        MapData.redTargetLoc.add(MapData.blue_left_tower);
        MapData.redTargetLoc.add(MapData.blue_right_tower);
        MapData.redTargetLoc.add(MapData.blue_heart);
        MapData.blueTargetLoc.add(MapData.red_left_tower);
        MapData.blueTargetLoc.add(MapData.red_right_tower);
        MapData.blueTargetLoc.add(MapData.red_heart);
        Bukkit.getLogger().info(ChatColor.GOLD+"[Heart of Fortress]"+ChatColor.BLUE+"Plugin Enable");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[Heart of Fortress]"+ChatColor.BLUE+"Plugin Enable");
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new ConnectListener(), this);
        this.getServer().getPluginManager().registerEvents(new AbilityListener(), this);
        this.getServer().getPluginManager().registerEvents(new GuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new SystemListener(), this);
    }
}
