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
        this.rchannel = new ArrayList<>();
        this.lchannel = new ArrayList<>();
        append(lchannel, rchannel);

    }

    public SoundWave() {
      
        this.rchannel = new ArrayList<>();
        this.lchannel = new ArrayList<>();

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
        double time = 0;
        this.rchannel = new ArrayList<>();
        this.lchannel = new ArrayList<>();

        //TODO: check plus or minus phase
        for (time = 0; time <= duration; time = time + 1.0 / (double) SoundWave.SAMPLES_PER_SECOND) {
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
  /*  public static void main(String[] args) {
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
    }*/

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
        ArrayList<Double> lEcho = new ArrayList<>();
        ArrayList<Double> rEcho = new ArrayList<>();

        double[] lEchoArray = HelperMethods.addEcho(delta, lEcho, alpha, lchannel);
        double[] rEchoArray = HelperMethods.addEcho(delta, rEcho, alpha, rchannel);

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
        lchannel = HelperMethods.scale(lchannel, scalingFactor);
        rchannel = HelperMethods.scale(rchannel, scalingFactor);
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
        double[] rchannel = getRightChannel();
        double[] lchannel = getLeftChannel();
        double alpha = RC / (RC + dt);

        double[] arrayL = HelperMethods.helpHPF(alpha, lchannel);
        double[] arrayR = HelperMethods.helpHPF(alpha, rchannel);

        SoundWave soundWave = new SoundWave(arrayL, arrayR);
        return soundWave;
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
        ComplexNumber temp1 = new ComplexNumber(0.0,0.0);
        ComplexNumber temp2 = new ComplexNumber(0.0,0.0);

        for (int freq = 0; freq < N - 1; freq++) {
            for (int t = 0; t < N - 1; t++) {
                //implement a complex number type.
                double imaginaryPart = Math.sin((2 * PI * freq * t) / N);
                double realPart = Math.cos(2 * PI * freq * t);
                ComplexNumber complexNumber = new ComplexNumber(realPart, imaginaryPart);
                ComplexNumber newFreq = new ComplexNumber();
                newFreq = ComplexNumber.multiply(complexNumber, channelR[t]);
                temp1.add(newFreq);
            }
            if (ComplexNumber.mod(temp1) >= ComplexNumber.mod(highestFreqRight)){
                highestFreqRight = temp1;
            }
        }

        //get the frequencies as an array for lchannel...
        for (int freq = 0; freq < N - 1; freq++) {
            for (int t = 0; t < N - 1; t++) {
                //implement a complex number type.
                double imaginaryPart = Math.sin((2 * PI * freq * t) / N);
                double realPart = Math.cos(2 * PI * freq * t);
                ComplexNumber complexNumber = new ComplexNumber(realPart, imaginaryPart);
                ComplexNumber newFreq = new ComplexNumber();
                newFreq = ComplexNumber.multiply(complexNumber, channelR[t]);
                temp2.add(newFreq);
            }
            //determine highest frequency using the modulus of the complex number frequencies
            //i.e. find magnitudes of the frequencies then compare them.

            if (ComplexNumber.mod(temp2) >= ComplexNumber.mod(highestFreqLeft)) {
                highestFreqLeft = temp2;
            }
        }

        double highestFreq = 0.0;
        if (ComplexNumber.mod(highestFreqLeft) >= ComplexNumber.mod(highestFreqRight)) {
            highestFreq = ComplexNumber.mod(highestFreqLeft);
        } else if (ComplexNumber.mod(highestFreqRight) > ComplexNumber.mod(highestFreqLeft)) {
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

        boolean contains = false;
        SoundWave soundWave = new SoundWave();
        double[] rightChannel = soundWave.getRightChannel();
        double[] leftChannel = soundWave.getLeftChannel();
        double[] checkChannelR = other.getRightChannel();
        double[] checkChannelL = other.getLeftChannel();

        //check if the right channel of other is within the right channel of soundWave
        for(int b = 0; b < rightChannel.length * SAMPLES_PER_SECOND; b++){
            for(int a = 0; a < checkChannelR.length * SAMPLES_PER_SECOND; a++){
                if((checkChannelR[a]/rightChannel[b]) == (checkChannelR[a + 1]/rightChannel[b + 1])){
                    for(int i = a; i < checkChannelR.length* SAMPLES_PER_SECOND; i++){
                        if((checkChannelR[i]/rightChannel[b]) == (checkChannelR[i + 1]/rightChannel[b + 1])){
                            contains = true;
                        } else {
                            contains = false;
                        }
                    }
                }
            }
        }

        //check if the left channel of other is within the left channel of soundWave
        for(int b = 0; b < leftChannel.length * SAMPLES_PER_SECOND; b++){
            for(int a = 0; a < checkChannelL.length * SAMPLES_PER_SECOND; a++){
                if((checkChannelL[a]/rightChannel[b]) == (checkChannelL[a + 1]/leftChannel[b + 1])){
                    for(int i = a; i < checkChannelL.length* SAMPLES_PER_SECOND; i++){
                        if((checkChannelL[i]/leftChannel[b]) == (checkChannelL[i + 1]/leftChannel[b + 1])){
                            contains = true;
                        } else {
                            contains = false;
                        }
                    }
                }
            }
        }

        return contains;


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
    /*public void sendToStereoSpeaker() {
        // You may not need to change this...
        double[] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        StdPlayer.playWave(lchannel, rchannel);
    }*/

}
