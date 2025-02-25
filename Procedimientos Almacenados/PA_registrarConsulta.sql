/*
	Procedimiento almacenado que registra una consulta en la base
    de datos. Recibe como parámetros los atributos de la consulta.
    Valida si el id de la cita existe, de ser así, inserta la nueva
    consulta en la base de datos.
*/
DELIMITER //
CREATE PROCEDURE REGISTRAR_CONSULTA (
	IN MOTIVO_CONSULTA VARCHAR(100),
    IN DIAGNOSTICO_CONSULTA TEXT,
    IN TRATAMIENTO_CONSULTA TEXT,
    IN ID INT
)
BEGIN
	-- Declarar variables
	DECLARE FECHA_HORA_CITA DATETIME; -- Obtiene fecha_hora de la cita
    DECLARE CITA_TIPO VARCHAR(10); -- Obtiene el tipo de cita
    DECLARE MINUTOS_TOLERANCIA INT;

	-- Empezar transacción
    START TRANSACTION;
	-- Validar si ya existe una consulta asociada a la cita
    IF EXISTS (
		SELECT 1
		FROM CONSULTAS
		WHERE ID_CITA = ID
	) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Ya existe una consulta asociada a la cita.";
		ROLLBACK;
    END IF;
	-- Validar si la cita existe
    IF NOT EXISTS(
		SELECT 1
        FROM CITAS
        WHERE ID_CITA = ID
	) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "La cita no existe.";
        ROLLBACK;
    END IF;

	-- Obtener la fecha y hora de la cita y su tipo
	SELECT FECHA_HORA, TIPO_CITA 
	INTO FECHA_HORA_CITA, CITA_TIPO
	FROM CITAS
	WHERE ID_CITA = ID;
	-- Establecer los minutos de tolerancia (15 para previa, 10 para emergencia)
	IF CITA_TIPO = "Previa" THEN
		SET MINUTOS_TOLERANCIA = 15;
	ELSEIF CITA_TIPO = "Emergencia" THEN
		SET MINUTOS_TOLERANCIA = 10;
	ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Tipo de cita erróneo.";
		ROLLBACK;
	END IF;
	-- Validar si la cita excede los minutos de tolerancia
	IF NOW() > DATE_ADD(FECHA_HORA_CITA, INTERVAL MINUTOS_TOLERANCIA MINUTE) THEN
		-- Marcar la cita como no atendida
        IF CITA_TIPO = "Previa" THEN
			UPDATE CITAS_PREVIAS SET ESTADO = "No atendida"
			WHERE ID_CITA_PREVIA = ID;
		ELSEIF CITA_TIPO = "Emergencia" THEN
			UPDATE CITAS_EMERGENCIA SET ESTADO = "No atendida"
			WHERE ID_CITA_EMERGENCIA = ID;
		END IF;
		-- Lanzar excepción
		ROLLBACK;
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = "El tiempo de tolerancia para registrar la consulta ha acabado.";
	END IF;
    
	-- Si no ocurrió ninguna excepción, marcar la cita como atendida
	IF CITA_TIPO = "Emergencia" THEN
		UPDATE CITAS_EMERGENCIA SET ESTADO = "Atendida"
		WHERE ID_CITA_EMERGENCIA = ID;
	ELSE
		UPDATE CITAS_PREVIAS SET ESTADO = "Atendida"
		WHERE ID_CITA_PREVIA = ID;
	END IF;
	-- Insertar la consulta
	INSERT INTO CONSULTAS (MOTIVO, DIAGNOSTICO, TRATAMIENTO, ID_CITA)
	VALUES (MOTIVO_CONSULTA, DIAGNOSTICO_CONSULTA, TRATAMIENTO_CONSULTA, ID);

	COMMIT;
END //
DELIMITER ;