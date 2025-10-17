package view.controller;

import controller.UsuarioController;
import dto.UsuarioDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Usuario;
import view.ScreenManager; // importante para trocar de telas

import java.util.List;

public class UsuarioFXController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;
    @FXML private Label lblMensagem;
    @FXML private TextField txtId;
    @FXML private Button btnAtualizar;
    @FXML private Button btnListar;
    @FXML private Button btnlimpar;
    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Long> colId;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colEmail;

    private final UsuarioController usuarioController = new UsuarioController();
    
    private Integer usuarioSelecionadoId = null;
    private Usuario usuarioSelecionado = null;


    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        btnSalvar.setOnAction(e -> salvarUsuario());
        btnVoltar.setOnAction(this::voltar);
        btnListar.setOnAction(e -> listar());
        btnlimpar.setOnAction(e -> limparCampos());
        
        configurarTabela();
        listar();
        tabelaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
    
    private void configurarTabela() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tabelaUsuarios.setOnMouseClicked(event -> {
            Usuario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                preencherCampos(selecionado);
                usuarioSelecionado = selecionado;
                usuarioSelecionadoId = selecionado.getId();
            }
        });
    }

    private void salvarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();

        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            lblMensagem.setText("Preencha todos os campos!");
            lblMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(nome);
        dto.setEmail(email);

        String resultado = usuarioController.salvarUsuario(dto);

        lblMensagem.setText(resultado);
        lblMensagem.setStyle(resultado.contains("sucesso") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

        if (resultado.contains("sucesso")) {
            txtNome.clear();
            txtEmail.clear();
            listar();
        }
    }
    
    @FXML
    private void deletar(){
    	if (usuarioSelecionadoId == null) {
            lblMensagem.setText("Selecione um usuário na tabela para deletar.");
            lblMensagem.setStyle("-fx-text-fill: red;");
            return;
        }
        
    	String resultado = usuarioController.deletarUsuario(usuarioSelecionadoId);
    	lblMensagem.setText(resultado);
    	
    	if (resultado.contains("sucesso")) {
    		listar();
            limparCampos();
        }
    }
    
    @FXML
    private void atualizar(ActionEvent event) {
        if (usuarioSelecionado == null) {
            lblMensagem.setText("Selecione um usuário na tabela para atualizar.");
            lblMensagem.setStyle("-fx-text-fill: red;");
            return;
        }
        
        String nome = txtNome.getText();
        String email = txtEmail.getText();

        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            lblMensagem.setText("Preencha todos os campos!");
            lblMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(nome);
        dto.setEmail(email);

        String resultado = usuarioController.atualizarUsuario(usuarioSelecionado.getId(), dto);

        lblMensagem.setText(resultado);
        lblMensagem.setStyle(resultado.contains("sucesso") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

        if (resultado.contains("sucesso")) {
        	listar();
            limparCampos();
        }
    }

    @FXML
    private void listar() {
        List<Usuario> usuarios = usuarioController.ListarUsuarios();
        tabelaUsuarios.setItems(FXCollections.observableArrayList(usuarios));
    }
    
    private void preencherCampos(Usuario usuario) {
        usuarioSelecionado = usuario;
        txtNome.setText(usuario.getNome());
        txtEmail.setText(usuario.getEmail());
    }
    
    private void limparCampos() {
        txtNome.clear();
        txtEmail.clear();
        txtId.clear();
        
        tabelaUsuarios.getSelectionModel().clearSelection();

        usuarioSelecionado = null;
        usuarioSelecionadoId = null;

        listar();
    }

    private void voltar(ActionEvent event) {
        try {
            ScreenManager.changeScene("menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            lblMensagem.setText("Erro ao carregar tela de menu!");
        }
    }
}
