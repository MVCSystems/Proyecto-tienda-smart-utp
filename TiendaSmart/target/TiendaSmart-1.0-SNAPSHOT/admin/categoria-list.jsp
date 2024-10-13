<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Tienda</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#" id="loadCategorias">
                            <i class="fas fa-cogs"></i>
                            Categorías
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="mt-5">
            <h1>Lista de Categorías</h1>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                    </tr>
                </thead>
                <tbody id="categoriasTableBody">
                    <!-- Aquí se insertarán las categorías mediante AJAX -->
                </tbody>
            </table>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("loadCategorias").addEventListener("click", function(event) {
                event.preventDefault();
                fetch('${pageContext.request.contextPath}/CategoriaServlet?action=listAjax')
                    .then(response => response.json())
                    .then(data => {
                        let tableBody = document.getElementById("categoriasTableBody");
                        tableBody.innerHTML = "";
                        data.forEach(categorias => {
                            let row = `<tr>
                                <td>${categorias[0]}</td>
                                <td>${categorias[1]}</td>
                            </tr>`;
                            tableBody.innerHTML += row;
                        });
                    });
            });
        });
    </script>
</body>
</html>