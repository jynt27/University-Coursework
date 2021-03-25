//Jayant Misra
//21015174

import java.util.*;
import java.lang.*;


public class Q1a_21015174
{   
    public static int n;
    public static int magic_matrix[][];

    // Main Method
    public static void main(String [] args)
    {   
    
        n = input();
        magic_matrix = create_magic_matrix();
        System.out.println();
        System.out.println("Magic Matrix : ");
        System.out.println();
        print_matrix(magic_matrix);
        
    }

    // to input an odd integer n
    public static int input()
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

        return n;
    }


    // to create a matrix and fill it with zeros 
    public static int[][] create_zero_matrix(int n)
    {
        int z_m[][] = new int[n][n];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                z_m[i][j] = 0;
            }
        }

        return z_m;
    }


    // to print the matrix
    public static void print_matrix(int m[][])
    {   
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(m[i][j] + " ");
            }System.out.println();
        }

    }


    // to create a magic square using the Algorithm given in the Coursework Pro-forma
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

}