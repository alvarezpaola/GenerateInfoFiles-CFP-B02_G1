/*
Integrantes:
- JUAN CAMILO ALEGRIAS ALVAREZ
- KEVIN STEVEN ALEJO CASTILLO
- YURI PAOLA ALVAREZ
- DANIELA ALZATE VALENCIA
- DAYRA CAMILA ARAGON GONZALEZ
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
    // Listas de nombres, apellidos, productos y precios
    private static final String[] NAMES = {"Kevin", "Daniela", "Dayra", "Paola", "Juan"};
    private static final String[] SURNAMES = {"Alejo", "Alzate", "Aragon", "Alvarez", "Alegrias"};
    private static final String[] PRODUCTS = {"Laptop Gamer", "Mouse Inalámbrico", "Teclado Mecánico", "Monitor 24''", "Silla Ergonómica"};
    private static final int[] PRICES = {2500000, 120000, 350000, 800000, 600000};
    private static final Random RANDOM = new Random(); // Generador de números aleatorios

    public static void main(String[] args) {
        try {
            // Genera archivo de productos
            createProductsFile(10);
            // Genera archivo de información de vendedores
            createSalesManInfoFile(5);
            // Genera archivos de ventas para cada vendedor
            for (int i = 0; i < 5; i++) {
                createSalesMenFile(5 + RANDOM.nextInt(10), NAMES[i] + " " + SURNAMES[i], 1000 + i);
            }
            System.out.println("Archivos CSV generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }

    // Método para crear un archivo CSV con productos
    public static void createProductsFile(int productsCount) throws IOException {
        File file = new File("productos.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < productsCount; i++) {
                String productId = String.format("P%03d", i + 1); // Genera un ID con formato P001, P002...
                String productName = PRODUCTS[i % PRODUCTS.length]; // Obtiene un producto de la lista
                int price = PRICES[i % PRICES.length]; // Obtiene un precio de la lista
                writer.write(productId + ";" + productName + ";" + price + "\n");
            }
        }
    }

    // Método para crear un archivo CSV con información de vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        File file = new File("vendedores.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < salesmanCount; i++) {
                String name = NAMES[RANDOM.nextInt(NAMES.length)]; // Selecciona un nombre aleatorio
                String surname = SURNAMES[RANDOM.nextInt(SURNAMES.length)]; // Selecciona un apellido aleatorio
                writer.write("CC;" + (1000 + i) + ";" + name + ";" + surname + "\n");
            }
        }
    }

    // Método para crear un archivo CSV de ventas de un vendedor
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        File file = new File("ventas_" + id + ".csv"); // Archivo con el ID del vendedor
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("CC;" + id + "\n"); // Escribe el identificador del vendedor
            for (int i = 0; i < randomSalesCount; i++) {
                String productId = String.format("P%03d", 1 + RANDOM.nextInt(5)); // Selecciona un producto aleatorio
                int quantity = 1 + RANDOM.nextInt(5); // Genera una cantidad aleatoria
                writer.write(productId + ";" + quantity + "\n");
            }
        }
    }
}
