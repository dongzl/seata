package io.seata.compressor.sevenz;

import io.seata.common.loader.LoadLevel;
import io.seata.core.compressor.Compressor;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/26 下午1:48
 */
@LoadLevel(name = "SNAPPY")
public class SnappyCompressor implements Compressor {

    @Override
    public byte[] compress(byte[] bytes) {
//        try {FstUndoLogParserTest
//            return Snappy.compress(bytes);
//        } catch (IOException e) {
//            throw new RuntimeException("Snappy compress error", e);
//        }
        return SnappyUtil.compress(bytes);
    }

    @Override
    public byte[] decompress(byte[] bytes) {
//        try {
//            return Snappy.uncompress(bytes);
//        } catch (IOException e) {
//            throw new RuntimeException("Snappy decompress error", e);
//        }
        return SnappyUtil.decompress(bytes);
    }
}
