package atividade1;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;


/**
 *  Repositório para o banco de dados dos clientes
 */
public class ClieteRepository{
    private static Database database;
    private static Dao<Cliente, Integer> dao;
    private List<Cliente> loadedClientes;
    private Cliente loadedCliente; 
    // Construtores
    public ClieteRepository(Database database) {
        ClieteRepository.setDatabase(database);
        loadedClientes = new ArrayList<Cliente>();
    }
    /**
     * Seleciona o banco de dados sobre qual o repositorio vai operar
     * 
     * @param database Banco de dados que deseja atribuir ao repositório 
     */
    public static void setDatabase(Database database) {
        ClieteRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Cliente.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Cliente.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    /**
     *  Adiciona uma nova instância de Cliente ao banco de dados
     * 
     * @param cliente Instância do tipo Cliente
     * @return Retorna o mesmo cliente que foi dado no parâmetro, porém com o seu ID definido
     */
    public Cliente create(Cliente cliente) {
        int nrows = 0;
        try {
            nrows = dao.create(cliente);
            if ( nrows == 0 )
                throw new SQLException("Error: object not saved");
            this.loadedCliente = cliente;
            loadedClientes.add(cliente);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cliente;
    }    
    
    
    
    
    
    public List<Cliente> loadAll() {
        try {
            this.loadedClientes =  dao.queryForAll();
            if (!this.loadedClientes.isEmpty())
                this.loadedClientes = (List<Cliente>) this.loadedClientes.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedClientes;
    }
    /**
     * Atualiza o Cliente existente no banco de dados que possui o mesmo ID que o do parâmetro
     * 
     * @param cliente Instância do tipo Cliente
     */
    public void update(Cliente cliente) {
      try{
          dao.update(cliente);
      }catch (SQLException e){
          System.out.println("Não foi possível executar a atualização.");
      }
    }
    /**
     * Deleta o Cliente existente no banco de dados que possui o mesmo ID que o do parâmetro
     * 
     * @param cliente Instância do tipo Cliente
     */
    public void delete(Cliente cliente) {
      try{
          dao.delete(cliente);
      } catch (SQLException e){
          System.out.println("Não foi possível executar a exclusão.");
      }
    }
    
     
}