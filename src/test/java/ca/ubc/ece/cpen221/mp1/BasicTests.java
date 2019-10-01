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
        double[] rchannel = {1.0, 0.2, -0.3, -1.0};
        double[] lchannelAdd = {.5, .1, .1, -.5};
        double[] rchannelAdd = {1.0, .1, .15, -.5};
        double[] lchannelAdded = {1.5, 0.6, -0.4, -1.5, 1.0};
        double[] rchannelAdded = {2, .3, -.15, -1.5};

        SoundWave wave = new SoundWave(lchannel, rchannel);
        SoundWave toAdd = new SoundWave(lchannelAdd, rchannelAdd);

        wave =  wave.add(toAdd);

        double[] lchannel1 = wave.getLeftChannel();

        double[] rchannel1 = wave.getRightChannel();

        Assert.assertArrayEquals(rchannelAdded, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannelAdded, lchannel1, 0.00001);

    }
}




