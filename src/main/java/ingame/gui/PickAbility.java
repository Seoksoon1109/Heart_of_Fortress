package ingame.gui;

import data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class PickAbility {
    public static String pickTitle;
    public static String pickWarrior;
    public static String pickArcher;
    public static String pickAssassin;
    public static String pickMiner;

    public PickAbility() {
    }

    public static void openPickInv(Player player) {
        Inventory pickInv = Bukkit.createInventory(null, 27, pickTitle);
        ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
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
        ArrayList<String> al = new ArrayList<>();
        ArrayList<String> bl = new ArrayList<>();
        ArrayList<String> sl = new ArrayList<>();
        ArrayList<String> pl = new ArrayList<>();
        int warriorLevel = PlayerData.getLevel(player, "warrior");
        int archerLevel = PlayerData.getLevel(player, "archer");
        int assassinLevel = PlayerData.getLevel(player, "assassin");
        int minerLevel = PlayerData.getLevel(player, "miner");
        pickAm.setDisplayName(pickWarrior);
        al.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "현재 레벨 : " + warriorLevel);
        al.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "전직 비용 : " + 1000 * warriorLevel);
        al.add("");
        al.add("" + ChatColor.BOLD + ChatColor.GREEN + "▷" + ChatColor.RESET + "클릭시 전직합니다.");
        pickAm.setLore(al);
        pickAxe.setItemMeta(pickAm);
        pickBm.setDisplayName(pickArcher);
        bl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "현재 레벨 : " + archerLevel);
        bl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "전직 비용 : " + 1000 * archerLevel);
        bl.add("");
        bl.add("" + ChatColor.BOLD + ChatColor.GREEN + "▷" + ChatColor.RESET + "클릭시 전직합니다.");
        pickBm.setLore(bl);
        pickBow.setItemMeta(pickBm);
        pickSm.setDisplayName(pickAssassin);
        sl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "현재 레벨 : " + assassinLevel);
        sl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "전직 비용 : " + 1000 * assassinLevel);
        sl.add("");
        sl.add("" + ChatColor.BOLD + ChatColor.GREEN + "▷" + ChatColor.RESET + "클릭시 전직합니다.");
        pickSm.setLore(sl);
        pickSword.setItemMeta(pickSm);
        pickPm.setDisplayName(pickMiner);
        pl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "현재 레벨 : " + minerLevel);
        pl.add("" + ChatColor.BOLD + ChatColor.GREEN + "◎" + ChatColor.RESET + "전직 비용 : " + 1000 * minerLevel);
        pl.add("");
        pl.add("" + ChatColor.BOLD + ChatColor.GREEN + "▷" + ChatColor.RESET + "클릭시 전직합니다.");
        pickPm.setLore(pl);
        pickPickaxe.setItemMeta(pickPm);
        pickInv.setItem(10, pickAxe);
        pickInv.setItem(12, pickBow);
        pickInv.setItem(14, pickSword);
        pickInv.setItem(16, pickPickaxe);

        for(int i = 0; i < 27; ++i) {
            if (i != 10 && i != 12 && i != 14 && i != 16) {
                pickInv.setItem(i, none);
            }
        }

        player.openInventory(pickInv);
    }
}
