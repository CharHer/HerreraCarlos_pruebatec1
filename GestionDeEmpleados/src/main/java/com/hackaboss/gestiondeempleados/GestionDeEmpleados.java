
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
        /*List<Empleado> listaEmpleados = controlPersis.traerEmpleados(); //Para llamar la lista de empleados
        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado.toString());
        }*/

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
                    case 1:
                        System.out.println("Ha seleccionado la opcion 1");
                        break;

                    case 2:
                        System.out.println("Ha seleccionado la opcion 2");
                        break;

                    case 3:
                        System.out.println("Ha seleccionado la opcion 3");
                        break;

                    case 4:
                        System.out.println("Ha seleccionado la opcion 4");
                        break;

                    case 5:
                        System.out.println("Ha seleccionado la opcion 5");
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
