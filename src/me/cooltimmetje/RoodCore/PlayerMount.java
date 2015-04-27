package me.cooltimmetje.RoodCore;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * This class has been created on 26-4-2015 at 16:19 by cooltimmetje.
 */
public class PlayerMount implements Listener {

    @EventHandler
    public void onRightClickPlayer(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        if(event.getRightClicked() instanceof Player){
            Player mount = (Player) event.getRightClicked();
            if(p.hasPermission("roodcore.playermount")){
                mount.setPassenger(p);
            }

        } else if (event.getRightClicked() instanceof TNTPrimed){
            Entity mount = event.getRightClicked();
            if(!p.getName().equals("ThoThoKill")) {
                mount.setPassenger(p);
            }
        }
    }

}
