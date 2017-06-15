
package Ques;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private int		size;
	private Item[]	items;

	// construct an empty randomized queue
	public RandomizedQueue()
	{
		size = 0;
		items = ( Item[] ) new Object[ 1 ];
	}

	// is the queue empty?
	public boolean isEmpty()
	{
		return size == 0;
	}

	// return the number of items in the queue
	public int size()
	{
		return size;
	}

	// add the item
	public void enqueue( Item item )
	{
		if( item == null )
			throw new NullPointerException( "Item is NULL!" );
		if( size == items.length )
		{
			resize( 2 * items.length );
		}
		items[size] = item;
		size++;
	}

	// delete and return a random item
	public Item dequeue()
	{
		emptyCheck();
		int rand = StdRandom.uniform( size );
		Item elt = items[rand];
		if( size - 1 == rand )
		{
			items[rand] = null;
		}
		else
		{
			items[rand] = items[size - 1]; // index will always be zero to length-1
			items[size - 1] = null;
		}
		if( size > 0 && size == items.length / 4 )
		{
			resize( items.length / 2 );
		}
		size--;
		return elt;
	}

	// return (but do not delete) a random item
	public Item sample()
	{
		emptyCheck();
		int rand = StdRandom.uniform( size );
		return items[rand];

	}

	private void emptyCheck()
	{
		if( size == 0 )
		{
			throw new NoSuchElementException();
		}
	}

	private void resize( int capacity )
	{
		Item[] copy = ( Item[] ) new Object[ capacity ];
		for( int i = 0; i < size; i++ )
		{
			copy[i] = items[i];
		}
		items = copy;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator()
	{
		return new RandomizedIterator();
	}

	private class RandomizedIterator implements Iterator<Item>
	{
		int queueLength = size;

		public boolean hasNext()
		{
			return queueLength != 0;
		}

		public Item next()
		{
			if( !hasNext() )
			{
				throw new NoSuchElementException();
			}

			int rand = StdRandom.uniform( queueLength );
			Item itemtoreturn = items[rand];
			if( rand == queueLength - 1 )
			{
				queueLength--;
				return itemtoreturn;
			}

			else
			{
				items[rand] = items[queueLength - 1];
				items[queueLength - 1] = itemtoreturn;
				queueLength--;
				return itemtoreturn;
			}

		}

		public void remove()
		{
			throw new UnsupportedOperationException(); //This is not supported in this API
		}
	}

	public static void main( String[] args )
	{
		RandomizedQueue<Integer> testQueue = new RandomizedQueue<Integer>();
		testQueue.enqueue( 1 );
		testQueue.enqueue( 2 );
		testQueue.enqueue( 3 );
		testQueue.enqueue( 4 );
		testQueue.enqueue( 5 );
		testQueue.enqueue( 6 );
		testQueue.enqueue( 7 );
		testQueue.enqueue( 8 );
		Iterator<Integer> it = testQueue.iterator();
		while( it.hasNext() )
		{
			int elt = it.next();
			System.out.println( elt + " " );
		}

	}
}
