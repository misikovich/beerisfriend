package org.misikovich.beerisfriend;

import io.papermc.paper.util.Tick;
import jdk.vm.ci.hotspot.JFR;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.misikovich.beerisfriend.Foods.Alcohol;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public final class BeerIsFriend extends JavaPlugin implements Listener {
    List<EffectGiveableFood> giveableFoodSet;
    static boolean DEBUG = true;
    @Override
    public void onEnable() {
        // Plugin startup logic
        giveableFoodSet = new ArrayList<>();
        Alcohol beer = new Alcohol(
                "beer",
                Material.POTION,
                new PotionEffect(PotionEffectType.CONFUSION, sToTick(50), 3),
                new PotionEffect(PotionEffectType.SLOW, sToTick(50), 3),
                new PotionEffect(PotionEffectType.SLOW_DIGGING, sToTick(50), 10),
                new PotionEffect(PotionEffectType.UNLUCK, sToTick(50), 10),
                new PotionEffect(PotionEffectType.ABSORPTION, sToTick(30), 2),
                new PotionEffect(PotionEffectType.WEAKNESS, sToTick(), 3)
                );
        new PotionEffector(false, false, false)
                .newEffect(PotionEffectType.CONFUSION, 50, 3)
                .newEffect(PotionEffectType.SLOW, 50, 3)
                .newEffect(PotionEffectType.SLOW_DIGGING, 50, 10)
                .newEffect(PotionEffectType.SLOW_DIGGING, 50, 10)
        giveableFoodSet.add(beer);
        getServer().getPluginManager().registerEvents(this, this);
        Log("success", "Loaded");
    }

    @EventHandler
    public void onPlayerDrink(PlayerItemConsumeEvent event) {
        Log("opd start", "New event!");
        Material eMaterial = event.getItem().getType();
        Log("opd", "Got material, ID: " + eMaterial);
        if (!event.getItem().hasItemMeta()) return;
        Log("opd", "Item has Meta");
        if (!event.getItem().getItemMeta().hasDisplayName()) return;
        Log("opd", "Item has DisplayName");
        String eDisplayName = event.getItem().getItemMeta().getDisplayName();
        Log("opd", "DisplayName: " + eDisplayName);
        Player player = event.getPlayer();
        Log("opd", "Player of event: " + player.displayName().examinableName());

        for (EffectGiveableFood effectGiveableFood : giveableFoodSet) {
            Log("opd validate", "Home DisplayName: " +effectGiveableFood.getDisplayName()+ " == Event DisplayName: " + eDisplayName);
            if (!effectGiveableFood.getDisplayName().equalsIgnoreCase(eDisplayName)) continue;
            Log("opd good", "DisplayName match!");
            Log("opd validate", "Home material: " +effectGiveableFood.getMaterial()+ " == Event material: " + eMaterial);
            Log("opd validate", String.valueOf(effectGiveableFood.getMaterial().equals(eMaterial)));
            if (!effectGiveableFood.getMaterial().equals(eMaterial)) continue;
            Log("opd good", "Material match!");
            effectGiveableFood.giveEffect(player);
            Log("opd success", "Effect had given to the player!");
            Log("opd success", "Effect: " + effectGiveableFood);
        }
        Log("opd end", "Item got no matches");
    }

    public void Log(String prefix, String message) {
        if (!DEBUG) return;
        String s = "[" + prefix + "]: " + message;
        getLogger().info(s);
    }
    private int sToTick(int s) {
        return s * 20;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
