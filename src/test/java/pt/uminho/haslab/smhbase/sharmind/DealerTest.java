package pt.uminho.haslab.smhbase.sharmind;

import pt.uminho.haslab.smhbase.exceptions.InvalidNumberOfBits;
import pt.uminho.haslab.smhbase.exceptions.InvalidSecretValue;
import pt.uminho.haslab.smhbase.sharemindImp.SharemindDealer;
import pt.uminho.haslab.smhbase.interfaces.SharedSecret;
import pt.uminho.haslab.smhbase.interfaces.Dealer;
import java.math.BigInteger;
import java.util.Collection;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pt.uminho.haslab.smhbase.sharemindImp.SharemindBitVectorDealer;
import pt.uminho.haslab.smhbase.sharmind.helpers.ValuesGenerator;

@RunWith(Parameterized.class)
public class DealerTest {

	// Tests will run for numbers that use 80 bits at most.

	private final BigInteger value;
	private final int nbits;

	public DealerTest(int nbits, BigInteger value) {
		this.value = value;
		this.nbits = nbits;
	}

	@Parameterized.Parameters
	public static Collection nbitsValues() {
		return ValuesGenerator.SingleValueGenerator();
	}

	@Test
	public void shareSecret() throws InvalidNumberOfBits, InvalidSecretValue {

		Dealer dealer = new SharemindDealer(nbits);

		SharedSecret secret = dealer.share(value);

		assertEquals(secret.unshare().equals(value), true);

	}

	@Test
	public void shareBitVectorSecret() throws InvalidNumberOfBits,
			InvalidSecretValue {

		Dealer dealer = new SharemindBitVectorDealer(nbits);

		SharedSecret secret = dealer.share(value);

		assertEquals(secret.unshare().equals(value), true);

	}
}