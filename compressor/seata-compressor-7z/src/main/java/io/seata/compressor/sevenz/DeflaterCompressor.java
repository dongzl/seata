package io.seata.compressor.sevenz;

import io.seata.common.loader.LoadLevel;
import io.seata.core.compressor.Compressor;

/**
 * @author dongzonglei
 * @description
 * @date 2020/10/25 下午10:45
 */
@LoadLevel(name = "DEFLATER")
public class DeflaterCompressor implements Compressor {

    @Override
    public byte[] compress(byte[] bytes) {
        return DeflaterUtil.compress(bytes);
    }

    @Override
    public byte[] decompress(byte[] bytes) {
        return DeflaterUtil.decompress(bytes);
    }
}
