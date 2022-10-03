import java.util.*;

class game
{
    int size;
    char board[][];
    game(int n)
    {
        size=n;
        board =new char[n][n];
    
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
                board[i][j]='_';
        }
    }
}

class tictactoe
{
    
    static Scanner scn=new Scanner (System.in);

    static int n;

    static void printBoard(char data[][])
    {
        System.out.println();
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data.length;j++)
            {
                System.out.print(data[i][j]+"   ");
            }
            System.out.println("\n");
        }
    }

    static boolean isFull(char data[][])
    {
        for(int i=0;i<data.length;i++)
            for(int j=0;j<data.length;j++)
                if(data[i][j]=='_')
                    return false;
        return true;
    }

    public static void runGame(game curr)
    {
        String name[]=new String [2];
        System.out.println("Enter Player1 name :");
        name[0]=scn.next();
        System.out.println("Enter Player2 name :");
        name[1]=scn.next();
        System.out.println(name[0]+" is X and "+name[1]+" is O");
        int player=0;
        char choices[]={'x','o'};
        System.out.println("Board is empty");
        System.out.println("Enter your choice in the form of row [Enter] column");
        while( !isFull(curr.board))
        {
            printBoard(curr.board);
            System.out.println(name[player]+"'s turn :");
            int row=scn.nextInt();
            int col=scn.nextInt();
            if(row>curr.board.length-1 || col > curr.board.length-1)
            {
                System.out.println("!!!!!!! INVALID INPUT !!!!!!!!!");
                continue;
            }
            if(curr.board[row][col]!='_')
            {
                System.out.println("Invalid Entry, try again");
                continue;
            }
            curr.board[row][col]=choices[player];
            if(check(row,col,curr))
            {
                System.out.println(name[player]+" WON!!!!!!!!");
                printBoard(curr.board);
                return ;
            }    
            player=(player+1)%2;
        }
        System.out.println("Was a Tie :(");
        printBoard(curr.board);
    }

    static boolean check(int row,int col,game curr)
    {
        if(checkRow(row,curr))
            return true;
        if(checkCol(col,curr))
            return true;
        if(row==col)
            if(checkDiag1(curr))
                return true;
        if(row+col == curr.board.length-1)
            if(checkDiag2(curr))
                return true;
        
        return false;
    }

    static boolean checkRow(int row, game curr)
    {
        for(int i=1;i<curr.board.length;i++)
        {
            if(curr.board[row][i]!=curr.board[row][0])
                return false;
        }
        for(int i=0;i<curr.board.length;i++)
            curr.board[row][i]-=32;
        return true;
    }
    
    static boolean checkCol(int col, game curr)
    {
        for(int i=1;i<curr.board.length;i++)
        {
            if(curr.board[i][col]!=curr.board[0][col])
                return false;
        }
        for(int i=0;i<curr.board.length;i++)
            curr.board[i][col]-=32;
        return true;
    }
    static boolean checkDiag1(game curr)
    {
        for(int i=1;i<curr.board.length;i++)
            if(curr.board[i][i]!= curr.board[0][0])
                return false;
        
        for(int i=0;i<curr.board.length;i++)
            curr.board[i][i]-=32;
        return true;
    }
    static boolean checkDiag2(game curr)
    {
        for(int i=1;i<curr.board.length;i++)
            if(curr.board[i][curr.board.length-i-1]!= curr.board[0][curr.board.length-1])
                return false;
        
        for(int i=0;i<curr.board.length;i++)
            curr.board[i][curr.board.length-1-i]-=32;
        return true;
    }

    public static void main(String args [])
    {
        while(true)
        {
        System.out.println("Enter the size of board :");
        n=scn.nextInt();   
        
        game curr=new game(n);
        runGame(curr);
        System.out.println("Play again?\nY / N");
        char choice =scn.next().charAt(0);
        if(choice=='N' || choice=='n')
            break;
        }

    }
}