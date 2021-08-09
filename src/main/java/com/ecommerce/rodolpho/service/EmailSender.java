package com.ecommerce.rodolpho.service;

public class EmailSender {

    public static void enviarEmail(String rementente, String destino, String mensagem) {
        System.out.println(
                "De: " + rementente + "\n" +
                "Para: " + destino + "\n" +
                "Mensagem: " + mensagem
        );
    }
}
