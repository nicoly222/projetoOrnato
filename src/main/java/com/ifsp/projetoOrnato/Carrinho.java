 package com.ifsp.projetoOrnato;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um carrinho possui vários itens
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoItem> items = new ArrayList<>();

    public Carrinho() {}

    public Long getId() {
        return id;
    }

    public List<CarrinhoItem> getItems() {
        return items;
    }

    public void addItem(CarrinhoItem item) {
        items.add(item);
    }
}
