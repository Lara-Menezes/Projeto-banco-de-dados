package view;

import controller.CategoriaController;

import javax.swing.*;
import java.awt.*;

public class CategoriaFrame extends JFrame {

    private JTextField nomeField;
    private JLabel mensagemLabel;
    private final CategoriaController controller = new CategoriaController();

    public CategoriaFrame() {
        setTitle("Cadastro de Categoria");
        setSize(400, 258);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nomeField = new JTextField();
        nomeField.setBounds(207, 10, 151, 20);
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(30, 67, 131, 40);
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(30, 118, 131, 40);
        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setBounds(182, 67, 192, 40);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Nome da Categoria:");
        label.setBounds(10, 8, 121, 25);
        getContentPane().add(label);
        getContentPane().add(nomeField);
        getContentPane().add(salvarButton);
        getContentPane().add(mensagemLabel);
        getContentPane().add(voltarButton);

        salvarButton.addActionListener(e -> salvarCategoria());
        voltarButton.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });
    }

    private void salvarCategoria() {
        String nome = nomeField.getText();
        String mensagem = controller.salvarCategoria(nome);

        mensagemLabel.setText(mensagem);

        if (mensagem.startsWith("Categoria cadastrada")) {
            nomeField.setText("");
        }
    }
}
