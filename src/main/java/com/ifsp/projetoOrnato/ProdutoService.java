package com.ifsp.projetoOrnato;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto novosDados) {
        return produtoRepository.findById(id).map(produto -> {

            produto.setNome(novosDados.getNome());
            produto.setDescricao(novosDados.getDescricao());
            produto.setPreco(novosDados.getPreco());
            produto.setImagem(novosDados.getImagem());

            return produtoRepository.save(produto);

        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
