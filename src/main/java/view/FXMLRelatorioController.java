package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.dto.LancamentoUsuarioDTO;
import model.dto.SaldoUsuarioDTO;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table; 
import com.itextpdf.layout.property.TextAlignment;  
//import com.itextpdf.text.pdf.TextField;
import com.itextpdf.kernel.color.Color; 
import com.itextpdf.kernel.pdf.PdfDocument; 


import com.itextpdf.layout.border.Border; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table; 
import com.itextpdf.layout.property.TextAlignment;  



import controller.ControladoraRelatorio;
import javafx.event.ActionEvent;




public class FXMLRelatorioController {
	DateTimeFormatter formataDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
	private Button btnTotalReceitas;
	@FXML
	private DatePicker txtInicial;
	@FXML
	private DatePicker txtFinal;
	

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
		public void loadCategoria(ActionEvent event) throws IOException {
			Parent parent = FXMLLoader.load(getClass().getResource("FXMLCategoria.fxml"));
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
		Parent parent = FXMLLoader.load(getClass().getResource("FXMLDespesa.fxml"));
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
	// Event Listener on Button[#btnTotalReceitas].onAction
	@FXML
	public void totalReceitas(ActionEvent event) {
		//converter double para Reais
		Locale ptBr = new Locale("pt", "BR");
				
		/* O itext ao gerar um pdf com mesmo nome de arquivo simplesmente substitui,
		* para resolver isso utiliza-se a hora do computador para botar no fim do no
		* do arquivo para cada arquivo ser unico pelo contexto necessario.
		*/
		String idArquivo = String.valueOf(System.currentTimeMillis());
		String nomeArquivo = "Receitas_"+idArquivo+".pdf";
		
		
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentosUsuarioDTO = controladoraRelatorio.gerarRelatorioTotalReceitasUsuarioController();
				
		// Cria documento com o tamanho desejado
		Document document = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
			

			document.addTitle("Total Receitas");
			document.open();
			// cria padrão de fonte para título
			Font f = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLD);
			Paragraph h1 = new Paragraph("Dr. Muquirana - Relatório", f); 
			Paragraph vazio = new Paragraph(" "); 
			Paragraph h2 = new Paragraph("TOTAL DE RECEITAS");
			//centraliza cabeçalho 
			h1.setAlignment(Element.ALIGN_CENTER);
			h2.setAlignment(Element.ALIGN_CENTER);
			document.add(h1);
			document.add(vazio);
			document.add(h2);
			// cria tabela
			// cria cabecalho da table
		    PdfPTable tabela = new PdfPTable(3);
		    tabela.setWidthPercentage(400 / 5.23f);
		    tabela.setWidths(new int[]{1, 3, 2});
		    

		    
		    // insere titulo da tabela
		    //Paragraph t1 = new Paragraph("Table 1");
		    PdfPCell cell;

	        document.add(vazio);
	        document.add(vazio);
	        document.add(vazio);
	        
	        Font boldBranca = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

	        
	        cell = new PdfPCell();
	        cell.setColspan(3);
//	        cell.setRowspan(2);
	        cell = new PdfPCell(new Paragraph("  ID", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Nome", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Total Receitas", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        			// preenche celulas da tabela
			for(int i = 0; i < listaLancamentosUsuarioDTO.size(); i++) {
		        cell = new PdfPCell(new Paragraph(" "+String.valueOf(listaLancamentosUsuarioDTO.get(i).getIdUsuario())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+listaLancamentosUsuarioDTO.get(i).getNome()));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaLancamentosUsuarioDTO.get(i).getValor())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
			}
			document.add(tabela);
			
			document.add(vazio);

			document.add(vazio);
			Paragraph bottom = new Paragraph("Total de linhas: "+ listaLancamentosUsuarioDTO.size() + ".");
			bottom.setAlignment(Element.ALIGN_CENTER);
			Paragraph bottom2 = new Paragraph(" ");
			bottom2.setAlignment(Element.ALIGN_CENTER);
			document.add(bottom);
			document.add(bottom2);
			
			
			
			
			//gera aviso que o pdf foi gerado.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Relatório - Dr. Muquirana");
			alert.setHeaderText("Arquivo "+".pdf Gerado.");
			alert.setContentText("Operação bem sucedida");

			alert.showAndWait();
			
		}
		catch(Exception e) {
			// AtualizarMensagem Erro			
			System.out.println(e);
		}
		document.close();
		

	}
	
