package system;

import data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class TeamSystem {
    public static Team red;
    public static Team blue;


    public static Team getTeam(@NotNull Player player){
        if(PlayerData.isRed(player)) return red;
        else return blue;
    }

    public static void setTeam(){
        ShowTeam showTeam = new ShowTeam();
        showTeam.runTaskTimer(Main.getInstance(),0L,4L);
    }

    @SuppressWarnings("deprecation")
    public static void teamSetting(){
        red.setColor(ChatColor.RED);
        red.setPrefix(ChatColor.RED + "");
        red.setAllowFriendlyFire(false);
        red.setCanSeeFriendlyInvisibles(true);
        red.setNameTagVisibility(NameTagVisibility.ALWAYS);
        blue.setColor(ChatColor.BLUE);
        blue.setPrefix(ChatColor.BLUE + "");
        blue.setAllowFriendlyFire(false);
        blue.setCanSeeFriendlyInvisibles(true);
        blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
    }

}
