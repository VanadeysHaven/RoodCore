package me.cooltimmetje.RoodCore.Managers;

import me.cooltimmetje.RoodCore.GUIs.PreferencesMenu;
import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Methods;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * This class has been created on 10-3-2015 at 18:56 by cooltimmetje.
 */
public class ConfigManager {

    public static void loadData(Player p){
        String uuid = Methods.getUUID(p);

        String fileName = uuid + ".yml";
        File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCore").getDataFolder(), fileName);
        FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

        if(!userFile.exists()){
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Methods.msgPlayer(Main.arrowTag + "&aYou do not have a config file on disk, so we'll generate one for you. :)", p);
            Methods.msgPlayer(Main.arrowTag + "&7&oUUID: " + uuid, p);
        }

        if(!user.contains("tokens")){
            user.set("tokens", 0);
            TokensGiver.tokensAmount.put(p.getName(), 0);
        } else { //load data
            TokensGiver.tokensAmount.put(p.getName(), user.getInt("tokens"));
        }
        if(!user.contains("tokenTime")){
            user.set("tokenTime", TokensGiver.tokenTimer);
            TokensGiver.tokensTime.put(p.getName(), TokensGiver.tokenTimer);
        } else {
            TokensGiver.tokensTime.put(p.getName(), user.getInt("tokenTime"));
        }

        if(!user.contains("flight")){
            user.set("flight", false);
        } else {
            if(user.getBoolean("flight")){
                PreferencesMenu.flyOn.add(p.getName());
            }
        }
        if(!user.contains("pvp")){
            user.set("pvp", false);
        } else {
            if(user.getBoolean("pvp")){
                PreferencesMenu.pvpOn.add(p.getName());
            }
        }

        try {
            user.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file saving failed");
        }
    }

    public static void saveData(Player p){
        String uuid = Methods.getUUID(p);

        String fileName = uuid + ".yml";
        File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCore").getDataFolder(), fileName);
        FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

        user.set("tokens", TokensGiver.tokensAmount.get(p.getName()));
        user.set("tokenTime", TokensGiver.tokensTime.get(p.getName()));

        user.set("flight", PreferencesMenu.flyOn.contains(p.getName()));
        user.set("pvp", PreferencesMenu.pvpOn.contains(p.getName()));

        try {
            user.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file saving failed");
        }
    }

}
