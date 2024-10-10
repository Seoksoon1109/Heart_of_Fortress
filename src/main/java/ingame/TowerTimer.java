package ingame;

import data.MapData;
import data.PlayerData;
import data.TimerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TowerTimer extends BukkitRunnable {
    private int num;
    private int time;

    public TowerTimer() {
        this.num = MapData.towerTime;
        this.time = this.num;
    }

    public void run() {
        TimerData.towerID = this.getTaskId();
        if (this.num != -1) {
            if (this.num != 0) {
                String m = "" + this.num / 60 + "";
                String s = "" + this.num % 60 + "";
                if (this.num % 60 < 10) {
                    s = "0" + this.num % 60 + "";
                }

                TimerData.tower.setTitle("" + ChatColor.AQUA + "타워 활성화까지 【 " + ChatColor.LIGHT_PURPLE + m + " 분 " + ChatColor.LIGHT_PURPLE + s + " 초" + ChatColor.AQUA + " 】 남았습니다.");
                TimerData.tower.setProgress((double)this.num / (double)this.time);

                for(int i = 0; i < PlayerData.allPlayer.size(); ++i) {
                    TimerData.tower.addPlayer(PlayerData.allPlayer.get(i));
                }

            } else {
                TimerData.tower.removeAll();
                MapData.onTower = true;
                Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "타워가 활성화되었습니다. 지금부터 타워를 파괴할 수 있습니다.");
                this.cancel();
            }
            --this.num;
        }

    }
}
