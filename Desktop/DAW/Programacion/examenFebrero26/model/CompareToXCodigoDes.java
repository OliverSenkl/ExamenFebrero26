package model;

import java.util.Comparator;

public class CompareToXCodigoDes implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return p2.getCodigoProducto().compareToIgnoreCase(p1.getCodigoProducto());
    }
}
