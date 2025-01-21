import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainClass {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/7e637f4659ca93ed1315e1ea/latest/USD";
    private static JsonObject conversionRates;

    public static void main(String[] args) {
        try {
            // Obtener las tasas de conversión desde la API
            obtenerTasasDeConversion();

            // Crear el objeto Scanner para leer las entradas del usuario
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Mostrar el menú
                System.out.println("\nSelecciona la moneda a la que deseas convertir (0 para salir):");
                System.out.println("1. ARS - Peso argentino");
                System.out.println("2. BOB - Boliviano");
                System.out.println("3. BRL - Real brasileño");
                System.out.println("4. CLP - Peso chileno");
                System.out.println("5. COP - Peso colombiano");
                System.out.println("6. USD - Dólar estadounidense");
                System.out.println("0. Salir");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();

                // Salir si el usuario ingresa '0'
                if (opcion == 0) {
                    System.out.println("¡Hasta luego!");
                    break;
                }

                // Validar la opción
                String monedaSeleccionada = "";
                switch (opcion) {
                    case 1: monedaSeleccionada = "ARS"; break;
                    case 2: monedaSeleccionada = "BOB"; break;
                    case 3: monedaSeleccionada = "BRL"; break;
                    case 4: monedaSeleccionada = "CLP"; break;
                    case 5: monedaSeleccionada = "COP"; break;
                    case 6: monedaSeleccionada = "USD"; break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 6.");
                        continue;
                }

                // Pedir la cantidad a convertir
                System.out.print("Ingresa la cantidad en USD a convertir: ");
                double cantidad = scanner.nextDouble();

                // Verificar que "conversion_rates" no es null y contiene la moneda seleccionada
                if (conversionRates != null && conversionRates.has(monedaSeleccionada)) {
                    double tasaDeConversion = conversionRates.get(monedaSeleccionada).getAsDouble();
                    double resultado = cantidad * tasaDeConversion;
                    System.out.println(cantidad + " USD equivalen a " + resultado + " " + monedaSeleccionada);
                } else {
                    System.out.println("Error: No se pudo obtener la tasa de conversión para " + monedaSeleccionada);
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    // Método para obtener las tasas de conversión desde la API
    private static void obtenerTasasDeConversion() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta de la API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Imprimir la respuesta completa para depuración
        System.out.println("Respuesta de la API: " + response.toString());

        // Convertir la respuesta JSON a un objeto Java
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

        // Verificar si "conversion_rates" existe en la respuesta
        if (jsonResponse.has("conversion_rates")) {
            // Extraer las tasas de conversión si existe la clave
            conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
        } else {
            System.out.println("Error: 'conversion_rates' no encontrado en la respuesta de la API.");
            conversionRates = new JsonObject(); // Opcionalmente, puedes poner un valor por defecto
        }
    }
}
