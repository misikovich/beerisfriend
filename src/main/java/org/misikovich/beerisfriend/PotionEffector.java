package org.misikovich.beerisfriend;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PotionEffector {

//    public EffectBuilder(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
//        super(type, duration, amplifier, ambient, particles, icon);
//    }
    private boolean ambient;
    private boolean particles;
    private boolean icon;
    private List<PotionEffect> potionEffects;

    public PotionEffector(boolean ambient, boolean particles, boolean icon) {
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
        potionEffects = new ArrayList<>();
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

    public PotionEffector newEffect(@NotNull PotionEffectType type, int durationS, int ampl) {
        if (ifContainsType(type))
        int durationTick = durationS * 20;
        new PotionEffect(type, durationTick, ampl, ambient, particles, icon);
        return this;
    }

    private boolean ifContainsType(PotionEffectType type) {
        for (PotionEffect potionEffect : potionEffects) {
            if (potionEffect.getType().equals(type)) return true;
        }
        return false;
    }

    public List<PotionEffect> getPotionEffects() {
        return potionEffects;
    }
}
