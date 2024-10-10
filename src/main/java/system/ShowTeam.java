package system;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import system.ChatType;
import system.TeamSystem;

import java.util.Collections;

public class ShowTeam extends BukkitRunnable {
    private int num = 30;
    private String team = "";

    public ShowTeam(){
    }

    @Deprecated
    public void run() {
        if (this.num != -1) {
            if (this.num != 0) {
                if (this.num % 3 == 0) {
                    this.team = ChatColor.RED + "RED";
                } else if (this.num % 3 == 1) {
                    this.team = ChatColor.WHITE + "Spectate";
                } else if (this.num % 3 == 2) {
                    this.team = ChatColor.BLUE + "BLUE";
                }
                for(Player player : PlayerData.allPlayer){
                    player.sendTitle(team,null,0,3*20,0);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
                }

                --this.num;
            } else {
                int member;
                for(member = 0; member < 3; ++member) {
                    Collections.shuffle(PlayerData.allPlayer);
                }

                member = PlayerData.allPlayer.size() / 2;

                for(int i = 0; i < PlayerData.allPlayer.size(); ++i) {
                    Player player = PlayerData.allPlayer.get(i);
                    PlayerData.chatType.put(player.getUniqueId(), ChatType.all);
                    player.sendMessage(ChatColor.GOLD + "[System]" + ChatColor.YELLOW + "팀 설정 변경으로 인해 채팅 채널이 [ALL]으로 변경되었습니다.");
                    if (i < member) {
                        TeamSystem.red.addPlayer(player);
                        player.sendTitle(ChatColor.RED+"RED","is your team",0,3*20,0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 1.0F);
                    } else if (i < member * 2) {
                        TeamSystem.blue.addPlayer(player);
                        player.sendTitle(ChatColor.BLUE+"BLUE","is your team",0,3*20,0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 1.0F);
                    } else {
                        if (PlayerData.hasTeam(player)) {
                            if (TeamSystem.red.getPlayers().contains(player)) {
                                TeamSystem.red.removePlayer(player);
                            } else if (TeamSystem.blue.getPlayers().contains(player)) {
                                TeamSystem.blue.removePlayer(player);
                            }
                        }
                        player.sendTitle("SPECTATOR","is your team",0,3*20,0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 1.0F);
                    }
                }
                --this.num;
            }
        }
    }
}
