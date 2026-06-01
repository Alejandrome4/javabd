/*DROP DATABASE IF EXISTS sistema_reservas;

CREATE DATABASE sistema_reservas
CHARACTER SET utf8mb4
COLLATE utf8mb4_spanish2_ci;

USE sistema_reservas;

CREATE TABLE IF NOT EXISTS USUARIO (
        id_usuario INT AUTO_INCREMENT PRIMARY KEY,
        correo_electronico VARCHAR(100) NOT NULL UNIQUE,
contraseña VARCHAR(100) NOT NULL,
nombre VARCHAR(100) NOT NULL UNIQUE,
fecha_nacimiento DATE,
tipo_usuario ENUM('Administrador', 'Normal') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS RECURSO (
        id_recurso INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
descripcion TEXT,
ubicacion VARCHAR(200),
capacidad INT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS HORARIO (
        id_horario INT AUTO_INCREMENT PRIMARY KEY,
        dia_semana ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
hora_inicio TIME NOT NULL,
hora_fin TIME NOT NULL,
CONSTRAINT chk_horario CHECK (hora_fin > hora_inicio)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ADMINISTRADOR (
        id_usuario INT PRIMARY KEY,
        telefono_guardia VARCHAR(20) NOT NULL,
FOREIGN KEY (id_usuario)
REFERENCES USUARIO(id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS USUARIONORMAL (
        id_usuario INT PRIMARY KEY,
        direccion VARCHAR(200),
telefono_movil VARCHAR(20),
fotografia VARCHAR(255),
FOREIGN KEY (id_usuario)
REFERENCES USUARIO(id_usuario)
ON DELETE RESTRICT
ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS DISPONIBLEEN (
        id_recurso INT NOT NULL,
        id_horario INT NOT NULL,
        PRIMARY KEY (id_recurso, id_horario),
FOREIGN KEY (id_recurso)
REFERENCES RECURSO(id_recurso)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (id_horario)
REFERENCES HORARIO(id_horario)
ON DELETE CASCADE
ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS RESERVA (
        id_recurso INT NOT NULL,
        id_reserva_local INT NOT NULL,
        id_usuario INT NOT NULL,
        fecha DATE NOT NULL,
        hora_inicio TIME NOT NULL,
        hora_fin TIME NOT NULL,
        coste DECIMAL(10,2),
numero_plazas INT,
motivo TEXT,
observaciones TEXT,
PRIMARY KEY (id_recurso, id_reserva_local),
FOREIGN KEY (id_recurso)
REFERENCES RECURSO(id_recurso)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (id_usuario)
REFERENCES USUARIONORMAL(id_usuario)
ON DELETE RESTRICT
ON UPDATE CASCADE,
CONSTRAINT chk_reserva_horario CHECK (hora_fin > hora_inicio)
) ENGINE=InnoDB;

INSERT INTO USUARIO (correo_electronico, contraseña, nombre, fecha_nacimiento, tipo_usuario) VALUES
('coord_general@hub.es', 'pass9876', 'Elena Santonja', '1978-05-12', 'Administrador'),
        ('dev_senior@freelance.com', 'alpha2024', 'Marcos Ruiz', '1988-09-03', 'Normal'),
        ('diseno_arte@estudio.com', 'pixel_art', 'Lucía Méndez', '1993-02-14', 'Normal'),
        ('soporte_it@hub.es', 'secure_root', 'David Vizcaíno', '1999-10-25', 'Administrador'),
        ('comunidad@elche.es', 'gestor2026', 'Beatriz Soler', '1991-06-30', 'Normal');

INSERT INTO ADMINISTRADOR (id_usuario, telefono_guardia) VALUES
(1, '+34 600100200'),
        (4, '+34 699888777');

INSERT INTO USUARIONORMAL (id_usuario, direccion, telefono_movil, fotografia) VALUES
(2, 'Calle Corredora 10, Elche', '+34 655444333', 'marcos_profile.png'),
        (3, 'Sector V, Manzana 4, Elche', '+34 677888999', NULL),
        (5, 'Carrer Major del Raval 5, Elche', NULL, 'beatriz_avatar.jpg');

INSERT INTO RECURSO (nombre, descripcion, ubicacion, capacidad) VALUES
('Estudio de Podcast', 'Cabina insonorizada con micrófonos profesionales', 'Planta Baja - Ala Este', 3),
        ('Sala Creativa', 'Espacio con pizarras de cristal y materiales de dibujo', 'Planta 2 - Box 204', 8),
        ('Auditorio Principal', 'Escenario con sonido envolvente y 100 butacas', 'Planta Baja - Acceso A', 100),
        ('Taller de Prototipado', 'Impresoras 3D y herramientas de corte láser', 'Sótano - Taller 1', 6),
        ('Terraza Lounge', 'Zona abierta para eventos de networking', 'Azotea - Planta 4', 40);

INSERT INTO HORARIO (dia_semana, hora_inicio, hora_fin) VALUES
('Lunes', '10:00:00', '12:00:00'),
        ('Miércoles', '17:00:00', '19:00:00'),
        ('Jueves', '09:00:00', '11:00:00'),
        ('Viernes', '18:00:00', '20:00:00'),
        ('Sábado', '10:00:00', '14:00:00');

INSERT INTO DISPONIBLEEN (id_recurso, id_horario) VALUES
(1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5);

INSERT INTO RESERVA (id_recurso, id_reserva_local, id_usuario, fecha, hora_inicio, hora_fin, coste, numero_plazas, motivo, observaciones) VALUES
(1, 1, 2, '2026-03-20', '10:00:00', '11:30:00', 45.00, 2, 'Grabación Episodio 1', 'Necesita técnico de sonido'),
        (2, 1, 2, '2026-03-22', '17:00:00', '18:30:00', 20.00, 5, 'Brainstorming App', 'Llevar rotuladores propios'),
        (3, 1, 5, '2026-03-24', '09:00:00', '11:00:00', 150.00, 80, 'Charla Ciberseguridad', 'Probar HDMI antes'),
        (1, 2, 2, '2026-03-27', '10:30:00', '12:00:00', 45.00, 1, 'Edición de audio', 'Uso de monitores de estudio'),
        (5, 1, 3, '2026-03-28', '11:00:00', '13:30:00', 80.00, 25, 'Inauguración Exposición', 'Servicio de catering contratado');

        SELECT nombre, ubicacion, capacidad FROM RECURSO;

SELECT u.nombre AS Usuario, r.motivo, r.fecha
FROM USUARIO u
JOIN RESERVA r ON u.id_usuario = r.id_usuario;*/