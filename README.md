# Challenge GlobalLogic

## Descripción
El siguiente proyecto fue realizado en lenguaje java a partir del uso de Spring boot framework.
Se utilizó base de datos en memoria H2 junto con Jpa para el manejo de la capa de datos.
La capa de seguridad es manejada con spring security haciendo uso de JWT para verificación de autenticación.
Se realizó el testing con Junit y Mockito.


### Tecnologia empleada
El siguiente proyecto fue realizado con:

    - Java 8
    - Spring Boot Framework
    - Junit
    - Sping security y JWT
    - Base de datos H2

Tambien resaltar el uso de las siguientes dependencias:

    - Modelmapper
    - Encriptador AES 256
    - Lombok


### *Aclaración:
En el siguiente proyecto se hace uso del puerto 8080 por defecto. Asegurarse de que el mismo no este siendo usado por otra aplicación.


## Desarrollo
El proyecto cuenta con los siguientes endpoints:



### Metodo de verbo POST

#### Crear Usuario:

Este método permite crear un nuevo usuario.

```
http://localhost:8080/api/v1/usuario/sign-up 
```

En este caso es necesario mandar por payload el contenido del nuevo producto.

Ejemplo:
```
{
    "name":"Test Prueba",
    "email":"test@testing.com",
    "password":"LoginTest123",
    "role": "ADMIN",
    "phones": [
        {
            "number": 123,
            "citycode":54,
            "contrycode":"351"
        }
    ]
}
```

### Métodos de verbo GET

#### Login:

Este método permite acceder a los datos de un usuario ya creado.
Está protegido por la capa de seguridad por lo cual es necesario pasar un token válido por la cabecera.
El token se genera al crear el "usuario" y se actualiza al consultar el método "Login".

```
    http://localhost:8080/api/v1/registro/login
```

En este caso es necesario mandar por payload el contenido del nuevo producto.

Ejemplo:
```
{
    "user":"test@testing.com",
    "password": "LoginTest123",
    "role": "ADMIN"
}
```


#### GetToken:

Permite obtener el token para un usuario específico (necesario para el metodo login).
Este método solo se crea para facilitar la tarea de prueba.

```
    http://localhost:8080/api/v1/registro/getToken/test@testing.com
```


### URI de los métodos creados

```
Crear Usuario:
------------- 

curl --location --request POST 'http://localhost:8080/api/v1/usuario/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Test Prueba",
    "email":"test@testing.com",
    "password":"LoginTest123",
    "role": "ADMIN",
    "phones": [
        {
            "number": 123,
            "citycode":54,
            "contrycode":"351"
        }
    ]
}'


Login:
-----

curl --location --request GET 'http://localhost:8080/api/v1/registro/login' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3RpbmcuY29tIiwiZXhwIjoxNjg0Mjc5MjE0LCJpYXQiOjE2ODQyNTA0MTQsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.r1OJPL2_d11ZBDyTfL7RXvu7pZq-LP2wCUyY4hPkiqg' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user":"test@testing.com",
    "password": "LoginTest123",
    "role": "ADMIN"
}'

GetToken:
--------

curl --location --request GET 'http://localhost:8080/api/v1/registro/getToken/test@testing.com'
```

* Aclaracion: El token incorporado en la cabecera del metodo "Login" debe ser actualizado cada vez que se realice la consulta.
  Un token nuevo se generará al ejecutar "Login", pero puede consultarse el mismo haciendo uso del metodo "GetToken" a partir del "Username".


