package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import me.cooltimmetje.RoodCore.Tokens.Tokens;
import me.cooltimmetje.RoodCore.Tokens.TokensShop;
import net.md_5.bungee.api.ChatColor;
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
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ess rel");
        Bukkit.broadcastMessage("&3&m-----&r &4&lR00DCore &3&m-----".replace('&', '§'));
        Bukkit.broadcastMessage("&eLoading... &7&oPlease wait...".replace('&','§'));
        this.saveDefaultConfig();

        plugin = this;

        getLogger().info("[R00DCore] Registering Events...");
        registerEvents(this, new JoinQuitEvent(), new TimeCommandGUI(), new XPStorage(), new Tokens(), new TokensShop(), new PreferencesMenu(), new FlightManager(), new PvPManager());

        getLogger().info("[R00DCore] Registering Commands...");
        getCommand("time").setExecutor(new TimeCommandGUI());
        getCommand("cooldown").setExecutor(new CooldownList());
        getCommand("tokens").setExecutor(new Tokens());
        getCommand("tokenshop").setExecutor(new TokensShop());
        getCommand("codetim").setExecutor(new StaffPesten());
        getCommand("coderood").setExecutor(new StaffPesten());
        getCommand("prefs").setExecutor(new PreferencesMenu());
        getCommand("fly").setExecutor(new PreferencesMenu());

        getLogger().info("[R00DCore] Adding recipes...");
        getServer().addRecipe(CustomRecipes.boneMealGrind);

        getLogger().info("[R00DCore] Hooking into APIs...");
        if (getServer().getPluginManager().getPlugin("TitleManager") != null && getServer().getPluginManager().getPlugin("TitleManager").isEnabled())
            getLogger().info("[RCR] Successfully hooked into TitleManager!");
        else {
            getLogger().warning("[RCR] Failed to hook into TitleManager, disabling plugin!");
            getPluginLoader().disablePlugin(this);
        }

        Tokens.tokenGiver();
        for(Player p : Bukkit.getOnlinePlayers()){
            loadProfile(p);
        }
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

        getTokens(p, userFile, user);
        getGroup(p, userFile, user);
        getSettings(p, userFile, user);
    }

    private static void getTokens(Player p, File userFile, FileConfiguration user) {

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

    private static void getSettings(Player p, File userFile, FileConfiguration user) {
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

    public static void getGroup(Player p, File userFile, FileConfiguration user){
        String displayName = p.getDisplayName();

        int i = displayName.indexOf(' ');
        String groupName = displayName.substring(0, i);

        groupName = ChatColor.stripColor(groupName.replace('[', ' ').replace(']', ' ').replace("'", " ").replace("+", "Plus").trim());

        if(!user.contains("group")){
            user.set("group", groupName);
        } else {
            String onFile = user.getString("group");
            if(onFile != groupName){
                user.set("group", groupName.trim());
            }
        }

        try {
            user.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file saving failed");
        }
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

    public static void sendTitle(String title, String subTitle, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), subTitle.replace('&', '§')).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendMain(String title, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendSub(String title, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), TitleObject.TitleType.SUBTITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendAction(String title, Player p){
        new ActionbarTitleObject(title.replace('&', '§')).send(p);
    }

    public static void Broadcast(String msg){
        Bukkit.broadcastMessage(msg.replace('&','§'));
    }

    public static void msgPlayer(String msg, Player p){
        p.sendMessage(msg.replace('&', '§'));
    }
    public static String arrowTag = "&3>&c>&9> &f".replace('&', '§');

}
