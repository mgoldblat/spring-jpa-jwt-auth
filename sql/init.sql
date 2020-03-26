use auth;
INSERT INTO user (id, username, password, user_details_id) values (1, 'admin', '$2a$10$/3nzrqwhOqZGrcvUcYblFOBtxEn4FC1EPFIiyqR7U8VKuyvjsak5C', null);
INSERT INTO user (id, username, password, user_details_id) values (2, 'customer', '$2a$10$Tb4Dktmddq3Cp1gYK9Dnn.ZkS9/fwH4YMsTv/MAnh5MmQssZZ6pNW', null);
INSERT INTO role (id, name, description) values (1, 'ADMIN', 'System administrator');
INSERT INTO role (id, name, description) values (2, 'CUSTOMER', 'External client');
INSERT INTO user_roles (user_id, role_id) values (1,1);
INSERT INTO user_roles (user_id, role_id) values (2,2);