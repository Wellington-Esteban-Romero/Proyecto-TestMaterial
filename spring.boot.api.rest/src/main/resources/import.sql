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


INSERT INTO pruebas (id,nombre,descripcion,fecha_creacion,tiempo_ejecucion,coste,persona_realiza_prueba,material_id,tipoprueba_id) VALUES (1,'prueba laboratorio','fue mal',STR_TO_DATE('1-01-2012', '%d-%m-%Y'),3,600.0,'Pedro',1,2);
INSERT INTO pruebas (id,nombre,descripcion,fecha_creacion,tiempo_ejecucion,coste,persona_realiza_prueba,material_id,tipoprueba_id) VALUES (2,'prueba ambiental','prueba correcta',STR_TO_DATE('1-01-2012', '%d-%m-%Y'),6,600.0,'Juan',1,6);

INSERT INTO tipospruebas (id, nombre) VALUES (1,'Pruebas de resistencia');
INSERT INTO tipospruebas (id, nombre) VALUES (2,'Pruebas de densidad');
INSERT INTO tipospruebas (id, nombre) VALUES (3,'Pruebas de absorción');
INSERT INTO tipospruebas (id, nombre) VALUES (4,'Pruebas de permeabilidad');
INSERT INTO tipospruebas (id, nombre) VALUES (5,'Pruebas de resistencia al fuego');
INSERT INTO tipospruebas (id, nombre) VALUES (6,'Pruebas de resistencia a los agentes químicos');
INSERT INTO tipospruebas (id, nombre) VALUES (7,'Pruebas de adherencia');


INSERT INTO materiales (id,descripcion,fabricante, fecha_adquisicion, vida_util,nombrematerial_id) VALUES (1,'material nuevo','EmpresaA', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 50, 1);
INSERT INTO materiales (id,descripcion,fabricante,fecha_adquisicion, vida_util,nombrematerial_id) VALUES (2,'material llego roto','EmpresaB',STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 50,1);

INSERT INTO `usuarios` (username,email, password, enabled,nombre,apellidos) VALUES ('pepe','ejemplo@gmail.com','$2a$10$S9M50w9pR1VQWGHcBeBNyOJ0Nr2fStBSo.Z6BvN2Vaqs046kcY3RO',1,"Wellington","Romero") ;
INSERT INTO `usuarios` (username,email, password, enabled,nombre,apellidos) VALUES ('admin','ejemploAdmin@gmail.com','$2a$10$FhdM/.IKyys8WRme77n2BO7Ry02wKmEs0/YA9JLZojks1YztaT.Hm',1,"Soraya","Romero") ;

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1); 