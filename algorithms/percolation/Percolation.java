public class Percolation 
{
  
  private int [][] grid;
  private WeightedQuickUnionUF wt;
  private int sz;
  
  public Percolation(int N) {       // N-by-N grid, with all sites blocked
    sz = N;
    grid = new int [N + 1][N + 1];  // 
    for (int i = 1; i < N+1; i++)
      for (int j = 1; j < N+1; j++)
        grid[i][j] = 0;             // 0 means blocked. 1 is full.
    
    // Add tree
    wt = new WeightedQuickUnionUF(N*N+2);
  }

  
  public void open(int i, int j) {         // open site (row i, column j)
    if (i < 1 || i > sz || j < 1 || j > sz)
      throw new IndexOutOfBoundsException("index (" 
                                            + i + ","+ j+ ") is out of bounds");
    grid[i][j] = 1;
    int idx = (i - 1)*sz + j;
    int up  = (i - 2)*sz + j;
    int dn  = i*sz + j;
    int lef = (i - 1)*sz + j - 1;
    int rig = (i - 1)*sz + j + 1;
    
    // Connect first row to virtual node
    if (i == 1)
      wt.union(idx, 0);
    else {
      if (isOpen(i - 1, j))
         wt.union(idx, up);  // Connect to upper node
    }
    // Connect last row to virtual node
    if (i == sz) 
      wt.union(idx, sz*sz + 1);   
    else {
      if (isOpen(i + 1, j))
         wt.union(idx, dn); // Connect to lower node
    }
    
    if (j > 1)
      if (isOpen(i, j-1))
          wt.union(idx, lef);
    if (j < sz) 
      if (isOpen(i, j + 1))
          wt.union(idx, rig);
  }
  public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
    if (i < 1 || i > sz || j < 1 || j > sz)
      throw new IndexOutOfBoundsException("index (" 
                                            + i + ","+ j+ ") is out of bounds");
    
    return grid[i][j] == 1;

  }
  
  public boolean isFull(int i, int j) {    // is site (row i, column j) full?
    if (i < 1 || i > sz || j < 1 || j > sz)
      throw new IndexOutOfBoundsException("index (" 
                                            + i + ","+ j+ ") is out of bounds");    
    
    return wt.connected(0, (i-1)*sz + j);
  }
  public boolean percolates() {            // does the system percolate?
    return wt.connected(0, sz*sz+1);
  }
  
}