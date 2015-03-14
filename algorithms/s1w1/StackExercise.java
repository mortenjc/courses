
public class StackExercise {
    
private static void popprint(Stack s)
{
    StdOut.println(s.pop());
}

public static void main(String[] args)
{
    Stack<String> st = new Stack<String>();
    
    while (!StdIn.isEmpty())
    {
        String s = StdIn.readString();
        if (s.equals("-"))  StdOut.print(st.pop() + " ");
        else
            st.push(s);
    }
    StdOut.println("");
}
}