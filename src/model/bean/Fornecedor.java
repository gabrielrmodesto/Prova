/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author gabrielrmodesto
 */
public class Fornecedor {
    private int codigo;
    private String nome;
    private String situacao;
    private String cidade;

    public Fornecedor() {
    }

    public Fornecedor(int codigo, String nome, String situacao, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.situacao = situacao;
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
}
