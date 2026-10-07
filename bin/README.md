# AlDía Poli

Aplicación web para que estudiantes gestionen semestres, materias, actividades evaluativas, calificaciones, promedios, metas académicas y notificaciones internas.

## Stack
- Java 21
- Spring Boot 3.4.5
- Spring MVC + Thymeleaf
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL 17
- Bootstrap 5
- Maven

## Arquitectura y patrón
- **MVC**: controladores web, servicios/modelo de dominio y vistas Thymeleaf.
- **Observer**: `ActividadEvaluativa` actúa como `ISubject`. Al registrar o modificar una calificación se notifica a `RendimientoObserver`, que recalcula el promedio actual y genera una notificación interna.

## Funciones incluidas
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
DB_PASSWORD=postgres
PORT=8080
```

Se recomienda configurar `DB_PASSWORD` en el sistema y no subir credenciales reales a GitHub.

### PowerShell
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/aldia_poli"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="TU_CLAVE"
```

## 3. Ejecutar
Con Java 21 y Maven instalados:

```bash
mvn spring-boot:run
```

Abrir `http://localhost:8080`.

## 4. Compilar JAR
```bash
mvn clean package
java -jar target/aldia-poli-0.0.1-SNAPSHOT.jar
```

## 5. GitHub
```bash
git init
git add .
git commit -m "Proyecto inicial AlDia Poli"
git branch -M main
git remote add origin URL_DE_TU_REPOSITORIO
git push -u origin main
```

## Despliegue sin Docker
Crea una base PostgreSQL administrada y un Web Service Java conectado al repositorio de GitHub.

Variables de entorno en producción:
- `DB_URL` — URL JDBC, por ejemplo `jdbc:postgresql://host:5432/db`
- `DB_USERNAME`
- `DB_PASSWORD`
- `PORT` — normalmente lo asigna la plataforma

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

## Nota de entrega
Antes de presentar, crear al menos:
1. un usuario estudiante,
2. un semestre,
3. dos materias,
4. varias actividades cuyos porcentajes sumen 100%,
5. algunas notas y al menos una actividad pendiente.

Eso permite demostrar login, CRUD, promedios, riesgo académico, meta académica, calendario y Observer/notificaciones en una sola exposición.
