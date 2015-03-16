package me.cooltimmetje.RoodCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

/**
 * This class has been created on 14-3-2015 at 12:36 by cooltimmetje.
 */
public class DeathEvent implements Listener{

    public HashMap<Integer, String> deathSayings = new HashMap<Integer, String>();
    public int sayings = 20;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.setDeathMessage(null);
        if(deathSayings.isEmpty()){
            deathSayings.put(1, "N00B");
            deathSayings.put(2, "*claps*");
            deathSayings.put(3, "...");
            deathSayings.put(4, "Do you even Minecraft bruh?!");
            deathSayings.put(5, "rekt");
            deathSayings.put(6, "shrekt");
            deathSayings.put(7, "Well, that escalated quick!");
            deathSayings.put(8, "Who said that you should hug the creeper?");
            deathSayings.put(9, "Aaaaand! REEEEEKTTT!");
            deathSayings.put(10, "*facepalm*");
            deathSayings.put(11, "*grabs popcorn* This is quite enjoyable!");
            deathSayings.put(12, "R.I.P stands for 'RIP in pieces'");
            deathSayings.put(13, "Welp, gg!");
            deathSayings.put(14, "Again?!");
            deathSayings.put(15, "That's gonna leave a mark!");
            deathSayings.put(16, "Sigh...");
            deathSayings.put(17, "Don't fuck with the boss...");
            deathSayings.put(18, "Red is not always the answer");
            deathSayings.put(19, "i liek trains");
            deathSayings.put(20, "The cake is a lie.");
        }

        Player p = event.getEntity();
        int msg = Methods.randomInt(1, sayings);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            if(pl == p) {
                Methods.msgPlayer("&c&lYOU DIED! &b&o" + deathSayings.get(msg), pl);
            } else {
                Methods.msgPlayer("&c&l" + p.getName() + " DIED! &b&o"+ deathSayings.get(msg), pl);
            }
        }
    }

}
