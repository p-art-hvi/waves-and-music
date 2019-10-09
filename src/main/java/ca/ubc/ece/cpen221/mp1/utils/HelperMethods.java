
package ca.ubc.ece.cpen221.mp1.utils;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static final int MAX = 1;
    public static final int MIN = -1;

    public static double[] add(double[] channelA, double[] channelB) {
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

        for (int j = 0; j < newChannel.length; j++) {
            if (newChannel[j] > MAX) {
                newChannel[j] = MAX;
            } else if (newChannel[j] < MIN) {
                newChannel[j] = MIN;
            }
        }

        return newChannel;
    }


    public static double[] addEcho(int delta, ArrayList<Double> echo,
                                   double alpha, List<Double> channel) {

        for (int i = 0; i < delta; i++) {
            echo.add(channel.get(i));
        }

        for (int i = delta; i < channel.size(); i++) {
            double echoValue = channel.get(i) + channel.get(i - delta) * alpha;
            echo.add(echoValue);
        }

        for (int i = channel.size(); i < channel.size() + delta; i++) {
            echo.add(channel.get(i - delta) * alpha);
        }

        double[] echoArray = new double[echo.size()];

        for (int j = 0; j < echo.size(); j++) {
            if (echo.get(j) >= MAX) {
                echoArray[j] = MAX;
            } else if (echo.get(j) <= MIN) {
                echoArray[j] = MIN;
            } else {
                echoArray[j] = echo.get(j);
            }
        }
        return echoArray;
    }

    public static int helpDFT(int channelSize, double [] channel, int highestFreq,
                              ComplexNumber highestFreqAmplitude, ComplexNumber temp) {
        double imaginaryPart;
        double realPart;
        for (int freq = 0; freq < channelSize - highestFreq; freq++) {
            temp.reset();
            for (int t = 0; t < channelSize; t++) {
                //implement a complex number type.
                imaginaryPart = (-1.0) * (Math.sin((2.0 * Math.PI * freq * t)
                        / (double) channelSize));
                realPart = Math.cos((2.0 * Math.PI * freq * t) / (double) channelSize);
                ComplexNumber complexNumber = new ComplexNumber(realPart, imaginaryPart);
                ComplexNumber newFreq = ComplexNumber.multiply(complexNumber, channel[t]);
                temp.add(newFreq);
            }

            //determine highest frequency using the modulus of the complex number frequencies
            //i.e. find magnitudes of the frequencies then compare them.

            if (ComplexNumber.mod(temp) >= ComplexNumber.mod(highestFreqAmplitude)) {
                highestFreq = freq;
                highestFreqAmplitude = new ComplexNumber(temp.realPart(), temp.imagPart());
            }
        }
        return highestFreq;
    }

    public static double [] helpHPF(double alpha, double [] channel) {
        double[] channelArray = new double[channel.length];
        channelArray[0] = channel[0];

        for (int i = 1; i < channel.length; i++) {
            channelArray[i] = alpha * channelArray[i - 1] + alpha * (channel[i] - channel[i - 1]);
        }

        return channelArray;
    }

    public static ArrayList<Double> scale(List<Double> channel, double scalingFactor) {

        ArrayList<Double> scaled = new ArrayList<>();

        for (int i = 0; i < channel.size(); i++) {
            double scaledVal = scalingFactor * channel.get(i);
            if (scaledVal >= MAX) {
                scaled.add((double) MAX);
            } else if (scaledVal <= MIN) {
                scaled.add((double) MIN);
            } else {
                scaled.add(scaledVal);
            }
        }
        return scaled;
    }

}
