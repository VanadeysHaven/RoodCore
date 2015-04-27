package me.cooltimmetje.RoodCore.Tokens.TokensShop;

import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Methods;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 12-3-2015 at 16:30 by cooltimmetje.
 */
public class Rankup implements Listener,CommandExecutor {

    public static HashMap<String, Integer> nameID = new HashMap<String, Integer>();
    public static HashMap<Integer, String> idName = new HashMap<Integer, String>();
    public static HashMap<Integer, String> idColor = new HashMap<Integer, String>();
    public static HashMap<Integer, String> spamList = new HashMap<Integer, String>();

    public static int rankCost = 250;

    public static int spamAmount;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("rankup")){
            openRankUp(p);
        }
        return true;
    }

    public static void listRanks() {
        if (nameID.isEmpty()) {
            nameID.put("User", 1);
            nameID.put("UserPlus", 2);
            nameID.put("UserPlusPlus", 3);
            nameID.put("Noob", 4);
            nameID.put("NoobPlus", 5);
            nameID.put("NoobPlusPlus", 6);
            nameID.put("Bruh", 7);
            nameID.put("BruhPlus", 8);
            nameID.put("BruhPlusPlus", 9);
            nameID.put("Miner", 10);
            nameID.put("MinerPlus", 11);
            nameID.put("MinerPlusPlus", 12);
            nameID.put("Mand", 13);
            nameID.put("MandPlus", 14);
            nameID.put("MandPlusPlus", 15);
            nameID.put("Tichelaar", 16);
            nameID.put("TichelaarPlus", 17);
            nameID.put("TichelaarPlusPlus", 18);
            nameID.put("Makkum", 19);
            nameID.put("MakkumPlus", 20);
            nameID.put("MakkumPlusPlus", 21);
            nameID.put("Vip", 22);
            nameID.put("VipPlus", 23);
            nameID.put("VipPlusPlus", 24);
            nameID.put("Mvp", 25);
            nameID.put("MvpPlus", 26);
            nameID.put("MvpPlusPlus", 27);
            nameID.put("Ultra", 28);
            nameID.put("UltraPlus", 29);
            nameID.put("UltraPlusPlus", 30);
            nameID.put("Hero", 31);
            nameID.put("HeroPlus", 32);
            nameID.put("HeroPlusPlus", 33);
            nameID.put("Legend", 34);
            nameID.put("LegendPlus", 35);
            nameID.put("LegendPlusPlus", 36);

        }
        if (idName.isEmpty()) {
            idName.put(1, "User");
            idName.put(2, "UserPlus");
            idName.put(3, "UserPlusPlus");
            idName.put(4, "Noob");
            idName.put(5, "NoobPlus");
            idName.put(6, "NoobPlusPlus");
            idName.put(7, "Bruh");
            idName.put(8, "BruhPlus");
            idName.put(9, "BruhPlusPlus");
            idName.put(10, "Miner");
            idName.put(11, "MinerPlus");
            idName.put(12, "MinerPlusPlus");
            idName.put(13, "Mand");
            idName.put(14, "MandPlus");
            idName.put(15, "MandPlusPlus");
            idName.put(16, "Tichelaar");
            idName.put(17, "TichelaarPlus");
            idName.put(18, "TichelaarPlusPlus");
            idName.put(19, "Makkum");
            idName.put(20, "MakkumPlus");
            idName.put(21, "MakkumPlusPlus");
            idName.put(22, "Vip");
            idName.put(23, "VipPlus");
            idName.put(24, "VipPlusPlus");
            idName.put(25, "Mvp");
            idName.put(26, "MvpPlus");
            idName.put(27, "MvpPlusPlus");
            idName.put(28, "Ultra");
            idName.put(29, "UltraPlus");
            idName.put(30, "UltraPlusPlus");
            idName.put(31, "Hero");
            idName.put(32, "HeroPlus");
            idName.put(33, "HeroPlusPlus");
            idName.put(34, "Legend");
            idName.put(35, "LegendPlus");
            idName.put(36, "LegendPlusPlus");
        }
        if (idColor.isEmpty()) {
            idColor.put(1, "&8[&7User&8]");
            idColor.put(2, "&8[&7User&6+&8]");
            idColor.put(3, "&8[&7User&6+&c+&8]");
            idColor.put(4, "&8[&dNoob&8]");
            idColor.put(5, "&8[&dNoob&6+&8]");
            idColor.put(6, "&8[&dNoob&6+&c+&8]");
            idColor.put(7, "&8[&9Bruh&8]");
            idColor.put(8, "&8[&9Bruh&6+&8]");
            idColor.put(9, "&8[&9Bruh&6+&c+&8]");
            idColor.put(10, "&8[&eMiner&8]");
            idColor.put(11, "&8[&eMiner&6+&8]");
            idColor.put(12, "&8[&eMiner&6+&c+&8]");
            idColor.put(13, "&8[&2Mand&8]");
            idColor.put(14, "&8[&2Mand&6+&8]");
            idColor.put(15, "&8[&2Mand&6+&c+&8]");
            idColor.put(16, "&8[&3Tichelaar&8]");
            idColor.put(17, "&8[&3Tichelaar&6+&8]");
            idColor.put(18, "&8[&3Tichelaar&6+&c+&8]");
            idColor.put(19, "&8[&5Makkum&8]");
            idColor.put(20, "&8[&5Makkum&6+&8]");
            idColor.put(21, "&8[&5Makkum&6+&c+&8]");
            idColor.put(22, "&8[&1Vip&8]");
            idColor.put(23, "&8[&1Vip&6+&8]");
            idColor.put(24, "&8[&1Vip&6+&c+&8]");
            idColor.put(25, "&8[&bMvp&8]");
            idColor.put(26, "&8[&bMvp&6+&8]");
            idColor.put(27, "&8[&bMvp&6+&c+&8]");
            idColor.put(28, "&8[&aUltra&8]");
            idColor.put(29, "&8[&aUltra&6+&8]");
            idColor.put(30, "&8[&aUltra&6+&c+&8]");
            idColor.put(31, "&8[&6Hero&8]");
            idColor.put(32, "&8[&6Hero&6+&8]");
            idColor.put(33, "&8[&6Hero&6+&c+&8]");
            idColor.put(34, "&8[&aLegend&8]");
            idColor.put(35, "&8[&aLegend&6+&8]");
            idColor.put(36, "&8[&aLegend&6+&c+&8]");
        }
        if (spamList.isEmpty()) {
            spamList.put(1, "bg");
            spamList.put(2, "hax");
            spamList.put(3, "n00b");
            spamList.put(4, "je moeder");
            spamList.put(5, "HA! GAAAY!");
            spamList.put(6, "bna");
            spamList.put(7, "bg");
        }
        spamAmount = 7;

    }

    public static void openRankUp(Player p){

        Inventory inv = Bukkit.createInventory(null, 27, "&7&oRankup".replace('&','§'));

        String curGroupName = Methods.getGroup(p).trim();
        String curGroupColor = idColor.get(nameID.get(curGroupName));
        String nextGroupName = idName.get(nameID.get(curGroupName) + 1);
        if(nextGroupName == null){
            Methods.msgPlayer("&9Rankup> &cThere is no next rank available for you!", p);
            p.closeInventory();
            return;
        }
        String nextGroupColor = idColor.get(nameID.get(curGroupName) + 1);

        Methods.createDisplay(Material.NETHER_STAR, 1, 0, curGroupColor.replace('&', '§') + " &b-> " + nextGroupColor.replace('&', '§'),
                "&aIf the ranks are not correct, try again in a minute!", inv, 14);
        Methods.createDisplay(Material.HARD_CLAY, 1, 13, "&2&lCONFIRM &b- &9Cost: " + rankCost, "&aMAKE SURE YOU ARE BUYING THE RIGHT RANK! SEE NETHER STAR! NO REFUNDS!", inv, 12);
        Methods.createDisplay(Material.HARD_CLAY, 1, 14, "&c&lCANCEL", null, inv, 16);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Rankup"))
            return;

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }

        switch (e.getCurrentItem().getType()){
            case HARD_CLAY:
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains("CONFIRM")){
                    if(TokensGiver.tokensAmount.get(p.getName()) >= rankCost){
                        TokensGiver.tokensAmount.put(p.getName(), TokensGiver.tokensAmount.get(p.getName()) - rankCost);

                        String curGroupName = Methods.getGroup(p).trim();
                        String curGroupColor = idColor.get(nameID.get(curGroupName));

                        String nextGroupName = idName.get(nameID.get(curGroupName) + 1);
                        String nextGroupColor = idColor.get(nameID.get(curGroupName) + 1);

                        for(Player pl : Bukkit.getOnlinePlayers()) {
                            Methods.sendTitle("&9Rankup> &c&l" + p.getName() + " &a&lRANKED UP! &6&lGG!", curGroupColor + " &b-> " + nextGroupColor, 20, 100, 20, pl);
                            Methods.msgPlayer("&9RankUp> &c&l" + p.getName() + " &a&lRANKED UP! &6&lGG!", pl);
                            pl.playSound(pl.getLocation(), Sound.ENDERDRAGON_DEATH, 100, 1);
                        }

                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + nextGroupName);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");

                        p.chat("gg");

                        Methods.shootFirework(p);
                        Methods.shootFirework(p);
                        Methods.shootFirework(p);
                        Methods.shootFirework(p);
                        Methods.shootFirework(p);

                        Player tim = Bukkit.getPlayer("Cooltimmetje");
                        if(tim != null){
                            for(int i=0; i<5; i++){
                                int spam = Methods.randomInt(1,spamAmount);
                                tim.chat(spamList.get(spam));
                            }
                        }
                        p.closeInventory();
                        ConfigManager.saveData(p);
                        break;
                    } else {
                        p.closeInventory();
                        int needed = rankCost - TokensGiver.tokensAmount.get(p.getName());
                        Methods.msgPlayer("&9Rankup> &aYou need &9" + needed + " &amore &9tokens &ato buy that!", p);
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
                        break;
                    }
                } else {
                    MainMenu.openMain(p);
                    break;
                }
            default:
                break;
        }
    }

}
