package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Pair<T, U> {
    public final T first;
    public final U second;
    List<Pair> pairs = new ArrayList<>();

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
        pairs.add(this);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    public void remove() {
        Iterator<Pair> iter = pairs.iterator();
        while (iter.hasNext()) {
            Pair pair = iter.next();
            if (pair.equals(this)) {
                iter.remove();
            }
        }
    }
}