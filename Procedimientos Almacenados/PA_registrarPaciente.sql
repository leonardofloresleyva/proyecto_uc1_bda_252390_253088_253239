/*
	Procedimiento almacenado para registrar un nuevo paciente a la clínica.
    Recibe todos los datos relacionados al paciente, a excepción de su ID.
    Inserta los datos correspondientes a las tablas USUARIOS, PACIENTE y
    DIRECCIONES, en ese orden. Si sucede un error en alguna de las 
    operaciones, se revierten todos los cambios y se devuelve un mensaje.
*/
DELIMITER $$
CREATE PROCEDURE REGISTRAR_PACIENTE(
    IN CORREO VARCHAR(150),
    IN PASSW VARCHAR(128),
    IN NOMBRE_PACIENTE VARCHAR(100),
    IN APELLIDO_P VARCHAR(100),
    IN APELLIDO_M VARCHAR(100),
    IN TELEFONO_PACIENTE VARCHAR(20),
    IN FECHA_N DATE,
    IN COLONIA_PACIENTE VARCHAR(100),
    IN CALLE_PACIENTE VARCHAR(100),
    IN NUMERO_PACIENTE VARCHAR(100)
)
BEGIN
	-- EXIT HANDLER para revertir todos los cambios si sucede un error en una operación.
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Error en registro de paciente. Los cambios fueron revertidos.";
	END;
    -- Comienza la transacción.
	START TRANSACTION;
		-- 1.- Se inserta el usuario.
        INSERT INTO USUARIOS(
			USUARIO, 
            CONTRASENIA
		)
        VALUES(
			CORREO, PASSW
		);
		-- 2.- Se obtiene el ID generado por la tabla USUARIOS y se almacena en una variable.
        SET @ID_USUARIO = LAST_INSERT_ID();
		-- 3.- Se inserta el paciente.
        INSERT INTO PACIENTES(
			ID_PACIENTE, 
			NOMBRES, 
            APELLIDO_PATERNO, 
            APELLIDO_MATERNO, 
            TELEFONO, 
            FECHA_NACIMIENTO
		)
        VALUES(
			@ID_USUARIO, 
            NOMBRE_PACIENTE, 
            APELLIDO_P, 
            APELLIDO_M, 
            TELEFONO_PACIENTE, 
            FECHA_N
		);
        -- 4.- Se inserta la direccion del paciente.
        INSERT INTO DIRECCIONES(
			COLONIA,
            CALLE,
            NUMERO,
            ID_PACIENTE
		)
        VALUES(
			COLONIA_PACIENTE,
            CALLE_PACIENTE,
            NUMERO_PACIENTE,
            @ID_USUARIO
		);
	COMMIT; -- Si no hubo ningún error, se guardan los cambios permanentemente.
END $$ -- Fin de procedimiento.
DELIMITER ;

-- Ejemplo de un registro de un paciente. Prueba del procedimiento.
CALL REGISTRAR_PACIENTE(
	"johnwick@outlook.com",
    "askldaskjdsajdasdjlasj",
    "John",
    "Wick",
    "Wick",
    "733737373",
    "1970-09-18",
    "Nueva York",
    "Cum",
    "100");

-- Elimina el procedimiento.
DROP PROCEDURE REGISTRAR_PACIENTE;