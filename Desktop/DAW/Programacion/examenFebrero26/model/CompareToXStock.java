package model;

import java.util.Comparator;

public class CompareToXStock implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return Integer.compare(p1.getStock(),p2.getStock());
    }
}
