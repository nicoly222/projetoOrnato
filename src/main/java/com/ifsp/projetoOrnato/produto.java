package com.ifsp.projetoOrnato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class produto {
@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "nome")
private String nome; 
@Column(name = "descricao")
private String descricao;
@Column(name = "preco")
private double preco;
@Column(name = "imagem")
private String imagem;
private String categoria;


    
}
