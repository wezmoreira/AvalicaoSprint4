package br.com.compass.avaliacao.enums;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return this.sexo;
    }
}
