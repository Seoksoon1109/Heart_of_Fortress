package listeners;

import ability.GiveArcher;
import ability.GiveAssassin;
import ability.GiveMiner;
import ability.GiveWarrior;
import data.MapData;
import data.PlayerData;
import ingame.MoneySystem;
import ingame.RegenBlock;
import ingame.RespawnTimer;
import ingame.ScoreSystem;
import ingame.gui.PickAbility;
import ingame.gui.UpgradeAbility;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import system.GameManager;
import system.Main;
import system.TeamSystem;

import java.util.Objects;


public class SystemListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (GameManager.isStart) {
            e.setCancelled(true);
            e.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (GameManager.isStart) {
            Player player = e.getEntity();
            Player killer = e.getEntity().getKiller();
            PlayerData.resetCool(player);
            if (killer != null) {
                killer.setLevel(killer.getLevel() + 50);
                MoneySystem.saveMoney(killer);
                ScoreSystem.addScore(killer, 1);
            }
            e.getDrops().clear();
            e.setDroppedExp(0);
        }
    }

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent e) {
        final Player player = e.getPlayer();
        if (GameManager.isStart) {
            PlayerData.deadPlayer = player;
            RespawnTimer timer = new RespawnTimer();
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 7*20,100,false,false));
            timer.runTaskTimer(Main.getInstance(),0,20);
        }
    }

    @EventHandler
    public void onBuilt(BlockPlaceEvent e) {
        if (GameManager.isStart) {
            if (e.getBlock().getType().equals(Material.CHEST)) {
                MapData.chest.add(e.getBlock().getLocation());
            } else {
                e.setCancelled(true);
                e.getPlayer().sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "게임 중 블럭을 설치할 수 없습니다!");
            }
        } else if (!e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "블럭을 설치할 수 있는 권한이 없습니다.");
        }

    }


    public String sendBrokeMessage(String team, int towerType){//1= left, 2= right
        String position = "";
        int percent = 0;
        if(team.equalsIgnoreCase("RED")){
            team = "레드";
            if(towerType==1){
                position = "왼쪽";
                percent = 100-(MapData.redLeft*50);
            }else if(towerType==2){
                position = "오른쪽";
                percent = 100-(MapData.redRight*50);
            }
        }else if(team.equalsIgnoreCase("BLUE")){
            team = "blue";
            if(towerType==1){
                position = "왼쪽";
                percent = 100-(MapData.blueLeft*50);
            }else if(towerType==2){
                position = "오른쪽";
                percent = 100-(MapData.blueRight*50);
            }
        }
        return ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + team + "팀의 " + position + "포탑이 " + percent + "% 파괴되었습니다.";
    }



    @EventHandler
    @Deprecated
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Location location = e.getBlock().getLocation();
        String warningTroll = "" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + player.getName() + "(이)가 아군의 포탑이나 심장을 파괴하려고 시도했습니다!";

        if (GameManager.isStart) {
            if (MapData.mineLoc.contains(location)) {
                Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                    public void run() {
                        RegenBlock.regenBLock(location);
                    }
                }, 5L);
            }
            else if(MapData.redTargetLoc.contains(location)){ //블루팀 건물
                if (PlayerData.isBlue(player)) {
                    Bukkit.broadcastMessage(warningTroll);
                }else{
                    if(player.getItemInHand().getType().equals(Material.AIR)){
                        if(location.equals(MapData.blue_left_tower)) {
                            Bukkit.broadcastMessage(sendBrokeMessage("RED", 1));
                            MoneySystem.giveTowerMoney(TeamSystem.red);
                            --MapData.blueLeft;
                            if (MapData.blueLeft != -1) e.setCancelled(true);
                            else {
                                block.setType(Material.AIR);
                                MapData.blue_left_tower_beacon.getBlock().setType(Material.AIR);
                                block.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
                            }
                        } else if(location.equals(MapData.blue_right_tower)){
                                Bukkit.broadcastMessage(sendBrokeMessage("RED",2));
                                MoneySystem.giveTowerMoney(TeamSystem.red);
                                --MapData.blueRight;
                                if(MapData.blueRight!=-1)  e.setCancelled(true);
                                else{
                                    block.setType(Material.AIR);
                                    MapData.blue_right_tower_beacon.getBlock().setType(Material.AIR);
                                    block.getWorld().playSound(location,Sound.ENTITY_GENERIC_EXPLODE, 10,1);
                                }
                        } else {
                            if(MapData.blueLeft==-1 && MapData.blueRight==-1) GameManager.endGame("red");
                            else{
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "상대팀 포탑을 모두 파괴해야 심장을 파괴할 수 있습니다!");
                                e.setCancelled(true);
                            }
                        }
                    }else{
                        e.setCancelled(true);
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "타워나 요새의 심장은 맨손으로 파괴해야 합니다!");
                    }
                }
            }else if(MapData.blueTargetLoc.contains(location)){ //레드팀 건물
                if (PlayerData.isRed(player)) {
                    Bukkit.broadcastMessage(warningTroll);
                }else{
                    if(player.getItemInHand().getType().equals(Material.AIR)){
                        if(location.equals(MapData.red_left_tower)) {
                            Bukkit.broadcastMessage(sendBrokeMessage("BLUE", 1));
                            MoneySystem.giveTowerMoney(TeamSystem.blue);
                            --MapData.redLeft;
                            if (MapData.redLeft != -1) e.setCancelled(true);
                            else {
                                block.setType(Material.AIR);
                                MapData.red_left_tower_beacon.getBlock().setType(Material.AIR);
                                block.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
                            }
                        } else if(location.equals(MapData.red_right_tower)){
                            Bukkit.broadcastMessage(sendBrokeMessage("BLUE",2));
                            MoneySystem.giveTowerMoney(TeamSystem.blue);
                            --MapData.redRight;
                            if(MapData.redRight!=-1)  e.setCancelled(true);
                            else{
                                block.setType(Material.AIR);
                                MapData.red_right_tower_beacon.getBlock().setType(Material.AIR);
                                block.getWorld().playSound(location,Sound.ENTITY_GENERIC_EXPLODE, 10,1);
                            }
                        } else {
                            if(MapData.redLeft==-1 && MapData.redRight==-1) GameManager.endGame("blue");
                            else{
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "상대팀 포탑을 모두 파괴해야 심장을 파괴할 수 있습니다!");
                                e.setCancelled(true);
                            }
                        }
                    }else{
                        e.setCancelled(true);
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "타워나 요새의 심장은 맨손으로 파괴해야 합니다!");
                    }
                }
            }
            else if(MapData.chest.contains(location)){
                player.sendMessage(ChatColor.GOLD+"[요새의 심장]"+ChatColor.RED+"상자를 성공적으로 제거하였습니다.");
            }else{
                player.sendMessage(ChatColor.GOLD+"[요새의 심장]"+ChatColor.RED+"플레이어가 게임 중에 설치한 블럭만 파괴할 수 있습니다!");
            }
        }else{
            if(!player.isOp()) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.GOLD+"[요새의 심장]"+ChatColor.RED+"관리자만 맵을 수정할 수 있습니다!");
            }
        }
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        if (GameManager.isStart) {
            int warriorLevel;
            int archerLevel;
            int assassinLevel;
            int minerLevel;
            if (e.getView().getTitle().equals(PickAbility.pickTitle)) {
                warriorLevel = PlayerData.getLevel(player, "warrior");
                archerLevel = PlayerData.getLevel(player, "archer");
                assassinLevel = PlayerData.getLevel(player, "assassin");
                minerLevel = PlayerData.getLevel(player, "miner");
                if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(PickAbility.pickWarrior)) {
                        player.getInventory().clear();
                        GiveWarrior.giveItem(player, warriorLevel);
                        e.setCancelled(true);
                        player.closeInventory();
                        if (PlayerData.isRed(player)) {
                            player.teleport(MapData.redStart);
                        } else if (PlayerData.isBlue(player)) {
                            player.teleport(MapData.blueStart);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(PickAbility.pickArcher)) {
                        player.getInventory().clear();
                        GiveArcher.giveItem(player, archerLevel);
                        e.setCancelled(true);
                        player.closeInventory();
                        if (PlayerData.isRed(player)) {
                            player.teleport(MapData.redStart);
                        } else if (PlayerData.isBlue(player)) {
                            player.teleport(MapData.blueStart);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(PickAbility.pickAssassin)) {
                        player.getInventory().clear();
                        GiveAssassin.giveItem(player, assassinLevel);
                        e.setCancelled(true);
                        player.closeInventory();
                        if (PlayerData.isRed(player)) {
                            player.teleport(MapData.redStart);
                        } else if (PlayerData.isBlue(player)) {
                            player.teleport(MapData.blueStart);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(PickAbility.pickMiner)) {
                        player.getInventory().clear();
                        GiveMiner.giveItem(player, minerLevel);
                        e.setCancelled(true);
                        player.closeInventory();
                        if (PlayerData.isRed(player)) {
                            player.teleport(MapData.redStart);
                        } else if (PlayerData.isBlue(player)) {
                            player.teleport(MapData.blueStart);
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            } else if (e.getView().getTitle().equals(UpgradeAbility.upgradeTitle)) {
                warriorLevel = PlayerData.getLevel(player, "warrior");
                archerLevel = PlayerData.getLevel(player, "archer");
                assassinLevel = PlayerData.getLevel(player, "assassin");
                minerLevel = PlayerData.getLevel(player, "miner");
                if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(UpgradeAbility.upgradeWarrior)) {
                        if (warriorLevel < 4) {
                            if (player.getLevel() >= 1000 * warriorLevel) {
                                player.setLevel(player.getLevel() - 1000 * warriorLevel);
                                MoneySystem.saveMoney(player);
                                PlayerData.upgrade(player, "warrior");
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.GREEN + "당신의 전사는 이제 " + ChatColor.YELLOW + PlayerData.getLevel(player, "warrior") + ChatColor.GREEN + "차 입니다.");
                                e.setCancelled(true);
                                player.closeInventory();
                            } else {
                                e.setCancelled(true);
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "포인트 잔액이 부족합니다.!");
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(UpgradeAbility.upgradeArcher)) {
                        if (archerLevel < 4) {
                            if (player.getLevel() >= 1000 * archerLevel) {
                                player.setLevel(player.getLevel() - 1000 * archerLevel);
                                MoneySystem.saveMoney(player);
                                PlayerData.upgrade(player, "archer");
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.GREEN + "당신의 궁수는 이제 " + ChatColor.YELLOW + PlayerData.getLevel(player, "archer") + ChatColor.GREEN + "차 입니다.");
                                e.setCancelled(true);
                                player.closeInventory();
                            } else {
                                e.setCancelled(true);
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "포인트 잔액이 부족합니다.!");
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(UpgradeAbility.upgradeAssassin)) {
                        if (assassinLevel < 4) {
                            if (player.getLevel() >= 1000 * assassinLevel) {
                                player.setLevel(player.getLevel() - 1000 * assassinLevel);
                                MoneySystem.saveMoney(player);
                                PlayerData.upgrade(player, "assassin");
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.GREEN + "당신의 암살자는 이제 " + ChatColor.YELLOW + PlayerData.getLevel(player, "assassin") + ChatColor.GREEN + "차 입니다.");
                                e.setCancelled(true);
                                player.closeInventory();
                            } else {
                                e.setCancelled(true);
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "포인트 잔액이 부족합니다.!");
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(UpgradeAbility.upgradeMiner)) {
                        if (minerLevel < 4) {
                            if (player.getLevel() >= 1000 * minerLevel) {
                                player.setLevel(player.getLevel() - 1000 * minerLevel);
                                MoneySystem.saveMoney(player);
                                PlayerData.upgrade(player, "miner");
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.GREEN + "당신의 광부는 이제 " + ChatColor.YELLOW + PlayerData.getLevel(player, "miner") + ChatColor.GREEN + "차 입니다.");
                                e.setCancelled(true);
                                player.closeInventory();
                            } else {
                                e.setCancelled(true);
                                player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "포인트 잔액이 부족합니다.!");
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        Location location = block.getLocation();
        Material blockType = block.getType();
        if(GameManager.isStart){
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                final Location loc;
                if (blockType.equals(Material.REDSTONE_BLOCK)) {
                    loc = block.getLocation();
                    loc.getBlock().setType(Material.RED_STAINED_GLASS);
                    Location apple = new Location(block.getWorld(), loc.getX(), loc.getY() - 1.0D, loc.getZ());
                    player.playSound(apple, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
                    block.getWorld().dropItem(apple, new ItemStack(Material.APPLE));
                    Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                        public void run() {
                            loc.getBlock().setType(Material.REDSTONE_BLOCK);
                        }
                    }, (long)MapData.appleTime * 20L);
                } else if (blockType.equals(Material.GOLD_BLOCK)) {
                    location.getBlock().setType(Material.YELLOW_STAINED_GLASS);
                    player.playSound(block.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
                    int random = (int)(Math.random() * 3.0D);
                    switch(random) {
                        case 0:
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if ((PlayerData.isRed(player) && PlayerData.isRed(p)) || (PlayerData.isBlue(player) && PlayerData.isBlue(p))) {
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 0), false);
                                    p.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "보너스 효과로 [" + ChatColor.AQUA + "신속" + ChatColor.YELLOW + "] 효과를 얻었습니다!");
                                }
                            }
                            break;
                        case 1:
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if ((PlayerData.isRed(player) && PlayerData.isRed(p)) || (PlayerData.isBlue(player) && PlayerData.isBlue(p))) {
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2400, 0,false), false);
                                    p.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "보너스 효과로 [" + ChatColor.AQUA + "저항" + ChatColor.YELLOW + "] 효과를 얻었습니다!");
                                }
                            }
                            break;
                        case 2:
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if ((PlayerData.isRed(player) && PlayerData.isRed(p)) || (PlayerData.isBlue(player) && PlayerData.isBlue(p))) {
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0), false);
                                    p.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "보너스 효과로 [" + ChatColor.AQUA + "힘" + ChatColor.YELLOW + "] 효과를 얻었습니다!");
                                }
                            }
                            break;
                    }
                    Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                        public void run() {
                            block.setType(Material.GOLD_BLOCK);
                        }
                    }, (long)MapData.buffTime * 20L);
                }
            }

            int amount;
            if (player.getItemInHand().getType().equals(Material.APPLE)) {
                if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 3.0F, 1.0F);
                    player.getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
                    amount = (int)(Math.random() * 3.0D);
                    switch(amount) {
                        case 0:
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_RED + "독사과를 먹었습니다. [" + ChatColor.RED + "독" + ChatColor.DARK_RED + "] 효과를 일시적으로 얻습니다.");
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 2));
                            break;
                        case 1:
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_GREEN + "사과를 먹었습니다. [" + ChatColor.AQUA + "재생" + ChatColor.DARK_GREEN + "] 효과를 일시적으로 얻습니다.");
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
                            break;
                        case 2:
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "보너스! [" + ChatColor.AQUA + "100Point" + ChatColor.GOLD + "] 를 획득하셨습니다.");
                            player.setLevel(player.getLevel() + 100);
                            MoneySystem.saveMoney(player);
                    }
                }
            } else if (player.getItemInHand().getType().equals(Material.BOOK)) {
                if (player.getItemInHand().getItemMeta().getDisplayName().equals("Respawn")) {
                    if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                        double x = player.getLocation().getX();
                        double z = player.getLocation().getZ();
                        if ((((x > 207 && x < 289) && (z > 58 && z < 156)) || ((x > -11 && x < 71) && (z > 58 && z < 156)))) {
                            player.setHealth(0);
                        } else {
                            player.sendMessage("" + ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "기지 밖에서는 사용할 수 없는 아이템입니다.");
                        }
                    }
                } else if (player.getItemInHand().getItemMeta().getDisplayName().equals("Upgrade") && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                    UpgradeAbility.openUpgradeInv(player);
                }
            } else if (player.getItemInHand().getType().equals(Material.PAPER) && player.getItemInHand().getItemMeta().getDisplayName().equals(MoneySystem.point10)) {
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    if (event.getHand().equals(EquipmentSlot.HAND) && player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 10, amount);
                        player.getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - amount);
                    } else if (event.getHand().equals(EquipmentSlot.HAND) && !player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 10, 1);
                        player.getItemInHand().setAmount(amount - 1);
                    }
                }
            } else if (player.getItemInHand().getType().equals(Material.PAPER) && player.getItemInHand().getItemMeta().getDisplayName().equals(MoneySystem.point100)) {
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    if (event.getHand().equals(EquipmentSlot.HAND) && player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 100, amount);
                        player.getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - amount);
                    } else if (event.getHand().equals(EquipmentSlot.HAND) && !player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 100, 1);
                        player.getItemInHand().setAmount(amount - 1);
                    }
                }
            } else if (player.getItemInHand().getType().equals(Material.PAPER) && player.getItemInHand().getItemMeta().getDisplayName().equals(MoneySystem.point1000)) {
                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    if (event.getHand().equals(EquipmentSlot.HAND) && player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 1000, amount);
                        player.getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - amount);
                    } else if (event.getHand().equals(EquipmentSlot.HAND) && !player.isSneaking()) {
                        amount = player.getInventory().getItemInHand().getAmount();
                        MoneySystem.useMoney(player, 1000, 1);
                        player.getItemInHand().setAmount(amount - 1);
                    }
                }
            }
        }
    }
}