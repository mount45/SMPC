package pt.uminho.haslab.smpc.sharmind.batch;

import org.junit.runners.Parameterized;
import pt.uminho.haslab.smpc.exceptions.InvalidNumberOfBits;
import pt.uminho.haslab.smpc.exceptions.InvalidSecretValue;
import pt.uminho.haslab.smpc.interfaces.Player;
import pt.uminho.haslab.smpc.interfaces.Players;
import pt.uminho.haslab.smpc.sharemindImp.BigInteger.SharemindSecretFunctions;
import pt.uminho.haslab.smpc.sharemindImp.BigInteger.SharemindSharedSecret;
import pt.uminho.haslab.smpc.sharmind.helpers.BatchDbTest;
import pt.uminho.haslab.smpc.sharmind.helpers.ValuesGenerator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OverflowProtocolTest extends BatchProtocolTest {

    private final List<byte[]> valuesOne;
    private final List<byte[]> valuesTwo;
    private final List<byte[]> valuesThree;

    public OverflowProtocolTest(int nbits, List<BigInteger> u2s,
                                List<BigInteger> u3s) {
        super(nbits);
        valuesOne = new ArrayList<byte[]>();
        valuesTwo = new ArrayList<byte[]>();
        valuesThree = new ArrayList<byte[]>();

       for (int i = 0; i < 1; i++) {
            valuesOne.add(BigInteger.ZERO.toByteArray());
            valuesTwo.add(u2s.get(i).toByteArray());
            valuesThree.add(u3s.get(i).toByteArray());
        }
    }

    @Parameterized.Parameters
    public static Collection nbitsValues() {
        return ValuesGenerator.TwoValuesBatchGenerator();
    }

    @Override
    public List<BatchDbTest> prepareDatabases(Players players)
            throws InvalidNumberOfBits, InvalidSecretValue {
        Player p0 = players.getPlayer(0);
        Player p1 = players.getPlayer(1);
        Player p2 = players.getPlayer(2);

        Db rdb0 = new Db(valuesOne, p0);
        Db rdb1 = new Db(valuesTwo, p1);
        Db rdb2 = new Db(valuesThree, p2);

        List<BatchDbTest> result = new ArrayList<BatchDbTest>();

        result.add(rdb0);
        result.add(rdb1);
        result.add(rdb2);

        return result;

    }

    @Override
    public void condition(BatchDbTest db1, BatchDbTest db2, BatchDbTest db3) {
        List<byte[]> res1 = db1.getResult();
        List<byte[]> res2 = db2.getResult();
        List<byte[]> res3 = db3.getResult();

        for (int i = 0; i < res1.size(); i++) {
            BigInteger u1 = new BigInteger(res1.get(i));
            BigInteger u2 = new BigInteger(res2.get(i));
            BigInteger u3 = new BigInteger(res3.get(i));
            BigInteger valueTwo = new BigInteger(valuesTwo.get(i));
            BigInteger valueThree = new BigInteger(valuesThree.get(i));
            SharemindSharedSecret secret = new SharemindSharedSecret(1, u1, u2,
                    u3);

            BigInteger mod = BigInteger.valueOf(2).pow(nbits);
            int compared = valueTwo.compareTo(BigInteger.ZERO.subtract(
                    valueThree).mod(mod));

            if (!valueThree.equals(BigInteger.ZERO)
                    && (compared == 1 || compared == 0)) {
                System.out.println("expected 1 and res is " +  secret.unshare());
                assertEquals(BigInteger.ONE, secret.unshare());
            } else {
                assertEquals(BigInteger.ZERO, secret.unshare());

            }

        }
    }

    private class Db extends BatchDbTest {

        private final Player player;

        public Db(List<byte[]> firstShares, Player player) {
            super(firstShares);
            this.player = player;

        }

        @Override
        public void run() {
            try {
                BigInteger mod = BigInteger.valueOf(2).pow(nbits);

                SharemindSecretFunctions ssf = new SharemindSecretFunctions(
                        nbits, mod);
                super.protocolResults = ssf.overflow(super.secrets, player);
            } catch (InvalidNumberOfBits ex) {
                throw new IllegalStateException(ex);
            } catch (InvalidSecretValue ex) {
                throw new IllegalStateException(ex);
            }
        }

    }

}
