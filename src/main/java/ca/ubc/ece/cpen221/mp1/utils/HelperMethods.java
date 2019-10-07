package ca.ubc.ece.cpen221.mp1.utils;

import ca.ubc.ece.cpen221.mp1.SoundWave;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {
    public static double[] add(double[] channelA, double[] channelB){
        int size = 0;
        if (channelA.length >= channelB.length) {
            size = channelA.length;
        } else {
            size = channelB.length;
        }

        double[] newChannel = new double[size];

        for (int i = 0; i < channelA.length && i < channelB.length; i++) {
            double addValue = channelA[i] + channelB[i];
            newChannel[i] = addValue;
        }
        if (channelA.length > channelB.length) {
            for (int i = channelB.length; i < channelA.length; i++) {
                newChannel[i] = channelA[i];
            }
        } else if (channelA.length < channelB.length) {
            for (int i = channelA.length; i < channelB.length; i++) {
                newChannel[i] = channelB[i];
            }
        }
        return newChannel;
    }


    public static double[] addEcho(int delta, ArrayList<Double> echo, int SAMPLES_PER_SECOND, double alpha, List<Double> channel){
        for (int i = delta * SAMPLES_PER_SECOND; i < channel.size(); i++) {
            double echoValue = channel.get(i)
                    + echo.get(i - delta * SAMPLES_PER_SECOND) * alpha;
            echo.add(echoValue);
        }

        for (int i = channel.size(); i < channel.size() + delta * SAMPLES_PER_SECOND; i++) {
            echo.add(channel.get(i - delta * SAMPLES_PER_SECOND) * alpha);
        }

        double[] echoArray = new double[echo.size()];

        for (int i = 0; i < echoArray.length; i++) {
            echoArray[i] = echo.get(i);
        }
        return echoArray;
    }
}
