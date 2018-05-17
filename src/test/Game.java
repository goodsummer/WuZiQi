package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game
{
   private static final int SIZE = 20;
   private static Chess[][] board = new Chess[SIZE][SIZE];
   private List<Chess> chessList = new ArrayList<Chess>();
   private ChessPlayer blackChessPlayer;
   private ChessPlayer whiteChessPlayer;
   private int chessSeq = 1;
   private ChessPlayer nextChessPlayer;

   public Game(String blackName, String whiteName) {
      blackChessPlayer = new ChessPlayer(blackName, ChessColor.BLACK);
      whiteChessPlayer = new ChessPlayer(whiteName, ChessColor.WHITE);
      
      nextChessPlayer = blackChessPlayer;
   }
   
   public void addChess(int x, int y)
   {
      for (Chess chess : chessList)
      {
         if (chess.getX() == x && chess.getY() == y)
         {
            System.out.println("这个地方已经有棋子了，换个地方吧");
            return;
         }
      }   
      
      board[x][y] = nextChessPlayer.addChess(chessSeq, x, y, chessList);

      if (!isGameOver(board[x][y]))
      {
         printChessBoard();
         changeNextPlayer();
         chessSeq ++;
      }
      else
      {
         printSuccessChessBoard();
         System.exit(0);
      }
   }
   
   public void backOneChess()
   {
      Chess chess = chessList.get(chessList.size() - 1);
      
      board[chess.getX()][chess.getY()] = null;
      chessList.remove(chessList.size() - 1);
      
      printChessBoard();
      changeNextPlayer();
      chessSeq --;
   }

   private void changeNextPlayer()
   {
      if (nextChessPlayer == blackChessPlayer)
      {
         nextChessPlayer = whiteChessPlayer;
      }
      else
      {
         nextChessPlayer = blackChessPlayer;
      }
   }

   private boolean isGameOver(Chess chess)
   {
      if ((this.getNeighborCountNorth(chess) +  this.getNeighborCountSouth(chess))> 3 ||
            (this.getNeighborCountNorthEast(chess) + this.getNeighborCountSouthWest(chess)) > 3 ||
            (this.getNeighborCountNorthWest(chess) + this.getNeighborCountSouthEast(chess)) > 3 ||
            (this.getNeighborCountWest(chess) + this.getNeighborCountEast(chess)) > 3)
      {
         return true;
      }
      
      return false;
   }

   public void printChessList()
   {     
      chessList.forEach(chess -> {
         String color = chess.getColor()  == ChessColor.BLACK ? "黑" : "白";
         int seq = chess.getSequence(); 
         int x = chess.getX();
         int y = chess.getY();
         
         System.out.println("第" + seq + "手，" + color + "棋，" + "[" + x + "," + y + "]" );
      });
   }
   
   private void printChessBoard()
   {
      StringBuilder fullBoard = new StringBuilder("");
      
      for(int i=0; i<SIZE; i++)
      {
         StringBuilder line = new StringBuilder("");
         StringBuilder vertical = new StringBuilder("");
         
         for(int j=0; j<SIZE; j++)    
         {
            Chess chess = board[i][j];
            
            if (chess == null)
            {
               line.append("- - ");
            } 
            else if (chess.getColor() == ChessColor.BLACK)
            {
               line.append("● - ");
            }
            else 
            {
               line.append("○ - ");
            }          
            
            vertical.append("|   ");
            if (j == SIZE - 1)
            {
               line.delete(line.length() - 3, line.length());
               vertical.delete(vertical.length() - 3, vertical.length());
            }
         }
         
         if (i < 10)
         {
            fullBoard.append(i + "  ").append(line).append("  " + i).append("\n");
         }
         else
         {
            fullBoard.append(i + " ").append(line).append("  " + i).append("\n");
         }
         
         if (i != SIZE -1)
         {
            fullBoard.append("   " + vertical).append("\n");
         }
      }
      
      StringBuilder number = new StringBuilder("");      
      for(int i=0; i<SIZE; i++)
      {
         if (i < 10)
         {
            number.append(i + "   ");
         }
         else
         {
            number.append(i + "  ");
         }
         if (i == SIZE - 1)
         {
            number.append("\n");
         }
      }
      
      String board = "   " + number.toString() + fullBoard.toString() + "   " +  number.toString();
      System.out.println(board);
   }

   public void printSuccessChessBoard()
   {
      String color = nextChessPlayer.getChessClor() == ChessColor.BLACK ? "黑" : "白";
      
      System.out.println("游戏结束！" + color + "棋胜！胜利者：" + nextChessPlayer.getName());
      printChessList();
      printChessBoard();
   }
   
   public int getNeighborCountNorthEast(Chess chess) {
      int x = chess.getX() + 1;
      int y = chess.getY() - 1;
       
      int count = 0;
      
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountNorthEast(board[x][y]);
         }
      } 
      catch(Exception e){}
   
      return count;
   }
   
   public int getNeighborCountSouthWest(Chess chess)
   {
      int x = chess.getX() - 1;
      int y = chess.getY() + 1;
      
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountSouthWest(board[x][y]);
         }
      } 
      catch(Exception e){}
      
      
      return count;
   }
   
   public int getNeighborCountNorthWest(Chess chess) {
      int x = chess.getX() - 1;
      int y = chess.getY() - 1;
       
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountNorthWest(board[x][y]);
         }
      } 
      catch(Exception e){}
      
      return count;
   }
   
   public int getNeighborCountSouthEast(Chess chess) {
      int x = chess.getX() + 1;
      int y = chess.getY() + 1;
       
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountSouthEast(board[x][y]);
         }
      } 
      catch(Exception e){}
      
      return count;
   }
   
   public int getNeighborCountNorth(Chess chess) {
      int x = chess.getX();
      int y = chess.getY() - 1;
 
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountNorth(board[x][y]);
         }
      } 
      catch(Exception e){}
    
      
      return count;
   }
   
   public int getNeighborCountSouth(Chess chess) {
      int x = chess.getX();
      int y = chess.getY() + 1;
       
      int count = 0;

      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountSouth(board[x][y]);
         }
      } 
      catch(Exception e){}
      
      
      return count;
   }
   
   public int getNeighborCountWest(Chess chess) {
      int x = chess.getX() - 1;
      int y = chess.getY();
       
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountWest(board[x][y]);
         }
      } 
      catch(Exception e){}

      return count;
   }
   
   public int getNeighborCountEast(Chess chess) {
      int x = chess.getX() + 1;
      int y = chess.getY();
       
      int count = 0;
      try 
      {
         if (board[x][y].getColor() == chess.getColor())
         {
            count ++;
            count = count + getNeighborCountEast(board[x][y]);
         }
      } 
      catch(Exception e){}

      return count;
   }
 
   public static void main(String[] args)
   {
      Game game = new Game("未命名", "未命名");  
      game.printChessBoard();
      
      Scanner scan = new Scanner(System.in);
      while(scan.hasNext())
      {
         String s = scan.nextLine();
         
         if ("back".equals(s))
         {
            game.backOneChess();
         }
         else
         {
            try {
               int x = Integer.valueOf(s.split(" ")[0]);
               int y = Integer.valueOf(s.split(" ")[1]);
               
               game.addChess(x, y);
            }
            catch(Exception e)
            {
               System.out.println("输入有误！重新输入吧");
            }   
         }          
      }
      
      scan.close();
   }
}

