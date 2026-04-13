package com.cadastro.modelo;

/**
 * Modelo de dados do cliente (POJO).
 * Representa apenas os dados — sem lógica de tela.
 */
public class Cliente {

    // Atributos privados (encapsulamento)
    private String nome;
    private String email;
    private String telefone;

    // Construtor com todos os campos
    public Cliente(String nome, String email, String telefone) {
        this.nome      = nome;
        this.email     = email;
        this.telefone  = telefone;
    }

    // --- Getters e Setters ---

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Exibe os dados no console de forma legível
    @Override
    public String toString() {
        return "=== Cliente Cadastrado ===\n" +
                "Nome:     " + nome     + "\n" +
                "E-mail:   " + email    + "\n" +
                "Telefone: " + telefone;
    }
}
