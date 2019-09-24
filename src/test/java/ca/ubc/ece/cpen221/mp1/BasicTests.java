package ca.ubc.ece.cpen221.mp1;

import org.junit.Assert;
import org.junit.Test;
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

        //TODO: complete test case
    }

    // TODO: add more tests
    @Test
    public void append(double[] lchannel, double[] rchannel){

    }
}
