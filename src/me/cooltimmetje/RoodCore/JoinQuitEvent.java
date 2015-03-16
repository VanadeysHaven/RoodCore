package me.cooltimmetje.RoodCore;

import com.avaje.ebean.enhance.asm.commons.Method;
import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Managers.ResourcePackManager;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class has been created on 10-3-2015 at 20:41 by cooltimmetje.
 */
public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player p = event.getPlayer();

        event.setJoinMessage("&9Join> &c".replace('&', '§') + p.getName() + " &ajoined the game!".replace('&', '§'));
        ConfigManager.loadData(p);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Methods.sendTitle("&6&lWelcome, " + p.getDisplayName(), "&bTo &c&lThe &4#&lTeamR00D &c&lNetwork&b!", 20, 60, 20, p);

                Methods.updateTab(p, TokensGiver.tokensAmount.get(p.getName()));
                Methods.loadScoreboard(p);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    Methods.sendAction("&9Join> " + p.getDisplayName(), pl);
                    Methods.updateScoreboard(pl);
                    ResourcePackManager.loadResource(p);
                }
            }
        }, 20);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player p = event.getPlayer();
        event.setQuitMessage("&9Quit> &c".replace('&', '§') + p.getName() + " &aleft the server!".replace('&', '§'));

        for (final Player pl : Bukkit.getOnlinePlayers()) {
            Methods.sendAction("&9Quit> " + p.getDisplayName(), pl);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Methods.updateScoreboard(pl);
                    Methods.removeScoreboard(p);
                }
            },10);

        }
    }

}
