package me.cooltimmetje.RoodCore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SwaggerGear implements CommandExecutor {

    public static ItemStack swagSword(){
        ItemStack swagSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swagSwordMeta = swagSword.getItemMeta();
        swagSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        swagSwordMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
        swagSwordMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagSwordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 5, true);
        swagSwordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, true);
        swagSwordMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        swagSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        swagSwordMeta.setDisplayName("§a§lSwagger Sword");
        swagSword.setItemMeta(swagSwordMeta);
        return swagSword;
    }

    public static ItemStack swagPickNormal(){
        ItemStack swagPickNormal = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta swagPickNormalMeta = swagPickNormal.getItemMeta();
        swagPickNormalMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagPickNormalMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        swagPickNormalMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
        swagPickNormalMeta.setDisplayName("§a§lSwagger Pickaxe §8» §bNormal Speed §9- §bFortune");
        swagPickNormal.setItemMeta(swagPickNormalMeta);
        return swagPickNormal;
    }

    public static ItemStack swagPickNormalSilk(){
        ItemStack swagPickNormalSilk = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta swagPickNormalSilkMeta = swagPickNormalSilk.getItemMeta();
        swagPickNormalSilkMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagPickNormalSilkMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        swagPickNormalSilkMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        swagPickNormalSilkMeta.setDisplayName("§a§lSwagger Pickaxe §8» §bNormal Speed §9- §bSilk Touch");
        swagPickNormalSilk.setItemMeta(swagPickNormalSilkMeta);
        return swagPickNormalSilk;
    }

    public static ItemStack swagPickFast(){
        ItemStack swagPickFast = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta swagPickFastMeta = swagPickFast.getItemMeta();
        swagPickFastMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagPickFastMeta.addEnchant(Enchantment.DIG_SPEED, 100, true);
        swagPickFastMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
        swagPickFastMeta.setDisplayName("§a§lSwagger Pickaxe §8» §bFast Speed §9- §bFortune");
        swagPickFast.setItemMeta(swagPickFastMeta);
        return swagPickFast;
    }

    public static ItemStack swagPickFastSilk(){
        ItemStack swagPickFastSilk = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta swagPickFastSilkMeta = swagPickFastSilk.getItemMeta();
        swagPickFastSilkMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagPickFastSilkMeta.addEnchant(Enchantment.DIG_SPEED, 100, true);
        swagPickFastSilkMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        swagPickFastSilkMeta.setDisplayName("§a§lSwagger Pickaxe §8» §bFast Speed §9- §bSilk Touch");
        swagPickFastSilk.setItemMeta(swagPickFastSilkMeta);
        return swagPickFastSilk;
    }

    public static ItemStack swagAxe(){
        ItemStack swagAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta swagAxeMeta = swagAxe.getItemMeta();
        swagAxeMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagAxeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        swagAxeMeta.setDisplayName("§a§lSwagger Axe");
        swagAxe.setItemMeta(swagAxeMeta);
        return swagAxe;
    }

    public static ItemStack swagSpade(){
        ItemStack swagSpade = new ItemStack(Material.DIAMOND_SPADE);
        ItemMeta swagSpadeMeta = swagSpade.getItemMeta();
        swagSpadeMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagSpadeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        swagSpadeMeta.setDisplayName("§a§lSwagger Shovel");
        swagSpade.setItemMeta(swagSpadeMeta);
        return swagSpade;
    }

    public static ItemStack swagHelmet() {
        ItemStack swagHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta swagHelmetMeta = swagHelmet.getItemMeta();
        swagHelmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        swagHelmetMeta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        swagHelmetMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        swagHelmetMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        swagHelmetMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagHelmetMeta.addEnchant(Enchantment.OXYGEN, 3, true);
        swagHelmetMeta.addEnchant(Enchantment.WATER_WORKER, 1, true);
        swagHelmetMeta.setDisplayName("§a§lSwagger Helmet");
        swagHelmet.setItemMeta(swagHelmetMeta);
        return swagHelmet;
    }

    public static ItemStack swagChest() {
        ItemStack swagChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta swagChestMeta = swagChest.getItemMeta();
        swagChestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        swagChestMeta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        swagChestMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        swagChestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        swagChestMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagChestMeta.addEnchant(Enchantment.THORNS, 3, true);
        swagChestMeta.setDisplayName("§a§lSwagger Chestplate");
        swagChest.setItemMeta(swagChestMeta);
        return swagChest;
    }

    public static ItemStack swagLegs() {
        ItemStack swagLegs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta swagLegsMeta = swagLegs.getItemMeta();
        swagLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        swagLegsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        swagLegsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        swagLegsMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        swagLegsMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagLegsMeta.setDisplayName("§a§lSwagger Leggings");
        swagLegs.setItemMeta(swagLegsMeta);
        return swagLegs;
    }

    public static ItemStack swagBoots() {
        ItemStack swagBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta swagBootsMeta = swagBoots.getItemMeta();
        swagBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        swagBootsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        swagBootsMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        swagBootsMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        swagBootsMeta.addEnchant(Enchantment.DURABILITY, 2000, true);
        swagBootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
        swagBootsMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        swagBootsMeta.setDisplayName("§a§lSwagger Boots");
        swagBoots.setItemMeta(swagBootsMeta);
        return swagBoots;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("swaggergear")) {
            if (p.getName().equalsIgnoreCase("Cooltimmetje")){
                p.sendMessage(Main.arrowTag + "p.setSwagLevel(§lover 9000§a);");
                p.getInventory().clear();
                p.getInventory().setItem(0, swagSword());
                p.getInventory().setItem(1, swagPickNormal());
                p.getInventory().setItem(2, swagPickNormalSilk());
                p.getInventory().setItem(3, swagPickFast());
                p.getInventory().setItem(4, swagPickFastSilk());
                p.getInventory().setItem(5, swagAxe());
                p.getInventory().setItem(6, swagSpade());
                p.getInventory().setItem(7, (new ItemStack (Material.COOKED_BEEF, 64)));
                p.getInventory().setItem(8, (new ItemStack (Material.TORCH, 64)));
                p.getInventory().setHelmet(swagHelmet());
                p.getInventory().setChestplate(swagChest());
                p.getInventory().setLeggings(swagLegs());
                p.getInventory().setBoots(swagBoots());
                return true;
            } else {
                p.sendMessage(Main.arrowTag + "hoper...");
                return false;
            }

        }
        return false;
    }

}