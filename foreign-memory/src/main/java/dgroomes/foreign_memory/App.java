package dgroomes.foreign_memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.Random;

/**
 * See the README for more information.
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static final int ELEMENT_COUNT = 1000;

    public static void main(String[] args) {
        log.info("Let's explore the Java foreign memory API!");
        new App().run();
    }

    public void run() {
        // An arena is like a managed container of off-heap memory. It will be automatically freed after the try block
        // ends thanks to the 'try-with-resources' statement.
        try (Arena arena = Arena.ofConfined()) {
            // We're going to create arbitrary numbers, but they don't need to be random between runs, so we'll hardcode
            // a seed.
            Random random = new Random(1);

            // Allocate memory that matches the shape of an array of N integers. (how does this
            MemorySegment memorySegment = arena.allocateArray(ValueLayout.JAVA_INT, ELEMENT_COUNT);

            // Write arbitrary integers into the memory segment.
            for (int i = 0; i < ELEMENT_COUNT; i++) {
                memorySegment.set(ValueLayout.JAVA_INT, i * Integer.BYTES, random.nextInt());
            }

            // Read a sample of integers from the memory segment.
            log.info("Sample data: ");
            for (int i = 0; i < 10; i++) {
                var num = memorySegment.get(ValueLayout.JAVA_INT, (long) (i) * Integer.BYTES);
                log.info("\t: {}", num);
            }
        }
    }
}



