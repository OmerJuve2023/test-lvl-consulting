-- Insertar roles en la tabla 'roles'

-- Rol de administrador con todos los permisos
INSERT INTO roles (authority) VALUES ('ADMIN');

-- Rol de usuario básico con permisos limitados
INSERT INTO roles (authority) VALUES ('USER');

-- Rol de moderador con permisos para gestionar contenido
INSERT INTO roles (authority) VALUES ('MODERATOR');

-- Rol de invitado con permisos mínimos
INSERT INTO roles (authority) VALUES ('GUEST');

-- Rol de analista con permisos para ver datos pero no para modificar
INSERT INTO roles (authority) VALUES ('ANALYST');

-- Rol de desarrollador con permisos para desarrollo pero no para administración
INSERT INTO roles (authority) VALUES ('DEVELOPER');
