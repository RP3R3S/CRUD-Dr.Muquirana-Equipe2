package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.ReceitaVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import controller.ControladoraReceita;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;



public class FXMLReceitaAtualizarController {
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
	private TextField txtCodUsuario;
	@FXML
	private TextField txtDescricao;
	@FXML
	private Text lblCodUsuario;
	@FXML
	private Text lblDescricao;
	@FXML
	private Text lblData;
	@FXML
	private Text lblValor;
	@FXML
	private Button btnAtualizarReceita;
	@FXML
	private Button btnVoltar;
	@FXML
	private DatePicker txtData;
	@FXML
	private TextField txtValor;
	@FXML
	private Text lblCodReceita;
	@FXML
	private TextField txtCodReceita;

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
	// Event Listener on Button[#btnAtualizarReceita].onAction
	@FXML
	public void atualizarReceita(ActionEvent event) {
		ReceitaVO receitaVO = new ReceitaVO();
		
		if (txtCodReceita.getText().equals("") || txtCodUsuario.getText().equals("") || txtDescricao.getText().equals("") || txtData.getValue().equals(null) || txtValor.getText().equals("") ){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erro - Dr. Muquirana");
			alert.setHeaderText("Erro ao atualizar receita.");
			alert.setContentText("Todos os campos devem ser preenchidos.");

			alert.showAndWait();
			return;
		}
		
		receitaVO.setId(Integer.parseInt(txtCodReceita.getText())); 
		receitaVO.setIdUsuario(Integer.parseInt(txtCodUsuario.getText()));
		receitaVO.setDescricao(txtDescricao.getText());
		receitaVO.setDateReceita(txtData.getValue());
		receitaVO.setValor(Double.parseDouble(txtValor.getText()));
		
		ControladoraReceita controladoraReceita = new ControladoraReceita();
		controladoraReceita.atualizarReceitaController(receitaVO);
		
		// "limpa" os campos após a inserção dos dados
		txtCodReceita.setText("");
		txtCodUsuario.setText("");
		txtDescricao.setText("");
		txtData.setValue(null);
		txtValor.setText("");
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
}
