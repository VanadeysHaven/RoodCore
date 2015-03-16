package me.cooltimmetje.RoodCore.Tokens;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

/**
 * This class has been created on 10-3-2015 at 19:14 by cooltimmetje.
 */
public class TokensGiver implements Listener {

    public static HashMap<String, Integer> tokensAmount = new HashMap<String, Integer>();
    public static HashMap<String, Integer> tokensTime = new HashMap<String,Integer>();
    public static HashMap<String, Integer> killedNight = new HashMap<String, Integer>();

    public static int tokenTimer = 15;
    public static int tokenAmount = 20;
    public static int doubleChance = 10;
    public static int nightKills = 10;
    public static int nightTokens = 5;

    public static void tokenTimerGiver(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    int curTokens = tokensAmount.get(p.getName());
                    int curTime = tokensTime.get(p.getName());
                    if(curTime == 0){
                        curTime = tokenTimer;
                        int doubleInt = Methods.randomInt(1, 100);
                        if(doubleInt <= doubleChance){
                            curTokens = curTokens + tokenAmount * 2;
                            Methods.msgPlayer("&9+" + tokenAmount * 2 + " tokens! (" + tokenTimer + " minutes online!)", p);
                            Methods.sendAction("&9+" + tokenAmount * 2 + " tokens! (" + tokenTimer + " minutes online!)", p);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 100 ,1);
                            for(Player pl : Bukkit.getOnlinePlayers()){
                                if(p == pl){
                                    Methods.msgPlayer("&9Tokens> &aYou just got lucky and earned double tokens!", pl);
                                } else {
                                    Methods.msgPlayer("&9Tokens> " + p.getDisplayName() + " &ajust got lucky and earned double tokens!", pl);
                                }
                            }
                        } else {
                            curTokens = curTokens + tokenAmount;
                            Methods.msgPlayer("&9+" + tokenAmount + " tokens! (" + tokenTimer + " minutes online!)", p);
                            Methods.sendAction("&9+" + tokenAmount + " tokens! (" + tokenTimer + " minutes online!)", p);
                            p.playSound(p.getLocation(), Sound.NOTE_PIANO, 100, 1);
                        }
                    } else {
                        curTime = curTime - 1;
                    }
                    tokensAmount.put(p.getName(), curTokens);
                    tokensTime.put(p.getName(), curTime);
                    Methods.updateTab(p, tokensAmount.get(p.getName()));
                    Methods.updateScoreboard(p);
                    ConfigManager.saveData(p);
                }
            }
        }, 20, 1200);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onMobKill(EntityDeathEvent e){
        if(e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton || e.getEntity() instanceof Spider ||
                e.getEntity() instanceof Creeper || e.getEntity() instanceof  Enderman || e.getEntity() instanceof Ghast ||
                e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Slime || e.getEntity() instanceof Witch ||
                e.getEntity() instanceof Endermite || e.getEntity() instanceof Silverfish || e.getEntity() instanceof Blaze){
            if(e.getEntity().getKiller() != null) {
                Player killer = e.getEntity().getKiller();
                if(!killedNight.containsKey(killer.getName())){
                    killedNight.put(killer.getName(), 0);
                }
                int killedNightCount = killedNight.get(killer.getName());
                killedNightCount = killedNightCount + 1;
                if(killedNightCount >= nightKills){
                    int pToken = tokensAmount.get(killer.getName()) + nightTokens;
                    Methods.msgPlayer("&9+" + nightTokens + " tokens! (Killed " + nightKills + " night mobs!)", killer);
                    Methods.sendAction("&9+" + nightTokens + " tokens! (Killed " + nightKills + " night mobs!)", killer);
                    killer.playSound(killer.getLocation(), Sound.NOTE_PIANO, 100, 1);
                    tokensAmount.put(killer.getName(), pToken);
                    ConfigManager.saveData(killer);
                    killedNightCount = 0;
                }
                killedNight.put(killer.getName(), killedNightCount);
                Methods.updateScoreboard(killer);
            }
        }
    }

}
