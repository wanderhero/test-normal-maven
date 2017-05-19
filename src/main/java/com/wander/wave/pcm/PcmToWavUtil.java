package com.wander.wave.pcm;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Wander.Zeng
 * @create 2017-05-16 13:49
 * @desc PcmToWavUtil
 */
public class PcmToWavUtil {

    public static void convert(String src, String target) throws Exception {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);

        // 计算长度
        int pcmSize = fis.available();// 由于音频文件一般不会大于 Int 最大值，所以使用 available

        // 填入参数，比特率等等。这里用的是 16位 单声道 8000 hz
        WavHeader header = new WavHeader();
        // 长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = pcmSize + (44 - 8);
        header.fmtHdrLeth = 16;
        header.bitsPerSample = 16;
        header.channels = 1;
        header.formatTag = 0x0001;
        header.samplesPerSec = 8000;
        header.blockAlign = (short) (header.channels * header.bitsPerSample / 8);
        header.avgBytesPerSec = header.blockAlign * header.samplesPerSec;
        header.dataHdrLeth = pcmSize;

        byte[] h = header.getHeader();

        assert h.length == 44;// WAV标准，头部应该是44字节
        // write header
        fos.write(h, 0, h.length);
        // write data stream
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Convert OK!");
    }

    public static void main(String[] args) throws Exception {
        convert("/Users/wander/Downloads/test.pcm", "/Users/wander/Downloads/test.pcm.wav");
    }

}
