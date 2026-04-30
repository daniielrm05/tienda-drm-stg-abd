-- ==========================================
-- 1. MARCAS
-- ==========================================
INSERT INTO marca (nombre) VALUES ('Rolex');
INSERT INTO marca (nombre) VALUES ('Casio');
INSERT INTO marca (nombre) VALUES ('Seiko');
INSERT INTO marca (nombre) VALUES ('Omega');
INSERT INTO marca (nombre) VALUES ('Tag Heuer');

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
    ('1234567890123', 'Submariner', 'El reloj de buceo por excelencia. Resistente a 300 metros, fabricado en acero de ostra y con bisel giratorio unidireccional Cerachrom.', 8500.0, 10, 1, 'rolex_sub.jpg'),
    ('2234567890123', 'F-91W', 'Un clásico digital minimalista y ultra resistente. Batería de 7 años, luz y cronómetro.', 25.0, 0, 2, 'casio_f91.jpg'),
    ('3234567890123', 'Alpinist', 'Reloj de exploración con brújula interna y movimiento automático. Cristal de zafiro.', 750.0, 5, 3, 'seiko_alp.jpg'),
    ('4234567890123', 'Speedmaster', 'El primer reloj en la Luna. Cronógrafo manual con una historia inigualable en la exploración espacial.', 6200.0, 0, 4, 'omega_speed.jpg'),
    ('5234567890123', 'G-Shock Mudmaster', 'Diseñado para los entornos más extremos. Resistente a golpes, barro y vibraciones.', 350.0, 15, 2, 'casio_mud.jpg'),
    ('6234567890123', 'Reloj sin imagen', 'Descripción de un reloj que no tiene imagen asignada para probar el listado.', 100.0, 0, 3, NULL),
    ('7234567890123', 'Reloj muy largo', 'Esta es una descripción muy larga de más de 200 caracteres para que puedas probar en el listado HTML la función de abreviar. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 500.0, 20, 5, 'largo.jpg'),
    ('8234567890123', 'Seiko 5 Sports', 'Reloj automático robusto con esfera azul y correa de acero. Un icono de la fiabilidad japonesa para el uso diario.', 280.0, 5, 3, 'seiko_5.jpg'),
    ('9234567890123', 'Omega Seamaster', 'El reloj de James Bond. Elegancia y resistencia bajo el agua con válvula de escape de helio y movimiento Master Chronometer.', 5400.0, 0, 4, 'omega_sea.jpg'),
    ('0234567890123', 'Carrera Chronograph', 'Inspirado en las carreras de coches clásicas. Esfera limpia, máxima legibilidad y un diseño deportivo atemporal.', 4200.0, 10, 5, 'tag_carrera.jpg'),
    ('1111111111111', 'Casio Edifice', 'Tecnología y velocidad. Cronógrafo de cuarzo con panel solar y conexión Bluetooth para ajuste de hora automático.', 150.0, 20, 2, 'casio_edifice.jpg'),
    ('2222222222222', 'Prospex Turtle', 'Famoso por su caja en forma de tortuga. Un diver profesional certificado con una visibilidad nocturna excepcional.', 450.0, 0, 3, 'seiko_turtle.jpg'),
    ('3333333333333', 'Day-Date Oro', 'El reloj de los presidentes. Fabricado exclusivamente en oro de 18 quilates con el emblemático brazalete President.', 32000.0, 0, 1, 'rolex_daydate.jpg'),
    ('4444444444444', 'Vintage Geneve', 'Una pieza de colección de los años 70. Movimiento de cuerda manual y caja de oro fino. No incluye imagen por ser pieza única.', 1800.0, 0, 4, NULL),
    ('5555555555555', 'Aquaracer Professional', 'El reloj de herramientas para el mundo acuático. Bisel cerámico y estanqueidad hasta 300 metros con cierre de seguridad.', 2900.0, 12, 5, 'tag_aqua.jpg'),
    ('6666666666666', 'G-Shock GA-2100', 'Conocido como CasiOak por su bisel octogonal. El G-Shock más delgado y popular de la última década.', 99.0, 0, 2, 'casio_oak.jpg'),
    ('7777777777777', 'Presage Cocktail', 'Inspirado en la cultura de los cócteles de Tokio. Esfera con textura "sunburst" que brilla intensamente bajo la luz.', 420.0, 5, 3, 'seiko_cocktail.jpg'),
    ('8888888888888', 'Constellation', 'Distinguido por sus famosas "garras" en los laterales de la caja. Un equilibrio perfecto entre joyería y relojería.', 6800.0, 8, 4, 'omega_const.jpg'),
    ('9999999999999', 'Monaco Gulf Edition', 'El icónico reloj cuadrado que Steve McQueen hizo famoso. Colores legendarios de la escudería Gulf Racing.', 7100.0, 0, 5, 'tag_monaco.jpg'),
    ('1010101010101', 'Explorer II', 'El compañero ideal para expediciones polares o espeleología gracias a su aguja de 24 horas y bisel fijo graduado.', 9200.0, 0, 1, 'rolex_explorer.jpg');


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
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (11, 3); -- Edifice -> Smartwatch

-- Relojes con UNA sola categoría
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (3, 1); -- Alpinist -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (4, 4); -- Speedmaster -> Elegante
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (5, 1); -- Mudmaster -> Deportivo
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (8, 2); -- Seiko 5 -> Casual
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (9, 4); -- Seamaster -> Elegante
INSERT INTO reloj_categoria (reloj_id, categoria_id) VALUES (10, 1); -- Carrera -> Deportivo