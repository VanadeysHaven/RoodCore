package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TabTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import me.cooltimmetje.RoodCore.Managers.ResourcePackManager;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import me.cooltimmetje.RoodCore.Tokens.TokensShop.Rankup;
import mkremins.fanciful.FancyMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class has been created on 10-3-2015 at 17:10 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class Methods {

    public static ScoreboardManager sbManager = Bukkit.getScoreboardManager();

    public static float getPercentage(int n, int total) {
        float proportion = ((float) n) / ((float) total);
        return proportion * 100;
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

    public static ItemStack createItem(Material m, int amount, int data, String name, String lore){
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
        return item;
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String getGroup(Player p){
        String displayName = p.getDisplayName();

        int i = displayName.indexOf(' ');
        String groupName = displayName.substring(0, i);

        groupName = ChatColor.stripColor(groupName.replace('[', ' ').replace(']', ' ').replace("'", " ").replace("+", "Plus").trim());

        return groupName;
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
        Bukkit.broadcastMessage(msg.replace('&', '§'));
    }

    public static void msgPlayer(String msg, Player p){
        p.sendMessage(msg.replace('&', '§'));
    }

    public static String getUUID(Player p){
        String name, uuid = null;
        name = p.getName();
        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public static void updateScoreboard(Player p){
        if(!ScoreboardToggle.myRoodOff.contains(p.getName())) {
            Scoreboard board = sbManager.getNewScoreboard();

            Objective obj = board.registerNewObjective("myrood", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("&c&lMy&4&lR00D".replace('&', '§'));

            try {
                Score name = obj.getScore(p.getDisplayName());
                name.setScore(11);
            } catch (IllegalArgumentException e){
                Score name = obj.getScore("&e".replace('&', '§') + p.getName());
                name.setScore(11);
            }

            Score space1 = obj.getScore(" ");
            space1.setScore(10);

            Score tokensTitle = obj.getScore("&9&lTokens: &9&o".replace('&', '§'));
            tokensTitle.setScore(9);

            String curGroupName = Methods.getGroup(p).trim();
            String curGroupColor = null;
            String nextGroupColor = null;
            try {
                curGroupColor = Rankup.idColor.get(Rankup.nameID.get(curGroupName));
                nextGroupColor = Rankup.idColor.get(Rankup.nameID.get(curGroupName) + 1);
            } catch (NullPointerException e){
                curGroupColor = null;
                nextGroupColor = null;
            }

            if(TokensGiver.tokensAmount.get(p.getName()) <= Rankup.rankCost || curGroupColor == null || nextGroupColor == null) {
                    Score tokensAmount = obj.getScore("&9".replace('&', '§') + TokensGiver.tokensAmount.get(p.getName()) + "&b - &9&o(more in: ".replace('&', '§')
                            + TokensGiver.tokensTime.get(p.getName()) + "m)");
                    tokensAmount.setScore(8);
            } else {
                Score tokensAmount = obj.getScore("&9".replace('&', '§') + TokensGiver.tokensAmount.get(p.getName()) + "&b - &2&lRANKUP READY!".replace('&', '§'));
                tokensAmount.setScore(8);
            }

            Score space2 = obj.getScore("  ");
            space2.setScore(7);

            Score killedMobsTitle = obj.getScore("&3&lKilled mobs: &3&o(".replace('&', '§') + TokensGiver.nightTokens + " tokens)");
            killedMobsTitle.setScore(6);

            if (!TokensGiver.killedNight.containsKey(p.getName())) {
                TokensGiver.killedNight.put(p.getName(), 0);
            }
            Score killedMobs = obj.getScore("&3".replace('&', '§') + TokensGiver.killedNight.get(p.getName()) + "/" + TokensGiver.nightKills);
            killedMobs.setScore(5);

            Score space3 = obj.getScore("   ");
            space3.setScore(4);

            Score resourceTitle = obj.getScore("&d&lSelected RP:".replace('&', '§') + " &d".replace('&', '§') + ResourcePackManager.playerPack.get(p.getName()));
            resourceTitle.setScore(3);

            Score onlinePlayersTitle = obj.getScore("&a&lOnline players:".replace('&', '§') + " &a".replace('&', '§') + Bukkit.getOnlinePlayers().size());
            onlinePlayersTitle.setScore(2);

            String nextRank;
            if(nextGroupColor == null){
                nextRank = "&7&onone".replace('&', '§');
            } else {
                nextRank = nextGroupColor;
            }
            Score nextRankTitle = obj.getScore("&2&lNext Rank: ".replace('&', '§') + nextRank.replace('&', '§'));
            nextRankTitle.setScore(1);

            p.setScoreboard(board);
        }
    }

    public static void loadScoreboard(Player p){
        if(!ScoreboardToggle.myRoodOff.contains(p.getName())) {
            Scoreboard board = sbManager.getNewScoreboard();

            Objective obj = board.registerNewObjective("myrood", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("&c&lMy&4&lR00D &7&o(loading...)".replace('&', '§'));

            Score loading = obj.getScore("&7&oLoading scoreboard...".replace('&', '§'));
            loading.setScore(2);

            Score please = obj.getScore("&7&oPlease wait...".replace('&', '§'));
            please.setScore(1);

            p.setScoreboard(board);
        }
    }

    public static void reloadScoreboard(Player p){
        Scoreboard board = sbManager.getNewScoreboard();

        Objective obj = board.registerNewObjective("myrood", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("&c&lMy&4&lR00D &7&o(loading...)".replace('&', '§'));

        Score loading = obj.getScore("&7&oReloading server...".replace('&', '§'));
        loading.setScore(2);

        Score please = obj.getScore("&7&oPlease wait...".replace('&', '§'));
        please.setScore(1);

        p.setScoreboard(board);
    }

    public static void removeScoreboard(Player p){
        Scoreboard board = sbManager.getNewScoreboard();
        p.setScoreboard(board);
    }

    public static void updateTab(Player p, int tokens){
        new TabTitleObject("&aWelcome to &cThe &4#TeamR00D &cNetwork!".replace('&', '§'), p.getDisplayName() + " &b- &9".replace('&', '§') + tokens + " tokens").send(p);
        p.setPlayerListName(p.getDisplayName());
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String buildArg(String[] args, int start){
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < args.length; i++){
            if(i != start){
                sb.append(" ");
            } else {
                sb.append("&r");
            }
            sb.append(args[i]);
        }
        return sb.toString().trim();
    }

    public static void test(){

    }

    public static void shootFirework(Player p){
        //Spawn the Firework, get the FireworkMeta.
        final Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        //Our random generator
        Random r = new Random();

        //Get the type
        int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BURST;
        if (rt == 3) type = FireworkEffect.Type.STAR;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.BALL_LARGE;

        //Get our random colours
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);

        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(c1).withFade(c2).with(type).trail(true).build();

        //Then apply the effect to the meta
        fwm.addEffect(effect);

        //Generate some random power and set it
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);

        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);
    }

    private static Color getColor(int i) {
        Color c = null;
        if(i==1){
            c=Color.AQUA;
        }
        if(i==2){
            c=Color.BLACK;
        }
        if(i==3){
            c=Color.BLUE;
        }
        if(i==4){
            c=Color.FUCHSIA;
        }
        if(i==5){
            c=Color.GRAY;
        }
        if(i==6){
            c=Color.GREEN;
        }
        if(i==7){
            c=Color.LIME;
        }
        if(i==8){
            c=Color.MAROON;
        }
        if(i==9){
            c=Color.NAVY;
        }
        if(i==10){
            c=Color.OLIVE;
        }
        if(i==11){
            c=Color.ORANGE;
        }
        if(i==12){
            c=Color.PURPLE;
        }
        if(i==13){
            c=Color.RED;
        }
        if(i==14){
            c=Color.SILVER;
        }
        if(i==15){
            c=Color.TEAL;
        }
        if(i==16){
            c=Color.WHITE;
        }
        if(i==17){
            c=Color.YELLOW;
        }

        return c;
    }

}
