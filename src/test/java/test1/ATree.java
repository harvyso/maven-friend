package test1;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class ATree {
	
	static class DynamicAutomat {
		
		
		//List<V> vocabulary = new ArrayList<V>();
		
		//Set<K> entries = new HashSet<K>();
		//Map<, BitSet> entries = new BitSet();
		
		enum EntryTypes { UNDEFINED(0), LETTER(1), TAG(2); int key; EntryTypes(int k){key=k;}  };
		class Entry { public byte type; public int value;
			public Entry() { type=(byte)EntryTypes.UNDEFINED.key; }
			public boolean equals(Entry v) { return v.type==this.type && v.value==this.value; }
			public boolean equals(Character v) { return EntryTypes.LETTER.key==this.type && v==this.value; }
		};
		List<Map<Character, Entry>> positions = new ArrayList<Map<Character, Entry>>();
		
		public boolean add(CharSequence val) {
			split( val );
			return true;
		}
		
		// return true if val exists
		public boolean split(CharSequence val) {
			synchronized (positions) {
				// extent pool array when it needed
				int cnt = val.length()-positions.size();
				for ( int i=0; i<cnt; i++ ) {
					// add new pool
					positions.add( new HashMap<Character, Entry>() );
				}
			}
			
			boolean exists = true;
			for ( int i=0; i<val.length(); i++ ) {
				Map<Character, Entry> pool = positions.get( i );
				Character c = Character.valueOf( val.charAt(i) );
				Entry entry = pool.get( val );
				if ( entry==null ) {
					entry = new Entry();
					entry.type = (byte)EntryTypes.LETTER.key;
					entry.value = c.charValue();
					pool.put( c, entry );
					exists = false;
				}
			}
			return exists;
		}
	}
	
	@Test
	public void testSimple() {
		DynamicAutomat da = new DynamicAutomat();
		
		assertTrue( da.add( "mama" ) );
		
		assertTrue( da.add( "papa" ) );
		
		assertTrue( da.add( "parent" ) );
		
		assertTrue( da.add( "mause" ) );
	}

}
