const cartId = localStorage.getItem("cartId");

// ===============================
// CARREGAR ITENS DO CARRINHO
// ===============================
async function carregarCarrinho() {
    if (!cartId) {
        document.querySelector(".itens").innerHTML = "<p>Seu carrinho está vazio.</p>";
        return;
    }

    const response = await fetch(`http://localhost:8080/api/carrinho/${cartId}`);
    const carrinho = await response.json();

    const itensDiv = document.querySelector(".itens");
    itensDiv.innerHTML = ""; // limpar

    carrinho.items.forEach(item => {
        itensDiv.innerHTML += `
            <div class="item">
                <img src="${item.product.imageUrl}" alt="${item.product.nome}">
                <div class="info">
                  <h3>${item.product.nome}</h3>
                  <p>Ornato Jóias</p>
                  <span>R$ ${Number(item.product.preco).toFixed(2).replace('.', ',')}</span>
                </div>
                <div class="quantidade">
                    <p>Quantidade</p>
                    <div class="contador">
                        <button class="menos" data-id="${item.id}">-</button>
                        <span>${item.quantity}</span>
                        <button class="mais" data-id="${item.id}">+</button>
                    </div>
                </div>
            </div>
        `;
    });

    calcularResumo(carrinho);
}

carregarCarrinho();


// ===============================
// ALTERAR QUANTIDADES
// ===============================
document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("mais")) {
        const itemId = e.target.dataset.id;

        await fetch(`http://localhost:8080/api/carrinho/items/${itemId}/add`, {
            method: "PUT"
        });

        carregarCarrinho();
    }

    if (e.target.classList.contains("menos")) {
        const itemId = e.target.dataset.id;

        await fetch(`http://localhost:8080/api/carrinho/items/${itemId}/remove`, {
            method: "PUT"
        });

        carregarCarrinho();
    }
});


// ===============================
// CALCULAR RESUMO DO CARRINHO
// ===============================
function calcularResumo(carrinho) {

    let subtotal = carrinho.items.reduce((acc, item) =>
        acc + item.product.preco * item.quantity, 0);

    const entrega = 0;
    const desconto = subtotal >= 300 ? subtotal * 0.10 : 0;
    const total = subtotal - desconto;

    document.querySelector(".resumo").innerHTML = `
        <h2>Resumo</h2>
        <div class="box">
            <div class="linha">
                <p>Subtotal</p>
                <span>R$ ${subtotal.toFixed(2).replace('.', ',')}</span>
            </div>
            <div class="linha">
                <p>Entrega</p>
                <span>R$ 00,00</span>
            </div>
            <div class="linha desconto">
                <p>Desconto</p>
                <span>${desconto > 0 ? "-10%" : "0%"}</span>
            </div>
            <div class="linha total">
                <p>Total</p>
                <span class="preco-final">R$ ${total.toFixed(2).replace('.', ',')}</span>
            </div>
        </div>
        <button class="finalizar">FINALIZAR COMPRA</button>
    `;
}
