import java.util.ArrayDeque;
import java.util.Deque;

class StringBuilderMemento {
    private final String state;

    public StringBuilderMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

public class MyStringBuilder{
    private StringBuilder buff; //для значения которое лежит сейчас
    private Deque<StringBuilderMemento> history; //для команды undo

    public MyStringBuilder(){
        this.buff = new StringBuilder();
        this.history = new ArrayDeque<>();
    }

    public MyStringBuilder(String State){
        this.buff = new StringBuilder(State);
        this.history = new ArrayDeque<>();
    }
// по сути переинициализируем старые методы (ну а чё велосипед то изобретать), но с сохранением истории
    public MyStringBuilder append(Object obj) {
        buff.append(obj);
        history.push(new StringBuilderMemento(buff.toString()));
        return this;
    }

    public MyStringBuilder insert(int index, Object obj){
        buff.insert(index, index);
        history.push(new StringBuilderMemento(buff.toString()));
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        buff.delete(start, end);
        history.push(new StringBuilderMemento(buff.toString()));
        return this;
    }

    public MyStringBuilder replace(int start, int end, String str) {
        buff.replace(start, end, str);
        history.push(new StringBuilderMemento(buff.toString()));
        return this;
    }
    public MyStringBuilder undo() {
        if (history.size() <= 1) {
            System.out.println("Это начальное состояние");
            return this;
        }

        history.pop();
        StringBuilderMemento previous = history.peek();
        buff = new StringBuilder(previous.getState());
        return this;
    }

    
}
