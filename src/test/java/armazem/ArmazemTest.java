package armazem;

import ingredientes.base.Base;
import ingredientes.base.TipoBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.InvalidClassException;
import java.util.Optional;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    Armazem armazem;

    @BeforeAll
    public void setup() {
        armazem = new Armazem();
    }

    @Test
    public void should_add_ingredient() {
        Base sorvete = new Base(TipoBase.SORVETE);
        armazem.cadastrarIngredienteEmEstoque(sorvete);
        assertEquals(1, armazem.getEstoque().size());
    }

    @Test
    public void should_dispatch_exception_when_ingredient_not_found() {
        Base sorvete = new Base(TipoBase.SORVETE);
        armazem.cadastrarIngredienteEmEstoque(sorvete);
        armazem.descadastrarIngredienteEmEstoque(sorvete);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.descadastrarIngredienteEmEstoque(sorvete);
        });
        String expectedMessage = "Ingrediente não encontrado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
