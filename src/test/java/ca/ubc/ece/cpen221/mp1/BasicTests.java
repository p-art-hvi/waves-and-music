package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BasicTests {
    /**
     * testing the left and right channel getters
     */

    @Test
    public void testCreateWave() {
        double[] lchannel = {1.0, -1.0};
        double[] rchannel = {1.0, -1.0};
        SoundWave wave = new SoundWave(lchannel, rchannel);
        double[] lchannel1 = wave.getLeftChannel();
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
        double[] rchannel1 = wave.getRightChannel();
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
    }

    /**
     * testing a null wave as a default
     */

    @Test
    public void testEmptyWave() {
        double [] lchannel = new double [0];
        double [] rchannel = new double[0];

        SoundWave wave = new SoundWave();
        double[] lchannel1 = wave.getLeftChannel();
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
        double[] rchannel1 = wave.getRightChannel();
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
    }

    /**
     * testing creating SoundWave object using the wave equation
     */
    @Test
    public void testWaveFromScratch() {

        double freq = 70;
        double amp = .5;
        double phase = 2;
        double duration = 5;
        double time = 0;
        double [] rchannel = new double[5 * 44100 + 1];
        double [] lchannel = new double[5 * 44100 + 1];
        int i = 0;

        SoundWave wave = new SoundWave(freq, phase, amp, duration);
        double [] rchannel1 = wave.getRightChannel();
        double [] lchannel1 = wave.getLeftChannel();

        for (time = 0; i < rchannel.length; time = time + 1.0 / (double) 44100) {
            double omega = 2 * Math.PI * freq * time;
            double yValue = amp * Math.sin(omega + phase);
            rchannel[i] = yValue;
            lchannel[i] = yValue;
            i++;
        }
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
    }

    /**
     * testing append method using another SoundWave object
     */
    @Test
    public void testAppend() {

        double[] lchannel = {1.0, 0.5, -0.5, -1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAppend = {.5, .1, .1, -.5};
        double[] rchannelAppend = {1.0, .1, .15, -.5};
        double[] lchannelAppended = { 1.0, 0.5, -0.5, -1.0, 0.5, 0.1, 0.1, -0.5};
        double[] rchannelAppended = { 1.0, 0.2, -0.3, -1.0, 1.0, 0.1, 0.15, -0.5};


        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave waveAppend = new SoundWave(lchannelAppend, rchannelAppend);
        wave.append(waveAppend);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAppended, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAppended, lchannel1, 0.00001);

    }

    /**
     * testing append with an empty array and a duplicate array
     */
    @Test
    public void testAppendEmpty() {

        double[] lchannel = {1.0, 0.5, -0.5, -1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAppend = new double[0];
        double[] rchannelAppend = {1.0, .2, -0.3, -1.0};
        double[] lchannelAppended = { 1.0, 0.5, -0.5, -1.0};
        double[] rchannelAppended = { 1.0, 0.2, -0.3, -1.0, 1.0, 0.2, -0.3, -1.0};


        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave waveAppend = new SoundWave(lchannelAppend, rchannelAppend);
        wave.append(waveAppend);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAppended, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAppended, lchannel1, 0.00001);

    }

    /**
     * testing append method using individual left and right channels
     */
    @Test
    public void testAppend1() {
        double[] lchannel = {1.0, 0.5, -0.5, -1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAppend = {.5, .1, .1, -.5};
        double[] rchannelAppend = {1.0, .1, .15, -.5};
        double[] lchannelAppended = { 1.0, 0.5, -0.5, -1.0, 0.5, 0.1, 0.1, -0.5};
        double[] rchannelAppended = { 1.0, 0.2, -0.3, -1.0, 1.0, 0.1, 0.15, -0.5};


        SoundWave wave = new SoundWave(lchannel, rchannel);
        wave.append(lchannelAppend, rchannelAppend);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAppended, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAppended, lchannel1, 0.00001);
    }

    /**
     * test add function using multiple channels of the same length
     * and checks for capping off at max/min values (+1/-1)
     */
    @Test
    public void testAdd() {
        double[] lchannel = {1.0, 0.5, -0.5, -1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1.0, 0.6, -0.4, -1};
        double[] rchannelAdded = {1, .3, -.15, -1};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);
    }

    /**
     * test adding channels of varying lengths - added channel shorter than original channel
     */
    @Test
    public void testAdd1() {
        double[] lchannel = {1.0, 0.5, -0.5, -1.0, 1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0, 1.5};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1, 0.6, -0.4, -1, 1.0};
        double[] rchannelAdded = {1, .3, -.15, -1, 1};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }

    /**
     * test adding channels of varying lengths - added channel longer than original channel
     */
    @Test
    public void testAdd2() {
        double[] lchannel = {1.0, 0.5, -0.5};
        double[] rchannel = {1.0, 0.2, -0.3};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1, 0.6, -0.4, -0.5};
        double[] rchannelAdded = {1, .3, -.15, -.5};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }

    /**
     * testing echo function at indexes before the echo,
     * during the echo, and after the initial SoundWave
     */
    @Test
    public void testEcho() {
        double alpha = 0.2;
        int delta = 2 * 44100;

        double freq = 70;
        double amp = .5;
        double phase = 2;
        double duration = 5;

        SoundWave wave = new SoundWave(freq, phase, amp, duration);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double [] rchannelEchoed = echoedWave.getRightChannel();
        double [] lchannelEchoed = echoedWave.getLeftChannel();

        double calculatedValueAtOneSecond = amp * Math.sin(2 * Math.PI * freq + phase);
        double calculatedValueAtTwoAndHalfSeconds = amp * Math.sin(2 * 2.5 * Math.PI * freq + phase)
                + 0.2 * amp * Math.sin(2 * 0.5 * Math.PI * freq + phase);
        double calculatedValueAtSixSeconds =  0.2 * amp * Math.sin(2 * 4 * Math.PI * freq + phase);

        double valueAtOneSecond = rchannelEchoed[44100];
        double valueAtTwoAndHalfSeconds = rchannelEchoed[(int) (44100 * 2.5)];
        double valueAtSixSeconds = lchannelEchoed[(44100 * 6)];

        Assert.assertEquals(valueAtOneSecond, calculatedValueAtOneSecond, 0.00001);
        Assert.assertEquals(valueAtTwoAndHalfSeconds, calculatedValueAtTwoAndHalfSeconds, 0.00001);
        Assert.assertEquals(valueAtSixSeconds, calculatedValueAtSixSeconds, 0.00001);
    }

    /**
     * testing echo with delta greater than or equal to array length
     */

    @Test
    public void testEchoLargeDelta() {
        double[] lchannel = {1, 0.5, 0.2, -1, 0.75, -0.1};
        double[] rchannel = {0.6, -0.7, 0.95, 1, 0.5, 0.35};
        double[] lchannelManualEcho = {1, 0.5, 0.2, -1, 0.75, -0.1};
        double alpha = 1;
        int delta = 6;

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double[] lchannelEchoed = echoedWave.getLeftChannel();

        Assert.assertArrayEquals(lchannelEchoed, lchannelManualEcho, 0.0001);
    }

    /**
     * testing echo with alpha = 1
     */
    @Test
    public void testManualEcho() {
        double [] lchannel = {1, 0.5, 0.2, -1, 0.75, -0.1};
        double [] rchannel = {0.6, -0.7, 0.95, 1, 0.5, 0.35};
        double [] lchannelManualEcho = {1, 0.5, 1, -.5, 0.95, -1, 0.75, -0.1};
        double alpha = 1;
        int delta = 2;

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double [] lchannelEchoed = echoedWave.getLeftChannel();

        Assert.assertArrayEquals(lchannelEchoed, lchannelManualEcho, 0.0001);

    }

    /**
     * testing echo with only rchannel affected
     */

    @Test
    public void testrchannelEcho() {
        double [] lchannel = {1, 0.5, 0.2};
        double [] rchannel = {0.6, -0.7, 0.95, 1, 0.5, 0.35};
        double [] lchannelManualEcho = {1, 0.5, 0.2};
        double [] rchannelManualEcho = {0.6, -0.7, 0.95, 1, 0.8, 0, 0.475, 0.5, 0.25, 0.175};
        double alpha = 0.5;
        int delta = 4;

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double [] lchannelEchoed = echoedWave.getLeftChannel();
        double [] rchannelEchoed = echoedWave.getRightChannel();

        Assert.assertArrayEquals(lchannelEchoed, lchannelManualEcho, 0.0001);
        Assert.assertArrayEquals(rchannelEchoed, rchannelManualEcho, 0.0001);
    }

    /**
     * testing echo with only lchannel affected
     */

    @Test
    public void testlchannelEcho() {
        double [] rchannel = {1, 0.5, 0.2};
        double [] lchannel = {0.6, -0.7, 0.95, 1, 0.5, 0.35};
        double [] rchannelManualEcho = {1, 0.5, 0.2};
        double [] lchannelManualEcho = {0.6, -0.7, 0.95, 1, 0.8, 0, 0.475, 0.5, 0.25, 0.175};
        double alpha = 0.5;
        int delta = 4;

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double [] lchannelEchoed = echoedWave.getLeftChannel();
        double [] rchannelEchoed = echoedWave.getRightChannel();

        Assert.assertArrayEquals(lchannelEchoed, lchannelManualEcho, 0.0001);
        Assert.assertArrayEquals(rchannelEchoed, rchannelManualEcho, 0.0001);

    }


    /**
     * test scale function and ability to cap off values at min/max (-1/+1)
     */
    @Test
    public void testScaling() {
        double [] lchannel = {1, 0.5, 0.2, -1, 0.75, -0.1};
        double [] rchannel = {0.6, -0.7, 0.95, 1, 0.5, 0.35};
        double [] lchannelManualScale = {1, 0.75, 0.3, -1, 1, -0.15};
        double [] rchannelManualScale = {0.9, -1, 1, 1, 0.75, .525};
        double scale = 1.5;

        SoundWave wave = new SoundWave(lchannel, rchannel);
        wave.scale(scale);

        double [] lchannelScaled = wave.getLeftChannel();
        double [] rchannelScaled = wave.getRightChannel();


        Assert.assertArrayEquals(rchannelManualScale, rchannelScaled, 0.0001);
        Assert.assertArrayEquals(lchannelManualScale, lchannelScaled, 0.0001);
    }

    /**
     * testing high pass filter at various indexes
     */

    @Test
    public void testHPF() {
        int interval = 2 * 44100;
        double timeConstant = 2 * 44100;
        double alpha = timeConstant / (timeConstant + interval);

        double [] rchannel = {-.9, 0.1, 0.65, 1, 0, .75};
        double [] lchannel = {0.5, 1, 0.75, -0.5, 0.9, 0.2};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave hpfWave = wave.highPassFilter(interval, timeConstant);
        double [] rchannelHPF = hpfWave.getRightChannel();
        double [] lchannelHPF = hpfWave.getLeftChannel();


        double value2 = rchannelHPF[2];
        double calculatedValue2 = alpha * alpha * (rchannel[0]
                + rchannel[1] - rchannel[0]) + alpha * (rchannel[2] - rchannel[1]);

        double value5 = rchannelHPF[5];
        double calculatedValue5 = alpha * (rchannel[5] - rchannel[4])
                + alpha * (alpha * (alpha * calculatedValue2 + alpha
                       * (rchannel[3] - rchannel[2])) + alpha * (rchannel[4] - rchannel[3]));

        Assert.assertEquals(calculatedValue2, value2, 0.0001);
        Assert.assertEquals(calculatedValue5, value5, 0.0001);

    }

    /**
     * tests cutoff frequency feature of HPF
     */

    @Test
    public void testHPF2() {
        SoundWave wave1 = new SoundWave(200, 0, 0, 1);
        SoundWave wave2 = new SoundWave(100, 0, 0, 1);
        wave1.checksLength();
        wave2.checksLength();
        wave1.checksWavesLength(wave2);
        double cutoff = 123;
        SoundWave wave3 = wave1.add(wave2);
        SoundWave wave4 =
                wave3.highPassFilter(1, SoundWave.SAMPLES_PER_SECOND / (2 * Math.PI * cutoff));

        Assert.assertArrayEquals(wave1.getLeftChannel(), wave4.getLeftChannel(), 0.0001);
    }

    /**
     * tests basic DFT function
     */

    @Test
    public void testDFT() {
        SoundWave wave = new SoundWave(500.0, 0.0, 0.3, 0.03);
        double DFT = wave.highAmplitudeFreqComponent();
        double ans = 500;

        Assert.assertEquals(ans, DFT, 44100.0 / (44100.0 * 0.03));
    }

    /**
     * checks DFT for real sinusoidal waves
     */

    @Test
    public void testDFTRealWave() {
        SoundWave wave1 = new SoundWave(501, 0, 1, 0.05);
        SoundWave wave2 = new SoundWave(400, 0, 0.5, 0.05);

        SoundWave wave3 = wave1.add(wave2);
        double DFT = wave3.highAmplitudeFreqComponent();
        double ans = 501;

        Assert.assertEquals(ans, DFT, 44100.0 / (44100.0 * 0.05));
    }

    /**
     * check DFT with TA's inputs
     */

    @Test
    public void testDFT1() {
        SoundWave wave1 = new SoundWave(201, 0, 0.6, 0.05);
        SoundWave wave2 = new SoundWave(1552, 0, 0.4, 0.05);
        wave1.append(wave2);

        double DFT = wave1.highAmplitudeFreqComponent();
        double ans = 200;

        Assert.assertEquals(ans, DFT, 1);
    }

    /**
     *  check contains for no scaling factor
     */

    @Test
    public void testContains() {
        double[] rchannel = {1, 1, 0.5, 0.5, 1, 1, 0.5, -1};
        double[] lchannel = {0.75, 0.75, 0.5, 0.25, 1, -1, 1, 1};
        double[] rcontained = {0.5, 0.5, 1};
        double[] lcontained = {0.5, 0.25, 1};

        SoundWave contained = new SoundWave(lcontained, rcontained);
        SoundWave wave = new SoundWave(lchannel, rchannel);

        boolean ans = wave.contains(contained);

        Assert.assertEquals(ans, true);
    }

    /**
     * check contains with negative scaling factor
     */

    @Test
    public void testContainsScaling() {

        double[] rchannel = {1, 1, 0.5, 0.5, 1, 1, 0.5, -1};
        double[] lchannel = {0.75, 0.75, 0.5, 0.25, 1, -1, 1, 1};
        double[] rcontained = {-0.5, -0.25, 0.5};
        double[] lcontained = {0.5, -0.5, -0.5};

        SoundWave contained = new SoundWave(lcontained, rcontained);
        SoundWave wave = new SoundWave(lchannel, rchannel);

        boolean ans = wave.contains(contained);

        Assert.assertEquals(ans, true);

    }

    /**
     * check contains with a sinusoidal wave
     */

    @Test
    public void testContains1() {

        SoundWave contained = new SoundWave(501, 2, 1, 4);
        SoundWave wave = new SoundWave(100, 0, 0.5, 2);

        wave.append(contained);

        boolean ans = wave.contains(contained);

        Assert.assertEquals(ans, true);
    }

    /**
     * check contains for false
     */

    @Test
    public void testContainsFalse() {
        double[] rchannel = {1, 1, 0.5, 0.5, 1, 1, 0.5, -1};
        double[] lchannel = {0.75, 0.75, 0.5, 0.25, 1, -1, 1, 1};
        double[] rcontained = {0.2, 0.5, 1};
        double[] lcontained = {0.5, 0.25, 1};

        SoundWave contained = new SoundWave(lcontained, rcontained);
        SoundWave wave = new SoundWave(lchannel, rchannel);

        boolean ans = wave.contains(contained);

        Assert.assertEquals(ans, false);
    }

    /**
     * test contains with different channel lengths
     */

    @Test
    public void testContainsDifferentChannelLengths() {
        double[] rchannel = {1, 1, 0.5, 0.5, 1, 1, 0.5, 1, 2, 3};
        double[] lchannel = {0.75, 0.75, 0.5, 0.25, 1, -1, 1, 1};
        double[] rcontained = {1, 0.5, 0.5};
        double[] lcontained = {0.75, 0.5, 0.25};

        SoundWave contained = new SoundWave(lcontained, rcontained);
        SoundWave wave = new SoundWave(lchannel, rchannel);

        boolean ans = wave.contains(contained);

        Assert.assertEquals(ans, true);
    }

    /**
     * test similarity for different channel lengths:
     */

    @Test
    public void testSimilarityDiffChannelLengths() {
        double[] rchannel1 = {1.0, 2.0, 3.0, 4.0};
        double[] lchannel1 = {1.0, 2.0, 3.0, 4.0};
        SoundWave s = new SoundWave(lchannel1, rchannel1);
        double[] rchannel2 = {1.0, 2.0, 3.0};
        double[] lchannel2 = {1.0, 2.0, 3.0, 4.0};
        SoundWave other = new SoundWave(lchannel2, rchannel2);

        double ans = 0.0686788;
        double similarity = s.similarity(other);

        Assert.assertEquals(ans, similarity, 0.0001);
    }

    /**
     * test similarity for waves of different lengths and channels of different lengths:
     */

    @Test
    public void testSimilarityDiffWaveLengths() {
        double[] rchannel1 = {0.5, 2.0, 3.2, 4.1};
        double[] lchannel1 = {0.1, 0.2, 0.3, 0.4, 0.5, 0.7, 0.8};
        SoundWave s = new SoundWave(lchannel1, rchannel1);
        double[] rchannel2 = {0.2, 0.6, 0.7};
        double[] lchannel2 = {0.3, 2.1};
        SoundWave other = new SoundWave(lchannel2, rchannel2);

        double ans = 0.1007566;
        double similarity = s.similarity(other);

        Assert.assertEquals(ans, similarity, 0.0001);
    }

    /**
     * test similarity for when similarity is 1:
     */
    @Test
    public void testSimilarityEqualsOne() {
        double[] rchannel1 = {0, 0, 0, 0};
        double[] lchannel1 = {0, 0, 0, 0, 0, 0};
        SoundWave s = new SoundWave(lchannel1, rchannel1);
        double[] rchannel2 = {0, 0, 0};
        double[] lchannel2 = {0, 0, 0, 0, 0, 0, 0};
        SoundWave other = new SoundWave(lchannel2, rchannel2);

        double ans = 1;
        double similarity = s.similarity(other);

        Assert.assertEquals(ans, similarity, 0.000001);
    }

    /**
     * test similarity for when beta is equal to zero:
     */

    @Test
    public void testSimilarityBetaEqualsOne() {
        double[] rchannel1 = {0, 0, 0, 0};
        double[] lchannel1 = {0, 0, 0, 0};
        SoundWave s = new SoundWave(lchannel1, rchannel1);
        double[] rchannel2 = {0.5, 1.0, 1.5, 2.0};
        double[] lchannel2 = {1.0, 1.5, 2.0, 2.5};
        SoundWave other = new SoundWave(lchannel2, rchannel2);

        double ans = 0.52273;
        double similarity = s.similarity(other);

        Assert.assertEquals(ans, similarity, 0.00001);
    }

}




