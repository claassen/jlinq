import com.claassen.jlinq.JLinqBase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JLinqBaseTest {

    private List<TestClass> makeItems() {
        return Arrays.asList(
                new TestClass(1, 2),
                new TestClass(2, 4),
                new TestClass(3, 6)
        );
    }

    @Test
    public void testToList() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        List<TestClass> itemsToList = base.toList();


        assertThat(itemsToList, hasItems(items.get(0), items.get(1), items.get(2)));
    }

    @Test
    public void testFirst() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        TestClass first = base.first();


        assertThat(first, is(items.get(0)));
    }

    @Test
    public void testLast() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        TestClass last = base.last();

        assertThat(last, is(items.get(2)));
    }

    @Test
    public void testReduce() {
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(0, (memo, item) -> memo + item.getX()), equalTo(6));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(1, (memo, item) -> memo + item.getX()), equalTo(7));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(0, (memo, item) -> memo + item.getY()), equalTo(12));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(1, (memo, item) -> memo + item.getY()), equalTo(13));
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

    class TestJLinqBaseImplementation<T> extends JLinqBase<T> {

        public TestJLinqBaseImplementation(List<T> items) {
            Iterator<T> iterator = items.iterator();

            setNext(() -> iterator.next());
            setHasNext(() -> iterator.hasNext());
        }
    }
}


