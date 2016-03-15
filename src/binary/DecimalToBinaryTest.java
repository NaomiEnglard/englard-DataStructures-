package binary;

import org.junit.Assert;
import org.junit.Test;

public class DecimalToBinaryTest {

	@Test
	public void testGetBinary(){
		DecimalToBinary dB = new DecimalToBinary();
			Assert.assertEquals(dB.getBinaryForm(19), 10011);
	}
}
