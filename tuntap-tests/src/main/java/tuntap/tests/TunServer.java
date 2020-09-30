package tuntap.tests;

import jnr.ffi.Pointer;
import tuntap.*;

public class TunServer {

    public static void main(String[] args) {
        Service service = Service.Loader.load("tuntap-linux");
        Interface tun = service.create(new InterfaceOptions().id(0).name("tun-linux").mode(InterfaceMode.tun()));
        Buffer buffer = service.allocate(1504);
        buffer.writerIndex(0);
        Pointer ptr = buffer.buffer(Pointer.class);
        while (true) {
            tun.read(buffer);
            StringBuilder sb = new StringBuilder();
            sb.append("0x");
            for (long i = 0; i < buffer.writerIndex(); i++) {
                sb.append(String.format("%02X ", ptr.getByte(i)));
            }
            System.out.println(sb.toString());
            buffer.writerIndex(0);
        }
    }
}
