package br.com.uno.backend.api.solicitante.exceptions;

public class SolicitanteNotFoundException extends Exception{
    public SolicitanteNotFoundException() {
        super("Solicitante Não Encontrado!");
    }
}
