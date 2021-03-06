import java.util.*;

public class App {

    //for all ASCII characters
    private static final int ALPHABET_SIZE= 256;

    public HuffmanEncodedResult compress (final String data){
        
        final int[] freq = buildFrequencyTable(data);
        final Node root = buildHuffmanTree(freq);
        final Map<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
    }

    // get binary string for that particular character
    private static String generateEncodedData(final String data, final Map<Character, String> lookupTable) {

        final StringBuilder builder = new StringBuilder();
        for (final char character : data.toCharArray()) {
            builder.append(lookupTable.get(character));
        }
        return builder.toString();
    }
    // map each character to binary encoding, the more a char appears in a msg the shorter it's binary encoding will be
    private static Map<Character, String> buildLookupTable (final Node root){
        
        final Map<Character, String> lookupTable = new HashMap<>();

        buildLookupTableImpl(root, "",lookupTable);
        
        return lookupTable;

    }

    private static void buildLookupTableImpl(final Node node, final String s,final Map<Character,String> lookupTable) {

        if (!node.isLeaf()) {
            buildLookupTableImpl(node.leftChild, s + '0', lookupTable);
            buildLookupTableImpl(node.rightChild, s + '1', lookupTable);
        } else {
            lookupTable.put(node.character, s);
        }
    }
        // to build huffman tree
    private static Node buildHuffmanTree (int[] freq) {
        final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (char i = 0; i < ALPHABET_SIZE; i++) {
            if(freq[i] > 0) {
                priorityQueue.add(new Node (i, freq[i], null, null)); //make a new leaf node what it's freq is in that node and it has no L/R children
            }
        }
        // if message only has 1 char
        if(priorityQueue.size() == 1) {
            priorityQueue.add(new Node('\0', 1, null, null));
        }

        while(priorityQueue.size() > 1) {
            final Node left = priorityQueue.poll();
            final Node right = priorityQueue.poll();
            final Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll(); //returns root
    }

    private static int[] buildFrequencyTable (final String data){
        final int [] freq = new int[ALPHABET_SIZE];
        for(final char character : data.toCharArray()) {
            freq[character]++; //characters can be treated like integers

        }
        
        return freq;
    }

    public String decompress (final HuffmanEncodedResult result) {
        
        final StringBuilder resultBuilder = new StringBuilder();

        Node current = result.getRoot();

        int i = 0;
        while(i< result.getEncodedData().length()){

            while(!current.isLeaf()) {
                char bit = result.getEncodedData().charAt(i);
                if(bit == '1') {
                    current = current.rightChild;
                } else if (bit == '0') {
                    current = current.leftChild;
                } else {
                    throw new IllegalArgumentException("Invalid bit in message " + bit);
                }
                i++;
            }
            resultBuilder.append(current.character);
            current = result.getRoot();
        }

        return resultBuilder.toString();
    }

    static class Node implements Comparable<Node> {

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
        public int compareTo(final Node that) {
            
            final int frequencyComparison = Integer.compare(this.frequency, that.frequency);
            if (frequencyComparison != 0){
                return frequencyComparison;
            }
            return Integer.compare(this.frequency, that.frequency);
        }
    }

    static class HuffmanEncodedResult {

        final Node root;
        final String encodedData;

        HuffmanEncodedResult(final String encodedData, final Node root) {
            this.encodedData = encodedData;
            this.root = root;
        }
        public Node getRoot() {
            return this.root;
        }

        
        public String getEncodedData() {
            return this.encodedData;
        }
    }
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String message;
        System.out.println("Enter a message to see it encoded and decoded using a Huffman Tree: ");
        message = s.nextLine();
        
        s.close();
        final String test = message;
        final App encoder = new App();
        final HuffmanEncodedResult result = encoder.compress(test);
        System.out.println("Encoded message is: " + result.encodedData);
        System.out.println("Decoded message is: " + encoder.decompress(result));
	}

}