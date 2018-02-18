package pnodder.validation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pnodder.model.Booking;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class BookingTest {


    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameIsNull() {
        Booking booking = new Booking();
        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        Assert.assertEquals(4, violations.size());
        //Assert.assertEquals("must not be null", violations.iterator().next().getMessage());
    }

}
