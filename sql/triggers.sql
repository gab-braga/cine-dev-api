DELIMITER //

CREATE TRIGGER after_update_room
AFTER UPDATE ON rooms FOR EACH ROW
BEGIN
    IF NEW.map_uuid <> OLD.map_uuid THEN
        DELETE a FROM areas a
        JOIN maps m ON a.map_uuid = m.uuid
        WHERE m.uuid = OLD.map_uuid;

        DELETE m FROM maps m
        WHERE m.uuid = OLD.map_uuid;
    END IF;
END;

CREATE TRIGGER after_insert_session
AFTER INSERT ON sessions FOR EACH ROW
BEGIN
    INSERT INTO tickets (uuid, area_uuid, session_uuid)
    SELECT UUID_TO_BIN(UUID()), a.uuid, NEW.uuid
    FROM areas a JOIN rooms r ON r.map_uuid = a.map_uuid
    WHERE r.uuid = NEW.room_uuid AND a.area_type = 'SEAT';
END;

CREATE TRIGGER after_update_session
AFTER UPDATE ON sessions FOR EACH ROW
BEGIN
    DELETE FROM tickets WHERE session_uuid = OLD.uuid;

    INSERT INTO tickets (uuid, area_uuid, session_uuid)
    SELECT UUID_TO_BIN(UUID()), a.uuid, NEW.uuid
    FROM areas a JOIN rooms r ON r.map_uuid = a.map_uuid
    WHERE r.uuid = NEW.room_uuid AND a.area_type = 'SEAT';
END;
//

DELIMITER ;
