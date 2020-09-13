import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class music {
    static int sampleRate = 8000;

    public static void main(String[] args) {
        // Input scanning / assigning

        /**
         * FORMAT:
         * 	  (Duration), type, octave, note name, accidental
         *    11n4An
         *
         * 
         *    DURATION:
         *    	 description: The length/duration an inputed note should last (note/bar)
         *    	 format: (note/bar)
         *    	 variables: note - is a number between 1 and infinity.
         *    	             bar - is total amount of notes per bar
         *
         *    	 example: (3/1) would be an eight-note triplet 4/4 time.
         *    	 example: (5/1) would be an eight-note 5-let 4/4 time.
         *    	 example: (4/1) would be a quarter note in 4/4 time.
         *
         *
         * 	  TYPE:
         * 		 description: The type of object being inputed.
         * 		 format: type
         * 		 variables: n - note
         * 					r - rest
         *
         * 		 example: n would set the object to a note
         *		 example: r would set the object to a rest
         *
         * 
         *    OCTAVE:
         *       variables: octave - int
         *
         * 
         * 	  NAME:
         * 		 description: Note name
         * 		 format: name
         * 		 variables: name - a letter between A and G
         *
         * 
         *	  ACCIDENTAL:
         *		 description: accidental of a note
         *		 format: accidental
         *		 variables: # - sharp
         *					b - flat
         *					n - natural
         *
         * 
         *	  DYMANIC:
         *		 description: the volume of a note
         *		 format: dynamic
         *		 variables: 0 - off
         *					1 - ppp
         *					2 - pp
         *					3 - p
         *					4 - mp
         *					5 - mf
         *					6 - f
         *					7 - ff
         *					8 - fff
         */

        final AudioFormat af = new AudioFormat(sampleRate, 16, 1, true, true);
        Scanner input = new Scanner(System.in);
        try {
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af);
            line.start();
            System.out.print("BPM: ");
            String Bbpm = input.nextLine();
            int bpm = Integer.parseInt(Bbpm);
            System.out.print("Print music trace: ");
            String toDecode = input.nextLine().replaceAll(" ", "");
            System.out.println(toDecode);
            System.out.println(toDecode);
            String[] str = toDecode.split(",");
            List<String> dList = new ArrayList<String>();
            dList = Arrays.asList(str);
            System.out.println("-----");

            for (int i = 0; i < dList.size(); i++) {
                //114An   (Duration), type, octave, note name, accidental
                int frame = Integer.parseInt(dList.get(i).substring(0,1));
                int beats = Integer.parseInt(dList.get(i).substring(1,2));
                double duration = ((double) frame/beats)*((double) 60/bpm)*2;
                if (dList.get(i).substring(2,3).charAt(0) == 'r') {
                    System.out.println("Type: rest\nDuration: " + duration + "s\n");
                    Thread.sleep((long) (duration*1000));
                } else {
                    int octave = Integer.parseInt(dList.get(i).substring(2,3));
                    char noteName = dList.get(i).substring(3,4).charAt(0);
                    char accidental = dList.get(i).substring(4,5).charAt(0);
                    System.out.println("Note: " + noteName + accidental + " [+" + octave + "]\nDuration: " + duration + "s\n");
                    play(line, generateSineWavefreq(((Math.pow((Math.pow(Math.E, Math.log(2) / 12)), toNote(noteName, octave, accidental) - 49)) * 440), duration));
                    Thread.sleep((long) (100));
                }
            }
            line.drain();
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        input.close();
    }

    private static int toNote(char noteName, int octave, char accidental) {
        int noteNum = -1;
        String indexName = "" + noteName + accidental;
        if (octave == 0) {
            switch(indexName)
            {
                case "An":
                    noteNum = 1;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 2;
                    break;
                case "Bn":
                    noteNum = 3;
                    break;
            }
        } else if (octave == 1) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 4;
                    break;
                case "C#":
                case "Db":
                    noteNum = 5;
                    break;
                case "Dn":
                    noteNum = 6;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 7;
                    break;
                case "En":
                    noteNum = 8;
                    break;
                case "Fn":
                    noteNum = 9;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 10;
                    break;
                case "Gn":
                    noteNum = 11;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 12;
                    break;
                case "An":
                    noteNum = 13;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 14;
                    break;
                case "Bn":
                    noteNum = 15;
                    break;
            }
        } else if (octave == 2) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 16;
                    break;
                case "C#":
                case "Db":
                    noteNum = 17;
                    break;
                case "Dn":
                    noteNum = 18;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 19;
                    break;
                case "En":
                    noteNum = 20;
                    break;
                case "Fn":
                    noteNum = 21;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 22;
                    break;
                case "Gn":
                    noteNum = 23;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 24;
                    break;
                case "An":
                    noteNum = 25;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 26;
                    break;
                case "Bn":
                    noteNum = 27;
                    break;
            }
        } else if (octave == 3) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 28;
                    break;
                case "C#":
                case "Db":
                    noteNum = 29;
                    break;
                case "Dn":
                    noteNum = 30;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 31;
                    break;
                case "En":
                    noteNum = 32;
                    break;
                case "Fn":
                    noteNum = 33;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 34;
                    break;
                case "Gn":
                    noteNum = 35;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 36;
                    break;
                case "An":
                    noteNum = 37;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 38;
                    break;
                case "Bn":
                    noteNum = 39;
                    break;
            }
        } else if (octave == 4) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 40;
                    break;
                case "C#":
                case "Db":
                    noteNum = 41;
                    break;
                case "Dn":
                    noteNum = 42;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 43;
                    break;
                case "En":
                    noteNum = 44;
                    break;
                case "Fn":
                    noteNum = 45;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 46;
                    break;
                case "Gn":
                    noteNum = 47;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 48;
                    break;
                case "An":
                    noteNum = 49;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 50;
                    break;
                case "Bn":
                    noteNum = 51;
                    break;
            }
        } else if (octave == 5) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 52;
                    break;
                case "C#":
                case "Db":
                    noteNum = 53;
                    break;
                case "Dn":
                    noteNum = 54;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 55;
                    break;
                case "En":
                    noteNum = 56;
                    break;
                case "Fn":
                    noteNum = 57;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 58;
                    break;
                case "Gn":
                    noteNum = 59;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 60;
                    break;
                case "An":
                    noteNum = 61;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 62;
                    break;
                case "Bn":
                    noteNum = 63;
                    break;
            }
        } else if (octave == 6) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 64;
                    break;
                case "C#":
                case "Db":
                    noteNum = 65;
                    break;
                case "Dn":
                    noteNum = 66;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 67;
                    break;
                case "En":
                    noteNum = 68;
                    break;
                case "Fn":
                    noteNum = 69;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 70;
                    break;
                case "Gn":
                    noteNum = 71;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 72;
                    break;
                case "An":
                    noteNum = 73;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 74;
                    break;
                case "Bn":
                    noteNum = 75;
                    break;
            }
        } else if (octave == 7) {
            switch(indexName)
            {
                case "Cn":
                    noteNum = 76;
                    break;
                case "C#":
                case "Db":
                    noteNum = 77;
                    break;
                case "Dn":
                    noteNum = 78;
                    break;
                case "D#":
                case "Eb":
                    noteNum = 79;
                    break;
                case "En":
                    noteNum = 80;
                    break;
                case "Fn":
                    noteNum = 81;
                    break;
                case "F#":
                case "Gb":
                    noteNum = 82;
                    break;
                case "Gn":
                    noteNum = 83;
                    break;
                case "G#":
                case "Ab":
                    noteNum = 84;
                    break;
                case "An":
                    noteNum = 85;
                    break;
                case "A#":
                case "Bb":
                    noteNum = 86;
                    break;
                case "Bn":
                    noteNum = 87;
                    break;
            }
        } else if (octave == 8) {
            if ("Cn".equals(indexName)) {
                noteNum = 76;
            }
        }
        return noteNum;
    }

    private static byte[] generateSineWavefreq(double noteFreq2, double duration2) {
        // total samples = (duration in second) * (samples per second)
        byte[] sin = new byte[(int) (duration2 * sampleRate)];
        double samplingInterval = (double) (sampleRate / noteFreq2);
        for (int i = 0; i < sin.length; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            sin[i] = (byte) (Math.sin(angle) * 127);
        }
        return sin;
    }
    private static void play(SourceDataLine line, byte[] array) {
        int length = sampleRate * array.length / 1000;
        line.write(array, 0, array.length);
    }
}
// © 2019 Ryan Lahlou All Rights Reserved
