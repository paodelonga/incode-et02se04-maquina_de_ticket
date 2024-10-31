package dev.paodelonga.maquina_de_ticket.Tipo;

public enum EstadoPagamento {
    PENDENTE((byte) 0), PAGO((byte) 1);

    EstadoPagamento(Byte estado) {
    }
}
