package mockito;

import org.constant.SeasonEnum;
import org.testng.annotations.Test;

import java.math.BigInteger;

public class MockitoTest
{
    @Test
    public void testBit()
    {
        System.out.println(1048576 / 1024);
    }

    private int bitCount(BigInteger x) {
        return x.bitCount();
    }
}
