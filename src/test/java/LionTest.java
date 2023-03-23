import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    private Feline feline;

    @Test
    public void lionExceptionTest() {
        String expectedException = "Используйте допустимые значения пола животного - самец или самка";
        Exception actualException = assertThrows("Текст исключения",
                Exception.class,()-> new Lion("", new Feline()));
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
        String expectedText = "Используйте допустимые значения пола животного - самец или самка";
        Exception exception = null;
        try {
            new Lion(null, feline);
        } catch (Exception lionSex) {
            exception = lionSex;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals(expectedText, exception.getMessage());
    }

    @RunWith(Parameterized.class)
    public static class LionParameterizedTest {
        private String lionSex;
        private boolean hasManeExpected;

        @Mock
        Feline feline;

        public LionParameterizedTest(String lionSex, boolean hasManeExpected){
            this.lionSex = lionSex;
            this.hasManeExpected = hasManeExpected;
        }
        @Before
        public void SetUp() {
            MockitoAnnotations.initMocks(this);
        }
        @Parameterized.Parameters
        public static Object[][] getDataForm() {
            return new Object[][] {
                    {"Самец", true},
                    {"Самка", false},
            };
        }

        @Test
        public void maleHaveManyReturnsExpectedValue() throws Exception {
            Lion lion = new Lion(lionSex, feline);
            Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка",
                    hasManeExpected, lion.doesHaveMane());

        }

    }
}