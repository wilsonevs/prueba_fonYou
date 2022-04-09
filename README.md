
# PRUEBA TÉCNICA


El presente documento describe una necesidad de software que debe ser implementada con el
objetivo de validar los conocimientos del aspirante al cargo de JUNIOR SOFTWARE ENGINEER.
Se solicita la implementación de un Servicio Web (REST o SOAP) el cual permita gestionar un
examen de selección múltiple con las siguientes funcionalidades:

### Necesidades
El componente creado debe permitir por medio de un API (REST o SOAP sobre HTTP):

Crear un examen. Este se compone de un conjunto de preguntas con 4 opciones, 1 opción
correcta y un puntaje por cada pregunta que en total deben sumar 100 puntos (la nota
total del examen).
• Crear un estudiante. Este se compone de la siguiente información: nombre, edad, ciudad
y zona horaria de la ciudad.
• Definir fecha del examen y asignar examen a estudiantes en su zona horaria. Dada una
fecha de presentación del examen en zona horaria de Bogotá se debe recibir la fecha de
presentación para cada estudiante en su zona horaria correspondiente.
• Recopilar las respuestas de un estudiante. Se debe poder recopilar todas las respuestas
de un estudiante en un examen que se le ha asignado.
• Calificar los exámenes. Una vez recibida las respuestas de un estudiante se deberá
entregar la calificación de este.

### Restricciones
• Todos los métodos que se creen en el API deberán consumir JSON y producir JSON.
• La aplicación deberá ser implementada en lenguaje Java, por medio del Framework Spring.
• Los datos deberán quedar persistidos en una base de datos que usted escoja (ejemplo:
MySQL o PostgreSQL).
• Las dependencias deben ser configuradas en Maven.
• Se debe implementar el código aplicando las buenas prácticas que considere.
### Entregables
• Código fuente de la aplicación (preferiblemente en un repositorio como GitHub).
• Script para la creación de la BD (en caso de que se necesite).
• Colección de las CURL para el consumo de los métodos que se definan en el API (se puede
utilizar Postman).


## 🚀 Acerca de mí...
• Soy una persona autodidacta, ágil, comunicativo, responsable, dedicado y proactivo, 
amante de la tecnología e innovador. siempre dispuesto a afrontar nuevos retos que 
me hagan crecer tanto personal como profesionalmente, Cuento con más de 1 año de 
experiencia como developer java.

• Actualmente e tenido la gran oportunidad de participar en más
de 3 proyectos importantes dentro de la organización junto con un gran
equipo de desarrolladores, donde me desempeño como desarrollador
Backend manejando lenguaje como Java, Spring Boot, Thymeleaf,
Microservicios, Postman, AWS, Bitbucket, Maven, Kafka, Mqtt, Base de datos
Sql, NoSql y despliegue en tomcat.


## Autor

- [@Github/wilsonevs](https://github.com/wilsonevs)
- [@Linkedin/wilsonevs](https://www.linkedin.com/in/wilsonvalencs/)
- [@Torre/wilsonevs](https://torre.co/wilson_evs)


## CRUD
CRUD hace referencia a un acrónimo en el que se reúnen las primeras letras de las cuatro operaciones fundamentales de aplicaciones persistentes en sistemas de bases de datos:

* Create (Crear registros)
* Read (Leer registros)
* Update (Actualizar registros)
* Delete (Borrar registros)
|CRUD-Operación | SQL    | RESTful      |
| ------------- | -------| ------------ |
| leer          | SELECT | GET          |
| Crear         | INSERT | POST, PUT    |
| Actualizar    | UPDATE | PUT          |
| Borrar        | DELETE | SELECT       |


## Instalación

Dentro de la carpeta `Configuracion` se encuentra un archivo llamado `db.properties` debe 
ingresar y configurar su conexión o como ustedes lo tenga en su equipo.
```#connection
jdbc.driverClassName = com.mysql.cj.jdbc.Driver
jdbc.username= root
jdbc.password=
jdbc.url = jdbc:mysql://localhost:3306/fonYou?useSSL=false&serverTimeZone=UTC
```
Dentro del proyecto, buscar el archivo `ContextoAplicacion` que se encuentra 
en la carpeta configuración del proyecto, aqui es donde van a ingresar la ruta 
donde se va a guardar el archivo`db.properties`, ya que es recomendable que ningún 
tercero tenga acceso a el.

Recuerde que en el archivo `db.properties` es donde debes realizar la configuración 
de su base de datos.

```
@PropertySource("file:/xampp/htdocs/Curso/wilsonevs/configuracion/db.properties")
```

## Acceder al Swagger
Se implemento para que pudiera vizualizar los Json y poderlos consumir.


## Query base de datos
Se dejo dentro de la carpeta `query` que se encuentra dentro del proyecto. en el cual
contiene dos archivos `sql.sql` la creación de la base de datos
y `insertarDatos.json` las preguntas en Json. 

Ruta Swagger: [Swagger](http://localhost:8089/fonYou/api/swagger-ui.html#/)
http://localhost:8089/wilsonevs/api/swagger-ui.html#/

## API Referencía
##Nota 
* Primero se insertar las preguntas

* Segundo en el servicio del estudiante ingresa los datos y las preguntas


## GUIA DEL CONSUMO

#### Get - Busqueda completa


Ejemplo de como se debe consumir en Postman, 

Todos los servicios del GET se crearón con paginacionasi que debe ingresar lo siguiente
```http
  GET http://localhost:8089/fonYou/api/estudiante/?paginaActual=1&paginacion=100
  GET http://localhost:8089/fonYou/api/pregunta/usuario/?paginaActual=1&paginacion=100
  GET http://localhost:8089/fonYou/api/respuesta/?paginaActual=1&paginacion=100
```

| PARAMETRO         | TIPO      | VALOR     |
| :--------         | :-------  | :---------|
| `paginaActual`    | `int`     | `1`       |
| `paginacion`      | `int`     | `100`       |

#### Get - Busqueda por ID

```http
  GET http://localhost:8089/fonYou/api/estudiante/1
```

#### Post

```http
  POST http://localhost:8089/fonYou/api/estudiante/
```

```JSON
{
  "nombre": "Wilson Valencia 2",
  "edad": "33",
  "ciudad": "Medellín",
  "zona_horaria": "2022/03/26 22:00:00",
  "respuesta": [
    {
      "id_pregunta": 1,
      "respuesta": "a"
    },
    {
      "id_pregunta": 2,
      "respuesta": "b"
    },
    {
      "id_pregunta": 3,
      "respuesta": "a"
    }
  ]
}
```

#### Put

```http
  PUT http://localhost:8089/wilsonevs/api/estudiante/
```

```JSON
{
  "id_estudiante": 1,
  "nombre": "Wilson Valencia 2",
  "edad": "33",
  "ciudad": "Medellín",
  "zona_horaria": "2022/03/26 22:00:00",
  "respuesta": [
    {
      "id_pregunta": 1,
      "respuesta": "a"
    },
    {
      "id_pregunta": 2,
      "respuesta": "b"
    },
    {
      "id_pregunta": 3,
      "respuesta": "a"
    }
  ]
}
```


#### DELETE - Eliminación por ID

```http
  DELETE http://localhost:8089/fonYou/api/estudiante/1
