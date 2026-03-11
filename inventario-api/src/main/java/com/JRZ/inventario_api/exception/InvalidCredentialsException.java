package com.JRZ.inventario_api.exception;




public class InvalidCredentialsException extends RuntimeException {
    

    
    public InvalidCredentialsException() {
        
        super("Credenciales incorrectas"); 
    }

  
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
