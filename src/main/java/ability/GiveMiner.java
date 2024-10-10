//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ability;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GiveMiner {
    public GiveMiner() {
    }

    public static void giveItem(Player player, int level) {
        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta stonePickaxeM = stonePickaxe.getItemMeta();
        stonePickaxeM.setUnbreakable(true);
        stonePickaxeM.addEnchant(Enchantment.DIG_SPEED, 3, true);
        stonePickaxeM.setDisplayName("광부의 돌곡괭이");
        stonePickaxe.setItemMeta(stonePickaxeM);
        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta ironPickaxeM = ironPickaxe.getItemMeta();
        ironPickaxeM.setUnbreakable(true);
        ironPickaxeM.addEnchant(Enchantment.DIG_SPEED, 3, true);
        ironPickaxeM.setDisplayName("광부의 철곡괭이");
        ironPickaxe.setItemMeta(ironPickaxeM);
        ItemStack diamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta diamondPickaxeM = diamondPickaxe.getItemMeta();
        diamondPickaxeM.setUnbreakable(true);
        diamondPickaxeM.addEnchant(Enchantment.DIG_SPEED, 3, true);
        diamondPickaxeM.setDisplayName("광부의 다이아 곡괭이");
        diamondPickaxe.setItemMeta(diamondPickaxeM);
        ItemStack legendPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta legendPickaxeM = legendPickaxe.getItemMeta();
        legendPickaxeM.setUnbreakable(true);
        legendPickaxeM.addEnchant(Enchantment.DIG_SPEED, 5, true);
        legendPickaxeM.setDisplayName("광부의 전설적인 곡괭이");
        legendPickaxe.setItemMeta(legendPickaxeM);
        ItemStack respawn = new ItemStack(Material.BOOK, 1);
        ItemMeta respawnM = respawn.getItemMeta();
        respawnM.setDisplayName("Respawn");
        respawn.setItemMeta(respawnM);
        ItemStack upgrade = new ItemStack(Material.BOOK, 1);
        ItemMeta upgradeM = upgrade.getItemMeta();
        upgradeM.setDisplayName("Upgrade");
        upgrade.setItemMeta(upgradeM);
        switch(level) {
            case 1:
                player.getInventory().setItem(0, stonePickaxe);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                break;
            case 2:
                player.getInventory().setItem(0, ironPickaxe);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                break;
            case 3:
                player.getInventory().setItem(0, diamondPickaxe);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                break;
            case 4:
                player.getInventory().setItem(0, legendPickaxe);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        }

    }
}
