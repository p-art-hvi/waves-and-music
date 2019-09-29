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
        double [] rchannel = new double[5*44100];
        double [] lchannel = new double[5*44100];
        int i = 0;

        SoundWave wave = new SoundWave(freq, phase, amp, duration);
        double [] rchannel1 = wave.getRightChannel();
        double [] lchannel1 = wave.getLeftChannel();

        for (time = 0; time <= duration; time = time + 1.0 / (double) 44100) {
            double omega = 2 * Math.PI * freq * time;
            double yValue = amp * Math.sin(omega + phase);
            i++;
            rchannel[i] = yValue;
            lchannel[i] = yValue;
        }
        Assert.assertArrayEquals(rchannel, rchannel1, 0.00001);
        Assert.assertArrayEquals(lchannel, lchannel1, 0.00001);
    }

    // TODO: add more tests
    @Test
    public void append(double[] lchannel, double[] rchannel){

    }
}
