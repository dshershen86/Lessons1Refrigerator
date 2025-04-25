package model;
import java.util.Arrays;
public class Refrigerator implements Cloneable{
    private int count;
    private boolean[][] value;

    public Refrigerator(int count) {
        this.count = count;
        this.value = new boolean[count][count];
    }

    public int getCount() {
        return count;
    }

     public boolean[][] getValues() {
        return value;
    }
    public boolean getValue(int row, int col) {
        return value[row][col];
    }

    public void setValues(boolean[][] value) {
        this.value = value;
    }
    public void setValue(boolean value, int row, int col) {
        this.value[row][col] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(boolean [] s1 : value){
            sb.append(Arrays.toString(s1)).append('\n');
        }
        return new StringBuilder().append("Refrigerator{\n").append("value=\n").append(sb.toString()).append('}').toString();
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Refrigerator ref = (Refrigerator)obj;
        boolean value[][]=new boolean[this.count][this.count];
        for(int i = 0; i < count; i++){
            for(int j = 0; j < count; j++){
                value[i][j] = this.value[i][j];
            }
        }
        ref.setValues(value);
        return ref;
    }

}


