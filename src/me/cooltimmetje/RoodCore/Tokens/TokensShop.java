package me.cooltimmetje.RoodCore.Tokens;

import com.evilmidget38.UUIDFetcher;
import me.cooltimmetje.RoodCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class has been created on 8-3-2015 at 14:30 by cooltimmetje.
 */
public class TokensShop implements CommandExecutor,Listener{

    public HashMap<Integer, String> rankNames = new HashMap<Integer, String>();
    public HashMap<String, Integer> rankID = new HashMap<String, Integer>();

    int rankCost = 250;
    int codeTimCost = 80;

    public void shopGUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, "&7&oToken Shop".replace('&', '§'));

        Main.createDisplay(Material.NETHER_STAR, 1, 0, "&a&lRankup", "&7&lClick here to buy a rankup!", inv, 13);
        Main.createDisplay(Material.CHEST, 1, 0, "&a&lShop", "&7&lClick here to buy a cool stuff!", inv, 15);

        Main.createDisplay(Material.GOLD_NUGGET, 1, 0, "&a&lYour tokens: &b" + Tokens.tokens.get(p.getName()), null, inv, 32);

        p.openInventory(inv);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        shopGUI(p);
        return true;
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Token Shop"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case NETHER_STAR:
                openRankup(p);
                break;
            case CHEST:
                openShop(p);
            default:
                break;
        }
    }

    public void openRankup(Player p) {
        if(rankNames.isEmpty()){
            rankNames.put(1, "User");
            rankNames.put(2, "UserPlus");
            rankNames.put(3, "UserPlusPlus");
            rankNames.put(4, "Noob");
        }
        if(rankID.isEmpty()){
            rankID.put("User", 1);
            rankID.put("UserPlus", 2);
            rankID.put("UserPlusPlus", 3);
            rankID.put("Noob", 4);
        }
        Inventory inv = Bukkit.createInventory(null, 27, "&7&oRankup".replace('&', '§'));

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

        Main.createDisplay(Material.HARD_CLAY, 1, 13, "&2&lConfirm", "&7&oThis is going to cost you: &9" + rankCost + " tokens", inv, 12);

        String curGroup = user.getString("group");
        int curID = rankID.get(curGroup);
        String nextGroup = rankNames.get(curID + 1);
        Main.createDisplay(Material.NETHER_STAR, 1, 0, "&a&o" + curGroup + " &b-> &a&o" + nextGroup, "&aCost: &b" + rankCost, inv, 14);

        Main.createDisplay(Material.HARD_CLAY, 1, 14, "&c&lCancel", null, inv, 16);

        p.openInventory(inv);

    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick2(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Rankup"))
            return;

        final Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case HARD_CLAY:
                if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Confirm")) {
                    if(Tokens.tokens.get(p.getName()) >= rankCost) {

                        String name, uuid;
                        name = p.getName();
                        try {
                            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }

                        String fileName = uuid + ".yml";
                        final File userFile = new File(Bukkit.getServer().getPluginManager().getPlugin("RoodCore").getDataFolder(), fileName);
                        final FileConfiguration user = YamlConfiguration.loadConfiguration(userFile);

                        String curGroup = user.getString("group");
                        int curID = rankID.get(curGroup);
                        String nextGroup = rankNames.get(curID + 1);

                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + nextGroup);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ess rel");

                        int newToken = Tokens.tokens.get(p.getName()) - rankCost;
                        Tokens.tokens.put(p.getName(), newToken);

                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable(){
                            @Override
                            public void run() {
                                for(Player pl : Bukkit.getOnlinePlayers()){
                                    Main.msgPlayer("&9RankUp> " + p.getDisplayName() + " &a&lRANKED UP!", pl);
                                    Main.sendTitle("&9RankUp> " + p.getDisplayName(), " &a&lRANKED UP! &6&lGG!", 20, 100, 20, pl);
                                    pl.playSound(pl.getLocation(), Sound.ENDERDRAGON_DEATH, 1 , 100);
                                }
                            }
                        }, 40);

                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable(){
                            @Override
                            public void run() {
                                String displayName = p.getDisplayName();

                                int i = displayName.indexOf(' ');
                                String groupName = displayName.substring(0, i);

                                groupName = net.md_5.bungee.api.ChatColor.stripColor(groupName.replace('[', ' ').replace(']', ' ').replace("'", " ").replace("+", "Plus").trim());

                                if(!user.contains("group")){
                                    user.set("group", groupName);
                                } else {
                                    String onFile = user.getString("group");
                                    if(!onFile.equals(groupName)){
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
                        }, 60);



                        p.closeInventory();
                        break;
                    } else {
                        p.closeInventory();
                        Main.msgPlayer("&9TokenShop> &aYou do not have enough tokens.", p);
                        break;
                    }
                } else {
                    shopGUI(p);
                    break;
                }
            default:
                break;
        }
    }

    public void openShop(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "&7&oShop".replace('&', '§'));

        Main.createDisplay(Material.TNT, 1, 0, "&a&l/codetim", "&bCost: &9" + codeTimCost + " tokens", inv, 14);

        p.openInventory(inv);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onInventoryClick3(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Shop"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case TNT:
                if(Tokens.tokens.get(p.getName()) >= codeTimCost){
                    if(!p.hasPermission("roodcore.codetim")){
                        int tokens = Tokens.tokens.get(p.getName());
                        Tokens.tokens.put(p.getName(), tokens - codeTimCost);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " roodcore.codetim");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                        Main.msgPlayer("&9TokenShop> &aYou already bought &o/codetim&a!.", p);
                        Main.msgPlayer("&9-" + codeTimCost + " tokens! (Bought /codetim)", p);
                        break;
                    } else {
                        Main.msgPlayer("&9TokenShop> &aYou already bought this!", p);
                        break;
                    }
                } else {
                    p.closeInventory();
                    Main.msgPlayer("&9TokenShop> &aYou do not have enough tokens.", p);
                    break;
                }
            default:
                break;
        }
    }
}
