//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ingame;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import system.TeamSystem;
import java.util.Objects;
import java.util.UUID;


public class MoneySystem {
    public static String point10 = "" + ChatColor.GRAY + 10 + " Point";
    public static String point100 = "" + ChatColor.GOLD + 100 + " Point";
    public static String point1000 = "" + ChatColor.AQUA + 1000 + " Point";

    public MoneySystem() {
    }

    public static void useMoney(@NotNull Player player, int money, int amount) {
        int m = player.getLevel();
        player.setLevel(m + money * amount);
        saveMoney(player);
        player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "수표를 모두 사용하여 " + ChatColor.GREEN + money * amount + "Point " + ChatColor.YELLOW + "를 획득했습니다.");
    }


    public static void giveTowerMoney(Team team){
        for(Player player : PlayerData.allPlayer){
            if(TeamSystem.getTeam(player).equals(team)){
                UUID uuid = player.getUniqueId();
                int money = PlayerData.moneyData.get(uuid);
                PlayerData.moneyData.put(uuid, money+500);
                if(player.isOnline()){
                    MoneySystem.loadMoney(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
                    player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.GREEN + "팀원이 상대팀 타워를 파괴하여 500 Point를 획득하였습니다!");
                }
            }
        }
    }


    public static void saveMoney(@NotNull Player player) {
        int money = player.getLevel();
        PlayerData.moneyData.put(player.getUniqueId(), money);
    }

    public static void loadMoney(@NotNull Player player) {
        int money = PlayerData.moneyData.get(player.getUniqueId());
        player.setLevel(money);
    }

    public static void withdrawMoney(@NotNull Player player, int money, int amount) {
        int m = player.getLevel();
        if (money == 10 || money == 100 || money == 1000) {
            if (money * amount > m) {
                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "잔액이 부족합니다.");
            } else {
                ItemStack check = new ItemStack(Material.PAPER, amount);
                ItemMeta checkM = check.getItemMeta();
                if (money == 10) {
                    checkM.setDisplayName(point10);
                } else if (money == 100) {
                    checkM.setDisplayName(point100);
                } else{
                    checkM.setDisplayName(point1000);
                }

                check.setItemMeta(checkM);
                player.setLevel(m - money * amount);
                saveMoney(player);
                player.getInventory().addItem(check);
                if (money == 10) {
                    player.sendMessage(point10 + ChatColor.YELLOW + "수표가 " + ChatColor.GREEN + amount + ChatColor.YELLOW + "장 발행되었습니다.");
                } else if (money == 100) {
                    player.sendMessage(point100 + ChatColor.YELLOW + "수표가 " + ChatColor.GREEN + amount + ChatColor.YELLOW + "장 발행되었습니다.");
                } else{
                    player.sendMessage(point1000 + ChatColor.YELLOW + "수표가 " + ChatColor.GREEN + amount + ChatColor.YELLOW + "장 발행되었습니다.");
                }
            }
        }
    }
}
