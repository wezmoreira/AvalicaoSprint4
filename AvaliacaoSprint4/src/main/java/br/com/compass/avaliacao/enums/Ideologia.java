package br.com.compass.avaliacao.enums;

public enum Ideologia {
    DIREITA("Direita"),
    ESQUERDA("Esquerda"),
    CENTRO("Centro");

    private final String ideologia;

    Ideologia(String valor) {
        this.ideologia = valor;
    }

    public String getIdeologia() {
        return this.ideologia;
    }
}
