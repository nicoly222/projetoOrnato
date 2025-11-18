// ================================================
// 1. PEGAR O ID DO PRODUTO NA URL
// Exemplo: produto.html?id=1
// ================================================
const urlParams = new URLSearchParams(window.location.search);
const productId = parseInt(urlParams.get("id")) || 1; // Se não tiver ?id, usa 1

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
    try {
      const response = await fetch("http://localhost:8080/api/carrinho", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ usuarioId })
      });

      if (!response.ok) {
        throw new Error("Falha ao criar o carrinho.");
      }

      const novoCarrinho = await response.json();
      cartId = novoCarrinho.id;
      localStorage.setItem("cartId", cartId);
    } catch (error) {
      console.error("Erro ao criar o carrinho:", error);
      alert("Erro ao criar o carrinho. Tente novamente mais tarde.");
      return;
    }
  }

  return cartId;
}

// ================================================
// 4. ADICIONAR PRODUTO AO CARRINHO
// ================================================
async function adicionarAoCarrinho() {
  const cartId = await obterOuCriarCarrinho();

  if (!cartId) {
    return; // Não pode adicionar ao carrinho sem um cartId válido
  }

  try {
    const response = await fetch(`http://localhost:8080/api/carrinho/${cartId}/items`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        productId: productId,
        quantity: 1
      })
    });

    if (!response.ok) {
      throw new Error("Erro ao adicionar produto ao carrinho.");
    }

    mostrarMensagem("Produto adicionado ao carrinho! 🛍️");
  } catch (error) {
    console.error("Erro ao adicionar ao carrinho:", error);
    alert("Houve um erro ao adicionar o produto ao carrinho.");
  }
}

// ================================================
// 5. MENSAGEM DE CONFIRMAÇÃO BONITA
// ================================================
function mostrarMensagem(texto) {
  const msg = document.createElement("div");
  msg.textContent = texto;
  msg.style.position = "fixed";
  msg.style.top = "10px";
  msg.style.right = "10px";
  msg.style.backgroundColor = "#4CAF50";
  msg.style.color = "white";
  msg.style.padding = "10px";
  msg.style.borderRadius = "5px";
  msg.style.boxShadow = "0 2px 5px rgba(0,0,0,0.2)";
  msg.style.fontSize = "16px";
  msg.style.zIndex = "1000";

  document.body.appendChild(msg);

  setTimeout(() => {
    msg.remove();
  }, 3000);
}
