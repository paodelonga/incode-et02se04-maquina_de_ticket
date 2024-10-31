**Desafio Estacionamento:** Esta máquina para estacionamento deve ser capaz de:

- Emitir o ticket com um determinado valor fixo {ticket deve possuir um número e valor}
- Controlar se o ticket está pago/ou não
- Receber o pagamento do ticket
- Permitir que se possa consultar o saldo das operações a qualquer momento

---

## Refzona da massa

### Ticket

Que que ele tem? Ele tem:

- Identificador (String/Texto/UUID/Referencia)
  - UUID (time_mid)
- Valor (Float/Numerico)
- Estado de pagamento (String/Referencia)
  - Pago
  - Pendente
- Data e hora de emissao
- Data e hora de expedicao

---

```
==================================================

[1] Emitir ticket
[2] Alterar estado pagamento
[3] Consultar saldo das operações
[4] Consultar histórico de operações

[<] Escolha uma operação: 

==================================================

[1.0]
[>] Ticket emitido com sucesso

[>] Identificador: as2d
[>] Valor: R$ 20.00
[>] Estado de pagamento: PENDENTE
[>] Data de emissão: 00:00:00, 01/01/1970
[>] Data de expedição: 00:00:00, 01/01/1970

--------------------------------------------------

[1.0]
[!] Não foi possível emitir o ticket
[!] Quantidade máxima de tickets emitidos

==================================================

[2.0]
[<] Digite o identificador do ticket: ad2d

[>] Identificador: as2d
[>] Valor: R$ 20.00
[>] Estado de pagamento: PENDENTE
[>] Data de emissão: 00:00:00, 01/01/1970
[>] Data de expedição: 00:00:00, 01/01/1970

[1] Definir como pendente
[2] Definir como pago
[3] Retroceder

--------------------------------------------------

[2.0]
[<] Digite o identificador do ticket: ad2d

[!] Não foi possível alterar o estado de pagamento
[!] O ticket ad2d não foi encontrado no sistema

==================================================

[4.0]
[>] (00:00:00, 01/01/1970)
  Emissão de ticket realizada com sucesso
[>] (00:00:00, 01/01/1970)
  Tentativa de emissão de ticket, falha
  devido a quantidade máxima de tickets emitidos
[>] (00:00:00, 01/01/1970)
  Alteração no estado de pagamento do ticket as2d
  para PENDENTE  
[>] (00:00:00, 01/01/1970)
  Falha na alteração do estado de pagamento do
  ticket as2d para PENDENTE, o ticket não foi
  encontrado no sistema  
[>] (00:00:00, 01/01/1970)
  Consulta ao histórico de operações
```