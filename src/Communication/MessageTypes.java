package Communication;

public enum MessageTypes {
    // maybe pass in ID here and save some time
    TEST("Test", 1), TEST1("Test1", 2);

    private String message;
    private int ID;
    MessageTypes(String s, int ID){
        message = s;
        this.ID = ID;
    }

    /**
     * Get the ID of the message
     * @return int that is ID
     */
    public int getmessageID(){
        return ID;
    }

    /**
     * Get string rep of the message
     * @return String that is the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return "Message = " + message + " ID = " + ID;
    }
}
