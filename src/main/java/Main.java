import DAO.*;
import models.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


//subida 15-01-2025 21:46
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        CocheDAO cocheDAO = new CocheDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        ReparacionDAO reparacionDAO = new ReparacionDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        VentaDAO ventaDAO = new VentaDAO();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Clientes");//funciona
            System.out.println("2. Gestionar Coches");
            System.out.println("3. Gestionar Empleados");
            System.out.println("4. Gestionar Reparaciones");
            System.out.println("5. Gestionar Ventas");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    gestionarClientes(clienteDAO);
                    break;
                case 2:
                    gestionarCoches(cocheDAO,ventaDAO);
                    break;
                case 3:
                    gestionarEmpleados(empleadoDAO);
                    break;
                case 4:
                    gestionarReparaciones(reparacionDAO, cocheDAO, empleadoDAO);
                    break;
                case 5:
                    gestionarVentas(ventaDAO, clienteDAO, cocheDAO, empleadoDAO);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }

    private static void gestionarClientes(ClienteDAO clienteDAO) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Crear nuevo cliente");
            System.out.println("2. Ver cliente por ID");
            System.out.println("3. Ver todos los clientes");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = new Cliente();
                    System.out.print("Nombre del cliente: ");
                    nuevoCliente.setNombre(scanner.nextLine());
                    System.out.print("Email del cliente: ");
                    nuevoCliente.setEmail(scanner.nextLine());
                    System.out.print("Teléfono del cliente: ");
                    nuevoCliente.setTelefono(scanner.nextLine());

                    clienteDAO.save(nuevoCliente);
                    System.out.println("Cliente creado con ID: " + nuevoCliente.getId());
                    break;

                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Cliente cliente = clienteDAO.getById(id);
                    if (cliente != null) {
                        System.out.println("Cliente encontrado:");
                        System.out.println("Nombre: " + cliente.getNombre());
                        System.out.println("Email: " + cliente.getEmail());
                        System.out.println("Teléfono: " + cliente.getTelefono());
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;

                case 3:
                    List<Cliente> clientes = clienteDAO.getAll();
                    System.out.println("\nLista de clientes:");
                    for (Cliente c : clientes) {
                        System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() +
                                " | Email: " + c.getEmail());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del cliente a actualizar: ");
                    int idActualizar = Integer.parseInt(scanner.nextLine());
                    Cliente clienteActualizar = clienteDAO.getById(idActualizar);
                    if (clienteActualizar != null) {
                        System.out.print("Nuevo nombre (" + clienteActualizar.getNombre() + "): ");
                        String nombre = scanner.nextLine();
                        if (!nombre.isEmpty()) {
                            clienteActualizar.setNombre(nombre);
                        }

                        System.out.print("Nuevo email (" + clienteActualizar.getEmail() + "): ");
                        String email = scanner.nextLine();
                        if (!email.isEmpty()) {
                            clienteActualizar.setEmail(email);
                        }

                        System.out.print("Nuevo teléfono (" + clienteActualizar.getTelefono() + "): ");
                        String telefono = scanner.nextLine();
                        if (!telefono.isEmpty()) {
                            clienteActualizar.setTelefono(telefono);
                        }

                        clienteDAO.update(clienteActualizar);
                        System.out.println("Cliente actualizado correctamente");
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    clienteDAO.delete(idEliminar);
                    System.out.println("Cliente eliminado");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarCoches(CocheDAO cocheDAO, VentaDAO ventaDAO) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GESTIÓN DE COCHES ===");
            System.out.println("1. Crear nuevo coche");
            System.out.println("2. Ver coche por ID");
            System.out.println("3. Ver todos los coches");
            System.out.println("4. Actualizar coche");
            System.out.println("5. Eliminar coche");
            System.out.println("0. Volver al menú principal");

            try {
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        // Crear nuevo coche
                        Coche nuevoCoche = new Coche();
                        System.out.print("Marca: ");
                        nuevoCoche.setMarca(scanner.nextLine());
                        System.out.print("Modelo: ");
                        nuevoCoche.setModelo(scanner.nextLine());
                        System.out.print("Año: ");
                        nuevoCoche.setAño(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Precio: ");
                        nuevoCoche.setPrecio(Double.parseDouble(scanner.nextLine()));

                        cocheDAO.save(nuevoCoche);
                        System.out.println("Coche creado");

                        break;

                    case 2:
                        // Ver coche por ID
                        System.out.print("Ingrese el ID del coche: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Coche coche = cocheDAO.getById(id);
                        if (coche != null) {
                            System.out.println("Coche encontrado:");
                            System.out.println("Marca: " + coche.getMarca());
                            System.out.println("Modelo: " + coche.getModelo());
                            System.out.println("Año: " + coche.getAño());
                            System.out.println("Precio: " + coche.getPrecio());
                            System.out.println("Ventas asociadas: " + coche.getVentas());
                        } else {
                            System.out.println("Coche no encontrado");
                        }
                        break;

                    case 3:
                        // Ver todos los coches
                        List<Coche> coches = cocheDAO.getAll();
                        System.out.println("\nLista de coches:");
                        for (Coche c : coches) {
                            System.out.println("ID: " + c.getId() + " | Marca: " + c.getMarca() +
                                    " | Modelo: " + c.getModelo() + " | Año: " + c.getAño() +
                                    " | Precio: " + c.getPrecio());
                        }
                        break;

                    case 4:
                        // Actualizar coche
                        System.out.print("Ingrese el ID del coche a actualizar: ");
                        int idActualizar = Integer.parseInt(scanner.nextLine());
                        Coche cocheActualizar = cocheDAO.getById(idActualizar);
                        if (cocheActualizar != null) {
                            System.out.print("Nueva marca (" + cocheActualizar.getMarca() + "): ");
                            String marca = scanner.nextLine();
                            if (!marca.isEmpty()) cocheActualizar.setMarca(marca);

                            System.out.print("Nuevo modelo (" + cocheActualizar.getModelo() + "): ");
                            String modelo = scanner.nextLine();
                            if (!modelo.isEmpty()) cocheActualizar.setModelo(modelo);

                            System.out.print("Nuevo año (" + cocheActualizar.getAño() + "): ");
                            String ano = scanner.nextLine();
                            if (!ano.isEmpty()) cocheActualizar.setAño(Integer.parseInt(ano));

                            System.out.print("Nuevo precio (" + cocheActualizar.getPrecio() + "): ");
                            String precio = scanner.nextLine();
                            if (!precio.isEmpty()) cocheActualizar.setPrecio(Double.parseDouble(precio));

                            cocheDAO.update(cocheActualizar);
                            System.out.println("Coche actualizado correctamente");
                        } else {
                            System.out.println("Coche no encontrado");
                        }
                        break;

                    case 5:
                        // Eliminar coche
                        System.out.print("Ingrese el ID del coche a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        Coche cocheEliminar = cocheDAO.getById(idEliminar);
                        if (cocheEliminar != null) {
                            cocheDAO.delete(cocheEliminar);
                            System.out.println("Coche eliminado");
                        } else {
                            System.out.println("Coche no encontrado");
                        }
                        break;

                    case 0:
                        // Salir
                        return;

                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    private static void gestionarEmpleados(EmpleadoDAO empleadoDAO) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Crear nuevo empleado");
            System.out.println("2. Ver empleado por ID");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("0. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    try {
                        // Crear empleado
                        Empleado nuevoEmpleado = new Empleado();
                        System.out.print("Nombre del empleado: ");
                        nuevoEmpleado.setNombre(scanner.nextLine());
                        System.out.print("Puesto: ");
                        nuevoEmpleado.setPuesto(scanner.nextLine());
                        System.out.print("Salario: ");
                        nuevoEmpleado.setSalario(Double.parseDouble(scanner.nextLine()));

                        // Guardar empleado (solo empleado, sin necesidad de usuario)
                        empleadoDAO.save(nuevoEmpleado);

                        System.out.println("Empleado creado con ID: " + nuevoEmpleado.getId());
                    } catch (Exception e) {
                        System.out.println("Error al crear empleado: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID del empleado: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Empleado empleado = empleadoDAO.getById(id);
                    if (empleado != null) {
                        System.out.println("Empleado encontrado:");
                        System.out.println("Nombre: " + empleado.getNombre());
                        System.out.println("Puesto: " + empleado.getPuesto());
                        System.out.println("Salario: " + empleado.getSalario());
                    } else {
                        System.out.println("Empleado no encontrado");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del empleado a actualizar: ");
                    int idActualizar = Integer.parseInt(scanner.nextLine());
                    Empleado empleadoActualizar = empleadoDAO.getById(idActualizar);
                    if (empleadoActualizar != null) {
                        System.out.print("Nuevo nombre (" + empleadoActualizar.getNombre() + "): ");
                        String nombre = scanner.nextLine();
                        if (!nombre.isEmpty()) {
                            empleadoActualizar.setNombre(nombre);
                        }

                        System.out.print("Nuevo puesto (" + empleadoActualizar.getPuesto() + "): ");
                        String puesto = scanner.nextLine();
                        if (!puesto.isEmpty()) {
                            empleadoActualizar.setPuesto(puesto);
                        }

                        System.out.print("Nuevo salario (" + empleadoActualizar.getSalario() + "): ");
                        String salario = scanner.nextLine();
                        if (!salario.isEmpty()) {
                            empleadoActualizar.setSalario(Double.parseDouble(salario));
                        }

                        empleadoDAO.update(empleadoActualizar);
                        System.out.println("Empleado actualizado correctamente");

                    } else {
                        System.out.println("Empleado no encontrado");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del empleado a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    Empleado empEliminar = empleadoDAO.getById(idEliminar);
                    if (empEliminar != null) {
                        empleadoDAO.delete(empEliminar);
                        System.out.println("Empleado eliminado");
                    } else {
                        System.out.println("Empleado no encontrado");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarReparaciones(ReparacionDAO reparacionDAO, CocheDAO cocheDAO, EmpleadoDAO empleadoDAO) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE REPARACIONES ===");
            System.out.println("1. Crear nueva reparación");
            System.out.println("2. Ver reparación por ID");
            System.out.println("3. Ver todas las reparaciones");
            System.out.println("4. Actualizar reparación");
            System.out.println("5. Eliminar reparación");
            System.out.println("0. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    Reparacion nuevaReparacion = new Reparacion();

                    // Seleccionar coche
                    System.out.print("ID del coche a reparar: ");
                    int cocheId = Integer.parseInt(scanner.nextLine());
                    Coche coche = cocheDAO.getById(cocheId);
                    if (coche == null) {
                        System.out.println("Coche no encontrado");
                        break;
                    }
                    nuevaReparacion.setCoche(coche);

                    // Seleccionar empleado
                    System.out.print("ID del empleado (mecánico): ");
                    int empleadoId = Integer.parseInt(scanner.nextLine());
                    Empleado empleado = empleadoDAO.getById(empleadoId);
                    if (empleado == null) {
                        System.out.println("Empleado no encontrado");
                        break;
                    }
                    nuevaReparacion.setEmpleado(empleado);

                    System.out.print("Descripción de la reparación: ");
                    nuevaReparacion.setDescripcion(scanner.nextLine());
                    System.out.print("Costo de la reparación: ");
                    nuevaReparacion.setCosto(Double.parseDouble(scanner.nextLine()));
                    nuevaReparacion.setFecha(new Date());

                    reparacionDAO.save(nuevaReparacion);
                    System.out.println("Reparación creada con ID: " + nuevaReparacion.getId());
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la reparación: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Reparacion reparacion = reparacionDAO.getById(id);
                    if (reparacion != null) {
                        System.out.println("Reparación encontrada:");
                        System.out.println("Fecha: " + reparacion.getFecha());
                        System.out.println("Descripción: " + reparacion.getDescripcion());
                        System.out.println("Costo: " + reparacion.getCosto());
                        System.out.println("Coche: " + reparacion.getCoche().getMarca() + " " +
                                reparacion.getCoche().getModelo());
                        System.out.println("Mecánico: " + reparacion.getEmpleado().getNombre());
                    } else {
                        System.out.println("Reparación no encontrada");
                    }
                    break;

                case 3:
                    List<Reparacion> reparaciones = reparacionDAO.getAll();
                    System.out.println("\nLista de reparaciones:");
                    for (Reparacion r : reparaciones) {
                        System.out.println("ID: " + r.getId() +
                                " | Fecha: " + r.getFecha() +
                                " | Coche: " + r.getCoche().getMarca() + " " + r.getCoche().getModelo() +
                                " | Costo: " + r.getCosto());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la reparación a actualizar: ");
                    int idActualizar = Integer.parseInt(scanner.nextLine());
                    Reparacion repActualizar = reparacionDAO.getById(idActualizar);
                    if (repActualizar != null) {
                        System.out.print("Nueva descripción (" + repActualizar.getDescripcion() + "): ");
                        String descripcion = scanner.nextLine();
                        if (!descripcion.isEmpty()) {
                            repActualizar.setDescripcion(descripcion);
                        }

                        System.out.print("Nuevo costo (" + repActualizar.getCosto() + "): ");
                        String costo = scanner.nextLine();
                        if (!costo.isEmpty()) {
                            repActualizar.setCosto(Double.parseDouble(costo));
                        }

                        reparacionDAO.update(repActualizar);
                        System.out.println("Reparación actualizada correctamente");
                    } else {
                        System.out.println("Reparación no encontrada");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ID de la reparación a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    Reparacion repEliminar = reparacionDAO.getById(idEliminar);
                    if (repEliminar != null) {
                        reparacionDAO.delete(repEliminar);
                        System.out.println("Reparación eliminada");
                    } else {
                        System.out.println("Reparación no encontrada");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarVentas(VentaDAO ventaDAO, ClienteDAO clienteDAO, CocheDAO cocheDAO, EmpleadoDAO empleadoDAO) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GESTIÓN DE VENTAS ===");
            System.out.println("1. Registrar nueva venta");
            System.out.println("2. Ver venta por ID");
            System.out.println("3. Ver todas las ventas");
            System.out.println("4. Actualizar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver al menú principal");

            try {
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        Venta nuevaVenta = new Venta();

                        // Seleccionar cliente
                        System.out.print("ID del cliente: ");
                        int clienteId = Integer.parseInt(scanner.nextLine());
                        Cliente cliente = clienteDAO.getById(clienteId);
                        if (cliente == null) {
                            System.out.println("Cliente no encontrado");
                            break;
                        }
                        nuevaVenta.setCliente(cliente);

                        // Seleccionar coche
                        System.out.print("ID del coche: ");
                        int cocheId = Integer.parseInt(scanner.nextLine());
                        Coche coche = cocheDAO.getById(cocheId);
                        if (coche == null) {
                            System.out.println("Coche no encontrado");
                            break;
                        }
                        nuevaVenta.setCoches(List.of(coche));

                        // Seleccionar empleado
                        System.out.print("ID del empleado (vendedor): ");
                        int empleadoId = Integer.parseInt(scanner.nextLine());
                        Empleado empleado = empleadoDAO.getById(empleadoId);
                        if (empleado == null) {
                            System.out.println("Empleado no encontrado");
                            break;
                        }
                        nuevaVenta.setEmpleado(empleado);

                        System.out.print("Precio final de venta: ");
                        nuevaVenta.setMonto(Double.parseDouble(scanner.nextLine()));
                        nuevaVenta.setFecha(new Date());

                        ventaDAO.save(nuevaVenta);
                        System.out.println("Venta registrada con ID: " + nuevaVenta.getId());
                        break;

                    case 2:
                        System.out.print("Ingrese el ID de la venta: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Venta venta = ventaDAO.getById(id);
                        if (venta != null) {
                            System.out.println("Venta encontrada:");
                            System.out.println("Fecha: " + venta.getFecha());
                            System.out.println("Cliente: " + venta.getCliente().getNombre());
                            System.out.println("Coche: " + venta.getCoches());
                            System.out.println("Vendedor: " + venta.getEmpleado().getNombre());
                            System.out.println("Precio monto: " + venta.getMonto());
                        } else {
                            System.out.println("Venta no encontrada");
                        }
                        break;

                    case 3:
                        List<Venta> ventas = ventaDAO.getAll();
                        System.out.println("\nLista de ventas:");
                        for (Venta v : ventas) {
                            System.out.println("ID: " + v.getId() +
                                    " | Fecha: " + v.getFecha() +
                                    " | Cliente: " + v.getCliente().getNombre() +
                                    " | Coche: " + v.getCoches() +
                                    " | Precio: " + v.getMonto());
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el ID de la venta a actualizar: ");
                        int idActualizar = Integer.parseInt(scanner.nextLine());
                        Venta ventaActualizar = ventaDAO.getById(idActualizar);
                        if (ventaActualizar != null) {
                            System.out.print("Nuevo precio final (" + ventaActualizar.getMonto() + "): ");
                            String precioFinal = scanner.nextLine();
                            if (!precioFinal.isEmpty()) {
                                ventaActualizar.setMonto(Double.parseDouble(precioFinal));
                            }

                            ventaDAO.update(ventaActualizar);
                            System.out.println("Venta actualizada correctamente");
                        } else {
                            System.out.println("Venta no encontrada");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese el ID de la venta a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        Venta ventaEliminar = ventaDAO.getById(idEliminar);
                        if (ventaEliminar != null) {
                            ventaDAO.delete(ventaEliminar);
                            System.out.println("Venta eliminada");
                        } else {
                            System.out.println("Venta no encontrada");
                        }
                        break;

                    case 0:
                        return;

                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
}