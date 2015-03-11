package me.cooltimmetje.RoodCore.Managers;

import me.cooltimmetje.RoodCore.GUIs.PreferencesMenu;
import me.cooltimmetje.RoodCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This class has been created on 9-3-2015 at 19:57 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class FlightManager implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Player p = event.getPlayer();

                if(PreferencesMenu.flyOn.contains(p.getName())){
                    p.setAllowFlight(true);
                    if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                        p.setFlying(true);
                    }
                }

            }
        }, 20);
    }

}
