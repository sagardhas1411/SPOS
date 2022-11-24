// Java code for next fit 

import java.util.Arrays; 

public class NextFit1 { 
  
// Method to allocate memory to the blocks following the Next fit algorithm 

static void Next_Fit(int block_size[], int m, int process_size[], int n) 
{ 
 // The code will store the block id for a block which is assigned to a process
        int allocate[] = new int[n], j = 0; 
  
  // No block is assigned to any process at the beginning 
       
 Arrays.fill(allocate, -1); 
  
// Find a suitable block for each process as per its size and assign memory to it  
         
        for(int i = 0; i < n; i++) { 
  
            // Not starting from the beginning
            while (j < m) { 
  
                if (block_size[j] >= process_size[i]) { 
  
                    // block j is allocated to p[i] process 
                    allocate[i] = j; 
  
                    // Reduce available memory in this block. 
                    block_size[j] -= process_size[i]; 
  
                    break; 
                } 

  // mod m will traverse the blocks from the starting when the pointer reaches at the end. 
  
                j = (j + 1) % m; 
            } 
        } 
  
        System.out.print("\nProcess No.\tProcess Size\tBlock no.\n"); 
        for (int i = 0; i < n; i++) { 
            System.out.print( i + 1 + "\t\t" + process_size[i] 
                    + "\t\t"); 
            if (allocate[i] != -1) { 
                System.out.print(allocate[i] + 1); 
            } else { 
                System.out.print("Not Allocated"); 
            } 
            System.out.println(""); 
        } 
    } 
  
// Driver program 
 
    static public void main(String[] args) { 
        int block_size[] = {5, 10, 20}; 
        int process_size[] = {10, 20, 5}; 
        int m = block_size.length; 
        int n = process_size.length; 
        Next_Fit(block_size, m, process_size, n); 
    } 
} 
