package model;

import java.util.Comparator;

public class CompareToXPrecioDes implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return Double.compare(p2.getPrecio(),p1.getPrecio());
    }
}
