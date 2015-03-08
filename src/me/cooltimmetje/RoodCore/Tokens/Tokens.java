package me.cooltimmetje.RoodCore.Tokens;

import com.evilmidget38.UUIDFetcher;
import me.cooltimmetje.RoodCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class has been created on 7-3-2015 at 19:59 by cooltimmetje.
 */
public class Tokens implements CommandExecutor,Listener{

    public static HashMap<String, Integer> tokenTime = new HashMap<String, Integer>();
    public static HashMap<String, Integer> tokens = new HashMap<String, Integer>();
    public static HashMap<String, Integer> killedNight = new HashMap<String, Integer>();

    public static int tokenInterval = 15;
    static int tokenAmount = 20;
    static int doubleChance = 10;
    static int nightKills = 10;
    static int nightTokens = 5;

    public static void tokenGiver(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {

                for(Player p : Bukkit.getOnlinePlayers()){
                    String name, uuid;
                    name = p.getName();
                    try {
                        uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

                    String fileName = uuid + ".yml";
                    File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCore").getDataFolder(), fileName);
                    FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

                    int time = tokenTime.get(p.getName());
                    time = time - 1;
                    if(time == 0){
                        int tokenCount = tokens.get(p.getName());
                        int doubleInt = Main.randomInt(1, 100);
                        if(doubleInt <= doubleChance){
                            tokenCount = tokenCount + tokenAmount * 2;
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                if (pl == p) {
                                    Main.msgPlayer("&9Tokens> &aYou just got lucky and recieved double tokens!", p);
                                    Main.msgPlayer("&9+" + tokenAmount * 2 + " tokens! (" + tokenInterval + " minutes online!)", p);
                                } else {
                                    Main.msgPlayer("&9Tokens> &a" + p.getDisplayName() + " &ajust got lucky and recieved double tokens!", pl);
                                }
                            }
                        } else {
                            tokenCount = tokenCount + tokenAmount;
                            Main.msgPlayer("&9+" + tokenAmount + " tokens! (" + tokenInterval + " minutes online!)", p);
                        }
                        tokens.put(p.getName(), tokenCount);

                        time = tokenInterval;
                    }
                    user.set("tokens", tokens.get(p.getName()));
                    user.set("tokenTime", time);
                    tokenTime.put(p.getName(), time);
                    Main.getGroup(p, userFile, user);

                    try {
                        user.save(userFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 20, 1200);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("tokens")){
            Main.msgPlayer("&9Tokens> &aYou currently have &9" + tokens.get(p.getName()) + " tokens&a! You'll get more in &9" + tokenTime.get(p.getName()) + "m&a!", p);
        }
        return true;
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onMobKill(EntityDeathEvent e){
        if(e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton || e.getEntity() instanceof Spider ||
                e.getEntity() instanceof  Creeper || e.getEntity() instanceof  Enderman || e.getEntity() instanceof Ghast ||
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
                    int pToken = tokens.get(killer.getName()) + nightTokens;
                    Main.msgPlayer("&9+" + nightTokens + " tokens! (Killed " + nightKills + " night mobs!)", killer);
                    tokens.put(killer.getName(), pToken);
                    saveToFile(killer);
                    killedNightCount = 0;
                }
                killedNight.put(killer.getName(), killedNightCount);
            }
        }
    }

    public static void saveToFile(Player p){
        String name, uuid;
        name = p.getName();
        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String fileName = uuid + ".yml";
        File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCore").getDataFolder(), fileName);
        FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

        user.set("tokens", tokens.get(p.getName()));

        try {
            user.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file saving failed");
        }
    }

}
