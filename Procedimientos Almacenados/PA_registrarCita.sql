/*
	Procedimiento almacenado para registrar un nuevo cita en la clínica.
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
    IN TIPO_CITA ENUM("Previa", "Emergencia")
)
BEGIN
	-- Comenzar la transacción
    START TRANSACTION;
		-- Verificar si la cita que se quiere ingresar ya está asociada a una misma fecha y un mismo médico
		IF EXISTS (
			SELECT 1
			FROM CITAS
			WHERE ID_PACIENTE = IDPACIENTE
			AND ID_MEDICO = IDMEDICO
			AND DATE(FECHA_HORA) = DATE(FECHAHORA) -- Solo compara la fecha
		) THEN
			-- Si ya existe una cita, lanzar un error y deshacer la transacción
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El paciente ya tiene una cita programada con este médico para esta fecha.';
			ROLLBACK;
		ELSE
			-- Insertar la cita
			INSERT INTO CITAS (FECHA_HORA, ID_MEDICO, ID_PACIENTE) VALUES (FECHAHORA, IDMEDICO, IDPACIENTE);
			-- Obtener id generado por la tabla de citas
			SET @CITA_ID = LAST_INSERT_ID();
			-- Validar si es cita previa o cita de emergencia
			IF TIPO_CITA = "Previa" THEN
				INSERT INTO CITAS_MEDICAS (ID_CITA_PREVIA, ID_CITA) VALUES (@CITA_ID, @CITA_ID);
			ELSEIF TIPO_CITA = "Emergencia" THEN
				-- Generar un folio automático
				SET @FOLIO_EMERGENCIA = ROUND(RAND()*100000000); -- Crea una cadena de números aleatorios de 8 dígitos para guardar en el folio
				INSERT INTO CITAS_EMERGENCIA (ID_CITA_EMERGENCIA, FOLIO, ID_CITA) VALUES (@CITA_ID, @FOLIO_EMERGENCIA, @CITA_ID);
			END IF;
			COMMIT;
		END IF;
END //
DELIMITER ;
