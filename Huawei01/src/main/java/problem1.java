import java.util.Scanner;



public class problem1 {
    public static void main(String[] args) {
        //a1();
        //System.out.println(generateMatrix(3));
    }

    public static void a1(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] str = s.split(" ");
            int length = str[str.length - 1].length();
            System.out.println(length);
        }
    }

    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n){
            if (s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i == m;
    }

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        int left = 0;
        int right = index;
        char temp;
        char[] charArray = word.toCharArray();
        if (right >= 0) {
            while (left < right){
                temp = charArray[left];
                charArray[left] = charArray[right];
                charArray[right] = temp;
                left++;
                right--;
            }
            word = new String(charArray);
        }
        return word;
    }

    public static int[][] generateMatrix(int n) {
        int maxNum = n * n;
        int curNum = 1;
        int[][] matrix = new int[n][n];
        int row = 0,column = 0;
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};//右下左上,方向数组
        int directionIndex = 0;

        while(curNum <= maxNum){
            matrix[row][column] = curNum;
            curNum++;
            //计算下一个位置
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            //检查越界或者已被填充
            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0){
                directionIndex = (directionIndex + 1) % 4;
            }
            //移动
            row = row + directions[directionIndex][0];
            column = column + directions[directionIndex][1];
        }
        return matrix;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int revertedNumber = 0;
        while(x > revertedNumber){
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }

    public static void countChar() {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        String s3 = s1.toLowerCase().replaceAll(s2.toLowerCase(), "");
        System.out.println(s1.length() - s3.length());
    }


    public static void changeLine(){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = sb.append(str);
            int addZero = 8 - str.length() % 8;
            while(addZero > 0 && addZero < 8){
                sb2.append("0");
                addZero--;
            }
            String str2 = sb2.toString();
            while (str2.length() > 0) {
                System.out.println(str2.substring(0, 8));
                str2 = str2.substring(8);
            }
        }
    }
}
