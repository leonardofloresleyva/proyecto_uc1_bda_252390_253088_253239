-- Registrar medicos llamando al procedimiento REGISTRAR_MEDICO()
CALL REGISTRAR_MEDICO(
	"dhfiurursf",
    "Medico",
    "Felipe Humberto",
    "Cabada",
    "Arismendiz", 
    "AE-1234567",
    "Medicina General",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"876543",
    "Medico",
    "Christian Gibrán",
    "Durán", "Solano",
    "765498",
    "Neumología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"0ASsuyw",
    "Medico",
    "José Guadalupe",
    "Valtierra",
    "Navarro",
    "AE-70870",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"mdlmslnr",
    "Medico",
    "Claudia",
    "Sheinbaum",
    "Pardo",
    "AE-109965",
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"JbiIUbKBk",
    "Medico",
    "Kassandra",
    "Quezada",
    "Villalobos", 
    "113259",
    "Psicología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"86545hgf",
    "Medico",
    "Harry",
    "James",
    "Potter",
    "AE-009971",
    "Pediatría",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"njugi98h",
    "Medico",
    "Alejandro",
    "Almonte",
    "Rivera",
    "AE-112244", 
    "Cardiología",
    "Alta"
);

CALL REGISTRAR_MEDICO(
	"HHAHA77",
    "Medico",
    "Enrique",
    "Peña",
    "Nieto",
    "AE-776655",
    "Oncología",
    "Alta"
);

-- Registrar horarios para médicos
-- Horarios para el médico con ID 3
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('LUNES', '09:00', '17:00', 3),
('MIERCOLES', '14:00', '20:00', 3),
('VIERNES', '10:00', '18:00', 3);
-- Horarios para el médico con ID 4
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('MARTES', '08:00', '16:00', 4),
('JUEVES', '11:00', '19:00', 4);
-- Horarios para el médico con ID 5
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('LUNES', '13:00', '21:00', 5),
('MIERCOLES', '07:00', '15:00', 5),
('VIERNES', '15:00', '23:00', 5);
-- Horarios para el médico con ID 6
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('MARTES', '10:00', '18:00', 6),
('JUEVES', '08:00', '16:00', 6);
-- Horarios para el médico con ID 7
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('LUNES', '08:00', '12:00', 7),
('MIERCOLES', '16:00', '20:00', 7),
('VIERNES', '11:00', '15:00', 7);
-- Horarios para el médico con ID 8
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('MARTES', '14:00', '18:00', 8),
('JUEVES', '10:00', '14:00', 8);
-- Horarios para el médico con ID 9
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('LUNES', '11:00', '19:00', 9),
('MIERCOLES', '09:00', '17:00', 9),
('VIERNES', '13:00', '21:00', 9);
-- Horarios para el médico con ID 10
INSERT INTO HORARIOS_MEDICOS (DIA_SEMANA, HORA_ENTRADA, HORA_SALIDA, ID_MEDICO) VALUES
('MARTES', '07:00', '15:00', 10),
('JUEVES', '15:00', '23:00', 10);