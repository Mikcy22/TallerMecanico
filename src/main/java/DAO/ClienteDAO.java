package DAO;

import models.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;

import java.util.List;

public class ClienteDAO {

    public void save(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cliente getById(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Cliente", Cliente.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(int clienteId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            // Buscar el cliente por su ID
            Cliente cliente = session.get(Cliente.class, clienteId);
            if (cliente != null) {
                // Eliminar el cliente si existe
                session.delete(cliente);
                System.out.println("Cliente eliminado: " + cliente.getId());
            } else {
                System.out.println("Cliente no encontrado con ID: " + clienteId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
