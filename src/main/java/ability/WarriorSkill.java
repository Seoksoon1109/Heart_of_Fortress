//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ability;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class WarriorSkill {
    public static HashMap<UUID, Long> attack1 = new HashMap<>();
    public static HashMap<UUID, Long> attack2 = new HashMap<>();
    public static HashMap<UUID, Long> attack3 = new HashMap<>();
    public static HashMap<UUID, Long> attack4 = new HashMap<>();
    public static HashMap<UUID, Long> tank1 = new HashMap<>();
    public static HashMap<UUID, Long> tank2 = new HashMap<>();
    public static int attackCool1 = 40;
    public static int attackCool2 = 40;
    public static int attackCool3 = 40;
    public static int attackCool4 = 40;
    public static int tankCool1 = 60;
    public static int tankCool2 = 50;

    public WarriorSkill() {
    }

    public static void useAttack(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        int Level = PlayerData.getLevel(player, "warrior");
        switch(Level) {
            case 1:
                attackCool(player, uuid, attack1, attackCool1);
                break;
            case 2:
                attackCool(player, uuid, attack2, attackCool2);
                break;
            case 3:
                attackCool(player, uuid, attack3, attackCool3);
                break;
            case 4:
                attackCool(player, uuid, attack4, attackCool4);
        }

    }

    public static void attackCool(Player player, UUID uuid, @NotNull HashMap<UUID, Long> attack, int attackCool) {
        long coolLeft;
        if (attack.containsKey(uuid)) {
            coolLeft = attack.get(uuid) / 1000L + (long) attackCool - System.currentTimeMillis() / 1000L;
            if (coolLeft <= 0L) {
                Attack(player);
                player.setCooldown(Material.PURPLE_DYE, attackCool * 20);
                attack.put(uuid, System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "공격 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
            }
        } else {
            Attack(player);
            player.setCooldown(Material.PURPLE_DYE, attackCool * 20);
            attack.put(uuid, System.currentTimeMillis());
        }
    }

    public static void useTank(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        int Level = PlayerData.getLevel(player, "warrior");
        switch(Level) {
            case 1:
            case 2:
                player.sendMessage(ChatColor.RED + "탱크 스킬은 3차 전직 후 부터 사용할 수 있습니다!");
                break;
            case 3:
                tankCool(player, uuid, tank1, tankCool1);
                break;
            case 4:
                tankCool(player, uuid, tank2, tankCool2);
        }

    }

    public static void tankCool(Player player, UUID uuid, @NotNull HashMap<UUID, Long> tank, int tankCool) {
        long coolLeft;
        if (tank.containsKey(uuid)) {
            coolLeft = tank.get(uuid) / 1000L + (long) tankCool - System.currentTimeMillis() / 1000L;
            if (coolLeft <= 0L) {
                Tank(player);
                player.setCooldown(Material.CYAN_DYE, tankCool * 20);
                tank.put(uuid, System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "탱크 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
            }
        } else {
            Tank(player);
            player.setCooldown(Material.CYAN_DYE, tankCool * 20);
            tank.put(uuid, System.currentTimeMillis());
        }
    }

    public static void Attack(Player player) {
        int Level = PlayerData.getLevel(player, "warrior");
        switch(Level) {
            case 1:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0, true));
                break;
            case 2:
            case 4:
            case 3:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 1, true));
                break;
        }

    }

    public static void Tank(Player player) {
        int Level = PlayerData.getLevel(player, "warrior");
        switch(Level) {
            case 1:
            case 2:
                player.sendMessage(ChatColor.RED + "탱크 스킬은 3차 전직 후 부터 사용할 수 있습니다!");
                break;
            case 3:
            case 4:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 400, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2, true));
        }

    }
}
