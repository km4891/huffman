
public class App {

    //for all ASCII characters
    private static final int ALPHABET_SIZE= 256;

    public HuffmanEncodedResult compress (final String data){
        
        final int[] freq = buildFrequencyTable(data);

        return null;
    }

    private static Node buildHuffmanTree (int[] freq) {

    }

    private static int[] buildFrequencyTable (final String data){
        final int [] freq = new int[ALPHABET_SIZE];
        for(final char character : data.toCharArray()) {
            freq[character]++; //characters can be treated like integers

        }
        
        return freq;
    }

    public String decompress (final HuffmanEncodedResult result) {
        return null;
    }

    static class Node {

        private final char character;
        private final int frequency;
        private final Node leftChild;
        private final Node rightChild;

        private Node(final char character,
                     final int frequency,
                     final Node leftChild,
                     final Node rightChild) {
            this.character = character;
            this.frequency = frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            }
        boolean isLeaf() {
            return this.leftChild == null && this.rightChild == null;
        }
    }

    static class HuffmanEncodedResult {

    }
	public static void main(String[] args) {
        final String test = "abcdeffg";
        final int[] ft = buildFrequencyTable(test);
        System.out.println(ft);
	}

}