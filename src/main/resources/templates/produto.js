// produto.js

document.addEventListener("DOMContentLoaded", () => {
  const thumbs = document.querySelectorAll(".thumb");
  const principal = document.querySelector(".principal");
  const tamanhoBtns = document.querySelectorAll(".tamanhos button");
  const cores = document.querySelectorAll(".cores img");

  // 🖼️ troca de imagem principal ao clicar na miniatura
  thumbs.forEach(img => {
    img.addEventListener("click", () => {
      principal.src = img.src;
      thumbs.forEach(t => t.classList.remove("ativo"));
      img.classList.add("ativo");
    });
  });

  // 📏 seleção de tamanho (P, M, G)
  tamanhoBtns.forEach(btn => {
    btn.addEventListener("click", () => {
      tamanhoBtns.forEach(b => b.classList.remove("ativo"));
      btn.classList.add("ativo");
    });
  });

  // 🎨 seleção de cor
  cores.forEach(cor => {
    cor.addEventListener("click", () => {
      cores.forEach(c => c.classList.remove("ativo"));
      cor.classList.add("ativo");
    });
  });

  // 💬 botão de comprar
  const botaoComprar = document.querySelector(".btn-comprar");
  botaoComprar.addEventListener("click", () => {
    alert("Produto adicionado ao carrinho!");
  });
});
