package org.misikovich.beerisfriend;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.misikovich.beerisfriend.Foods.Alcohol;

import java.util.ArrayList;
import java.util.List;

public final class BeerIsFriend extends JavaPlugin implements Listener {
    List<EffectGiveableFood> giveableFoodSet = new ArrayList<>();
    static boolean DEBUG = false;
    @Override
    public void onEnable() {
        // Plugin startup logic
        initVariables();
        // BEER LOADED
        getServer().getPluginManager().registerEvents(this, this);
        Log("пиво", "Наливай!", true);
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
            if (!effectGiveableFood.getDisplayName().equals(eDisplayName)) continue;
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
        Log(prefix, message, false);
    }
    public void Log(String prefix, String message, boolean force) {
        if (!DEBUG || !force) return;
        String s = "[" + prefix + "]: " + message;
        getLogger().info(s);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Log("пиво", "Закругляемся!", true);
    }

    public void initVariables() {
        NameWrapper beerNames = new NameWrapper("beer", "pivo", "пиво", "пивко", "піво", "пиво", "півко", "кружка піва", "пузирь піва");
        Material beerMaterial = Material.POTION;
        PotionEffector beerEffector = new PotionEffector(false, false, false)
                .setEffect(PotionEffectType.CONFUSION,
                        50, 3)
                .setEffect(PotionEffectType.SLOW,
                        50, 3)
                .setEffect(PotionEffectType.SLOW_DIGGING,
                        50, 10)
                .setEffect(PotionEffectType.UNLUCK,
                        50, 10)
                .setEffect(PotionEffectType.ABSORPTION,
                        30, 5)
                .setEffect(PotionEffectType.WEAKNESS,
                        50, 10);

        Alcohol beer = new Alcohol(beerNames, beerMaterial, beerEffector.getPotionEffects());
        giveableFoodSet.add(beer);
    }
}
