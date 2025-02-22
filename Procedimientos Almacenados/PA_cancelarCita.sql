/*
	Procedimiento almacenado para cancelar una cita que ya ha sido
    registrada, siempre y cuando esté dentro de un mínimo de 24 horas
    de anticipación. Recibe como parámetro el id de la cita y valida si
    es una cita previa (las citas de emergencia no pueden cancelarse),
    valida si se quiere cancelar dentro del periodo de tiempo de 24 horas
    y valida si la cita no ha sido atendida. Si las validaciones son
    incorrectas, se actualiza el estado de la cita previa de "No atendida" 
    a "Cancelada."
*/
DELIMITER //
CREATE PROCEDURE CANCELAR_CITA (
    IN CITA_ID INT
)
BEGIN
    -- Declarar variables
    DECLARE FECHA_HORA_CITA DATETIME;
    DECLARE DIFERENCIA_HORAS INT;
    DECLARE CITA_TIPO VARCHAR(20); -- Variable para almacenar el tipo de cita
    DECLARE CITA_ESTADO VARCHAR(20); -- Variable para almacenar el estado de la cita
    
    -- Obtener la fecha y hora, el tipo y el estado de la cita
	SELECT C.FECHA_HORA,
		CASE
			WHEN CP.ID_CITA_PREVIA IS NOT NULL THEN "Previa"
            WHEN CE.ID_CITA_EMERGENCIA IS NOT NULL THEN "Emergencia"
            ELSE NULL -- En caso de que no se encuentre la cita
		END,
        CASE
			WHEN CP.ESTADO IS NOT NULL THEN CP.ESTADO
            WHEN CE.ESTADO IS NOT NULL THEN CE.ESTADO
            ELSE NULL
		END
	INTO FECHA_HORA_CITA, CITA_TIPO, CITA_ESTADO
    FROM CITAS AS C
    LEFT JOIN CITAS_PREVIAS AS CP
    ON C.ID_CITA = CP.ID_CITA_PREVIA
    LEFT JOIN CITAS_EMERGENCIA AS CE
    ON C.ID_CITA = CE.ID_CITA_EMERGENCIA
    WHERE C.ID_CITA = CITA_ID;
    
    -- Verificar si existe la cita
    IF FECHA_HORA_CITA IS NULL THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "La cita no existe";
	END IF;
    -- Verificar si es una cita de emergencia
    IF CITA_TIPO = "Emergencia" THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "Una cita de emergencia no puede cancelarse";
	END IF;
    -- Verificar si la cita ya fue atendida
    IF CITA_ESTADO = "Atendida" THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "No se puede cancelar una cita atendida";
	END IF;
    
    -- Obtener la diferencia de horas
    SET DIFERENCIA_HORAS = TIMESTAMPDIFF(HOUR, NOW(), FECHA_HORA_CITA);
    -- Verificar si la cancelación se está realizando 24 horas antes del límite de tiempo
    IF DIFERENCIA_HORAS >= 24 THEN
		START TRANSACTION;
			-- Actualizar el estado de la cita
			UPDATE CITAS_PREVIAS
			SET ESTADO = "Cancelada"
			WHERE ID_CITA_PREVIA = CITA_ID;
        COMMIT;
	ELSE 
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "No se puede cancelar la cita, pasaron las 24 horas de anticipación";
	END IF;
END //
DELIMITER ;