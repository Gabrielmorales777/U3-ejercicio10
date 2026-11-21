public class Main {

    public static void main(String[] args) {


        BilleteraVirtual billetera = new BilleteraVirtual("lucia.mp", 100000, 50000, "4821");

        System.out.println("=== Estado inicial ===");
        System.out.println(billetera.obtenerEstado());

        System.out.println("\n=== Transferencias exitosas ===");
        billetera.transferir(20000);
        billetera.transferir(25000);

        System.out.println("\n=== Transferencias rechazadas ===");
        billetera.transferir(10000);
        billetera.transferir(-500);
        billetera.transferir(0);

        System.out.println("\n=== Cambio de límite: titularidad no validada ===");
        billetera.configurarLimiteDiario(200000, "0000");
        System.out.println("\n=== Cambio de límite: titularidad validada ===");
        billetera.configurarLimiteDiario(80000, "4821");
        billetera.transferir(10000);

        System.out.println("\n=== Nuevo día y rechazo por saldo insuficiente ===");
        billetera.iniciarNuevoDia("4821");
        billetera.configurarLimiteDiario(500000, "4821");
        billetera.transferir(90000);

        System.out.println("\n=== Estado final ===");
        System.out.println(billetera.obtenerEstado());


    }
}
