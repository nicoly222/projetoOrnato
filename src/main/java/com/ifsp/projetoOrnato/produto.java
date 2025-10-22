package com.ifsp.projetoOrnato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class produto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "nome")
private String nome; 
@Column(name = "descricao")
private String descricao;
@Column(name = "preco")
private double preco;
@Column(name = "imagem")
private String imagem;
@Column(name = "categoria")
private String categoria;
public produto(){

}
public produto(String nome, String descricao, double preco, String imagem, String categoria){
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.imagem = imagem;
    this.categoria = categoria;
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
public String getimagem(){
    return imagem;
}
public void setimagem(String imagem){
    this.imagem = imagem;
}
public String getcategoria(){
    return categoria;
}
public void setCategoria(String categoria){
    this.categoria = categoria;
}


}


    

