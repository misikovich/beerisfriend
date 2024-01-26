package org.misikovich.beerisfriend;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class EffectGiveableFood implements EffectGiveable {
    private final NameWrapper displayName;
    private final Material material;

    public EffectGiveableFood(NameWrapper name, Material material) {
        this.displayName = name;
        this.material = material;
    }

    public NameWrapper getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }


    @Override
    public abstract void giveEffect(Player player);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffectGiveableFood that = (EffectGiveableFood) o;
        return Objects.equals(displayName, that.displayName) && material == that.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName);
    }

    @Override
    public String toString() {
        return "EffectGiveableFood{" +
                "displayName='" + displayName + '\'' +
                ", material=" + material +
                '}';
    }
}
