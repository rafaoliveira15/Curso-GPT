import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

public class FormularioApp extends JFrame {

    private JFormattedTextField txtData;
    private JComboBox<String> cbOperador, cbFornecedor, cbProduto, cbTipoMov;
    private JTextField txtQuantidade;
    private final String ARQUIVO_NOME = "dados_movimentacao.txt";

    public FormularioApp() {
        setTitle("Sistema de Controle de Movimentação - Versão Beta");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new GridLayout(8, 2, 10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // DATA
        painelPrincipal.add(new JLabel("Data (dd/mm/aaaa):"));
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            txtData = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtData = new JFormattedTextField();
        }
        painelPrincipal.add(txtData);

        // OPERADOR
        painelPrincipal.add(new JLabel("Operador:"));
        cbOperador = new JComboBox<>(new String[]{"José", "Maria", "Joana", "Lucas"});
        painelPrincipal.add(cbOperador);

        // FORNECEDOR
        painelPrincipal.add(new JLabel("Fornecedor:"));
        cbFornecedor = new JComboBox<>(new String[]{"ABC", "WYZ", "XPTO"});
        painelPrincipal.add(cbFornecedor);

        // PRODUTO
        painelPrincipal.add(new JLabel("Produto:"));
        cbProduto = new JComboBox<>(new String[]{"Prod A", "Prod B", "Prod C", "Prod D", "Prod E"});
        painelPrincipal.add(cbProduto);

        // QUANTIDADE (BLOQUEIA LETRAS)
        painelPrincipal.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        ((AbstractDocument) txtQuantidade.getDocument()).setDocumentFilter(new NumeroFilter());
        painelPrincipal.add(txtQuantidade);

        // TIPO
        painelPrincipal.add(new JLabel("Tipo de Movimentação:"));
        cbTipoMov = new JComboBox<>(new String[]{"Entrada", "Saída"});
        painelPrincipal.add(cbTipoMov);

        JButton btnSalvar = new JButton("Registrar Movimentação");
        JButton btnListar = new JButton("Ver Registros");
        JButton btnSair = new JButton("Sair");

        painelPrincipal.add(btnSalvar);
        painelPrincipal.add(btnListar);
        painelPrincipal.add(new JLabel(""));
        painelPrincipal.add(btnSair);

        add(painelPrincipal);

        btnSalvar.addActionListener(e -> salvarDados());
        btnListar.addActionListener(e -> exibirRegistros());
        btnSair.addActionListener(e -> System.exit(0));
    }

    // ===== FILTRO PARA ACEITAR APENAS NÚMEROS =====
    class NumeroFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private void salvarDados() {
        try {
            // ===== VALIDAÇÃO DA DATA =====
            String dataTexto = txtData.getText();

            if (dataTexto.contains(" ")) {
                JOptionPane.showMessageDialog(this, "Preencha a data corretamente!");
                return;
            }

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            java.util.Date dataDigitada = sdf.parse(dataTexto);
            java.util.Date hoje = new java.util.Date();

            if (dataDigitada.after(hoje)) {
                JOptionPane.showMessageDialog(this, "Não é permitido inserir datas futuras!");
                return;
            }

            // ===== VALIDAÇÃO DA QUANTIDADE =====
            if (txtQuantidade.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a quantidade!");
                return;
            }

            int quantidade = Integer.parseInt(txtQuantidade.getText());

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero!");
                return;
            }

            // ===== SALVAR =====
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_NOME, true))) {

                String registro =
                        "Data: " + dataTexto +
                                " | Operador: " + cbOperador.getSelectedItem() +
                                " | Fornecedor: " + cbFornecedor.getSelectedItem() +
                                " | Produto: " + cbProduto.getSelectedItem() +
                                " | Qtd: " + quantidade +
                                " | Tipo: " + cbTipoMov.getSelectedItem();

                writer.write(registro);
                writer.newLine();

                JOptionPane.showMessageDialog(this, "Salvo com sucesso!");

                txtQuantidade.setText("");
                txtData.setValue(null);
            }

        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void exibirRegistros() {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(ARQUIVO_NOME)));
            JOptionPane.showMessageDialog(this, conteudo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Arquivo não encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioApp app = new FormularioApp();
            app.setVisible(true);
        });
    }
}