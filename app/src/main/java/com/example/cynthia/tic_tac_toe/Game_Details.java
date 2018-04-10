package com.example.cynthia.tic_tac_toe;

import android.widget.ImageView;

/**
 * Created by cynthia on 3/30/18.
 */

public class Game_Details {
    private static ImageView[] sBlocks;
    public static String sWinner;
    public static int sSet;
    public static final int CIRCLE = 0;
    public static final int CROSS = 1;

    private static boolean areSameInSet(int first, int second, int third, int set) {
        boolean value = sBlocks[first - 1].getId() == sBlocks[second - 1].getId() && sBlocks[second - 1].getId() == sBlocks[third - 1].getId();
        if (value) {
            if (sBlocks[first - 1].getId() == CIRCLE)
                sWinner = "CIRCLE";
            else
                sWinner = "CROSS";
            sSet = set;
        }
        return value;
    }


    public static boolean isCompleted(int position, ImageView[] blocks) {
        Game_Details.sBlocks = blocks;
        boolean isComplete = false;
        switch (position) {
            case 1:
                isComplete = areSameInSet(1, 2, 3, 1) ||
                        areSameInSet(1, 4, 7, 4) ||
                        areSameInSet(1, 5, 9, 7);
                break;
            case 2:
                isComplete = areSameInSet(1, 2, 3, 1) ||
                        areSameInSet(2, 5, 8, 5);
                break;
            case 3:
                isComplete = areSameInSet(1, 2, 3, 1) ||
                        areSameInSet(3, 6, 9, 6) ||
                        areSameInSet(3, 5, 7, 8);
                break;
            case 4:
                isComplete = areSameInSet(4, 5, 6, 2) ||
                        areSameInSet(1, 4, 7, 4);
                break;
            case 5:
                isComplete = areSameInSet(4, 5, 6, 2) ||
                        areSameInSet(2, 5, 8, 5) ||
                        areSameInSet(1, 5, 9, 7) ||
                        areSameInSet(3, 5, 7, 8);
                break;
            case 6:
                isComplete = areSameInSet(4, 5, 6, 2) ||
                        areSameInSet(3, 6, 9, 6);
                break;
            case 7:
                isComplete = areSameInSet(7, 8, 9, 3) ||
                        areSameInSet(1, 4, 7, 4) ||
                        areSameInSet(3, 5, 7, 8);
                break;
            case 8:
                isComplete = areSameInSet(7, 8, 9, 3) ||
                        areSameInSet(2, 5, 8, 5);
                break;
            case 9:
                isComplete = areSameInSet(7, 8, 9, 3) ||
                        areSameInSet(3, 6, 9, 6) ||
                        areSameInSet(1, 5, 9, 7);
                break;

        }
        return isComplete;
    }

}
