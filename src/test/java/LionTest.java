import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    private Feline feline;

    @Test
    public void lionExceptionTest() {
        String expectedException = "Используйте допустимые значения пола животного - самец или самка";
        Exception actualException = assertThrows("Текст исключения",
                Exception.class, () -> new Lion("", new Feline()));
        assertEquals("Неправильный текст исключения: ", expectedException, actualException.getMessage());
    }

    @Test
    public void getKittensReturnsExpectedValue() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        int expectedKittenCount = 1;

        assertEquals(expectedKittenCount, lion.getKittens());
    }

    @Test
    public void getFoodReturnsExpectedValue() throws Exception {

        Lion lion = new Lion("Самка", feline);
        Mockito.when(feline.getFood("Хищник"))
                .thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, lion.getFood());
    }

    @Test(expected = Exception.class)
    public void getExceptionOneGenderValue() throws Exception {
        new Lion(null, feline);
    }

    @Test
    public void getExceptionTwoGenderValue() {
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            new Lion(null, feline);
        });
        String expectedText = "Используйте допустимые значения пола животного - самец или самка";
        assertEquals(expectedText, exception.getMessage());
    }
}