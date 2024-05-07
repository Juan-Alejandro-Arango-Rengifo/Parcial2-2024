package org.example;

import org.example.modelo.Paciente;
import org.example.repository.ProductRepositoryImpl;
import org.example.repository.Repository;
import org.example.util.ConexionBaseDatos;

import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getInstace()){
            Repository<Paciente> repository = new ProductRepositoryImpl();
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;

            while (!salir) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Listar pacientes");
                System.out.println("2. Mostrar paciente por ID");
                System.out.println("3. Ingresar nuevo paciente");
                System.out.println("4. Eliminar paciente por ID");
                System.out.println("5. Salir");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        // Listar todos los pacientes
                        System.out.println("Lista de pacientes:");
                        repository.Listar().forEach(System.out::println);
                        break;
                    case 2:
                        // Mostrar paciente por ID
                        System.out.print("Ingrese el ID del paciente: ");
                        long id = scanner.nextLong();
                        Paciente paciente = repository.porid(id);
                        if (paciente != null) {
                            System.out.println("Paciente encontrado:");
                            System.out.println(paciente);
                        } else {
                            System.out.println("No se encontró ningún paciente con el ID proporcionado.");
                        }
                        break;
                    case 3:
                        // Ingresar nuevo paciente
                        System.out.println("Ingrese los datos del nuevo paciente:");
                        System.out.print("Nombre: ");
                        scanner.nextLine(); // Consumir la nueva línea pendiente después de nextInt()
                        String nombre = scanner.nextLine();
                        System.out.print("Raza: ");
                        String raza = scanner.nextLine();
                        System.out.print("Peso: ");
                        long peso = scanner.nextLong();
                        Paciente nuevoPaciente = new Paciente();
                        nuevoPaciente.setNombre(nombre);
                        nuevoPaciente.setRaza(raza);
                        nuevoPaciente.setPeso(peso);
                        repository.guardar(nuevoPaciente);
                        System.out.println("Paciente ingresado con éxito.");
                        break;
                    case 4:
                        // Eliminar paciente por ID
                        System.out.print("Ingrese el ID del paciente que desea eliminar: ");
                        long idEliminar = scanner.nextLong();
                        repository.eliminar(idEliminar);
                        System.out.println("Paciente eliminado con éxito.");
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
