# **AlDía Poli**

## **¿Qué es AlDía Poli?**
**AlDía Poli** es un sistema diseñado para que los estudiantes gestionen y consulten su información académica (materias, actividades evaluativas, notas, promedios, semestres y notificaciones) de una forma más eficiente y en un solo lugar.

---

## **Problema identificado**
Los estudiantes necesitan consultar constantemente su rendimiento académico y calcular cuánto deben obtener en las actividades restantes para alcanzar una nota determinada. Si este proceso se realiza manualmente, pueden presentarse errores en los cálculos, pérdida de información y falta de seguimiento oportuno. Además, como estudiantes hemos identificado que de forma digital no existe un sitio centralizado para realizar todas estas operaciones. Así es como nace **AlDía Poli**.

---

## **Visión funcional del proyecto**
El proyecto busca convertirse en una plataforma académica que centralice la información del estudiante y automatice:

* **Registro y consulta** de materias.
* **Registro de actividades** y porcentajes.
* **Cálculo automático** de notas parciales y definitivas.
* **Proyección** de notas necesarias.
* **Seguimiento** del promedio semestral.
* **Notificaciones** sobre actividades, entregas o cambios en las calificaciones.
* **Consulta histórica** por semestre.

---

## **Propuesta del proyecto**
Desarrollar una aplicación web denominada **AlDía Poli**, orientada a estudiantes del Politécnico, que permita administrar su información académica de forma sencilla, segura y centralizada.

La plataforma debe permitir que cada estudiante registre sus materias y actividades evaluativas, ingrese sus calificaciones y consulte automáticamente su desempeño. El sistema calculará el promedio de cada materia, el promedio general del semestre y las notas requeridas en las actividades pendientes.

Además, se propone implementar un sistema de notificaciones que informe sobre fechas de entrega, actividades próximas, cambios en las notas y situaciones académicas relevantes.

---

## **Módulos principales**

### 1. Autenticación
* Registro de usuarios.
* Inicio y cierre de sesión.
* Validación de datos.
* Recuperación y cambio de contraseña.

### 2. Gestión académica
* Creación y consulta de semestres.
* Registro, edición y eliminación de materias.
* Asociación de actividades evaluativas a cada materia.

### 3. Gestión de notas
* Registro y modificación de calificaciones.
* Configuración de porcentajes.
* Cálculo de notas parciales y definitivas.
* Proyección de notas restantes.

### 4. Promedios
* Promedio por materia.
* Promedio semestral.
* Consulta del rendimiento histórico.

### 5. Notificaciones
* Recordatorios de actividades.
* Alertas sobre fechas de entrega.
* Avisos relacionados con cambios en las calificaciones.

---

## **Objetivos del proyecto**

### Objetivo general
Desarrollar una aplicación web que permita a los estudiantes del Politécnico gestionar, consultar y analizar su información académica, automatizando el cálculo de notas y promedios, y proporcionando notificaciones oportunas sobre sus actividades y compromisos académicos.

### Objetivos específicos
1. **Autenticación y Seguridad:** Diseñar un módulo de autenticación que permita registrar usuarios y proteger el acceso a la información académica.
2. **Estructura Académica:** Permitir el registro, edición, consulta y eliminación de semestres y materias.
3. **Gestión de Evaluaciones:** Implementar la gestión de actividades evaluativas asociadas a cada materia, incluyendo tipo, porcentaje, fecha y calificación.
4. **Cálculo Automático:** Automatizar el cálculo de notas parciales, notas definitivas y promedios por materia.
5. **Promedio Semestral:** Calcular el promedio general de cada semestre a partir de las materias registradas y sus respectivos créditos.
6. **Proyección de Notas:** Proporcionar una herramienta que permita determinar las calificaciones necesarias en las actividades pendientes para alcanzar una nota objetivo.
7. **Sistema de Alertas:** Implementar un sistema de notificaciones para recordar fechas de entrega y comunicar cambios importantes en el rendimiento académico.
8. **Histórico Académico:** Permitir la consulta histórica del desempeño académico organizado por semestres.
9. **Arquitectura:** Aplicar una arquitectura MVC que facilite la separación entre la interfaz, la lógica de negocio y la gestión de datos.
10. **Patrones de Diseño:** Utilizar el patrón Observer para actualizar automáticamente los promedios y notificaciones cuando se modifique una calificación.
11. **Validación:** Validar la información ingresada para evitar porcentajes inválidos, notas fuera del rango permitido y datos incompletos.
12. **Diseño Responsivo:** Diseñar una interfaz clara y adaptable que permita consultar la información académica desde computadores y dispositivos móviles.

