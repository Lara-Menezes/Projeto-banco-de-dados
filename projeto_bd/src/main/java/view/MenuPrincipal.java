package view;

import javax.swing.*;

import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema de Tarefas");
        setSize(400, 252);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton usuarioBtn = new JButton("Usuários");
        usuarioBtn.setBounds(114, 30, 143, 40);
        JButton categoriaBtn = new JButton("Categorias");
        categoriaBtn.setBounds(114, 81, 143, 40);
        JButton tarefaBtn = new JButton("Tarefas");
        tarefaBtn.setBounds(114, 132, 143, 40);
        getContentPane().setLayout(null);

        getContentPane().add(usuarioBtn);
        getContentPane().add(categoriaBtn);
        getContentPane().add(tarefaBtn);

        usuarioBtn.addActionListener(e -> {
        	dispose();
        	new UsuarioFrame().setVisible(true);
        });
        
        categoriaBtn.addActionListener(e -> {
        	dispose();
        	new CategoriaFrame().setVisible(true);
        });
        tarefaBtn.addActionListener(e -> {
        	dispose();
        	new TarefaFrame().setVisible(true);
        });
        
        setVisible(true);
    }
}

