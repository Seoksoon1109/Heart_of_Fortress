package ability;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class ArcherSkill {
    public static HashMap<UUID, Long> knock1 = new HashMap<>();
    public static HashMap<UUID, Long> knock2 = new HashMap<>();
    public static HashMap<UUID, Long> sniper1 = new HashMap<>();
    public static HashMap<UUID, Long> sniper2 = new HashMap<>();
    public static HashMap<UUID, Long> sniper3 = new HashMap<>();
    public static HashMap<UUID, Long> sniper4 = new HashMap<>();
    public static ArrayList<UUID> bullet = new ArrayList<>();
    final static int knockCool1 = 25;
    final static int knockCool2 = 20;
    final static int sniperCool1 = 30;
    final static int sniperCool2 = 27;
    final static int sniperCool3 = 24;
    final static int sniperCool4 = 20;

    public ArcherSkill() {
    }

    public static void useSniper(Player player) {
        int Level = PlayerData.getLevel(player, "archer");
        UUID uuid = player.getUniqueId();
        switch(Level) {
            case 1:
                sniperCool(player, uuid, sniper1, sniperCool1);
                break;
            case 2:
                sniperCool(player, uuid, sniper2, sniperCool2);
                break;
            case 3:
                sniperCool(player, uuid, sniper3, sniperCool3);
                break;
            case 4:
                sniperCool(player, uuid, sniper4, sniperCool4);
        }
    }

    public static void sniperCool(Player player, UUID uuid, HashMap<UUID, Long> sniper, int sniperCool) {
        long coolLeft;
        if (sniper.containsKey(uuid)) {
            coolLeft = sniper.get(uuid) / 1000L + (long) sniperCool - System.currentTimeMillis() / 1000L;
            if (coolLeft <= 0L) {
                Sniper(player);
                sniper.put(uuid, System.currentTimeMillis());
                player.setCooldown(Material.LIGHT_GRAY_DYE, sniperCool * 20);
            } else {
                player.sendMessage(ChatColor.RED + "스나이퍼 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
            }
        } else {
            Sniper(player);
            sniper.put(uuid, System.currentTimeMillis());
            player.setCooldown(Material.LIGHT_GRAY_DYE, sniperCool * 20);
        }
    }

    public static void useKnockBack(Player player) {
        int Level = PlayerData.getLevel(player, "archer");
        UUID uuid = player.getUniqueId();
        switch(Level) {
            case 1:
            case 2:
                player.sendMessage(ChatColor.RED + "넉백 스킬은 3차 전직 후 부터 사용할 수 있습니다!");
                break;
            case 3:
                knockBackCool(player, uuid, knock1, knockCool1);
                break;
            case 4:
                knockBackCool(player, uuid, knock2, knockCool2);
        }

    }

    public static void knockBackCool(Player player, UUID uuid, HashMap<UUID, Long> knockBack, int knockBackCool) {
        long coolLeft;
        if (knockBack.containsKey(uuid)) {
            coolLeft = knockBack.get(uuid) / 1000L + (long) knockBackCool - System.currentTimeMillis() / 1000L;
            if (coolLeft <= 0L) {
                KnockBack(player);
                knockBack.put(uuid, System.currentTimeMillis());
                player.setCooldown(Material.GRAY_DYE, knockBackCool * 20);
            } else {
                player.sendMessage(ChatColor.RED + "넉백 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
            }
        } else {
            KnockBack(player);
            knockBack.put(uuid, System.currentTimeMillis());
            player.setCooldown(Material.GRAY_DYE, knockBackCool * 20);
        }
    }

    public static void KnockBack(Player player) {
        List<Entity> nearByPlayers = player.getNearbyEntities(6,6,6);
        for(Entity entity : nearByPlayers){
            if(entity instanceof Player){
                Player target = (Player)entity;
                if((PlayerData.isRed(player)&&PlayerData.isBlue(target))||(PlayerData.isBlue(player)&&PlayerData.isRed(target))){
                    Location playerLocation = player.getLocation();
                    Location targetLocation = target.getLocation();
                    Vector vector = new Vector();
                    vector.setX(targetLocation.getX()-playerLocation.getX());
                    vector.setY(targetLocation.getY()-playerLocation.getY()+0.2D);
                    vector.setZ(targetLocation.getZ()-playerLocation.getX());
                    vector.normalize();
                    vector.multiply(2);
                    player.setVelocity(vector);
                }
            }
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, true));
        player.setCooldown(Material.BOW, 100);
    }

    public static void Sniper(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        bullet.add(arrow.getUniqueId());
        arrow.setShooter(player);
        arrow.setVelocity(player.getEyeLocation().getDirection().multiply(100));
    }
}
