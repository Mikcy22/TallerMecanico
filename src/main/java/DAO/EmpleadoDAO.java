package DAO;

import models.Empleado;
import models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;

public class EmpleadoDAO {

    public void save(Empleado empleado) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Verificar si el Usuario ya está persistido
            Usuario usuario = empleado.getUsuario();
            if (usuario != null && usuario.getId() == 0) {
                session.save(usuario); // Guardar el Usuario si aún no existe
            }

            session.save(empleado); // Guardar el Empleado
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Empleado getById(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Empleado.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Empleado empleado) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Actualizar primero el Usuario relacionado
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                session.update(usuario);
            }

            session.update(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Empleado empleado) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Eliminar primero el Usuario relacionado si es necesario
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                session.delete(usuario);
            }

            session.delete(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
