import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorDeMonedas converter = new ConversorDeMonedas();

        System.out.println("\n****************************************");
        System.out.println("Bienvenido al Conversor de Monedas");

        while (true) {
            System.out.println("\n1) Dólar a Lempira Hondureño");
            System.out.println("2) Lempira Hondureño a Dólar");
            System.out.println("3) Dólar a Real Brasileño");
            System.out.println("4) Real Brasileño a Dólar");
            System.out.println("5) Dólar a Peso Colombiano");
            System.out.println("6) Peso Colombiano a Dólar");
            System.out.println("7) Dólar a Peso Dominicano");
            System.out.println("8) Peso Dominicano a Dólar");
            System.out.println("9) Salir");
            System.out.println("\n****************************************");
            System.out.print("Seleccione una opción del menú: ");

            int option;
            try {
                option = scanner.nextInt();
                if (option == 9) break;
                if (option < 1 || option > 9) {
                    System.out.println("Opción inválida. Por favor, seleccione una opción del menú.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer del Scanner
                continue;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double amount;
            try {
                amount = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer del Scanner
                continue;
            }

            try {
                String result = converter.convertirMoneda(option, amount);
                System.out.println("Resultado equivale a: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        System.out.println("** Programa finalizado. Gracias por utilizar nuestra aplicación. **");
        scanner.close();
    }
}