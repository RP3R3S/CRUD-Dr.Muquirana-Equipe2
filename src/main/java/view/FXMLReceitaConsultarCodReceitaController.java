package view;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;  
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.ReceitaVO;
import model.vo.UsuarioVO;
import javafx.scene.control.TextField;

import java.io.IOException;

import controller.ControladoraReceita;
import controller.ControladoraUsuario;
import javafx.event.ActionEvent;

public class FXMLReceitaConsultarCodReceitaController {
	@FXML
	private Button btnUsuario;
	@FXML
	private Button btnReceita;
	@FXML
	private Button btnDespesa;
	@FXML
	private Button btnRelatorio;
	@FXML
	private Button brnSobre;
	@FXML
	private Button btnVoltar;
	@FXML
	private Button btnConsultarReceitaPorCod;
	@FXML
	private TextField cmbCodigo;
	@FXML
	private Text lblCodReceita;
	@FXML
	private Text lblNomeUsuario;
	@FXML
	private Text lblCodUsuario;
	@FXML
	private Text lblDescricao;
	@FXML
	private Text txtCodUsuario;
	@FXML
	private Text txtDescricao;
	@FXML
	private Text txtNomeUsuario;
	@FXML
	private Text lblData;
	@FXML
	private Text txtData;
	@FXML
	private Text lblValor;
	@FXML
	private Text txtValor;

	// Event Listener on Button[#btnUsuario].onAction
	@FXML
	public void loadUsuario(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLUsuario.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
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
	// Event Listener on Button[#btnVoltar].onAction
	@FXML
	public void voltarReceita(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLReceita.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on Button[#btnConsultarReceitaPorCod].onAction
	@FXML
	public void consultarReceitaPorCodigo(ActionEvent event) {
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Locale ptBr = new Locale("pt", "BR");
		
		ReceitaVO receitaVO = new ReceitaVO();
		
		receitaVO.setId(Integer.parseInt(cmbCodigo.getText())); 
		ControladoraReceita controladoraReceita = new ControladoraReceita();
		ReceitaVO idReceita = controladoraReceita.consultarReceitaController(receitaVO);
		
		UsuarioVO usuario = new UsuarioVO();
		
		usuario.setIdUsuario(idReceita.getIdUsuario());
		
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		usuario = controladoraUsuario.consultarUsuarioCOntroller(usuario);
		
		txtCodUsuario.setText(Integer.toString(idReceita.getIdUsuario()));
		txtNomeUsuario.setText(usuario.getNome());
		txtDescricao.setText(idReceita.getDescricao());
		txtData.setText(idReceita.getDateReceita().format(dataFormatter));
		txtValor.setText(NumberFormat.getCurrencyInstance(ptBr).format(idReceita.getValor()));

		
	}
}
