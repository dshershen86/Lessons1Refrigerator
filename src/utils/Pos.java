package utils;

import java.util.Objects;

public class Pos{
    int xpos,ypos;

    public Pos(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return xpos == pos.xpos && ypos == pos.ypos;
    }
    @Override
    public int hashCode() {
        return Objects.hash(xpos, ypos);
    }

    @Override
    public String toString() {
        return "Pos{" +
                "xpos=" + xpos +
                ", ypos=" + ypos +
                '}';
    }
}