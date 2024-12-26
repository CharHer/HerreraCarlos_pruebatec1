#Informe técnico

##Introducción

Este documento explica el desarrollo e implementación de un **sistema de gestión de empleados** creado en Java, utilizando JPA (Java Persistence API) para la persistencia de datos. El sistema tiene como objetivo gestionar la información de los empleados de una organización, permitiendo realizar **operaciones CRUD (Crear, Leer, Actualizar, Eliminar)** en una base de datos MySQL.


##Tecnologías Utilizadas

-Lenguaje de Programación: Java (versión 17)

-Base de Datos: MySQL

-Gestor de Dependencias: Maven

-IDE: NetBeans

-Servidor Local: XAMPP


##Arquitectura del Sistema

-Logica: Contiene las clases de la lógica de negocio.

-Persistencia: Incluye las clases para la comunicación con la base de datos.

-Base de datos: Almacena y organiza los datos de los empleados.


##Descripción de las Clases Principales

###Clase Empleado 
Esta clase representa la entidad principal del sistema con los atributos del empleado. Utiliza anotaciones JPA para mapearse con la base de datos y tiene métodos getters, setters y toString.

###Clase ControladoraPersistencia 
Es la intermediaria entre la lógica y la base de datos, realizando funciones como crear, eliminar, actualizar y obtener listas de empleados. Gestiona EntityManager y EntityManagerFactory para operaciones de persistencia.

###Clase EmpleadoJpaController 
Implementa las operaciones CRUD sobre la base de datos mediante JPA. Sus métodos destacados son create para persistir nuevos empleados, edit para actualizar datos de empleados existentes, destroy para eliminar empleados por su ID, y findEmpleado para recuperar empleados según su ID.

###Clase Exception 
Valida datos ingresados para asegurar que el nombre, apellido y cargo no sean vacíosñ el salario sea mayor a 0 y que a fecha de ingreso no sea futura


##Ejecución del Sistema
Para ejecutar el sistema, asegúrese de que XAMPP esté en funcionamiento y que la base de datos "empleados" esté creada. Luego ejecute el proyecto desde NetBeans.
