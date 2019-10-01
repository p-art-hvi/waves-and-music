package ca.ubc.ece.cpen221.mp1;

import ca.ubc.ece.cpen221.mp1.utils.HasSimilarity;
import javazoom.jl.player.StdPlayer;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoundWave implements HasSimilarity<SoundWave> {

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
        double[] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        return lchannel;
    }

    /**
     * Obtain the right channel for this wave.
     * Changes to the returned array should not affect this SoundWave.
     *
     * @return an array that represents the right channel for this wave.
     */
    public double[] getRightChannel() {
        // TODO: Implement this

        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        return rchannel; // change this
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
        // TODO: Implement this method.
        double[] rChannelOne = getRightChannel();
        ArrayList<Double> rChannelOneArray = new ArrayList(Arrays.asList(rChannelOne));
        double[] lChannelOne = getLeftChannel();
        ArrayList<Double> lChannelOneArray = new ArrayList(Arrays.asList(lChannelOne));

        double[] rChannelTwo = other.getRightChannel();
        ArrayList<Double> rChannelTwoArray = new ArrayList(Arrays.asList(rChannelTwo));
        double[] lChannelTwo = other.getLeftChannel();
        ArrayList<Double> lChannelTwoArray = new ArrayList(Arrays.asList(lChannelTwo));

        for (int i = 0; i < rChannelTwoArray.size(); i++) {
            rChannelOneArray.add(rChannelTwoArray.get(i));
        }

        rchannel = rChannelOneArray;

        for (int i = 0; i < lChannelTwoArray.size(); i++) {
            lChannelOneArray.add(lChannelTwoArray.get(i));
        }

        lchannel = lChannelOneArray;
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
        // TODO: Implement this method
        double[] rchannelA = getRightChannel();
        ArrayList<Double> rchannelA1 = new ArrayList(Arrays.asList(rchannelA));
        double[] lchannelB = getLeftChannel();
        ArrayList<Double> lchannelB1 = new ArrayList(Arrays.asList(lchannelB));

        double[] rOtherChannel = other.getRightChannel();
        ArrayList<Double> rOtherChannel1 = new ArrayList(Arrays.asList(rOtherChannel));
        double[] lOtherChannel = other.getLeftChannel();
        ArrayList<Double> lOtherChannel1 = new ArrayList(Arrays.asList(lOtherChannel));

        int sizeRight = 0;
        int sizeLeft = 0;

        if (rchannelA1.size() < rOtherChannel1.size()
                || rchannelA1.size() == rOtherChannel1.size()) {
            sizeRight = rchannelA1.size();
        } else {
            sizeRight = rOtherChannel1.size();
        }

        if (lchannelB1.size() < lOtherChannel1.size()
                || lchannelB1.size() == lOtherChannel1.size()) {
            sizeLeft = lchannelB1.size();
        } else {
            sizeLeft = lOtherChannel1.size();
        }

        for (int i = 0; i < sizeRight; i++) {
            double addValue = 0;
            addValue = rchannelA1.get(i) + rOtherChannel1.get(i);
            rchannel.add(addValue);
        }

        for (int i = 0; i < sizeLeft; i++) {
            double addValue = 0;
            addValue = lchannelB1.get(i) + lOtherChannel1.get(i);
            lchannel.add(addValue);
        }

        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        double[] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();

        SoundWave soundWave = new SoundWave(lchannel, rchannel);
        
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

        for (int i = delta * SAMPLES_PER_SECOND; i < lchannel.size(); i++) {
            double echoValue = lchannel.get(i)
                    + lchannel.get(i - delta * SAMPLES_PER_SECOND) * alpha;
            lEcho.add(echoValue);
        }

        for (int i = lchannel.size(); i < lchannel.size() + delta * SAMPLES_PER_SECOND; i++) {
            lEcho.add(lchannel.get(i - delta * SAMPLES_PER_SECOND) * alpha);
        }

        for (int i = delta * SAMPLES_PER_SECOND; i < rchannel.size(); i++) {
            double echoValue = rchannel.get(i)
                    + rchannel.get(i - delta * SAMPLES_PER_SECOND) * alpha;
            rEcho.add(echoValue);
        }

        for (int i = rchannel.size(); i < rchannel.size() + delta * SAMPLES_PER_SECOND; i++) {
            rEcho.add(rchannel.get(i - delta * SAMPLES_PER_SECOND) * alpha);
        }

        lchannel = lEcho;
        rchannel = rEcho;

        double [] lchannel = this.lchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();
        double[] rchannel = this.rchannel.stream().mapToDouble(x -> x.doubleValue()).toArray();

        SoundWave soundWave = new SoundWave(lchannel, rchannel);
        return soundWave;
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
        return null; // change this
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
        return -1; // change this
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

        return contains; // change this
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
