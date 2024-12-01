package errors;

public class Errors extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3660060001119312915L;

    public Errors(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}


    
