
CREATE DATABASE fonYou;
USE fonYou;

CREATE TABLE IF NOT EXISTS estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) not null,
    edad VARCHAR(10) not null,
    ciudad VARCHAR(45) not null,
    nota_total VARCHAR(10) not null,
    zona_horaria TIMESTAMP,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS pregunta (
    id_pregunta INT AUTO_INCREMENT PRIMARY KEY,
    pregunta TEXT(250) not null,
    opcion1 TEXT(250) not null,
    opcion2 TEXT(250) not null,
    opcion3 TEXT(250) not null,
    opcion4 TEXT(250) not null,
    respuesta TEXT(10) not null,
    valor_nota TEXT(10) not null,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS respuesta (
    id_respuesta INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT(10) not null,
    id_pregunta INT(10) not null,
    respuesta TEXT(10) not null,
    fecha_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_pregunta) REFERENCES pregunta(id_pregunta) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;