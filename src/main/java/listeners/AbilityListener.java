package listeners;

import ability.ArcherSkill;
import ability.AssassinSkill;
import ability.WarriorSkill;
import data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import system.GameManager;
import system.Main;
import java.util.UUID;

public class AbilityListener implements Listener {

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (player.getItemInHand().getType().equals(Material.PURPLE_DYE) && player.getItemInHand().getItemMeta().getDisplayName().equals("Attack SKill")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                WarriorSkill.useAttack(player);
            }
        } else if (player.getItemInHand().getType().equals(Material.CYAN_DYE) && player.getItemInHand().getItemMeta().getDisplayName().equals("Tank SKill")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                WarriorSkill.useTank(player);
            }
        } else if (player.getItemInHand().getType().equals(Material.LIGHT_GRAY_DYE) && player.getItemInHand().getItemMeta().getDisplayName().equals("Sniper Skill")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                ArcherSkill.useSniper(player);
            }
        } else if (player.getItemInHand().getType().equals(Material.GRAY_DYE) && player.getItemInHand().getItemMeta().getDisplayName().equals("KnockBack Skill")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                ArcherSkill.useKnockBack(player);
            }
        } else if (player.getItemInHand().getType().equals(Material.PINK_DYE) && player.getItemInHand().getItemMeta().getDisplayName().equals("Hide Skill")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                AssassinSkill.useHide(player);
            }
        } else if ((player.getItemInHand().getType().equals(Material.LIME_DYE)) && (player.getItemInHand().getItemMeta().getDisplayName().equals("Teleport Skill"))) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
                AssassinSkill.useTeleport(player);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (GameManager.isStart) {
            if (AssassinSkill.vanished.contains(player)) {
                if (!action.equals(Action.PHYSICAL)) {
                    setHideCool(player);
                }
            }
        }
    }

    public void onDamage(EntityDamageEvent e) {
        if (GameManager.isStart) {
            if (e.getEntity() instanceof Villager) {
                e.setCancelled(true);
            } else if (e.getEntity() instanceof Player) {
                Player player = (Player)e.getEntity();
                UUID uuid = player.getUniqueId();
                if (WarriorSkill.attack4.containsKey(player.getUniqueId())) {
                    long coolLeft = WarriorSkill.attack4.get(uuid) / 1000L + (long)WarriorSkill.attackCool4 - System.currentTimeMillis() / 1000L;
                    if (coolLeft >= 25L) {
                        double damage = e.getDamage();
                        player.damage(damage);
                        e.setCancelled(true);
                    }
                }
                if(AssassinSkill.vanished.contains(player)){
                    setHideCool(player);
                }
            }
        }
    }

    public void setHideCool(Player player) {
        for(Player p : Bukkit.getOnlinePlayers()){
            p.showPlayer(Main.getInstance(),player);
        }
        int level = PlayerData.getLevel(player, "assassin");
        switch(level) {
            case 1:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 10);
                AssassinSkill.vanished.remove(player);
                AssassinSkill.hide1.put(player.getUniqueId(), System.currentTimeMillis());
                player.setCooldown(Material.PINK_DYE, AssassinSkill.cool1 * 20);
                break;

            case 2:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 10);
                AssassinSkill.vanished.remove(player);
                AssassinSkill.hide2.put(player.getUniqueId(), System.currentTimeMillis());
                player.setCooldown(Material.PINK_DYE, AssassinSkill.cool2 * 20);
                break;

            case 3:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 10);
                AssassinSkill.vanished.remove(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 70,0, true));
                AssassinSkill.hide3.put(player.getUniqueId(), System.currentTimeMillis());
                player.setCooldown(Material.PINK_DYE, AssassinSkill.cool3 * 20);
                break;

            case 4:
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10, 10);
                AssassinSkill.vanished.remove(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 70,1, true));
                AssassinSkill.hide4.put(player.getUniqueId(), System.currentTimeMillis());
                player.setCooldown(Material.PINK_DYE, AssassinSkill.cool4 * 20);
                break;
        }
    }


    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (GameManager.isStart) {
            if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player) {
                Arrow arrow = (Arrow) e.getDamager();
                Player damaged = (Player) e.getEntity();
                UUID uuid = arrow.getUniqueId();
                if (ArcherSkill.bullet.contains(uuid)) {
                    ProjectileSource shooter = ((Projectile) e.getDamager()).getShooter();
                    Player player = (Player) shooter;
                    e.setDamage(0.0D);
                    int shooterLevel = PlayerData.getLevel(player, "archer");
                    switch (shooterLevel) {
                        case 1:
                            if (damaged.getHealth() < 5.0D) {
                                damaged.setHealth(0.0D);
                            } else {
                                damaged.setHealth(damaged.getHealth() - 5.0D);
                            }

                            arrow.remove();
                            break;
                        case 2:
                            if (damaged.getHealth() < 6.0D) {
                                damaged.setHealth(0.0D);
                            } else {
                                damaged.setHealth(damaged.getHealth() - 6.0D);
                            }

                            arrow.remove();
                            break;
                        case 3:
                            if (damaged.getHealth() < 8.0D) {
                                damaged.setHealth(0.0D);
                            } else {
                                damaged.setHealth(damaged.getHealth() - 8.0D);
                            }

                            arrow.remove();
                            break;
                        case 4:
                            if (damaged.getHealth() < 10.0D) {
                                damaged.setHealth(0.0D);
                            } else {
                                damaged.setHealth(damaged.getHealth() - 10.0D);
                            }
                            arrow.remove();
                    }
                }
            }
        }
    }
}
