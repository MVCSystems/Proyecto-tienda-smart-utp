<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">${producto == null ? 'Agregar Producto' : 'Editar Producto'}</h1>
        <form action="ProductoServlet?action=${productos == null ? 'insert' : 'update'}" method="post">
            <input type="hidden" name="id" id="id" value="${productos != null ? productos.id : ''}">
            
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${productos != null ? productos.nombre : ''}" required>
            </div>
            
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" required>${productos != null ? productos.descripcion : ''}</textarea>
            </div>
            
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="${productos != null ? productos.precio : ''}" required>
            </div>
            
            <div class="mb-3">
                <label for="categoria_id" class="form-label">Categoría</label>
                <select class="form-control" id="categoria_id" name="categoria_id" required>
                    <c:forEach var="categoria" items="${listCategorias}">
                        <option value="${categoria[0]}" ${producto != null && producto[4] == categoria[0] ? 'selected' : ''}>${categoria[1]}</option>
                    </c:forEach>
                </select>
            </div>
            
            <button type="submit" class="btn btn-primary">${producto == null ? 'Agregar' : 'Actualizar'}</button>
            <a href="${pageContext.request.contextPath}/ProductoServlet" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
