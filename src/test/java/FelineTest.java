
import com.example.Feline;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {
    @Spy
    private Feline feline;

    @Test
    public void listOfMeatIsCorrect() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    public void belongsFamilyFeline() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void getKittensReturnsCorrectFixNumber() {
        int expectedNumber = feline.getKittens();
        int actualNumber = 1;
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getKittensReturnsAnyCorrectFixNumber() {
        int expected = 99;
        int actual = feline.getKittens(99);
        assertEquals(expected, actual);
    }

}