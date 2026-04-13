import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 *  Cadastro de Clientes — FlatLaf (Swing moderno)
 *  Dependência: flatlaf-3.x.jar  (ver roteiro de instalação)
 * ============================================================
 */
public class CadastroClientes extends JFrame {

    // ── Campos do formulário ──────────────────────────────────
    private JTextField      txtNome;
    private JFormattedTextField txtDataNasc;
    private JFormattedTextField txtCPF;
    private JTextField      txtEmail;
    private JFormattedTextField txtCelular;

    // ── Tabela ────────────────────────────────────────────────
    private DefaultTableModel tableModel;
    private JTable            tabela;

    // ── Repositório em memória ────────────────────────────────
    private final List<Cliente> clientes = new ArrayList<>();

    // ─────────────────────────────────────────────────────────
    //  MODELO DE DADOS (inner record / class)
    // ─────────────────────────────────────────────────────────
    static class Cliente {
        String nome, dataNasc, cpf, email, celular;
        Cliente(String nome, String dataNasc, String cpf,
                String email, String celular) {
            this.nome     = nome;
            this.dataNasc = dataNasc;
            this.cpf      = cpf;
            this.email    = email;
            this.celular  = celular;
        }
    }

    // ─────────────────────────────────────────────────────────
    //  CONSTRUTOR
    // ─────────────────────────────────────────────────────────
    public CadastroClientes() {
        super("Cadastro de Clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(criarPainelFormulario(), BorderLayout.NORTH);
        add(criarPainelTabela(),     BorderLayout.CENTER);
        add(criarPainelBotoes(),     BorderLayout.SOUTH);
    }

    // ─────────────────────────────────────────────────────────
    //  PAINEL — FORMULÁRIO
    // ─────────────────────────────────────────────────────────
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "  Dados do Cliente  "));
        painel.setBackground(UIManager.getColor("Panel.background"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(6, 10, 6, 10);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.WEST;

        // ── Coluna 0: rótulos; Coluna 1: campos ──────────────

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painel.add(new JLabel("Nome *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNome = new JTextField(30);
        painel.add(txtNome, gbc);

        // Data de Nascimento
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painel.add(new JLabel("Data de Nasc. * (dd/mm/aaaa)"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtDataNasc = criarFormattedField("##/##/####");
        painel.add(txtDataNasc, gbc);

        // CPF
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painel.add(new JLabel("CPF *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCPF = criarFormattedField("###.###.###-##");
        painel.add(txtCPF, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        painel.add(new JLabel("E-mail *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtEmail = new JTextField(30);
        painel.add(txtEmail, gbc);

        // Celular
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painel.add(new JLabel("Celular * (+55 (XX) 9XXXX-XXXX)"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCelular = criarFormattedField("+55 (##) #####-####");
        painel.add(txtCelular, gbc);

        return painel;
    }

    /** Cria um JFormattedTextField com máscara, sem lançar checked exception. */
    private JFormattedTextField criarFormattedField(String mascara) {
        try {
            MaskFormatter fmt = new MaskFormatter(mascara);
            fmt.setPlaceholderCharacter('_');
            JFormattedTextField campo = new JFormattedTextField(fmt);
            campo.setColumns(20);
            return campo;
        } catch (java.text.ParseException e) {
            // Não deve ocorrer com máscaras fixas
            return new JFormattedTextField();
        }
    }

    // ─────────────────────────────────────────────────────────
    //  PAINEL — TABELA
    // ─────────────────────────────────────────────────────────
    private JScrollPane criarPainelTabela() {
        String[] colunas = {"Nome", "Data Nasc.", "CPF", "E-mail", "Celular"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabela = new JTable(tableModel);
        tabela.setRowHeight(26);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "  Clientes Cadastrados  "));
        return scroll;
    }

    // ─────────────────────────────────────────────────────────
    //  PAINEL — BOTÕES
    // ─────────────────────────────────────────────────────────
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 8));

        JButton btnSalvar  = new JButton("💾 Salvar");
        JButton btnListar  = new JButton("📋 Listar Clientes");
        JButton btnLimpar  = new JButton("🗑 Limpar");

        // ── Estilo dos botões ─────────────────────────────────
        btnSalvar.putClientProperty("JButton.buttonType", "default");   // destaque FlatLaf
        btnSalvar.setPreferredSize (new Dimension(140, 34));
        btnListar.setPreferredSize (new Dimension(150, 34));
        btnLimpar.setPreferredSize (new Dimension(120, 34));

        // ── LISTENERS (ponto central de aprendizagem) ─────────

        /*
         * Listener 1 — Salvar
         * Dispara ao clicar em "Salvar".
         * 1. Coleta os valores dos campos.
         * 2. Valida se todos estão preenchidos.
         * 3. Valida o CPF (algoritmo oficial).
         * 4. Adiciona o cliente à ArrayList.
         * 5. Exibe mensagem de sucesso e limpa o formulário.
         */
        btnSalvar.addActionListener((ActionEvent e) -> salvarCliente());

        /*
         * Listener 2 — Listar
         * Limpa a tabela e a repopula a partir da ArrayList,
         * garantindo que a view sempre reflita o estado atual
         * dos dados em memória.
         */
        btnListar.addActionListener((ActionEvent e) -> listarClientes());

        /*
         * Listener 3 — Limpar
         * Restaura todos os campos para o estado inicial,
         * sem alterar a ArrayList.
         */
        btnLimpar.addActionListener((ActionEvent e) -> limparFormulario());

        painel.add(btnLimpar);
        painel.add(btnListar);
        painel.add(btnSalvar);
        return painel;
    }

    // ─────────────────────────────────────────────────────────
    //  LÓGICA DE NEGÓCIO
    // ─────────────────────────────────────────────────────────

    /** Coleta, valida e persiste um cliente na ArrayList. */
    private void salvarCliente() {
        String nome     = txtNome.getText().trim();
        String dataNasc = txtDataNasc.getText().trim();
        String cpf      = txtCPF.getText().trim();
        String email    = txtEmail.getText().trim();
        String celular  = txtCelular.getText().trim();

        // ── Validação de campos obrigatórios ──────────────────
        if (nome.isEmpty() || dataNasc.contains("_") ||
                cpf.contains("_")  || email.isEmpty() || celular.contains("_")) {
            mostrarErro("Todos os campos são obrigatórios.\nPreencha os campos em destaque.");
            return;
        }

        // ── Validação de e-mail simples ───────────────────────
        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            mostrarErro("Formato de e-mail inválido.");
            return;
        }

        // ── Validação de CPF (algoritmo oficial) ──────────────
        String cpfNumeros = cpf.replaceAll("[^\\d]", "");
        if (!validarCPF(cpfNumeros)) {
            mostrarErro("CPF inválido. Verifique os dígitos e tente novamente.");
            return;
        }

        // ── Verifica duplicidade de CPF ───────────────────────
        for (Cliente c : clientes) {
            if (c.cpf.replaceAll("[^\\d]", "").equals(cpfNumeros)) {
                mostrarErro("Já existe um cliente cadastrado com este CPF.");
                return;
            }
        }

        clientes.add(new Cliente(nome, dataNasc, cpf, email, celular));
        JOptionPane.showMessageDialog(this,
                "✅ Cliente \"" + nome + "\" cadastrado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparFormulario();
    }

    /** Repopula a JTable a partir da ArrayList. */
    private void listarClientes() {
        tableModel.setRowCount(0);                   // limpa linhas existentes
        for (Cliente c : clientes) {
            tableModel.addRow(new Object[]{
                    c.nome, c.dataNasc, c.cpf, c.email, c.celular
            });
        }
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum cliente cadastrado ainda.",
                    "Lista vazia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** Restaura o formulário para o estado inicial. */
    private void limparFormulario() {
        txtNome.setText("");
        txtDataNasc.setValue(null);
        txtCPF.setValue(null);
        txtEmail.setText("");
        txtCelular.setValue(null);
        txtNome.requestFocusInWindow();
    }

    private void mostrarErro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro de validação",
                JOptionPane.ERROR_MESSAGE);
    }

    // ─────────────────────────────────────────────────────────
    //  ALGORITMO DE VALIDAÇÃO DE CPF
    //  Regra oficial da Receita Federal Brasileira.
    // ─────────────────────────────────────────────────────────
    private boolean validarCPF(String cpf) {
        if (cpf.length() != 11) return false;

        // Rejeita sequências repetidas (ex: 111.111.111-11)
        if (cpf.chars().distinct().count() == 1) return false;

        try {
            int[] d = new int[11];
            for (int i = 0; i < 11; i++) d[i] = cpf.charAt(i) - '0';

            // Primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += d[i] * (10 - i);
            int r1 = (soma * 10) % 11;
            if (r1 == 10 || r1 == 11) r1 = 0;
            if (r1 != d[9]) return false;

            // Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) soma += d[i] * (11 - i);
            int r2 = (soma * 10) % 11;
            if (r2 == 10 || r2 == 11) r2 = 0;
            return r2 == d[10];

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ─────────────────────────────────────────────────────────
    //  PONTO DE ENTRADA
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Instala o Look and Feel FlatLaf ANTES de criar qualquer componente
        FlatLightLaf.setup();

        // Ajustes globais de UI (opcional — experimente mudar estes valores!)
        UIManager.put("Button.arc",           10);
        UIManager.put("Component.arc",        8);
        UIManager.put("TextComponent.arc",    8);
        UIManager.put("defaultFont",          new Font("Segoe UI", Font.PLAIN, 13));

        // Garante que a janela seja criada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new CadastroClientes().setVisible(true));
    }
}
