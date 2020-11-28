public class Course {
    private String courseID;
    private String courseName;
    private int capacity;

    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Course(String courseID, String courseName, int capacity){
        this.courseID = courseID;
        this.courseName = courseName;
        this.capacity = capacity;
    }

}
