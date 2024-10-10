package ability;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GiveArcher {
    public GiveArcher() {
    }

    public static void giveItem(Player player, int level) {
        ItemStack Sniper = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta SniperM = Sniper.getItemMeta();
        SniperM.setDisplayName("Sniper Skill");
        Sniper.setItemMeta(SniperM);
        ItemStack KnockBack = new ItemStack(Material.GRAY_DYE);
        ItemMeta KnockBackM = KnockBack.getItemMeta();
        KnockBackM.setDisplayName("KnockBack Skill");
        KnockBack.setItemMeta(KnockBackM);
        ItemStack respawn = new ItemStack(Material.BOOK, 1);
        ItemMeta respawnM = respawn.getItemMeta();
        respawnM.setDisplayName("Respawn");
        respawn.setItemMeta(respawnM);
        ItemStack upgrade = new ItemStack(Material.BOOK, 1);
        ItemMeta upgradeM = upgrade.getItemMeta();
        upgradeM.setDisplayName("Upgrade");
        upgrade.setItemMeta(upgradeM);
        ItemStack changeArrow = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta changeArrowM = changeArrow.getItemMeta();
        changeArrowM.setDisplayName("Change Arrow");
        changeArrow.setItemMeta(changeArrowM);
        switch (level) {
            case 1:
                ItemStack Bow1 = new ItemStack(Material.BOW, 1);
                ItemMeta Bow1M = Bow1.getItemMeta();
                Bow1M.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                Bow1M.setUnbreakable(true);
                Bow1.setItemMeta(Bow1M);
                player.getInventory().setItem(0, Bow1);
                player.getInventory().setItem(1, Sniper);
                player.getInventory().setItem(6, new ItemStack(Material.ARROW));
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                break;
            case 2:
                ItemStack Bow2 = new ItemStack(Material.BOW, 1);
                ItemMeta Bow2M = Bow2.getItemMeta();
                Bow2M.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                Bow2M.addEnchant(Enchantment.KNOCKBACK, 2, true);
                Bow2M.setUnbreakable(true);
                Bow2.setItemMeta(Bow2M);
                player.getInventory().setItem(0, Bow2);
                player.getInventory().setItem(1, Sniper);
                player.getInventory().setItem(6, new ItemStack(Material.ARROW));
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                break;
            case 3:
                ItemStack Bow3 = new ItemStack(Material.BOW, 1);
                ItemMeta Bow3M = Bow3.getItemMeta();
                Bow3M.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                Bow3M.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                Bow3M.setUnbreakable(true);
                Bow3.setItemMeta(Bow3M);
                player.getInventory().setItem(0, Bow3);
                player.getInventory().setItem(1, Sniper);
                player.getInventory().setItem(2, KnockBack);
                player.getInventory().setItem(6, new ItemStack(Material.ARROW));
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                break;
            case 4:
                ItemStack Bow4 = new ItemStack(Material.BOW, 1);
                ItemMeta Bow4M = Bow4.getItemMeta();
                Bow4M.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                Bow4M.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
                Bow4M.setUnbreakable(true);
                Bow4.setItemMeta(Bow4M);
                player.getInventory().setItem(0, Bow4);
                player.getInventory().setItem(1, Sniper);
                player.getInventory().setItem(2, KnockBack);
                player.getInventory().setItem(6, new ItemStack(Material.ARROW));
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        }
    }
}

