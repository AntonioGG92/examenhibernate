package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Empresa";
        String usuario = "root";
        String contraseña = "";

        String crearTablaSQL = """
                CREATE TABLE IF NOT EXISTS Empleados (
                    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    departamento VARCHAR(50) NOT NULL
                );
                """;

        String insertarDatosSQL = """
                INSERT INTO Empleados (nombre, email, departamento) VALUES
                ('Antonio Guerrero', 'antonioguerrero@empresa.com', 'Recursos Humanos'),
                ('Benito Camela', 'benitocamela@empresa.com', 'Marketing'),
                ('Tomas Turbado', tomasturbado@empresa.com', 'IT');
                """;

        String mostrarDatosSQL = "SELECT * FROM Empleados;";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement sentencia = conexion.createStatement()) {

            // Crear la tabla
            sentencia.execute(crearTablaSQL);
            System.out.println("Tabla 'Empleados' creada o ya existe.");

            // Insertar datos
            sentencia.executeUpdate(insertarDatosSQL);
            System.out.println("Datos insertados en la tabla 'Empleados'.");

            // Mostrar los datos
            ResultSet resultado = sentencia.executeQuery(mostrarDatosSQL);
            System.out.println("Datos en la tabla 'Empleados':");
            while (resultado.next()) {
                int id = resultado.getInt("id_empleado");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String departamento = resultado.getString("departamento");
                System.out.printf("ID: %d, Nombre: %s, Email: %s, Departamento: %s%n", id, nombre, email, departamento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

