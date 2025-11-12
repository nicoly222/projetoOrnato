// Exemplo de listagem de produtos
fetch("http://localhost:8080/api/produtos")
  .then(res => res.json())
  .then(data => {
    data.forEach(produto => {
      console.log(produto.nome);
    });
  });
