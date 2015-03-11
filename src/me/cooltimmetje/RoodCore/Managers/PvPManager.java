package me.cooltimmetje.RoodCore.Managers;

import me.cooltimmetje.RoodCore.GUIs.PreferencesMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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

    //TODO add potions
    //TODO add bow

}
