import java.util.Comparator;
import java.util.PriorityQueue;

public class Heaps_PriorityQueue {

    static class Student implements Comparable<Student>{ // function overriding
        String name;
        int rank;

        public Student(String name, int rank){
            this.rank = rank;
            this.name = name;
        }

        @Override
        public int compareTo(Student s2){
            return this.rank - s2.rank;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3);
        pq.add(7);
        pq.add(1);
        pq.add(4);
        pq.add(2);

        System.out.print("Low Number, High Priority: ");
        while(!pq.isEmpty()){
            System.out.print(pq.remove() + " ");
        }
        System.out.println();

        pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(3);
        pq.add(7);
        pq.add(1);
        pq.add(4);
        pq.add(2);

        System.out.print("Low Number, Low Priority: ");
        while(!pq.isEmpty()){
            System.out.print(pq.remove() + " ");
        }
        System.out.println();

        PriorityQueue<Student> pq_student = new PriorityQueue<>();
        pq_student.add(new Student("A", 40));
        pq_student.add(new Student("B", 20));
        pq_student.add(new Student("C", 10));
        pq_student.add(new Student("D", 30));
        pq_student.add(new Student("E", 50));

        System.out.print("Low Rank, Low Priority (Student): ");
        while(!pq_student.isEmpty()){
            System.out.print(pq_student.remove().name + " ");
        }
        System.out.println();

    }
}
