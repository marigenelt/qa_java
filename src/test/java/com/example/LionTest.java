package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {

    @Mock
    Feline feline;
    Lion lion;
    private final String sex;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lion = new Lion(sex, feline);
    }

    public LionTest(String sex) {
        this.sex = sex;
    }

    @Parameterized.Parameters
    public static Object[][] getLionSex() {
        return new Object[][]{
                {"Самец"},
                {"Самка"},
        };
    }


    @Test
    public void getKittens() {
        int expectedKittensAmount = 1;
        when(feline.getKittens()).thenReturn(expectedKittensAmount);
        assertEquals(expectedKittensAmount, lion.getKittens());
    }


    @Test
    public void doesHaveMane() {
        if ("Самец".equals(sex)) {
            assertTrue("У самца есть грива", lion.doesHaveMane());
        } else if ("Самка".equals(sex)) {
            assertFalse("У самки нет гривы", lion.doesHaveMane());
        }
    }

    @Test
    public void getFood() throws Exception {
        List<String> expectedFood = List.of("Яблоки", "Оливки", "Чипсы");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void lionGetFoodDependsOnFelineGetFood() throws Exception {
        lion.getFood();
        verify(feline, times(1)).getFood("Хищник");
    }


}