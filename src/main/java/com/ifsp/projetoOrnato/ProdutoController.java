package com.ifsp.projetoOrnato;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        final produto produto = produtoRepository.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        return "produto";
    }
}