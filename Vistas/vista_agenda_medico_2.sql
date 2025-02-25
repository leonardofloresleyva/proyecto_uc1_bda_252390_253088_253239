/*
	Vista que muestra la agenda de un médico, es decir, las citas
    asociadas a este que no estén atendidas, canceladas (en el
    caso de citas previas) o no atendidas según la hora en la que
    se registraron.
*/
CREATE VIEW AGENDA_CITAS_MEDICO AS
SELECT
	M.ID_MEDICO,
	C.ID_CITA,
    C.FECHA_HORA,
    C.TIPO_CITA,
    P.ID_PACIENTE,
    P.NOMBRES AS NOMBRES_PACIENTE,
    P.APELLIDO_PATERNO APELLIDO_PATERNO_PACIENTE,
    P.APELLIDO_MATERNO APELLIDO_MATERNO_PACIENTE,
    P.ESTADO AS ESTADO_PACIENTE,
    P.FECHA_NACIMIENTO AS FECHA_NACIMIENTO_PACIENTE,
    CASE 
		WHEN C.FECHA_HORA > NOW() THEN "Pendiente de asistir"
        ELSE "No atendida"
	END AS ESTADO_CITA
FROM CITAS AS C
INNER JOIN MEDICOS AS M
ON M.ID_MEDICO = C.ID_MEDICO
INNER JOIN PACIENTES AS P
ON P.ID_PACIENTE = C.ID_PACIENTE
WHERE (CP.ESTADO = 'No atendida' OR CE.ESTADO = 'No atendida');
USE potro_clinica;
DROP VIEW AGENDA_CITAS_MEDICO;