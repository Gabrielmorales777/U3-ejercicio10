public class BilleteraVirtual {

    private static final int LARGO_MINIMO_PIN = 4;


    private final String alias;
    private double saldo;
    private double limiteDiarioTransferencia;
    private double montoTransferidoHoy;
    private final String pin;

    public BilleteraVirtual(String alias, double saldoInicial, double limiteDiarioTransferencia, String pin) {
        if (alias == null || alias.trim().isEmpty()) {
            throw new IllegalArgumentException("El alias no puede estar vacío.");
        }
        if (!esMontoValido(saldoInicial) && saldoInicial != 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo ni un valor inválido.");
        }
        if (!esMontoValido(limiteDiarioTransferencia)) {
            throw new IllegalArgumentException("El límite diario debe ser mayor a 0.");
        }
        if (pin == null || pin.length() < LARGO_MINIMO_PIN) {
            throw new IllegalArgumentException("El PIN debe tener al menos " + LARGO_MINIMO_PIN + " caracteres.");
        }
        this.alias = alias;
        this.saldo = saldoInicial;
        this.limiteDiarioTransferencia = limiteDiarioTransferencia;
        this.montoTransferidoHoy = 0.0;
        this.pin = pin;
    }


    public String getAlias() {
        return alias;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteDiarioTransferencia() {
        return limiteDiarioTransferencia;
    }

    public double getMontoTransferidoHoy() {
        return montoTransferidoHoy;
    }




    public boolean transferir(double monto) {
        if (!esMontoValido(monto)) {
            System.out.println("Transferencia rechazada: el monto debe ser un número mayor a 0 (se ingresó "
                    + monto + ").");
            return false;
        }

        boolean superaSaldo = monto > saldo;
        boolean superaLimite = (montoTransferidoHoy + monto) > limiteDiarioTransferencia;

        if (superaSaldo && superaLimite) {
            System.out.println("Transferencia rechazada: $" + monto + " supera el saldo ($" + saldo
                    + ") y el límite diario disponible ($" + disponibleHoy() + ").");
            return false;
        }
        if (superaSaldo) {
            System.out.println("Transferencia rechazada: saldo insuficiente. Saldo disponible: $" + saldo + ".");
            return false;
        }
        if (superaLimite) {
            System.out.println("Transferencia rechazada: supera el límite diario. Disponible hoy: $"
                    + disponibleHoy() + ".");
            return false;
        }
        saldo -= monto;
        montoTransferidoHoy += monto;
        System.out.println("Transferencia aprobada: $" + monto + ". Saldo: $" + saldo
                + " | Disponible hoy: $" + disponibleHoy());
        return true;
    }


    public boolean configurarLimiteDiario(double nuevoLimite, String pinIngresado) {
        if (!pin.equals(pinIngresado)) {
            System.out.println("Cambio de límite rechazado: no se pudo validar la titularidad (PIN incorrecto).");
            return false;
        }
        if (!esMontoValido(nuevoLimite)) {
            System.out.println("Cambio de límite rechazado: el nuevo límite debe ser mayor a 0.");
            return false;
        }
        limiteDiarioTransferencia = nuevoLimite;
        System.out.println("Límite diario actualizado a $" + limiteDiarioTransferencia + ".");
        return true;
    }


    public boolean iniciarNuevoDia(String pinIngresado) {
        if (!pin.equals(pinIngresado)) {
            System.out.println("Reinicio de jornada rechazado: PIN incorrecto.");
            return false;
        }
        montoTransferidoHoy = 0.0;
        System.out.println("Nuevo día iniciado: el acumulado de transferencias se reinició.");
        return true;
    }


    private boolean esMontoValido(double valor) {
        return !Double.isNaN(valor) && !Double.isInfinite(valor) && valor > 0;
    }

    private double disponibleHoy() {
        return limiteDiarioTransferencia - montoTransferidoHoy;
    }


    public String obtenerEstado() {
        return "Alias: " + alias + " | Saldo: $" + saldo
                + " | Límite diario: $" + limiteDiarioTransferencia
                + " | Transferido hoy: $" + montoTransferidoHoy;
    }
}
