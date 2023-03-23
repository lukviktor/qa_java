import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class LionParameterizedTest {
    private String lionSex;
    private boolean hasManeExpected;

    @Mock
    Feline feline;

    public LionParameterizedTest(String lionSex, boolean hasManeExpected) {
        this.lionSex = lionSex;
        this.hasManeExpected = hasManeExpected;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getDataForm() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Test
    public void maleHaveManyReturnsExpectedValue() throws Exception {
        Lion lion = new Lion(lionSex, feline);
        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                hasManeExpected, lion.doesHaveMane());

    }
}