import java.util.Stack;

public class Problem1 {
    public static void main(String[] args) {

        System.out.println(reverse("abd(jnb)asdf"));

    }

    public static String reverse(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        var sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {

            if (chars[i] != '(') {
                sb.append(chars[i]);
            } else if (chars[i] == '(') {
                sb.append(chars[i++]);
                while(chars[i] != ')') {
                    stack.push(chars[i]);
                    i++;
                }
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
