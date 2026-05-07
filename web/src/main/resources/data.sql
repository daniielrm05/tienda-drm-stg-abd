-- ==========================================
-- 1. MARCAS
-- ==========================================
INSERT INTO marca (nombre) VALUES ('Rolex');
INSERT INTO marca (nombre) VALUES ('Casio');
INSERT INTO marca (nombre) VALUES ('Seiko');
INSERT INTO marca (nombre) VALUES ('Omega');
INSERT INTO marca (nombre) VALUES ('Tag Heuer');
INSERT INTO marca (nombre) VALUES ('Samsung');


-- ==========================================
-- 2. CATEGORÍAS
-- ==========================================
INSERT INTO categoria_reloj (nombre, descripcion, imagen) VALUES
    ('Deportivo', 'Relojes resistentes, ideales para el deporte y aventura', 'deportivo.jpg'),
    ('Casual', 'Relojes versátiles para el día a día', 'casual.jpg'),
    ('Smartwatch', 'Relojes inteligentes con monitorización y notificaciones', 'smartwatch.jpg'),
    ('Elegante', 'Relojes de gala y diseños sofisticados', 'elegante.jpg');

-- ==========================================
-- 3. RELOJES
-- Nota: 'marca_id' es obligatorio (optional = false)
-- ==========================================
INSERT INTO reloj (codigo, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES
    ('1234567890123', 'Submariner', 'El reloj de buceo por excelencia. Presenta una caja de acero Oystersteel de 41 mm y un bisel giratorio unidireccional con disco Cerachrom de cerámica negra. Su esfera cuenta con grandes marcadores luminiscentes que garantizan una legibilidad óptima en las profundidades marinas. Resistente hasta 300 metros.', 8500.0, 10, 1, '/imagenes/relojes/rolex_sub.jpg'),
    ('2234567890123', 'F-91W', 'Un icono de la relojería digital minimalista y ultra resistente. Este modelo es famoso mundialmente por su precisión, batería de larga duración de hasta 7 años y funciones esenciales como cronómetro de 1/100 segundos, alarma diaria y señal horaria. Su diseño retro se mantiene vigente tras décadas de éxito.', 25.0, 0, 2, '/imagenes/relojes/casio_f91.jpg'),
    ('3234567890123', 'Alpinist', 'Reloj de exploración diseñado para los amantes de la montaña. Incorpora una brújula interna accionada por una corona secundaria a las 4 y un movimiento automático de alta precisión con 70 horas de reserva de marcha. Su cristal de zafiro con lente de aumento protege una esfera verde con índices dorados.', 750.0, 5, 3, '/imagenes/relojes/seiko_alp.jpg'),
    ('4234567890123', 'Speedmaster', 'Conocido como el "Moonwatch", fue el primer reloj usado en la Luna durante las misiones Apolo. Este cronógrafo de cuerda manual presenta una escala taquimétrica en el bisel y un diseño que apenas ha cambiado en 50 años. Es una pieza histórica indispensable para cualquier coleccionista de alta relojería.', 6200.0, 0, 4, '/imagenes/relojes/omega_speed.jpg'),
    ('5234567890123', 'G-Shock Mudmaster', 'Construido para resistir los entornos más hostiles del planeta. Su estructura está diseñada para evitar que el barro, el polvo y los escombros entren en los botones, mientras que su tecnología Triple Sensor permite medir la dirección, la presión barométrica, la altitud y la temperatura con gran exactitud.', 350.0, 15, 2, '/imagenes/relojes/casio_mud.jpg'),
    ('6234567890123', 'Luxe Luxury', 'Reloj de gama media-baja que destaca por sus acabados que imitan la alta joyería. Cuenta con una caja de aluminio cepillado de alta densidad y agujas bañadas en color oro rosa. Es la opción ideal para quienes buscan un accesorio elegante y funcional para eventos sociales sin realizar una inversión elevada.', 100.0, 0, 3, NULL),
    ('7234567890123', 'Stainless Steel Formula 1', 'Inspirado en el mundo de la competición automovilística. Este modelo de acero inoxidable presenta una esfera negra con detalles en rojo deportivo y un bisel fijo con escala taquimétrica. Su brazalete incluye un sistema de extensión para poder llevarlo cómodamente sobre el mono de carreras o ropa deportiva.', 500.0, 20, 5, '/imagenes/relojes/tagheuer_stainless.jpg'),
    ('8234567890123', 'Seiko 5 Sports', 'El sucesor del legendario SKX. Un reloj automático robusto con una estanqueidad de 100 metros y un fondo de caja transparente que permite ver el mecanismo en funcionamiento. Su diseño versátil se adapta tanto a un estilo casual como a uno más formal gracias a su correa de acero inoxidable de eslabones sólidos.', 280.0, 5, 3, '/imagenes/relojes/seiko_5.jpg'),
    ('9234567890123', 'Omega Seamaster', 'El modelo elegido por James Bond desde 1995. Destaca por su bisel cerámico azul con escala de inmersión de esmalte blanco y su válvula de escape de helio a las 10 en punto. El movimiento Master Chronometer garantiza la mayor precisión y resistencia magnética de la industria relojera suiza actual.', 5400.0, 0, 4, '/imagenes/relojes/omega_sea.png'),
    ('0234567890123', 'Carrera Chronograph', 'Un cronógrafo deportivo elegante inspirado en la famosa carrera Carrera Panamericana. Su diseño de esfera limpia y minimalista prioriza la legibilidad inmediata de los tiempos, convirtiéndolo en un instrumento de precisión para pilotos. Monta el calibre automático Heuer 02 con una impresionante reserva de marcha.', 4200.0, 10, 5, '/imagenes/relojes/tag_carrera.jpg'),
    ('1111111111111', 'Casio Edifice', 'Tecnología y velocidad. Cronógrafo de cuarzo con panel solar y conexión Bluetooth para ajuste de hora automático.', 150.0, 20, 2, '/imagenes/relojes/casio_edifice.jpg'),
    ('2222222222222', 'Prospex Turtle', 'Famoso por su caja en forma de tortuga. Un diver profesional certificado con una visibilidad nocturna excepcional.', 450.0, 0, 3, '/imagenes/relojes/seiko_turtle.jpg'),
    ('3333333333333', 'Day-Date Oro', 'El reloj de los presidentes. Fabricado exclusivamente en oro de 18 quilates con el emblemático brazalete President.', 32000.0, 0, 1, '/imagenes/relojes/rolex_daydate.jpg'),
    ('4444444444444', 'Vintage Geneve', 'Una pieza de colección de los años 70. Movimiento de cuerda manual y caja de oro fino. No incluye imagen por ser pieza única.', 1800.0, 0, 4, NULL),
    ('5555555555555', 'Aquaracer Professional', 'El reloj de herramientas para el mundo acuático. Bisel cerámico y estanqueidad hasta 300 metros con cierre de seguridad.', 2900.0, 12, 5, '/imagenes/relojes/tag_aqua.jpg'),
    ('6666666666666', 'G-Shock GA-2100', 'Conocido como CasiOak por su bisel octogonal. El G-Shock más delgado y popular de la última década.', 99.0, 0, 2, '/imagenes/relojes/casio_oak.jpg'),
    ('7777777777777', 'Presage Cocktail', 'Inspirado en la cultura de los cócteles de Tokio. Esfera con textura "sunburst" que brilla intensamente bajo la luz.', 420.0, 5, 3, '/imagenes/relojes/seiko_cocktail.jpg'),
    ('8888888888888', 'Constellation', 'Distinguido por sus famosas "garras" en los laterales de la caja. Un equilibrio perfecto entre joyería y relojería.', 6800.0, 8, 4, '/imagenes/relojes/omega_const.jpg'),
    ('9999999999999', 'Monaco Gulf Edition', 'El icónico reloj cuadrado que Steve McQueen hizo famoso. Colores legendarios de la escudería Gulf Racing.', 7100.0, 0, 5, '/imagenes/relojes/tag_monaco.jpg'),
    ('1010101010101', 'Explorer II', 'El compañero ideal para expediciones polares o espeleología gracias a su aguja de 24 horas y bisel fijo graduado.', 9200.0, 0, 1, '/imagenes/relojes/rolex_explorer.jpg');


-- ==========================================
-- 4. RELACIÓN RELOJ-CATEGORÍA
-- IDs categorías: 1:Deportivo, 2:Casual, 3:Smartwatch, 4:Elegante
-- ==========================================
-- Relojes con MÚLTIPLES categorías
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (1, 1); -- Submariner -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (1, 4); -- Submariner -> Elegante
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (2, 1); -- F-91W -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (2, 2); -- F-91W -> Casual
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (11, 1); -- Edifice -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (11, 2); -- Edifice -> Casual

-- Relojes con UNA sola categoría
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (3, 1); -- Alpinist -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (4, 4); -- Speedmaster -> Elegante
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (5, 1); -- Mudmaster -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (8, 2); -- Seiko 5 -> Casual
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (9, 4); -- Seamaster -> Elegante
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (10, 1); -- Carrera -> Deportivo

-- Usuario Administrador
INSERT INTO usuario (username, nombre, apellidos, email, password, fecha_registro)
VALUES (
        'admin',
        'Admin',
        'Sistema',
        'admin@tienda.com',
        '$2a$12$D476zB.F6NInWnpL98X9beH6G1eH/1Z516Yv32zT83D.O2Ym0NfMa',
        CURRENT_TIMESTAMP
       );

-- Usuario Normal 1 (Cliente estándar)
INSERT INTO usuario (username, nombre, apellidos, email, password, fecha_registro)
VALUES ('cliente1', 'Dani', 'Ruiz Magro', 'dani@correo.com', '$2a$12$D476zB.F6NInWnpL98X9beH6G1eH/1Z516Yv32zT83D.O2Ym0NfMa', CURRENT_TIMESTAMP);

-- Usuario Normal 2 (Otro cliente)
INSERT INTO usuario (username, nombre, apellidos, email, password, fecha_registro)
VALUES ('user2', 'Salvador', 'Trincado Grande', 'salva@correo.com', '$2a$12$D476zB.F6NInWnpL98X9beH6G1eH/1Z516Yv32zT83D.O2Ym0NfMa', CURRENT_TIMESTAMP);

-- ==========================================
-- 5. ROLES Y ASIGNACIONES
-- ==========================================

-- 1. Insertamos los roles en la tabla 'rol' (o 'roles', asegúrate de cómo la llamó tu equipo)
--INSERT INTO rol (nombre) VALUES ('ROLE_USER');
--INSERT INTO rol (nombre) VALUES ('ROLE_ADMIN');

-- Asignar ADMIN al usuario 'admin'
--INSERT INTO usuarios_roles (usuario_id, rol_id)
--SELECT u.id, r.id FROM usuario u, rol r WHERE u.username = 'admin' AND r.nombre = 'ROLE_ADMIN';

-- Asignar USER a 'cliente1'
-- INSERT INTO usuarios_roles (usuario_id, rol_id)
-- SELECT u.id, r.id FROM usuario u, rol r WHERE u.username = 'cliente1' AND r.nombre = 'ROLE_USER';

-- Asignar USER a 'user2'
-- INSERT INTO usuarios_roles (usuario_id, rol_id)
-- SELECT u.id, r.id FROM usuario u, rol r WHERE u.username = 'user2' AND r.nombre = 'ROLE_USER';