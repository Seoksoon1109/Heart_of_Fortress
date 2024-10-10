package ingame.gui;

import ingame.MoneySystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Merchant {
    public Merchant() {
    }

    public static void openMerchant(Player player) {
        ItemStack stone = new ItemStack(Material.COBBLESTONE, 64);
        ItemStack coal = new ItemStack(Material.COAL, 8);
        ItemStack iron = new ItemStack(Material.IRON_ORE, 1);
        ItemStack gold = new ItemStack(Material.GOLD_ORE, 1);
        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack p10x1 = new ItemStack(Material.PAPER, 1);
        ItemMeta p10x1m = p10x1.getItemMeta();
        p10x1m.setDisplayName(MoneySystem.point10);
        p10x1.setItemMeta(p10x1m);
        ItemStack p10x2 = new ItemStack(Material.PAPER, 2);
        ItemMeta p10x2m = p10x1.getItemMeta();
        p10x2m.setDisplayName(MoneySystem.point10);
        p10x2.setItemMeta(p10x2m);
        ItemStack p10x5 = new ItemStack(Material.PAPER, 5);
        ItemMeta p10x5m = p10x1.getItemMeta();
        p10x5m.setDisplayName(MoneySystem.point10);
        p10x5.setItemMeta(p10x1m);
        ItemStack p10x10 = new ItemStack(Material.PAPER, 10);
        ItemMeta p10x10m = p10x1.getItemMeta();
        p10x10m.setDisplayName(MoneySystem.point10);
        p10x10.setItemMeta(p10x10m);
        ItemStack p100x1 = new ItemStack(Material.PAPER, 1);
        ItemMeta p100x1m = p100x1.getItemMeta();
        p100x1m.setDisplayName(MoneySystem.point100);
        p100x1.setItemMeta(p100x1m);
        ItemStack p100x3 = new ItemStack(Material.PAPER, 3);
        ItemMeta p100x3m = p100x1.getItemMeta();
        p100x3m.setDisplayName(MoneySystem.point100);
        p100x3.setItemMeta(p100x3m);
        ItemStack p100x10 = new ItemStack(Material.PAPER, 10);
        ItemMeta p100x10m = p100x1.getItemMeta();
        p100x10m.setDisplayName(MoneySystem.point100);
        p100x10.setItemMeta(p100x10m);
        ItemStack p1000 = new ItemStack(Material.PAPER, 1);
        ItemMeta p1000m = p1000.getItemMeta();
        p1000m.setDisplayName(MoneySystem.point1000);
        p1000.setItemMeta(p1000m);
        List<MerchantRecipe> list = new ArrayList<>();
        MerchantRecipe recipe1 = new MerchantRecipe(p100x1, -1);
        recipe1.addIngredient(stone);
        list.add(recipe1);
        MerchantRecipe recipe2 = new MerchantRecipe(p10x5, -1);
        recipe2.addIngredient(coal);
        list.add(recipe2);
        MerchantRecipe recipe3 = new MerchantRecipe(p10x1, -1);
        recipe3.addIngredient(iron);
        list.add(recipe3);
        MerchantRecipe recipe4 = new MerchantRecipe(p10x2, -1);
        recipe4.addIngredient(gold);
        list.add(recipe4);
        MerchantRecipe recipe5 = new MerchantRecipe(p100x1, -1);
        recipe5.addIngredient(diamond);
        list.add(recipe5);
        MerchantRecipe recipe6 = new MerchantRecipe(p100x3, -1);
        recipe6.addIngredient(emerald);
        list.add(recipe6);
        MerchantRecipe recipe7 = new MerchantRecipe(p100x1, -1);
        recipe7.addIngredient(p10x10);
        list.add(recipe7);
        MerchantRecipe recipe8 = new MerchantRecipe(p1000, -1);
        recipe8.addIngredient(p100x10);
        list.add(recipe8);
        MerchantRecipe recipe9 = new MerchantRecipe(p10x10, -1);
        recipe9.addIngredient(p100x1);
        list.add(recipe9);
        MerchantRecipe recipe10 = new MerchantRecipe(p100x10, -1);
        recipe10.addIngredient(p1000);
        list.add(recipe10);
        MerchantRecipe recipe11 = new MerchantRecipe(new ItemStack(Material.CHEST, 1), -1);
        recipe11.addIngredient(p10x5);
        list.add(recipe11);
        org.bukkit.inventory.Merchant merchant = Bukkit.createMerchant("§a§lNPC SHOP");
        merchant.setRecipes(list);
        player.openMerchant(merchant, true);
    }
}
