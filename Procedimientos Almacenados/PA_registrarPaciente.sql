
DELIMITER $$
CREATE PROCEDURE REGISTRAR_PACIENTE(
    IN CORREO VARCHAR(150),
    IN PASSW VARCHAR(128),
    IN ROL_PACIENTE ENUM("Paciente"),
    IN NOMBRE_PACIENTE VARCHAR(100),
    IN APELLIDO_P VARCHAR(100),
    IN APELLIDO_M VARCHAR(100),
    IN TELEFONO_PACIENTE VARCHAR(20),
    IN FECHA_N DATE,
    IN ESTADO_PACIENTE ENUM("Alta"),
    IN COLONIA_PACIENTE VARCHAR(100),
    IN CALLE_PACIENTE VARCHAR(100),
    IN NUMERO_PACIENTE VARCHAR(100)
)
BEGIN
	START TRANSACTION;
		-- 1.- Se inserta el usuario.
        INSERT INTO USUARIOS(
			USUARIO, 
            CONTRASENIA, 
            ROL
		)
        VALUES(
			CORREO, PASSW, ROL_PACIENTE
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
            FECHA_NACIMIENTO, 
            ESTADO
		)
        VALUES(
			@ID_USUARIO, 
            NOMBRE_PACIENTE, 
            APELLIDO_P, 
            APELLIDO_M, 
            TELEFONO_PACIENTE, 
            FECHA_N, 
            ESTADO_PACIENTE
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
	ROLLBACK;
END $$
DELIMITER ;

CALL REGISTRAR_PACIENTE(
	"amlo@gmail.com",
    "xhalKJsdldkLKS*%%/)/)LL",
    "Paciente",
    "Andres Manuel",
    "Lopez",
    "Obrador",
    "6442897654",
    "1953-10-13",
    "Alta",
    "Edomex",
    "Miguel Aleman",
    "45");
