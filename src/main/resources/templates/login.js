document.querySelector('form').addEventListener('submit', async function(e) {
    e.preventDefault();
    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    const response = await fetch('http://localhost:8080/usuarios/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email, senha }),
    });

    if (response.ok) {
        
        window.location.href = '/pagina-desejada.html';
    } else {
        alert('Usuário ou senha inválidos');
    }
});