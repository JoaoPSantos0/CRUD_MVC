
package atividade1;

import atividade1.ClieteRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author joaopedro
 */
public class InterfaceController implements Initializable {

    @FXML
    private TextField textNome;
    @FXML
    private Button btnAdc;
    @FXML
    private TableView<Cliente> tableView;
    @FXML
    private Button btnDeletar;
    @FXML
    private TableColumn<Cliente, String> nomeCol;
    
    
    Database database = new Database("databaseClientes");
    ClieteRepository clienteRP = new ClieteRepository(database);
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDados();
    }    
    
    ObservableList<Cliente> clientes;
    
    public void carregarDados(){
        clientes = FXCollections.observableArrayList(clienteRP.loadAll());
        nomeCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
        
        tableView.setItems(clientes);
    }
    
    @FXML
    @SuppressWarnings("empty-statement")
    public void buttonAdicionarClicked(){
    
        try{
            String name = textNome.getText();
     
            if( "".equals(name)){
                System.out.println("Dados inválidos ou incompletos!");;
            }
            else{
                database.getConnection();
                Cliente cliente = new Cliente(name);
                clienteRP .create(cliente);
                database.close();
                carregarDados();
                
            }
        } catch (Exception e){
            System.out.println("Erro");
        }
    }
    
    public void limparInfo(){
        
        textNome.setText("");
    }
    
    
}
