package org.misikovich.beerisfriend.Foods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.misikovich.beerisfriend.EffectGiveableFood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Alcohol extends EffectGiveableFood {
    private final List<String> phrases;
    private final PotionEffect[] potionEffects;
    private final Random random;

    public Alcohol(String name, Material material, PotionEffect... potionEffects) {
        super(name, material);

        this.potionEffects = potionEffects;
        random = new Random();
        phrases = new ArrayList<>();
        phrases.add("бля шо тут налито яща зблюю");
        phrases.add("ебать меня вставило");
        phrases.add("бля мажет шо піздец");
        phrases.add("да ну нахуооой бляяяяя");
        phrases.add("піздец мне болшье не налівай");
        phrases.add("хорошо пошла");
    }

    @Override
    public void giveEffect(Player player) {
        for (PotionEffect potionEffect : potionEffects) {
            if (potionEffect == null) continue;
            player.addPotionEffect(potionEffect);
        }

        player.sendMessage(phrases.get(random.nextInt(phrases.size() - 1)));
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "potionEffects=" + Arrays.toString(potionEffects) +
                "} " + super.toString();
    }
}
