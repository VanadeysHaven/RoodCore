package me.cooltimmetje.RoodCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class has been created on 7-3-2015 at 15:47 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        e.setJoinMessage("&9Join> &c".replace('&', '§') + p.getName() + " &ajoined the server!".replace('&', '§'));

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Main.loadProfile(p);

                Main.sendTitle("&6&lWelcome, " + p.getDisplayName(), "&bTo &c&lThe &4#&lTeamR00D &c&lNetwork&b!", 20, 60, 20, p);

                for (Player pl : Bukkit.getOnlinePlayers()) {
                    Main.sendAction("&9Join> " + p.getDisplayName(), pl);
                }

            }
        }, 20);


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("&9Quit> &c".replace('&', '§') + p.getName() + " &aleft the server!".replace('&', '§'));

        for (Player pl : Bukkit.getOnlinePlayers()) {
            Main.sendAction("&9Quit> " + p.getDisplayName(), pl);
        }

    }
}