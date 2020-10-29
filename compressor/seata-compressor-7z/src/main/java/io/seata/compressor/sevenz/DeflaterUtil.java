package io.seata.compressor.sevenz;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/25 下午10:46
 */
public class DeflaterUtil {

    private static final int BUFFER_SIZE = 1024;

    public static byte[] compress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null");
        }
        int lenght = 0;
        Deflater deflater = new Deflater();
        deflater.setInput(bytes);
        deflater.finish();
        byte[] outputBytes = new byte[BUFFER_SIZE];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            while (!deflater.finished()) {
                lenght = deflater.deflate(outputBytes);
                bos.write(outputBytes, 0, lenght);
            }
            deflater.end();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Deflater compress error", e);
        }
    }

    public static byte[] decompress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null");
        }
        int length = 0;
        Inflater inflater = new Inflater();
        inflater.setInput(bytes);
        byte[] outputBytes = new byte[BUFFER_SIZE];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            while (!inflater.finished()) {
                length = inflater.inflate(outputBytes);
                if (length == 0) {
                    break;
                }
                bos.write(outputBytes, 0, length);
            }
            inflater.end();
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Deflater decompress error", e);
        }
    }
}