	public void totalDespesas(ActionEvent event) {
		
		//converter double para Reais
		Locale ptBr = new Locale("pt", "BR");
				
		/* O itext ao gerar um pdf com mesmo nome de arquivo simplesmente substitui,
		* para resolver isso utiliza-se a hora do computador para botar no fim do no
		* do arquivo para cada arquivo ser unico pelo contexto necessario.
		*/
		String idArquivo = String.valueOf(System.currentTimeMillis());
		String nomeArquivo = "Despesas_"+idArquivo+".pdf";
		
		
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentosUsuarioDTO = controladoraRelatorio.gerarRelatorioTotalDespesasUsuarioController();
				
		// Cria documento com o tamanho desejado
		Document document = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
			

			document.addTitle("Total Despesas");
			document.open();
			// cria padrão de fonte para título
			Font f = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLD);
			Paragraph h1 = new Paragraph("Dr. Muquirana - Relatório", f); 
			Paragraph vazio = new Paragraph(" "); 
			Paragraph h2 = new Paragraph("TOTAL DE DESPESAS");
			//centraliza cabeçalho 
			h1.setAlignment(Element.ALIGN_CENTER);
			h2.setAlignment(Element.ALIGN_CENTER);
			document.add(h1);
			document.add(vazio);
			document.add(h2);
			// cria tabela
			// cria cabecalho da table
		    PdfPTable tabela = new PdfPTable(3);
		    tabela.setWidthPercentage(400 / 5.23f);
		    tabela.setWidths(new int[]{1, 3, 2});
		    

		    
		    // insere titulo da tabela
		    //Paragraph t1 = new Paragraph("Table 1");
		    PdfPCell cell;

	        document.add(vazio);
	        document.add(vazio);
	        document.add(vazio);
	        
