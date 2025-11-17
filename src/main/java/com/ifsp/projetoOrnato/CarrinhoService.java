package com.ifsp.projetoOrnato;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(
            CarrinhoRepository carrinhoRepository,
            CarrinhoItemRepository itemRepository,
            ProdutoRepository produtoRepository
    ) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
    }

    // Criar carrinho vazio
    public Carrinho criarCarrinho() {
        Carrinho carrinho = new Carrinho();
        return carrinhoRepository.save(carrinho);
    }

    // Buscar carrinho por ID
    public Optional<Carrinho> buscarPorId(Long id) {
        return carrinhoRepository.findById(id);
    }

    // Adicionar um item ao carrinho
    public Carrinho adicionarItem(Long carrinhoId, Long produtoId, Integer quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CarrinhoItem item = new CarrinhoItem(carrinho, produto, quantidade);
        carrinho.getItems().add(item);

        carrinhoRepository.save(carrinho);
        return carrinho;
    }

    // Atualizar quantidade do item
    public CarrinhoItem atualizarQuantidade(Long itemId, Integer novaQtd) {
        CarrinhoItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setQuantity(novaQtd);
        return itemRepository.save(item);
    }

    // Remover item
    public void removerItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

