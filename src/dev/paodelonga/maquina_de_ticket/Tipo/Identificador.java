package dev.paodelonga.maquina_de_ticket.Tipo;

import java.util.UUID;

public class Identificador {
    private String identificador;

    public Identificador(UUID uuid) {
        this.identificador = uuid.toString().split("-")[1];
    }

    public String getIdentificador() {
        return identificador;
    }
}
