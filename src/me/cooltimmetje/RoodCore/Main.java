package me.cooltimmetje.RoodCore;

import me.cooltimmetje.RoodCore.GUIs.PreferencesMenu;
import me.cooltimmetje.RoodCore.GUIs.TimeCommandGUI;
import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Managers.FlightManager;
import me.cooltimmetje.RoodCore.Managers.PvPManager;
import me.cooltimmetje.RoodCore.Managers.ResourcePackManager;
import me.cooltimmetje.RoodCore.Tokens.TokensCommand;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import me.cooltimmetje.RoodCore.Tokens.TokensShop.MainMenu;
import me.cooltimmetje.RoodCore.Tokens.TokensShop.Rankup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
        registerEvents(this, new PreferencesMenu(), new FlightManager(), new TimeCommandGUI(), new XPStorage(), new JoinQuitEvent(), new PvPManager(), new MainMenu(), new Rankup(),
                new DeathEvent(), new TokensGiver(), new JukeboxFirework());

        getLogger().info("[R00DCore] Registering Commands...");
        getCommand("prefs").setExecutor(new PreferencesMenu());
        getCommand("time").setExecutor(new TimeCommandGUI());
        getCommand("cooldown").setExecutor(new CooldownList());
        getCommand("codetim").setExecutor(new StaffPesten());
        getCommand("coderood").setExecutor(new StaffPesten());
        getCommand("tokens").setExecutor(new TokensCommand());
        getCommand("tokenshop").setExecutor(new MainMenu());
        getCommand("rankup").setExecutor(new Rankup());
        getCommand("swaggergear").setExecutor(new SwaggerGear());
        getCommand("myrood").setExecutor(new ScoreboardToggle());
        getCommand("rp").setExecutor(new ResourcePackManager());
        getCommand("rpinfo").setExecutor(new ResourcePackManager());

        getLogger().info("[R00DCore] Adding recipes...");
        getServer().addRecipe(CustomRecipes.boneMealGrind);

        getLogger().info("[R00DCore] Hooking into APIs...");
        if (getServer().getPluginManager().getPlugin("TitleManager") != null && getServer().getPluginManager().getPlugin("TitleManager").isEnabled())
            getLogger().info("[RCR] Successfully hooked into TitleManager!");
        else {
            getLogger().warning("[RCR] Failed to hook into TitleManager, disabling plugin!");
            getPluginLoader().disablePlugin(this);
        }

        for(final Player p : Bukkit.getOnlinePlayers()){
            ConfigManager.loadData(p);
            Methods.loadScoreboard(p);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    Methods.updateScoreboard(p);
                }
            },60);
        }

        TokensGiver.tokenTimerGiver();
        StopLoad();
        getLogger().info("[R00DCore] Done! Load time: " + loadFinal + "ms");
        Bukkit.broadcastMessage("&2Loading complete! &aLoad Time: &c".replace('&', '§') + loadFinal + "ms");
        Bukkit.broadcastMessage("&3&m-----&r &4&lR00DCore &3&m-----".replace('&','§'));
    }

    public void onDisable(){
        plugin = null;//To stop memory leeks
        for(Player p : Bukkit.getOnlinePlayers()){
            Methods.removeScoreboard(p);
        }
        getServer().resetRecipes();
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

    public static String arrowTag = "&3>&c>&9> &f".replace('&', '§');

}
