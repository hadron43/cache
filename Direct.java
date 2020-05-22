import java.util.*;

public class Direct {

    static class Block{
        int data[];

        Block(int size){
            data = new int[size];
        }

        int read(int offset){
            if(offset>=data.length){
                System.err.println("System Error! Invalid Offset!");
                return 0;
            }

            return data[offset];
        }

        void write(int offset, int value){
            if(offset>=data.length){
                System.err.println("System Error! Invalid Offset!");
                return;
            }

            data[offset] = value;
        }

        void print(){
            for(int i=0; i<data.length; ++i){
                System.out.print("|"+i+" "+data[i]);
            }
            System.out.print("|");
        }
    }

    static class Cache{
        boolean valid[];
        int counter[];
        int tag[];      //Block Address
        Block data[];   //Data

        static int tagSize;
        static int offsetSize;
        static int powersOfTwo[];

        private void powInit(){
            powersOfTwo = new int[31];
            powersOfTwo[0] = 1;
            for(int i=1; i<31; ++i)
                powersOfTwo[i] = powersOfTwo[i-1]*2;
        }

        int S, CL, B;
        Cache(int S, int CL, int B){
            //Checking for consistency in given constraints
            if(S!=(CL*B*8)){
                System.err.println("Sizes are inconsistent! Aborting!!");
                System.exit(0);
            }

            offsetSize = (int)(Math.log(B)/Math.log(2));  
            tag = new int[CL];
            data = new Block[CL];
            for(int i=0; i<CL; ++i)
                data[i] = new Block(B);

            valid = new boolean[CL];
            counter = new int[CL];

            this.B = B;
            this.CL = CL;
            this.S = S;
            powInit();
        }

        static int cycleNo = 0;

        int lookup(int address){
            int tagValue = address>>(offsetSize);
            int noOfIndexBits = (int)(Math.log(CL)/Math.log(2));
            int index = tagValue & (powersOfTwo[noOfIndexBits] - 1);
            tagValue = tagValue >> noOfIndexBits;

            if(tag[index]!=tagValue)
                index = -1;

            ++cycleNo;
            if(cycleNo==tag.length/2){
                for(int i=0; i<counter.length; ++i)
                    if(counter[i]>0)
                        counter[i]--;
                
                cycleNo = 0;
            }
            if(index!=-1)
                counter[index] = 15;
            return index;
        }

        void read(int address){
            int index = lookup(address);
            if(index==-1 || valid[index]==false){
                System.out.println("Cache Miss!");
                return;
            }

            int offset = address & (powersOfTwo[offsetSize]-1);
            System.out.println(address+" --> "+data[index].read(offset));
        }

        void write(int address, int value){
            int index = lookup(address);
            if(index==-1){
                int tagValue = address>>(offsetSize);
                int noOfIndexBits = (int)(Math.log(CL)/Math.log(2));
                index = tagValue & (powersOfTwo[noOfIndexBits] - 1);
                tagValue = tagValue >> noOfIndexBits;

                tag[index] = tagValue;
                data[index] = new Block(B);
            }

            valid[index] = true;
            int offset = address & (powersOfTwo[offsetSize]-1);
            // For Debugging Purpose
            // System.out.println(index+" "+powersOfTwo[offsetSize]+" "+offset);
            data[index].write(offset, value);
        }

        void print(){
            for(int i=0; i<data.length; ++i){
                System.out.print(tag[i]+"\t"+valid[i]+"\t");
                data[i].print();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter S, CL, B: ");
        int S = sc.nextInt();
        int CL = sc.nextInt();
        int B = sc.nextInt();

        Cache cache = new Cache(S, CL, B);

        boolean Q = true;

        while(Q){
            String command = sc.next();

            if(command.equals("print")){
                cache.print();
                continue;
            }

            int address = sc.nextInt();
            if(command.equals("read")){
                cache.read(address);
            }
            else if(command.equals("write")){
                int data = sc.nextInt();
                cache.write(address, data);
            }
            else if(command.equals("exit")){
                cache.print();
                Q = false;
            }
            else
                System.out.println("Invalid Command!");
        }
        sc.close();
    }
}