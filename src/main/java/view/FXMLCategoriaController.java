package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.CategoriaVO;
import model.vo.UsuarioVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ControladoraCategoria;
import controller.ControladoraUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class FXMLCategoriaController implements Initializable{
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
	private Text lblTitulo;
	@FXML
	//
	private Text lblTitulo1;
	@FXML
	private TextField txtDescricao;
	@FXML
	private Button btnIncluir;
	@FXML
	private Button btnVoltar;
	@FXML
	private ComboBox cmbCodigo;
	@FXML
	private Text lblTitulo11;
	@FXML
	private Button btnExcluir;
	
	private List<CategoriaVO> listaCategoriaVO = new ArrayList();
	private List<String> listaCategoriaVOString = new ArrayList();
	private ObservableList<String> observableListCategorias;
	
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
	// Event Listener on Button[#btnIncluir].onAction
	@FXML
	public void incluirCategoria(ActionEvent event) throws IOException {
		CategoriaVO categoriaVO = new CategoriaVO();
		if (txtDescricao.getText().equals("")){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erro - Dr. Muquirana");
			alert.setHeaderText("Erro ao cadastrar categoria.");
			alert.setContentText("Descrição deve ser preenchida.");

			alert.showAndWait();
			
			return;
		}
		categoriaVO.setDescricao(txtDescricao.getText());

		
		ControladoraCategoria controladoraCategoria = new ControladoraCategoria();
		controladoraCategoria.cadastrarCategoriaController(categoriaVO);
	

		
		//atualiza ComboBOX
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLCategoria.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
	}
	// Event Listener on Button[#btnVoltar].onAction
	@FXML
	public void loadCadastrarDespesa(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLDespesaCadastro.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	// Event Listener on ComboBox[#cmbCodigo].onAction
	@FXML
	public void consultarCategoria(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnExcluir].onAction
	@FXML
	public void excluirCategoria(ActionEvent event) throws IOException {
		CategoriaVO categoriaVO = new CategoriaVO();

		
		categoriaVO.setDescricao((String) cmbCodigo.getValue());
		System.out.println(cmbCodigo.getValue());
		ControladoraCategoria controladoraCategoria = new ControladoraCategoria();
		controladoraCategoria.excluirCategoriaController(categoriaVO);
		
		//atualiza ComboBOX
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLCategoria.fxml"));
		Scene scene = new Scene(parent, 800, 600);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarComboBoxCategorias();
	}
	private void carregarComboBoxCategorias() {
	ControladoraCategoria controladoraCategoria = new ControladoraCategoria();
		
		listaCategoriaVO = controladoraCategoria.consultarTodosCategoriasController();
		for (int i = 0; i<listaCategoriaVO.size(); i++) {
			listaCategoriaVOString.add(listaCategoriaVO.get(i).getDescricao());
		}
		 
		observableListCategorias = FXCollections.observableArrayList(listaCategoriaVOString);
		//clnCod.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		
		cmbCodigo.setItems(observableListCategorias);

		
	}
}
