package de.baeckerit.jdk.util;

import java.util.ArrayList;
import java.util.Collection;

public abstract class UtilsCollection {

    public static <S extends T, T> ArrayList<S> filter(Collection<S> items, IObjectFilter<T> filter) {
        ArrayList<S> filteredItems = new ArrayList<>();
        for (S s : items) {
            if (filter.select(s)) {
                filteredItems.add(s);
            }
        }
        return filteredItems;
    }
}
