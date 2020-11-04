public class pair {
    int index_one;
    int index_two;

    pair(int index_one, int index_two){
        this.index_one = index_one;
        this.index_two = index_two;
    }

    void set(int i, int j){
        this.index_one = i;
        this.index_two = j;
    }

    int getIndexOne(){
        return this.index_one;
    }

    int getIndexTwo(){
        return this.index_two;
    }

    void printPair(){
        System.out.println("Index of the blocked process: " + this.index_one);
        System.out.println("The number of units requested by the process: " + this.index_two);
    }
}
