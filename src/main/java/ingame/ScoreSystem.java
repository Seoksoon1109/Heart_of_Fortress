package ingame;

import data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class ScoreSystem {
    final static ScoreboardManager boardManager = Bukkit.getScoreboardManager();
    public static Scoreboard scoreboard;
    public static Objective scoreObjective;
    public static Objective hpObjective;


    public static void boardSetting(){
        hpObjective.setDisplayName(ChatColor.RED + "❤");
        hpObjective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    @Deprecated
    public static void updateBoard(){
        scoreObjective.unregister();
        scoreObjective = scoreObjective.getScoreboard().registerNewObjective("score","tmp");
        scoreObjective.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "★Score Rank★");
        scoreObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Player player : PlayerData.allPlayer) {
            Score score = scoreObjective.getScore(player.getName());
            score.setScore(PlayerData.scores.get(player.getUniqueId()));
        }
    }

    public static void setScore(@NotNull Player player, int score){
        PlayerData.scores.put(player.getUniqueId(), score);
    }

    public static void addScore(@NotNull Player player, int score){
        UUID uuid = player.getUniqueId();
        int tmp = PlayerData.scores.get(uuid);
        PlayerData.scores.put(uuid, tmp+score);
    }

    public static void resetScore(){
        for(Player player : PlayerData.allPlayer){
            setScore(player, 0);
        }
    }
}
