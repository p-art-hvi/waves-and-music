package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BasicTests {

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

    @Test
    public void testWaveFromScratch() {

        double freq = 70;
        double amp = .5;
        double phase = 2;
        double duration = 5;
        double time = 0;
        double [] rchannel = new double[5*44100 + 1];
        double [] lchannel = new double[5*44100 + 1];
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

    @Test
    public void testAdd() {
        double[] lchannel = {1.0, 0.5, -0.5, -1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1.5, 0.6, -0.4, -1.5};
        double[] rchannelAdded = {2, .3, -.15, -1.5};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }

    @Test
    public void testAdd1() {
        double[] lchannel = {1.0, 0.5, -0.5, -1.0, 1.0};
        double[] rchannel = {1.0, 0.2, -0.3, -1.0, 1.5};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1.5, 0.6, -0.4, -1.5, 1.0};
        double[] rchannelAdded = {2, .3, -.15, -1.5, 1.5};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }

    @Test
    public void testAdd2() {
        double[] lchannel = {1.0, 0.5, -0.5};
        double[] rchannel = {1.0, 0.2, -0.3};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1.5, 0.6, -0.4, -0.5};
        double[] rchannelAdded = {2, .3, -.15, -.5};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }

    @Test
    public void testEcho() {
        double alpha = 0.2;
        int delta = 2;

        double freq = 70;
        double amp = .5;
        double phase = 2;
        double duration = 5;
        double [] rchannel = new double[5*44100 + 1];
        double [] lchannel = new double[5*44100 + 1];

        SoundWave wave = new SoundWave(freq, phase, amp, duration);
        SoundWave echoedWave = wave.addEcho(delta, alpha);
        double [] rchannelEchoed = echoedWave.getRightChannel();
        double [] lchannelEchoed = echoedWave.getLeftChannel();

        double calculatedValueAtOneSecond = amp * Math.sin(2 * Math.PI * freq + phase);
        double calculatedValueAtTwoAndHalfSeconds = amp * Math.sin(2 * 2.5 * Math.PI * freq + phase)
                + 0.2 * amp * Math.sin(2 * 0.5 * Math.PI * freq + phase);
        double calculatedValueAtSixSeconds =  0.2 * amp * Math.sin(2 * 4 * Math.PI * freq + phase);

        double valueAtOneSecond = rchannelEchoed[44100];
        double valueAtTwoAndHalfSeconds = rchannelEchoed[(int)(44100 * 2.5)];
        double valueAtSixSeconds = lchannelEchoed[(44100 * 6)];

        Assert.assertEquals(valueAtOneSecond, calculatedValueAtOneSecond, 0.00001);
        Assert.assertEquals(valueAtTwoAndHalfSeconds, calculatedValueAtTwoAndHalfSeconds, 0.00001);
        Assert.assertEquals(valueAtSixSeconds, calculatedValueAtSixSeconds, 0.00001);
    }

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

    @Test
    public void testHPF() {
        int interval = 2;
        double timeConstant = 2;
        double alpha = 0.5;

        double freq = 70;
        double amp = .5;
        double phase = 2;
        double duration = 5;
        double [] rchannel = new double[5*44100 + 1];
        double [] lchannel = new double[5*44100 + 1];

        SoundWave wave = new SoundWave(freq, phase, amp, duration);
        SoundWave hpfWave = wave.highPassFilter(interval, timeConstant);
        double [] rchannelHPF = hpfWave.getRightChannel();
        double [] lchannelHPF = hpfWave.getLeftChannel();

    }
}




