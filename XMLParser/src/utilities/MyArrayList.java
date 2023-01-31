package utilities;


/**
 * 
 * @author Mahdiyeh
 *
 * @param <E> Type of element stored in MyArrayList
 */
public class  MyArrayList<E> implements ListADT<E> {
	
    /**
	 * the serialVersionUID of the class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The size of the first array that is created
	 */
	private static final int FIRST_ARRAY_SIZE = 10;
    
	/**
	 * array that holds all of the contents of the MyArrayList
	 */
    private E [ ] myArray;
    /**
     * size of the array
     */
    private int arraySize;
    /**
     * class constructor
     */
    public MyArrayList( ){
    	clear();
    }

	@Override
	public int size() {
		return arraySize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
        arraySize = 0;
        myArray =  (E[]) new Object[ FIRST_ARRAY_SIZE ] ;   		
	}
	
	void checkSpaceAndAddSpace(){
		// add space to myArray if it is full
	    if( myArray.length == size( ) ){
	        E [ ] old = myArray;
	        myArray = (E []) new Object[ size( ) * 2 + 1 ];
	        for( int i = 0; i < size( ); i++ )
	            myArray[ i ] = old[ i ];
        }
	}

	@Override
	public boolean add(int index, Object toAdd) throws NullPointerException,
			IndexOutOfBoundsException {
		if (toAdd==null)
			throw new NullPointerException();
	    if( index < 0 || index > size( ) )
	    	throw new IndexOutOfBoundsException("Index " + index + "; size " + size( ));

	    checkSpaceAndAddSpace();
	    
	    for( int i = arraySize; i > index; i-- )
            myArray[ i ] = myArray[ i - 1 ];
        
        myArray[ index ] = (E) toAdd;
        arraySize++;  

		return true;
	}

	@Override
	public boolean add(Object toAdd) throws NullPointerException {
		if (toAdd==null)
			throw new NullPointerException();
        return add( size( ), toAdd );
	}

	@Override
	public boolean addAll(ListADT toAdd) throws NullPointerException {
		if(toAdd == null) throw new NullPointerException();
		for(int i=0; i<toAdd.size(); i++) {
			add( toAdd );
		}
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
	       if( index < 0 || index >= size( ) )
	            throw new ArrayIndexOutOfBoundsException( "Index " + index + "; size " + size( ) );
	        return myArray[ index ];    
	 	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index<0 || index>=size())
			throw new IndexOutOfBoundsException( "Index " + index + "; size " + size( ));
		E removedItem = myArray[ index ];
        
        for( int i = index; i < size( ) - 1; i++ )
            myArray[ i ] = myArray[ i + 1 ];
        arraySize--;    
        
        return removedItem;
	}

	@Override
	public Object remove(Object toRemove) throws NullPointerException {
		if (toRemove==null)
			throw new NullPointerException();
		for (int i=0;i<size();i++)
			if (myArray[i].equals(toRemove))
		       return remove(i);
		return null;
	}

	@Override
	public Object set(int index, Object toChange) throws NullPointerException,
			IndexOutOfBoundsException {
		if (toChange==null)
			throw new NullPointerException();
	       if( index < 0 || index >= size( ) )
	            throw new ArrayIndexOutOfBoundsException( "Index " + index + "; size " + size( ) );
	        E previouse = myArray[ index ];    
	        myArray[ index ] = (E) toChange;
	        
	        return previouse;    
	}

	@Override
	public boolean isEmpty() {
        return size( ) == 0;
	}

	@Override
	public boolean contains(Object toFind) throws NullPointerException {
		if (toFind == null) 
			throw new NullPointerException();

		for(int i=0; i< arraySize; i++){
			if (myArray[i].equals(toFind)) {
				return true;
			} 
		}
		//if the element was not found then false is returned
		return false;
	}

	@Override
	public Object[] toArray(Object[] toHold) throws NullPointerException {
		// *******************
		// it is not deep copy
		// *******************
		
		if (toHold==null)
			throw new NullPointerException("parameter toHold in method toArray is null") ;
		
		if (toHold.length<arraySize) {
			toHold = toArray();
			return toHold;
		}
			
		for (int i=0;i<arraySize;i++)
			toHold[i]=myArray[i];
		
		for (int i=arraySize;i<toHold.length;i++)
			toHold[i]=null;
		
		return toHold;
	}

	@Override
	public Object[] toArray() {
		E[] tempArray =  (E[]) new Object[ arraySize ] ;  
		for (int i=0;i<arraySize;i++)
			tempArray[i]=myArray[i];
		return tempArray;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}

	
	private class MyArrayListIterator implements Iterator<E>{
        private int current = 0;
        private boolean okToRemove = false;
        
        public boolean hasNext() {
            return current < size( ); 
        }
        
        
        public E next( ) {
            if( !hasNext() ) 
                throw new java.util.NoSuchElementException( ); 
                  
            okToRemove = true;
            return myArray[ current++ ];
        }
        
    }
}