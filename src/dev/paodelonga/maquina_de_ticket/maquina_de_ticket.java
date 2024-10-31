package dev.paodelonga.maquina_de_ticket;

import dev.paodelonga.maquina_de_ticket.Aplicacao.Interface;
import dev.paodelonga.maquina_de_ticket.Servico.Servico;

public class maquina_de_ticket {
    public static void main(String[] args) {
        new Interface(new Servico()).Iniciar();
    }
}
