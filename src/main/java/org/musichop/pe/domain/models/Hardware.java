package org.musichop.pe.domain.models;

public class Hardware extends Articulo{
    private String material;
    public Hardware(String nombreArticulo, String marca, int version, String modelo, String material) {
        super(nombreArticulo, marca, version, modelo);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String showDetails() {
        return "Hardware { " +
                "material= " + material +
                '}' + super.showDetails();
    }
}
