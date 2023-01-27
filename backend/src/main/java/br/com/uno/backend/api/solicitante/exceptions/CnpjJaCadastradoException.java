package br.com.uno.backend.api.solicitante.exceptions;

public class CnpjJaCadastradoException extends Exception{
    public CnpjJaCadastradoException() {
        super("CNPJ já cadastrado!");
    }
}
