INSERT INTO users
    (uuid,
    active,
    confirmed,
    cpf,
    created_at,
    email,
    name,
    password,
    role)
VALUES (
    UUID_TO_BIN(UUID()),
    1,
    1,
    '71086848020',
    NOW(),
    "admin@mail.com",
    "Admin Default",
    "$2y$10$6wkZKR08B6BAqSPr7brfNOk1.YHA/Y03MJwPdslc./RqEkFxoL9tO",
    "ADMIN");