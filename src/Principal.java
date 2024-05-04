import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorDeMonedas converter = new ConversorDeMonedas();

        System.out.println("\n****************************************");
        System.out.println("BIEVENIDO AL CONVERSOR DE MONEDAS");

        while (true) {
            System.out.println("\n1) De Dólar a Lempira Hondureño");
            System.out.println("2) De Lempira Hondureño a Dólar");
            System.out.println("3) De Dólar a Colón Costarricense");
            System.out.println("4) De Colón Costarricense a Dólar");
            System.out.println("5) De Dólar a Quetzal Guatemalteco");
            System.out.println("6) De Quetzal Guatemalteco a Dólar");
            System.out.println("7) De Dólar a Córdoba Nicaragüense");
            System.out.println("8) De Córdoba Nicaragüense a Dólar");
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
                System.out.println((int) amount + " " + ConversorDeMonedas.obtenerNombreMoneda(option) + " equivalen a " + result + " " + ConversorDeMonedas.obtenerNombreMoneda(ConversorDeMonedas.opuestoOpcion(option)));

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        System.out.println("**** Ha finalizado. Gracias por utilizar nuestro sistema. ****");
        scanner.close();
    }
}