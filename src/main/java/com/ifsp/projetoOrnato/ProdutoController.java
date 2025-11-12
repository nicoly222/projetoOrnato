package com.ifsp.projetoOrnato;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    @GetMapping
    public List<produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<produto> buscarPorId(@PathVariable Long id) {
        Optional<produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo produto
    @PostMapping
    public ResponseEntity<produto> criar(@RequestBody produto produto) {
        produto salvo = produtoRepository.save(produto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<produto> atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Optional<produto> opt = produtoRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produto produto = opt.get();
        produto.setNome(produtoAtualizado.getnome());
        produto.setDescricao(produtoAtualizado.getdescricao());
        produto.setPreco(produtoAtualizado.getpreco());
        produto.setImagem(produtoAtualizado.getImagem());
        

        produto salvo = produtoRepository.save(produto);
        return ResponseEntity.ok(salvo);
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
