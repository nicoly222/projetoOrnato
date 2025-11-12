// carrinho.js

document.addEventListener("DOMContentLoaded", () => {
  const itens = document.querySelectorAll(".item");
  const subtotalEl = document.querySelector(".linha:nth-child(1) span");
  const totalEl = document.querySelector(".total .preco-final");

  function atualizarTotais() {
    let subtotal = 0;

    itens.forEach(item => {
      const precoText = item.querySelector(".info span").textContent;
      const preco = parseFloat(precoText.replace("R$", "").replace(",", "."));
      const quantidade = parseInt(item.querySelector(".contador span").textContent);
      subtotal += preco * quantidade;
    });

    const desconto = subtotal > 300 ? subtotal * 0.1 : 0;
    const total = subtotal - desconto;

    subtotalEl.textContent = `R$${subtotal.toFixed(2).replace(".", ",")}`;
    totalEl.textContent = `R$${total.toFixed(2).replace(".", ",")}`;
  }

  // 🔢 controles de quantidade
  itens.forEach(item => {
    const btnMais = item.querySelector(".contador button:last-child");
    const btnMenos = item.querySelector(".contador button:first-child");
    const qtdEl = item.querySelector(".contador span");

    btnMais.addEventListener("click", () => {
      let qtd = parseInt(qtdEl.textContent);
      qtdEl.textContent = (qtd + 1).toString().padStart(2, "0");
      atualizarTotais();
    });

    btnMenos.addEventListener("click", () => {
      let qtd = parseInt(qtdEl.textContent);
      if (qtd > 1) {
        qtdEl.textContent = (qtd - 1).toString().padStart(2, "0");
        atualizarTotais();
      }
    });
  });

  // Inicializa os totais ao abrir a página
  atualizarTotais();

  // 🛍️ finalizar compra
  document.querySelector(".finalizar").addEventListener("click", () => {
    alert("Compra finalizada com sucesso! 💎");
  });
});
