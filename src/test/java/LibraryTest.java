import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class LibraryTest {

    @Test
    public void testSomeLibraryMethod() {

        Collection<TestClass> test = Arrays.asList(
                new TestClass(1, 1),
                new TestClass(1, 2),
                new TestClass(1, 3),
                new TestClass(1, 4),
                new TestClass(1, 5),
                new TestClass(2, 6),
                new TestClass(2, 7),
                new TestClass(2, 8),
                new TestClass(2, 9),
                new TestClass(2, 10),
                new TestClass(2, 11)
        );

        Collection<TestClass> test2 = Arrays.asList(
                new TestClass(1, 10),
                new TestClass(1, 20),
                new TestClass(1, 30),
                new TestClass(1, 40),
                new TestClass(1, 50),
                new TestClass(2, 60),
                new TestClass(2, 70),
                new TestClass(2, 80),
                new TestClass(2, 90),
                new TestClass(2, 100),
                new TestClass(2, 110)
        );

        //test1.join(test2 on x)

        //



//        double sum = query(test).mapn(x -> x.getX()).avg();

        //List<Tuple<Test, Test>> result = query(test).join((x, y) -> x.getX() == y.getX(), test2).toList();

        System.out.println("asd");
    }
}

class TestClass {
    private Integer x;
    private Integer y;

    public TestClass(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
