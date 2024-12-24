package com.hackaboss.sistemagestionempleados;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;

public class SistemaGestionEmpleados {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        ControladoraPersistencia controlPersis = new ControladoraPersistencia();

        int opcion;
        boolean salir = false;

        System.out.println("\nBienvenido al sistema de Gestión de Empleados\n");

        while (!salir) {

            mostrarMenu();
            System.out.println("Seleccione una opción: ");
            
            try {

                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1: //agregar

                        System.out.println("Ingrese los datos del nuevo empleado: ");
                        Empleado nuevoEmpleado = ingresarDatos();

                        controlPersis.crearEmpleado(nuevoEmpleado);
                        System.out.println("\nEmpleado agregado exitosamente");

                        break;
                    case 2://mostrar
                        System.out.println("Mostrando a todos los empleados: \n");
                        List<Empleado> listaEmpleados = controlPersis.traerEmpleados();
                        for (Empleado emp : listaEmpleados) {
                            System.out.println(emp.toString());
                        }

                        break;
                    case 3://actualizar
                        System.out.println("Por favor, indique el Id del empleado para actulizar la información: ");
                        int id = teclado.nextInt();
                        teclado.nextLine();

                        Empleado empleado = controlPersis.traerEmpleado(id);
                        Empleado datosActualizados = ingresarDatos();

                        empleado.setNombre(datosActualizados.getNombre());
                        empleado.setApellido(datosActualizados.getApellido());
                        empleado.setCargo(datosActualizados.getCargo());
                        empleado.setSalario(datosActualizados.getSalario());
                        empleado.setFecha_ingreso(datosActualizados.getFecha_ingreso());

                        controlPersis.modificarEmpleado(empleado);
                        System.out.println("\nEmpleado actualizado exitosamente.");

                        break;
                    case 4://eliminar
                        System.out.println("Por favor, indique el Id del empleado que desea borrar");
                        int idBorrar = teclado.nextInt();
                        teclado.nextLine();

                        Empleado empleadoABorrar = controlPersis.traerEmpleado(idBorrar);

                        if (empleadoABorrar != null) {
                            
                            controlPersis.borrarEmpleado(idBorrar);
                            System.out.println("Empleado borrado exitosamente.");
                        } 
                        else {

                            System.out.println("No se encontró un empleado con el ID proporcionado");
                        }

                        break;
                    case 5://buscar por cargo
                        System.out.println("Para realizar la búsqueda indique el cargo:");
                        String cargoBuscar = teclado.nextLine().trim();

                        List<Empleado> empleadosEncontrados = controlPersis.traerEmpleadosCargo(cargoBuscar);

                        if (empleadosEncontrados != null && !empleadosEncontrados.isEmpty()) {
                            System.out.println("Empleados con el cargo " + cargoBuscar + ":");
                            for (Empleado emp : empleadosEncontrados) {
                                System.out.println(emp);
                            }
                        } 
                        else {
                            System.out.println("No se encontraron empleados con el cargo '" + cargoBuscar + "'.");
                        }
                        break;
                    case 6:
                        salir = true;

                        break;

                    default:
                        System.out.println("\nIngrese solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido");
                teclado.next();
            }
        }

        teclado.close();

    }

    //METODOS
    private static void mostrarMenu() {
        
        
        System.out.println(" ____________________________________");
        System.out.println("|     ***    MENÚ PRINCIPAL   ***    |");
        System.out.println("|____________________________________|");
        System.out.println("|       1. Agregar Empleado          |");
        System.out.println("|       2. Mostrar Empleados         |");
        System.out.println("|       3. Actualizar Empleado       |");
        System.out.println("|       4. Eliminar Empleado         |");
        System.out.println("|       5. Buscar Empleados          |");
        System.out.println("|       6. Salir                     |");
        System.out.println("|____________________________________|\n");
    }

    private static Empleado ingresarDatos() {
        Empleado empleado = new Empleado();

        String nom = null;
        boolean nombreValido = false;

        while (nombreValido != true) {

            try {
                System.out.print("Nombre: ");
                String  nombre = teclado.nextLine().trim();
                validarNombre(nombre);
                nombreValido = true;
                empleado.setNombre(nombre);

            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String ape = null;
        boolean apellidoValido = false;

        while (apellidoValido != true) {

            try {
                System.out.print("Apellido: ");
                String apellido = teclado.nextLine();
                validarApellido(apellido);
                apellidoValido = true;
                empleado.setApellido(apellido);

            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
            }
        }

        String carg = null;
        boolean cargoValido = false;

        while (cargoValido != true) {

            try {
                System.out.print("Cargo: ");
                String cargo = teclado.nextLine();
                validarCargo(cargo);
                cargoValido = true;
                empleado.setCargo(cargo);

            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
            }
        }

        double sal = 0.0;
        boolean salarioValido = false;

        while (salarioValido != true) {
            try {
                System.out.print("Salario: ");
                double salario = teclado.nextDouble();
                teclado.nextLine();
                validarSalario(salario);
                salarioValido = true;
                empleado.setSalario(salario);
            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
            }
        }

        boolean fechaValida = false;

        while (fechaValida != true) {
            try {
                System.out.print("Fecha de ingreso (yyyy-mm-dd): ");
                String fechaIngreso = teclado.nextLine().trim();
                LocalDate fecha = LocalDate.parse(fechaIngreso);
                validarFecha(fecha);
                fechaValida = true;
                empleado.setFecha_ingreso(fecha);
            } 
            catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use el formato yyyy-mm-dd.");
            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return empleado;

    }

    private static void validarNombre(String nom) {
        if (nom == null || nom.trim().isEmpty()) { 
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
    }

    private static void validarApellido(String ape) {
        if (ape == null || ape.trim().isEmpty()) { 
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
    }

    private static void validarCargo(String carg) {
        if (carg == null || carg.trim().isEmpty()) { 
            throw new IllegalArgumentException("El cargo no puede estar vacio");
        }
    }

    private static void validarSalario(double sal) {
        if (sal <= 0) {
            throw new IllegalArgumentException("El saldo debe ser mayor a 0");
        }
    }

    public static void validarFecha(LocalDate fechain) {
        if (fechain.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser en el futuro");
        }
    }

}
