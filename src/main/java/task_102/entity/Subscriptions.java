package task_102.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "Subscriptions")
public class Subscriptions implements Serializable {

    @EmbeddedId
    private Id id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscriptions() {
    }

    public Subscriptions(Id id, Student student, Course course, Date subscriptionDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "student=" + student.getName() +
                ", course=" + course.getName() +
                '}';
    }

    @Embeddable
    @Data
    public static class Id implements Serializable {
        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public Id() {
        }

        public Id(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }
    }
}