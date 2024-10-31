package dev.paodelonga.maquina_de_ticket.Repositorio;

import dev.paodelonga.maquina_de_ticket.Entidade.Ticket;
import dev.paodelonga.maquina_de_ticket.Servico.Resposta;
import dev.paodelonga.maquina_de_ticket.Tipo.Identificador;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Optional;

public class Repositorio {
    LinkedHashMap<LocalDateTime, String> listaDeOperacoes;
    LinkedList<Ticket> listaTicketEmitidos;
    Short quantidadeTicketDisponivel;
    Short quantidadeMaximaTicket;

    public Repositorio(Short quantidade_maxima_ticket) {
        listaDeOperacoes = new LinkedHashMap<>();
        listaTicketEmitidos = new LinkedList<Ticket>();
        quantidadeMaximaTicket = quantidade_maxima_ticket;
    }

    public LinkedList<Ticket> getListaTicketExpedido() {
        return listaTicketEmitidos;
    }

    public LinkedHashMap<LocalDateTime, String> getListaDeOperacoes() {
        return listaDeOperacoes;
    }

    public Short getQuantidadeTicketDisponivel() {
        quantidadeTicketDisponivel = (short) (quantidadeMaximaTicket - listaTicketEmitidos.size());
        return quantidadeTicketDisponivel;
    }

    public void setListaTicketExpedido(LinkedList<Ticket> lista_ticket_expedido) {
        listaTicketEmitidos = lista_ticket_expedido;
    }

    public void setQuantidadeTicketDisponivel(Short quantidade_ticket_disponivel) {
        quantidadeTicketDisponivel = quantidade_ticket_disponivel;
    }

    public void addOperacao(String descricao) {
        listaDeOperacoes.put(LocalDateTime.now(), descricao);
    }

    public Resposta<Boolean, Integer, String, Ticket> addTicket(Ticket ticket) {
        if (
            listaTicketEmitidos.stream()
                .anyMatch(
                    t -> t.getIdentificador().getIdentificador()
                        .equals(ticket.getIdentificador().getIdentificador())
                )
        ) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                1,
                "ticket existente",
                ticket
            );
        }

        if (listaTicketEmitidos.size() >= quantidadeMaximaTicket) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                0,
                "lista cheia",
                ticket
            );
        } else {
            listaTicketEmitidos.add(ticket);

            return new Resposta<Boolean, Integer, String, Ticket>(
                true,
                2,
                "adicionado",
                ticket
            );
        }
    }

    public Resposta<Boolean, Integer, String, Ticket> removeTicket(Ticket ticket) {
        return removeTicket(ticket.getIdentificador());
    }
    public Resposta<Boolean, Integer, String, Ticket> removeTicket(Identificador identificador) {
        return removeTicket(identificador.getIdentificador());
    }
    public Resposta<Boolean, Integer, String, Ticket> removeTicket(String identificador) {
        if (listaTicketEmitidos.isEmpty()) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                0,
                "lista vazia",
                null
            );
        }

        Optional<Ticket> t_stream = listaTicketEmitidos
            .stream()
            .filter(
                t -> t.getIdentificador().getIdentificador().equals(identificador)
            )
            .findFirst();

        if (t_stream.isPresent()) {
            Ticket ticket = t_stream.get();

            listaTicketEmitidos.remove(ticket);
            return new Resposta<Boolean, Integer, String, Ticket>(
                true,
                2,
                "removido",
                ticket
            );
        } else {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                1,
                "nao encontrado",
                null
            );
        }
    }

    public Resposta<Boolean, Integer, String, Ticket> getTicket(Ticket ticket) {
        return getTicket(ticket.getIdentificador());
    }
    public Resposta<Boolean, Integer, String, Ticket> getTicket(Identificador identificador) {
        return getTicket(identificador.getIdentificador());
    }
    public Resposta<Boolean, Integer, String, Ticket> getTicket(String identificador) {
        if (listaTicketEmitidos.isEmpty()) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                0,
                "lista vazia",
                null
            );
        }

        Optional<Ticket> t_stream = listaTicketEmitidos
            .stream()
            .filter(
                t -> t.getIdentificador().getIdentificador().equals(identificador)
            )
            .findFirst();

        if (t_stream.isPresent()) {
            return new Resposta<Boolean, Integer, String, Ticket>(
                true,
                2,
                "encontrado",
                t_stream.get()
            );
        } else {
            return new Resposta<Boolean, Integer, String, Ticket>(
                false,
                1,
                "nao encontrado",
                null
            );
        }

    }
}
