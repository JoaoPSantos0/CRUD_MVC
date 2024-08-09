package atividade1;

import com.j256.ormlite.field.DatabaseField;

/**
 *  POJO para o banco de dados dos clientes
 */
public class Cliente{
    // Atributos da classe dos clientes. Novos atributos podem ser adicionados, contanto que a adaptação seja feita no código
    // abaixo e que um novo banco de dados seja criado. Adicionei somente alguns para teste.
   
    @DatabaseField
    private String nome;
    

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    
}