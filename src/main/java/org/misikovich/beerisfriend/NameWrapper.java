package org.misikovich.beerisfriend;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;

public class NameWrapper {
    private final HashSet<String> names = new HashSet<>();

    public NameWrapper(@NotNull String... possibleNames) {
        Collections.addAll(names, possibleNames);
    }

    public boolean equals(String s) {
        return names.contains(s.toLowerCase());
    }

    public void addName(String name) {
        names.add(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "NameWrapper{" +
                "names=" + names +
                '}';
    }
}
