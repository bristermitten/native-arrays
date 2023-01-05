public class UnsafeMemory {
    private final long size;
    private final long address;

    static {
        System.loadLibrary("arrays");
    }

    private UnsafeMemory(long size, long address) {
        this.size = size;
        this.address = address;
    }

    public static UnsafeMemory create(long size) {
        return new UnsafeMemory(size, allocate(size));
    }

    private static native long allocate(long size);

    public native void set(long index, Object value);

    public native <T> T get(long index);

    public void free() {
        free(size);
    }

    private native void free(long size);

    @Override
    public String toString() {
        return "UnsafeMemory{" +
                "size=" + size +
                ", address=" + address +
                '}';
    }
}
