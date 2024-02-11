DELIMITER //

CREATE TRIGGER after_update_room
AFTER UPDATE ON rooms FOR EACH ROW
BEGIN
    IF (NEW.map_uuid <> OLD.map_uuid) THEN
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
    IF (NEW.room_uuid <> OLD.room_uuid) THEN
        DELETE FROM tickets WHERE session_uuid = OLD.uuid;

        INSERT INTO tickets (uuid, area_uuid, session_uuid)
        SELECT UUID_TO_BIN(UUID()), a.uuid, NEW.uuid
        FROM areas a JOIN rooms r ON r.map_uuid = a.map_uuid
        WHERE r.uuid = NEW.room_uuid AND a.area_type = 'SEAT';
    END IF;
END;

CREATE TRIGGER after_update_ticket
AFTER UPDATE ON tickets FOR EACH ROW
BEGIN
    IF (NEW.reservation_uuid IS NULL XOR OLD.reservation_uuid IS NULL) THEN
        UPDATE sessions SET
        number_free_seats = (SELECT COUNT(*) FROM tickets
            WHERE session_uuid = NEW.session_uuid
            AND reservation_uuid IS NULL)
        WHERE uuid = NEW.session_uuid;
    END IF;
END;
//

DELIMITER ;
