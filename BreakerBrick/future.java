public class future{
public char[] flag = new char[26];
public  static char[][] genMatrix(char[][] mat, char str[]) {
	for (int i = 0; i < 25; i++) {
		int m = (i * 2) % 25;
		int f = (i * 7) % 25;
		mat[m/5][m%5] = str[f];
	}
   for(int i=0; i<mat.length; i++){
      for(int k=0; k<mat[0].length; k++)
         System.out.print(mat[i][k] + " ");
     System.out.println(); 
      }

   return mat;
}

/*public void genAuthString(char[][] mat, char[] auth) {
	auth[0] = mat[0][0] + mat[4][4];
	auth[1] = mat[2][1] + mat[0][2];
	auth[2] = mat[4][2] + mat[4][1];
	auth[3] = mat[1][3] + mat[3][1];
	auth[4] = mat[3][4] + mat[1][2];
	auth[5] = mat[1][0] + mat[2][3];
	auth[6] = mat[2][4] + mat[2][0];
	auth[7] = mat[3][3] + mat[3][2] + mat[0][3];
	auth[8] = mat[0][4] + mat[4][0] + mat[0][1];
	auth[9] = mat[3][3] + mat[2][0];
	auth[10] = mat[4][0] + mat[1][2];
	auth[11] = mat[0][4] + mat[4][1];
	auth[12] = mat[0][3] + mat[0][2];
	auth[13] = mat[3][0] + mat[2][0];
	auth[14] = mat[1][4] + mat[1][2];
	auth[15] = mat[4][3] + mat[2][3];
	auth[16] = mat[2][2] + mat[0][2];
	auth[17] = mat[1][1] + mat[4][1];
}*/

public static void main(String[] args) {
	char[] flag = new char[26];
	System.out.println("What's the flag: ");
	//scanf("%25s", flag);
	flag[25] = 0;

	// Setup matrix
	char[][] mat= new char[5][5];// Matrix for a jumbled string
	genMatrix(mat, flag);
   for(int i=0; i<mat.length; i++){
      for(int k=0; k<mat[0].length; k++)
         System.out.print(mat[i][k] + " ");
     System.out.println(); 
      }
      
	// Generate auth string
	char[] auth = new char[19]; // The auth string they generate
	auth[18] = 0; // null byte
	//genAuthString(mat, auth);	
	//char[] pass = "\x8b\xce\xb0\x89\x7b\xb0\xb0\xee\xbf\x92\x65\x9d\x9a\x99\x99\x94\xad\xe4\x00";
	
	// Check the input
	/*if (!strcmp(pass, auth)) {
		puts("Yup thats the flag!");
	} else {
		puts("Nope. Try again.");
	}*/
	
}
}