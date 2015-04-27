package me.cooltimmetje.RoodCore.Teleport.Request;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * This class has been created on 26-4-2015 at 12:11 by cooltimmetje.
 */
public class TpaTeleport {

    public static void tpaPlayer(final Player p, final Player target){
        Methods.msgPlayerTag("Tpa", "Request &2accepted&a!", target);
        Methods.msgPlayerTag("Tpa", target.getDisplayName() + " &2accepted&a your request! Starting teleport...", p);

        Methods.sendTitle("&eTeleporting... &3Please wait...", "&eTP'ing you to: " + target.getDisplayName(), 20, 80, 0, p);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 120, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 10));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 130));

        p.setFlying(false);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Methods.sendTitle("&eTeleport Complete!", "&eYou TP'ed to: " + target.getDisplayName(), 20, 60, 20, p);

                p.teleport(target.getLocation());
            }
        },100);
    }

}
