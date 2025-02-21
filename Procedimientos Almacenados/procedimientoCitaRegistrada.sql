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
    IN ESTADO_CITA VARCHAR(50),
    IN TIPO_CITA ENUM("Previa", "Emergencia")
)
BEGIN
	-- Comenzar la transacción
    START TRANSACTION;
		-- Insertar la cita
        INSERT INTO CITAS (FECHA_HORA, ID_MEDICO, ID_PACIENTE) VALUES (FECHAHORA, IDMEDICO, IDPACIENTE);
        -- Obtener id generado por la tabla de citas
		SET @CITA_ID = LAST_INSERT_ID();
        
        -- Validar si es cita previa o cita de emergencia
        IF TIPO_CITA = "Previa" THEN
			INSERT INTO CITAS_MEDICAS (ID_CITA_PREVIA, ESTADO, ID_CITA) VALUES (@CITA_ID, ESTADO_CITA, @CITA_ID);
		ELSEIF TIPO_CITA = "Emergencia" THEN
			-- Generar un folio automático
            SET @FOLIO_EMERGENCIA = ROUND(RAND()*100000000); -- Crea una cadena de números aleatorios de 8 dígitos para guardar en el folio
			INSERT INTO CITAS_EMERGENCIA (ID_CITA_EMERGENCIA, ESTADO, FOLIO, ID_CITA) VALUES (@CITA_ID, ESTADO_CITA, @FOLIO_EMERGENCIA, @CITA_ID);
		END IF;
	COMMIT;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- --
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
    IN CITA_ID INT
)
BEGIN
	-- Crear variable para verificar si existe la cita
	DECLARE CITA_EXISTE INT;
	-- Empezar transacción
    START TRANSACTION;
		-- Verificar que la cita existe
		SELECT COUNT(*)
		INTO CITA_EXISTE
		FROM CITAS
		WHERE ID_CITA = CITA_ID;
		-- Lanzar mensaje de error si existe la cita
        IF CITA_EXISTE = 0 THEN
			SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = "La cita que quiere consultar no existe.";
		ELSE 
			-- Si la cita sí existe, se inserta en las consultas
            INSERT INTO CONSULTAS (MOTIVO, DIAGNOSTICO, TRATAMIENTO, ID_CITA)
				VALUES (MOTIVO_CONSULTA, DIAGNOSTICO_CONSULTA, TRATAMIENTO_CONSULTA, CITA_ID);
		END IF;
	COMMIT;
END //
DELIMITER ;
    
