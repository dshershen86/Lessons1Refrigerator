package controller;

import model.Refrigerator;
import utils.*;
import service.RefrigeratorService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RefrigeratorController implements RefrigeratorService {
    Refrigerator refrigeratorTrue;
    Refrigerator refrigerator;
    List<Move> moves;

    @Override
    public Refrigerator loadRefrigerator(String fileName){

        List<String> content = null;
        try {
            content = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            refrigerator = new Refrigerator(Integer.parseInt(content.get(0)));
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < refrigerator.getCount(); i++)
            for (int j = 0; j< refrigerator.getCount(); j++) {
                try {
                    refrigerator.setValue(Integer.parseInt(content.get(i + 1).substring(j, j+1))!=0?true:false, i, j);
                }catch (NumberFormatException e){
                    throw new NumberFormatException();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        return refrigerator;
    }

    private void createrefrigeratorTrue(int count){
        boolean[][] memo = new boolean[count][count];
        for (int i = 0; i < count; i++)
            for (int j = 0; j < count; j++)
                memo[i][j] = true;
        refrigeratorTrue = new Refrigerator(count);
        refrigeratorTrue.setValues(memo);
    }
    private Refrigerator change(Refrigerator ref, int ypos,int xpos) throws CloneNotSupportedException {
        List<Pos> coords;
        coords=new ArrayList<>();
        Refrigerator result= (Refrigerator) ref.clone();
        for (int i = 0; i < result.getCount(); i++){
            result.setValue(!result.getValue(i,xpos),i,xpos);
            coords.add(new Pos(i,xpos));
        }

        for (int i =0; i < result.getCount(); i++)
            if (!coords.contains(new Pos(ypos,i)))
                result.setValue(!result.getValue(ypos,i),ypos,i);
        return result;
    }
    //BOF
    private List<Move> rPlay(Move m) throws CloneNotSupportedException {
        int countMoves=0;
        List<Move>moves = new ArrayList<Move>();
        Refrigerator tref=refrigerator;
        if (m!=null){
            tref=m.refrigerator;
            countMoves = m.countMoves+1;
            //tref = m.refrigerators.get(m.refrigerators.size()-1);
        }

        for (int i = 0; i < tref.getCount(); i++)
            for (int j = 0; j < tref.getCount(); j++){
                Pos p = new Pos(j,i);
                if ((m!=null)&&(m.posList.contains(p)))
                    continue;

                Move move;
                if (m==null)
                    move = new Move();
                else
                    move = (Move) m.clone();
                Refrigerator resultRef= null;
                try {
                    resultRef = change(tref,i,j);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                move.change(j,i,countMoves+1);
                move.refrigerator=resultRef;
                move.parent =m;
                move.level=m==null?0:m.level+1;
                if(Arrays.deepEquals(resultRef.getValues(),refrigeratorTrue.getValues())){
                    move.isWin=true;
                    moves.add(move);
                    return moves;
                }
                moves.add(move);

            }
        return moves;
    }
    private Move returnLastMove(List<Move> m){
        return  m.get(moves.size()-1);
    }
    private boolean isWin(List<Move> m){
        return returnLastMove(m).isWin;
    }
    @Override
    public Move play() throws CloneNotSupportedException {
        this.moves = new ArrayList<Move>();
        if(refrigeratorTrue==null){
            createrefrigeratorTrue(refrigerator.getCount());
        }
        moves=rPlay(null);
        if(isWin(moves))
            return moves.get(moves.size()-1);
        while(moves.get(0).level<=refrigerator.getCount()*refrigerator.getCount())
            for(Move move:moves) {
                moves = rPlay(move);

                if(isWin(moves))
                    return returnLastMove(moves);
            };
        return null;
    }
}
