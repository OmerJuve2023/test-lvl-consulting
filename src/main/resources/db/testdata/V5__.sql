-- Insertar roles en la tabla 'roles'

-- Rol de administrador con todos los permisos
INSERT INTO roles (authority) VALUES ('ROLE_ADMIN');

-- Rol de usuario básico con permisos limitados
INSERT INTO roles (authority) VALUES ('ROLE_USER');

-- Rol de moderador con permisos para gestionar contenido
INSERT INTO roles (authority) VALUES ('ROLE_MODERATOR');

-- Rol de invitado con permisos mínimos
INSERT INTO roles (authority) VALUES ('ROLE_GUEST');

-- Rol de analista con permisos para ver datos pero no para modificar
INSERT INTO roles (authority) VALUES ('ROLE_ANALYST');

-- Rol de desarrollador con permisos para desarrollo pero no para administración
INSERT INTO roles (authority) VALUES ('ROLE_DEVELOPER');
