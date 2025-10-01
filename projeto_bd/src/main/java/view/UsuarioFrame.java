package view;

import controller.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class UsuarioFrame extends JFrame {

    private JTextField nomeField;
    private JTextField emailField;
    private JLabel mensagemLabel;
    private final UsuarioController controller = new UsuarioController();

    public UsuarioFrame() {
        setTitle("Cadastro de Usuário");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nomeField = new JTextField();
        nomeField.setBounds(192, 0, 192, 35);
        emailField = new JTextField();
        emailField.setBounds(192, 35, 192, 35);
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(20, 94, 192, 35);
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(20, 140, 192, 35);
        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setBounds(192, 68, 192, 35);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Nome:");
        label.setBounds(0, 0, 192, 35);
        getContentPane().add(label);
        getContentPane().add(nomeField);
        JLabel label_1 = new JLabel("Email:");
        label_1.setBounds(0, 35, 192, 35);
        getContentPane().add(label_1);
        getContentPane().add(emailField);
        getContentPane().add(salvarButton);
        getContentPane().add(mensagemLabel);
        getContentPane().add(voltarButton);

        salvarButton.addActionListener(e -> salvarUsuario());
        voltarButton.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });
    }

    private void salvarUsuario() {
        String nome = nomeField.getText();
        String email = emailField.getText();

        String mensagem = controller.salvarUsuario(nome, email);
        mensagemLabel.setText(mensagem);

        if (mensagem.startsWith("Usuário cadastrado")) {
            nomeField.setText("");
            emailField.setText("");
        }
    }
}
