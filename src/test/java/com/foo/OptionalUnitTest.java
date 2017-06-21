package com.foo;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Java8 introduced a new class called Optional in java.util package.
 * It is used to represent a value is present or absent.
 * The main advantage is that no more too many null checks and NPE.
 */

public class OptionalUnitTest {
    @Test
    public void whenCreateEmptyOptional_thenCorrect(){
        Optional<String> empty = Optional.empty();
        //isPresent API is used to check if there is a value inside the Optional obejct.
        //A valye is present if and only if we have created Optional with a non-null value.
        assertFalse(empty.isPresent());
    }

    @Test
    public void isPresent_shouldBeTrue_whenNoEmptyOptionalIsCreated(){
        Optional<String> opt = Optional.of("Luke");
        assertTrue(opt.isPresent());
    }

    @Test
    public void shouldGiveNonNullWhenCreateNonNullableString(){
        String name = "Luke";
        Optional<String> opt = Optional.of(name);
        assertEquals("Optional[Luke]", opt.toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRaiseNPEIfTheAugumentPassedToOfIsNull(){
        String name = null;
        Optional<String> opt = Optional.of(name);
    }

    @Test
    public void ifPresentShouldPrintStringValueWhenStringIsNotEmpty(){
        String name = "Luke";
        Optional<String> opt = Optional.of(name);
        opt.ifPresent(name1-> System.out.println("name=" + name1));
    }

    @Test
    public void isPresentShouldNotPrintAnythingForEmptyStringAndNoExceptionShouldBeRaised(){
        Optional<String> opt = Optional.empty();
        opt.ifPresent(name -> System.out.println("name = " + name));
    }

    @Test
    public void whenOrElseWorks_thenCorrect(){
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("Luke");
        assertEquals("Luke", name);

        String notNullName = "John";
        String name1 = Optional.ofNullable(notNullName).orElse("Luke");
        assertEquals("John", name1);
        //when the wrapped value is not present then orElse is the same as orElseGet
        //however when the wrapped value is present then orElse will create a default value which is redundant.
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowsWorks_thenCorrect(){
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void shouldGetWrappedValueWithinOptionalObject(){
        Optional<String> opt = Optional.of("Luke");
        String name = opt.get();

        assertEquals("Luke", name);
    }

    @Test
    public void filter_shouldReturnAProperOptionalObjectWhenConditionIsMapped(){
        Integer year = 2017;
        Optional<Integer> yearOptional = Optional.of(year);
        Optional opt = yearOptional.filter(y->y == 2017);
        assertNotNull(opt);
    }

    class Modem{
        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        private Double price;
        public Modem(Double price) {
            this.price = price;
        }
    }
    private boolean isPriceInRange(final Modem modem){
        return Optional.ofNullable(modem)
                .map(Modem::getPrice)
                .filter(p->p>=10)
                .filter(p->p<15)
                .isPresent();

    }

    @Test
    public void priceShouldBeInRange(){
        Modem modem = new Modem(new Double(13));
        assertTrue(isPriceInRange(modem));
    }
}
