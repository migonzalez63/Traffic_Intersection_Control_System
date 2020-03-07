package Communication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Message{

    private List<Object> messageList;

    /**
     * Constructor for the message class.
     * @param objects for the message
     */
    public Message(Object...objects) {
        messageList = new ArrayList<>();
        Collections.addAll(messageList, objects);
    }

    /**
     * Returning the objects from the message.
     * @return messageList for checking the objects.
     */
    public List<Object> getMessageList() {
        return messageList;
    }

    @Override
    public String toString() {
        return "Message{" + "messageList=" + messageList + '}';
    }

}
