
package com.hackaboss.gestiondeempleados;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import logica.Empleado;
import persistencia.ControladoraPersistencia;

public class GestionDeEmpleados {

    public static void main(String[] args) {

        ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        Empleado empleado = new Empleado();

        Scanner teclado = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        System.out.println("\nBienvenido al sistema de Gestión de Empleados\n");

        while (!salir) {

            mostrarMenu();
            System.out.println("Seleccione una opción: ");

            try {

                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1: //agregar
                        System.out.println("Indique los datos del nuevo empleado:");

                        System.out.println("Nombre: ");
                        teclado = new Scanner(System.in);
                        String nombre = teclado.nextLine();

                        System.out.println("Apellido: ");
                        String apellido = teclado.nextLine();

                        System.out.println("Cargo: ");
                        String cargo = teclado.nextLine();

                        System.out.println("Salario: ");
                        teclado = new Scanner(System.in);
                        double salario = teclado.nextDouble();

                        System.out.println("Fecha de ingreso (yyyy-mm-dd");
                        teclado = new Scanner(System.in);
                        String fechaIngresoStr = teclado.nextLine();
                        LocalDate fecha_ingreso = LocalDate.parse(fechaIngresoStr);

                        Empleado nuevoEmpleado = new Empleado();
                        nuevoEmpleado.setNombre(nombre);
                        nuevoEmpleado.setApellido(apellido);
                        nuevoEmpleado.setCargo(cargo);
                        nuevoEmpleado.setSalario(salario);
                        nuevoEmpleado.setFecha_ingreso(fecha_ingreso);

                        controlPersis.crearEmpleado(nuevoEmpleado);
                        System.out.println("Empleado agregado exitosamente");

                        break;

                    case 2: // mostrar

                        System.out.println("Mostrando a todos los empleados: \n");
                        List<Empleado> listaEmpleados = controlPersis.traerEmpleados();
                        for (int i = 0; i < listaEmpleados.size(); i++) {
                            empleado = listaEmpleados.get(i);
                            System.out.println(empleado.toString());
                        }

                        break;

                    case 3: //actualizar
                        System.out.println("Por favor, indique el Id del empleado para actulizar la información\n");
                        int id = teclado.nextInt();
                        empleado = controlPersis.traerEmpleado(id);
                        
                        System.out.println("Nombre: ");
                        teclado = new Scanner(System.in);
                        String nombreActualizado = teclado.nextLine();

                        System.out.println("Apellido: ");
                        String apellidoActualizado = teclado.nextLine();

                        System.out.println("Cargo: ");
                        String cargoActualizado = teclado.nextLine();

                        System.out.println("Salario: ");
                        teclado = new Scanner(System.in);
                        double salarioActualizado = teclado.nextDouble();

                        System.out.println("Fecha de ingreso (yyyy-mm-dd");
                        teclado = new Scanner(System.in);
                        String fechaIngresoActualizado = teclado.nextLine();
                        LocalDate fecha_ingresoActualizada = LocalDate.parse(fechaIngresoActualizado);
                        
                        empleado.setNombre(nombreActualizado);
                        empleado.setApellido(apellidoActualizado);
                        empleado.setCargo(cargoActualizado);
                        empleado.setSalario(salarioActualizado);
                        empleado.setFecha_ingreso(fecha_ingresoActualizada);
                        
                        controlPersis.actualizarEmpleado(empleado);
                        System.out.println("\nEmpleado actualizado exitosamente");

                        break;

                    case 4: //eliminar
                        System.out.println("Por favor, indique el Id del empleado que desea borrar");
                        int idBorrar = teclado.nextInt();
                        empleado = controlPersis.traerEmpleado(idBorrar);
                        
                        controlPersis.eliminarEmpleado(idBorrar);
                        System.out.println("Empleado borrado exitosamente");
                        break;

                    case 5: //buscar
                        System.out.println("Para realizar la busqueda indique el cargo: ");
                        teclado.nextLine();
                        String cargoBuscar = teclado.nextLine();

                        List<Empleado> empleadosEncontrados = controlPersis.traerEmpleadosCargo(cargoBuscar);
                        if (empleadosEncontrados.isEmpty()) {
                            System.out.println("No se encontraron empleados con el cargo: " + cargoBuscar);
                        } else {
                            System.out.println("Empleados con el cargo '" + cargoBuscar + "':");
                            for (Empleado emp : empleadosEncontrados) {
                                System.out.println(emp);
                            }
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

}
