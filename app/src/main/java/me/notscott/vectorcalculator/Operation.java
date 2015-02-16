package me.notscott.vectorcalculator;

import android.os.Build;

/**
 * Created by scott on 2/16/15.
 */
public enum Operation {
    /***
     * Addition of 2 or 3 vectors
     */
    ADDITION(0),

    /***
     * Scalar Product of 2 vectors
     */
    SCALAR(1),

    /***
     * Vector (Cross) Product of 2 vectors
     */
    CROSS(2);

    private final int value;

    private Operation(int i){
        this.value = i;
    }

    public static Operation fromInt(int i) {
        switch (i){
            case 0: return ADDITION;
            case 1: return SCALAR;
            case 2: return CROSS;
            default: return null;
        }
    }
}
