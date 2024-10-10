//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ability;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GiveWarrior {
    public GiveWarrior() {
    }

    public static void giveItem(Player player, int level) {
        ItemStack woodAxe = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta woodAxeM = woodAxe.getItemMeta();
        woodAxeM.setUnbreakable(true);
        woodAxeM.setDisplayName("전사의 나무 전투도끼");
        woodAxe.setItemMeta(woodAxeM);
        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE, 1);
        ItemMeta stoneAxeM = stoneAxe.getItemMeta();
        stoneAxeM.setUnbreakable(true);
        stoneAxeM.setDisplayName("전사의 돌 전투도끼");
        stoneAxe.setItemMeta(stoneAxeM);
        ItemStack ironAxe = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta ironAxeM = ironAxe.getItemMeta();
        ironAxeM.setUnbreakable(true);
        ironAxeM.setDisplayName("전사의 철 전투도끼");
        ironAxe.setItemMeta(ironAxeM);
        ItemStack diamondAxe = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta diamondAxeM = ironAxe.getItemMeta();
        diamondAxeM.setUnbreakable(true);
        diamondAxeM.setDisplayName("전사의 다이아몬드 전투도끼");
        diamondAxe.setItemMeta(diamondAxeM);
        ItemStack shield = new ItemStack(Material.SHIELD, 1);
        ItemMeta shieldM = ironAxe.getItemMeta();
        shieldM.setUnbreakable(true);
        shieldM.setDisplayName("전사의 방패");
        shield.setItemMeta(shieldM);
        ItemStack respawn = new ItemStack(Material.BOOK, 1);
        ItemMeta respawnM = respawn.getItemMeta();
        respawnM.setDisplayName("Respawn");
        respawn.setItemMeta(respawnM);
        ItemStack upgrade = new ItemStack(Material.BOOK, 1);
        ItemMeta upgradeM = upgrade.getItemMeta();
        upgradeM.setDisplayName("Upgrade");
        upgrade.setItemMeta(upgradeM);
        ItemStack Attack = new ItemStack(Material.PURPLE_DYE);
        ItemMeta AttackM = Attack.getItemMeta();
        AttackM.setDisplayName("Attack SKill");
        Attack.setItemMeta(AttackM);
        ItemStack Heal = new ItemStack(Material.CYAN_DYE);
        ItemMeta HealM = Heal.getItemMeta();
        HealM.setDisplayName("Tank SKill");
        Heal.setItemMeta(HealM);
        switch(level) {
            case 1:
                player.getInventory().setItem(0, woodAxe);
                player.getInventory().setItem(1, Attack);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                break;
            case 2:
                player.getInventory().setItem(0, stoneAxe);
                player.getInventory().setItem(1, Attack);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                break;
            case 3:
                player.getInventory().setItem(0, ironAxe);
                player.getInventory().setItem(1, Attack);
                player.getInventory().setItem(2, Heal);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                break;
            case 4:
                player.getInventory().setItem(0, diamondAxe);
                player.getInventory().setItem(1, Attack);
                player.getInventory().setItem(2, Heal);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        }

    }
}
