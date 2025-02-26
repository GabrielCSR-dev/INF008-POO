package br.edu.ifba.inf008.models;

import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.io.Serializable;

import java.util.ArrayList;
import java.time.LocalDate;

public class Loan implements Serializable{
    private int ID;
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate expirationDate;
    private boolean expirationStatus;
    private double fine; 
    private static int numberOfLoans = 0;

    public static Loan openLoan(User borrower){

        return;
    }
}