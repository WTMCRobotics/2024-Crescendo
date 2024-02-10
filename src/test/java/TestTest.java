import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestTest {

    @Nested
    class BasicGetValuesTest {

        @BeforeEach
        void setup() {
            System.out.println("dsf");
        }

        @Test
        public void testTest() {}

        @Test
        public void booleanTest() {
            assertEquals(true, !false);
        }

        @Test
        public void intTest() {
            assertEquals(7, (int) 7.0);
        }

        @Test
        public void nullTest() {
            assertNull(null);
        }
    }
}
