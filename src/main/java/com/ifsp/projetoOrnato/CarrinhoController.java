package com.ifsp.projetoOrnato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    // Criar novo carrinho
    @PostMapping
    public Carrinho criarCarrinho() {
        return carrinhoService.criarCarrinho();
    }

    // Buscar carrinho por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarCarrinho(@PathVariable Long id) {
        return carrinhoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Adicionar item ao carrinho
    @PostMapping("/{id}/items")
    public Carrinho adicionarItem(
            @PathVariable Long id,
            @RequestBody CarrinhoItemRequest req
    ) {
        return carrinhoService.adicionarItem(id, req.getProductId(), req.getQuantity());
    }

    // Atualizar item
    @PutMapping("/{id}/items/{itemId}")
    public CarrinhoItem atualizarItem(
            @PathVariable Long itemId,
            @RequestBody CarrinhoItemRequest req
    ) {
        return carrinhoService.atualizarQuantidade(itemId, req.getQuantity());
    }

    // Remover item
    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        carrinhoService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }
}

// Classe auxiliar para receber JSON
class CarrinhoItemRequest {
    private Long productId;
    private Integer quantity;

    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }

    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
