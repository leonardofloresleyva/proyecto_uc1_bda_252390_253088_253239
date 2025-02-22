/*
	Vista que muestra el historial de citas de un paciente,
    tanto por especialidad del médico, un rango de fechas
    y un paciente en específico.
*/
CREATE VIEW HISTORIAL_CITAS_PACIENTES AS
SELECT
	C.ID_CITA,
    C.FECHA_HORA,
    C.TIPO_CITA,
    M.ID_MEDICO,
    P.ID_PACIENTE,
    M.ESPECIALIDAD
FROM CITAS AS C
INNER JOIN MEDICOS AS M
ON M.ID_MEDICO = C.ID_MEDICO
INNER JOIN PACIENTES AS P
ON P.ID_PACIENTE = C.ID_PACIENTE;