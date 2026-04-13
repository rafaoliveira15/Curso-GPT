import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroClienteForm extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JFormattedTextField txtTelefone;
    private JTextField txtCPF;

    // ===== CONFIG BANCO =====
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes";
    private static final String USER = "root";
    private static final String PASSWORD = "Senai@118"; // altere se necessário

    public CadastroClienteForm() {
        setTitle("Cadastro de Clientes");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        add(txtNome, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        add(txtEmail, gbc);

        // Telefone
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        try {
            MaskFormatter mascara = new MaskFormatter("(##) #####-####");
            mascara.setPlaceholderCharacter('_');
            txtTelefone = new JFormattedTextField(mascara);
        } catch (Exception e) {
            txtTelefone = new JFormattedTextField();
        }
        add(txtTelefone, gbc);

        // CPF
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("CPF:"), gbc);

        gbc.gridx = 1;
        txtCPF = new JTextField(20);
        add(txtCPF, gbc);

        // Botões
        gbc.gridx = 0; gbc.gridy = 4;
        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar, gbc);

        gbc.gridx = 1;
        JButton btnLimpar = new JButton("Limpar");
        add(btnLimpar, gbc);

        btnSalvar.addActionListener(e -> salvarDados());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    // ===== CONEXÃO =====
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ===== SALVAR =====
    private void salvarDados() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText();
        String cpf = txtCPF.getText().replaceAll("[^0-9]", "");

        if (nome.isEmpty() || email.isEmpty() || telefone.contains("_") || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ CORRETO: tabela = clientes
        String sql = "INSERT INTO clientes (nome, email, telefone, cpf) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, cpf);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // ===== LIMPAR =====
    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setValue(null);
        txtCPF.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroClienteForm().setVisible(true));
    }
}