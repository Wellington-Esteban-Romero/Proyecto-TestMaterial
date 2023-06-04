INSERT INTO nombresmateriales (id, nombre) VALUES (1,'Concreto');
INSERT INTO nombresmateriales (id, nombre) VALUES (2,'Ladrillo');
INSERT INTO nombresmateriales (id, nombre) VALUES (3,'Piedra');
INSERT INTO nombresmateriales (id, nombre) VALUES (4,'Madera');
INSERT INTO nombresmateriales (id, nombre) VALUES (5,'Vidrio');
INSERT INTO nombresmateriales (id, nombre) VALUES (6,'Acero');
INSERT INTO nombresmateriales (id, nombre) VALUES (7,'Asfalto');
INSERT INTO nombresmateriales (id, nombre) VALUES (8,'Yeso');
INSERT INTO nombresmateriales (id, nombre) VALUES (9,'Plástico');
INSERT INTO nombresmateriales (id, nombre) VALUES (10,'Poliestireno expandido (EPS)');
INSERT INTO nombresmateriales (id, nombre) VALUES (11,'Hormigón celular');
INSERT INTO nombresmateriales (id, nombre) VALUES (12,'Cerámica');
INSERT INTO nombresmateriales (id, nombre) VALUES (13,'Mampostería');
INSERT INTO nombresmateriales (id, nombre) VALUES (14,'Adoquines');
INSERT INTO nombresmateriales (id, nombre) VALUES (15,'Azulejos');
INSERT INTO nombresmateriales (id, nombre) VALUES (16,'Paneles de yeso');
INSERT INTO nombresmateriales (id, nombre) VALUES (17,'Metal expandido');
INSERT INTO nombresmateriales (id, nombre) VALUES (18,'Fibra de vidrio');
INSERT INTO nombresmateriales (id, nombre) VALUES (19,'Membranas impermeabilizantes');
INSERT INTO nombresmateriales (id, nombre) VALUES (20,'Pintura');

INSERT INTO tipospruebas (id, nombre) VALUES (1,'Pruebas de resistencia');
INSERT INTO tipospruebas (id, nombre) VALUES (2,'Pruebas de densidad');
INSERT INTO tipospruebas (id, nombre) VALUES (3,'Pruebas de absorción');
INSERT INTO tipospruebas (id, nombre) VALUES (4,'Pruebas de permeabilidad');
INSERT INTO tipospruebas (id, nombre) VALUES (5,'Pruebas de resistencia al fuego');
INSERT INTO tipospruebas (id, nombre) VALUES (6,'Pruebas de resistencia a los agentes químicos');
INSERT INTO tipospruebas (id, nombre) VALUES (7,'Pruebas de adherencia');


INSERT INTO materiales (id,descripcion,fabricante, fecha_adquisicion, vida_util,nombrematerial_id) VALUES (1, 'Material nuevo','Empresa A', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 50, 1);
INSERT INTO materiales (id,descripcion,fabricante,fecha_adquisicion, vida_util,nombrematerial_id) VALUES (2, 'Material llegó roto','Empresa B',STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 50, 3);

INSERT INTO `usuarios` (username,email, password, enabled, nombre,apellidos) VALUES ('Welly.tecnico','ejemplo@gmail.com','$2a$10$S9M50w9pR1VQWGHcBeBNyOJ0Nr2fStBSo.Z6BvN2Vaqs046kcY3RO', 1, "Wellington","Romero") ;
INSERT INTO `usuarios` (username,email, password, enabled, nombre,apellidos) VALUES ('Pablo.jefe','ejemploAdmin@gmail.com','$2a$10$FhdM/.IKyys8WRme77n2BO7Ry02wKmEs0/YA9JLZojks1YztaT.Hm', 1, "Pablo","Jacinto") ;

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1); 