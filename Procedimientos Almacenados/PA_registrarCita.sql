/*
	Procedimiento almacenado para registrar una nueva cita en la clínica.
    Recibe todos los datos relacionados a la cita sin el id de la cita,
    recibiendo también tanto el estado como el tipo de cita.
    Inserta los datos correspondientes a la tabla CITAS y, dependiendo
    del tipo de estado, agrega los datos a la tabla CITAS_MEDICAS
    O CITAS_EMERGENCIA.
*/
DELIMITER //
CREATE PROCEDURE REGISTRAR_CITA (
	IN FECHAHORA DATETIME,
    IN IDMEDICO INT,
    IN IDPACIENTE INT,
    IN CITA_TIPO ENUM("Previa", "Emergencia")
)
BEGIN
	-- Comenzar la transacción
    START TRANSACTION;
		-- Verificar si el médico está activo
		IF NOT EXISTS (
			SELECT 1
            FROM MEDICOS
            WHERE ID_MEDICO = IDMEDICO
            AND ESTADO = 'Activo'
		) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El médico no está activo.';
			ROLLBACK;
		ELSEIF EXISTS (
			SELECT 1
			FROM CITAS
			WHERE ID_PACIENTE = IDPACIENTE
			AND ID_MEDICO = IDMEDICO
			AND FECHA_HORA = FECHAHORA
		) THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El paciente ya tiene una cita programada con este médico para esta fecha.';
			ROLLBACK;
		ELSE
			-- Insertar la cita
			INSERT INTO CITAS (FECHA_HORA, TIPO_CITA, ID_MEDICO, ID_PACIENTE) VALUES (FECHAHORA, CITA_TIPO, IDMEDICO, IDPACIENTE);
			-- Obtener id generado por la tabla de citas
			SET @CITA_ID = LAST_INSERT_ID();
			-- Validar si es cita previa o cita de emergencia
			IF CITA_TIPO = "Previa" THEN
				INSERT INTO CITAS_PREVIAS (ID_CITA_PREVIA) VALUES (@CITA_ID);
			ELSEIF CITA_TIPO = "Emergencia" THEN
				-- Generar un folio automático
				SET @FOLIO_EMERGENCIA = ROUND(RAND()*100000000); -- Crea una cadena de números aleatorios de 8 dígitos para guardar en el folio
				INSERT INTO CITAS_EMERGENCIA (ID_CITA_EMERGENCIA, FOLIO) VALUES (@CITA_ID, @FOLIO_EMERGENCIA);
			END IF;
			COMMIT;
		END IF;
END //
DELIMITER ;