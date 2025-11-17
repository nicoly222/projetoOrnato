package com.ifsp.projetoOrnato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CarrinhoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um item pertence a um carrinho
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    // Um item está relacionado a um produto
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto product;

    private Integer quantity;

    public CarrinhoItem() {}

    public CarrinhoItem(Carrinho carrinho, Produto product, Integer quantity) {
        this.carrinho = carrinho;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public Produto getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer q) {
        this.quantity = q;
    }
}

