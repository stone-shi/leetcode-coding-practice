package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

/*
158. Read N Characters Given Read4 II - Call multiple times
Hard

Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.



Method read4:

The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.

The return value is the number of actual characters read.

Note that read4() has its own file pointer, much like FILE *fp in C.

Definition of read4:

    Parameter:  char[] buf
    Returns:    int

Note: buf[] is destination not source, the results from read4 will be copied to buf[]
Below is a high level example of how read4 works:

File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
char[] buf = new char[4]; // Create buffer with enough space to store characters
read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file


Method read:

By using the read4 method, implement the method read that reads n characters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.

The return value is the number of actual characters read.

Definition of read:

    Parameters:	char[] buf, int n
    Returns:	int

Note: buf[] is destination not source, you will need to write the results to buf[]


Example 1:

File file("abc");
Solution sol;
// Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
sol.read(buf, 1); // After calling your read method, buf should contain "a". We read a total of 1 character from the file, so return 1.
sol.read(buf, 2); // Now buf should contain "bc". We read a total of 2 characters from the file, so return 2.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
Example 2:

File file("abc");
Solution sol;
sol.read(buf, 4); // After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.


Note:

Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
The read function may be called multiple times.
Please remember to RESET your class variables declared in Solution, as static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
It is guaranteed that in a given test case the same buffer buf is called by read.

 */
public class Leet158ReadNCharactersGivenRead4II extends BasicStudy {

    public Leet158ReadNCharactersGivenRead4II() {


        String[] casesP1 = {"abcde", "abc", "abc"};
        int[][] casesP2 = {{1,4}, {1, 2, 1},{4,1}};
        String[] answer = {"1,a,4,bcde,", "1,a,2,bc,0,,", "3,abc,0,,"};


        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[2];
            p[0] = casesP1[i];
            p[1] = casesP2[i];
            addParameterAndAnswer(p, answer[i], true);
        }


    }

    /* 2nd try 5/26/2020
     */

    public class Reader42nd extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        char[] buf4 = new char[4];
        int buf4pt = 0;
        int buf4ct = 0;

        public int read(char[] buf, int n) {

            int ct = 0;
            int pt = 0;
            boolean eof = false;
            while ( ct < n && ! eof ){
                if (buf4ct > 0){
                    buf[pt++] = buf4[buf4pt++];
                    buf4ct--;
                    ct++;
                }else{
                    buf4pt = 0;
                    buf4ct = read4(buf4);
                    eof = buf4ct == 0;
                }
            }
            return ct;
        }
    }

    @CaseRunner
    public String runCase(String fileBuffer, int[] readCount ){

        Reader42nd solution = new Reader42nd();
        solution.setBuffer(fileBuffer.toCharArray());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < readCount.length; i++) {
            char[] buf = new char[readCount[i]];
            int ct = solution.read(buf, readCount[i]);
            sb.append(ct).append(',');
            for (int j = 0; j < ct; j++)
                sb.append(buf[j]);
            sb.append(',');
        }

        return sb.toString();
    }

    public class Reader4 {

        private char[] buffer;
        private int pt;

        public void setBuffer(char[] buf) {
            this.buffer = new char[buf.length];
            System.arraycopy(buf, 0, this.buffer, 0, buf.length);
            pt = 0;
        }

        protected int read4(char[] buf){
            int ct = 0;
            while (pt < this.buffer.length && ct < 4)
                buf[ct++] = this.buffer[pt++];
            return ct;
        }
    }

    public class Solution extends Reader4 {
        private char[] buffer = new char[4];
        private int pt = 0;
        private int bufCount = 0;
        public int read(char[] buf, int n) {

            int count = 0;
            while (count < n){
                if (bufCount == 0 ){
                    bufCount = read4(buffer);
                    if (bufCount == 0)
                        break;
                }

                buf[count++] = buffer[pt++];
                bufCount--;
                if (pt == 4)
                    pt = 0;
            }
            return count;
        }
    }
}
