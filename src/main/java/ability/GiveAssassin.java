package ability;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveAssassin {
    public GiveAssassin() {
    }

    public static void giveItem(Player player, int level) {
        ItemStack woodSword = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemMeta woodSwordM = woodSword.getItemMeta();
        woodSwordM.setUnbreakable(true);
        woodSwordM.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
        woodSwordM.setDisplayName("암살자의 나무검");
        woodSword.setItemMeta(woodSwordM);
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta stoneSwordM = stoneSword.getItemMeta();
        stoneSwordM.setUnbreakable(true);
        stoneSwordM.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
        stoneSwordM.setDisplayName("암살자의 돌검");
        stoneSword.setItemMeta(stoneSwordM);
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta ironSwordM = ironSword.getItemMeta();
        ironSwordM.setUnbreakable(true);
        ironSwordM.addEnchant(Enchantment.DAMAGE_ALL, 0, true);
        ironSwordM.setDisplayName("암살자의 철검");
        ironSword.setItemMeta(ironSwordM);
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta diamondSwordM = diamondSword.getItemMeta();
        diamondSwordM.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        diamondSwordM.setDisplayName("암살자의 다이아몬드 검");
        diamondSword.setItemMeta(diamondSwordM);
        ItemStack respawn = new ItemStack(Material.BOOK, 1);
        ItemMeta respawnM = respawn.getItemMeta();
        respawnM.setDisplayName("Respawn");
        respawn.setItemMeta(respawnM);
        ItemStack upgrade = new ItemStack(Material.BOOK, 1);
        ItemMeta upgradeM = upgrade.getItemMeta();
        upgradeM.setDisplayName("Upgrade");
        upgrade.setItemMeta(upgradeM);
        ItemStack Hide = new ItemStack(Material.PINK_DYE);
        ItemMeta HideM = Hide.getItemMeta();
        HideM.setDisplayName("Hide Skill");
        Hide.setItemMeta(HideM);
        ItemStack Tp = new ItemStack(Material.LIME_DYE);
        ItemMeta TpM = Tp.getItemMeta();
        TpM.setDisplayName("Teleport Skill");
        Tp.setItemMeta(TpM);
        switch(level) {
            case 1:
                player.getInventory().setItem(0, woodSword);
                player.getInventory().setItem(1, Hide);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                break;
            case 2:
                player.getInventory().setItem(0, stoneSword);
                player.getInventory().setItem(1, Hide);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                break;
            case 3:
                player.getInventory().setItem(0, ironSword);
                player.getInventory().setItem(1, Hide);
                player.getInventory().setItem(2, Tp);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
                break;
            case 4:
                player.getInventory().setItem(0, diamondSword);
                player.getInventory().setItem(1, Hide);
                player.getInventory().setItem(2, Tp);
                player.getInventory().setItem(7, upgrade);
                player.getInventory().setItem(8, respawn);
        }

    }
}