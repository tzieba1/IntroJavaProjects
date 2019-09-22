public class ByteShortIntLong
  {

  public static void main(String[] args) {

    // int has a width of 32
    int myMinValue = -2147483648;
    int myMaxValue = 2_147_483_647;

    // byte has a width of 8
    byte myMinByteValue = -128;
    byte myMaxByteValue = 127;

    // Putting "(byte)" on the RHS and putting the expression on RHS in round
    // parentheses casts the expression as the type byte since Java will convert
    // the expression automatically to an int which will not fit in the declared
    // type, which is byte.
    byte myNewbyteValue = (byte) (myMinByteValue / 2);

    // short has a width of 16
    short myShortValue = 32767;

    //Casting a short.
    short myNewShortValue = (short) (myShortValue / 2);
    
    // long has a width of 64
    long myMinLongValue = -9_223_372_036_854_775_807L;
    long myMaxLongValue = 9_223_372_036_854_775_807L;
  }
}
