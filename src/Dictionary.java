// Emily Xie
// 251035713
import java.util.LinkedList;

public class Dictionary implements DictionaryADT {
	private int size;
	private LinkedList<Layout>[] dictionary;
	
	public Dictionary(int size) {
		// Set fixed linked list to size.
		dictionary = new LinkedList[size];
		this.size = size;
		// Set variable size linked list for collisions. 
		for (int i = 0; i < size; i++) {
			dictionary[i] = new LinkedList();
		}
	}
	
	private int hash(String k) {
		
		final int a = 23; //random prime number
		
		int value = 0;
		// Iterate through starting at last values.
		for (int i = k.length()-1; i >= 0; i--) {
			// Multiply value by a, add character.
			value = (value*a + k.charAt(i)) % size;
		}
		return value;
	}
	
	private int getBin(String k) {
		// Changing hash by modulus size.
		return hash(k);
	}
	
	public int put(Layout data) throws DictionaryException{
		// Get board layout data
		String k = data.getBoardLayout();
		// Determine size of bin
		int binSize = getBin(k);
		// Check if Linked List is empty. No collision, return 0.
		if (dictionary[binSize].size() == 0) {
			dictionary[binSize].add(data);
			return 0;
		}
		// If there is a collision, compare hash code.
		else {
			// Compare hash code in Linked List in bin.
			for (Layout l : dictionary[binSize]) {
				if (k.equals(l.getBoardLayout())) {
					throw new DictionaryException("Duplicate key");
				}
			}
			// If hash codes do not match, add data to bin,return 0.
			dictionary[binSize].add(data);
			return 1;
		}
	}
	public void remove(String boardLayout) throws DictionaryException{
		// Get board layout that is stored
		String k = boardLayout;
		// Determine size of bin.
		int binSize = getBin(k);
		
		// Compare hash code in Linked List in bin. If hash codes match, remove.
		for (Layout l : dictionary[binSize]) {
			if (k.equals(l.getBoardLayout())) {
				dictionary[binSize].remove(l);
				return;
			}
				
		}
		// If it does not match, throw Exception.
		throw new DictionaryException("Board layout does not exist.");
	}
	public int getScore(String boardLayout) {
		// Get board layout that is stored.
		String k = boardLayout;
		// Determine size of bin.
		int binSize = getBin(k);
			
		// Compare hash code in Linked List in bin. If hash codes match, return score.
		for (Layout l : dictionary[binSize]) {
			if (k.equals(l.getBoardLayout())) {
				return l.getScore();
			}
		}
		//Otherwise return -1.
		return -1;
	}

public static void main(String[] s) throws Exception {
    Dictionary d = new Dictionary(10);
    System.out.println(d.put(new Layout("ccccdddcc", 3)));
    System.out.println(d.put(new Layout("ccccdddcc", 3)));
    System.out.println(d.put(new Layout("ccccdddcd", 4)));
    System.out.println(d.put(new Layout("cccddddcd", 5)));
    System.out.println(d.put(new Layout("cccpdddcd", 6)));



    d.remove("ccccdddcc");
    d.remove("ccccdddcd");
    d.remove("cccddddcd");
    d.remove("cccpdddcd");

    System.out.println(d.getScore("cccpdddcd"));
    System.out.println(d.getScore("ccccdddcc"));
    System.out.println(d.getScore("ccccdddcd"));
    System.out.println(d.getScore("cccddddcd"));
    System.out.println(d.getScore("cccpdddcd"));
}
}