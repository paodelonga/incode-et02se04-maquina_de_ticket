package dev.paodelonga.maquina_de_ticket.Aplicacao;

import dev.paodelonga.maquina_de_ticket.Entidade.Ticket;
import dev.paodelonga.maquina_de_ticket.Servico.Resposta;
import dev.paodelonga.maquina_de_ticket.Servico.Servico;
import dev.paodelonga.maquina_de_ticket.Tipo.EstadoPagamento;
import dev.paodelonga.maquina_de_ticket.Utilidade.Leitura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Interface {
    Servico servico;

    public Interface(Servico servico) {
        this.servico = servico;
    }

    private void exibirSeparador() {
        System.out.print("\n");
        for (byte x = 0; x < 60; x++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }

    private void exibirEspacamento() {
        System.out.println("");
    }

    private void exibirOperacoes() {
        HashMap<Integer, String> operacoes = new HashMap<>();

        operacoes.put(1, "Emitir ticket");
        operacoes.put(2, "Alterar estado pagamento");
        operacoes.put(3, "Consultar saldo das operações");
        operacoes.put(4, "Consultar histórico de operações");
        operacoes.put(5, "Finalizar sistema");

        for (Integer key : operacoes.keySet()) {
            System.out.println(
                String.format(
                    "[%s] %s", key, operacoes.get(key)
                )
            );
        }
    }

    private void emitirTicket() {
        Resposta<Boolean, Integer, String, Ticket> respostaEmitirTicket = servico.emitirTicket();

        if (respostaEmitirTicket.getCodigo() == 0) {
            exibirSeparador();
            System.out.println("""
                [!] Não foi possível emitir o ticket
                [!] Quantidade máxima de tickets emitidos.
                """
            );
            servico.reportarOperacao("""
                Falha na tentativa de emissão de ticket
                A quantidade maxima de tickets foi alcançada.
                """
            );
        } else if (respostaEmitirTicket.getCodigo() == 1) {
            emitirTicket();
        } else if (respostaEmitirTicket.getCodigo() == 2) {
            exibirSeparador();
            System.out.println("[>] Ticket emitido com sucesso");
            servico.reportarOperacao(
                String.format("""
                        Sucesso na tentativa de emissão de ticket
                        Um novo ticket de identificador %s foi emitido.
                        """,
                    respostaEmitirTicket.getObjeto().getIdentificador().getIdentificador()
                )
            );
            exibirEspacamento();
            StringBuilder informacao_ticket = new StringBuilder();
            if (respostaEmitirTicket.getObjeto().getIdentificador() != null) {
                informacao_ticket.append("[>] Identificador: ");
                informacao_ticket.append(respostaEmitirTicket.getObjeto().getIdentificador().getIdentificador());
                informacao_ticket.append("\n");
            }
            if (respostaEmitirTicket.getObjeto().getValor() != null) {
                informacao_ticket.append("[>] Valor: R$ ");
                informacao_ticket.append(respostaEmitirTicket.getObjeto().getValor());
                informacao_ticket.append("\n");
            }
            if (respostaEmitirTicket.getObjeto().getEstadoPagamento() != null) {
                informacao_ticket.append("[>] Estado de pagamento: ");
                informacao_ticket.append(respostaEmitirTicket.getObjeto().getEstadoPagamento().toString().toLowerCase());
                informacao_ticket.append("\n");
            }
            if (respostaEmitirTicket.getObjeto().getDataExpedicao() != null) {
                informacao_ticket.append("[>] Data de expedicao: ");
                informacao_ticket.append(respostaEmitirTicket.getObjeto().getDataExpedicao().format(DateTimeFormatter.ofPattern("hh:mm:ss, dd/MM/yyyy")));
                informacao_ticket.append("\n");
            }
            if (respostaEmitirTicket.getObjeto().getDataPagamento() != null) {
                informacao_ticket.append("[>] Data de pagamento: ");
                informacao_ticket.append(respostaEmitirTicket.getObjeto().getDataPagamento().format(DateTimeFormatter.ofPattern("hh:mm:ss, dd/MM/yyyy")));
                informacao_ticket.append("\n");
            }
            System.out.print(informacao_ticket.toString());
        }
    }

    private void alterarEstadoPagamento() {
        Boolean finalizar = false;

        exibirSeparador();
        String identificador = Leitura.lerString(
            "[<] Digite o identificador do ticket: "
        );

        Resposta<Boolean, Integer, String, Ticket> respostaObterTicket;
        Resposta<Boolean, Integer, String, Ticket> respostaAlterarEstadoPagamento;
        respostaObterTicket = servico.obterTicket(identificador);

        if (respostaObterTicket.getCodigo() == 0) {
            exibirEspacamento();
            System.out.println("""
                [!] Não foi possível alterar o estado de pagamento
                [!] Nenhum ticket foi emitido pelo sistema.
                """
            );
            servico.reportarOperacao("""
                Falha na alteração do estado de pagamento
                Nenhum ticket emitido foi pelo sistema.
                """
            );
        } else if (respostaObterTicket.getCodigo() == 1) {
            exibirEspacamento();
            System.out.println("""
                [!] Não foi possível alterar o estado de pagamento
                [!] O ticket não foi encontrado no sistema.
                """
            );
            servico.reportarOperacao(
                String.format("""
                        Falha na alteração do estado de pagamento
                        O ticket %s não foi encontrado no sistema sistema.
                        """,
                    identificador
                )
            );

        } else if (respostaObterTicket.getCodigo() == 2) {
            exibirEspacamento();

            StringBuilder informacao_ticket = new StringBuilder();
            if (respostaObterTicket.getObjeto().getIdentificador() != null) {
                informacao_ticket.append("[>] Identificador: ");
                informacao_ticket.append(respostaObterTicket.getObjeto().getIdentificador().getIdentificador());
                informacao_ticket.append("\n");
            }
            if (respostaObterTicket.getObjeto().getValor() != null) {
                informacao_ticket.append("[>] Valor: R$ ");
                informacao_ticket.append(respostaObterTicket.getObjeto().getValor());
                informacao_ticket.append("\n");
            }
            if (respostaObterTicket.getObjeto().getEstadoPagamento() != null) {
                informacao_ticket.append("[>] Estado de pagamento: ");
                informacao_ticket.append(respostaObterTicket.getObjeto().getEstadoPagamento().toString().toLowerCase());
                informacao_ticket.append("\n");
            }
            if (respostaObterTicket.getObjeto().getDataExpedicao() != null) {
                informacao_ticket.append("[>] Data de expedicao: ");
                informacao_ticket.append(respostaObterTicket.getObjeto().getDataExpedicao().format(DateTimeFormatter.ofPattern("hh:mm:ss, dd/MM/yyyy")));
                informacao_ticket.append("\n");
            }
            if (respostaObterTicket.getObjeto().getDataPagamento() != null) {
                informacao_ticket.append("[>] Data de pagamento: ");
                informacao_ticket.append(respostaObterTicket.getObjeto().getDataPagamento().format(DateTimeFormatter.ofPattern("hh:mm:ss, dd/MM/yyyy")));
                informacao_ticket.append("\n");
            }
            System.out.print(informacao_ticket.toString());

            exibirEspacamento();
            while (!finalizar) {
                System.out.println("""
                    [1] Definir como pendente
                    [2] Definir como pago
                    [3] Retroceder"""
                );

                exibirEspacamento();
                switch (
                    Leitura.lerInteiro("[<] Escolha uma operação: ")
                ) {
                    case 1:
                        if (servico.alterarEstadoPagamento(
                            respostaObterTicket.getObjeto(),
                            EstadoPagamento.PENDENTE
                        ).getCodigo() == 1) {
                            exibirSeparador();
                            System.out.println("[>] Estado de pagamento alterado com sucesso");
                            System.out.println("[>] Novo estado definido como: pendente");
                            servico.reportarOperacao(
                                String.format("""
                                        Sucesso na alteração do estado de pagamento
                                        Novo estado do ticket %s definido como pendente.
                                        """,
                                    identificador

                                )
                            );
                            finalizar = true;
                        } else {
                            exibirSeparador();
                            System.out.println("[!] Estado de pagamento não alterado");
                            System.out.println("[!] O estado requisitado é o mesmo definido");
                            servico.reportarOperacao(
                                String.format("""
                                        Falha na alteração do estado de pagamento
                                        O estado atual do ticket %s é o mesmo requisitado.
                                        """,
                                    identificador
                                )
                            );
                            finalizar = true;
                        }
                        break;
                    case 2:
                        if (servico.alterarEstadoPagamento(
                            respostaObterTicket.getObjeto(),
                            EstadoPagamento.PAGO
                        ).getCodigo() == 1) {
                            exibirSeparador();
                            System.out.println("[>] Estado de pagamento alterado com sucesso");
                            System.out.println("[>] Novo estado definido como: pago");
                            servico.reportarOperacao(
                                String.format("""
                                        Sucesso na alteração do estado de pagamento
                                        Novo estado do ticket %s definido como pago.
                                        """,
                                    identificador
                                )
                            );
                            finalizar = true;
                        } else {
                            exibirSeparador();
                            System.out.println("[!] Estado de pagamento não alterado");
                            System.out.println("[!] O estado requisitado é o mesmo definido");
                            servico.reportarOperacao(
                                String.format("""
                                        Falha na alteração do estado de pagamento
                                        O estado atual do ticket %s é o mesmo requisitado.
                                        """,
                                    identificador
                                )
                            );
                            finalizar = true;
                        }
                        break;
                    case 3:
                        finalizar = true;
                        break;
                }
            }
        }
    }

    private void consultarSaldoOperacoes() {
        exibirSeparador();
        Resposta<Boolean, Integer, String, Float> respostaConsultarSaldoOperacoes;
        respostaConsultarSaldoOperacoes = servico.consultarSaldoOperacoes();

        if (respostaConsultarSaldoOperacoes.getCodigo() == 0) {
            System.out.println("""
                [!] Não foi possível consultar o saldo das operações
                [!] Nenhum ticket foi emitido pelo sistema
                """
            );
            servico.reportarOperacao("""
                Falha ao consultar o saldo das operações
                Nenhum ticket foi emitido pelo sistema
                """
            );
        } else if (respostaConsultarSaldoOperacoes.getCodigo() == 1) {
            System.out.println("""
                [!] Não foi possível consultar o saldo das operações
                [!] Não existem tickets pagos no sistema
                """
            );
            servico.reportarOperacao("""
                Falha ao consultar o saldo das operações
                Não existem tickets pagos no sistema
                """
            );
        } else if (respostaConsultarSaldoOperacoes.getCodigo() == 2) {
            System.out.println(
                String.format(
                    "[>] Ao todo foram arrecadados um total de R$ %.2f",
                    respostaConsultarSaldoOperacoes.getObjeto()
                )
            );
            servico.reportarOperacao("Sucesso ao consultar o saldo das operações.");
        }
    }

    private void consultarHistoricoOperacoes() {
        exibirSeparador();

        for (LocalDateTime operacao : servico.consultarHistoricoOperacoes().keySet()) {
            System.out.println(
                String.format("""
                        [>] (%s)
                        %s""",
                    operacao.format(DateTimeFormatter.ofPattern("hh:mm:ss, dd/MM/yyyy")),
                    servico.consultarHistoricoOperacoes().get(operacao)
                )
            );
        }
    }

    public void Iniciar() {
        boolean finalizar_sistema = false;

        while (!finalizar_sistema) {
            exibirSeparador();
            exibirOperacoes();
            exibirEspacamento();

            switch (
                Leitura.lerInteiro("[<] Escolha uma operação: ")
            ) {
                case 1:
                    emitirTicket();
                    break;
                case 2:
                    alterarEstadoPagamento();
                    break;
                case 3:
                    consultarSaldoOperacoes();
                    break;
                case 4:
                    consultarHistoricoOperacoes();
                    break;
                case 5:
                    finalizar_sistema = true;
                    break;
                default:
                    exibirSeparador();
                    System.out.println("[!] Uma operação inexistente foi selecionada");
                    break;
            }
        }
    }
}
