package com.example.patiobalmax.model;

public class ArchivoRegistro {
    private String patio;
    private String puesto;
    private String detalleLugar1;
    private String patenteLugar1;
    private String detalleLugar2;
    private String patenteLugar2;
    private String nombreArrendatario;

    public ArchivoRegistro(String patio, String puesto, String detalleLugar1, String patenteLugar1,
                           String detalleLugar2, String patenteLugar2, String nombreArrendatario) {
        this.patio = patio;
        this.puesto = puesto;
        this.detalleLugar1 = detalleLugar1;
        this.patenteLugar1 = patenteLugar1;
        this.detalleLugar2 = detalleLugar2;
        this.patenteLugar2 = patenteLugar2;
        this.nombreArrendatario = nombreArrendatario;
    }

    public String getPatio() {
        return patio;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getDetalleLugar1() {
        return detalleLugar1;
    }

    public String getPatenteLugar1() {
        return patenteLugar1;
    }

    public String getDetalleLugar2() {
        return detalleLugar2;
    }

    public String getPatenteLugar2() {
        return patenteLugar2;
    }

    public String getNombreArrendatario() {
        return nombreArrendatario;
    }
}
