import config.HibernateUtil;
import org.hibernate.Session;



/*
public class Main {
    public static void main(String[] args) {

        // 1. Obtener la fábrica de sesiones de Hibernate
        Session session = HibernateUtil.getSession();

    }
}
*/


import DAO.UsuarioDAO;
import models.Usuario;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Session session = HibernateUtil.getSession();

        // Crear un nuevo usuario
       /* Usuario usuario = new Usuario();

        usuario.setUsername("admin");
        usuario.setPassword("1234");

        usuarioDAO.create(usuario);*/

        // Leer un usuario por ID
        //Usuario retrieved = usuarioDAO.read(2);
        //System.out.println("Un usuario leído: " + retrieved.getUsername());

        // Leer todos los usuarios
        //usuarioDAO.readAll().forEach(u -> System.out.println("Usuario: " + u.getUsername()));

        // Actualizar un usuario
        //retrieved.setPassword("admin1234");
        //usuarioDAO.update(retrieved);

        // Eliminar un usuario
        //usuarioDAO.delete(retrieved.getId());

        // Cerrar la SessionFactory al finalizar
        HibernateUtil.closeSessionFactory();
    }
}
