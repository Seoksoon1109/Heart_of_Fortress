package listeners;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import system.ChatType;
import system.TeamSystem;

import java.util.Iterator;
import java.util.Set;

public class ChatListener implements Listener {

    @Deprecated
    @EventHandler
    public void onPlayerChat(@NotNull AsyncPlayerChatEvent event){
        String msg = event.getMessage();
        Player player = event.getPlayer();
        event.setCancelled(true);
        Set<OfflinePlayer> playerRed;
        Iterator<OfflinePlayer> it;
        Player p;
        if ((PlayerData.chatType.get(player.getUniqueId())).equals(ChatType.red)) {
            playerRed = TeamSystem.red.getPlayers();
            it = playerRed.iterator();

            while(it.hasNext()) {
                p = it.next().getPlayer();
                if (p != null) {
                    p.sendMessage(ChatColor.RED + "[RED]" + ChatColor.WHITE + "<" + player.getName() + "> " + msg);
                }
            }
        } else if ((PlayerData.chatType.get(player.getUniqueId())).equals(ChatType.blue)) {
            playerRed = TeamSystem.blue.getPlayers();
            it = playerRed.iterator();

            while(it.hasNext()) {
                p = it.next().getPlayer();
                if (p != null) {
                    p.sendMessage(ChatColor.BLUE + "[BLUE]" + ChatColor.WHITE + "<" + player.getName() + "> " + msg);
                }
            }
        } else {
            for (Player p1 : PlayerData.allPlayer) {
                p1.sendMessage("[ALL]" + ChatColor.WHITE + "<" + player.getName() + "> " + msg);
            }
        }
    }
}
