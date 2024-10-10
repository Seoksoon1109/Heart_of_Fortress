package listeners;

import ingame.MoneySystem;
import ingame.ScoreSystem;
import data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import system.ChatType;
import system.Main;

import java.util.UUID;

public class ConnectListener implements Listener {
    String resourcePack = "https://www.dropbox.com/s/2n51lucn808xss3/%EC%9A%94%EC%83%88%EC%9D%98%20%EC%8B%AC%EC%9E%A5%281.14.4%20Texture%29.zip?dl=1";


    @EventHandler @Deprecated
    public void onJoin(@NotNull PlayerJoinEvent event){
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        player.setResourcePack(resourcePack);
        MoneySystem.loadMoney(player);
        player.setScoreboard(ScoreSystem.scoreboard);
        ScoreSystem.setScore(player, PlayerData.scores.get(uuid));
        ScoreSystem.updateBoard();
        if(!PlayerData.chatType.containsKey(uuid)){
            PlayerData.chatType.put(uuid, ChatType.all);
        }
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "[요새의 심장] 전용 리소스팩이 적용되었습니다.");
            }
        },60L);
    }

    @EventHandler @Deprecated
    public void onQuit(@NotNull PlayerQuitEvent event){
        ScoreSystem.updateBoard();
    }
}
