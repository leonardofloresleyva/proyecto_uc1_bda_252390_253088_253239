/*
	Trigger para validar que la consulta que se quiere
    crear esté asociada a una cita médica que está siendo
    atendida. En caso contrario, se lanza el trigger y
    no se guarda la consulta.
*/
DELIMITER //
CREATE TRIGGER ESTADO_CITAPREVIA_CONSULTA
BEFORE INSERT ON CONSULTAS
FOR EACH ROW
BEGIN
	-- Declarar variable para guardar el estado de la cita
    DECLARE ESTADO_CITA ENUM("Atendida", "Cancelada", "No atendida");
    
	-- Se obtiene el tipo de cita.
	SET @CITA_TIPO = (
		SELECT TIPO_CITA FROM CITAS
		WHERE ID_CITA = CITA_ID
	);
    
	IF @CITA_TIPO = "Emergencia" -- Si se trata de una cita de emergencia.
	THEN
		-- Obtener estado de la cita 
		SELECT ESTADO
		INTO ESTADO_CITA
		FROM CITAS_PREVIAS
		WHERE ID_CITA = NEW.ID_CITA;
	ELSE -- Si se trata de una cita previa.
		-- Obtener estado de la cita 
		SELECT ESTADO
		INTO ESTADO_CITA
		FROM CITAS_EMERGENCIA
		WHERE ID_CITA = NEW.ID_CITA;
	END IF;
    
	-- Validar que el estado de la cita sea atendida
	IF ESTADO_CITA != "Atendida" THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "No se puede registrar la consulta. La cita no ha sido atendida.";
	END IF;
END //
DELIMITER ;