DELIMITER //

CREATE TRIGGER after_insert_session
AFTER INSERT ON sessions FOR EACH ROW
BEGIN
    INSERT INTO tickets (uuid, session_id, seat_id, status_ticket)
    SELECT UUID_TO_BIN(UUID()), NEW.uuid, uuid, 'FREE'
    FROM seats WHERE room_id = NEW.room_id AND empty_space = 0;
END;
//

DELIMITER ;