package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.UsuarioVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import controller.ControladoraUsuario;
import javafx.event.ActionEvent;

public class FXMLUsuarioCadastroController {
	@FXML
	private Button btnCadastrarUsuario;
	@FXML
	private Button btnVoltar;
	@FXML
	private TextField txtSenha;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtLogin;
	@FXML
	private Text lblCPF;
	@FXML
	private Text lblTelefone;
	@FXML
	private Text lblLogin;
	@FXML
	private PasswordField pwSenha;
	@FXML
	private Text lblTitulo;
	@FXML
	private Label lblAviso;
	

	// Event Listener on Button.onAction
	@FXML
	public void loadUsuario(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLUsuario.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
		//loadConsultarTodos
	}
	// Event Listener on Button[#btnReceita].onAction
	@FXML
	public void loadReceita(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLReceita.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on Button[#btnDespesa].onAction
	@FXML
	public void loadDespesa(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLDespesa.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on Button[#btnRelatorio].onAction
	@FXML
	public void loadRelatorio(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLRelatorio.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on Button[#brnSobre].onAction
	@FXML
	public void loadSobre(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLSobre.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on Button[#btnCadastrarUsuario].onAction
	@FXML
	public void cadastrarUsuario(ActionEvent event) {
		UsuarioVO usuarioVO = new UsuarioVO();
		if (txtCPF.getText().equals("") || txtNome.getText().equals("") || txtLogin.getText().equals("") || pwSenha.getText().equals("") ){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erro - Dr. Muquirana");
			alert.setHeaderText("Erro ao cadastrar usuário.");
			alert.setContentText("Todos os campos devem ser preenchidos.");

			alert.showAndWait();
			
			return;
		}
		usuarioVO.setNome(txtNome.getText());
		usuarioVO.setCpf(txtCPF.getText());
		usuarioVO.setTelefone(txtTelefone.getText());
		usuarioVO.setLogin(txtLogin.getText());
		usuarioVO.setSenha(pwSenha.getText());
		
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		controladoraUsuario.cadastrarUsuarioController(usuarioVO);
	
		txtNome.setText("");
		txtCPF.setText("");
		txtTelefone.setText("");
		txtLogin.setText("");
		pwSenha.setText("");
		
		
	}
	
	public void voltarUsuario(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLUsuario.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
	}
}
