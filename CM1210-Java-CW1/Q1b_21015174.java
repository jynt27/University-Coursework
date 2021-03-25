//Jayant Misra
//21015174

import java.util.*;

public class Q1b_21015174
{
    
    public static int n;
    public static int magic_matrix[][];
    public static int shuffled_matrix[][];
    public static int number_of_moves = 0;

    // Main method
    public static void main(String [] args)
    {
        input();

        magic_matrix = create_magic_matrix();
        System.out.println("Magic Matrix : ");
        System.out.println();
        print_matrix(magic_matrix);

        // copying elements of magic matrix into shuffled matrix
        shuffled_matrix = new int[magic_matrix.length][magic_matrix.length];
        for(int i = 0 ; i < n ; i++)
        {
            for(int j = 0 ; j < n ; j++)
            {
                shuffled_matrix[i][j] = magic_matrix[i][j];
            }
        }

        shuffle();
        System.out.println();
        System.out.println("Shuffled Matrix : ");
        System.out.println();
        print_matrix(shuffled_matrix);
        System.out.println();


        int flag = 0;
        while(flag == 0) // to stop the program from crashing
        {
        try{
            play();
            flag = 1;         // flag is there to check if the play() function is fully executed.
        }
        catch(Exception e)
        {
            System.out.println();
            System.out.println("Invalid Input!!");
            System.out.println();
            number_of_moves = number_of_moves + 1; // because number_of_moves is not incremented in play() function when there is an invalid input.
        }
        }

        
    }

    // to take instructions and play
    public static void play()
    {
        Scanner sc = new Scanner(System.in);
        int i,j;
        String a,b;
        char direction;

        while(compare_matrices() == false)
        {
            System.out.println("Enter the move! (i j <U,D,R,L>) or type 'exit' to exit. ");
            String str = sc.nextLine();
            
            if (str.equals("exit")) // terminates the program if the user inputs "exit"
            {
                System.exit(0);
            }

            number_of_moves = number_of_moves + 1; // Counting the number of moves taken by the user

            a = str.substring(0,str.indexOf(' '));
            b = str.substring(str.indexOf(' ') + 1 , str.lastIndexOf(' ')); 

            i = Integer.parseInt(a) - 1; // Subtracting 1 because the indexing in java starts from 0 but the user inputs "1 1" for the top left cell
            j = Integer.parseInt(b) - 1;
            direction = str.charAt(str.length() - 1);

            swap(i,j,direction);
            System.out.println();
            System.out.println("shuffled_matrix: ");
            System.out.println();
            print_matrix(shuffled_matrix);

            
        }
        System.out.println();
        System.out.println("shuffled_matrix: ");
        print_matrix(shuffled_matrix);

        if (compare_matrices())
        {
            System.out.println("Task Completed!");
            System.out.println("Number of moves taken= " + number_of_moves);

        }

    }

    // to input an odd integer n
    public static void input()
    {
        Scanner sc = new Scanner(System.in);
        // Asking the user for input until he enters a positive odd number
        do
        {
            System.out.println("Enter a positive odd number!");

            try
            {
                n = sc.nextInt();
            }
            catch(Exception e)
            {
                System.out.println();
                System.out.println("Invalid Input!!!");
                System.exit(0);
            }
            
        }while(n % 2 == 0 || n < 0);
        
    }

    // to create a matrix and fill it with zeros 
    public static int[][] create_zero_matrix(int n)
    {   int i, j;
        int z_m[][] = new int[n][n];
        for(i = 0; i < n; i++)
        {
            for(j = 0; j < n; j++)
            {
                z_m[i][j] = 0;
            }
        }
        return z_m;
    }


    // to print the matrix
    public static void print_matrix(int m[][])
    {   int i, j;
        for(i = 0; i < n; i++)
        {
            for(j = 0; j < n; j++)
            {
                System.out.print(m[i][j] + " ");
            }System.out.println();
        }

    }


    // to create a magic square
    public static int[][] create_magic_matrix()
    {
        int m[][] = create_zero_matrix(n);
        int x,y,current_x,current_y,i;
        x = 0;
        y = n/2;

        // Putting 1 in the first cell
        m[x][y]=1;

        // Filling the rest of the cells
        for(i = 2;i<=n*n;i++)
        {
            current_x = x;
            current_y = y;
            x = x - 1;
            y = y - 1;

            // Wrapping the Matrix
            if (x == -1)
            {
                x = n - 1;
            }
            if (y == -1)
            {
                y = n - 1;
            }

            // Navigating down if cell [x-1][y-1] is already used
            if (m[x][y] != 0)
            {
                x = current_x + 1;
                y = current_y;
            }
            
            m[x][y]=i;
        }
        return m;
    }

    //to swap the elements according to given instructions
    public static void swap(int x, int y, char d)
    {
        int current_x, current_y;
        current_x = x;
        current_y = y;

        // Navigating in a "Wrapped" matrix as per instructions given by the user
        if (d == 'U'|| d == 'u')
        {
            x = x - 1;
            if (x == -1)
            {
                x = n - 1;
            }
        }
        else if (d == 'D'|| d == 'd')
        {
            x  = x + 1;
            if (x == n)
            {
                x = 0;
            }
            
        }
        else if (d == 'L'|| d == 'l')
        {
            y = y - 1;
            if (y == -1)
            {
                y = n - 1;
            }
            
        }
        else if (d == 'R'|| d == 'r')
        {
            y =  y + 1;
            if(y == n)
            {
                y = 0;
            }
            
        }
        else{
            System.out.println("Invalid Direction!!");
        }

        // Swapping values of the the two cells
        int temp = shuffled_matrix[x][y];
        shuffled_matrix[x][y] = shuffled_matrix[current_x][current_y];
        shuffled_matrix[current_x][current_y] = temp;
    }

    //to shuffle the matrix by swapping elements n*n times randomly 
    public static void shuffle()
    {
        Random rand = new Random();
        int rx,ry,dir;
        
        char directions[] = {'U','D','L','R'};

        for(int i = 0 ; i < n*n ; i++)
        {
            rx = rand.nextInt(n);
            ry = rand.nextInt(n);           // Randomly producing inputs for swap function to shuffle
            dir = rand.nextInt(4);
            swap(rx,ry,directions[dir]);
        }
    }

    // to compare the two 2D Matrices by comparing each element of the matrix
    public static boolean compare_matrices()
    {
        boolean a = true; 
        
        for(int i = 0; i<n; i++)
        {
            for(int j = 0; j<n; j++)
            {
                if(shuffled_matrix[i][j] !=  magic_matrix[i][j])
                {
                    a = false;
                }
            }
        }
        return a;
    }
}