<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Lista de Productos</h1>
        <a href="${pageContext.request.contextPath}/ProductoServlet?action=new" class="btn btn-primary mb-3">Agregar Producto</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${listProductos}">
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.descripcion}</td>
                        <td>${producto.precio}</td>
                        <td>${producto.categoria.nombre}</td> <!-- Asumiendo que `categoria` es un objeto asociado -->
                        <td>
                            <a href="${pageContext.request.contextPath}/ProductoServlet?action=edit&id=${producto.id}" class="btn btn-warning">Editar</a>
                            <a href="${pageContext.request.contextPath}/ProductoServlet?action=delete&id=${producto.id}" class="btn btn-danger">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
