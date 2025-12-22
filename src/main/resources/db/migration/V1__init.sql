CREATE TABLE students (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  phone VARCHAR(20),
  date_of_birth DATE,
  registration_number VARCHAR(50) UNIQUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE teachers (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  lastName VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  phone VARCHAR(20),
  employee_number VARCHAR(50) UNIQUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE classrooms (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  school_year VARCHAR(10) NOT NULL
);

CREATE TABLE subjects (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE enrollments (
  id BIGSERIAL PRIMARY KEY,
  student_id BIGINT NOT NULL,
  classroom_id BIGINT NOT NULL,
  enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES students(id),
  CONSTRAINT fk_enrollment_classroom FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
  CONSTRAINT uk_student_classroom UNIQUE (student_id, classroom_id)
);

CREATE TABLE teacher_subjects (
  id BIGSERIAL PRIMARY KEY,
  teacher_id BIGINT NOT NULL,
  subject_id BIGINT NOT NULL,
  CONSTRAINT fk_ts_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id),
  CONSTRAINT fk_ts_subject FOREIGN KEY (subject_id) REFERENCES subjects(id),
  CONSTRAINT uk_teacher_subject UNIQUE (teacher_id, subject_id)
);

CREATE TABLE grades (
  id BIGSERIAL PRIMARY KEY,
  student_id BIGINT NOT NULL,
  subject_id BIGINT NOT NULL,
  teacher_id BIGINT NOT NULL,
  grade_value NUMERIC(4,2) NOT NULL,
  semester VARCHAR(10),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_grade_student FOREIGN KEY (student_id) REFERENCES students(id),
  CONSTRAINT fk_grade_subject FOREIGN KEY (subject_id) REFERENCES subjects(id),
  CONSTRAINT fk_grade_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE attendance (
  id BIGSERIAL PRIMARY KEY,
  student_id BIGINT NOT NULL,
  classroom_id BIGINT NOT NULL,
  attendance_date DATE NOT NULL,
  present BOOLEAN NOT NULL,
  CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES students(id),
  CONSTRAINT fk_attendance_classroom FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
  CONSTRAINT uk_attendance UNIQUE (student_id, classroom_id, attendance_date)
);
