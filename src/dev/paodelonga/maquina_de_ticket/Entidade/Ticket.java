package dev.paodelonga.maquina_de_ticket.Entidade;

import dev.paodelonga.maquina_de_ticket.Tipo.EstadoPagamento;
import dev.paodelonga.maquina_de_ticket.Tipo.Identificador;

import java.time.LocalDateTime;

public class Ticket {
    Identificador identificador;
    Float valor;
    EstadoPagamento estadoPagamento;
    LocalDateTime dataExpedicao;
    LocalDateTime dataPagamento;

    public Ticket(Identificador identificador) {
        this.identificador = identificador;
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public Float getValor() {
        return valor;
    }

    public EstadoPagamento getEstadoPagamento() {
        return estadoPagamento;
    }

    public LocalDateTime getDataExpedicao() {
        return dataExpedicao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void setEstadoPagamento(EstadoPagamento estado_pagamento) {
        this.estadoPagamento = estado_pagamento;
    }

    public void setDataExpedicao(LocalDateTime data_expedicao) {
        this.dataExpedicao = data_expedicao;
    }

    public void setDataPagamento(LocalDateTime data_pagamento) {
        this.dataPagamento = data_pagamento;
    }
}
