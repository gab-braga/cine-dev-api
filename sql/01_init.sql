CREATE DATABASE IF NOT EXISTS '${DB_NAME:-cinedev_db}';

GRANT ALL PRIVILEGES ON '${DB_NAME:-cinedev_db}'.* TO '${DB_USER:-cinedev_user}'@'%' IDENTIFIED BY '${DB_PASSWORD:-cinedev_password}';

FLUSH PRIVILEGES;
