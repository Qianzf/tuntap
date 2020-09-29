package tuntap.api.foreign;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;

public class Unsafe {

    public static final Native NATIVE;
    public static final Runtime RUNTIME;

    public interface Native {

        int socket(int domain, int type, int protocol);

        int open(String pathname, int flags);

        int close(int fd);

        int ioctl(int fd, long request, Object... args);

        Pointer memset(Pointer s, int c, @size_t long n);

        void free(Pointer ptr);

        @ssize_t
        long read(int fd, Pointer buf, @size_t long count);

        @ssize_t
        long write(int fd, Pointer buf, @size_t long count);

        int snprintf(Pointer str, @size_t long size, String format, Object... args);
    }

    static {
        NATIVE = LibraryLoader.create(Native.class).load("c");
        RUNTIME = Runtime.getRuntime(NATIVE);
    }
}
