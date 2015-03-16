package me.cooltimmetje.RoodCore;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.FireworkEffect.Type;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class has been created on 16-3-2015 at 18:00 by cooltimmetje.
 */
public class JukeboxFirework implements Listener {

    public ArrayList<Material> discs = new ArrayList<Material>();

    @EventHandler
    public void onRightClick(final PlayerInteractEvent event){
        final Player p = event.getPlayer();
        if(discs.isEmpty()){
            discs.add(Material.RECORD_3);
            discs.add(Material.RECORD_4);
            discs.add(Material.RECORD_5);
            discs.add(Material.RECORD_6);
            discs.add(Material.RECORD_7);
            discs.add(Material.RECORD_8);
            discs.add(Material.RECORD_9);
            discs.add(Material.RECORD_10);
            discs.add(Material.RECORD_11);
            discs.add(Material.RECORD_12);
            discs.add(Material.GOLD_RECORD);
            discs.add(Material.GREEN_RECORD);
        }
        if(event.getClickedBlock().getType() == Material.JUKEBOX){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(discs.contains(p.getItemInHand().getType())){
                    for(int i=0; i<3; i++){
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                //Spawn the Firework, get the FireworkMeta.
                                Firework fw = (Firework) p.getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.FIREWORK);
                                FireworkMeta fwm = fw.getFireworkMeta();

                                //Our random generator
                                Random r = new Random();

                                //Get the type
                                int rt = r.nextInt(4) + 1;
                                Type type = Type.BALL;
                                if (rt == 1) type = Type.BALL;
                                if (rt == 2) type = Type.BALL_LARGE;
                                if (rt == 3) type = Type.BURST;
                                if (rt == 4) type = Type.CREEPER;
                                if (rt == 5) type = Type.STAR;

                                //Get our random colours
                                int r1i = r.nextInt(17) + 1;
                                int r2i = r.nextInt(17) + 1;
                                Color c1 = getColor(r1i);
                                Color c2 = getColor(r2i);

                                //Create our effect with this
                                FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

                                //Then apply the effect to the meta
                                fwm.addEffect(effect);

                                //Generate some random power and set it
                                int rp = r.nextInt(2) + 1;
                                fwm.setPower(rp);

                                //Then apply this to our rocket
                                fw.setFireworkMeta(fwm);
                            }
                        },10);
                    }
                }
            }



        }
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


}
