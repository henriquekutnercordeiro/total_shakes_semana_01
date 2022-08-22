package armazem;

import ingredientes.base.Base;
import ingredientes.base.TipoBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    Armazem armazem;

    @BeforeAll
    public void setup() {
        armazem = new Armazem();
    }

    @Test
    public void should_add__properly() {
        Base sorvete = new Base(TipoBase.SORVETE);
        armazem.cadastrarIngredienteEmEstoque(sorvete);
        assertEquals(1, armazem.getEstoque().size());
    }
}
