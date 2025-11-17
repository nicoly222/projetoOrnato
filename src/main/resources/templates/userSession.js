
// ===========================
// EXIBIR NOME DO USUÁRIO
// ===========================
function carregarUsuario() {
    const nome = localStorage.getItem("usuarioNome");

    const loginLink = document.querySelector(".header-icons a:last-child");

    if (nome) {
        loginLink.innerHTML = `<i class="fas fa-user-circle"></i> Olá, ${nome}`;
        loginLink.href = "#";
    }
}

carregarUsuario();


// ===========================
// LOGOUT (caso queira no futuro)
// ===========================
function logout() {
    localStorage.removeItem("usuarioId");
    localStorage.removeItem("usuarioNome");
    localStorage.removeItem("cartId");

    window.location.href = "login.html";
}
