<script>
  // ================================================
  // 1. PEGAR O ID DO PRODUTO NA URL
  // Exemplo: produto.html?id=1
  // ================================================
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get("id") || 1; // Se não tiver ?id, usa 1


  // ================================================
  // 2. BUSCAR PRODUTO NO BACKEND
  // ================================================
  async function carregarProduto() {
    try {
      const response = await fetch(`http://localhost:8080/api/produtos/${productId}`);

      if (!response.ok) {
        throw new Error("Produto não encontrado no backend");
      }

      const produto = await response.json();

      // Preencher informações
      document.querySelector(".produto-info h1").textContent = produto.name;
      document.querySelector(".preco").textContent =
        "R$ " + Number(produto.price).toFixed(2).replace(".", ",");

      // Atualizar imagem, caso venha do banco
      if (produto.imageUrl !== null && produto.imageUrl !== "") {
        document.querySelector(".principal").src = produto.imageUrl;
      }

      // Remover seleção de cor/tamanho (já que são fixos)
      document.querySelector(".opcoes").style.display = "none";

    } catch (error) {
      console.error("Erro ao carregar o produto:", error);
    }
  }

  carregarProduto();


  // ================================================
  // 3. CRIAR CARRINHO SE NÃO EXISTIR
  // ================================================
      async function obterOuCriarCarrinho() {
    let cartId = localStorage.getItem("cartId");
    const usuarioId = localStorage.getItem("usuarioId");

    if (!usuarioId) {
        alert("Você precisa estar logado para comprar.");
        window.location.href = "login.html";
        return;
    }

    if (!cartId) {
        const response = await fetch("http://localhost:8080/api/carrinho", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ usuarioId })
        });

        const novoCarrinho = await response.json();
        cartId = novoCarrinho.id;
        localStorage.setItem("cartId", cartId);
    }

    return cartId;
}



  // ================================================
  // 4. ADICIONAR PRODUTO AO CARRINHO
  // ================================================
  async function adicionarAoCarrinho() {
    const cartId = await obterOuCriarCarrinho();

    await fetch(`http://localhost:8080/api/carrinho/${cartId}/items`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        productId: Number(productId),
        quantity: 1
      })
    });

    mostrarMensagem("Produto adicionado ao carrinho! 🛍️");
  }


  // ================================================
  // 5. MENSAGEM DE CONFIRMAÇÃO BONITA
  // ================================================
  function mostrarMensagem(texto) {
    const msg = document.createElement("div");
    msg.textConte
