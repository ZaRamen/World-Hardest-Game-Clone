package main;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Obstacle;
import obj.OBJ_Cell;
import obj.OBJ_Dio;
import obj.SuperObject;
import tile.TileM;

public class ProjRec
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        //uses both types of ArrayList
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 7, 9, 4));
        @SuppressWarnings("rawtypes")
		ArrayList arr2 = new ArrayList();
        //call all ArrayList methods
        System.out.println(arr.size());
        System.out.println(arr2.add(1));
        arr2.add(0, 1);
        System.out.println(arr2.get(0));
        System.out.println(arr2.set(1, 1));
        System.out.println(arr2.remove(1));

        arr2.add(1);
        arr2.add(1);
        arr2.add(0);
        //deletes without skipping an element
        removeInt(1, arr2);
        System.out.println(arr2);

        //insertion and selection sort also iteration
        int[] arr3 = {1, 3, 5, 2, 9, 4};

        insertionSort(arr3);
        arr3 = new int[]{1, 3, 5, 2, 9, 4};
        selectionSort(arr3);

        //calls superclass no argument constructor
        SuperObject s = new OBJ_Dio();
        //Uses an ArrayList of superclass object references but populates it with subclass object references.
        ArrayList<SuperObject> s2 = new ArrayList<>();
        s2.add(s);
        s2.add(new OBJ_Cell(0, 0));
        
       
        int[][] nums = {{1, 2, 3}, {4, 5, 6}};
        colMajor(nums);
        System.out.println();
        rowMajor(nums);

    }
    public static void colMajor(int[][] arr)
	{
		//uses enhacned for loops to traverse 2d arrays
		for(int[] i: arr)
		{
			for(int i2: i)
			{
				System.out.print(i2 + " ");
			}
			System.out.println();
		}
	}
	public static void rowMajor(int[][] arr)
	{
		for(int i = 0; i < arr[0].length; i++)
		{
			for(int i2 = 0; i2 < arr.length; i2++)
			{
				System.out.print(arr[i2][i] + " ");
			}
			System.out.println();
		}
	}
    //uses iteration to traverse and removes elements
    public static void removeInt(int i, ArrayList<Integer> arr)
    {
        for(int x = 0; x < arr.size(); x++)
        {
            if(arr.get(x) == i)
            {
                arr.remove(x);
                x--;
            }
        }
    }
    public static void selectionSort(int[] arr)
    {
        int count = 0; //statement execution count
        for(int i = 0; i < arr.length - 1; i++)
        {
            int minIndex = i; //index of the minimum value
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[j] < arr[minIndex])
                {
                    minIndex = j; //find the minimum
                }
            }
            //swap the minimum with the value at index i
            if(i != minIndex)
            {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                //count the swaps
                count++;
            }
            //print it out
            print(arr);
        }
        System.out.println("Statement Execution Count: " + count);
    }
    public static void insertionSort(int[] arr)
    {
        int count = 0;
        for(int i = 1; i < arr.length; i++)
        {
            //store the value at index i
            int key = arr[i];
            int j = i - 1;
            //check if the value to the left is greater
            while(j >= 0 && arr[j] > key)
            {
                //if it's greater, move it to the right one space
                arr[j + 1] = arr[j];
                //move the key to the left
                j--;
            }
            //and insert the value at index i in its proper position
            arr[j + 1] = key;
            //also count the number of insertions
            count++;
            print(arr);
        }
        System.out.println("Statement Execution Count: " + count);
    }
    public static void print(int[] arr)
    {
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

}
