public class Runner {
    public static void main(String[] args){
        CourseList list1 = new CourseList();
        list1.addCourse(10, new Course("1234", "math", 20));
        list1.addCourse(10, new Course("2345", "science", 30));
        list1.addCourse(10, new Course("3456", "english", 40));
        list1.addCourse(10, new Course("4567", "history", 50));

        list1.addCourse(1, new Course("1111", "bleh", 10));
        list1.changeCapacity("1111", 200);
        list1.removeCourse(1);
        System.out.println(list1.searchCourseName("english"));
    }
}
