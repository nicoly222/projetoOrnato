package com.ifsp.projetoOrnato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar produtos
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }    

    //Mostrar produto por ID
    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);

    }
     
    // Cadastrar novo produto
    public Produto insertNew(Produto produto){
        return produtoRepository.save(produto);
    }

    //ALterar prduto
    public Produto update(Long id, Produto produtoAlterado){
        Produto produtoExistente = produtoRepository.findById(id).orElse(produtoAlterado);
        produtoExistente.setNome(produtoAlterado.getnome());
        produtoExistente.setDescricao(produtoAlterado.getdescricao());
        produtoExistente.setPreco(produtoAlterado.getpreco());
        produtoExistente.setImagem(produtoAlterado.getImagem());
        return produtoRepository.save(produtoExistente);
    }

    //Deletar produto
    public void delete (Long id){
        produtoRepository.deleteById(id);
    }
    
}
