
package ca.ubc.ece.cpen221.mp1.utils;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static final int MAX = 1;
    public static final int MIN = -1;


    /**
     * adds (superimposes) two waves
     *
     * @param channelA is not null and represents the same side (left/right)
     *                 channel as channelB, of one SoundWave.
     * @param channelB is not null and represents the same side (left/right)
     *                 channel as channelA, of another SoundWave.
     * @return channel with the wo waves superimposed, capped at +1/-1
     */
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

    /**
     * Create a new channel with one wave echoed onto the other
     *
     * @param delta > 0. delta is the lag between this wave and the echo wave.
     * @param echo empty ArrayList to store echoed SoundWave.
     * @param alpha > 0 >= 1. alpha is the damping factor applied to the echo wave.
     * @param channel channel of SoundWave to echo
     * @return channel with an echo applied
     */

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

    /**
     * Return the frequency of the component with the greatest amplitude
     * contribution to this wave. This component is obtained by applying the
     * Discrete Fourier Transform to this wave.
     *
     * @param channelSize size of channel being used
     * @param channel array of channel being used
     * @param highestFreq previous highest frequency
     * @param highestFreqAmplitude empty ComplexNumber
     * @param temp temporary ComplexNumber
     * @return the frequency of the wave component of highest amplitude.
     * If more than one frequency has the same amplitude contribution then
     * return the higher frequency.
     */

    public static double helpDFT(int channelSize, double [] channel, double highestFreq,
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

    /**
     * Return a new sound wave that is obtained by applying a high-pass filter to
     * this wave.
     *
     * @param alpha >= 0.   RC / (RC + dt)
     * @param channel channel to apply high pass filter to
     * @return channel with HPF applied
     */

    public static double [] helpHPF(double alpha, double [] channel) {
        double[] channelArray = new double[channel.length];
        channelArray[0] = channel[0];

        for (int i = 1; i < channel.length; i++) {
            channelArray[i] = alpha * channelArray[i - 1] + alpha * (channel[i] - channel[i - 1]);
        }

        return channelArray;
    }

    /**
     * Scale the amplitude of this wave by a scaling factor.
     * After scaling, the amplitude values are clipped to remain
     * between -1 and +1.
     *
     * @param channel is the channel of SoundWave to scale
     * @param scalingFactor is a value > 0.
     * @return ArrayList of the scaled channel.
     */
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