## **Stack de Tecnologías**
- Java 21
- Spring Boot 3.4.5
- Thymeleaf
- Spring Data JPA 
- PostgreSQL 17
- Bootstrap 5
- Maven

## Arquitectura y patrón
- **MVC**: La arquitectura del proyecto se divide en:
  - controladores web 
  - Servicios/modelo de dominio  
  - vistas Thymeleaf.
- **Observer**: `ActividadEvaluativa` actúa como `ISubject`. Al registrar o modificar una calificación se notifica a `RendimientoObserver`, que recalcula el promedio actual y genera una notificación interna.

## **Funciones incluidas**
- Registro e inicio/cierre de sesión.
- CRUD básico de semestres y materias.
- Actividades tipo Parcial, Quiz y Taller.
- Porcentajes con validación de suma máxima del 100%.
- Registro/modificación de notas entre 0.0 y 5.0.
- Promedio actual y acumulado definitivo.
- Promedio ponderado por créditos disponible en servicio.
- Meta académica: nota necesaria en el porcentaje restante.
- Dashboard con materias en riesgo (< 3.0).
- Próximas actividades.
- Notificaciones internas por cambio de nota.
- Recordatorios automáticos internos a 24 y 48 horas.
- Diseño responsive con Bootstrap.
## **Pasos de Instalacion**
## 1. Crear la base de datos
En PostgreSQL 17:

```sql
CREATE DATABASE aldia_poli;
```

Las tablas se crean automáticamente con JPA (`ddl-auto=update`).

## 2. Variables de entorno
Valores por defecto locales:

```text
DB_URL=jdbc:postgresql://localhost:5432/aldia_poli
DB_USERNAME=postgres
DB_PASSWORD="Contraseña Personalizada"
PORT=8080
```

Se recomienda configurar `DB_PASSWORD` en el sistema y no subir credenciales reales a GitHub.


## 3.Clonar Repositorio

```bash
git clone https://github.com/bobpati/aldia-poli
```
## 4. Ejecutar
Con Java 21 y Maven instalados:

```bash
mvn clean compile
mvn spring-boot:run
```

Abrir `http://localhost:8080` o el puerto definido.





Build command:
```bash
mvn clean package -DskipTests
```

Start command:
```bash
java -jar target/aldia-poli-0.0.1-SNAPSHOT.jar
```

## Estructura principal
```text
src/main/java/com/aldia/poli/
├── config/
├── controller/
├── dto/
├── model/
├── observer/
├── repository/
└── service/
```

## Validaciones académicas
- Nota: 0.0 a 5.0.
- Créditos: mayores que 0.
- Cada actividad: porcentaje > 0 y <= 100.
- Suma de porcentajes por materia: no puede superar 100%.
- La fecha final de un semestre no puede ser anterior a la inicial.


## **Conclusión**

**AlDía Poli** representa una solución integral y moderna a la dispersión de información y el cálculo manual que enfrentan los estudiantes en su día a día académico. Al centralizar la gestión de materias, proyectar las notas requeridas y automatizar las alertas mediante patrones de diseño robustos como **MVC** y **Observer**, el proyecto no solo optimiza el seguimiento del rendimiento académico, sino que ofrece una herramienta intuitiva, escalable y accesible desde cualquier dispositivo.