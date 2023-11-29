package org.musichop.pe.domain.models;

public class Software extends Articulo{
    private TipoLicencia tipoLicencia;

    public Software(String nombreArticulo, String marca, int version, String modelo, TipoLicencia tipoLicencia) {
        super(nombreArticulo, marca, version, modelo);
        this.tipoLicencia = tipoLicencia;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    @Override
    public String showDetails() {
        return "Software {" +
                "tipoLicencia= " + tipoLicencia.toString() +
                '}' + super.showDetails();
    }
}
