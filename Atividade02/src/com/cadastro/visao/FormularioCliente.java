package com.cadastro.visao;

import com.cadastro.modelo.Cliente; // importa o modelo da outra camada

import javax.swing.*;
import java.awt.*;

/**
 * Visão (View) — formulário Swing para cadastro de clientes.
 * Responsabilidade: exibir a tela e validar os dados antes de criar o objeto.
 */
public class FormularioCliente extends JFrame {

    // Campos de texto do formulário
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoTelefone;

    public FormularioCliente() {
        configurarJanela();
        inicializarComponentes();
    }

    // Define título, tamanho e comportamento de fechamento
    private void configurarJanela() {
        setTitle("Cadastro de Clientes");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela
        setLayout(new GridLayout(4, 2, 10, 10));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    // Cria e posiciona todos os componentes visuais
    private void inicializarComponentes() {
        campoNome      = new JTextField();
        campoEmail     = new JTextField();
        campoTelefone  = new JTextField();

        JButton botaoSalvar  = new JButton("Salvar");
        JButton botaoLimpar  = new JButton("Limpar");

        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("E-mail:"));
        add(campoEmail);
        add(new JLabel("Telefone:"));
        add(campoTelefone);
        add(botaoLimpar);
        add(botaoSalvar);

        // Ações dos botões
        botaoSalvar.addActionListener(e -> aoClicarSalvar());
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    /**
     * Chamado ao clicar em "Salvar".
     * Valida os dados e, se corretos, instancia o objeto Cliente.
     */
    private void aoClicarSalvar() {
        String nome      = campoNome.getText().trim();
        String email     = campoEmail.getText().trim();
        String telefone  = campoTelefone.getText().trim();

        // --- Validações ---
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!", "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verifica se o e-mail contém '@'
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this,
                    "E-mail inválido! Deve conter '@'.", "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Criação do objeto (instanciação do modelo) ---
        Cliente novoCliente = new Cliente(nome, email, telefone);

        // Imprime os dados no console do IntelliJ
        System.out.println(novoCliente);

        JOptionPane.showMessageDialog(this,
                "Cliente cadastrado com sucesso!\nVeja o console para os detalhes.",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        limparCampos();
    }

    // Apaga o conteúdo de todos os campos
    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        campoNome.requestFocus(); // coloca o cursor no primeiro campo
    }

    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        // Garante que a UI rode na thread correta (boa prática Swing)
        SwingUtilities.invokeLater(() -> {
            FormularioCliente formulario = new FormularioCliente();
            formulario.setVisible(true);
        });
    }
}
