-- =============================================================================
-- FINEFLOW SEED DATA - Test Users and Demo Data
-- Run AFTER FINEFLOW_MASTER_v4_0.sql
-- =============================================================================

SET DEFINE OFF;

-- ============================================================================
-- 1. CREAR COLEGIO DE PRUEBA (I.E. Demo FineFlow)
-- ============================================================================

-- Verificar si ya existe
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM SCHOOLS WHERE DOCUMENT_NUMBER = '20123456789';
    IF v_exists = 0 THEN
        INSERT INTO SCHOOLS (id, name, document_number, address, phone, email, status, created_at, updated_at)
        VALUES (
            'SCHOOL-001',
            'I.E. Demo FineFlow',
            '20123456789',
            'Av. Brasil 1234, Lima',
            '999888777',
            'admin@demo.edu.pe',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Colegio creado: SCHOOL-001');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Colegio ya existe: SCHOOL-001');
    END IF;
    COMMIT;
END;
/

-- ============================================================================
-- 2. CREAR USUARIOS DE PRUEBA
-- BCrypt hash de "admin123" = $2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi
-- BCrypt hash de "password" = $2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
-- ============================================================================

-- Admin del colegio
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM USERS WHERE EMAIL = 'admin@demo.edu.pe' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO USERS (id, school_id, email, password_hash, role, first_name, last_name, status, created_at, updated_at)
        VALUES (
            'USER-ADMIN-001',
            'SCHOOL-001',
            'admin@demo.edu.pe',
            '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',  -- admin123
            'ADMIN',
            'Admin',
            'Demo',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Usuario admin creado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario admin ya existe');
    END IF;
    COMMIT;
END;
/

-- Coordinador
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM USERS WHERE EMAIL = 'coord@demo.edu.pe' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO USERS (id, school_id, email, password_hash, role, first_name, last_name, status, created_at, updated_at)
        VALUES (
            'USER-COORD-001',
            'SCHOOL-001',
            'coord@demo.edu.pe',
            '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',  -- admin123
            'COORDINATOR',
            'Coordinador',
            'Demo',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Usuario coordinador creado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario coordinador ya existe');
    END IF;
    COMMIT;
END;
/

-- Docente
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM USERS WHERE EMAIL = 'teacher@demo.edu.pe' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO USERS (id, school_id, email, password_hash, role, first_name, last_name, status, created_at, updated_at)
        VALUES (
            'USER-TCHR-001',
            'SCHOOL-001',
            'teacher@demo.edu.pe',
            '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',  -- admin123
            'TEACHER',
            'Profesor',
            'Demo',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Usuario docente creado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario docente ya existe');
    END IF;
    COMMIT;
END;
/

-- Estudiante
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM USERS WHERE EMAIL = 'student@demo.edu.pe' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO USERS (id, school_id, email, password_hash, role, first_name, last_name, status, created_at, updated_at)
        VALUES (
            'USER-STUD-001',
            'SCHOOL-001',
            'student@demo.edu.pe',
            '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',  -- admin123
            'STUDENT',
            'Estudiante',
            'Demo',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Usuario estudiante creado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario estudiante ya existe');
    END IF;
    COMMIT;
END;
/

-- Apoderado
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM USERS WHERE EMAIL = 'guardian@demo.edu.pe' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO USERS (id, school_id, email, password_hash, role, first_name, last_name, status, created_at, updated_at)
        VALUES (
            'USER-GUARD-001',
            'SCHOOL-001',
            'guardian@demo.edu.pe',
            '$2a$12$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',  -- admin123
            'GUARDIAN',
            'Apoderado',
            'Demo',
            'ACTIVE',
            SYSTIMESTAMP,
            SYSTIMESTAMP
        );
        DBMS_OUTPUT.PUT_LINE('Usuario apoderado creado');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usuario apoderado ya existe');
    END IF;
    COMMIT;
END;
/

-- ============================================================================
-- 3. CREAR ESTRUCTURA ACADÉMICA DE PRUEBA
-- ============================================================================

-- Nivel: Secundaria
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM ACADEMIC_LEVELS WHERE NAME = 'Secundaria' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO ACADEMIC_LEVELS (id, school_id, name, order_num, is_active, created_at)
        VALUES ('LEVEL-SEC-001', 'SCHOOL-001', 'Secundaria', 2, 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Nivel Secundaria creado');
    END IF;
    COMMIT;
END;
/

-- Año Escolar: 3ro de Secundaria 2025
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM SCHOOL_YEARS WHERE NAME = '3ro Secundaria 2025' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO SCHOOL_YEARS (id, school_id, academic_level_id, name, grade_number, calendar_year, is_active, created_at)
        VALUES ('YEAR-3SEC-001', 'SCHOOL-001', 'LEVEL-SEC-001', '3ro Secundaria 2025', 3, 2025, 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Año escolar creado');
    END IF;
    COMMIT;
END;
/

-- Período Académico: 1er Bimestre
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM ACADEMIC_PERIODS WHERE NAME = 'I Bimestre' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO ACADEMIC_PERIODS (id, school_id, school_year_id, name, period_type, start_date, end_date, is_active, created_at)
        VALUES ('PERIOD-1BIM-001', 'SCHOOL-001', 'YEAR-3SEC-001', 'I Bimestre', 'BIMESTER', DATE '2025-03-01', DATE '2025-04-30', 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Período académico creado');
    END IF;
    COMMIT;
END;
/

-- Sección: 3ro A
DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM SECTIONS WHERE NAME = '3A' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO SECTIONS (id, school_id, school_year_id, name, max_capacity, tutor_id, is_active, created_at)
        VALUES ('SEC-3A-001', 'SCHOOL-001', 'YEAR-3SEC-001', '3A', 35, NULL, 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Sección 3A creada');
    END IF;
    COMMIT;
END;
/

-- ============================================================================
-- 4. CREAR CURSOS DE PRUEBA
-- ============================================================================

DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM COURSES WHERE NAME = 'Matemática' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO COURSES (id, school_id, name, code, description, color_hex, is_active, created_at)
        VALUES ('COURSE-MAT-001', 'SCHOOL-001', 'Matemática', 'MAT', 'Matemática según CNEB', '#FF5722', 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Curso Matemática creado');
    END IF;
    COMMIT;
END;
/

DECLARE
    v_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*) INTO v_exists FROM COURSES WHERE NAME = 'Comunicación' AND SCHOOL_ID = 'SCHOOL-001';
    IF v_exists = 0 THEN
        INSERT INTO COURSES (id, school_id, name, code, description, color_hex, is_active, created_at)
        VALUES ('COURSE-COM-001', 'SCHOOL-001', 'Comunicación', 'COM', 'Comunicación según CNEB', '#2196F3', 1, SYSTIMESTAMP);
        DBMS_OUTPUT.PUT_LINE('Curso Comunicación creado');
    END IF;
    COMMIT;
END;
/

-- ============================================================================
-- 5. VERIFICACIÓN
-- ============================================================================

SELECT '=== USUARIOS DE PRUEBA ===' AS INFO FROM DUAL;
SELECT EMAIL, ROLE, FIRST_NAME, LAST_NAME FROM USERS WHERE SCHOOL_ID = 'SCHOOL-001';

SELECT '=== CONTRASEÑA PARA TODOS: admin123 ===' AS INFO FROM DUAL;
SELECT '=== SCHOOL_ID PARA LOGIN: SCHOOL-001 ===' AS INFO FROM DUAL;

-- =============================================================================
-- FIN DEL SCRIPT DE SEED DATA
-- =============================================================================
