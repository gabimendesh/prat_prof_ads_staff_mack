package br.com.escolaoctogono.dto;

public class PresencaInfoDTO {
    private Double presencas;
    private Double faltas;

    public PresencaInfoDTO(Double presencas, Double faltas) {
        this.presencas = presencas;
        this.faltas = faltas;
    }

    public Double getPresencas() {
        return presencas;
    }

    public void setPresencas(Double presencas) {
        this.presencas = presencas;
    }

    public Double getFaltas() {
        return faltas;
    }

    public void setFaltas(Double faltas) {
        this.faltas = faltas;
    }
}

