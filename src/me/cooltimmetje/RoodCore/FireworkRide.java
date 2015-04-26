package me.cooltimmetje.RoodCore;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class has been created on 25-4-2015 at 20:53 by cooltimmetje.
 */
public class FireworkRide implements CommandExecutor,Listener {

    public static ArrayList<String> fwrNoFall = new ArrayList<String>();

    public static HashMap<String, Long> cooldownFwr = new HashMap<String, Long>();

    public static int cdtime = 15;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getLabel().equalsIgnoreCase("fwr")) {
            if(p.hasPermission("roodcore.fireworkride")){
                if(cooldownCheck(p)){
                    Methods.msgPlayer("&9FireworkRide> &aWheee!", p);
                    shootFirework(p);
                    cooldownFwr.put(p.getName(), System.currentTimeMillis());
                    if (!fwrNoFall.contains(p.getName())) {
                        fwrNoFall.add(p.getName());
                    }
                }
            } else {
                Methods.msgPlayer("&9FireworkRide> &aYou need to be &8[&bMvp&8] &aor higher to use this!", p);
            }
        }
        return true;
    }

    public void shootFirework(Player p){
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
        fw.setPassenger(p);
    }

    private Color getColor(int i) {
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

    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getEntity();
                if(fwrNoFall.contains(p.getName())){
                    event.setCancelled(true);
                    p.setFallDistance(0F);
                    fwrNoFall.remove(p.getName());
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public boolean cooldownCheck(Player p){
        long lastUse = 0;
        if(cooldownFwr.containsKey(p.getName())){
            lastUse = cooldownFwr.get(p.getName());
        }

        int cdmillis = cdtime * 1000;
        if(System.currentTimeMillis() - lastUse >= cdmillis){
            return true;
        } else {
            int seconds = (int) (cdtime - ((System.currentTimeMillis() - lastUse) / 1000));
            int minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
            }
            Methods.msgPlayer("&9FireworkRide> &aThis command is still on cooldown for you. You can use it again in: &c" + minutes + "m" + seconds + "s", p);
            return false;
        }
    }

    public static String cooldownCheckList(Player p){
        String command = "/fwr";

        int seconds = cdtime;
        int minutes = 0;
        while(seconds >= 60){
            minutes = minutes + 1;
            seconds = seconds - 60;
        }

        String totalTime = minutes + "m" + seconds + "s";

        long lastUse = 0;
        if(cooldownFwr.containsKey(p.getName())){
            lastUse = cooldownFwr.get(p.getName());
        }

        int cdmillis = cdtime * 1000;
        String timeLeft = "&2Ready!!";
        if(System.currentTimeMillis() - lastUse >= cdmillis){
            timeLeft = "&2Ready!";
        } else {
            int secondsTime = (int) (cdtime - ((System.currentTimeMillis() - lastUse) / 1000));
            int minutesTime = 0;
            while (seconds >= 60) {
                minutesTime = minutesTime + 1;
                secondsTime = secondsTime - 60;
            }
            timeLeft = "&c" + minutesTime + "m" + secondsTime + "s";
        }

        return "&9" + command + " &b- &e" + totalTime + " &b- " + timeLeft;
    }

}
