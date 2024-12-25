
package logica;

import java.time.LocalDate;

public class Exception {

    public static void validarNombre(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
    }

    public static void validarApellido(String ape) {
        if (ape == null || ape.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
    }

    public static void validarCargo(String carg) {
        if (carg == null || carg.trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo no puede estar vacio");
        }
    }

    public static void validarSalario(double sal) {

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
