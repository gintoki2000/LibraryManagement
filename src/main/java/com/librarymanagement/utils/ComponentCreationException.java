/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.utils;

/**
 *
 * @author ngotrung
 */
public class ComponentCreationException extends Exception {

    public ComponentCreationException(String message) {
        super(message);
    }

    public ComponentCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentCreationException(Throwable cause) {
        super(cause);
    }
    
}
