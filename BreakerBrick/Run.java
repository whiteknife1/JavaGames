public class Run{
   public static void main(String[] args){
      String sg = "0000000000000000";
      String serialGuess = "";
      String str = "A878BF-28EB95-A77EF3";
      String str1 = "";
      for (int i = 0; i < sg.length(); i++)
      {
         serialGuess = sg.substring(i, i+1);
         char[] charArray = serialGuess.toCharArray();
         if (charArray[0] >= '0' && charArray[0] <= '9')
         {
            char chr = (char)((charArray[0] - 48 + 8) + 48);
            str1 += chr.toString();
         }
         else if (charArray[0] >= 'A' && charArray[0] <= 'F')
         {
            char chr1 = (char)((charArray[0] - 65 + 3) % '\u0006' + 65);
            str1 += ch1.toString();
         }
         else if (charArray[0] == '-')
         {
            str1 = string.Concat(str1, "-");
         }
      }
      System.out.print(sg);
   }
}