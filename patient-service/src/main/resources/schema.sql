CREATE TABLE IF NOT EXISTS patient (
    id             BIGSERIAL PRIMARY KEY,
    last_name      VARCHAR(100) NOT NULL,
    first_name     VARCHAR(100) NOT NULL,
    date_of_birth  DATE NOT NULL,
    gender         VARCHAR(1) NOT NULL CHECK (gender IN ('M', 'F')),
    address        VARCHAR(255),
    phone          VARCHAR(20),
    CONSTRAINT uq_patient_identity UNIQUE (last_name, first_name, date_of_birth)
);