package org.misikovich.beerisfriend;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PotionEffector {
    private boolean ambient;
    private boolean particles;
    private boolean icon;
    private final HashMap<PotionEffectType, PotionEffect> potionEffects;

    public PotionEffector(boolean ambient, boolean particles, boolean icon) {
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
        potionEffects = new HashMap<>();
    }

    public PotionEffector setAmbient(boolean ambient) {
        this.ambient = ambient;
        return this;
    }

    public PotionEffector setParticles(boolean particles) {
        this.particles = particles;
        return this;
    }

    public PotionEffector setIcon(boolean icon) {
        this.icon = icon;
        return this;
    }

    public PotionEffector setEffect(@NotNull PotionEffectType type, int durationS, int ampl) {
        PotionEffect potionEffect = new PotionEffect(
                type,
                sToTick(durationS),
                ampl,
                ambient,
                particles,
                icon
        );
        potionEffects.put(type, potionEffect);
        return this;
    }

    private int sToTick(int s) {
        return s * 20;
    }

    public List<PotionEffect> getPotionEffects() {
        return new ArrayList<>(potionEffects.values());
    }
}
