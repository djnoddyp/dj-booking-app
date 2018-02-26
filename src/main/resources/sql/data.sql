INSERT INTO Artists (ID, Name, Fee) VALUES (1, 'Tiesto', 5000);
INSERT INTO Artists (ID, Name, Fee) VALUES (2, 'Armin van Buuren', 4500);
INSERT INTO Artists (ID, Name, Fee) VALUES (3, 'Andy Moor', 3000);
INSERT INTO Artists (ID, Name, Fee) VALUES (4, 'Paul Oakenfold', 3750);
INSERT INTO Artists (ID, Name, Fee) VALUES (5, 'Solarstone', 4000);
INSERT INTO Artists (ID, Name, Fee) VALUES (6, 'Noddy P', 50);

INSERT INTO users (username, password) VALUES ('randy', 'burger');
INSERT INTO users (username, password) VALUES ('patrick', 'trance');

INSERT INTO user_roles (username, role_name) VALUES ('randy', 'guest');
INSERT INTO user_roles (username, role_name) VALUES ('patrick', 'admin');

INSERT INTO roles_permissions (role_name, permission) VALUES ('guest', 'view.user');
INSERT INTO roles_permissions (role_name, permission) VALUES ('admin', 'view.user');
INSERT INTO roles_permissions (role_name, permission) VALUES ('admin', 'add.user');
INSERT INTO roles_permissions (role_name, permission) VALUES ('admin', 'delete.user');


