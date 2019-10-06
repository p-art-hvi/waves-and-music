package ca.ubc.ece.cpen221.mp1;

import ca.ubc.ece.cpen221.mp1.utils.ComplexNumber;
import ca.ubc.ece.cpen221.mp1.utils.HasSimilarity;
import ca.ubc.ece.cpen221.mp1.utils.HelperMethods;
import javazoom.jl.player.StdPlayer;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class SoundWave implements HasSimilarity<SoundWave> {

    public static final double PI = 3.14156;
    public static final int SAMPLES_PER_SECOND = 44100;

    // some representation fields that you could use
    private List<Double> lchannel = new ArrayList<Double>();
    private List<Double> rchannel = new ArrayList<Double>();
    private int samples = 0;

    /**
     * Create a new SoundWave using the provided left and right channel
     * amplitude values. After the SoundWave is created, changes to the
     * provided arguments will not affect the SoundWave.
     *
     * @param lchannel is not null and represents the left channel.
     * @param rchannel is not null and represents the right channel.
     */
    public SoundWave(double[] lchannel, double[] rchannel) {
        // TODO: Implement this constructor
        this.rchannel = new ArrayList<>();
        this.lchannel = new ArrayList<>();
        append(lchannel, rchannel);

    }

    public SoundWave() {
        // TODO: You should implement a default constructor
        // that creates an empty wave
        //this.rchannel = new ArrayList<>();
        //this.lchannel = new ArrayList<>();
    }

    /**
     * Create a new sinusoidal sine wave,
     * sampled at a rate of 44,100 samples per second
     *
     * @param freq      the frequency of the sine wave, in Hertz
     * @param phase     the phase of the sine wave, in radians
     * @param amplitude the amplitude of the sine wave, 0 < amplitude <= 1
     * @param duration  the duration of the sine wave, in seconds
     */
    public SoundWave(double freq, double phase, double amplitude, double duration) {
        // TODO: Implement this constructor
        double time = 0;
        this.rchannel = new ArrayList<>();
        this.lchannel = new ArrayList<>();

        //TODO: check plus or minus phase
        for (time = 0; time <= duration; time = time + 1.0 / (double) SAMPLES_PER_SECOND) {
            double omega = 2 * Math.PI * freq * time;
            double yValue = amplitude * Math.sin(omega + phase);
            rchannel.add(yValue);
            lchannel.add(yValue);
        }

    }

    /**
     * Obtain the left channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the left channel for this wave.
     */
    public double[] getLeftChannel() {
        double[] newLchannel = new double[this.lchannel.size()];

        for (int i = 0; i < newLchannel.length; i++) {
            newLchannel[i] = lchannel.get(i);
        }

        return newLchannel;
    }

    /**
     * Obtain the right channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the right channel for this wave.
     */
    public double[] getRightChannel() {
        double[] newRchannel = new double[this.rchannel.size()];

        for (int i = 0; i < newRchannel.length; i++) {
            newRchannel[i] = rchannel.get(i);
        }
        return newRchannel;
    }

    /**
     * A simple main method to play an MP3 file. Note that MP3 files should
     * be encoded to use stereo channels and not mono channels for the sound to
     * play out correctly.
     * <p>
     * One should try to get this method to work correctly at the start.
     * </p>
     *
     * @param args are currently ignored but you could be creative.
     */
    public static void main(String[] args) {
        File file = new File("mp3/anger.mp3");
        StdPlayer.open("mp3/anger.mp3");
        SoundWave sw = new SoundWave();
        while (!StdPlayer.isEmpty()) {
            double[] lchannel = StdPlayer.getLeftChannel();
            double[] rchannel = StdPlayer.getRightChannel();
            sw.append(lchannel, rchannel);
        }

        sw.sendToStereoSpeaker();
        StdPlayer.close();
    }

    /**
     * Append a wave to this wave.
     *
     * @param other the wave to append.
     */
    public void append(SoundWave other) {

        double[] rChannelTwo = other.getRightChannel();
        List<Double> rChannelTwoArray = new ArrayList<Double>();
        double[] lChannelTwo = other.getLeftChannel();
        ArrayList<Double> lChannelTwoArray = new ArrayList<Double>();


       for (int i = 0; i < rChannelTwo.length; i++) {
          rChannelTwoArray.add(rChannelTwo[i]);
        }

        for (int i = 0; i < lChannelTwo.length; i++) {
            lChannelTwoArray.add(lChannelTwo[i]);
        }

        rchannel.addAll(rchannel.size(), rChannelTwoArray);

        lchannel.addAll(lchannel.size(), lChannelTwoArray);

    }

    /**
     * Append a wave to this wave.
     *
     * @param lchannel
     * @param rchannel
     */
    public void append(double[] lchannel, double[] rchannel) {

        for (int i = 0; i < lchannel.length; i++) {
            this.lchannel.add(lchannel[i]);
        }

        for (int i = 0; i < rchannel.length; i++) {
            this.rchannel.add(rchannel[i]);
        }
    }

    /**
     * Create a new wave by adding another wave to this wave.
     * (You should also write clear specifications for this method.)
     * @param other the wave to superimpose/add
     */
    public SoundWave add(SoundWave other) {
        double[] rchannelA = getRightChannel();
        double[] lchannelA = getLeftChannel();
        double[] rOtherChannel = other.getRightChannel();
        double[] lOtherChannel = other.getLeftChannel();

        double[] rNewChannel = HelperMethods.add(rchannelA, rOtherChannel);
        double[] lNewChannel = HelperMethods.add(lchannelA,lOtherChannel);

        SoundWave soundWave = new SoundWave(lNewChannel, rNewChannel);
        return soundWave;
    }

    /**
     * Create a new wave by adding an echo to this wave.
     *
     * @param delta > 0. delta is the lag between this wave and the echo wave.
     * @param alpha > 0. alpha is the damping factor applied to the echo wave.
     * @return a new sound wave with an echo.
     */
    public SoundWave addEcho(int delta, double alpha) {
        // TODO: create a test for this method
        ArrayList<Double> lEcho = new ArrayList<>();
        ArrayList<Double> rEcho = new ArrayList<>();

        for (int i = 0; i < delta * SAMPLES_PER_SECOND; i++) {
            lEcho.add(lchannel.get(i));
            rEcho.add(rchannel.get(i));
        }

        double[] lEchoArray = HelperMethods.addEcho(delta, lEcho, SAMPLES_PER_SECOND, alpha, lchannel);
        double[] rEchoArray = HelperMethods.addEcho(delta, rEcho, SAMPLES_PER_SECOND, alpha, rchannel);

        SoundWave echo = new SoundWave(lEchoArray, rEchoArray);

        return echo;
    }

    /**
     * Scale the amplitude of this wave by a scaling factor.
     * After scaling, the amplitude values are clipped to remain
     * between -1 and +1.
     *
     * @param scalingFactor is a value > 0.
     */
    public void scale(double scalingFactor) {
        // TODO: Implement this method.
        int max = 1;
        int min = -1;
        ArrayList<Double> lScaled = new ArrayList<>();
        ArrayList<Double> rScaled = new ArrayList<>();

        for (int i = 0; i < lchannel.size(); i++) {
            double scaledVal = scalingFactor * lchannel.get(i);
            if (scaledVal >= max) {
                lScaled.add(1.0);
            } else if (scaledVal <= min) {
                lScaled.add(-1.0);
            } else {
                lScaled.add(scaledVal);
            }
        }

        for (int i = 0; i < rchannel.size(); i++) {
            double scaledVal = scalingFactor * rchannel.get(i);
            if (scaledVal >= max) {
                rScaled.add(1.0);
            } else if (scaledVal <= min) {
                rScaled.add(-1.0);
            } else {
                rScaled.add(scaledVal);
            }
        }

        lchannel = lScaled;
        rchannel = rScaled;
    }

    /**
     * Return a new sound wave that is obtained by applying a high-pass filter to
     * this wave.
     *
     * @param dt >= 0. dt is the time interval used in the filtering process.
     * @param RC > 0. RC is the time constant for the high-pass filter.
     * @return
     */
    public SoundWave highPassFilter(int dt, double RC) {
        // TODO: Implement this
        double[] channelR = new double[rchannel.size()];
        channelR = getRightChannel();

        double[] arrayR = new double[channelR.length];

        double alpha = RC / (RC + dt);

        arrayR[0] = channelR[0];

        for (int i = 1; i < channelR.length; i++){
            arrayR[i] = alpha * arrayR[i-1] + alpha * (channelR[i] - channelR[i - 1]);
        }

        //for the left channel...
        double[] channelL = new double[lchannel.size()];
        channelL = getLeftChannel();

        double[] arrayL = new double[channelL.length];

        arrayL[0] = channelL[0];

        for (int i = 1; i < channelL.length; i++){
            arrayL[i] = alpha * arrayL[i-1] + alpha * (channelL[i] - channelL[i - 1]);
        }

        SoundWave soundWave = new SoundWave(arrayL, arrayR);
        return soundWave; // change this
    }

    /**
     * Return the frequency of the component with the greatest amplitude
     * contribution to this wave. This component is obtained by applying the
     * Discrete Fourier Transform to this wave.
     *
     * @return the frequency of the wave component of highest amplitude.
     * If more than one frequency has the same amplitude contribution then
     * return the higher frequency.
     */
    public double highAmplitudeFreqComponent() {
        // TODO: Implement this method
        //implement the DFT algorithm...
        //get the frequencies as an array for rchannel...
        double[] channelR;
        channelR = getRightChannel();

        double[] frequencyR = new double[rchannel.size()];

        int N = rchannel.size();
        ComplexNumber highestFreqRight = new ComplexNumber(0.0, 0.0);
        ComplexNumber highestFreqLeft = new ComplexNumber(0.0, 0.0);

        for(int k = 0; k < N - 1; k++){
            for(int t = 0; t < N - 1; t++){
                //implement a complex number type.
                //k is frequency
                double imaginaryPart = Math.sin((2*PI*k*t)/N);
                double realPart = Math.cos(2*PI*k*t);
                ComplexNumber complexNumber = new ComplexNumber(realPart, imaginaryPart);
                ComplexNumber newFreq = new ComplexNumber();
                newFreq = ComplexNumber.multiply(complexNumber, channelR[t]);

                //determine the highest frequency using the modulus of the complex number frequencies
                //i.e. find the magnitudes of the frequencies then compare them.
                if (ComplexNumber.mod(newFreq) >= ComplexNumber.mod(highestFreqRight)){
                    highestFreqRight = newFreq;
                }
            }
        }

        //get the frequencies as an array for lchannel...
        for(int k = 0; k < N - 1; k++){
            for(int t = 0; t < N - 1; t++){
                //implement a complex number type.
                //k is frequency
                double imaginaryPart = Math.sin((2*PI*k*t)/N);
                double realPart = Math.cos(2*PI*k*t);
                ComplexNumber complexNumber = new ComplexNumber(realPart, imaginaryPart);
                ComplexNumber newFreq = new ComplexNumber();
                newFreq = ComplexNumber.multiply(complexNumber, channelR[t]);

                //determine the highest frequency using the modulus of the complex number frequencies
                //i.e. find the magnitudes of the frequencies then compare them.
                if (ComplexNumber.mod(newFreq) >= ComplexNumber.mod(highestFreqLeft)){
                    highestFreqLeft = newFreq;
                }
            }
        }
        double highestFreq = 0.0;
        if(ComplexNumber.mod(highestFreqLeft) >= ComplexNumber.mod(highestFreqRight)){
            highestFreq = ComplexNumber.mod(highestFreqLeft);
        } else if (ComplexNumber.mod(highestFreqRight) > ComplexNumber.mod(highestFreqLeft)){
            highestFreq = ComplexNumber.mod(highestFreqRight);
        }

        return highestFreq;
    }

    /**
     * Determine if this wave fully contains the other sound wave as a pattern.
     *
     * @param other is the wave to search for in this wave.
     * @return true if the other wave is contained in this after amplitude scaling,
     * and false if the other wave is not contained in this with any
     * possible amplitude scaling.
     */
    public boolean contains(SoundWave other) {
        // TODO: Implement this method
        return false; // change this
    }

    /**
     * Determine the similarity between this wave and another wave.
     * The similarity metric, gamma, is the sum of squares of
     * instantaneous differences.
     *
     * @param other is not null.
     * @return the similarity between this wave and other.
     */
    public double similarity(SoundWave other) {
        // TODO: Implement this method
        return -1;
    }

    /**
     * Play this wave on the standard stereo device.
     */
    public void sendToStereoSpeaker() {
        // You may not need to change this...
        double[] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        StdPlayer.playWave(lchannel, rchannel);
    }

}
