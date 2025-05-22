package br.com.germanott.gestao_vagas.exceptions;

//Class to handle exceptions when a user is already found
// This class extends RuntimeException, which means it is an unchecked exception.
// Unchecked exceptions do not need to be declared in the method signature or caught
// in a try-catch block.
public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existe: ");
    }
}
