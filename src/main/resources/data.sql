-- Création de la table USERS
CREATE TABLE IF NOT EXISTS USERS (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255),
  name VARCHAR(255),
  password VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

-- Création de la table RENTALS
CREATE TABLE IF NOT EXISTS RENTALS (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  surface NUMERIC,
  price NUMERIC,
  picture VARCHAR(255),
  description VARCHAR(2000),
  owner_id INTEGER NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES USERS(id)
);

-- Création de la table MESSAGES
CREATE TABLE IF NOT EXISTS MESSAGES (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  rental_id INTEGER,
  user_id INTEGER,
  message VARCHAR(2000),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (rental_id) REFERENCES RENTALS(id),
  FOREIGN KEY (user_id) REFERENCES USERS(id)
);

-- Index unique sur l'email des utilisateurs
ALTER TABLE USERS ADD UNIQUE INDEX USERS_index (email);

-- Insertion de données dans la table USERS
INSERT INTO USERS (email, name, password, created_at, updated_at)
VALUES ('bonjour@bonjour.com', 'user', '$2a$10$Q2oDpfI1Z0oTTIoEXNMi4OdwyaSH/DPV..wG7hEqpELVPSlQKKP4S', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO USERS (email, name, password, created_at, updated_at)
VALUES ('bonjour2@bonjour.com', 'Bonjour2', '$2a$10$Q2oDpfI1Z0oTTIoEXNMi4OdwyaSH/DPV..wG7hEqpELVPSlQKKP4S', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion de données dans la table RENTALS
INSERT INTO RENTALS (name, surface, price, picture, description, owner_id, created_at, updated_at)
VALUES ('Apartment 1', 60.0, 1200.0, 'https://media.istockphoto.com/id/1448371583/fr/photo/immeubles-dappartements-modernes-de-grande-hauteur-dans-le-quartier-des-affaires-zuidas.jpg?s=1024x1024&w=is&k=20&c=IYmx9SoKUuIiBvnl2P_X4mfwO71OLYs9pEd-oBlML98=', 'partment', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO RENTALS (name, surface, price, picture, description, owner_id, created_at, updated_at)
VALUES ('Apartment 2', 45.0, 950.0, 'https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?q=80&w=2760&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'apartment 2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertion de données dans la table MESSAGES
INSERT INTO MESSAGES (rental_id, user_id, message, created_at, updated_at)
VALUES (1, 2, 'I am interpreting', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO MESSAGES (rental_id, user_id, message, created_at, updated_at)
VALUES (2, 1, 'I have questions', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
