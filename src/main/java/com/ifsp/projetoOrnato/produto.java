package com.ifsp.projetoOrnato; // Recomenda-se usar um pacote 'model' para entidades

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal; // Recomendado para valores monetários

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nomes de coluna explícitos não são estritamente necessários se forem iguais
    // aos nomes dos atributos, mas mantive por segurança.
    @Column(name = "nome")
    private String nome; 
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "preco")
    // Mudei para BigDecimal, que é o tipo padrão e recomendado para dinheiro
    private BigDecimal preco; 
    
    @Column(name = "imagem")
    private String imagem;

    // Construtor padrão (obrigatório pelo JPA)
    public Produto() {

    }
    
    // Construtor com argumentos
    public Produto(String nome, String descricao, BigDecimal preco, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
    }

    // --- Getters e Setters Corretos (Padrão JavaBeans) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Corrigido: getNome()
    public String getNome() {
        return nome;
    }
    
    // Corrigido: setNome()
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Corrigido: getDescricao()
    public String getDescricao() {
        return descricao;
    }
    
    // Corrigido: setDescricao()
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Corrigido: getPreco()
    public BigDecimal getPreco() {
        return preco;
    }
    
    // Corrigido: setPreco()
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}