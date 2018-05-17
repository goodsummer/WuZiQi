package test;

public class Chess
{
   private ChessColor color;
   private int x;
   private int y;
   private int sequence;
   private String playerName;
   
   public Chess(ChessColor color, int x, int y, int sequence, String playerName)
   {
      super();
      this.color = color;
      this.setX(x);
      this.setY(y);
      this.setSequence(sequence);
      this.setPlayerName(playerName);
   }
   
   public ChessColor getColor()
   {
      return this.color;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getSequence()
   {
      return sequence;
   }

   public void setSequence(int sequence)
   {
      this.sequence = sequence;
   }

   public String getPlayerName()
   {
      return playerName;
   }

   public void setPlayerName(String playerName)
   {
      this.playerName = playerName;
   }
   
}
