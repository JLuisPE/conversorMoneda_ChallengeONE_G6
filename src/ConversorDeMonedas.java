

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class ConversorDeMonedas {
    private final static String API_URL = "https://v6.exchangerate-api.com/v6/bc730fa8f110ef3c74a36e57/latest/USD";
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // Método para convertir una cantidad de una moneda a otra
    public String convertirMoneda(int opcion, double cantidad) throws Exception {
        // Realizar la solicitud a la API y obtener la respuesta en formato JSON
        String jsonRespuesta = obtenerTasasDeCambio();
        //Obtenemos el mapa de tasas de cambió especificos y los válidamos
        Map tasas = gson.fromJson(jsonRespuesta, Map.class);
        Map<String, Double> tasasDeCambio = (Map<String, Double>) tasas.get("conversion_rates");
        if (tasasDeCambio == null || tasasDeCambio.isEmpty()) {
            throw new Exception("No se pudo obtener tasas de cambio válidas desde la API");
        }

        // Realizar la conversión de acuerdo a la opción seleccionada
        double resultado = 0.0;
        Locale locale = Locale.US;
        switch (opcion) {
            case 1:
                resultado = cantidad * tasasDeCambio.getOrDefault("HNL", 0.00);
                locale = new Locale("es", "HN");
                break;
            case 2:
                resultado = cantidad / tasasDeCambio.getOrDefault("HNL", 0.0);
                break;
            case 3:
                resultado = cantidad * tasasDeCambio.getOrDefault("BRL", 0.0);
                locale = new Locale("pt", "BR");
                break;
            case 4:
                resultado = cantidad / tasasDeCambio.getOrDefault("BRL", 0.0);
                break;
            case 5:
                resultado = cantidad * tasasDeCambio.getOrDefault("COP", 0.0);
                locale = new Locale("es", "CO");
                break;
            case 6:
                resultado = cantidad / tasasDeCambio.getOrDefault("COP", 0.0);
                break;
            case 7:
                resultado = cantidad * tasasDeCambio.getOrDefault("DOP", 0.0);
                locale = new Locale("es", "DO");
                break;
            case 8:
                resultado = cantidad / tasasDeCambio.getOrDefault("DOP", 0.0);
                break;
            default:
                // Opción de conversión no válida
                throw new IllegalArgumentException("Opción de conversión no válida");
        }
        // Formatear el resultado como moneda con el locale correspondiente
        return formatCurrency(resultado, locale);
    }

    // Método para formatear un monto como moneda en el locale especificado
    private String formatCurrency(double amount, Locale locale) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(amount);
    }

    // Método para obtener las tasas de cambio desde la API
    private String obtenerTasasDeCambio() throws Exception {
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        // Enviar la solicitud y obtener la respuesta - se verifica si se completo correctamente la solicitud
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
            throw new Exception("La solicitud a la API no se completó correctamente");
        }

        // Devilver el cuerpo de la respuesta JSON de tasas de cambio
        return respuesta.body();
    }
}