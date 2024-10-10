package data;

import ability.ArcherSkill;
import ability.AssassinSkill;
import ability.WarriorSkill;
import org.bukkit.entity.Player;
import system.ChatType;
import system.TeamSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class PlayerData {
    public static HashMap<UUID, Integer> moneyData = new HashMap<>();
    public static HashMap<UUID, Integer> warrior = new HashMap<>();
    public static HashMap<UUID, Integer> archer = new HashMap<>();
    public static HashMap<UUID, Integer> assassin = new HashMap<>();
    public static HashMap<UUID, Integer> miner = new HashMap<>();
    public static ArrayList<Player> allPlayer = new ArrayList<>();
    public static HashMap<UUID, ChatType> chatType = new HashMap<>();
    public static HashMap<UUID, Integer> scores;
    public static Player deadPlayer;

    @SuppressWarnings("deprecation")
    public static boolean hasTeam(Player player) {
        return TeamSystem.red.getPlayers().contains(player) || TeamSystem.blue.getPlayers().contains(player);
    }

    @SuppressWarnings("deprecation")
    public static boolean isRed(Player player) {
        return TeamSystem.red.getPlayers().contains(player);
    }


    @SuppressWarnings("deprecation")
    public static boolean isBlue(Player player) {
        return TeamSystem.blue.getPlayers().contains(player);
    }


    public static int getLevel(Player player, String ability) {
        if (ability.equalsIgnoreCase("warrior")) {
            return warrior.get(player.getUniqueId());
        } else if (ability.equalsIgnoreCase("archer")) {
            return archer.get(player.getUniqueId());
        } else if (ability.equalsIgnoreCase("assassin")) {
            return assassin.get(player.getUniqueId());
        } else {
            return ability.equalsIgnoreCase("miner") ? miner.get(player.getUniqueId()) : 0;
        }
    }

    public static void upgrade(Player player, String ability) {
        int level;
        if (ability.equalsIgnoreCase("warrior")) {
            level = warrior.get(player.getUniqueId());
            warrior.put(player.getUniqueId(), level + 1);
        } else if (ability.equalsIgnoreCase("archer")) {
            level = archer.get(player.getUniqueId());
            archer.put(player.getUniqueId(), level + 1);
        } else if (ability.equalsIgnoreCase("assassin")) {
            level = assassin.get(player.getUniqueId());
            assassin.put(player.getUniqueId(), level + 1);
        } else if (ability.equalsIgnoreCase("miner")) {
            level = miner.get(player.getUniqueId());
            miner.put(player.getUniqueId(), level + 1);
        }

    }

    public static void resetCool(Player player) {
        UUID uuid = player.getUniqueId();
        WarriorSkill.attack1.remove(uuid);
        WarriorSkill.attack2.remove(uuid);
        WarriorSkill.attack3.remove(uuid);
        WarriorSkill.attack4.remove(uuid);
        WarriorSkill.tank1.remove(uuid);
        WarriorSkill.tank2.remove(uuid);
        ArcherSkill.sniper1.remove(uuid);
        ArcherSkill.sniper2.remove(uuid);
        ArcherSkill.sniper3.remove(uuid);
        ArcherSkill.sniper4.remove(uuid);
        ArcherSkill.knock1.remove(uuid);
        ArcherSkill.knock2.remove(uuid);
        AssassinSkill.teleport.remove(uuid);
        AssassinSkill.hide1.remove(uuid);
        AssassinSkill.hide2.remove(uuid);
        AssassinSkill.hide3.remove(uuid);
        AssassinSkill.hide4.remove(uuid);
    }

}
