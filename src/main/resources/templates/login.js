document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    const nome = document.getElementById('nome').value.trim();
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;

    if (!nome || !email || !senha) {
        alert('Preencha todos os campos!');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/usuarios/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, email, senha }),
        });

        if (response.ok) {
            // Redireciona se login OK
            window.location.href = '/pagina-desejada.html';
        } else {
            alert('Usuário ou senha inválidos');
        }
    } catch (err) {
        alert('Erro ao tentar logar. Tente novamente.');
        console.error(err);
    }
});