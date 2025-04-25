import controller.RefrigeratorController;
import model.Refrigerator;
import utils.Move;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        RefrigeratorController refController = new RefrigeratorController();
        Refrigerator example = refController.loadRefrigerator("./sample.txt");
        Move m=refController.play();
        System.out.println(example);
        if(!(m==null)&&(m.isWin)) {
            System.out.println("WIN");
            Deque<Move> stack = new ArrayDeque<>();
            stack.add(m);
            while(!(m.parent==null)) {
                m = m.parent;
                stack.add(m);
            }

            while (!stack.isEmpty()) {
                m=stack.peekFirst();
                System.out.println(m.posList.get(0));
                System.out.println(m.refrigerator);
                System.out.println("=======================");
                stack.pollFirst();
            }

        }else{
            System.out.println("no solutions");
        }
    }
}