package utils;

import model.Refrigerator;

import java.util.ArrayList;
import java.util.List;

public class Move implements Cloneable {
    public Refrigerator refrigerator;
    public int countMoves;
    public Move parent;
    public List<Pos> posList;
    public Pos thisPos;
    public int level;
    public boolean isWin;

    public Move(int xpos, int ypos, int countMoves) {
        this.countMoves = countMoves;
        this.isWin = false;
        this.posList = new ArrayList<>();
    }

    public Move() {

        this.posList = new ArrayList<>();
    }

    public void change(int xpos, int ypos, int countMoves) {
        this.countMoves = countMoves;
        this.posList.add(new Pos(xpos, ypos));
        this.thisPos = new Pos(xpos, ypos);
        if (this.parent != null) {
            for (Pos pos : this.parent.posList)
                this.posList.add(pos);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Move move = (Move) obj;
        move.posList = new ArrayList<>();
        return move;
    }
}