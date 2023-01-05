import java.lang.ref.Cleaner;

public class Array<T> {

    private static final Cleaner CLEANER = Cleaner.create();


    private final int length;

    public UnsafeMemory memory;

    public Array(int length) {
        this.length = length;
        this.memory = UnsafeMemory.create(length);
        CLEANER.register(this, new ArrayCleaner(memory));
    }


    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return memory.get(index);
    }

    public void set(int index, T value) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        memory.set(index, value);
    }

    private record ArrayCleaner(UnsafeMemory mem) implements Runnable {
        @Override
        public void run() {
            mem.free();
        }
    }

}
