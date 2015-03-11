package me.cooltimmetje.RoodCoreOld;

import com.evilmidget38.UUIDFetcher;
import me.cooltimmetje.RoodCoreOld.Tokens.Tokens;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

/**
 * This class has been created on 10-3-2015 at 16:55 by cooltimmetje.
 */
public class DataManager {

    public static void saveAll(Player p){
        String name, uuid;
        name = p.getName();
        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String fileName = uuid + ".yml";
        File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCoreOld").getDataFolder(), fileName);
        FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

        user.set("tokens", Tokens.tokens.get(p.getName()));
        user.set("tokenTime", Tokens.tokenTime.get(p.getName()));
    }

    public static void getGroup(Player p){
        String displayName = p.getDisplayName();

        int i = displayName.indexOf(' ');
        String groupName = displayName.substring(0, i);



    }

}