	        Font boldBranca = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

	        
	        cell = new PdfPCell();
	        cell.setColspan(3);
//	        cell.setRowspan(2);
	        cell = new PdfPCell(new Paragraph("  ID", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Nome", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Total Despesas", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        			// preenche celulas da tabela
			for(int i = 0; i < listaLancamentosUsuarioDTO.size(); i++) {
		        cell = new PdfPCell(new Paragraph(" "+String.valueOf(listaLancamentosUsuarioDTO.get(i).getIdUsuario())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+listaLancamentosUsuarioDTO.get(i).getNome()));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaLancamentosUsuarioDTO.get(i).getValor())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
			}
			document.add(tabela);
			
			document.add(vazio);

			document.add(vazio);
			Paragraph bottom = new Paragraph("Total de linhas: "+ listaLancamentosUsuarioDTO.size() + ".");
			bottom.setAlignment(Element.ALIGN_CENTER);
			Paragraph bottom2 = new Paragraph(" ");
			bottom2.setAlignment(Element.ALIGN_CENTER);
			document.add(bottom);
			document.add(bottom2);
			
			
			
			
			//gera aviso que o pdf foi gerado.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Relatório - Dr. Muquirana");
			alert.setHeaderText("Arquivo "+".pdf Gerado.");
			alert.setContentText("Operação bem sucedida");

			alert.showAndWait();
			
		}
		catch(Exception e) {
			// AtualizarMensagem Erro			
			System.out.println(e);
		}
		document.close();
		

	}
	
	public void saldoTotal(ActionEvent event) {
		
		//converter double para Reais
		Locale ptBr = new Locale("pt", "BR");
				
		/* O itext ao gerar um pdf com mesmo nome de arquivo simplesmente substitui,
		* para resolver isso utiliza-se a hora do computador para botar no fim do no
		* do arquivo para cada arquivo ser unico pelo contexto necessario.
		*/
		String idArquivo = String.valueOf(System.currentTimeMillis());
		String nomeArquivo = "SaldoTotal_"+idArquivo+".pdf";
		
		
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		
		ArrayList<SaldoUsuarioDTO> listaSaldoDTO = controladoraRelatorio.gerarRelatorioSaldoTotalController();
				
		// Cria documento com o tamanho desejado
		Document document = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
			

			document.addTitle("Saldo Total");
			document.open();
			// cria padrão de fonte para título
			Font f = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLD);
			Paragraph h1 = new Paragraph("Dr. Muquirana - Relatório", f); 
			Paragraph vazio = new Paragraph(" "); 
			Paragraph h2 = new Paragraph("SALDO TOTAL");
			//centraliza cabeçalho 
			h1.setAlignment(Element.ALIGN_CENTER);
			h2.setAlignment(Element.ALIGN_CENTER);
			document.add(h1);
			document.add(vazio);
			document.add(h2);
			// cria tabela
			// cria cabecalho da table
		    PdfPTable tabela = new PdfPTable(5);
		    tabela.setWidthPercentage(500 / 5.23f);
		    tabela.setWidths(new int[]{1, 3, 2, 2, 2});
		    

		    
		    // insere titulo da tabela
		    //Paragraph t1 = new Paragraph("Table 1");
		    PdfPCell cell;

	        document.add(vazio);
	        document.add(vazio);
	        document.add(vazio);
	        
	        Font boldBranca = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

	        
	        cell = new PdfPCell();
	        cell.setColspan(3);
//	        cell.setRowspan(2);
	        cell = new PdfPCell(new Paragraph("  ID", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Nome", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Total Receitas", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Total Despesas", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Saldo Final", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        			// preenche celulas da tabela
			for(int i = 0; i < listaSaldoDTO.size(); i++) {
		        cell = new PdfPCell(new Paragraph(" "+String.valueOf(listaSaldoDTO.get(i).getId())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+listaSaldoDTO.get(i).getNome()));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaSaldoDTO.get(i).getTotalReceita())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaSaldoDTO.get(i).getTotalDespesa())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaSaldoDTO.get(i).getTotalSaldo())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        
		        tabela.addCell(cell);
			}
			document.add(tabela);
			
			document.add(vazio);

			document.add(vazio);
			Paragraph bottom = new Paragraph("Total de linhas: "+ listaSaldoDTO.size() + ".");
			bottom.setAlignment(Element.ALIGN_CENTER);
			Paragraph bottom2 = new Paragraph(" ");
			bottom2.setAlignment(Element.ALIGN_CENTER);
			document.add(bottom);
			document.add(bottom2);
			
			
			
			
			//gera aviso que o pdf foi gerado.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Relatório - Dr. Muquirana");
			alert.setHeaderText("Arquivo "+nomeArquivo+" gerado.");
			alert.setContentText("Operação bem sucedida");

			alert.showAndWait();
			
		}
		catch(Exception e) {
			// AtualizarMensagem Erro			
			System.out.println(e);
		}
		document.close();
		

	}
	
	public void parcialReceitas(ActionEvent event) {
		
		//converter double para Reais
		Locale ptBr = new Locale("pt", "BR");
				
		/* O itext ao gerar um pdf com mesmo nome de arquivo simplesmente substitui,
		* para resolver isso utiliza-se a hora do computador para botar no fim do no
		* do arquivo para cada arquivo ser unico pelo contexto necessario.
		*/
		String idArquivo = String.valueOf(System.currentTimeMillis());
		String nomeArquivo = "ParcialReceitas_"+idArquivo+".pdf";
		
		
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		LancamentoUsuarioDTO lancamentoUsuarioDTO = new LancamentoUsuarioDTO();
		lancamentoUsuarioDTO.setDataInicioPesquisa(txtInicial.getValue());
		
		lancamentoUsuarioDTO.setDataFimPesquisa(txtFinal.getValue());
		
		ArrayList<LancamentoUsuarioDTO> listaLancamentosUsuarioDTO = controladoraRelatorio.gerarRelatorioTotalReceitasUsuariosPorPeriodoController(lancamentoUsuarioDTO);
		
				
		// Cria documento com o tamanho desejado
		Document document = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
			

			document.addTitle("Total Receitas por período.");
			document.open();
			// cria padrão de fonte para título
			Font f = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLD);
			Paragraph h1 = new Paragraph("Dr. Muquirana - Relatório", f); 
			Paragraph vazio = new Paragraph(" "); 
			Paragraph h2 = new Paragraph("TOTAL DE RECEITAS POR PERÍODO");
			//centraliza cabeçalho 
			h1.setAlignment(Element.ALIGN_CENTER);
			h2.setAlignment(Element.ALIGN_CENTER);
			document.add(h1);
			document.add(vazio);
			document.add(h2);
			Paragraph h3 = new Paragraph("Período de " + txtInicial.getValue().format(formataDate) + " a "+txtFinal.getValue().format(formataDate)+".");
			h3.setAlignment(Element.ALIGN_CENTER);
			document.add(h3);
			// cria tabela
			// cria cabecalho da table
		    PdfPTable tabela = new PdfPTable(3);
		    tabela.setWidthPercentage(500 / 5.23f);
		    tabela.setWidths(new int[]{1, 3, 2});
		    

		    
		    // insere titulo da tabela
		    //Paragraph t1 = new Paragraph("Table 1");
		    PdfPCell cell;

	        document.add(vazio);
	        document.add(vazio);
	        document.add(vazio);
	        
	        Font boldBranca = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

	        
	        cell = new PdfPCell();
	        cell.setColspan(3);
