package ingame;

import data.PlayerData;
import ingame.gui.PickAbility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class RespawnTimer extends BukkitRunnable {
    private final Player player = PlayerData.deadPlayer;
    private final ItemStack respawn = new ItemStack(Material.BOOK, 1);
    private final ItemMeta respawnM = respawn.getItemMeta();
    private int num = 7;
    @Override
    public void run() {
        if (this.num != -1) {
            if (this.num != 0) {
                player.sendTitle(""+ChatColor.RED+num+"Seconds","are left before Respawn",0,3*20,0);
            } else {
                player.resetTitle();
                Objects.requireNonNull(respawnM).setDisplayName("Respawn");
                respawn.setItemMeta(respawnM);
                MoneySystem.loadMoney(player);
                PickAbility.openPickInv(player);
                player.getInventory().setItem(0, respawn);
                this.cancel();
            }
            --this.num;
        }
    }
}
