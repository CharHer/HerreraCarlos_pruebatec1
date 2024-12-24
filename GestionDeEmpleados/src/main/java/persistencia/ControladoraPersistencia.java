package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) {

        empleadoJpa.create(empleado);

    }

    public void borrarEmpleado(int id) {

        try {
            empleadoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado traerEmpleado(int id) {
        return empleadoJpa.findEmpleado(id);
    }

    public List<Empleado> traerEmpleadosCargo(String cargo) {
        String jpql = "SELECT e FROM Empleado e WHERE LOWER(e.cargo) = LOWER(:cargo)";
        TypedQuery<Empleado> query = entityManager.createQuery(jpql, Empleado.class);
        query.setParameter("cargo", cargo);
        return query.getResultList();
    }

    public List<Empleado> traerEmpleados() {
        return empleadoJpa.findEmpleadoEntities();
    }

    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJpa.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Para crear instancias de EntityManager, que se utilizan para interactuar con la base de datos 
    (realizar operaciones CRUD sobre las entidades).*/
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public ControladoraPersistencia() {

        emf = Persistence.createEntityManagerFactory("empleadosPU");
        entityManager = emf.createEntityManager();
    }

    public void cerrar() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

}
