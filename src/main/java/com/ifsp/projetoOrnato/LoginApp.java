package com.ifsp.projetoOrnato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginApp {

    
    private static final String URL = "jdbc:mysql://localhost:3306/ornato";
    private static final String USER = "ORNATO";

    public static boolean autenticar(String nome, String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND email = ? AND senha = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (autenticar(nome, email, senha)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Nome, e-mail ou senha incorretos.");
        }
    }
}