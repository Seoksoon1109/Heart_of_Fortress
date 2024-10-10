package system;

import data.MapData;
import data.PlayerData;
import data.TimerData;
import ingame.ScoreSystem;
import ingame.StartTimer;
import ingame.TowerTimer;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class GameManager {
    final static ArrayList<LivingEntity> villagers = new ArrayList<>();
    public static boolean isStart = false;

    public GameManager() {
    }

    public static void createNPC(@NotNull Location loc, float rotation) {

    }

    public static void startingGame() {
        String PluginVersion = "";
        String ApiVersion = "";
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("HEART_OF_FORTRESS");
        if (plugin != null) {
            PluginVersion = plugin.getDescription().getVersion();
            ApiVersion = plugin.getDescription().getAPIVersion();
        }

        String msg1 = "" + ChatColor.GREEN + "=-=-=-=-=-=-=" + ChatColor.GOLD + "[Heart of Fortress]" + ChatColor.GREEN + "=-=-=-=-=-=-=";
        String msg2 = "" + ChatColor.GREEN + "API version : " + ChatColor.BLUE + ApiVersion;
        String msg3 = "" + ChatColor.GREEN + "Plugin version : " + ChatColor.BLUE + PluginVersion + "";
        String msg4 = "" + ChatColor.GREEN + "제작자 : hyun_21(Seoksoon)";
        String msg5 = "" + ChatColor.GREEN + "제작자 이메일 : hyun_21@naver.com";
        String msg6 = "" + ChatColor.YELLOW + "오류제보 및 기타문의사항은 이메일로 부탁드립니다.";
        String msg7 = "" + ChatColor.GREEN + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
        String msg = "" + msg1 + "\n" + msg2 + "\n" + msg3 + "\n" + msg4 + "\n" + msg5 + "\n" + msg6 + "\n" + msg7 + "";
        Bukkit.broadcastMessage(msg);
        StartTimer start = new StartTimer();
        start.runTaskTimer(Main.getInstance(), 60L, 20L);
    }

    public static void startGame() {
        PlayerData.allPlayer.addAll(Bukkit.getOnlinePlayers());
        resetGame();
        isStart = true;
        createNPC(MapData.redNpc, MapData.redNpc_HeadRotation);
        createNPC(MapData.blueNpc, MapData.blueNpc_HeadRotation);
        Bukkit.broadcastMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "게임이 시작되었습니다.");
        TowerTimer towerTimer = new TowerTimer();
        towerTimer.runTaskTimer(Main.getInstance(), 0L, 20L);

        for(Player player : PlayerData.allPlayer){
            if(PlayerData.hasTeam(player)){
                if(PlayerData.isRed(player)) player.setBedSpawnLocation(MapData.redSpawn);
                else if(PlayerData.isBlue(player)) player.setBedSpawnLocation(MapData.blueSpawn);
                UUID uuid = player.getUniqueId();
                player.setGameMode(GameMode.SURVIVAL);
                player.setHealth(0.0D);
                player.setExp(0.0F);
            }
            else{
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(MapData.defaultSpawn);
            }
        }
    }

    public static void endGame(String team) {
        for(Player p : Bukkit.getOnlinePlayers()){
            p.setBedSpawnLocation(MapData.defaultSpawn);
        }
        if (team.equalsIgnoreCase("red")) {
            team = ChatColor.RED+"레드";
            sendWinner(ChatColor.RED+"RED Team");
        } else if (team.equalsIgnoreCase("blue")) {
            team = ChatColor.BLUE+"블루";
            sendWinner(ChatColor.BLUE+"BLUE Team");
        }
        String msg1 = "" + ChatColor.GOLD + "[요새의 심장]" + team + ChatColor.RED + "팀 요새의 심장이 파괴되어 게임이 종료되었습니다.";
        String msg2 = "" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.AQUA + "승자는 [" + team + ChatColor.AQUA + "]팀 입니다!";
        Bukkit.broadcastMessage(msg1);
        Bukkit.broadcastMessage(msg2);
    }

    public static void sendWinner(String team){
        for(Player player : PlayerData.allPlayer){
            player.sendTitle(team,"is the Winner",0,3*20,0);
        }
    }

    public static void resetGame() {
        for(Entity e : MapData.world.getEntities()){
            e.remove();
        }
        MapData.blue_left_tower.getBlock().setType(Material.IRON_BLOCK);
        MapData.blue_left_tower_beacon.getBlock().setType(Material.BEACON);
        MapData.blue_right_tower.getBlock().setType(Material.IRON_BLOCK);
        MapData.blue_right_tower_beacon.getBlock().setType(Material.BEACON);
        MapData.blue_heart.getBlock().setType(Material.DIAMOND_BLOCK);
        MapData.red_left_tower.getBlock().setType(Material.IRON_BLOCK);
        MapData.red_left_tower_beacon.getBlock().setType(Material.BEACON);
        MapData.red_right_tower.getBlock().setType(Material.IRON_BLOCK);
        MapData.red_right_tower_beacon.getBlock().setType(Material.BEACON);
        MapData.red_heart.getBlock().setType(Material.DIAMOND_BLOCK);
        Bukkit.getServer().getScheduler().cancelTask(TimerData.towerID);
        Bukkit.getServer().getScheduler().cancelTask(TimerData.restartID);
        TimerData.tower.removeAll();
        TimerData.restart.removeAll();
        isStart = false;
        MapData.onTower = false;
        MapData.redLeft = 1;
        MapData.redRight = 1;
        MapData.blueLeft = 1;
        MapData.blueRight = 1;
        ScoreSystem.resetScore();
        MapData.chest.clear();
        PlayerData.allPlayer.clear();

        for(Location location : MapData.mineLoc){
            location.getBlock().setType(Material.STONE);
        }
        for(Location location : MapData.chest){
            Chest chest = (Chest) location.getBlock().getState();
            chest.getBlockInventory().clear();
            location.getBlock().setType(Material.AIR);
        }
    }
}
