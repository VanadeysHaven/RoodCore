package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class has been created on 7-3-2015 at 19:59 by cooltimmetje.
 */
public class Tokens implements CommandExecutor{

    public static HashMap<String, Integer> tokenTime = new HashMap<String, Integer>();
    public static HashMap<String, Integer> tokens = new HashMap<String, Integer>();

    static int tokenInterval = 15;
    static int tokenAmount = 20;
    static int doubleChance = 35;

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
                                } else {
                                    Main.msgPlayer("&9Tokens> &a" + p.getDisplayName() + " &ajust got lucky and recieved double tokens!", p);
                                }
                            }
                        } else {
                            tokenCount = tokenCount + tokenAmount;
                        }
                        tokens.put(p.getName(), tokenCount);
                        user.set("tokens", tokenCount);
                        Main.msgPlayer("&9+" + tokenAmount + " tokens! (" + tokenInterval + " minutes online!)", p);
                        time = tokenInterval;
                    }
                    user.set("tokenTime", time);
                    tokenTime.put(p.getName(), time);

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

}
