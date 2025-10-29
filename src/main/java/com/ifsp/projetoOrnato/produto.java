package com.ifsp.projetoOrnato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "nome")
private String nome; 
@Column(name = "descricao")
private String descricao;
@Column(name = "preco")
private double preco;
@Column(name = "material")
private String material;
public Produto(){

}
public Produto(String nome, String descricao, double preco, String material){
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.material = material;
}

public String getnome(){
    return nome;
}
public void setNome(String nome){
    this.nome = nome;
}
public String getdescricao(){
    return descricao;
}
public void setDescricao(String descricao){
    this.descricao = descricao;
}
public double getpreco(){
    return preco;
}
public void setPreco(double preco){
    this.preco = preco;
}

public String getcategoria(){
    return material;
}
public void setCategoria(String material){
    this.material = material;
}


}


    
