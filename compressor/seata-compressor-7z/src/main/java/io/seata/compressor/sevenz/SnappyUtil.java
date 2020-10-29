package io.seata.compressor.sevenz;

import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/26 下午1:59
 */
public class SnappyUtil {

    private static final int BUFFER_SIZE = 8192;
    
    public static byte[] compress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null");
        }
        int count;
        byte[] buffer = new byte[BUFFER_SIZE];
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             SnappyOutputStream sos = new SnappyOutputStream(os)) {
            while ((count = is.read(buffer)) != -1) {
                sos.write(buffer, 0, count);
            }
            sos.flush();
            byte[] output = os.toByteArray();
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Snappy compress error", e);
        }
    }
    
    public static byte[] decompress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null");
        }
        byte[] buffer = new byte[BUFFER_SIZE];
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             SnappyInputStream sis = new SnappyInputStream(is)) {
            while (true) {
                int count = sis.read(buffer, 0, buffer.length);
                if (count == -1) {
                    break;
                }
                os.write(buffer, 0, count);
            }
            os.flush();
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Snappy decompress error", e);
        }
    }
}
