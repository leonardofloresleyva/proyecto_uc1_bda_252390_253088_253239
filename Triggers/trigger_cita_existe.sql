/*
	Trigger que valida que una consulta nueva
    esté asociada a una cita existente.
*/
DELIMITER $$
CREATE TRIGGER CITA_EXISTE
BEFORE INSERT ON CONSULTAS
FOR EACH ROW
BEGIN
	-- Crear variable para verificar si existe la cita
	DECLARE CITA_EXISTE INT;
    -- Verificar que la cita existe
	SELECT COUNT(*)
	INTO CITA_EXISTE
	FROM CITAS
	WHERE ID_CITA = NEW.ID_CITA;
	-- Lanzar mensaje de error si existe la cita
	IF CITA_EXISTE = 0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "La cita que quiere registrar en la consulta no existe.";
	END IF;
END $$
DELIMITER ;

DROP TRIGGER CITA_EXISTE;