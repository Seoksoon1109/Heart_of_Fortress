//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ingame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import system.GameManager;

public class StartTimer extends BukkitRunnable {
    private int num = 3;

    public StartTimer() {
    }

    public void run() {
        if (this.num != -1) {
            if (this.num != 0) {
                Bukkit.broadcastMessage("" + ChatColor.RED + this.num + "초");
            } else {
                GameManager.startGame();
                this.cancel();
            }
            --this.num;
        }

    }
}
