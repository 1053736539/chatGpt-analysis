package com.cb.knowledge.base.essearch;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 22:39
 * @Copyright (c) 2025
 * @Description 压缩算法
 */
public class DeltaCompression {
    public static byte[] encode(List<Integer> docIds) {
        if (docIds.isEmpty()) return new byte[0];

        List<Integer> deltas = new ArrayList<>();
        int prev = 0;
        for (int id : docIds) {
            deltas.add(id - prev);
            prev = id;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(bos)) {
            for (int delta : deltas) {
                writeVarInt(delta, dos);
            }
        } catch (IOException e) {
            throw new RuntimeException("压缩失败", e);
        }
        return bos.toByteArray();
    }

    public static List<Integer> decode(byte[] data) {
        List<Integer> docIds = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int current = 0;

        while (buffer.hasRemaining()) {
            int delta = readVarInt(buffer);
            current += delta;
            docIds.add(current);
        }
        return docIds;
    }

    private static void writeVarInt(int value, DataOutputStream out) throws IOException {
        while ((value & ~0x7F) != 0) {
            out.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte(value);
    }

    private static int readVarInt(ByteBuffer buffer) {
        int result = 0;
        int shift = 0;
        byte b;
        do {
            b = buffer.get();
            result |= (b & 0x7F) << shift;
            shift += 7;
        } while ((b & 0x80) != 0);
        return result;
    }
}
