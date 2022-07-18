package br.com.compass.avaliacao.enums;

public enum Cargos {

    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private final String cargos;

    Cargos(String valor) {
        this.cargos = valor;
    }

    public String getCargos() {
        return this.cargos;
    }
}
