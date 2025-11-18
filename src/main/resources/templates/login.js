document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    // Coleta os dados do formulário
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;

    // Validação simples: Verificar se os campos estão preenchidos
    if (!email || !senha) {
        alert('Preencha todos os campos!');
        return;
    }

    try {
        // Envia os dados para o backend
        const response = await fetch('http://localhost:8080/usuarios/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, senha }),
        });

        // Verifica a resposta do servidor
        if (response.ok) {
            const data = await response.json(); // O servidor provavelmente retorna o token ou dados do usuário
            const token = data.token; // Supondo que o token seja retornado sob a chave "token"
            
            // Armazena o token no localStorage (ou sessionStorage) para persistir a sessão
            localStorage.setItem('authToken', token);

            // Redireciona para a página principal (ou qualquer outra página protegida)
            window.location.href = '/pagina-desejada.html';
        } else {
            alert('Usuário ou senha inválidos');
        }
    } catch (err) {
        // Em caso de erro de rede ou outro tipo de falha
        alert('Erro ao tentar logar. Tente novamente.');
        console.error(err);
    }
});
