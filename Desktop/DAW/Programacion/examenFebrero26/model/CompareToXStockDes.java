package model;

import java.util.Comparator;

public class CompareToXStockDes implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return Integer.compare(p2.getStock(),p1.getStock());
    }
}
