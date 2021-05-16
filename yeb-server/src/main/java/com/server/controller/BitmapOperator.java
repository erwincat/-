package com.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.BitSet;


@Slf4j
public class BitmapOperator {

    private static final long serialVersionUID = 1L;

    protected final static int ADDRESS_BITS_PER_WORD = 6;
    protected final static int BITS_PER_WORD         = 1 << ADDRESS_BITS_PER_WORD;//单页的bit数量
    protected final static int BIT_INDEX_MASK        = BITS_PER_WORD - 1;

    protected BitSet bitSet;

    public BitmapOperator() {
        System.out.println();
    }

    public BitmapOperator(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    public BitmapOperator(Long[] longs) {
        this.bitSet = BitSet.valueOf(ArrayUtils.toPrimitive(longs));
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public long[] getBitmapLongArray() {
        return bitSet.toLongArray();
    }

    public int[] setBit(int startInt, int endInt, int interval) {

        int intervalTmp = interval;
        while (true) {
            if (intervalTmp == 0) break;
            if (startInt > endInt) break;

            boolean b = bitSet.get(endInt--);
            if (!b) {
                intervalTmp--;
            } else {
                intervalTmp = interval;
            }
        }
        if (intervalTmp != 0) {
            return new int[0];
        }


        int[] ints = new int[interval];
        for (int i = interval; i > 0; i--) {
            bitSet.set(endInt + i);
            ints[i - 1] = endInt + i;
        }

        return ints;
    }

    public int[] getNextClearBits(int startInt, int interval) {

        int intervalTmp = interval;
        int endInt = startInt;
        while (true) {
            if (intervalTmp == 0) break;
            if (intervalTmp == interval) {
                endInt = bitSet.nextClearBit(endInt);
            }

            boolean b = bitSet.get(endInt++);
            if (!b) {
                intervalTmp--;
            } else {
                intervalTmp = interval;
            }
        }
        int[] ints = new int[interval];
        for (int i = interval; i > 0; i--) {
            ints[interval - i] = endInt - i;
        }
        return ints;
    }

    /**
     * Sets the bits from the specified {@code startIndex} (inclusive) to the
     * specified {@code endIndex} (inclusive) to {@code false}.
     *
     * @param startIndex 开始位
     * @param endIndex   结束位
     * @return 已清理位
     * @see BitSet#clear()
     */
    public int[] cleanBit(int startIndex, int endIndex) {

        if (startIndex > endIndex)
            throw new RuntimeException("starting index larger than ending index");
        int interval = endIndex - startIndex;

        byte by = 0b1;
        int[] ints = new int[interval + 1];
        for (int i = 0; i > interval; i++) {
            boolean b = bitSet.get(i + startIndex);
            by &= b ? 0b1 : 0b0;//必须所有未皆为true
            ints[i] = i + startIndex;
        }
        if (by != 0b1)
            return new int[]{};

        bitSet.clear(startIndex, endIndex + 1);
        return ints;
    }

    /**
     * 按照指定位数，计算出整页数并踢出，随后把剩余的位数全部往右移废弃剩余位数
     *
     * @param originArray 将要移动的数组
     * @param totalBit    需要剔除的位数
     * @return 移动后的数组
     */
    public static long[] rangeBitmap(Long[] originArray, final int totalBit) {
        double totalKickOut = (double) totalBit / BITS_PER_WORD;//需要被踢出的页数
        double modsLong = totalKickOut - (totalBit >> 6);//取出未满的一页的页数
        short numberOfNeedBits = (short) (modsLong * BITS_PER_WORD);//未满一页的bit数
        if ((totalBit >> 6) > originArray.length) {
            return new long[0];//长度不够直接扔掉
        }
        Long[] rangeOriginArray = Arrays.copyOfRange(originArray, (totalBit >> 6), originArray.length);
        return numberOfNeedBits == 0
                ? shiftBitmap(rangeOriginArray, numberOfNeedBits)
                : ArrayUtils.toPrimitive(rangeOriginArray);
    }

    /**
     * 所有数组向右移动 {@code totalOfShiftBit} 位
     *
     * @param originArray     原始数组
     * @param totalOfShiftBit 需要移动的位数
     * @return
     */
    private static long[] shiftBitmap(final Long[] originArray, final short totalOfShiftBit) {
        int originSize = originArray.length;
        long[] newArray = new long[originSize];

        for (int i = 0; i < originSize; i++) {
            Long thisVal = originArray[i];
            long newVal = thisVal >>> totalOfShiftBit;
            log.debug("当前数据: {}", Long.toBinaryString(thisVal));
            log.debug("新数据: {}", Long.toBinaryString(newVal));
            if (i == (originSize - 1)) {
                newArray[i] = newVal;
                continue;
            }
            Long nextVal = originArray[i + 1];//下一个数
            long needBits = (1L << totalOfShiftBit) - 1;//需要的bit
            long tmpVal = nextVal & needBits;//取走需要的数据
            tmpVal <<= BITS_PER_WORD - totalOfShiftBit;//把取出来的往左移
            newVal |= tmpVal;
            newArray[i] = newVal;

//            log.debug("需要的bit位 {}", Long.toBinaryString(needBits));
            log.debug("下个数 {}", Long.toBinaryString(nextVal));
            log.debug("被取走的位 {}", Long.toBinaryString(tmpVal));
            log.debug("取走移位后 {}", Long.toBinaryString(tmpVal));
            log.debug("填充后的新数据: {}", Long.toBinaryString(newVal));
        }

        return newArray;
    }

    @Override
    public String toString() {
        return bitSet.toString();
    }

}
