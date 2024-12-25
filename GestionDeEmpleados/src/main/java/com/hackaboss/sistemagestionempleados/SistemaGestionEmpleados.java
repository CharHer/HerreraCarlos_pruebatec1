package com.hackaboss.sistemagestionempleados;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import static logica.Exception.validarApellido;
import static logica.Exception.validarCargo;
import static logica.Exception.validarFecha;
import static logica.Exception.validarNombre;
import static logica.Exception.validarSalario;
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
                    case 1:
                        System.out.println("\nIngrese los datos del nuevo empleado: ");
                        Empleado nuevoEmpleado = ingresarDatos();

                        controlPersis.crearEmpleado(nuevoEmpleado);
                        System.out.println("\nEmpleado agregado exitosamente");

                        break;

                    case 2:
                        System.out.println("\nMostrando a todos los empleados: ");
                        List<Empleado> listaEmpleados = controlPersis.traerEmpleados();

                        for (Empleado emp : listaEmpleados) {
                            System.out.println(emp.toString());
                        }

                        break;

                    case 3:
                        System.out.println("\nPor favor, indique el Id del empleado para actulizar la información: ");
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

                    case 4:
                        System.out.println("\nPor favor, indique el Id del empleado que desea borrar");
                        int idBorrar = teclado.nextInt();
                        teclado.nextLine();

                        Empleado empleadoABorrar = controlPersis.traerEmpleado(idBorrar);

                        if (empleadoABorrar != null) {

                            controlPersis.borrarEmpleado(idBorrar);
                            System.out.println("\nEmpleado borrado exitosamente.");
                        } else {

                            System.out.println("\nNo se encontró un empleado con el ID proporcionado");
                        }

                        break;

                    case 5:
                        System.out.println("\nPara realizar la búsqueda indique el cargo:");
                        String cargoBuscar = teclado.nextLine().trim();

                        List<Empleado> empleadosEncontrados = controlPersis.traerEmpleadosCargo(cargoBuscar);

                        if (empleadosEncontrados != null && !empleadosEncontrados.isEmpty()) {
                            System.out.println("\nEmpleados con el cargo " + cargoBuscar + ":");
                            for (Empleado emp : empleadosEncontrados) {
                                System.out.println(emp);
                            }
                        } else {
                            System.out.println("\nNo se encontraron empleados con el cargo '" + cargoBuscar + "'.");
                        }
                        break;

                    case 6:
                        salir = true;

                        break;

                    default:
                        System.out.println("\nIngrese solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor, ingrese un número válido");
                teclado.next();
            }
        }

        teclado.close();

    }

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

    public static Empleado ingresarDatos() {
        Empleado empleado = new Empleado();

        String nom = null;
        boolean nombreValido = false;

        while (nombreValido != true) {

            try {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine().trim();
                validarNombre(nombre);
                nombreValido = true;
                empleado.setNombre(nombre);

            } catch (IllegalArgumentException e) {
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

            } catch (IllegalArgumentException e) {
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

            } catch (IllegalArgumentException e) {
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
            } catch (IllegalArgumentException e) {
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
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use el formato yyyy-mm-dd.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return empleado;

    }

}
