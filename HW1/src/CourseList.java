public class CourseList {
    private Course[] listOfCourses;

    // Getter
    public Course[] getListOfCourses(){
        return this.listOfCourses;
    }

    // Constructors
    public CourseList(){
        this.listOfCourses = new Course[10];
    }

    /**
     * Returns the number of !null elements in the list of courses.
     *
     * @return the amount of courses
     **/
    public int size(){
        int counter = 0;
        for(int i = 0; i < getListOfCourses().length; i++){
            if(getListOfCourses()[i] != null){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Adds a course to the list of courses given the list is not full
     *
     * @param i the index of the element to insert an element before
     * @param course the course to be inserted
     **/
    public void addCourse(int i, Course course){

        System.out.println("Operation: Add a course to index " + i + ".");
        System.out.println("Course: "
                + course.getCourseID() + ", "
                + course.getCourseName() + ", "
                + course.getCapacity());
        System.out.println();
        System.out.println(displayList(true));

        if(i < size() && size() < getListOfCourses().length){
            int j = size();
            while(j >= i){
                getListOfCourses()[j + 1] = getListOfCourses()[j];
                j--;
            }
            getListOfCourses()[i] = course;
        }
        else {
            getListOfCourses()[size()] = course;
        }
        System.out.println(displayList(false));
    }

    /**
     * Removes the course at the index
     *
     * @param i the index to set to null
     * @return whether the target element was set to null*/
    public boolean removeCourse(int i){
        System.out.println("Operation: Remove a course from index " + i + ".");
        Course temp = getCourseWithIndex(i);
        System.out.println("Course: "
                + temp.getCourseID() + ", "
                + temp.getCourseName() + ", "
                + temp.getCapacity());
        System.out.println();
        System.out.println(displayList(true));

        if(i > size()){
            System.out.println(displayList(false));
            return false;
        }
        else {
            for(int j = i; j < size(); j++){
                getListOfCourses()[j] = getListOfCourses()[j + 1];
            }
            System.out.println(displayList(false));
            return true;
        }
    }

    /**
     * Changes the capacity of a target course if it exists in the course list
     *
     * @param courseID the ID of the target course
     * @param capacity the new capacity of the target course
     * @return whether or not the target course exists in the course list
     **/
    public boolean changeCapacity(String courseID, int capacity){
        System.out.println("Operation: change the capacity of the course to " + capacity);
        int courseIndex = searchCourseID(courseID);
        System.out.println(displayList(true));
        if(courseIndex != -1){
            getCourseWithIndex(courseIndex).setCapacity(capacity);
            System.out.println(displayList(false));
            return true;
        }
        System.out.println(displayList(false));
        return false;
    }


    public Course getCourseWithIndex(int i){
        return getListOfCourses()[i];
    }

    /**
     * Returns the index of the course matching the search ID
     *
     * @param courseID the ID of the course
     * @return the index of the matched course
     **/
    public int searchCourseID(String courseID){
        for(int i = 0; i < size(); i++){
            if(getCourseWithIndex(i).getCourseID().equals(courseID)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the course matching the search name
     *
     * @param courseName the name of the target course
     * @return the index of the matched course
     **/
    public int searchCourseName(String courseName){
        for(int i = 0; i < size(); i++){
            if(getCourseWithIndex(i).getCourseName().equals(courseName)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a String to display the course list either before or after an operation
     *
     * @param before whether the operation has occurred or not
     * @return the display string
     **/
    public String displayList(boolean before){
        String beforeOrAfter = "";
        if(before){
            beforeOrAfter = "before ";
        }
        else{
            beforeOrAfter = "after ";
        }

        String output = "List " + beforeOrAfter + "the operation:\n";
        for(int i = 0; i < size(); i++){
            Course temp = getCourseWithIndex(i);
            output = output + i +". " + "Course: "
                    + temp.getCourseID() + ", "
                    + temp.getCourseName() + ", "
                    + temp.getCapacity() + "\n";
        }
        output = output + "\n";
        return output;
    }
}
