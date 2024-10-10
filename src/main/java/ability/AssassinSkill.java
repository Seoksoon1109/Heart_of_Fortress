package ability;

import data.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import system.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AssassinSkill {
    public static HashMap<UUID, Long> hide1 = new HashMap<>();
    public static HashMap<UUID, Long> hide2 = new HashMap<>();
    public static HashMap<UUID, Long> hide3 = new HashMap<>();
    public static HashMap<UUID, Long> hide4 = new HashMap<>();
    public static HashMap<UUID, Long> teleport = new HashMap<>();
    public static int teleportCool = 30;
    public static ArrayList<Player> vanished = new ArrayList<>();
    public static int cool1 = 50;
    public static int cool2 = 47;
    public static int cool3 = 44;
    public static int cool4 = 40;

    public AssassinSkill() {
    }

    public static void useHide(Player player) {
        int Level = PlayerData.getLevel(player, "assassin");
        UUID uuid = player.getUniqueId();
        long coolLeft;
        switch(Level) {
            case 1:
                if (hide1.containsKey(uuid)) {
                    coolLeft = hide1.get(uuid) / 1000L + (long)cool1 - System.currentTimeMillis() / 1000L;
                    if (coolLeft <= 0L) {
                        Hide(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "은신 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
                    }
                } else {
                    Hide(player);
                }
                break;
            case 2:
                if (hide2.containsKey(uuid)) {
                    coolLeft = hide2.get(uuid) / 1000L + (long)cool2 - System.currentTimeMillis() / 1000L;
                    if (coolLeft <= 0L) {
                        Hide(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "은신 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
                    }
                } else {
                    Hide(player);
                }
                break;
            case 3:
                if (hide3.containsKey(uuid)) {
                    coolLeft = hide3.get(uuid) / 1000L + (long)cool3 - System.currentTimeMillis() / 1000L;
                    if (coolLeft <= 0L) {
                        Hide(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "은신 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
                    }
                } else {
                    Hide(player);
                }
                break;
            case 4:
                if (hide4.containsKey(uuid)) {
                    coolLeft = hide4.get(uuid) / 1000L + (long)cool4 - System.currentTimeMillis() / 1000L;
                    if (coolLeft <= 0L) {
                        Hide(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "은신 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
                    }
                } else {
                    Hide(player);
                }
        }

    }

    public static void Hide(Player player) {
        if(!vanished.contains(player)) {
            vanished.add(player);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(Main.getInstance(), player);
            }
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 10, 10);
        }
    }

    public static void useTeleport(Player player){
        int Level = PlayerData.getLevel(player, "assassin");
        UUID uuid = player.getUniqueId();
        long coolLeft;
        switch(Level) {
            case 3:
            case 4:
                if (teleport.containsKey(uuid)) {
                    coolLeft = teleport.get(uuid) / 1000L + (long)teleportCool - System.currentTimeMillis() / 1000L;
                    if (coolLeft <= 0L) {
                        Teleport(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "순신 스킬 활성화 까지 " + ChatColor.YELLOW + coolLeft + ChatColor.RED + "초 남았습니다.");
                    }
                } else {
                    Teleport(player);
                }
                break;
        }
    }

    public static void Teleport(Player player){
        Player target=null;
        for(Player t : Bukkit.getOnlinePlayers()){
            if(t.getLocation().distance(player.getLocation())<=30){
                Vector playerLookDirection = player.getEyeLocation().getDirection();
                Vector playerEyeLocation = player.getEyeLocation().toVector();
                Vector EntityLocation = t.getLocation().toVector();
                Vector playerEntityGap = EntityLocation.subtract(playerEyeLocation);
                float angle = playerLookDirection.angle(playerEntityGap);
                if(angle<0.15f) {
                    target = t;
                    break;
                }
            }
        }
        if(target!=null){
            Location targetLoc = getBlockBehindPlayer(target);
            player.teleport(targetLoc.add(0,0.2,0));
            Objects.requireNonNull(targetLoc.getWorld()).playSound(targetLoc,Sound.ENTITY_ENDERMAN_TELEPORT,10,10);
            teleport.put(player.getUniqueId(), System.currentTimeMillis());
            player.setCooldown(Material.LIME_DYE, teleportCool * 20);
        }
    }

    public static Location getBlockBehindPlayer (Player player) {
        Vector inverseDirectionVec = player.getLocation (). getDirection (). normalize (). multiply (-1);
        return player.getLocation (). add (inverseDirectionVec);
    }
}
