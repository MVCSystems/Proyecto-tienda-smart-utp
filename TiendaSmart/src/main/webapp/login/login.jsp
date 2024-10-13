<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            margin-top: 100px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .login-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-primary {
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="login-container">
                    <h2 class="text-center login-title">Login</h2>
                    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                        <div class="form-group mb-3">
                            <label for="email">Email:</label>
                            <input type="text" id="email" name="email" class="form-control" required autocomplete="email">
                        </div>
                        <div class="form-group mb-3">
                            <label for="contrasenha">Password:</label>
                            <input type="password" id="contrasenha" name="contrasenha" class="form-control" required autocomplete="current-password">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block w-100">Login</button>
                    </form>
                    <%
                        String message = (String) request.getAttribute("message");
                        if (message != null) {
                            out.println("<div class='alert alert-danger mt-3'>" + message + "</div>");
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>