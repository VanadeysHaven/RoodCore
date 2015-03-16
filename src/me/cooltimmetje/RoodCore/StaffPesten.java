package me.cooltimmetje.RoodCore;

import com.darkblade12.particleeffect.ParticleEffect;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * This class has been created on 8-3-2015 at 19:08 by cooltimmetje.
 */
public class StaffPesten implements CommandExecutor {

    static long lastUseTim;
    String lastUserTim = null;
    static int cooldownTim = 150;
    int tokenTim = 2;

    static long lastUseRood;
    static String lastUserRood = null;
    static int cooldownRood = 90;
    static int tokenRood = 2;
    int kickChanceRood = 18;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if (cmd.getLabel().equalsIgnoreCase("codetim")) {
            Player tim = Bukkit.getPlayer("Cooltimmetje");
            if (tim != null) {
                if (allowedTim(p)) {
                    if (p.hasPermission("roodcore.codetim")) {
                        tim.setFlying(false);
                        tim.setVelocity(new Vector(0, 3, 0));
                        ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, tim.getLocation(), 20);
                        Bukkit.getWorld("Survival").playSound(tim.getLocation(), Sound.EXPLODE, 100, 1);
                        Methods.sendTitle("&a&lYou shot " + tim.getDisplayName() + " &a&linto the sky!", "+2 tokens!", 20, 60, 20, p);
                        Methods.sendMain(p.getDisplayName() + "&a&l shot you into the sky!", 20, 60, 20, tim);
                        lastUseTim = System.currentTimeMillis();
                        lastUserTim = p.getName();

                        if (lastUserTim.equalsIgnoreCase("ThoThoKill")) {
                            Methods.sendMain("&b&lPAYBACK BITCH!", 20, 60, 20, p);
                            Bukkit.getWorld("Survival").playSound(p.getLocation(), Sound.EXPLODE, 100, 1);
                            p.setVelocity(new Vector(0, 3, 0));
                            ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, p.getLocation(), 20);
                        }

                        Methods.Broadcast("&9CodeTim> &a" + p.getDisplayName() + " &ashot " + tim.getDisplayName() + " &ainto the sky!");
                        Methods.msgPlayer("&9+" + tokenTim + " tokens! (Shot Cooltimmetje into the sky!)", p);
                        int tokenAmount = TokensGiver.tokensAmount.get(p.getName()) + tokenTim;
                        TokensGiver.tokensAmount.put(p.getName(), tokenAmount);
                    } else {
                        Methods.msgPlayer("&9CodeTim> &aYou need to be &8[&3Tichelaar&8] &aor higher to use this!", p);
                    }
                }
            } else {
                Methods.msgPlayer("&9CodeTim> &aCooltimmetje is currently not online!", p);
            }
        } else if (cmd.getLabel().equalsIgnoreCase("coderood")) {
            final Player rood = Bukkit.getPlayer("ThoThoKill");
            if (rood != null) {
                if (allowedThomas(p)) {
                    rood.setFlying(false);
                    rood.setVelocity(new Vector(0, 3, 0));
                    ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, rood.getLocation(), 20);
                    Bukkit.getWorld("Survival").playSound(rood.getLocation(), Sound.EXPLODE, 100, 1);
                    Methods.sendTitle("&a&lYou shot " + rood.getDisplayName() + " &a&linto the sky!", "+2 tokens!", 20, 60, 20, p);
                    Methods.sendMain(p.getDisplayName() + "&a&l shot you into the sky!", 20, 60, 20, rood);
                    lastUseRood = System.currentTimeMillis();
                    lastUserRood = p.getName();

                    Methods.Broadcast("&9CodeRood> &a" + p.getDisplayName() + " &ashot " + rood.getDisplayName() + " &ainto the sky!");
                    Methods.msgPlayer("&9+" + tokenRood + " tokens! (Shot ThoThoKill into the sky!)", p);
                    int tokenAmount = TokensGiver.tokensAmount.get(p.getName()) + tokenRood;
                    TokensGiver.tokensAmount.put(p.getName(), tokenAmount);

                    int random = Methods.randomInt(1, 100);
                    if (random <= kickChanceRood) {

                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                rood.kickPlayer("rekt!");
                                Methods.Broadcast("&9CodeRood> &a" + p.getDisplayName() + " &agot a critical shot on " + rood.getDisplayName() + "&a!");
                                Methods.msgPlayer("&9+" + tokenRood + " tokens! (Critical shot on ThoThoKill!)", p);
                                int tokenAmount = TokensGiver.tokensAmount.get(p.getName()) + tokenRood;
                                TokensGiver.tokensAmount.put(p.getName(), tokenAmount);
                            }
                        }, 40);
                    }
                }

            }

        }
        return true;
    }

    public boolean allowedTim(Player p){

        int cdmillis = cooldownTim * 1000;
        if(System.currentTimeMillis() - lastUseTim >= cdmillis){
            return true;
        } else {
            int seconds = (int) (cooldownTim - ((System.currentTimeMillis() - lastUseTim) / 1000));
            int minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
            }
            Methods.msgPlayer("&9CodeTim> &aThis command is still on cooldown. &c" + lastUserTim + " &aused it already! You can use it again in: &c" + minutes + "m" + seconds + "s", p);
            return false;
        }

    }
    
    public boolean allowedThomas(Player p){

        int cdmillis = cooldownRood * 1000;
        if(System.currentTimeMillis() - lastUseRood >= cdmillis){
            return true;
        } else {
            int seconds = (int) (cooldownRood - ((System.currentTimeMillis() - lastUseRood) / 1000));
            int minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
            }
            Methods.msgPlayer("&9CodeRood> &aThis command is still on cooldown. &c" + lastUserRood + " &aused it already! You can use it again in: &c" + minutes + "m" + seconds + "s", p);
            return false;
        }

    }

    public static String cooldownCheckListTim(){
        String command = "/codetim";

        int seconds = cooldownTim;
        int minutes = 0;
        while(seconds >= 60){
            minutes = minutes + 1;
            seconds = seconds - 60;
        }
        String totalTime = minutes + "m" + seconds + "s";

        int cdmillis = cooldownTim * 1000;
        String timeLeft = "lol";
        if(System.currentTimeMillis() - lastUseTim >= cdmillis){
            timeLeft = "&2Ready!";

        } else {
            seconds = (int) (cooldownTim - (System.currentTimeMillis() - lastUseTim) / 1000);
            minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
                timeLeft = "&c" + minutes + "m" + seconds + "s";
            }
        }

        return "&9" + command + " &b- &e" + totalTime + " &b- " + timeLeft + " &7&o(global cooldown)";
    }

    public static String cooldownCheckListRood(){
        String command = "/coderood";

        int seconds = cooldownRood;
        int minutes = 0;
        while(seconds >= 60){
            minutes = minutes + 1;
            seconds = seconds - 60;
        }
        String totalTime = minutes + "m" + seconds + "s";

        int cdmillis = cooldownRood * 1000;
        String timeLeft = "lol";
        if(System.currentTimeMillis() - lastUseRood >= cdmillis){
            timeLeft = "&2Ready!";

        } else {
            seconds = (int) (cooldownRood - (System.currentTimeMillis() - lastUseRood) / 1000);
            minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
                timeLeft = "&c" + minutes + "m" + seconds + "s";
            }
        }

        return "&9" + command + " &b- &e" + totalTime + " &b- " + timeLeft + " &7&o(global cooldown)";
    }
}
