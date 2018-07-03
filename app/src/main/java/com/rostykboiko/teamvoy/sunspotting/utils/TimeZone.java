package com.rostykboiko.teamvoy.sunspotting.utils;

public class TimeZone {
    private long dstOffset;
    private long rawOffset;

    public long getRawOffset() {
        return rawOffset;
    }

    public long getDstOffset() {
        return dstOffset;
    }
}