//	        cell.setRowspan(2);
	        cell = new PdfPCell(new Paragraph("  ID", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Nome", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Receitas Período", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);

	        			// preenche celulas da tabela
			for(int i = 0; i < listaLancamentosUsuarioDTO.size(); i++) {
		        cell = new PdfPCell(new Paragraph(" "+String.valueOf(listaLancamentosUsuarioDTO.get(i).getIdUsuario())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+listaLancamentosUsuarioDTO.get(i).getNome()));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaLancamentosUsuarioDTO.get(i).getValor())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);

		        
			}
			document.add(tabela);
			
			document.add(vazio);

			document.add(vazio);
			Paragraph bottom = new Paragraph("Total de linhas: "+ listaLancamentosUsuarioDTO.size() + ".");
			bottom.setAlignment(Element.ALIGN_CENTER);
			Paragraph bottom2 = new Paragraph(" ");
			bottom2.setAlignment(Element.ALIGN_CENTER);
			document.add(bottom);
			document.add(bottom2);
			
			
			
			
			//gera aviso que o pdf foi gerado.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Relatório - Dr. Muquirana");
			alert.setHeaderText("Arquivo "+nomeArquivo+" gerado.");
			alert.setContentText("Operação bem sucedida");

			alert.showAndWait();
			
		}
		catch(Exception e) {
			// AtualizarMensagem Erro			
			System.out.println(e);
		}
		document.close();
		
	}
	
	public void parcialDespesas(ActionEvent event) {
		
		//converter double para Reais
		Locale ptBr = new Locale("pt", "BR");
				
		/* O itext ao gerar um pdf com mesmo nome de arquivo simplesmente substitui,
		* para resolver isso utiliza-se a hora do computador para botar no fim do no
		* do arquivo para cada arquivo ser unico pelo contexto necessario.
		*/
		String idArquivo = String.valueOf(System.currentTimeMillis());
		String nomeArquivo = "ParcialDespessa_"+idArquivo+".pdf";
		
		
		ControladoraRelatorio controladoraRelatorio = new ControladoraRelatorio();
		LancamentoUsuarioDTO lancamentoUsuarioDTO = new LancamentoUsuarioDTO();
		lancamentoUsuarioDTO.setDataInicioPesquisa(txtInicial.getValue());
		
		lancamentoUsuarioDTO.setDataFimPesquisa(txtFinal.getValue());
		
		ArrayList<LancamentoUsuarioDTO> listaLancamentosUsuarioDTO = controladoraRelatorio.gerarRelatorioTotalDespesasUsuariosPorPeriodoController(lancamentoUsuarioDTO);
		
				
		// Cria documento com o tamanho desejado
		Document document = new Document(PageSize.A4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
			

			document.addTitle("Total Despesas por período.");
			document.open();
			// cria padrão de fonte para título
			Font f = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLD);
			Paragraph h1 = new Paragraph("Dr. Muquirana - Relatório", f); 
			Paragraph vazio = new Paragraph(" "); 
			Paragraph h2 = new Paragraph("TOTAL DE DESPESAS POR PERÍODO");
			//centraliza cabeçalho 
			h1.setAlignment(Element.ALIGN_CENTER);
			h2.setAlignment(Element.ALIGN_CENTER);
			document.add(h1);
			document.add(vazio);
			document.add(h2);
			Paragraph h3 = new Paragraph("Período de " + txtInicial.getValue().format(formataDate) + " a "+txtFinal.getValue().format(formataDate)+".");
			h3.setAlignment(Element.ALIGN_CENTER);
			document.add(h3);
			// cria tabela
			// cria cabecalho da table
		    PdfPTable tabela = new PdfPTable(3);
		    tabela.setWidthPercentage(500 / 5.23f);
		    tabela.setWidths(new int[]{1, 3, 2});
		    

		    
		    // insere titulo da tabela
		    //Paragraph t1 = new Paragraph("Table 1");
		    PdfPCell cell;

	        document.add(vazio);
	        document.add(vazio);
	        document.add(vazio);
	        
	        Font boldBranca = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

	        
	        cell = new PdfPCell();
	        cell.setColspan(3);
//	        cell.setRowspan(2);
	        cell = new PdfPCell(new Paragraph("  ID", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Nome", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);
	        cell = new PdfPCell(new Paragraph("  Receitas Período", boldBranca));
	        cell.setBackgroundColor(BaseColor.BLACK);
	        tabela.addCell(cell);

	        			// preenche celulas da tabela
			for(int i = 0; i < listaLancamentosUsuarioDTO.size(); i++) {
		        cell = new PdfPCell(new Paragraph(" "+String.valueOf(listaLancamentosUsuarioDTO.get(i).getIdUsuario())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+listaLancamentosUsuarioDTO.get(i).getNome()));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);
		        cell = new PdfPCell(new Paragraph(" "+NumberFormat.getCurrencyInstance(ptBr).format(listaLancamentosUsuarioDTO.get(i).getValor())));
		        if (i %2 != 0 ) cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        tabela.addCell(cell);

		        
			}
			document.add(tabela);
			
			document.add(vazio);

			document.add(vazio);
			Paragraph bottom = new Paragraph("Total de linhas: "+ listaLancamentosUsuarioDTO.size() + ".");
			bottom.setAlignment(Element.ALIGN_CENTER);
			Paragraph bottom2 = new Paragraph(" ");
			bottom2.setAlignment(Element.ALIGN_CENTER);
			document.add(bottom);
			document.add(bottom2);
			
			
			
			
			//gera aviso que o pdf foi gerado.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Relatório - Dr. Muquirana");
			alert.setHeaderText("Arquivo "+".pdf gerado.");
			alert.setContentText("Operação bem sucedida");

			alert.showAndWait();
			
		}
		catch(Exception e) {
			// AtualizarMensagem Erro			
			System.out.println(e);
		}
		document.close();
		
	}
}
