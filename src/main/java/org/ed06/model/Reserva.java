package org.ed06.model;

import java.time.LocalDate;

public class Reserva {
    public static final double DESCUENTO_VIP = 0.9;
    public static final double DESCUENTO_ESTANCIA_LARGA = 0.95;
    public static final int DIAS_ESTANCIA_LARGA = 7;
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    // Calcula el precio total de la reserva. Para calcular el precio total, se debe calcular el precio base de la habitación por el número de noches de la reserva. En el caso de que el cliente sea vip, se aplicará un descuento del 10%. Además, si el intervalo de fechas es mayor a 7 días, se aplicará un descuento adicional del 5%.
    // Devuelve precio total de la reserva
    public double calcularPrecioFinal() {
        //calculamos los días de la reserva
        int numeroNoches = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();
        // Calculamos el precio base de la habitación por el número de noches de la reserva
        double precioBase = habitacion.getPrecioBase() * numeroNoches;
        // Declaramos la variable para almacenar el precio final
        double precioFinal = precioBase;

        // Si el cliente es VIP, aplicamos un descuento del 10%
        if (cliente.isEsVip()) {
            precioFinal *= DESCUENTO_VIP;
        }

        // Si el intervalo de fechas es mayor a 7 días, aplicamos un descuento adicional del 5%
        if (numeroNoches > DIAS_ESTANCIA_LARGA) {
            precioFinal *= DESCUENTO_ESTANCIA_LARGA;
        }


        // Devolvemos el precio final
        return precioFinal;
    }

    public void mostrarReserva() {
        System.out.println("Reserva #" + id);
        System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() + " - Precio base: " + habitacion.getPrecioBase());
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Fecha de inicio: " + fechaInicio.toString());
        System.out.println("Fecha de fin: " + fechaFin.toString());
        System.out.printf("Precio total: %.2f €\n", precioTotal);
    }
}
