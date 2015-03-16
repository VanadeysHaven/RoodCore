package me.cooltimmetje.RoodCore.Managers;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

/**
 * This class has been created on 15-3-2015 at 12:59 by cooltimmetje.
 */
public class ResourcePackManager implements CommandExecutor {

    public static HashMap<Integer, String> packURL = new HashMap<Integer, String>();
    public static HashMap<String, Integer> playerPack = new HashMap<String, Integer>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        setURL();
        final Player p = (Player) sender;
        if (cmd.getLabel().equalsIgnoreCase("rp")) {
            if (args.length == 1) {
                if (Methods.isInt(args[0])){
                    int pack = Integer.parseInt(args[0]);
                    String downloadURL = packURL.get(pack);
                    if (downloadURL != null) {
                        Methods.msgPlayer("&9ResourcePack> &aAttempting to send you the resourcepack, click yes when prompted.", p);
                        Methods.msgPlayer("&9ResourcePack> &aDownloading can take a while depending on your internet speed!", p);
                        p.setResourcePack(downloadURL);
                        playerPack.put(p.getName(), pack);
                        Methods.updateScoreboard(p);
                    } else {
                        Methods.msgPlayer("&9ResourcePack> &cInvalid pack!", p);
                    }
                } else {
                    Methods.msgPlayer("&9ResourcePack> &cPlease enter a number!", p);
                }
            } else {
                Methods.msgPlayer("&9ResourcePack> &cPlease specify the pack you want to download!", p);
            }
        } else if(cmd.getLabel().equalsIgnoreCase("rpinfo")){
            if(args.length == 1){
                if(args[0].equals("1")){
                    Methods.msgPlayer("&3&m-----&r &2&lResourcePack 1 &3&m-----", p);
                    Methods.msgPlayer("&eOriginal Song &b- &9RP Song", p);
                    Methods.msgPlayer("&e13 &b- &9Darude - Sandstorm", p);
                    Methods.msgPlayer("&eCat &b- &9K3 - Mama Sé", p);
                    Methods.msgPlayer("&eBlocks &b- &9New Kids - Turbo (ft. DJ Paul Elstak)", p);
                    Methods.msgPlayer("&eChrip &b- &9Star Wars - The Imperial March", p);
                    Methods.msgPlayer("&eFar &b- &9DJ Snake - Lean On (ft. MØ)", p);
                    Methods.msgPlayer("&eMall &b- &9Pegboard Nerds - Hero (ft. Elizaveta)", p);
                    Methods.msgPlayer("&eMellohi &b- &9Avicii - The Nights", p);
                    Methods.msgPlayer("&eStal &b- &9Lets Be Friends - FTW", p);
                    Methods.msgPlayer("&eStrad &b- &9Stephen Walking - Dads In Space", p);
                    Methods.msgPlayer("&eWard &b- &9Pixl - The Escape", p);
                    Methods.msgPlayer("&e11 &b- &9DVBBS and Borgeous - TSUNAMI", p);
                    Methods.msgPlayer("&eWait &b- &7&o(original song)", p);
                } else if(args[0].equals("2")){
                    Methods.msgPlayer("&3&m-----&r &2&lResourcePack 2 &3&m-----", p);
                    Methods.msgPlayer("&eOriginal Song &b- &9RP Song", p);
                    Methods.msgPlayer("&e13 &b- &9Deorro - Five Hours", p);
                    Methods.msgPlayer("&eCat &b- &9Rick Astley - Never Gonna Give You Up", p);
                    Methods.msgPlayer("&eBlocks &b- &7&oHeleentje van Cappelle - En Dan Gaan We Naar De Speeltuin", p);
                    Methods.msgPlayer("&eChrip &b- &9Andrew Gold - Spooky Scary Skeletons (Remix)", p);
                    Methods.msgPlayer("&eFar &b- &9Andrew Gold - Spooky Scary Skeletons", p);
                    Methods.msgPlayer("&eMall &b- &9SKRILLEX - Bangarang (ft. Sirah)", p);
                    Methods.msgPlayer("&eMellohi &b- &9Pegboard Nerds - Hero (ft. Elizaveta)[Stonebank Remix]", p);
                    Methods.msgPlayer("&eStal &b- &7&o(original song)", p);
                    Methods.msgPlayer("&eStrad &b- &7&o(original song)", p);
                    Methods.msgPlayer("&eWard &b- &7&o(original song)", p);
                    Methods.msgPlayer("&e11 &b- &7&o(original song)", p);
                    Methods.msgPlayer("&eWait &b- &7&o(original song)", p);
                } else {
                    Methods.msgPlayer("&9RPInfo> &cInvalid pack!", p);
                }
            }
        }
        return true;
    }

    public static void loadResource(Player p){
        if(playerPack.get(p.getName()) != 0){
            setURL();
            String downloadURL = packURL.get(playerPack.get(p.getName()));
            p.setResourcePack(downloadURL);
            Methods.msgPlayer("&9ResourcePack> &aAttempting to send you the resourcepack, click yes when prompted.", p);
            Methods.msgPlayer("&9ResourcePack> &aDownloading can take a while depending on your internet speed!", p);
        }
    }

    public static void setURL(){
        if (packURL.isEmpty()) {
            packURL.put(1, "https://www.dropbox.com/s/kgw83iyhxzn81ql/RoodMusic1.zip?dl=1");
            packURL.put(2, "https://www.dropbox.com/s/9a006cyx0d1g8ea/RoodMusic2.zip?dl=1");
        }
    }

}

//        Methods.msgPlayer("&3&m-----&r &2&lResourcePack 1 &3&m-----", p);
//        Methods.msgPlayer("&eOriginal Song &b- &9RP Song", p);
//        Methods.msgPlayer("&e13 &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eCat &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eBlocks &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eChrip &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eFar &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eMall &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eMellohi &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eStal &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eStrad &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eWard &b- &7&o(original song)", p);
//        Methods.msgPlayer("&e11 &b- &7&o(original song)", p);
//        Methods.msgPlayer("&eWait &b- &7&o(original song)", p);
