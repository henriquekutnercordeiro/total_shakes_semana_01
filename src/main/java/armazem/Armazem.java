package armazem;
import ingredientes.Ingrediente;
import ingredientes.base.Base;
import ingredientes.base.TipoBase;

import java.util.TreeMap;

public class Armazem {

    private TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    public void cadastrarIngredienteEmEstoque(Ingrediente ingredient) {

        if(estoque.containsKey(ingredient)){
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        } else {
            estoque.put(ingredient, 0);
        }
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente){
        if(!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente não encontrado");
        } else{
            estoque.remove(ingrediente);
        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade){
        if(!estoque.containsKey(ingrediente)) {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        } else if(quantidade > 0){
            int totalUpdated = estoque.get(ingrediente) + quantidade;
            estoque.put(ingrediente, totalUpdated);
        } else {
            throw new IllegalArgumentException("Quantidade invalida");
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade){
        int storeQuant = estoque.get(ingrediente);
        if(!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente não encontrado");
        } else if(quantidade <= 0){
            throw new IllegalArgumentException("Quantidade invalida");
        } else if(storeQuant == quantidade || storeQuant < quantidade) {
            estoque.remove(ingrediente);
            System.out.println("Ingrediente removido");
        } else {
            int totalUpdated = storeQuant - quantidade;
            estoque.put(ingrediente, totalUpdated);
        }

    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente){
        if(!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente não encontrado");
        } else {
            return estoque.get(ingrediente);
        }
    }


    // Getters
    public TreeMap<Ingrediente, Integer> getEstoque() {
        return estoque;
    }

    public static void main(String[] args) {

        Armazem myStore = new Armazem();
        Base sorvete = new Base(TipoBase.SORVETE);
        myStore.cadastrarIngredienteEmEstoque(sorvete);

        myStore.adicionarQuantidadeDoIngredienteEmEstoque(sorvete, 5);
        myStore.reduzirQuantidadeDoIngredienteEmEstoque(sorvete, 3);
        System.out.println(myStore.getEstoque());
        System.out.println(myStore.consultarQuantidadeDoIngredienteEmEstoque(sorvete));
    }


}
