CREATE DATABASE IF NOT EXISTS cinedev_db;

GRANT ALL PRIVILEGES ON cinedev_db.* TO '${DB_USER:-cinedev_user}'@'%' IDENTIFIED BY '${DB_PASSWORD:-cinedev_password}';

FLUSH PRIVILEGES;
