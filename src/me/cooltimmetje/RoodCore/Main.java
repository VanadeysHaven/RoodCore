package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class has been created on 7-3-2015 at 14:43 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class Main extends JavaPlugin{
    private static Plugin plugin;
    public long loadStart;
    public long loadStop;
    public long loadFinal;

    public void onEnable(){
        StartLoad();
        Bukkit.broadcastMessage("&3&m-----&r &4&lR00DCore &3&m-----".replace('&','§'));
        Bukkit.broadcastMessage("&eLoading... &7&oPlease wait...".replace('&','§'));
        this.saveDefaultConfig();

        for(Player p : Bukkit.getOnlinePlayers()){
            loadProfile(p);
        }

        plugin = this;

        getLogger().info("[R00DCore] Registering Events...");
        registerEvents(this, new JoinQuitEvent(), new TimeCommandGUI(), new XPStorage());

        getLogger().info("[R00DCore] Registering Commands...");
        getCommand("time").setExecutor(new TimeCommandGUI());
        getCommand("cooldown").setExecutor(new CooldownList());
        getCommand("tokens").setExecutor(new Tokens());

//        getLogger().info("[R00DCore] Adding recipes...");
//        getServer().addRecipe(...)

//        getLogger().info("[R00DCore] Hooking into APIs...");
//        if (getServer().getPluginManager().getPlugin("TitleManager") != null && getServer().getPluginManager().getPlugin("TitleManager").isEnabled())
//            getLogger().info("[RCR] Successfully hooked into TitleManager!");
//        else {
//            getLogger().warning("[RCR] Failed to hook into TitleManager, disabling plugin!");
//            getPluginLoader().disablePlugin(this);
//        }

        Tokens.tokenGiver();
        StopLoad();
        getLogger().info("[R00DCore] Done! Load time: " + loadFinal + "ms");
        Bukkit.broadcastMessage("&2Loading complete! &aLoad Time: &c".replace('&', '§') + loadFinal + "ms");
        Bukkit.broadcastMessage("&3&m-----&r &4&lR00DCore &3&m-----".replace('&','§'));
    }

    public void onDisable(){
        plugin = null;//To stop memory leeks
        getServer().resetRecipes();

        for(Player p : Bukkit.getOnlinePlayers()){
            saveProfile(p);
        }
    }

    public static void loadProfile(Player p) {
        p.sendMessage(arrowTag + "&b&oLoading your profile from disk... &8(".replace('&', '§') + p.getDisplayName() + "&8)".replace('&', '§'));
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1 , 100);

        getTokens(p);
    }

    private static void getTokens(Player p) {
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

        if(!userFile.exists()){
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.msgPlayer(Main.arrowTag + "&aYou do not have a config file on disk, so we'll generate one for you. :)", p);
            Main.msgPlayer(Main.arrowTag + "&7&oUUID: " + uuid, p);
        }

        if(!user.contains("tokens")){
            user.set("tokens", 0);
            try {
                user.save(userFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!user.contains("tokenTime")){
            int timeToken = Tokens.tokenInterval;
            user.set("tokenTime", timeToken);
            try {
                user.save(userFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int tokens = user.getInt("tokens");
        Tokens.tokens.put(p.getName(), tokens);
        int tokenTime = user.getInt("tokenTime");
        Tokens.tokenTime.put(p.getName(), tokenTime);//
    }

    public void saveProfile(Player p){
        p.sendMessage(arrowTag + "&b&oSaving your profile to disk... &8(".replace('&', '§') + p.getDisplayName() + "&8)".replace('&', '§'));
        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1 , 100);
    }

    public void StartLoad(){
        loadStart = System.currentTimeMillis();
    }

    public void StopLoad(){
        loadStop = System.currentTimeMillis();
        loadFinal = (loadStop - loadStart);
    }

    public static void registerEvents(Plugin plugin, Listener... listeners) {
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }

    }

    public static Plugin getPlugin(){
        return plugin;
    }

    public static void createDisplay(Material m, int amount, int data, String name, String lore, Inventory inv, int slotNumber){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(name.replace('&', '§'));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(lore.replace('&', '§'));
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slotNumber - 1, item);
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void Broadcast(String msg){
        Bukkit.broadcastMessage(msg.replace('&','§'));
    }

    public static void msgPlayer(String msg, Player p){
        p.sendMessage(msg.replace('&', '§'));
    }
    public static String arrowTag = "&3>&c>&9> &f".replace('&', '§');

}
