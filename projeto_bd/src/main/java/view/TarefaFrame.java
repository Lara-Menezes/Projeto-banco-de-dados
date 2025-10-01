package view;

import controller.TarefaController;
import model.Categoria;
import model.Usuario;
import service.UsuarioService;
import service.CategoriaService;

import javax.swing.*;
import java.awt.*;

public class TarefaFrame extends JFrame {

    private JTextField tituloField;
    private JTextField descricaoField;
    private JTextField prazoField;
    private JCheckBox concluidaCheck;
    private JComboBox<Usuario> usuarioCombo;
    private JComboBox<Categoria> categoriaCombo;
    private JLabel mensagemLabel;

    private final TarefaController controller = new TarefaController();
    private final UsuarioService usuarioService = new UsuarioService();
    private final CategoriaService categoriaService = new CategoriaService();

    public TarefaFrame() {
        setTitle("Cadastro de Tarefa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tituloField = new JTextField();
        tituloField.setBounds(242, 0, 242, 36);
        descricaoField = new JTextField();
        descricaoField.setBounds(242, 36, 242, 36);
        prazoField = new JTextField("yyyy-MM-dd");
        prazoField.setBounds(242, 72, 242, 36);
        concluidaCheck = new JCheckBox("Concluída");
        concluidaCheck.setBounds(10, 180, 242, 36);
        usuarioCombo = new JComboBox<>(usuarioService.listarUsuarios().toArray(new Usuario[0]));
        usuarioCombo.setBounds(242, 108, 242, 36);
        categoriaCombo = new JComboBox<>(categoriaService.listarCategorias().toArray(new Categoria[0]));
        categoriaCombo.setBounds(242, 144, 242, 36);
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(10, 230, 242, 36);
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 283, 242, 36);
        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setBounds(242, 216, 242, 36);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Título:");
        label.setBounds(0, 0, 242, 36);
        getContentPane().add(label); getContentPane().add(tituloField);
        JLabel label_1 = new JLabel("Descrição:");
        label_1.setBounds(0, 36, 242, 36);
        getContentPane().add(label_1); getContentPane().add(descricaoField);
        JLabel label_2 = new JLabel("Prazo:");
        label_2.setBounds(0, 72, 242, 36);
        getContentPane().add(label_2); getContentPane().add(prazoField);
        JLabel label_3 = new JLabel("Usuário:");
        label_3.setBounds(0, 108, 242, 36);
        getContentPane().add(label_3); getContentPane().add(usuarioCombo);
        JLabel label_4 = new JLabel("Categoria:");
        label_4.setBounds(0, 144, 242, 36);
        getContentPane().add(label_4); getContentPane().add(categoriaCombo);
        getContentPane().add(concluidaCheck); JLabel label_5 = new JLabel("");
 label_5.setBounds(242, 180, 242, 36);
 getContentPane().add(label_5);
        getContentPane().add(salvarButton); getContentPane().add(mensagemLabel);
        getContentPane().add(voltarButton);

        salvarButton.addActionListener(e -> salvarTarefa());
        voltarButton.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });
    }

    private void salvarTarefa() {
        String titulo = tituloField.getText();
        String descricao = descricaoField.getText();
        String prazo = prazoField.getText();
        boolean concluida = concluidaCheck.isSelected();
        Usuario usuario = (Usuario) usuarioCombo.getSelectedItem();
        Categoria categoria = (Categoria) categoriaCombo.getSelectedItem();

        String mensagem = controller.salvarTarefa(titulo, descricao, prazo, concluida, usuario, categoria);

        mensagemLabel.setText(mensagem);

        if (mensagem.startsWith("Tarefa cadastrada")) {
            tituloField.setText("");
            descricaoField.setText("");
            prazoField.setText("yyyy-MM-dd");
            concluidaCheck.setSelected(false);
        }
    }
}
