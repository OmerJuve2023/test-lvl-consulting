-- Insertar roles en la tabla 'roles'

-- Rol de administrador con todos los permisos
INSERT INTO roles (authority)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_GUEST'),
       ('ROLE_ANALYST'),
       ('ROLE_DEVELOPER'),
       ('ROLE_TESTER'),
       ('ROLE_MANAGER'),
       ('ROLE_CLIENT');

-- Insertar usuarios en la tabla 'users'

-- INSERT INTO users (username, password, first_name, last_name, company, phone, email, enabled, image)
-- VALUES ('user10', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'jose', 'perez', 'company', '123456789', 'rafa@gmail.com', true, null),
--        ('user1', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'maria', 'lopez', 'company', '987654321', 'maria.lopez@gmail.com', true, null),
--        ('user2', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'carlos', 'gomez', 'company', '123123123', 'carlos.gomez@gmail.com', true, null),
--        ('user3', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'ana', 'martinez', 'company', '321321321', 'ana.martinez@gmail.com', true, null),
--        ('user4', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'luis', 'rodriguez', 'company', '456456456', 'luis.rodriguez@gmail.com', true, null),
--        ('user5', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'sofia', 'fernandez', 'company', '654654654', 'sofia.fernandez@gmail.com', true, null),
--        ('user6', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'diego', 'sanchez', 'company', '789789789', 'diego.sanchez@gmail.com', true, null),
--        ('user7', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'laura', 'diaz', 'company', '987987987', 'laura.diaz@gmail.com', true, null),
--        ('user8', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'pablo', 'torres', 'company', '147147147', 'pablo.torres@gmail.com', true, null),
--        ('user9', '$2a$10$3NOa79b0ULUrJEa/eai3yezNL7oV1aPamZTI7JojhOa7KotjvOUUS',
--         'carla', 'morales', 'company', '258258258', 'carla.morales@gmail.com', true, null);

-- Insertar roles en la tabla 'user_roles'

/*INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1);*/

-- -- Insertar proyectos en la tabla 'proyect'
--
-- INSERT INTO proyect (nombre, descripcion, estado, fecha_inicio, fecha_fin, user_id, image)
-- VALUES ('Proyecto Alpha', 'Descripción del Proyecto Alpha', 'Activo', '2024-01-15 09:00:00', '2024-06-15 18:00:00',
--         1,
--         NULL),
--        ('Proyecto Beta', 'Descripción del Proyecto Beta', 'En progreso', '2024-02-01 09:00:00',
--         '2024-07-01 18:00:00',
--         NULL),
--        ('Proyecto Gamma', 'Descripción del Proyecto Gamma', 'Finalizado', '2024-03-10 09:00:00',
--         '2024-08-10 18:00:00',
--         2, NULL),
--        ('Proyecto Delta', 'Descripción del Proyecto Delta', 'Activo', '2024-04-20 09:00:00', '2024-09-20 18:00:00',
--         4,
--         NULL),
--        ('Proyecto Epsilon', 'Descripción del Proyecto Epsilon', 'En progreso', '2024-05-15 09:00:00',
--         '2024-10-15 18:00:00', 5, NULL),
--        ('Proyecto Zeta', 'Descripción del Proyecto Zeta', 'Pendiente', '2024-06-01 09:00:00', '2024-11-01 18:00:00',
--         7,
--         NULL),
--        ('Proyecto Eta', 'Descripción del Proyecto Eta', 'Activo', '2024-07-10 09:00:00', '2024-12-10 18:00:00', 7,
--         NULL),
--        ('Proyecto Theta', 'Descripción del Proyecto Theta', 'En progreso', '2024-08-05 09:00:00',
--         '2025-01-05 18:00:00',
--         8, NULL),
--        ('Proyecto Iota', 'Descripción del Proyecto Iota', 'Finalizado', '2024-09-15 09:00:00', '2025-02-15 18:00:00',
--         8,
--         NULL),
--        ('Proyecto Kappa', 'Descripción del Proyecto Kappa', 'Pendiente', '2024-10-01 09:00:00',
--         '2025-03-01 18:00:00',
--         10, NULL);
--
-- -- Insertar tareas en la tabla 'task'
--
-- INSERT INTO task (id, codigo, nombre, categoria, imagen, proyecto_id)
-- VALUES (1, 'Tarea 1', 'Tarea 1', 'Categoría 1', NULL, 1),
--        (2, 'Tarea 2', 'Tarea 2', 'Categoría 2', NULL, 2),
--        (3, 'Tarea 3', 'Tarea 3', 'Categoría 3', NULL, 3),
--        (4, 'Tarea 4', 'Tarea 4', 'Categoría 4', NULL, 4),
--        (5, 'Tarea 5', 'Tarea 5', 'Categoría 5', NULL, 5),
--        (6, 'Tarea 6', 'Tarea 6', 'Categoría 6', NULL, 6),
--        (7, 'Tarea 7', 'Tarea 7', 'Categoría 7', NULL, 7),
--        (8, 'Tarea 8', 'Tarea 8', 'Categoría 8', NULL, 8),
--        (9, 'Tarea 9', 'Tarea 9', 'Categoría 9', NULL, 9),
--        (10, 'Tarea 10', 'Tarea 10', 'Categoría 10', NULL, 10);