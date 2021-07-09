package task_102.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int duration;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    @ManyToOne(cascade = ALL)
    private Teacher teacher;

    @Column(name = "students_count")
    private int studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;


    @ManyToMany(cascade = ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Subscriptions> subscriptions;

    public Course() {
    }

    public Course(int id, String name, int duration, String description, CourseType type, Teacher teacher, int studentsCount, int price, float pricePerHour, List<Student> students, List<Subscriptions> subscriptions) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.type = type;
        this.teacher = teacher;
        this.studentsCount = studentsCount;
        this.price = price;
        this.pricePerHour = pricePerHour;
        this.students = students;
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", teacher=" + teacher.getName() +
                '}';
    }
}

