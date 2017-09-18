package pt.uminho.haslab.smhbase.sharmind.helpers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author roger
 */
public class ValuesGenerator {
    // Tests will run for numbers that use 80 bits at most.
    public static final int maxBits = 63;
    public static final int nValues = 1;
    public static final int batchValues = 100;
    private final static SecureRandom generator = new SecureRandom();

    /* Number of bits must be greater than 0 */
    private static int genNumberBits(int maxBits) {
        int nBits = generator.nextInt(maxBits);
        if (nBits == 0) {
            return 1;
        }
        return nBits;

    }

    public static Collection SingleValueGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            int genNbits = genNumberBits(maxBits);
            BigInteger genValue = new BigInteger(genNbits, generator);

            Object[] parameter = new Object[2];
            parameter[0] = genNbits;
            parameter[1] = genValue;
            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection SingleBatchValueGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            int genNbits = genNumberBits(maxBits);
            List<BigInteger> bvals = new ArrayList<BigInteger>();
            for (int j = 0; j < batchValues; j++) {
                BigInteger genValue = new BigInteger(genNbits, generator);
                bvals.add(genValue);
            }
            Object[] parameter = new Object[2];
            parameter[0] = genNbits;
            parameter[1] = bvals;
            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection ShareConvGenerator() {
        Object[] parameters = new Object[nValues];
        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);
            BigInteger genValueOne = new BigInteger(1, generator);
            BigInteger genValueTwo = new BigInteger(1, generator);
            BigInteger genValueThree = new BigInteger(1, generator);

            Object[] parameter = new Object[4];
            parameter[0] = genNbits;
            parameter[1] = genValueOne;
            parameter[2] = genValueTwo;
            parameter[3] = genValueThree;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection shareBatchConvGenerator() {
        Object[] parameters = new Object[nValues];
        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);
            List<BigInteger> genValuesOne = new ArrayList<BigInteger>();
            List<BigInteger> genValuesTwo = new ArrayList<BigInteger>();
            List<BigInteger> genValuesThree = new ArrayList<BigInteger>();

            for (int j = 0; j < batchValues; j++) {
                BigInteger genValueOne = new BigInteger(1, generator);
                BigInteger genValueTwo = new BigInteger(1, generator);
                BigInteger genValueThree = new BigInteger(1, generator);
                genValuesOne.add(genValueOne);
                genValuesTwo.add(genValueTwo);
                genValuesThree.add(genValueThree);
            }

            Object[] parameter = new Object[4];
            parameter[0] = genNbits;
            parameter[1] = genValuesOne;
            parameter[2] = genValuesTwo;
            parameter[3] = genValuesThree;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection BinaryValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            BigInteger genValueOne = new BigInteger(1, generator);
            BigInteger genValueTwo = new BigInteger(1, generator);

            Object[] parameter = new Object[3];
            parameter[0] = 1;
            parameter[1] = genValueOne;
            parameter[2] = genValueTwo;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection BinaryBatchValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            List<BigInteger> firstValues = new ArrayList<BigInteger>();
            List<BigInteger> secondValues = new ArrayList<BigInteger>();

            for (int j = 0; j < batchValues; j++) {
                BigInteger genValueOne = new BigInteger(1, generator);
                BigInteger genValueTwo = new BigInteger(1, generator);
                firstValues.add(genValueOne);
                secondValues.add(genValueTwo);
            }
            Object[] parameter = new Object[3];
            parameter[0] = 1;
            parameter[1] = firstValues;
            parameter[2] = secondValues;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection TwoValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);

            BigInteger genValueOne = new BigInteger(genNbits, generator);
            BigInteger genValueTwo = new BigInteger(genNbits, generator);

            Object[] parameter = new Object[3];
            parameter[0] = genNbits;
            parameter[1] = genValueOne;
            parameter[2] = genValueTwo;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection TwoValuesBatchGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);

            List<BigInteger> firstValues = new ArrayList<BigInteger>();
            List<BigInteger> secondValues = new ArrayList<BigInteger>();

            for (int j = 0; j < batchValues; j++) {
                BigInteger genValueOne = new BigInteger(genNbits, generator);
                BigInteger genValueTwo = new BigInteger(genNbits, generator);
                firstValues.add(genValueOne);
                secondValues.add(genValueTwo);
            }

            Object[] parameter = new Object[3];
            parameter[0] = genNbits;
            parameter[1] = firstValues;
            parameter[2] = secondValues;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection TwoLongValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {
            int genNbits = 63;
            BigInteger genValueOne = new BigInteger(genNbits, generator);
            BigInteger genValueTwo = new BigInteger(genNbits, generator);
            Object[] parameter = new Object[3];
            parameter[0] = genNbits;
            parameter[1] = genValueOne;
            parameter[2] = genValueTwo;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    public static Collection shiftValueGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);
            int shiftN = genNumberBits(genNbits);

            BigInteger genValueOne = new BigInteger(genNbits, generator);

            Object[] parameter = new Object[3];
            parameter[0] = genNbits;
            parameter[1] = shiftN;
            parameter[2] = genValueOne;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    /*
     * Generate values for the Bitconj Test Cases. It generates three Values,p1,
     * p2, p3. p1 = 11111...111 p2 = p3 With these values the result of bitConj
     * must be 1.
     */
    public static Collection BitConjEqualValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            int genNbits = genNumberBits(maxBits);
            BigInteger max = BigInteger.valueOf(2).pow(genNbits)
                    .subtract(BigInteger.ONE);
            BigInteger genValue = new BigInteger(genNbits, generator);

            Object[] parameter = new Object[4];
            parameter[0] = genNbits;
            parameter[1] = max;
            parameter[2] = genValue;
            parameter[3] = genValue;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

    /*
     * Generate values for the Bitconj Test Cases. It generates three Values,p1,
     * p2, p3. p1 = 11111...111 p2 = p3 With these values the result of bitConj
     * must be 1.
     */
    public static Collection BitConjDiffValuesGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {

            int genNbits = genNumberBits(maxBits);
            BigInteger genValueOne = new BigInteger(genNbits, generator);
            BigInteger genValueTwo = new BigInteger(genNbits, generator);

            if (genNbits > 0 && !genValueOne.equals(genValueTwo)) {
                BigInteger max = BigInteger.valueOf(2).pow(genNbits)
                        .subtract(BigInteger.ONE);

                Object[] parameter = new Object[4];
                parameter[0] = genNbits;
                parameter[1] = max;
                parameter[2] = genValueOne;
                parameter[3] = genValueTwo;

                parameters[i] = parameter;
            } else {
                i--;
            }
        }

        return Arrays.asList(parameters);
    }

    public static Collection SubVectorGenerator() {
        Object[] parameters = new Object[nValues];

        for (int i = 0; i < nValues; i++) {
            int genNbits = genNumberBits(maxBits);

            BigInteger genValueOne = new BigInteger(genNbits, generator);
            int start;
            int end;
            if (genNbits < 2) {
                start = 0;
                end = 0;
            } else {
                start = generator.nextInt(genNbits - 1);
                end = generator.nextInt(start + 1);
            }

            if (end < start) {
                int aux = start;
                start = end;
                end = aux;
            }

            Object[] parameter = new Object[4];
            parameter[0] = genNbits;
            parameter[1] = genValueOne;
            parameter[2] = start;
            parameter[3] = end;

            parameters[i] = parameter;
        }

        return Arrays.asList(parameters);
    }

}
