package dev.paodelonga.maquina_de_ticket.Servico;

public class Resposta<Boolean, Integer, String, T> {
    private Boolean estado;
    private Integer codigo;
    private String descricao;
    private T objeto;
    
    public Resposta(Boolean estado, Integer codigo, String descricao, T objeto) {
        this.estado = estado;
        this.codigo = codigo;
        this.descricao = descricao;
        this.objeto = objeto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public T getObjeto() {
        return objeto;
    }
}
