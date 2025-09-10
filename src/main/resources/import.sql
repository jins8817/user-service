INSERT INTO "user" ( username, email, codice_fiscale, nome, cognome,created_at,updated_at) VALUES ( 'urstest', 'usr.test@gmail.com', 'RSSMRA80L05F593A', 'Mario', 'Rossi',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO "user" ( username, email, codice_fiscale, nome, cognome,created_at,updated_at) VALUES ( 'urstest2', 'usr2.test@gmail.com', 'RSSMRA80L05F593B', 'Mario', 'Rossi',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO "user" ( username, email, codice_fiscale, nome, cognome,created_at,updated_at) VALUES ( 'urstest3', 'usr3.test@gmail.com', 'RSSMRA80L05F593C', 'Mario', 'Rossi',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO "user" ( username, email, codice_fiscale, nome, cognome,created_at,updated_at) VALUES ( 'urstest4', 'usr4.test@gmail.com', 'RSSMRA80L05F593D', 'Mario', 'Rossi',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO roles (role_id, name) VALUES (1,'OWNER');
INSERT INTO roles (role_id, name) VALUES (2,'OPERATOR');
INSERT INTO roles (role_id, name) VALUES (3,'MAINTAINER');
INSERT INTO roles (role_id, name) VALUES (4,'DEVELOPER');
INSERT INTO roles (role_id, name) VALUES (5,'REPORTER');


INSERT INTO user_role (user_id, role_id) VALUES (1,1);
INSERT INTO user_role (user_id, role_id) VALUES (1,4);

