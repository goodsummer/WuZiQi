package test;

import java.util.List;

public class ChessPlayer
{
   private String name;
   private ChessColor color;
   
   public ChessPlayer(String name, ChessColor color)
   {
      super();
      this.name = name;
      this.color = color;
   }

   public Chess addChess(int seq, int x, int y, List<Chess> chessList) {
      Chess chess = new Chess(this.color, x, y, seq, this.name);
      chessList.add(chess);
      
      return chess;
   }
   
   public String getName() 
   {
      return this.name;
   }
   
   public ChessColor getChessClor()
   {
      return this.color;
   }
}
