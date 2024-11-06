package PC_3;

import java.sql.*;
import java.util.Scanner;

public class Menu {

    public void mostrarMenu() {
        System.out.println("\n--------MENU--------");
        System.out.println("1.- Insertar datos");
        System.out.println("2.- Mostrar datos");
        System.out.println("3.- Buscar cliente por Codigo");
        System.out.println("4.- Buscar cliente por RUC");
    }

    public void insertarDatos(String apellidoz, String nombre, int RUC, double credito, String direccion, int telefono) {
        String url = "jdbc:mysql://localhost/Empresa_PERU";
        String username = "Romero";
        String password = "60781212";

        try {
            Connection conexion = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO cliente (apellidos, nombres, RUC, linea_de_credito, direccion, telefono) VALUE (?,?,?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, apellidoz);
            statement.setString(2, nombre);
            statement.setInt(3, RUC);
            statement.setDouble(4, credito);
            statement.setString(5, direccion);
            statement.setInt(6, telefono);

            int usuarioAgregado = statement.executeUpdate();
            if (usuarioAgregado > 0) {
                System.out.println("Datos agregados correctamente.");
            } else {
                System.out.println("Error al insertar datos.");
            }

            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarDatos() {
        String url = "jdbc:mysql://localhost/Empresa_PERU";
        String username = "Romero";
        String password = "60781212";

        try {
            Connection conexion = DriverManager.getConnection(url, username, password);

            Statement statement = conexion.createStatement();

            String sql = "SELECT * FROM cliente";
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("+--------+---------------------+-----------+-----------+----------+-------------------------------+-----------+");
            System.out.printf("| %-7s| %-20s| %-10s| %-10s| %-9s| %-30s| %-10s|%n", "Codigo", "Apellidos", "Nombres", "RUC", "Credito", "Direccion", "Telefono");
            System.out.println("+--------+---------------------+-----------+-----------+----------+-------------------------------+-----------+");
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String apellidos = rs.getString("apellidos");
                String nombres = rs.getString("nombres");
                int RUC = rs.getInt("RUC");
                double credito = rs.getDouble("linea_de_credito");
                String direccion = rs.getString("direccion");
                int telefono = rs.getInt("telefono");

                System.out.printf("| %-7d| %-20s| %-10s| %-10d| %-9.2f| %-30s| %-10d|%n", codigo, apellidos, nombres, RUC, credito, direccion, telefono);
            }
            System.out.println("+--------+---------------------+-----------+-----------+----------+-------------------------------+-----------+");

            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarCodigo(int codigo) {
        String url = "jdbc:mysql://localhost/Empresa_PERU";
        String username = "Romero";
        String password = "60781212";

        try {
            Connection conexion = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM cliente WHERE codigo = ? ";
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int codigoCliente = resultSet.getInt("codigo");
                String apellidoCliente = resultSet.getString("apellidos");
                String nombreCliente = resultSet.getString("nombres");
                int rucCliente = resultSet.getInt("RUC");
                double creditoCliente = resultSet.getDouble("linea_de_credito");
                String direccionCliente = resultSet.getString("direccion");
                int numerocLiente = resultSet.getInt("telefono");

                System.out.println("\nCliente encontrado: ");
                System.out.println("Codigo: "+codigoCliente);
                System.out.println("Apellidos: "+ apellidoCliente);
                System.out.println("Nombre: "+nombreCliente);
                System.out.println("RUC: "+rucCliente);
                System.out.println("Credito: "+ creditoCliente);
                System.out.println("Direccion: "+ direccionCliente);
                System.out.println("Telefono: "+ numerocLiente);
            } else {
                System.out.println("Cliente no encontrado por Codigo.");
            }

            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarRUC(int ruc) {
        String url = "jdbc:mysql://localhost/Empresa_PERU";
        String username = "Romero";
        String password = "60781212";

        try {
            Connection conexion = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM cliente WHERE RUC = ? ";
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setInt(1, ruc);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int codigoCliente = resultSet.getInt("codigo");
                String apellidoCliente = resultSet.getString("apellidos");
                String nombreCliente = resultSet.getString("nombres");
                int rucCliente = resultSet.getInt("RUC");
                double creditoCliente = resultSet.getDouble("linea_de_credito");
                String direccionCliente = resultSet.getString("direccion");
                int numerocLiente = resultSet.getInt("telefono");

                System.out.println("\nCliente encontrado: ");
                System.out.println("Codigo: "+codigoCliente);
                System.out.println("Apellidos: "+ apellidoCliente);
                System.out.println("Nombre: "+nombreCliente);
                System.out.println("RUC: "+rucCliente);
                System.out.println("Credito: "+ creditoCliente);
                System.out.println("Direccion: "+ direccionCliente);
                System.out.println("Telefono: "+ numerocLiente);

            } else {
                System.out.println("Cliente no encontrado por RUC.");
            }

            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        while (true) {
            menu.mostrarMenu();
            System.out.println("Escoja una opcion [1, 2, 3, 4] o [0] para salir:");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el Apellido del cliente: ");
                    String apellidos = sc.nextLine();
                    System.out.println("Ingrese el nombre: ");
                    String nombres = sc.nextLine();
                    System.out.println("Ingrese su RUC:");
                    int RUC = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese su linea de credito: ");
                    double credito = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Ingrese su direccion:");
                    String direccion = sc.nextLine();
                    System.out.println("Ingrese su numero de telefono:");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    menu.insertarDatos(apellidos, nombres, RUC, credito, direccion, telefono);
                    break;
                case 2:
                    System.out.println("\n---------------------------------------------Registro de clientes---------------------------------------------");
                    menu.mostrarDatos();
                    break;
                case 3:
                    System.out.println("Ingrese el codigo del cliente:");
                    int codigo = sc.nextInt();
                    menu.buscarCodigo(codigo);
                    break;
                case 4:
                    System.out.println("Ingrese el RUC del cliente:");
                    int ruc = sc.nextInt();
                    menu.mostrarRUC(ruc);
                    break;
                case 0:
                    System.out.println("Saliendo del programa....");
                    sc.close();
                    return;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
                    break;
            }
        }

    }
}
