package ingame.gui;

import data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class UpgradeAbility {

    public static String upgradeTitle = "" + ChatColor.GOLD + ChatColor.BOLD + "Upgrade your Ability!";
    public static String upgradeWarrior = "" + ChatColor.YELLOW + ChatColor.BOLD + "[전사 업그레이드]";
    public static String upgradeArcher = "" + ChatColor.YELLOW + ChatColor.BOLD + "[궁수 업그레이드]";
    public static String upgradeAssassin = "" + ChatColor.YELLOW + ChatColor.BOLD + "[암살자 업그레이드]";
    public static String upgradeMiner = "" + ChatColor.YELLOW + ChatColor.BOLD + "[광부 업그레이드]";

    public static ArrayList<String> setItemInfo(String ability, int abilityLevel){
        ArrayList<String> lore = new ArrayList<>();
        lore.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "현재 레벨 : " + abilityLevel);
        lore.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "전직 비용 : " + 1000 * abilityLevel);
        lore.add("");
        lore.add("" + ChatColor.BOLD + ChatColor.GREEN + "▷" + ChatColor.RESET + "클릭시 " + ability +"를 업그레이드 합니다.");
        return lore;
    }

    public static void openUpgradeInv(Player player) {
        Inventory upgradeInv = Bukkit.createInventory(null, 27, upgradeTitle);
        ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta noneM = none.getItemMeta();
        noneM.setDisplayName(" ");
        none.setItemMeta(noneM);
        ItemStack pickAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta pickAm = pickAxe.getItemMeta();
        ItemStack pickBow = new ItemStack(Material.BOW);
        ItemMeta pickBm = pickBow.getItemMeta();
        ItemStack pickSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta pickSm = pickSword.getItemMeta();
        ItemStack pickPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickPm = pickPickaxe.getItemMeta();
        int warriorLevel = PlayerData.getLevel(player, "warrior");
        int archerLevel = PlayerData.getLevel(player, "archer");
        int assassinLevel = PlayerData.getLevel(player, "assassin");
        int minerLevel = PlayerData.getLevel(player, "miner");
        pickAm.setDisplayName(upgradeWarrior);
        pickAm.setLore(setItemInfo("전사", warriorLevel));
        pickAxe.setItemMeta(pickAm);
        pickBm.setDisplayName(upgradeArcher);
        pickBm.setLore(setItemInfo("궁수", archerLevel));
        pickBow.setItemMeta(pickBm);
        pickSm.setDisplayName(upgradeAssassin);
        pickSm.setLore(setItemInfo("암살자", assassinLevel));
        pickSword.setItemMeta(pickSm);
        pickPm.setDisplayName(upgradeMiner);
        pickPm.setLore(setItemInfo("광부", minerLevel));
        pickPickaxe.setItemMeta(pickPm);
        upgradeInv.setItem(10, pickAxe);
        upgradeInv.setItem(12, pickBow);
        upgradeInv.setItem(14, pickSword);
        upgradeInv.setItem(16, pickPickaxe);
        for(int i = 0; i < 27; ++i) {
            if (i != 10 && i != 12 && i != 14 && i != 16) {
                upgradeInv.setItem(i, none);
            }
        }
        player.openInventory(upgradeInv);
    }
}
