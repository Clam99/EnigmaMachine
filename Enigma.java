import java.util.*;

public class Enigma {
    private Rotor[] rotors;
    private Rotor reflector;
    private Plugboard pb;
    public Enigma(int[] rotorNums, char[] start, char[] ring, String[] plug) throws ArrayIndexOutOfBoundsException {
        if (rotorNums.length != 4 || start.length != 3) {
            ArrayIndexOutOfBoundsException up = new ArrayIndexOutOfBoundsException();
            throw up;//ha ha
        }
        Rotor r1 = new Rotor(rotorNums[0], (int)Character.toUpperCase(start[0])-65, (int)Character.toUpperCase(ring[0])-65, false);//fast rotor
        Rotor r2 = new Rotor(rotorNums[1], (int)Character.toUpperCase(start[1])-65, (int)Character.toUpperCase(ring[1])-65, false);//medium rotor
        Rotor r3 = new Rotor(rotorNums[2], (int)Character.toUpperCase(start[2])-65, (int)Character.toUpperCase(ring[2])-65, false);//slow rotor
        reflector = new Rotor(rotorNums[3], 0, 0, true);
        rotors = new Rotor[3];
        rotors[0] = r1;
        rotors[1] = r2;
        rotors[2] = r3;
        pb = new Plugboard(plug);
    }
    public String encrypt(String s) {
        String out = "";
        for (int i = 0;i<s.length();i++) {//remove spaces
            if (((int)s.charAt(i)<=90 && (int)s.charAt(i)>=65) || ((int)s.charAt(i)<=122 && (int)s.charAt(i)>=97)) {
                increment(); //incrementation happens before encryption!!
                out += Character.toString(encrypt(s.charAt(i)));
            }
        }
        return out;
    }
    public char encrypt(char c) {
        c = Character.toUpperCase(c);
        c = pb.output(c);
        for (int i = 0; i<rotors.length;i++) {
            c = rotors[i].getOutput(c);
        }
        c = reflector.getOutput(c);
        for (int i = rotors.length-1; i>=0; i--) {
            c = rotors[i].getReversedOutput(c);
        }
        c = pb.output(c);
        return c;
    }
    public void increment() {
        if (rotors[1].needIncrement()) {
        	rotors[1].increment();
        	rotors[2].increment();
        }
        else if (rotors[0].needIncrement()) {
            rotors[1].increment();
        }
        rotors[0].increment();
    }
}
