package me.cooltimmetje.RoodCore.Managers;

import me.cooltimmetje.RoodCore.GUIs.PreferencesMenu;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.*;

/**
 * This class has been created on 9-3-2015 at 20:33 by cooltimmetje.
 */
public class PvPManager implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            if(!PreferencesMenu.pvpOn.contains(event.getEntity().getName()) || !PreferencesMenu.pvpOn.contains(event.getDamager().getName())){
                event.setCancelled(true);
            } else {
                return;
            }
        } else {
            return;
        }
    }

    @EventHandler
    public void onBowHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (event.getEntity() instanceof Player && arrow.getShooter() instanceof Player) {
                Player shot = (Player) event.getEntity();
                Player damager = (Player) arrow.getShooter();

                if (!PreferencesMenu.pvpOn.contains(shot.getName()) || !PreferencesMenu.pvpOn.contains(damager.getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

//    @EventHandler
//    public void onItemUse(PlayerInteractEvent event){
//        Player p = event.getPlayer();
//        if(p.getItemInHand() != null){
//            if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL){
//                if(eb)
//            }
//        } else {
//            return;
//        }
//    }

    //TODO add potions

}
