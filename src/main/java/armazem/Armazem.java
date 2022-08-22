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

    public TreeMap<Ingrediente, Integer> getEstoque() {
        return estoque;
    }

    public static void main(String[] args) {

        Armazem ss = new Armazem();
        Base sorvete = new Base(TipoBase.SORVETE);


        ss.cadastrarIngredienteEmEstoque(sorvete);
        System.out.println(ss.getEstoque().get(sorvete));

    }


}
