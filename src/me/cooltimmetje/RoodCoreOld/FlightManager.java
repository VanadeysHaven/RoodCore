package me.cooltimmetje.RoodCoreOld;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This class has been created on 9-3-2015 at 19:57 by cooltimmetje.
 */
public class FlightManager implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();

        if(PreferencesMenu.flyOn.contains(p.getName())){
            p.setAllowFlight(true);
            if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                p.setFlying(true);
            }
        }
    }

}
