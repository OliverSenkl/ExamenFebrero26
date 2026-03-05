package model;

import java.util.Comparator;

public class CompareToXCodigo implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return p1.getCodigoProducto().compareToIgnoreCase(p2.getCodigoProducto());
    }
}