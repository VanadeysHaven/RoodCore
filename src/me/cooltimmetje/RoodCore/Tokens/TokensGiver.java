package me.cooltimmetje.RoodCore.Tokens;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * This class has been created on 10-3-2015 at 19:14 by cooltimmetje.
 */
public class TokensGiver {

    public static HashMap<String, Integer> tokensAmount = new HashMap<String, Integer>();
    public static HashMap<String, Integer> tokensTime = new HashMap<String,Integer>();

    public static int tokenTimer = 15;
    public static int tokenAmount = 20;
    public static int doubleChance = 10;

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
                        }
                    } else {
                        curTime = curTime - 1;
                    }
                    tokensAmount.put(p.getName(), curTokens);
                    tokensTime.put(p.getName(), curTime);
                    ConfigManager.saveData(p);
                }
            }
        }, 20, 1200);
    }

}
