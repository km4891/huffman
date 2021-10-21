
public class App {

    //for all ASCII characters
    private static final int ALPHABET_SIZE= 256;

    public HuffmanEncodedResult compress (final String data){
        final int [] freq = new int[ALPHABET_SIZE];
        for(final char character : data.toCharArray()) {
            freq[character]++; //characters can be treated like integers

        }
        
        return null;
    }

    public String decompress (final HuffmanEncodedResult result) {
        return null;
    }

    static class HuffmanEncodedResult {

    }
	public static void main(String[] args) {
	}

}