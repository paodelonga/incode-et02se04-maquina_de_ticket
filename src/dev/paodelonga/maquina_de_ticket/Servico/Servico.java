package dev.paodelonga.maquina_de_ticket.Servico;

import dev.paodelonga.maquina_de_ticket.Entidade.Ticket;
import dev.paodelonga.maquina_de_ticket.Repositorio.Repositorio;
import dev.paodelonga.maquina_de_ticket.Tipo.EstadoPagamento;
import dev.paodelonga.maquina_de_ticket.Tipo.Identificador;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Servico {
    Repositorio repositorio;

    public Servico() {
        repositorio = new Repositorio((short) 512);
    }

    public Resposta<Boolean, Integer, String, Ticket> emitirTicket() {
        Ticket ticket = new Ticket(new Identificador(UUID.randomUUID()));
        ticket.setDataExpedicao(LocalDateTime.now());
        ticket.setEstadoPagamento(EstadoPagamento.PENDENTE);
        ticket.setValor((float) 25.00);

        return repositorio.addTicket(ticket);
    }

    public Resposta<Boolean, Integer, String, Ticket> obterTicket(String identificador) {
        return repositorio.getTicket(identificador);
    }

    public Resposta<Boolean, Integer, String, Ticket> alterarEstadoPagamento(
        Ticket ticket, EstadoPagamento estadoPagamento
    ) {
        if (ticket.getEstadoPagamento() == estadoPagamento) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                0,
                "semelhante",
                ticket
            );
        } else if (estadoPagamento == EstadoPagamento.PENDENTE) {
            ticket.setEstadoPagamento(estadoPagamento);
            ticket.setDataPagamento(null);
        } else if (estadoPagamento == EstadoPagamento.PAGO) {
            ticket.setEstadoPagamento(estadoPagamento);
            ticket.setDataPagamento(LocalDateTime.now());
        }

        return new Resposta<Boolean, Integer, String, Ticket>(
            true,
            1,
            "alterado",
            ticket
        );
    }

    public Resposta<Boolean, Integer, String, Float> consultarSaldoOperacoes() {
        if (repositorio.getListaTicketExpedido().isEmpty()) {
            return new Resposta<Boolean, Integer, String, Float>(
                false,
                0,
                "lista vazia",
                null
            );
        }

         if (repositorio.getListaTicketExpedido()
            .stream()
            .filter(t -> t.getEstadoPagamento() == EstadoPagamento.PAGO)
            .findAny()
            .isEmpty()
        ) {
            return new Resposta<Boolean, Integer, String, Float>(
                false,
                1,
                "nenhum pago",
                null
           );
        }

        return new Resposta<Boolean, Integer, String, Float>(
            true,
            2,
            "saldo calculado",
            repositorio.getListaTicketExpedido().stream()
                .filter(t -> t.getEstadoPagamento() == EstadoPagamento.PAGO)
                .map(Ticket::getValor)
                .reduce(Float::sum)
                .get()
        );
    }

    public LinkedHashMap<LocalDateTime, String> consultarHistoricoOperacoes() {
        return repositorio.getListaDeOperacoes();
    }

    public void reportarOperacao(String descricao) {
        repositorio.addOperacao(descricao);
    }
}
