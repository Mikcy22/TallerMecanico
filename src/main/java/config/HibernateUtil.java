package config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    // Bloque estático para inicializar la SessionFactory solo una vez.
    static {
        try {
            // Se configura Hibernate con el archivo de configuración hibernate.cfg.xml.
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(models.Cliente.class)
                    .addAnnotatedClass(models.Coche.class)
                    .addAnnotatedClass(models.Reparacion.class)
                    .addAnnotatedClass(models.Empleado.class)
                    .addAnnotatedClass(models.Usuario.class)
                    .addAnnotatedClass(models.Venta.class)
                    .buildSessionFactory();
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error al crear la SessionFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // Método para obtener una nueva sesión
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Método para cerrar la SessionFactory cuando se termina
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
